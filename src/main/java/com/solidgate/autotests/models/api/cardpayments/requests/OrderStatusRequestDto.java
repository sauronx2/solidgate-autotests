package com.solidgate.autotests.models.api.cardpayments.requests;

import lombok.*;
import tools.jackson.databind.PropertyNamingStrategies.*;
import tools.jackson.databind.annotation.*;

@Data
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class OrderStatusRequestDto {

    private String orderId;
}
