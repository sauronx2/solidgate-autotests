package com.solidgate.autotests.models.entities;

import lombok.*;

import java.util.*;

import static java.util.Currency.*;

@Data
@AllArgsConstructor
public class MoneyEntity {

    private long minorUnits;
    private Currency currency;

    public static MoneyEntity of(long minorUnits, String currencyCode) {
        return new MoneyEntity(minorUnits, getInstance(currencyCode));
    }

    public String getCurrencyCode() {
        return currency.getCurrencyCode();
    }
}
