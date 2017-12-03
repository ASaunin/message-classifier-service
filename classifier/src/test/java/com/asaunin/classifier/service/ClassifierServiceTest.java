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

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = {
                ClassifierService.class,
                CategoryService.class,
                RuleService.class,
                DefaultPatternService.class,
                CustomPatternService.class})
@EnableConfigurationProperties
public class ClassifierServiceTest {

    @Autowired private CategoryService categoryService;
    @Autowired private RuleService ruleService;
    @Autowired private DefaultPatternService defaultPatternService;
    @Autowired private CustomPatternService customPatternService;

    @Autowired private ClassifierService classifier;

    private SubCategory undef;
    private SubCategory otp;
    private SubCategory nts;

    @Before
    public void initData() {
        clearCache();
        createSubCategories();
        createRules();
        createCustomPatterns();
        createDefaultPatterns();
    }

    private void clearCache() {
        categoryService.clear();
        ruleService.clear();
        defaultPatternService.clear();
        customPatternService.clear();
    }

    private void createSubCategories() {
        categoryService.insert(undef =
                SubCategory.builder()
                        .id(0)
                        .name("Undefined")
                        .category("DEF") // Default category
                        .build());

        categoryService.insert(
                SubCategory.builder()
                        .id(1)
                        .name("Gambling")
                        .category("BLK") // Blocked category
                        .build());

        categoryService.insert(
                SubCategory.builder()
                        .id(2)
                        .name("Generic")
                        .category("TS") // Default category
                        .build());

        categoryService.insert(
                SubCategory.builder()
                        .id(3)
                        .name("Marketing")
                        .category("MKT") // Marketing category
                        .build());

        categoryService.insert(otp =
                SubCategory.builder()
                        .id(4)
                        .name("One-Time-Password")
                        .category("TS") // Time sensitive category
                        .build());

        categoryService.insert(
                SubCategory.builder()
                        .id(5)
                        .name("Spam")
                        .category("BLK")// Blocked category
                        .build());

        categoryService.insert(nts =
                SubCategory.builder()
                        .id(6)
                        .name("Notification")
                        .category("TS") // Time sensitive category
                        .build());
    }

    private void createRules() {
        ruleService.insert(
                Rule.builder()
                        .id(1)
                        .subCategoryId(-1)
                        .subAccountId(1)
                        .country("Custom any text & sender matching & no category")
                        .build());

        ruleService.insert(
                Rule.builder()
                        .id(2)
                        .subCategoryId(4)
                        .subAccountId(1)
                        .country("Custom any text & sender matching")
                        .build());

        ruleService.insert(
                Rule.builder()
                        .id(3)
                        .subCategoryId(4)
                        .subAccountId(1)
                        .country("Custom any text matching")
                        .build());

        ruleService.insert(
                Rule.builder()
                        .id(4)
                        .subCategoryId(4)
                        .subAccountId(1)
                        .country("Custom any sender matching")
                        .build());

        ruleService.insert(
                Rule.builder()
                        .id(5)
                        .subCategoryId(4)
                        .subAccountId(1)
                        .country("Custom matching")
                        .build());

        ruleService.insert(
                Rule.builder()
                        .id(6)
                        .subCategoryId(4)
                        .subAccountId(1)
                        .country("No custom matching")
                        .build());

        ruleService.insert(
                Rule.builder()
                        .id(7)
                        .subCategoryId(4)
                        .subAccountId(2)
                        .build());
    }

    private void createCustomPatterns() {
        customPatternService.insert(
                CustomPattern.builder()
                        .id(1)
                        .ruleId(1)
                        .sender(".*")
                        .regex(".*")
                        .build());

        customPatternService.insert(
                CustomPattern.builder()
                        .id(2)
                        .ruleId(2)
                        .sender(".*")
                        .regex(".*")
                        .build());

        customPatternService.insert(
                CustomPattern.builder()
                        .id(3)
                        .ruleId(3)
                        .sender("VIBER")
                        .regex(".*")
                        .build());

        customPatternService.insert(
                CustomPattern.builder()
                        .id(4)
                        .ruleId(4)
                        .sender(".*")
                        .regex("(.*)(Hello, Alex Saunin!)(.*)")
                        .build());

        customPatternService.insert(
                CustomPattern.builder()
                        .id(5)
                        .ruleId(5)
                        .sender("VIBER")
                        .regex("(.*)(Hello, Alex Saunin!)(.*)")
                        .build());

        customPatternService.insert(
                CustomPattern.builder()
                        .id(7)
                        .ruleId(7)
                        .sender("VIBER")
                        .regex("(.*)(Hello, Alex Saunin!)(.*)")
                        .build());
    }

