package com.solidgate.autotests.models.api.paymentpage.responses;

import com.solidgate.autotests.models.api.shared.*;
import lombok.*;

@Data
public class InitPaymentPageResponseDto {

    private String url;
    private String guid;
    private String id;
    private ApiErrorDto error;
}
