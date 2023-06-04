package com.lsevo.emailservice.email;

import com.lsevo.emailservice.BaseIntegrationTest;
import com.lsevo.emailservice.email.model.EmailRequest;
import com.lsevo.emailservice.email.model.Importance;
import com.lsevo.emailservice.errors.ValidationError;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.BDDAssertions.then;

class EmailIntegrationTest extends BaseIntegrationTest {

    @Test
    void shouldSubmitEmail() {
        var givenRequest = createValidRequestObject();

        given()
                .body(toJson(givenRequest))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/email")
                .then()
                .statusCode(HttpStatus.CREATED.value())
                .body(Matchers.emptyString());

        thenEmailShouldBeSaved(givenRequest);
    }

    @Test
    void shouldThrowBadRequestWhenRequestInvalid() {
        var givenRequest = createInValidRequestObject();

        given()
                .body(toJson(givenRequest))
                .contentType(ContentType.JSON)
                .when()
                .post("/api/email")
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body(Matchers.equalTo(toJson(new ValidationError(Map.of("fromAddress", "must be a well-formed email address")))));

        thenEmailShouldNotBeSaved(givenRequest);
    }

    private EmailRequest createValidRequestObject() {
        return new EmailRequest("some@some", "some@some", Set.of("some@some"), "subject", Importance.LOW, "");
    }

    private EmailRequest createInValidRequestObject() {
        return new EmailRequest("some", "some@some", Set.of("some@some"), "subject", Importance.LOW, "");
    }

    private void thenEmailShouldBeSaved(EmailRequest givenRequest) {
        then(emailPersistenceHelper.exists(givenRequest)).isTrue();
    }

    private void thenEmailShouldNotBeSaved(EmailRequest givenRequest) {
        then(emailPersistenceHelper.exists(givenRequest)).isFalse();
    }
}
