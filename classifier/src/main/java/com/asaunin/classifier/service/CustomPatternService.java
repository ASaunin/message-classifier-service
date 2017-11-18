package com.asaunin.classifier.service;

import com.asaunin.cache.SimpleLoadableCache;
import com.asaunin.classifier.domain.CustomPattern;
import org.springframework.stereotype.Service;

@Service
public class CustomPatternService extends SimpleLoadableCache<Integer, CustomPattern> {

    public CustomPatternService() {
        super(CustomPattern::getId);
    }

    public Integer findSubCategory(String country, String senderId, String text) {
        return 0;
    }

}