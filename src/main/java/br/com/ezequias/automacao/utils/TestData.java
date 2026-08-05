package br.com.ezequias.automacao.utils;


public final class TestData {

    private TestData() {
    }

    public static String email() {
        return System.getProperty(
                "test.user.email",
                "teste2022@teste.com.br"
        );
    }

    public static String senha() {
        return System.getProperty(
                "test.user.password",
                "teste@"
        );
    }
}
