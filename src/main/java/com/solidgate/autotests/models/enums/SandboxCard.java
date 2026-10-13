package com.solidgate.autotests.models.enums;

import lombok.*;

@Getter
@RequiredArgsConstructor
public enum SandboxCard {

    SUCCESSFUL_PAYMENT("4067429974719265");

    private final String number;
}
