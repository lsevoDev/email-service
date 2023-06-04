package com.lsevo.emailservice.email.model;

import jakarta.validation.constraints.NotNull;
import lombok.NonNull;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

public record Email(
        long id,
        @NonNull UUID externalId,
        @NotNull String fromAddress,
        @NotNull String toAddress,
        @NonNull Set<String> ccAddresses,
        @NonNull String subject,
        @NonNull String content,
        @NonNull Importance importance,
        @NonNull Status status,
        String errorDescription,
        @NonNull Instant createdAt,
        Instant modifiedAt
) {
}
