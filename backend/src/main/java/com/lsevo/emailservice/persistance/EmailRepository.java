package com.lsevo.emailservice.persistance;

import org.springframework.data.jpa.repository.JpaRepository;

interface EmailRepository extends JpaRepository<EmailEntity, Long> {
}
