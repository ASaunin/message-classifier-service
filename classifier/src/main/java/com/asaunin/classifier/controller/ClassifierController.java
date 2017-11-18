package com.asaunin.classifier.controller;

import com.asaunin.classifier.domain.SubCategory;
import com.asaunin.classifier.model.request.ClassificationRequest;
import com.asaunin.classifier.model.response.CalcificationResponse;
import com.asaunin.classifier.service.ClassifierService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.asaunin.classifier.config.Constants.URI_API_PREFIX;
import static com.asaunin.classifier.config.Constants.URI_CLASSIFICATION;

@Log4j2
@RestController
@RequestMapping(URI_API_PREFIX + URI_CLASSIFICATION)
@RequiredArgsConstructor
public class ClassifierController {

    private final ClassifierService classifier;

    @PostMapping("/{subAccount}/{country}")
    public ResponseEntity<CalcificationResponse> classify(
            @PathVariable("subAccount") Integer subAccount,
            @PathVariable("country") String country,
            @RequestBody ClassificationRequest request) {
        log.debug("REST request to subAccount: {}, country: {}", subAccount, country);

        final SubCategory subCategory = classifier.classify(subAccount, country, request.getSenderId(), request.getText());
        log.info(subCategory.toString());

        return ResponseEntity.ok(new CalcificationResponse(subCategory));
    }

}
