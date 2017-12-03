package com.asaunin.classifier.service;

import com.asaunin.classifier.domain.AnyCountry;
import com.asaunin.classifier.domain.Country;
import com.asaunin.classifier.domain.DefaultPattern;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DefaultPatternService.class)
@EnableConfigurationProperties
public class DefaultPatternServiceTest {

    @Autowired private DefaultPatternService cache;

    @Before
    public void clearCache() {
        cache.clear();
        assertTrue(cache.isEmpty());
        assertThat(cache.size()).isZero();
    }

    @Test
    public void mapToKey() {
        DefaultPattern pattern = DefaultPattern.builder()
                .id(1)
                .country("RU")
                .regex(".*")
                .sender("VIBER")
                .subCategoryId(1)
                .build();

        assertThat(cache.mapToKey(pattern)).isEqualTo(Country.of("RU"));

        pattern = DefaultPattern.builder()
                .id(1)
                .regex(".*")
                .sender("VIBER")
                .subCategoryId(1)
                .build();

        assertThat(cache.mapToKey(pattern)).isEqualTo(AnyCountry.getInstance());
    }

    @Test
    public void findPatternsBy() {
        final DefaultPattern patternRu = DefaultPattern.builder()
                .id(1)
                .country("RU")
                .regex(".*")
                .sender("VIBER")
                .subCategoryId(1)
                .build();
        cache.insert(patternRu);

        final DefaultPattern patternAny = DefaultPattern.builder()
                .id(2)
                .regex("123")
                .updatedAt(ZonedDateTime.now())
                .sender("Alex Saunin")
                .build();
        cache.insert(patternAny);

        final List<DefaultPattern> listRu = cache.findPatternsBy(Country.of("RU")).collect(Collectors.toList());
        assertThat(listRu).containsExactly(patternRu);

        final List<DefaultPattern> listAny = cache.findPatternsBy(AnyCountry.getInstance()).collect(Collectors.toList());
        assertThat(listAny).containsExactly(patternAny);

        final List<DefaultPattern> listSg = cache.findPatternsBy(Country.of("SG")).collect(Collectors.toList());
        assertThat(listSg).isEmpty();
    }

}