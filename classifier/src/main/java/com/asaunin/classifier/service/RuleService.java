package com.asaunin.classifier.service;

import com.asaunin.classifier.cache.ListableCache;
import com.asaunin.classifier.domain.Country;
import com.asaunin.classifier.domain.Rule;
import com.asaunin.classifier.domain.SubAccountCountryPair;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
public class RuleService extends ListableCache<SubAccountCountryPair, Rule> {

    public RuleService() {
        super(new ConcurrentHashMap<>());
    }

    @Override
    protected SubAccountCountryPair mapToKey(Rule entity) {
        return SubAccountCountryPair.of(entity.getSubAccountId(), entity.getCountry());
    }

    public Stream<Rule> findRulesBy(Integer subAccountId, Country country) {
        final SubAccountCountryPair subAccountCountry = SubAccountCountryPair.of(subAccountId, country);
        List<Rule> rules = Optional.ofNullable(get(subAccountCountry)).orElse(Collections.emptyList());

        if (CollectionUtils.isEmpty(rules)) {
            if (!subAccountCountry.hasAnyCountry()) {
                final SubAccountCountryPair subAccountAnyCountry = SubAccountCountryPair.of(subAccountId);
                rules = Optional.ofNullable(get(subAccountAnyCountry)).orElse(Collections.emptyList());
            }
        }

        return rules.stream();
    }

}
