package com.lsevo.emailservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lsevo.emailservice.config.TestContainerConfiguration;
import com.lsevo.emailservice.persistance.EmailPersistenceHelper;
import io.restassured.RestAssured;
import lombok.SneakyThrows;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@Import(TestContainerConfiguration.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseIntegrationTest {

    @Autowired
    protected EmailPersistenceHelper emailPersistenceHelper;

    @Autowired
    private ObjectMapper objectMapper;

    @LocalServerPort
    private int port;

    @BeforeEach
    void setup() {
        emailPersistenceHelper.clear();
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = port;
    }

    @AfterEach
    void clear() {
        emailPersistenceHelper.clear();
        RestAssured.reset();
    }

    @SneakyThrows
    public String toJson(Object object) {
        return objectMapper.writeValueAsString(object);
    }
}
