package com.solidgate.autotests.config;

import com.solidgate.autotests.exceptions.*;

import java.net.*;

import static com.solidgate.autotests.config.ConfigKey.*;
import static com.solidgate.autotests.config.ConfigSource.*;
import static java.net.URI.*;
import static java.util.Arrays.*;

public record SolidgateConfig(String publicKey, String secretKey, URI paymentPageUrl, URI gateUrl) {

    @Override
    public String toString() {
        return "SolidgateConfig[publicKey=***, secretKey=***, paymentPageUrl=%s, gateUrl=%s]"
                .formatted(paymentPageUrl, gateUrl);
    }

    public static SolidgateConfig current() {
        return Holder.INSTANCE;
    }

    private static SolidgateConfig loadFrom(ConfigSource configSource) {
        var missingKeys = stream(values())
                .filter(key -> configSource
                        .findNonBlank(key.name())
                        .isEmpty())
                .toList();
        if (!missingKeys.isEmpty()) {
            throw new FrameworkException(
                    "Missing configuration %s: set it in .env, as an environment variable or a system property"
                            .formatted(missingKeys));
        }

        return new SolidgateConfig(
                getRequiredValue(configSource, SOLIDGATE_PUBLIC_KEY),
                getRequiredValue(configSource, SOLIDGATE_SECRET_KEY),
                create(getRequiredValue(configSource, SOLIDGATE_PAYMENT_PAGE_URL)),
                create(getRequiredValue(configSource, SOLIDGATE_GATE_URL)));
    }

    private static String getRequiredValue(ConfigSource configSource, ConfigKey key) {
        return configSource
                .findNonBlank(key.name())
                .orElseThrow();
    }

    private static final class Holder {

        private static final SolidgateConfig INSTANCE = loadFrom(fromSystemPropertiesEnvironmentAndDotenv());
    }
}
