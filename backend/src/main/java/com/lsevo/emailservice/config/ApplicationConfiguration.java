package com.lsevo.emailservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.time.Clock;

@EnableAsync
@Configuration
public class ApplicationConfiguration {

    @Bean
    public Clock clock() {
        return Clock.systemUTC();
    }

}
