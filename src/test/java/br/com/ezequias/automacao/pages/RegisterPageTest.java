package br.com.ezequias.automacao.pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import java.util.List;

import br.com.ezequias.automacao.factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class RegisterPageTest {

    private WebDriver driver;
    private RegisterPage registerpage;
    private MockedStatic<DriverFactory> driverFactoryMock;

    @BeforeEach
    void setUp() {
        driver = mock(WebDriver.class);

        driverFactoryMock = mockStatic(DriverFactory.class);
        driverFactoryMock
                .when(DriverFactory::getDriver)
                .thenReturn(driver);

        registerpage = spy(new RegisterPage());
    }

    @AfterEach
    void tearDown() {
        driverFactoryMock.close();
    }

    @Test
    void deveAcessarTelaCadastro() {
        doNothing()
                .when(registerpage)
                .clicar(any(By.class));

        registerpage.acessarTelaCadastro();

        verify(driver)
                .get("https://demowebshop.tricentis.com");

        ArgumentCaptor<By> locatorCaptor =
                ArgumentCaptor.forClass(By.class);

        verify(registerpage)
                .clicar(locatorCaptor.capture());

        assertEquals(
                "By.className: ico-register",
                locatorCaptor.getValue().toString()
        );

    }

    @Test
    void devePreencherCadastro() {
        String nome = "Ezequias";
        String sobrenome = "Santana";
        String email = "teste@teste.com";
        String senha = "123456";

        doNothing()
                .when(registerpage)
                .escrever(any(By.class), any(String.class));

        registerpage.preencherCadastro(
                nome,
                sobrenome,
                email,
                senha
        );

        ArgumentCaptor<By> locatorCaptor =
                ArgumentCaptor.forClass(By.class);

        ArgumentCaptor<String> valorCaptor =
                ArgumentCaptor.forClass(String.class);

        verify(registerpage,
                org.mockito.Mockito.times(5))
                .escrever(
                        locatorCaptor.capture(),
                        valorCaptor.capture()
                );

        List<By> locators =
                locatorCaptor.getAllValues();

        List<String> valores =
                valorCaptor.getAllValues();

        assertEquals(
                "By.id: FirstName",
                locators.get(0).toString()
        );

        assertEquals(
                "By.id: LastName",
                locators.get(1).toString()
        );

        assertEquals(
                "By.id: Email",
                locators.get(2).toString()
        );

        assertEquals(
                "By.id: Password",
                locators.get(3).toString()
        );

        assertEquals(
                "By.id: ConfirmPassword",
                locators.get(4).toString()
        );

        assertEquals(nome, valores.get(0));
        assertEquals(sobrenome, valores.get(1));
        assertEquals(email, valores.get(2));
        assertEquals(senha, valores.get(3));
        assertEquals(senha, valores.get(4));
    }

    @Test
    void deveClicarRegistrar() {
        doNothing()
                .when(registerpage)
                .clicar(any(By.class));

        registerpage.clicarRegistrar();

        ArgumentCaptor<By> locatorCaptor =
                ArgumentCaptor.forClass(By.class);

        verify(registerpage)
                .clicar(locatorCaptor.capture());

        assertEquals(
                "By.id: register-button",
                locatorCaptor.getValue().toString()
        );
    }

    @Test
    void deveRetornarTrueQuandoCadastroRealizadoComSucesso() {
        doReturn(true)
                .when(registerpage)
                .elementoEstaVisivel(any(By.class));

        boolean resultado =
                registerpage.cadastroRealizadoComSucesso();

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoCadastroNaoRealizado() {
        doReturn(false)
                .when(registerpage)
                .elementoEstaVisivel(any(By.class));

        boolean resultado =
                registerpage.cadastroRealizadoComSucesso();

        assertFalse(resultado);
    }

    @Test
    void deveObterMensagemEmailExistente() {
        String mensagem =
                "The specified email already exists";

        doReturn(mensagem)
                .when(registerpage)
                .obterTexto(any(By.class));

        String resultado =
                registerpage.obterMensagemEmailExistente();

        assertEquals(mensagem, resultado);
    }

    @Test
    void deveRetornarTrueQuandoMensagemCampoObrigatorioVisivel() {
        doReturn(true)
                .when(registerpage)
                .elementoEstaVisivel(any(By.class));

        boolean resultado =
                registerpage.mensagemCampoObrigatorioVisivel();

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoMensagemCampoObrigatorioNaoVisivel() {
        doReturn(false)
                .when(registerpage)
                .elementoEstaVisivel(any(By.class));

        boolean resultado =
                registerpage.mensagemCampoObrigatorioVisivel();

        assertFalse(resultado);
    }

}
