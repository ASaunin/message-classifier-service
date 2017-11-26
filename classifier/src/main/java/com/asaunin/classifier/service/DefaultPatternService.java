package com.asaunin.classifier.service;

import com.asaunin.cache.DeletableCache;
import com.asaunin.classifier.domain.DefaultPattern;
import org.springframework.stereotype.Service;

@Service
public class DefaultPatternService extends DeletableCache<Integer, DefaultPattern> {

    public DefaultPatternService() {
        super(DefaultPattern::getId);
    }

    public Integer findSubCategory(String country, String senderId, String text) {
        return 0;
    }

}
