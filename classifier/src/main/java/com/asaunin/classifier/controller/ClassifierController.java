package com.asaunin.classifier.controller;

import com.asaunin.classifier.domain.Country;
import com.asaunin.classifier.domain.SubCategory;
import com.asaunin.classifier.model.request.ClassificationRequest;
import com.asaunin.classifier.model.response.CalcificationResponse;
import com.asaunin.classifier.service.ClassifierService;
import io.swagger.annotations.*;
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
@Api(tags = "classifier", description = "Classifier service")
@RestController
@RequestMapping(
        value = URI_API_PREFIX + URI_CLASSIFICATION,
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@RequiredArgsConstructor
public class ClassifierController {

    private final ClassifierService classifier;

    @ApiOperation(value = "Classify operation", response = CalcificationResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Bad request"),
            @ApiResponse(code = 404, message = "Not found"),
            @ApiResponse(code = 500, message = "Internal error")
    })
    @PostMapping(value = "/{subAccount}/{country}")
    public ResponseEntity classify(
            @ApiParam(value = "SubAccount Id", defaultValue = "1") @PathVariable("subAccount") Integer subAccount,
            @ApiParam(value = "Country code", defaultValue = "RU") @PathVariable("country") String countryName,
            @ApiParam(value = "Request") @RequestBody ClassificationRequest request) {
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
