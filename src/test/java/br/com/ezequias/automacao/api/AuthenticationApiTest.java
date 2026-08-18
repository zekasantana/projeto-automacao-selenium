package br.com.ezequias.automacao.api;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

class AuthenticationApiTest {

    private static final String BASE_URL =
            "https://dummyjson.com";

    @Test
    void deveRealizarLoginComSucesso() {

        String body = """
                {
                  "username": "emilys",
                  "password": "emilyspass"
                }
                """;

        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth/login")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("username", equalTo("emilys"))
                .body("accessToken", notNullValue())
                .body("refreshToken", notNullValue());
    }

    @Test
    void deveAcessarUsuarioAutenticadoComToken() {

        String body = """
            {
              "username": "emilys",
              "password": "emilyspass"
            }
            """;

        String accessToken = given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth/login")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .extract()
                .path("accessToken");

        given()
                .baseUri(BASE_URL)
                .auth()
                .oauth2(accessToken)
                .when()
                .get("/auth/me")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("username", equalTo("emilys"))
                .body("email", notNullValue());
    }

    @Test
    void deveRecusarLoginComCredenciaisInvalidas() {

        String body = """
                {
                  "username": "usuario_invalido"
                  "password": "senha_invalida"
                }
                """;
        given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/auth/login")
                .then()
                .log().ifValidationFails()
                .statusCode(400)
                .body("message", notNullValue());
    }

    @Test
    void deveRecusarAcessoSemToken() {

        given()
                .baseUri(BASE_URL)
                .when()
                .get("/auth/me")
                .then()
                .log().ifValidationFails()
                .statusCode(401)
                .body("message", notNullValue());
    }
}
