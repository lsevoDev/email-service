package com.lsevo.emailservice.email.service;

import com.lsevo.emailservice.email.model.Email;
import lombok.NonNull;
import org.springframework.scheduling.annotation.Async;

interface EmailSenderService {

    @Async
    void send(@NonNull Email email);
}
