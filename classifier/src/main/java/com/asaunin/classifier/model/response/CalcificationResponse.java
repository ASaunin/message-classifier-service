package com.asaunin.classifier.model.response;

import com.asaunin.classifier.domain.SubCategory;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class CalcificationResponse extends BaseResponse {

    @ApiModelProperty(value = "Sms category", example = "TS", required = true)
    private final String category;

    public CalcificationResponse(SubCategory subCategory) {
        this.category = subCategory.getCategory();
    }

}
