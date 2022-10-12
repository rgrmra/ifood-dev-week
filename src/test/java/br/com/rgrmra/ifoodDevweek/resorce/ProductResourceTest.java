package br.com.rgrmra.ifoodDevweek.resorce;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

@Disabled
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductResourceTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTes() {
        RestAssured.port = randomPort;
    }

    private final String mapping = "/ifood-dev-week/products";

    @Order(1)
    @Test
    public void whenAddFirstProductThenReturnsOk() {
        RestAssured.given()
                .body("{\n" +
                        "  \"available\": true,\n" +
                        "  \"name\": \"first\",\n" +
                        "  \"price\": 25.00,\n" +
                        "  \"restaurantId\": 2\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post(mapping)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("first"));
    }

    @Order(2)
    @Test
    public void whenAddSecondProductThenReturnsOk() {
        RestAssured.given()
                .body("{\n" +
                        "  \"available\": true,\n" +
                        "  \"name\": \"second\",\n" +
                        "  \"price\": 25.00,\n" +
                        "  \"restaurantId\": 2\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post(mapping)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("second"));
    }


    @Order(3)
    @Test
    public void whenAddThirdProductThenReturnsOk() {
        RestAssured.given()
                .body("{\n" +
                        "  \"available\": false,\n" +
                        "  \"name\": \"third\",\n" +
                        "  \"price\": 25.00,\n" +
                        "  \"restaurantId\": 2\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post(mapping)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("third"));
    }


    @Order(4)
    @Test
    public void whenAddFourthProductWithoutPriceThenReturnsBadRequest() {
        RestAssured.given()
                .body("{\n" +
                        "  \"available\": true,\n" +
                        "  \"name\": \"fourth\",\n" +
                        "  \"price\": 0,\n" +
                        "  \"restaurantId\": 2\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post(mapping)
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Order(5)
    @Test
    public void whenGetAllProductsThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(6)
    @Test
    public void whenGetFirstProductThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("first"));
    }

    @Order(7)
    @Test
    public void whenSearchFirstProductThenReturnsOk() {
        RestAssured.given()
                .body("")
                .when()
                .get(mapping.concat("/search/first"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(8)
    @Test
    public void whenUpdateSecondProductThenReturnsOk() {
        RestAssured.given()
                .body("{\n" +
                        "  \"available\": true,\n" +
                        "  \"name\": \"Product 2\",\n" +
                        "  \"price\": 25.00,\n" +
                        "  \"restaurantId\": 2\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put(mapping.concat("/2"))
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("Product 2"));
    }

    @Order(9)
    @Test
    public void whenDeleteProductThenReturnsOk() {
        RestAssured.given()
                .when()
                .delete(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(10)
    @Test
    public void whenGetFirstProductThenReturnsNotFound() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }
}