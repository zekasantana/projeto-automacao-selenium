package br.com.ezequias.automacao.api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured
        .enableLoggingOfRequestAndResponseIfValidationFails;

public class ApiBase {

    private static final String DEFAULT_BASE_URI =
            "https://fakestoreapi.com";

    protected RequestSpecification requestSpecification;

    @BeforeEach
    protected void configurarApi() {

        enableLoggingOfRequestAndResponseIfValidationFails();

        String baseUri = System.getProperty(
                "api.base.uri",
                System.getenv().getOrDefault(
                        "API_BASE_URI",
                        DEFAULT_BASE_URI
                )
        );

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(baseUri)
                .setContentType("application/json")
                .build();
    }
}