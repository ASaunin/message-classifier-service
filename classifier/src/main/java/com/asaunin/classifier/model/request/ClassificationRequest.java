package com.asaunin.classifier.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class ClassificationRequest extends BaseRequest {

    @JsonProperty(value = "source", required = true)
    private final String sender;
    @JsonProperty(value = "body")
    private final String text;

}
