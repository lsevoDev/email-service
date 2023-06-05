# Email-service

## Introduction
Simple service that accepts request for sending email. There is no actual email sending but it can be accomplished by 
implementing `com.lsevo.emailservice.email.service.EmailSenderService` interface.

## Prerequisites
- Maven 3.9.2
- Java 19
- Docker
- npm

## Project setup
This service uses MSSQL database as persistence storage. To configure service to run two things are required.

1. MSSQL database to be running. If there is no MSSQL database simply run following docker command:
````
docker run -e "ACCEPT_EULA=Y" -e "MSSQL_SA_PASSWORD=StrongPassword2023!" -p 1433:1433 -d mcr.microsoft.com/mssql/server:2019-latest
````

2. Add MSSQL connection properties to [application.yml](./src/main/resources/application.yml)
````yaml
spring:
  datasource:
    driverClassName: com.microsoft.sqlserver.jdbc.SQLServerDriver
    url: jdbc:sqlserver://localhost:1433;encrypt=true;trustServerCertificate=true;
    username: sa
    password: StrongPassword2023!
  jpa:
    properties:
      hibernate:
        dialect: org.hibernate.dialect.SQLServerDialect
````
## Backend service
To run backend service in development mode after setting up project simply run:
````shell
mvn spring-boot:run
````
After service has started you can access swagger ui page on 
[localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) where you can see api documentation and 
execute requests.

## Frontend
To run frontend app in development mode on [localhost:3000](localhost:3000) simply run:
````shell
npm start --prefix frontend
````

## Package build

To package build into fat jar simply run:

````maven
mvn clean install
````
and execute jar file with command:
````shell
java -jar backend/target/backend-0.0.1-SNAPSHOT.jar
````
