package com.solidgate.autotests.utils;

import lombok.experimental.*;

import java.security.*;

import static java.lang.Math.*;
import static java.lang.String.*;
import static java.util.UUID.*;

@UtilityClass
public class RandomUtils {

    private static final SecureRandom SECURE_RANDOM = createSeededSecureRandom();

    public static int getRandomNumberWithDigits(int digitCount) {
        var min = (int) pow(10, digitCount - 1);
        var max = (int) pow(10, digitCount) - 1;

        return SECURE_RANDOM.nextInt(min, max + 1);
    }

    public static long getRandomLong(long minInclusive, long maxExclusive) {
        return SECURE_RANDOM.nextLong(minInclusive, maxExclusive);
    }

    public static String getRandomUUIDv4AsString() {
        return valueOf(randomUUID());
    }

    public static String getRandomUUIDv4Suffix() {
        var uuid = getRandomUUIDv4AsString();

        return uuid.substring(uuid.lastIndexOf('-') + 1);
    }

    private static SecureRandom createSeededSecureRandom() {
        var secureRandom = new SecureRandom();
        secureRandom.nextBytes(new byte[1]);

        return secureRandom;
    }
}
