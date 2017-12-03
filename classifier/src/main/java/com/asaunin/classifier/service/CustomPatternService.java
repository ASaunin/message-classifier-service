package com.asaunin.classifier.service;

import com.asaunin.classifier.cache.ListableCache;
import com.asaunin.classifier.domain.CustomPattern;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
public class CustomPatternService extends ListableCache<Integer, CustomPattern> {

    public CustomPatternService() {
        super(new ConcurrentHashMap<>());
    }

    @Override
    protected Integer mapToKey(CustomPattern entity) {
        return entity.getRuleId();
    }

    public Stream<CustomPattern> findPatternsBy(Integer ruleId) {
        return Optional.ofNullable(get(ruleId))
                .orElse(Collections.emptyList())
                .stream();
    }

}
