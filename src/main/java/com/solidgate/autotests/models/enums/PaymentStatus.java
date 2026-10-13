package com.solidgate.autotests.models.enums;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum PaymentStatus {

    SUCCESSFUL("Payment successful!");

    private final String title;
}