    private void createDefaultPatterns() {
        defaultPatternService.insert(
                DefaultPattern.builder()
                        .id(1)
                        .subCategoryId(-1)
                        .country("Default any text & sender matching & no category")
                        .sender(".*")
                        .regex(".*")
                        .build());

        defaultPatternService.insert(
                DefaultPattern.builder()
                        .id(2)
                        .subCategoryId(6)
                        .country("Default any text & sender matching")
                        .sender(".*")
                        .regex(".*")
                        .build());

        defaultPatternService.insert(
                DefaultPattern.builder()
                        .id(3)
                        .subCategoryId(6)
                        .country("Default any text matching")
                        .sender("VIBER")
                        .regex(".*")
                        .build());

        defaultPatternService.insert(
                DefaultPattern.builder()
                        .id(4)
                        .subCategoryId(6)
                        .country("Default any sender matching")
                        .sender(".*")
                        .regex("(.*)(Hello, Alex Saunin!)(.*)")
                        .build());

        defaultPatternService.insert(
                DefaultPattern.builder()
                        .id(5)
                        .subCategoryId(6)
                        .country("Default matching")
                        .sender("VIBER")
                        .regex("(.*)(Hello, Alex Saunin!)(.*)")
                        .build());

        defaultPatternService.insert(
                DefaultPattern.builder()
                        .id(6)
                        .subCategoryId(6)
                        .country("No default matching")
                        .sender("VIBER")
                        .regex("(.*)(Hello, Alex Saunin!)(.*)")
                        .build());

        defaultPatternService.insert(
                DefaultPattern.builder()
                        .id(7)
                        .subCategoryId(6)
                        .sender("NoCountry")
                        .regex("(.*)(Hello, NoCountry!)(.*)")
                        .build());
    }

    @Test
    public void whenCustomPatternNoCategoryThanStateIsCompleted() {
        final int subAccountId = 1;
        final Country country = Country.of("Custom any text & sender matching & no category");
        final String sender = "VIBER";
        final String text = "Hello, Alex Saunin!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isNull();

        result = classifier.classify(subAccountId, country, "", text);
        assertThat(result).isNull();

        result = classifier.classify(subAccountId, country, null, text);
        assertThat(result).isNull();

        result = classifier.classify(subAccountId, country, sender, "");
        assertThat(result).isNull();

        result = classifier.classify(subAccountId, country, sender, null);
        assertThat(result).isNull();

        result = classifier.classify(subAccountId, country, "", "");
        assertThat(result).isNull();

        result = classifier.classify(subAccountId, country, null, "");
        assertThat(result).isNull();

        result = classifier.classify(subAccountId, country, "", null);
        assertThat(result).isNull();

        result = classifier.classify(subAccountId, country, null, null);
        assertThat(result).isNull();
    }

