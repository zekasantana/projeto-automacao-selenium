package br.com.ezequias.automacao.api;

import org.junit.jupiter.api.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.enableLoggingOfRequestAndResponseIfValidationFails;

public class ApiBase {

    protected RequestSpecification requestSpecification;

    @BeforeEach
    protected void configurarApi() {

        enableLoggingOfRequestAndResponseIfValidationFails();

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://fakestoreapi.com")
                .setContentType("application/json")
                .build();
    }
}