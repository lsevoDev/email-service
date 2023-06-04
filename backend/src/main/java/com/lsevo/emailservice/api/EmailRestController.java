package com.lsevo.emailservice.api;

import com.lsevo.emailservice.email.model.EmailRequest;
import com.lsevo.emailservice.email.service.EmailService;
import com.lsevo.emailservice.errors.ValidationError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "email", description = "the email API")
@RestController
@RequestMapping("/api/email")
@RequiredArgsConstructor
public class EmailRestController {

    private final EmailService emailService;

    @Operation(summary = "Submit new email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Email submitted", content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid request",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationError.class)))
    })
    @PostMapping
    public ResponseEntity<Void> submit(@Valid @RequestBody EmailRequest email) {
        emailService.submit(email);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
