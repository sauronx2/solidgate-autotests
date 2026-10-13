package com.solidgate.autotests.models.enums;

import com.solidgate.autotests.exceptions.*;
import lombok.*;

import java.util.*;

import static java.util.Arrays.*;
import static java.util.Currency.*;
import static java.util.Locale.*;

@Getter
public enum FiatCurrency {

    EUR;

    private final Currency currency = getInstance(name());

    public String getSymbol() {
        return currency.getSymbol(US);
    }

    public static FiatCurrency fromSymbol(String symbol) {
        return stream(values())
                .filter(fiatCurrency -> symbol.equals(fiatCurrency.getSymbol()))
                .findFirst()
                .orElseThrow(() -> new FrameworkException("Unknown currency symbol: " + symbol));
    }
}
