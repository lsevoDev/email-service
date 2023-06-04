package com.lsevo.emailservice.email.model;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Set;

@Schema(description = "Model for submitting email")
public record EmailRequest(
        @NotEmpty
        @Email
        @Size(max = 320)
        @Schema(description = "from email address", example = "from@email.com")
        String fromAddress,
        @NotEmpty
        @Email
        @Size(max = 320)
        @Schema(description = "to email address", example = "to@email.com")
        String toAddress,
        @NotNull
        @ArraySchema(uniqueItems = true, arraySchema = @Schema(
                implementation = String.class,
                description = "cc email addresses",
                example = "[\"cc1@email.com\", \"cc2@email.com\"]"))
        Set<@Valid @Email @NotEmpty @Size(max = 320) String> ccAddresses,
        @NotEmpty
        @Schema(description = "email subject", example = "To John")
        @Size(max = 255)
        String subject,
        @NotNull
        @Schema(description = "importance of email", example = "NORMAL")
        Importance importance,
        @NotNull
        @Schema(description = "content of email", example = "Hello John how are you?")
        String content
) {
}
