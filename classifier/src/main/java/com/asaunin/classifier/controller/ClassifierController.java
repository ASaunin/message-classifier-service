package com.asaunin.classifier.controller;

import com.asaunin.classifier.domain.Country;
import com.asaunin.classifier.domain.SubCategory;
import com.asaunin.classifier.model.request.ClassificationRequest;
import com.asaunin.classifier.model.response.CalcificationResponse;
import com.asaunin.classifier.service.ClassifierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

import static com.asaunin.classifier.config.Constants.URI_API_PREFIX;
import static com.asaunin.classifier.config.Constants.URI_CLASSIFICATION;

@Log4j2
@RestController
@RequestMapping(
        value = URI_API_PREFIX + URI_CLASSIFICATION,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class ClassifierController {

    private final ClassifierService classifier;

    @PostMapping(value = "/{subAccount}/{country}")
    public ResponseEntity classify(
            @PathVariable("subAccount") Integer subAccount,
            @PathVariable("country") String countryName,
            @RequestBody ClassificationRequest request) {
        log.debug("REST request to subAccount: {}, country: {}", subAccount, countryName);

        final String sender = request.getSender();
        final String text = request.getText();
        final Country country = Country.of(countryName);
        final SubCategory subCategory = classifier.classify(subAccount, country, sender, text);

        if (Objects.isNull(subCategory)) {
            // TODO: 03.12.2017 Implement Exception handler
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"message\":\"Category is not found\"}");
        }

        return ResponseEntity.ok(new CalcificationResponse(subCategory));
    }

}
