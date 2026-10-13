package com.solidgate.autotests.config;

import io.github.cdimascio.dotenv.*;

import java.util.*;

import static io.github.cdimascio.dotenv.Dotenv.*;
import static java.lang.System.*;
import static java.util.Optional.*;
import static java.util.function.Predicate.*;

@FunctionalInterface
interface ConfigSource {

    Optional<String> find(String key);

    default ConfigSource orElse(ConfigSource fallback) {
        return key -> find(key).or(() -> fallback.find(key));
    }

    default Optional<String> findNonBlank(String key) {
        return find(key).filter(not(String::isBlank));
    }

    private static ConfigSource systemProperties() {
        return key -> ofNullable(getProperty(key));
    }

    private static ConfigSource environment() {
        return key -> ofNullable(getenv(key));
    }

    private static ConfigSource dotenvFile() {
        var dotenv = configure()
                .ignoreIfMissing()
                .load();

        return key -> ofNullable(dotenv.get(key, null));
    }

    static ConfigSource fromSystemPropertiesEnvironmentAndDotenv() {
        return systemProperties()
                .orElse(environment())
                .orElse(dotenvFile());
    }
}
