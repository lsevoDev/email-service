package com.lsevo.emailservice.email.service;

import com.lsevo.emailservice.email.model.Email;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
class NoOpEmailSenderService implements EmailSenderService {

    @Async
    @Override
    public void send(@NonNull Email email) {
        log.info("Sending email with id {}", email.id());
        log.info("Email with id {} sent", email.id());
    }
}
