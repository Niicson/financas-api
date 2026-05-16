package com.nicson.financasapi.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Value;

import java.time.OffsetDateTime;
import java.util.Map;

@Value
@Builder
public class ErrorResponse {
    OffsetDateTime timestamp;
    int status;
    String error;
    String message;
    String path;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    Map<String, String> validationErrors;
}
