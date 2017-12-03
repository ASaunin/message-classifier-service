package com.asaunin.classifier.service;

import com.asaunin.classifier.domain.CustomPattern;
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
@SpringBootTest(classes = CustomPatternService.class)
@EnableConfigurationProperties
public class CustomPatternServiceTest {

    @Autowired private CustomPatternService cache;

    @Before
    public void clearCache() {
        cache.clear();
        assertTrue(cache.isEmpty());
        assertThat(cache.size()).isZero();
    }

    @Test
    public void mapToKey() {
        final CustomPattern pattern = CustomPattern.builder()
                .id(1)
                .ruleId(1)
                .regex(".*")
                .sender("VIBER")
                .build();

        assertThat(cache.mapToKey(pattern)).isEqualTo(1);
    }

    @Test
    public void findPatternsBy() {
        final CustomPattern patternRu = CustomPattern.builder()
                .id(1)
                .ruleId(1)
                .regex(".*")
                .sender("VIBER")
                .build();
        cache.insert(patternRu);

        final CustomPattern patternAny = CustomPattern.builder()
                .id(2)
                .ruleId(2)
                .regex("123")
                .sender("Alex Saunin")
                .updatedAt(ZonedDateTime.now())
                .build();
        cache.insert(patternAny);

        final List<CustomPattern> listRu = cache.findPatternsBy(1).collect(Collectors.toList());
        assertThat(listRu).containsExactly(patternRu);

        final List<CustomPattern> listAny = cache.findPatternsBy(2).collect(Collectors.toList());
        assertThat(listAny).containsExactly(patternAny);

        final List<CustomPattern> listSg = cache.findPatternsBy(3).collect(Collectors.toList());
        assertThat(listSg).isEmpty();
    }

}