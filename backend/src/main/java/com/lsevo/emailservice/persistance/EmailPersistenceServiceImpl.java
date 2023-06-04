package com.lsevo.emailservice.persistance;

import com.lsevo.emailservice.email.model.Email;
import com.lsevo.emailservice.email.service.EmailPersistenceService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class EmailPersistenceServiceImpl implements EmailPersistenceService {
    private final EmailRepository repository;

    @Override
    public Email create(@NonNull Email email) {
        var entity = repository.save(toEntity(email));
        return toEmail(entity);
    }

    private Email toEmail(EmailEntity entity) {
        return new Email(
                entity.getId(),
                entity.getExternalId(),
                entity.getFromEmail(),
                entity.getToEmail(),
                entity.getCcEmail(),
                entity.getSubject(),
                entity.getContent(),
                entity.getImportance(),
                entity.getStatus(),
                entity.getErrorDescription(),
                entity.getCreatedAt(),
                entity.getModifiedAt()
        );
    }

    private EmailEntity toEntity(Email email) {
        var entity = new EmailEntity();
        entity.setId(email.id());
        entity.setExternalId(email.externalId());
        entity.setFromEmail(email.fromAddress());
        entity.setToEmail(email.toAddress());
        entity.setCcEmail(email.ccAddresses());
        entity.setSubject(email.subject());
        entity.setContent(email.content());
        entity.setImportance(email.importance());
        entity.setStatus(email.status());
        entity.setErrorDescription(email.errorDescription());
        entity.setCreatedAt(email.createdAt());
        entity.setModifiedAt(email.modifiedAt());
        return entity;
    }
}
