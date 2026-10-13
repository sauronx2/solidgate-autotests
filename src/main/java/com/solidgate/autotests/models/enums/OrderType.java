package com.solidgate.autotests.models.enums;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

@Getter
@RequiredArgsConstructor
public enum OrderType {

    AUTH("auth");

    @JsonValue
    private final String value;
}
