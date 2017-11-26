package com.asaunin.classifier.service;

import com.asaunin.cache.LoadableEntityCache;
import com.asaunin.classifier.ClassifierCacheFactory;
import com.asaunin.classifier.repository.LoadableRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.function.BiConsumer;

@Log4j2
@Service
@EnableScheduling
public class DataProvider {

    private final ClassifierCacheFactory factory;

    private ZonedDateTime lastUpdated;

    public DataProvider(@Autowired ClassifierCacheFactory factory) {
        this.factory = factory;
    }

    @Scheduled(
            initialDelayString = "${classifier.data-update.initial-delay}",
            fixedRateString = "${classifier.data-update.fixed-rate}")
    @ConditionalOnProperty("${classifier.data-update.enabled}")
    @SuppressWarnings("unchecked")
    public void update() {
        final boolean uploadSucceed = upload((cache, repo) ->
                cache.upload(repo.findByUpdatedAtAfter(lastUpdated)));
        if (uploadSucceed) {
            log.info("Scheduled data update proceeded");
        } else {
            log.error("Scheduled data update failed");
        }
    }

    public boolean load() throws Exception {
        return upload((cache, repo) -> cache.upload(repo.findAll()));
    }

    private boolean upload(BiConsumer<LoadableEntityCache, LoadableRepository> loader) {
        final ZonedDateTime uploadStarted = ZonedDateTime.now();
        final Iterable<Class> types = factory.getTypes();
        boolean uploadSucceed = true;
        for (Class type : types) {
            final LoadableEntityCache cache = factory.getCache(type);
            final LoadableRepository repo = factory.getRepository(type);
            try {
                loader.accept(cache, repo);
            } catch (Exception ex) {
                log.warn("Failed to upload data from: {} cause: {}", repo.getClass().getSimpleName(), ex.getMessage());
                uploadSucceed = false;
            }
        }
        this.lastUpdated = uploadStarted;
        return uploadSucceed;
    }

}
