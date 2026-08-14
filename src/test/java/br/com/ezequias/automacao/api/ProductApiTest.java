package br.com.ezequias.automacao.api;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import io.restassured.http.ContentType;

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
                .body("size()", greaterThan(0));
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
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("/products")
                .then()
                .log().ifValidationFails()
                .statusCode(201)
                .body("title", equalTo("Produto Teste"));

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

    @Test
    void deveAtualizarProdutoParcialmente() {

        String body = """
        {
          "title": "Produto Atualizado"
        }
        """;

        given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch("/products/1")
                .then()
                .log().ifValidationFails()
                .statusCode(200)
                .body("title", equalTo("Produto Atualizado"));
    }

    @Test
    void deveDeletarProduto() {

        given()
                .spec(requestSpecification)
                .when()
                .delete("/products/1")
                .then()
                .log().ifValidationFails()
                .statusCode(200);
    }

    @Test
    void deveRetornarBodyVazioQuandoProdutoNaoExiste() {

        given()
                .spec(requestSpecification)
                .when()
                .get("/products/999999")
                .then()
                .statusCode(200)
                .body(equalTo(""));
    }

    @Test
    void deveSimularAtualizacaoDeProdutoInexistente() {

        String body = """
        {
          "title": "Produto Inexistente"
        }
        """;

        given()
                .spec(requestSpecification)
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .patch("/products/999999")
                .then()
                .statusCode(200)
                .body("id", equalTo(999999))
                .body("title", equalTo("Produto Inexistente"));
    }

    @Test
    void deveRetornarBodyVazioAoExcluirProdutoInexistente() {

        given()
                .spec(requestSpecification)
                .when()
                .delete("/products/999999")
                .then()
                .statusCode(200)
                .body(equalTo(""));
    }
}