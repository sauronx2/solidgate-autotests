package com.solidgate.autotests.models.entities;

import lombok.*;

import java.time.*;

@Data
@Builder
public class CardEntity {

    private String number;
    private YearMonth expirationDate;
    private String cvv;
}
