package com.lsevo.emailservice.persistance;

import com.lsevo.emailservice.email.model.Importance;
import com.lsevo.emailservice.email.model.Status;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.JdbcTypeCode;

import java.sql.Types;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "Email")
@Table(name = "Email")
class EmailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "External_ID")
    @JdbcTypeCode(Types.VARCHAR)
    private UUID externalId;

    @Column(name = "From_Email")
    private String fromEmail;

    @Column(name = "To_Email")
    private String toEmail;

    @ElementCollection
    @CollectionTable(name = "Email_CC", joinColumns = @JoinColumn(name = "Email_ID"))
    @Column(name = "Email")
    private Set<String> ccEmail = new HashSet<>();

    @Column(name = "Subject")
    private String subject;

    @Column(name = "Content")
    private String content;

    @Column(name = "Importance")
    @Enumerated(EnumType.STRING)
    private Importance importance;

    @Column(name = "Status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "Error_Description")
    private String errorDescription;

    @Column(name = "Created_At")
    private Instant createdAt;

    @Column(name = "Modified_At")
    private Instant modifiedAt;
}
