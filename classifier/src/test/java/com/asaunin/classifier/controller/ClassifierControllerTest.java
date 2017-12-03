package com.asaunin.classifier.controller;

import com.asaunin.classifier.config.Constants;
import com.asaunin.classifier.domain.Country;
import com.asaunin.classifier.domain.SubCategory;
import com.asaunin.classifier.service.ClassifierService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {ClassifierController.class, Constants.class})
@WebMvcTest(value = {ClassifierController.class, Constants.class})
public class ClassifierControllerTest {

    @Autowired private MockMvc mvc;

    @MockBean private ClassifierService service;

    @Test
    public void whenRequestIsValidThanReturnOkStatus() throws Exception {
        final int subAccount = 1;
        final String countryName = "RU";
        final Country country = Country.of(countryName);
        final SubCategory expectedSubCategory = SubCategory.builder()
                .name("OTP")
                .category("TS")
                .build();

        when(service.classify(eq(subAccount), eq(country), eq("VIBER"), eq("Your code is 7777")))
                .thenReturn(expectedSubCategory);

        mvc.perform(
                post("/classifier/v1/classification/{subAccount}/{country}/", subAccount, countryName)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"source\":\"VIBER\", \"body\":\"Your code is 7777\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("{\"category\":\"TS\"}"))
                .andExpect(status().isOk());
    }

    @Test
    public void whenPathIsInvalidThanReturnNotFoundStatus() throws Exception {
        final int subAccount = 1;
        final String country = "RU";

        mvc.perform(
                post("/classifier/v1/classification/{subAccount}/", subAccount, country)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"source\":\"VIBER\", \"body\":\"Your code is 7777\"}"))
                .andExpect(status().isNotFound());

        mvc.perform(
                post("/classifier/v2/classification/{subAccount}/{country}/", subAccount, country)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"source\":\"VIBER\", \"body\":\"Your code is 7777\"}"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenRequestIsWrongThanReturnBadRequestStatus() throws Exception {
        final int subAccount = 1;
        final String countryName = "RU";
        final Country country = Country.of(countryName);
        final SubCategory expectedSubCategory = SubCategory.builder()
                .name("OTP")
                .category("TS")
                .build();

        when(service.classify(eq(subAccount), eq(country), eq("VIBER"), eq("Your code is 7777")))
                .thenReturn(expectedSubCategory);

        mvc.perform(
                post("/classifier/v1/classification/{subAccount}/{country}", "Hello", countryName)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"source\":\"VIBER\", \"body\":\"Your code is 7777\"}"))
                .andExpect(status().isBadRequest());

        mvc.perform(
                post("/classifier/v1/classification/{subAccount}/{country}/", subAccount, countryName)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"body\":\"Your code is 7777\"}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenNullCategoryThanReturnInternalErrorStatus() throws Exception {
        final int subAccount = 1;
        final String countryName = "RU";

        when(service.classify(any(), any(), any(), any()))
                .thenReturn(null);

        mvc.perform(
                post("/classifier/v1/classification/{subAccount}/{country}/", subAccount, countryName)
                        .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .content("{\"source\":\"VIBER\", \"body\":\"Your code is 7777\"}"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(content().string("{\"message\":\"Category is not found\"}"))
                .andExpect(status().isInternalServerError());
    }

}