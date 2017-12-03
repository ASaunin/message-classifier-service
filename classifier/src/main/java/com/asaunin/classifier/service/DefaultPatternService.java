package com.asaunin.classifier.service;

import com.asaunin.classifier.cache.ListableCache;
import com.asaunin.classifier.domain.Country;
import com.asaunin.classifier.domain.DefaultPattern;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
public class DefaultPatternService extends ListableCache<Country, DefaultPattern> {

    public DefaultPatternService() {
        super(new ConcurrentHashMap<>());
    }

    @Override
    protected Country mapToKey(DefaultPattern entity) {
        return entity.getCountry();
    }

    public Stream<DefaultPattern> findPatternsBy(Country country) {
        return Optional.ofNullable(get(country))
                .orElse(Collections.emptyList())
                .stream();
    }
}
