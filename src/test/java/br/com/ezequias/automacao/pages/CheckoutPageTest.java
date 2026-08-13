package br.com.ezequias.automacao.pages;

import br.com.ezequias.automacao.factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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

class CheckoutPageTest {

    private WebDriver driver;
    private MockedStatic<DriverFactory> driverFactoryMock;
    private CheckoutPage checkoutPage;

    @BeforeEach
    void setUp() {
        driver = mock(WebDriver.class);

        driverFactoryMock = mockStatic(DriverFactory.class);
        driverFactoryMock.when(DriverFactory::getDriver)
                .thenReturn(driver);

        checkoutPage = spy(new CheckoutPage());
    }

    @AfterEach
    void tearDown() {
        driverFactoryMock.close();
    }

    @Test
    void deveClicarNoCheckBoxDeTermosAoAceitarTermos() {
        doNothing()
                .when(checkoutPage)
                .clicar(any(By.class));

        checkoutPage.aceitarTermos();

        verify(checkoutPage)
                .clicar(By.id("termsofservice"));
    }

    @Test
    void deveClicarNoBotaoCheckoutAoSolicitarCheckout() {
        doNothing()
                .when(checkoutPage)
                .clicar(any(By.class));

        checkoutPage.clicarCheckout();

        verify(checkoutPage)
                .clicar(By.id("checkout"));
    }

    @Test
    void deveRetornarTrueQuandoUrlETituloIndicamPaginaDeCheckout() {
        doReturn(true)
                .when(checkoutPage)
                .elementoEstaVisivel(By.cssSelector(".page-title"));

        doReturn("Checkout")
                .when(checkoutPage)
                .obterTexto(By.cssSelector(".page-title"));

        doReturn("https://demowebshop.tricentis.com/onepagecheckout")
                .when(driver)
                .getCurrentUrl();

        assertTrue(checkoutPage.estaNaPaginaCheckout());
    }

    @Test
    void deveRetornarFalseQuandoPaginaDeCheckoutNaoForIdentificada() {
        doReturn(false)
                .when(checkoutPage)
                .elementoEstaVisivel(By.cssSelector(".page-title"));

        doReturn("https://demowebshop.tricentis.com/cart")
                .when(driver)
                .getCurrentUrl();

        assertFalse(checkoutPage.estaNaPaginaCheckout());
    }

    @Test
    void deveRetornarTrueQuandoMensagemDeTermosEstiverVisivel() {
        doReturn(true)
                .when(checkoutPage)
                .elementoEstaVisivel(
                        By.id("terms-of-service-warning-box")
                );

        assertTrue(checkoutPage.mensagemTermosEstaVisivel());
    }

    @Test
    void deveRetornarFalseQuandoMensagemDeTermosNaoEstiverVisivel() {
        doReturn(false)
                .when(checkoutPage)
                .elementoEstaVisivel(
                        By.id("terms-of-service-warning-box")
                );

        assertFalse(checkoutPage.mensagemTermosEstaVisivel());
    }

    @Test
    void deveRetornarTextoDaMensagemDeTermos() {
        String mensagemEsperada =
                "Please accept the terms of service before the next step.";

        doReturn(mensagemEsperada)
                .when(checkoutPage)
                .obterTexto(
                        By.id("terms-of-service-warning-box")
                );

        String mensagemAtual =
                checkoutPage.obterMensagemTermos();

        assertEquals(mensagemEsperada, mensagemAtual);
    }
}
