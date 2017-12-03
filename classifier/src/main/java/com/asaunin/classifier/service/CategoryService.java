package com.asaunin.classifier.service;

import com.asaunin.cache.DeletableCache;
import com.asaunin.classifier.domain.SubCategory;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Log4j2
@Service
public class CategoryService extends DeletableCache<Integer, SubCategory> {

    @Getter
    @Value("${classifier.default.sub-category-id:1}")
    private Integer defaultSubCategoryId;

    public CategoryService() {
        super(new ConcurrentHashMap<>());
    }

    @Override
    protected Integer mapToKey(SubCategory entity) {
        return entity.getId();
    }

    public SubCategory findById(Integer subCategoryId) {
        return get(subCategoryId);
    }

    public SubCategory findByIdOrDefault(Integer subCategoryId) {
        return Optional.ofNullable(get(subCategoryId)).orElse(getDefaultSubCategory());
    }

    public SubCategory getDefaultSubCategory() {
        return findById(defaultSubCategoryId);
    }

}
