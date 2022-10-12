package br.com.rgrmra.ifoodDevweek.resorce;

import br.com.rgrmra.ifoodDevweek.enumeration.PaymentMethod;
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
public class CartResourceTest {

    @LocalServerPort
    private int randomPort;

    @BeforeEach
    public void setUpTes() {
        RestAssured.port = randomPort;
    }

    private static final String mapping = "/ifood-dev-week/carts";

    @Order(1)
    @Test
    public void whenAddFirstCartThenReturnsOk() {
        RestAssured.given()
                .param("clientId", 2)
                .when()
                .post(mapping)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(2)
    @Test
    public void whenAddSecondCartThenReturnsOk() {
        RestAssured.given()
                .param("clientId", 2)
                .when()
                .post(mapping)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(3)
    @Test
    public void whenGetAllCartsThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping)
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(4)
    @Test
    public void whenGetFirstCartThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.OK.value())
                .body("id", Matchers.equalTo(1));
    }

    @Order(5)
    @Test
    public void whenGetCartsByClientThenReturnsOk() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/client/2"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(6)
    @Test
    public void whenCloseCartWithoutItemsThenReturnsBadRequest() {
        RestAssured.given()
                .when()
                .patch(mapping.concat("/2/checkout"))
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


    @Order(7)
    @Test
    public void whenAddFirstItemInCartThenReturnsOk() {
        RestAssured.given()
                .body("{\n" +
                        "  \"productId\": 2,\n" +
                        "  \"quantity\": 4\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .patch(mapping.concat("/2/add"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(8)
    @Test
    public void whenAddSecondItemInCartThenReturnsOk() {
        RestAssured.given()
                .body("{\n" +
                        "  \"productId\": 2,\n" +
                        "  \"quantity\": 4\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .patch(mapping.concat("/2/add"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(9)
    @Test
    public void whenAddFalseItemInCartThenReturnsBadRequest() {
        RestAssured.given()
                .body("{\n" +
                        "  \"productId\": 3,\n" +
                        "  \"quantity\": 1\n" +
                        "}")
                .contentType(ContentType.JSON)
                .when()
                .patch(mapping.concat("/2/add"))
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Order(10)
    @Test
    public void whenRemoveFirstItemInCartThenReturnsOk() {
        RestAssured.given()
                .when()
                .delete(mapping.concat("/2/delete/1"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(11)
    @Test
    public void whenCloseCartWithoutPaymentMethodThenReturnsBadRequest() {
        RestAssured.given()
                .when()
                .patch(mapping.concat("/2/checkout"))
                .then()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Order(12)
    @Test
    public void whenSetPaymentMethodThenReturnsOk() {
        RestAssured.given()
                .param("paymentMethod", PaymentMethod.CASH)
                .contentType(ContentType.JSON)
                .when()
                .patch(mapping.concat("/2/payment-method"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(13)
    @Test
    public void whenCloseCartThenReturnsOk() {
        RestAssured.given()
                .when()
                .patch(mapping.concat("/2/checkout"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(14)
    @Test
    public void whenDeleteFirstCartThenReturnsOK() {
        RestAssured.given()
                .when()
                .delete(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.OK.value());
    }

    @Order(15)
    @Test
    public void whenGetFirstCartThenReturnsNotFound() {
        RestAssured.given()
                .when()
                .get(mapping.concat("/1"))
                .then()
                .statusCode(HttpStatus.NOT_FOUND.value());
    }

}