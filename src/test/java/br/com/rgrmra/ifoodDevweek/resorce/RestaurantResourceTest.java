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
public class RestaurantResourceTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTes() {
        RestAssured.port = randomPort;
    }

    public final String mapping = "/ifood-dev-week/restaurants";

    @Order(1)
    @Test
    public void whenAddFirstRestaurantThenReturnsOk() {
        RestAssured.given()
                .body("{\n" +
                        "  \"address\": {\n" +
                        "    \"address\": \"string\",\n" +
                        "    \"city\": \"string\",\n" +
                        "    \"number\": 0,\n" +
                        "    \"secondAddress\": \"string\",\n" +
                        "    \"state\": \"string\",\n" +
                        "    \"zipCode\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"first\"\n" +
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
    public void whenAddSecondRestaurantThenReturnsOk() {
        RestAssured.given()
                .body("{\n" +
                        "  \"address\": {\n" +
                        "    \"address\": \"string\",\n" +
                        "    \"city\": \"string\",\n" +
                        "    \"number\": 0,\n" +
                        "    \"secondAddress\": \"string\",\n" +
                        "    \"state\": \"string\",\n" +
                        "    \"zipCode\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"second\"\n" +
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
    public void whenGetAllRestaurantThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(4)
    @Test
    public void whenSearchFirstRestaurantThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/search/first"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(5)
    @Test
    public void whenGetFirstRestaurantProductsListThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/1/products"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(6)
    @Test
    public void whenUpdateSecondRestaurantThenReturnsOk() {
        RestAssured.given()
                .body("{\n" +
                        "  \"address\": {\n" +
                        "    \"address\": \"Address 2\",\n" +
                        "    \"city\": \"City 2\",\n" +
                        "    \"number\": 2,\n" +
                        "    \"secondAddress\": \"Second Adress 2\",\n" +
                        "    \"state\": \"State 2\",\n" +
                        "    \"zipCode\": \"0000002\"\n" +
                        "  },\n" +
                        "  \"name\": \"Restaurant Name 2\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put(mapping.concat("/2"))
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("Restaurant Name 2"));
    }

    @Order(7)
    @Test
    public void whenGetFirstRestaurantThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("first"));
    }

    @Order(8)
    @Test
    public void whenDeleteFirstRestaurantThenReturnsOk() {
        RestAssured.given()
                .when()
                .delete(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(9)
    @Test
    public void whenGetFirstRestaurantThenReturnsNotFound() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}