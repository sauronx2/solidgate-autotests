package com.solidgate.autotests.models.enums;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@RequiredArgsConstructor
public enum TransactionStatus {

    PROCESSING("processing"),
    SUCCESS("success"),
    FAIL("fail"),
    VERIFY("verify"),
    @JsonEnumDefaultValue
    UNKNOWN("unknown");

    @JsonValue
    private final String value;
}
