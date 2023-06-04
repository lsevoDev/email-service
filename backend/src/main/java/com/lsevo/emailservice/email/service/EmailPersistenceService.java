package com.lsevo.emailservice.email.service;

import com.lsevo.emailservice.email.model.Email;
import lombok.NonNull;

public interface EmailPersistenceService {

    Email create(@NonNull Email email);
}
