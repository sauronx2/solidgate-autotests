package com.solidgate.autotests.models.enums;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@RequiredArgsConstructor
public enum TransactionOperation {

    AUTH("auth"),
    SETTLE("settle"),
    VOID("void"),
    REFUND("refund"),
    RECURRING_AUTH("recurring-auth"),
    RESIGN_AUTH("resign-auth"),
    APPLE_PAY("apple-pay"),
    GOOGLE_PAY("google-pay"),
    PAY("pay"),
    @JsonEnumDefaultValue
    UNKNOWN("unknown");

    @JsonValue
    private final String value;
}
