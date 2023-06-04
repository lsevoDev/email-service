package com.lsevo.emailservice.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.MSSQLServerContainer;

@TestConfiguration(proxyBeanMethods = false)
public class TestContainerConfiguration {

    @Bean
    @ServiceConnection
    public MSSQLServerContainer mssqlServerContainer() {
        return new MSSQLServerContainer("mcr.microsoft.com/mssql/server:2019-latest").acceptLicense();
    }
}
