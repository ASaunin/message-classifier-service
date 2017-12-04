package com.asaunin.classifier.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class ClassificationRequest extends BaseRequest {

    @ApiModelProperty(value = "Sms sender", example = "VIBER", required = true)
    @JsonProperty(value = "source", required = true)
    private final String sender;

    @ApiModelProperty(value = "Sms text", example = "Your code is 7777")
    @JsonProperty(value = "body")
    private final String text;

}
