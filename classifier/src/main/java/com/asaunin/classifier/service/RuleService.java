package com.asaunin.classifier.service;

import com.asaunin.cache.SimpleLoadableCache;
import com.asaunin.classifier.domain.Rule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService extends SimpleLoadableCache<Integer, Rule> {

    public RuleService() {
        super(Rule::getId);
    }

    public List<Rule> findBySubAccountId(Integer subAccountId) {
        return null;
    }

}
