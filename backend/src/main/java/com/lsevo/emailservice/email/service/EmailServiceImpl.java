package com.lsevo.emailservice.email.service;

import com.lsevo.emailservice.email.model.Email;
import com.lsevo.emailservice.email.model.EmailRequest;
import com.lsevo.emailservice.email.model.Status;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
class EmailServiceImpl implements EmailService {
    private final EmailPersistenceService emailPersistService;
    private final EmailSenderService emailSenderService;
    private final Clock clock;

    @Override
    public void submit(@NonNull EmailRequest request) {
        var email = emailPersistService.create(toNewEmail(request));
        emailSenderService.send(email);
    }

    private Email toNewEmail(EmailRequest request) {
        return new Email(
                0,
                UUID.randomUUID(),
                request.fromAddress(),
                request.toAddress(),
                request.ccAddresses(),
                request.subject(),
                request.content(),
                request.importance(),
                Status.NOT_SENT,
                null,
                clock.instant(),
                null
        );
    }
}
