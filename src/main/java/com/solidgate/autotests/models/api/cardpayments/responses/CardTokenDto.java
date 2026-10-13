package com.solidgate.autotests.models.api.cardpayments.responses;

import lombok.*;
import tools.jackson.databind.PropertyNamingStrategies.*;
import tools.jackson.databind.annotation.*;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class CardTokenDto {

    private String token;
    private String originalPaymentMethod;
}
