package com.lsevo.emailservice.persistance;

import com.lsevo.emailservice.email.model.EmailRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class EmailPersistenceHelper {
    private final EmailRepository repository;

    @Transactional(readOnly = true)
    public boolean exists(EmailRequest givenRequest) {
        for (var entity : repository.findAll()) {
            if (isEntityEqualToRequestData(entity, givenRequest)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEntityEqualToRequestData(EmailEntity entity, EmailRequest givenRequest) {
        return entity.getFromEmail().equals(givenRequest.fromAddress()) &&
                entity.getToEmail().equals(givenRequest.toAddress()) &&
                entity.getCcEmail().equals(givenRequest.ccAddresses()) &&
                entity.getSubject().equals(givenRequest.subject()) &&
                entity.getImportance() == givenRequest.importance() &&
                entity.getContent().equals(givenRequest.content());
    }

    public void clear() {
        repository.deleteAll();
    }
}
