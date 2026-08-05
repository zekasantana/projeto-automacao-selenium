package br.com.ezequias.automacao.unit.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import br.com.ezequias.automacao.utils.TestData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TestDataTest {

    private static final String PROPRIEDADE_EMAIL =
            "test.user.email";

    private static final String PROPRIEDADE_SENHA =
            "test.user.password";

    private static final String EMAIL_PADRAO =
            "teste2022@teste.com.br";

    private static final String SENHA_PADRAO =
            "teste@";

    private String emailOriginal;
    private String senhaOriginal;

    @BeforeEach
    void salvarPropriedadesOriginais() {
        emailOriginal = System.getProperty(PROPRIEDADE_EMAIL);
        senhaOriginal = System.getProperty(PROPRIEDADE_SENHA);
    }

    @AfterEach
    void restaurarPropriedadesOriginais() {
        restaurarPropriedade(
                PROPRIEDADE_EMAIL,
                emailOriginal
        );

        restaurarPropriedade(
                PROPRIEDADE_SENHA,
                senhaOriginal
        );
    }

    @Test
    void deveRetornarEmailPadraoQuandoPropriedadeNaoForInformada() {
        System.clearProperty(PROPRIEDADE_EMAIL);

        String email = TestData.email();

        assertEquals(EMAIL_PADRAO, email);
    }

    @Test
    void deveRetornarEmailInformadoPorPropriedadeDoSistema() {
        String emailEsperado = "usuario.teste@teste.com.br";

        System.setProperty(
                PROPRIEDADE_EMAIL,
                emailEsperado
        );

        String email = TestData.email();

        assertEquals(emailEsperado, email);
    }

    @Test
    void deveRetornarSenhaPadraoQuandoPropriedadeNaoForInformada() {
        System.clearProperty(PROPRIEDADE_SENHA);

        String senha = TestData.senha();

        assertEquals(SENHA_PADRAO, senha);
    }

    @Test
    void deveRetornarSenhaInformadaPorPropriedadeDoSistema() {
        String senhaEsperada = "senha-segura-123";

        System.setProperty(
                PROPRIEDADE_SENHA,
                senhaEsperada
        );

        String senha = TestData.senha();

        assertEquals(senhaEsperada, senha);
    }

    private void restaurarPropriedade(
            String nomePropriedade,
            String valorOriginal
    ) {
        if (valorOriginal == null) {
            System.clearProperty(nomePropriedade);
            return;
        }

        System.setProperty(
                nomePropriedade,
                valorOriginal
        );
    }
}
