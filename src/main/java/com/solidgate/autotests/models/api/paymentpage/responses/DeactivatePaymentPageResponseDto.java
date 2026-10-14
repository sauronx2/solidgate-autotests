package com.solidgate.autotests.models.api.paymentpage.responses;

import com.solidgate.autotests.models.api.shared.*;
import lombok.*;

@Data
public class DeactivatePaymentPageResponseDto {

    private String status;
    private ApiErrorDto error;
}
