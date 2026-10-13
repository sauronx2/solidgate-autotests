package com.solidgate.autotests.utils;

import lombok.experimental.*;

import java.time.*;
import java.time.format.*;

import static com.solidgate.autotests.utils.RandomUtils.*;
import static java.time.YearMonth.*;
import static java.time.format.DateTimeFormatter.*;

@UtilityClass
public class DateTimeUtils {

    public static final DateTimeFormatter MM_YY = ofPattern("MMyy");
    private static final long MAX_CARD_VALIDITY_MONTHS = 120;

    public static YearMonth getRandomCardExpirationDate() {
        var monthsAhead = getRandomLong(1, MAX_CARD_VALIDITY_MONTHS + 1);

        return now().plusMonths(monthsAhead);
    }
}
