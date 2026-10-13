package com.solidgate.autotests.models.api.paymentpage.requests;

import com.solidgate.autotests.models.enums.*;
import lombok.*;
import tools.jackson.databind.PropertyNamingStrategies.*;
import tools.jackson.databind.annotation.*;

@Data
@AllArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class InitPaymentPageRequestDto {

    private OrderDto order;
    private PageCustomizationDto pageCustomization;

    @Data
    @Builder
    @JsonNaming(SnakeCaseStrategy.class)
    public static class OrderDto {

        private String orderId;
        private Long amount;
        private String currency;
        private String orderDescription;
        private OrderType type;
        private String customerEmail;
    }

    @Data
    @AllArgsConstructor
    @JsonNaming(SnakeCaseStrategy.class)
    public static class PageCustomizationDto {

        private String publicName;
    }
}
