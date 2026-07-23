package br.com.ezequias.automacao.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

public class ProductApiTest extends ApiBase {

    @Test
    void deveBuscarProdutoPorId() {

        given()
                .spec(REQUEST_SPEC)
                .when()
                .get("/products/1")
                .then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("title", notNullValue())
                .body("price", notNullValue());
    }

    @Test
    void deveBuscarTodosOsProdutos() {

        given()
                .spec(REQUEST_SPEC)
                .when()
                .get("/products")
                .then()
                .statusCode(200)
                .body("size()", org.hamcrest.Matchers.greaterThan(0));
    }

    @Test
    void deveCriarProduto() {

        String body = """
            {
              "title": "Produto Teste",
              "price": 99.99,
              "description": "Produto criado via automacao",
              "image": "https://i.pravatar.cc",
              "category": "electronics"
            }
            """;

        given()
                .spec(REQUEST_SPEC)
                .body(body)
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .body("title", org.hamcrest.Matchers.equalTo("Produto Teste"))
                .body("price", org.hamcrest.Matchers.equalTo(99.99f));
    }

}