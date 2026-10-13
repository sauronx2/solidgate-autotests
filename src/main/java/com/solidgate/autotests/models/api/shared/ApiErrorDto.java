package com.solidgate.autotests.models.api.shared;

import lombok.*;
import tools.jackson.databind.*;
import tools.jackson.databind.PropertyNamingStrategies.*;
import tools.jackson.databind.annotation.*;

@Data
@JsonNaming(SnakeCaseStrategy.class)
public class ApiErrorDto {

    private String code;
    private JsonNode messages;
    private JsonNode message;
    private String recommendedMessageForUser;
    private String merchantAdviceCode;
}
