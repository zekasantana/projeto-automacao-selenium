package br.com.ezequias.automacao.api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static org.hamcrest.Matchers.notNullValue;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ProductApiTest extends ApiBase {

    @Test
    void deveBuscarProdutoPorId() {

        given()
                .spec(requestSpecification)
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
                .spec(requestSpecification)
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
                .spec(requestSpecification)
                .body(body)
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .body("title", org.hamcrest.Matchers.equalTo("Produto Teste"))
                .body("price", org.hamcrest.Matchers.equalTo(99.99f));
    }

    @Test
    void deveValidarContratoDoProduto() {

        given()
                .spec(requestSpecification)
                .when()
                .get("/products/1")
                .then()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath(
                        "schemas/product-schema.json"));
    }

}