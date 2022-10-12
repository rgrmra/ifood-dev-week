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
public class ClientResourceTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTes() {
        RestAssured.port = randomPort;
    }

    private static final String mapping = "/ifood-dev-week/clients";

    @Order(1)
    @Test
    public void whenAddFirstClientThenReturnsOk() {
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
                        "  \"name\": \"string\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post(mapping)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("string"));
    }

    @Order(2)
    @Test
    public void whenAddSecondClientThenReturnsOk() {
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
                        "  \"name\": \"string\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .post(mapping)
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("string"));
    }

    @Order(3)
    @Test
    public void whenGetAllClientsThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(4)
    @Test
    public void whenUpdateSecondClientThenReturnsOk() {
        RestAssured.given()
                .body("{\n" +
                        "  \"address\": {\n" +
                        "    \"address\": \"Address 2\",\n" +
                        "    \"city\": \"City 2\",\n" +
                        "    \"number\": 2,\n" +
                        "    \"secondAddress\": \"Second Address 2\",\n" +
                        "    \"state\": \"State 2\",\n" +
                        "    \"zipCode\": \"0000002\"\n" +
                        "  },\n" +
                        "  \"name\": \"Client Name 2\"\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .put(mapping.concat("/2"))
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("name", Matchers.equalTo("Client Name 2"));;

    }

    @Order(5)
    @Test
    public void WhenGetClientThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/2"))
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", Matchers.equalTo(2));
    }

    @Order(6)
    @Test
    public void whenDeleteFirstClientThenReturnsOk() {
        RestAssured.given()
                .when()
                .delete(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(7)
    @Test
    public void whenGetFirstClientByIdThenReturstNotFound() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}