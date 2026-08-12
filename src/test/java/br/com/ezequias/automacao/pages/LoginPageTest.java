package br.com.ezequias.automacao.pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.ezequias.automacao.factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private MockedStatic<DriverFactory> driverFactoryMock;

    @BeforeEach
    void setUp() {
        driver = mock(WebDriver.class);

        driverFactoryMock = mockStatic(DriverFactory.class);

        driverFactoryMock
                .when(DriverFactory::getDriver)
                .thenReturn(driver);

        loginPage = new LoginPage();
    }

    @AfterEach
    void tearDown() {
        driverFactoryMock.close();
    }

    @Test
    void deveAcessarPaginaLogin() {

        loginPage.acessarLogin();

        verify(driver).get(
                "https://demowebshop.tricentis.com/login"
        );
    }

    @Test
    void deveObterMensagemDeErro() {

        WebElement elementoMensagem = mock(WebElement.class);

        when(driver.findElement(
                By.cssSelector(".validation-summary-errors")
        )).thenReturn(elementoMensagem);

        when(elementoMensagem.getText())
                .thenReturn("Login was unsuccessful.");

        String mensagem = loginPage.obterMensagemErro();

        assertEquals(
                "Login was unsuccessful.",
                mensagem

        );
    }

    @Test
    void deveInformarEmail() {

        WebElement campoEmail =
                mock(WebElement.class);

        when(driver.findElement(
                By.id("Email")
        )).thenReturn(campoEmail);

        when(campoEmail.isDisplayed())
                .thenReturn(true);

        loginPage.informarEmail(
                "teste@teste.com"
        );

        verify(campoEmail).clear();

        verify(campoEmail).sendKeys(
                "teste@teste.com"
        );
    }

    @Test
    void deveInformarSenha() {

        WebElement campoSenha =
                mock(WebElement.class);

        when(driver.findElement(
                By.id("Password")
        )).thenReturn(campoSenha);

        when(campoSenha.isDisplayed())
                .thenReturn(true);

        loginPage.informarSenha(
                "123456"
        );

        verify(campoSenha).clear();

        verify(campoSenha).sendKeys(
                "123456"
        );
    }

    @Test
    void deveClicarNoBotaoEntrar() {

        WebElement botaoLogin =
                mock(WebElement.class);

        when(driver.findElement(
                By.cssSelector("input.login-button")
        )).thenReturn(botaoLogin);

        when(botaoLogin.isDisplayed())
                .thenReturn(true);

        when(botaoLogin.isEnabled())
                .thenReturn(true);

        loginPage.clicarEntrar();

        verify(botaoLogin).click();
    }

    @Test
    void deveRealizarLogin() {

        WebElement campoEmail =
                mock(WebElement.class);

        WebElement campoSenha =
                mock(WebElement.class);

        WebElement botaoLogin =
                mock(WebElement.class);

        when(driver.findElement(
                By.id("Email")
        )).thenReturn(campoEmail);

        when(campoEmail.isDisplayed())
                .thenReturn(true);

        when(driver.findElement(
                By.id("Password")
        )).thenReturn(campoSenha);

        when(campoSenha.isDisplayed())
                .thenReturn(true);

        when(driver.findElement(
                By.cssSelector("input.login-button")
        )).thenReturn(botaoLogin);

        when(botaoLogin.isDisplayed())
                .thenReturn(true);

        when(botaoLogin.isEnabled())
                .thenReturn(true);

        loginPage.realizarLogin(
                "teste@teste.com",
                "123456"
        );

        verify(campoEmail).clear();
        verify(campoEmail).sendKeys(
                "teste@teste.com"
        );

        verify(campoSenha).clear();
        verify(campoSenha).sendKeys(
                "123456"
        );

        verify(botaoLogin).click();
    }

    @Test
    void deveRetornarTrueQuandoLoginForRealizado() {

        WebElement linkLogout =
                mock(WebElement.class);

        when(driver.findElement(
                By.className("ico-logout")
        )).thenReturn(linkLogout);

        when(linkLogout.isDisplayed())
                .thenReturn(true);

        boolean resultado =
                loginPage.loginRealizadoComSucesso();

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoLoginNaoForRealizado() {

        WebElement linkLogout =
                mock(WebElement.class);

        when(driver.findElement(
                By.className("ico-logout")
        )).thenReturn(linkLogout);

        when(linkLogout.isDisplayed())
                .thenReturn(false);

        boolean resultado =
                loginPage.loginRealizadoComSucesso();

        assertFalse(resultado);
    }

    @Test
    void deveRealizarLogout() {

        WebElement linkLogout =
                mock(WebElement.class);

        when(driver.findElement(
                By.className("ico-logout")
        )).thenReturn(linkLogout);

        when(linkLogout.isDisplayed())
                .thenReturn(true);

        when(linkLogout.isEnabled())
                .thenReturn(true);

        loginPage.realizarLogout();

        verify(linkLogout).click();
    }

    @Test
    void deveRetornarTrueQuandoEstiverNaPaginaLogin() {

        WebElement linkLogin =
                mock(WebElement.class);

        when(driver.findElement(
                By.className("ico-login")
        )).thenReturn(linkLogin);

        when(linkLogin.isDisplayed())
                .thenReturn(true);

        boolean resultado =
                loginPage.estaNaPaginaLogin();

        assertTrue(resultado);
    }


    @Test
    void deveRetornarFalseQuandoNaoEstiverNaPaginaLogin() {

        WebElement linkLogin =
                mock(WebElement.class);

        when(driver.findElement(
                By.className("ico-login")
        )).thenReturn(linkLogin);

        when(linkLogin.isDisplayed())
                .thenReturn(false);

        boolean resultado =
                loginPage.estaNaPaginaLogin();

        assertFalse(resultado);
    }

    @Test
    void deveAguardarLoginRealizado() {

        WebElement linkLogout =
                mock(WebElement.class);

        when(driver.findElement(
                By.className("ico-logout")
        )).thenReturn(linkLogout);

        when(linkLogout.isDisplayed())
                .thenReturn(true);

        loginPage.aguardarLoginRealizado();

        verify(driver).findElement(
                By.className("ico-logout")
        );
    }
}