    @Test
    public void whenCustomPatternAnyTextAndSenderMatchingThanStateIsCompleted() {
        final int subAccountId = 1;
        final Country country = Country.of("Custom any text & sender matching");
        final String sender = "VIBER";
        final String text = "Hello, Alex Saunin!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, "", text);
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, null, text);
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, sender, "");
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, sender, null);
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, "", "");
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, null, "");
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, "", null);
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, null, null);
        assertThat(result).isEqualTo(otp);
    }

    @Test
    public void whenCustomPatternAnyTextMatchingThanStateIsCompleted() {
        final int subAccountId = 1;
        final Country country = Country.of("Custom any text matching");
        final String sender = "VIBER";
        final String wrongSender = "REBIV";
        final String text = "Hello, Alex Saunin!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, sender, "");
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, sender, null);
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, wrongSender, text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, "");
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, null);
        assertThat(result).isEqualTo(undef);
    }

    @Test
    public void whenCustomPatternAnySenderMatchingThanStateIsCompleted() {
        final int subAccountId = 1;
        final Country country = Country.of("Custom any sender matching");
        final String sender = "VIBER";
        final String wrongText = "Buy, Saunin Alex!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, wrongText);
        assertThat(result).isEqualTo(undef);
    }

    @Test
    public void whenCustomPatternAnyCountryMatchingThanStateIsCompleted() {
        final int subAccountId = 2;
        final Country country = Country.of("Custom any country matching");
        final String sender = "VIBER";
        final String wrongSender = "REBIV";
        final String text = "Hello, Alex Saunin!";
        final String wrongText = "Buy, Saunin Alex!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, AnyCountry.getInstance(), sender, text);
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, AnyCountry.getInstance(), wrongSender, text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, AnyCountry.getInstance(), sender, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, AnyCountry.getInstance(), wrongSender, wrongText);
        assertThat(result).isEqualTo(undef);
    }

    @Test
    public void whenCustomPatternMatchingThanStateIsCompleted() {
        final int subAccountId = 1;
        final Country country = Country.of("Custom matching");
        final String sender = "VIBER";
        final String wrongSender = "REBIV";
        final String text = "Hello, Alex Saunin!";
        final String wrongText = "Buy, Saunin Alex!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isEqualTo(otp);

        result = classifier.classify(subAccountId, country, wrongSender, text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, sender, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, sender, "");
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, sender, null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", "");
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, "");
        assertThat(result).isEqualTo(undef);
    }

    @Test
    public void whenNoCustomPatternMatchingThanNoCategoryResponding() {
        final int subAccountId = 1;
        final Country country = Country.of("No custom matching");
        final String sender = "VIBER";
        final String text = "Hello, Alex Saunin!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, sender, "");
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, sender, null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", "");
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, "");
        assertThat(result).isEqualTo(undef);
    }

    @Test
    public void whenDefaultPatternAnyTextAndSenderMatchingThanStateIsCompleted() {
        final int subAccountId = 1;
        final Country country = Country.of("Default any text & sender matching");
        final String sender = "VIBER";
        final String text = "Hello, Alex Saunin!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, "", text);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, null, text);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, sender, "");
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, sender, null);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, "", "");
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, null, null);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, "", null);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, null, "");
        assertThat(result).isEqualTo(nts);
    }

    @Test
    public void whenDefaultPatternAnyTextMatchingThanStateIsCompleted() {
        final int subAccountId = 1;
        final Country country = Country.of("Default any text matching");
        final String sender = "VIBER";
        final String wrongSender = "REBIV";
        final String text = "Hello, Alex Saunin!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, sender, "");
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, sender, null);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, wrongSender, text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, "");
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, null);
        assertThat(result).isEqualTo(undef);
    }

    @Test
    public void whenDefaultPatternAnySenderMatchingThanStateIsCompleted() {
        final int subAccountId = 1;
        final Country country = Country.of("Default any sender matching");
        final String sender = "VIBER";
        final String text = "Hello, Alex Saunin!";
        final String wrongText = "Buy, Saunin Alex!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, "", text);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, null, text);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, sender, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, wrongText);
        assertThat(result).isEqualTo(undef);
    }

    @Test
    public void whenDefaultPatternAnyCountryMatchingThanStateIsCompleted() {
        final int subAccountId = 3;
        final Country country = Country.of("Default any country matching");
        final String sender = "NoCountry";
        final String wrongSender = "REBIV";
        final String text = "Hello, NoCountry!";
        final String wrongText = "Buy, Saunin Alex!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, AnyCountry.getInstance(), sender, text);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, AnyCountry.getInstance(), wrongSender, text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, AnyCountry.getInstance(), sender, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, AnyCountry.getInstance(), wrongSender, wrongText);
        assertThat(result).isEqualTo(undef);
    }

    @Test
    public void whenDefaultPatternMatchingThanStateIsCompleted() {
        final int subAccountId = 1;
        final Country country = Country.of("Default matching");
        final String sender = "VIBER";
        final String wrongSender = "REBIV";
        final String text = "Hello, Alex Saunin!";
        final String wrongText = "Buy, Saunin Alex!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, sender, text);
        assertThat(result).isEqualTo(nts);

        result = classifier.classify(subAccountId, country, sender, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, text);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, "");
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", "");
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, "");
        assertThat(result).isEqualTo(undef);
    }

    @Test
    public void whenNoDefaultPatternMatchingThanNoCategoryResponding() {
        final int subAccountId = 1;
        final Country country = Country.of("No default matching");
        final String wrongSender = "REBIV";
        final String wrongText = "Buy, Saunin Alex!";

        SubCategory result;

        result = classifier.classify(subAccountId, country, wrongSender, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, "");
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, wrongSender, wrongText);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", "");
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, "", null);
        assertThat(result).isEqualTo(undef);

        result = classifier.classify(subAccountId, country, null, "");
        assertThat(result).isEqualTo(undef);
    }

}