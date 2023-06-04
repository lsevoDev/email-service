package com.lsevo.emailservice.errors;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Map;

public record ValidationError(
        @Schema(description = "mapping of invalid fields and their corresponded errors", example = "{\"fromAddress\":\"must be a well-formed email address\", \"toAddress\":\"must be a well-formed email address\"}")
        Map<String, String> errors) {
}
