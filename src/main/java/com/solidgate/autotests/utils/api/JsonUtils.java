package com.solidgate.autotests.utils.api;

import lombok.experimental.*;
import tools.jackson.databind.json.*;

import static tools.jackson.databind.DeserializationFeature.*;
import static tools.jackson.databind.cfg.EnumFeature.*;

@UtilityClass
public class JsonUtils {

    private static final JsonMapper JSON_MAPPER = JsonMapper
            .builder()
            .enable(FAIL_ON_UNKNOWN_PROPERTIES)
            .enable(READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
            .build();

    public static JsonMapper getJsonMapper() {
        return JSON_MAPPER;
    }

    public static String toJson(Object object) {
        return JSON_MAPPER.writeValueAsString(object);
    }
}
