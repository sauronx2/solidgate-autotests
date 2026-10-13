package com.solidgate.autotests.utils;

import com.solidgate.autotests.exceptions.*;
import com.solidgate.autotests.models.entities.*;
import lombok.*;
import lombok.experimental.*;

import java.math.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;

import static com.solidgate.autotests.models.enums.FiatCurrency.*;
import static java.text.NumberFormat.*;
import static java.util.Locale.*;
import static java.util.regex.Pattern.*;

@UtilityClass
public class MoneyUtils {

    private static final Pattern DISPLAYED_MONEY_PATTERN =
            compile("^([^\\d\\s\\u00A0]+)[\\s\\u00A0]*([\\d,]+(?:\\.\\d+)?)$");

    @SneakyThrows(ParseException.class)
    public static MoneyEntity parseDisplayedMoney(String displayedMoney) {
        var displayedMoneyMatcher = DISPLAYED_MONEY_PATTERN.matcher(displayedMoney.strip());
        if (!displayedMoneyMatcher.matches()) {
            throw new FrameworkException("Not a displayed amount: '%s'".formatted(displayedMoney));
        }
        var currency = fromSymbol(displayedMoneyMatcher.group(1)).getCurrency();
        var majorUnitsFormat = (DecimalFormat) getNumberInstance(US);
        majorUnitsFormat.setParseBigDecimal(true);
        var majorUnits = (BigDecimal) majorUnitsFormat.parse(displayedMoneyMatcher.group(2));

        return new MoneyEntity(toMinorUnits(majorUnits, currency), currency);
    }

    public static long toMinorUnits(BigDecimal majorUnits, Currency currency) {
        var fractionDigits = currency.getDefaultFractionDigits();
        if (majorUnits.scale() != fractionDigits) {
            throw new FrameworkException("%s has %d fraction digits, %s uses %d"
                    .formatted(majorUnits, majorUnits.scale(), currency, fractionDigits));
        }

        return majorUnits
                .movePointRight(fractionDigits)
                .longValueExact();
    }
}
