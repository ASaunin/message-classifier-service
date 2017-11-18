package com.asaunin.classifier.service;

import com.asaunin.cache.SimpleLoadableCache;
import com.asaunin.classifier.ClassifierCacheFactory;
import com.asaunin.classifier.domain.BaseEntity;
import com.asaunin.classifier.repository.LoadableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Collection;

@Service
@EnableScheduling
public class DataProvider implements Loadable {

    private final ClassifierCacheFactory factory;

    private ZonedDateTime lastUpdated;

    public DataProvider(@Autowired ClassifierCacheFactory factory) {
        this.factory = factory;
    }

    @Scheduled(initialDelayString = "${classifier.data-update.initial-delay}",
            fixedRateString = "${classifier.data-update.fixed-rate}")
    @ConditionalOnProperty("${classifier.data-update.enabled}")
    public void update() {
//        updateAfter();
    }

    @Override
    public void loadData() throws Exception {
        final Iterable<Class> types = factory.getTypes();
        for (Class type : types) {
            final SimpleLoadableCache cache = (SimpleLoadableCache) factory.getCache(type);
            final LoadableRepository<BaseEntity, ?> repo = factory.getRepository(type);
            final Collection<BaseEntity> data = repo.findAll();
            cache.load(data);

        }
        this.lastUpdated = ZonedDateTime.now();
    }

    public void updateAfter(ZonedDateTime dateTime) throws Exception {

    }

}
