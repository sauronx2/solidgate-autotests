package com.solidgate.autotests.models.enums;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@RequiredArgsConstructor
public enum OrderStatus {

    PROCESSING("processing", false),
    THREE_DS_VERIFY("3ds_verify", false),
    AUTH_OK("auth_ok", true),
    AUTH_FAILED("auth_failed", true),
    SETTLE_OK("settle_ok", true),
    PARTIAL_SETTLED("partial_settled", true),
    VOID_OK("void_ok", true),
    REFUNDED("refunded", true),
    APPROVED("approved", true),
    @JsonEnumDefaultValue
    UNKNOWN("unknown", true);

    @JsonValue
    private final String value;
    private final boolean terminal;
}
