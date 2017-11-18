package com.asaunin.classifier.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class ClassificationRequest extends BaseRequest {

    @JsonProperty("source")
    private final String senderId;
    @JsonProperty("source")
    private final String text;

}
