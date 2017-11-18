package com.asaunin.classifier.model.response;

import com.asaunin.classifier.domain.SubCategory;
import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class CalcificationResponse extends BaseResponse {

    private final String category;

    public CalcificationResponse(SubCategory subCategory) {
        this.category = subCategory.getCategory();
    }

}
