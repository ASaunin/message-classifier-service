package com.asaunin.classifier.service;

import com.asaunin.cache.DeletableCache;
import com.asaunin.classifier.domain.CustomPattern;
import org.springframework.stereotype.Service;

@Service
public class CustomPatternService extends DeletableCache<Integer, CustomPattern> {

    public CustomPatternService() {
        super(CustomPattern::getId);
    }

    public Integer findSubCategory(String country, String senderId, String text) {
        return 0;
    }

}
