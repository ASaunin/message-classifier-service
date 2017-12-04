package com.asaunin.classifier.repository.h2;

import com.asaunin.classifier.domain.DefaultPattern;
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
public class DefaultPatternH2RepositoryTest extends H2RepositoryTestConfiguration {

    private final AtomicInteger idCounter = new AtomicInteger(0);

    @Autowired private SubCategoryH2Repository subCategoryRepo;
    @Autowired private DefaultPatternH2Repository patternRepo;

    private SubCategory subCategory;
    private DefaultPattern pattern;

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

        pattern = DefaultPattern.builder()
                .id(idCounter.incrementAndGet())
                .subCategoryId(subCategory.getId())
                .sender("VIBER")
                .country("RU")
                .text("(.*)")
                .build();
        patternRepo.save(pattern);
    }

    @Test
    public void findAll() {
        final DefaultPattern anotherPattern = DefaultPattern.builder()
                .id(3)
                .subCategoryId(subCategory.getId())
                .sender("WhatsUp")
                .country("RU")
                .text("(.*)")
                .build();
        patternRepo.save(anotherPattern);

        final Collection<DefaultPattern> list = patternRepo.findAll();
        assertThat(list)
                .hasSize(2)
                .containsExactly(pattern, anotherPattern)
                .usingElementComparatorIgnoringFields("updatedAt");
    }

    @Test
    public void findByUpdatedAtAfter() {
        Collection<DefaultPattern> list = patternRepo.findByUpdatedAtAfter(updatedAt.minusDays(1));
        assertThat(list)
                .hasSize(1)
                .containsExactly(pattern);

        list = patternRepo.findByUpdatedAtAfter(ZonedDateTime.now());
        assertThat(list).isEmpty();
    }

}