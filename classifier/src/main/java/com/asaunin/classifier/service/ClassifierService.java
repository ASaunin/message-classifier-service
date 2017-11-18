package com.asaunin.classifier.service;

import com.asaunin.classifier.domain.Rule;
import com.asaunin.classifier.domain.SubCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassifierService {

    private final CategoryService categoryService;
    private final RuleService ruleService;
    private final DefaultPatternService defaultPatternService;
    private final CustomPatternService customPatternService;

    public ClassifierService(@Autowired CategoryService categoryService,
                             @Autowired RuleService ruleService,
                             @Autowired DefaultPatternService defaultPatternService,
                             @Autowired CustomPatternService customPatternService) {
        this.categoryService = categoryService;
        this.ruleService = ruleService;
        this.defaultPatternService = defaultPatternService;
        this.customPatternService = customPatternService;
    }

    public SubCategory classify(Integer subAccountId, String country, String senderId, String text) {

        final List<Rule> rules = ruleService.findBySubAccountId(subAccountId);

        final Integer subCategoryId = defaultPatternService.findSubCategory(country, senderId, text);

        final SubCategory subCategory = categoryService.findById(subCategoryId);

        return subCategory;
    }

}
