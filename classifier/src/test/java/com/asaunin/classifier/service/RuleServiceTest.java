package com.asaunin.classifier.service;

import com.asaunin.classifier.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = RuleService.class)
@EnableConfigurationProperties
public class RuleServiceTest {

    @Autowired private RuleService cache;

    @Before
    public void clearCache() {
        cache.clear();
        assertTrue(cache.isEmpty());
        assertThat(cache.size()).isZero();
    }

    @Test
    public void mapToKey() {
        Rule rule = Rule.builder()
                .id(1)
                .country("RU")
                .subAccountId(1)
                .subCategoryId(2)
                .build();

        assertThat(cache.mapToKey(rule)).isEqualTo(SubAccountCountryPair.of(1, Country.of("RU")));

        rule = Rule.builder()
                .id(1)
                .subAccountId(10)
                .subCategoryId(2)
                .build();

        assertThat(cache.mapToKey(rule)).isEqualTo(SubAccountCountryPair.of(10, AnyCountry.getInstance()));
    }

    @Test
    public void findRulesBy() {
        final Rule pattern_1_Ru = Rule.builder()
                .id(1)
                .country("RU")
                .subAccountId(1)
                .build();
        cache.insert(pattern_1_Ru);

        assertThat(cache.findRulesBy(1, Country.of("RU"))).containsExactly(pattern_1_Ru);
        assertThat(cache.findRulesBy(1, AnyCountry.getInstance())).isEmpty();

        final Rule pattern_2_Sn = Rule.builder()
                .id(2)
                .country("SN")
                .subAccountId(2)
                .build();
        cache.insert(pattern_2_Sn);

        assertThat(cache.findRulesBy(1, Country.of("SN"))).isEmpty();
        assertThat(cache.findRulesBy(2, Country.of("RU"))).isEmpty();
        assertThat(cache.findRulesBy(2, Country.of("SN"))).containsExactly(pattern_2_Sn);
        assertThat(cache.findRulesBy(2, AnyCountry.getInstance())).isEmpty();

        final Rule pattern_3_Any = Rule.builder()
                .id(3)
                .subAccountId(3)
                .build();
        cache.insert(pattern_3_Any);

        assertThat(cache.findRulesBy(3, Country.of("SN"))).containsExactly(pattern_3_Any);
        assertThat(cache.findRulesBy(3, Country.of("RU"))).containsExactly(pattern_3_Any);
        assertThat(cache.findRulesBy(3, AnyCountry.getInstance())).containsExactly(pattern_3_Any);

        assertThat(cache.findRulesBy(4, Country.of("SN"))).isEmpty();
        assertThat(cache.findRulesBy(4, Country.of("RU"))).isEmpty();
        assertThat(cache.findRulesBy(4, AnyCountry.getInstance())).isEmpty();

    }

}