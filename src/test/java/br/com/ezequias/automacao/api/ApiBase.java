package br.com.ezequias.automacao.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class ApiBase {

    protected static final RequestSpecification REQUEST_SPEC =
            new RequestSpecBuilder()
                    .setBaseUri("https://fakestoreapi.com")
                    .setContentType(ContentType.JSON)
                    .build();

    static {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    protected ApiBase() {

    }
}
