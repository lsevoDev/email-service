package com.lsevo.emailservice.email.service;

import com.lsevo.emailservice.email.model.EmailRequest;
import lombok.NonNull;

public interface EmailService {
    void submit(@NonNull EmailRequest request);
}
