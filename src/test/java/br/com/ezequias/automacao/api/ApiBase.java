package br.com.ezequias.automacao.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

public class ApiBase {

    protected static RequestSpecification requestSpecification;

    @BeforeAll
    static void configurarApi() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://fakestoreapi.com")
                .addHeader("Accept", "application/json")
                .addHeader(
                        "User-Agent",
                        "Mozilla/5.0 QA-Automation-Portfolio GitHub-Actions"
                )
                .log(LogDetail.ALL)
                .build();

        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}