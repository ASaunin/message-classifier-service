package com.asaunin.classifier.repository.h2;

import com.asaunin.classifier.domain.CustomPattern;
import com.asaunin.classifier.domain.Rule;
import com.asaunin.classifier.domain.SubCategory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class CustomPatternH2RepositoryTest extends H2RepositoryTestConfiguration {

    private final AtomicInteger idCounter = new AtomicInteger(0);

    @Autowired private SubCategoryH2Repository subCategoryRepo;
    @Autowired private RuleH2Repository ruleRepo;
    @Autowired private CustomPatternH2Repository patternRepo;

    private SubCategory subCategory;
    private Rule rule;
    private CustomPattern pattern;

    private ZonedDateTime updatedAt;

    @Before
    public void setUp() {
        updatedAt = ZonedDateTime.now();
        subCategory = SubCategory.builder()
                .id(idCounter.incrementAndGet())
                .name("OTP")
                .category("TS")
                .build();
        subCategoryRepo.save(subCategory);

        rule = Rule.builder()
                .id(idCounter.incrementAndGet())
                .subCategoryId(subCategory.getId())
                .subAccountId(1)
                .country("TS")
                .build();
        ruleRepo.save(rule);

        pattern = CustomPattern.builder()
                .id(idCounter.incrementAndGet())
                .ruleId(rule.getId())
                .sender("VIBER")
                .regex("(.*)")
                .build();
        patternRepo.save(pattern);
    }

    @Test
    public void findAll() {
        final CustomPattern anotherPattern = CustomPattern.builder()
                .id(4)
                .ruleId(rule.getId())
                .sender("WhatsUp")
                .regex("(.*)")
                .build();
        patternRepo.save(anotherPattern);

        final Collection<CustomPattern> list = patternRepo.findAll();
        assertThat(list)
                .hasSize(2)
                .containsExactly(pattern, anotherPattern)
                .usingElementComparatorIgnoringFields("updatedAt");
    }

    @Test
    public void findByUpdatedAtAfter() {
        Collection<CustomPattern> list = patternRepo.findByUpdatedAtAfter(updatedAt.minusDays(1));
        assertThat(list)
                .hasSize(1)
                .containsExactly(pattern)
                .usingElementComparatorIgnoringFields("updatedAt");

        list = patternRepo.findByUpdatedAtAfter(ZonedDateTime.now());
        assertThat(list).isEmpty();
    }

}