package org.jjhome;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
class CountryServiceTest {
    @Test
    void testCountryServiceEndpoint() {
        given()
          .when().get("/country")
          .then()
             .statusCode(200)
             .body("size()", is(2));
    }

}