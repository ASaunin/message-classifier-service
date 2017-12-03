package com.asaunin.classifier.repository.h2;

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
public class SubCategoryH2RepositoryTest extends H2RepositoryTestConfiguration {

    private final AtomicInteger idCounter = new AtomicInteger(0);

    @Autowired private SubCategoryH2Repository subCategoryRepo;

    private SubCategory subCategory;

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
    }

    @Test
    public void findAll() {
        final SubCategory anotherSubCategory = SubCategory.builder()
                .id(2)
                .name("DEFAULT")
                .category("DEF")
                .build();
        subCategoryRepo.save(anotherSubCategory);

        final Collection<SubCategory> list = subCategoryRepo.findAll();
        assertThat(list)
                .hasSize(2)
                .containsExactly(subCategory, anotherSubCategory)
                .usingElementComparatorIgnoringFields("updatedAt");
    }

    @Test
    public void findByUpdatedAtAfter() {
        Collection<SubCategory> list = subCategoryRepo.findByUpdatedAtAfter(updatedAt.minusDays(1));
        assertThat(list)
                .hasSize(1)
                .containsExactly(subCategory);

        list = subCategoryRepo.findByUpdatedAtAfter(ZonedDateTime.now());
        assertThat(list).isEmpty();
    }

}