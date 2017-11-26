package com.asaunin.classifier.service;

import com.asaunin.cache.DeletableCache;
import com.asaunin.classifier.domain.SubCategory;
import org.springframework.stereotype.Service;

@Service
public class CategoryService extends DeletableCache<Integer, SubCategory> {

    public CategoryService() {
        super(SubCategory::getId);
    }

    public SubCategory findById(Integer subCategoryId) {
        return SubCategory.builder()
                .name("OTP")
                .category("TS")
                .build();
    }

}
