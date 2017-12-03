package com.asaunin.classifier.service;

import com.asaunin.classifier.domain.SubCategory;
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
@SpringBootTest(classes = CategoryService.class)
@EnableConfigurationProperties
public class CategoryServiceTest {

    @Autowired CategoryService cache;

    @Before
    public void clearCache() {
        cache.clear();
        assertTrue(cache.isEmpty());
        assertThat(cache.size()).isZero();
    }

    @Test
    public void mapToKey() {
        final SubCategory subCategory = SubCategory.builder()
                .id(1)
                .name("One-time-password")
                .category("Time-sensitive")
                .build();

        assertThat(cache.mapToKey(subCategory)).isEqualTo(1);
    }

    @Test
    public void defaultCategoryIdIsDefinedInProperties() {
        final Integer defaultSubCategoryId = cache.getDefaultSubCategoryId();

        assertThat(defaultSubCategoryId).isNotNull();
    }

    @Test
    public void getDefaultSubCategoryReturnsNullWhenNotCached() {
        final SubCategory defaultSubCategory = cache.getDefaultSubCategory();

        assertThat(defaultSubCategory).isNull();
    }

    @Test
    public void getDefaultSubCategoryReturnsNullWhenCached() {
        final Integer defaultSubCategoryId = cache.getDefaultSubCategoryId();
        final SubCategory expectedSubCategory = SubCategory.builder()
                .id(defaultSubCategoryId)
                .name("One-time-password")
                .category("Time-sensitive")
                .build();

        cache.insert(expectedSubCategory);

        final SubCategory defaultSubCategory = cache.getDefaultSubCategory();

        assertThat(defaultSubCategory).isEqualTo(expectedSubCategory);
    }

    @Test
    public void findByIdReturnsSubCategoryWhenPresent() {
        final Integer defaultSubCategoryId = cache.getDefaultSubCategoryId();
        final SubCategory defaultSubCategory = SubCategory.builder()
                .id(defaultSubCategoryId)
                .name("One-time-password")
                .category("Time-sensitive")
                .build();

        cache.insert(defaultSubCategory);

        assertThat(cache.findById(defaultSubCategoryId)).isEqualTo(defaultSubCategory);

        assertThat(cache.findById(defaultSubCategoryId + 1)).isNull();
    }

    @Test
    public void findByIdOrDefaultReturnsSubCategoryWhenPresentOrDefault() {
        final Integer defaultSubCategoryId = cache.getDefaultSubCategoryId();
        final SubCategory defaultSubCategory = SubCategory.builder()
                .id(defaultSubCategoryId)
                .name("One-time-password")
                .category("Time-sensitive")
                .build();
        cache.insert(defaultSubCategory);

        final Integer subCategoryId = defaultSubCategoryId + 1;
        final SubCategory subCategory = SubCategory.builder()
                .id(subCategoryId)
                .name("Two-time-password")
                .category("Time-sensitive")
                .build();
        cache.insert(subCategory);

        assertThat(cache.findByIdOrDefault(subCategoryId)).isEqualTo(subCategory);

        assertThat(cache.findByIdOrDefault(subCategoryId + 1)).isEqualTo(defaultSubCategory);
    }

}