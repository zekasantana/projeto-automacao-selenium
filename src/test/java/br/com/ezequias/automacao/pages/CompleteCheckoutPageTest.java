package br.com.ezequias.automacao.pages;

import br.com.ezequias.automacao.factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class CompleteCheckoutPageTest {

    private WebDriver driver;
    private MockedStatic<DriverFactory> driverFactoryMock;
    private CompleteCheckoutPage completeCheckoutPage;

    @BeforeEach
    void setUp() {
        driver = mock(WebDriver.class);

        driverFactoryMock = mockStatic(DriverFactory.class);
        driverFactoryMock.when(DriverFactory::getDriver)
                .thenReturn(driver);

        completeCheckoutPage =
                spy(new CompleteCheckoutPage());
    }

    @AfterEach
    void tearDown() {
        driverFactoryMock.close();
    }

    @Test
    void deveRetornarTrueQuandoPedidoForRealizadoComSucesso() {
        By mensagemPedidoConcluido =
                By.cssSelector(
                        ".section.order-completed .title strong"
                );

        doReturn(true)
                .when(completeCheckoutPage)
                .aguardarTextoVisivel(
                        mensagemPedidoConcluido,
                        "Your order has been successfully processed!"
                );

        assertTrue(
                completeCheckoutPage
                        .pedidoRealizadoComSucesso()
        );
    }

    @Test
    void deveRetornarFalseQuandoPedidoNaoForRealizadoComSucesso() {
        By mensagemPedidoConcluido =
                By.cssSelector(
                        ".section.order-completed .title strong"
                );

        doReturn(false)
                .when(completeCheckoutPage)
                .aguardarTextoVisivel(
                        mensagemPedidoConcluido,
                        "Your order has been successfully processed!"
                );

        assertFalse(
                completeCheckoutPage
                        .pedidoRealizadoComSucesso()
        );
    }

    @Test
    void devePreencherDadosDeCobrancaQuandoFormularioEstiverVisivel() {
        By billingFirstName = By.id("BillingNewAddress_FirstName");
        By billingLastName = By.id("BillingNewAddress_LastName");
        By billingEmail = By.id("BillingNewAddress_Email");
        By billingCountry = By.id("BillingNewAddress_CountryId");
        By billingCity = By.id("BillingNewAddress_City");
        By billingAddress = By.id("BillingNewAddress_Address1");
        By billingZipCode = By.id("BillingNewAddress_ZipPostalCode");
        By billingPhone = By.id("BillingNewAddress_PhoneNumber");
        By btnContinuarBilling = By.cssSelector(
                "#billing-buttons-container input[value='Continue']"
        );
        By shippingAddressSection = By.id("checkout-step-shipping");

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(billingFirstName);

        doReturn(false)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(billingEmail);

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(shippingAddressSection);

        doNothing()
                .when(completeCheckoutPage)
                .escrever(any(By.class), anyString());

        doNothing()
                .when(completeCheckoutPage)
                .selecionarPorTexto(any(By.class), anyString());

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        completeCheckoutPage.preencherDadosCobranca();

        verify(completeCheckoutPage)
                .escrever(billingFirstName, "Ezequias");

        verify(completeCheckoutPage)
                .escrever(billingLastName, "Santana");

        verify(completeCheckoutPage)
                .selecionarPorTexto(billingCountry, "Brazil");

        verify(completeCheckoutPage)
                .escrever(billingCity, "Barueri");

        verify(completeCheckoutPage)
                .escrever(billingAddress, "Rua Teste, 100");

        verify(completeCheckoutPage)
                .escrever(billingZipCode, "06400-000");

        verify(completeCheckoutPage)
                .escrever(billingPhone, "11999999999");

        verify(completeCheckoutPage)
                .clicar(btnContinuarBilling);
    }

    @Test
    void devePreencherEmailQuandoCampoEmailEstiverVisivel() {
        By billingFirstName = By.id("BillingNewAddress_FirstName");
        By billingEmail = By.id("BillingNewAddress_Email");
        By shippingAddressSection = By.id("checkout-step-shipping");

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(billingFirstName);

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(billingEmail);

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(shippingAddressSection);

        doNothing()
                .when(completeCheckoutPage)
                .escrever(any(By.class), anyString());

        doNothing()
                .when(completeCheckoutPage)
                .selecionarPorTexto(any(By.class), anyString());

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        completeCheckoutPage.preencherDadosCobranca();

        verify(completeCheckoutPage)
                .escrever(
                        eq(billingEmail),
                        argThat(email ->
                                email.startsWith("checkout")
                                        && email.endsWith("@teste.com")
                        )
                );
    }

    @Test
    void deveNaoPreencherDadosDeCobrancaQuandoFormularioNaoEstiverVisivel() {
        By billingFirstName = By.id("BillingNewAddress_FirstName");
        By btnContinuarBilling = By.cssSelector(
                "#billing-buttons-container input[value='Continue']"
        );
        By shippingAddressSection = By.id("checkout-step-shipping");

        doReturn(false)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(billingFirstName);

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(shippingAddressSection);

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        completeCheckoutPage.preencherDadosCobranca();

        verify(completeCheckoutPage, never())
                .escrever(any(By.class), anyString());

        verify(completeCheckoutPage, never())
                .selecionarPorTexto(any(By.class), anyString());

        verify(completeCheckoutPage)
                .clicar(btnContinuarBilling);
    }

    @Test
    void deveFazerExcecaoQuandoShippingAddressNaoForCarregado() {
        By billingFirstName = By.id("BillingNewAddress_FirstName");
        By shippingAddressSection = By.id("checkout-step-shipping");

        doReturn(false)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(billingFirstName);

        doReturn(false)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(shippingAddressSection);

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> completeCheckoutPage
                                .preencherDadosCobranca()
                );

        assertEquals(
                "A etapa Shipping Address não foi carregada após o Billing Address.",
                exception.getMessage()
        );
    }

    @Test
    void deveContinuarEnderecoDeEntregaQuandoOBotaoEstiverDisponivel() {
        By btnContinuarShippingAddress = By.cssSelector(
                "#shipping-buttons-container input[value='Continue']"
        );

        By shippingMethodSection =
                By.id("checkout-step-shipping-method");

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(btnContinuarShippingAddress);

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(shippingMethodSection);

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        completeCheckoutPage.continuarEnderecoEntrega();

        verify(completeCheckoutPage)
                .clicar(btnContinuarShippingAddress);
    }

    @Test
    void deveFazerExcecaoQuandoOBotaoContinuarShippingAddressNaoEstiverDisponivel() {
        By btnContinuarShippingAddress = By.cssSelector(
                "#shipping-buttons-container input[value='Continue']"
        );

        doReturn(false)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(btnContinuarShippingAddress);

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> completeCheckoutPage
                                .continuarEnderecoEntrega()
                );

        assertEquals(
                "O botão Continue da etapa Shipping Address não está disponível.",
                exception.getMessage()
        );

        verify(completeCheckoutPage, never())
                .clicar(any(By.class));
    }

    @Test
    void deveFazerExcecaoQuandoShippingMethodNaoForCarregado() {
        By btnContinuarShippingAddress = By.cssSelector(
                "#shipping-buttons-container input[value='Continue']"
        );

        By shippingMethodSection =
                By.id("checkout-step-shipping-method");

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(btnContinuarShippingAddress);

        doReturn(false)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(shippingMethodSection);

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> completeCheckoutPage
                                .continuarEnderecoEntrega()
                );

        assertEquals(
                "A etapa Shipping Method não foi carregada.",
                exception.getMessage()
        );

        verify(completeCheckoutPage)
                .clicar(btnContinuarShippingAddress);
    }

    @Test
    void deveSelecionarMetodoDeEntregaQuandoDisponivel() {
        By shippingMethod = By.id("shippingoption_0");

        By btnContinuarShippingMethod = By.cssSelector(
                "#shipping-method-buttons-container input[value='Continue']"
        );

        By paymentMethodSection =
                By.id("checkout-step-payment-method");

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(shippingMethod);

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(paymentMethodSection);

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        completeCheckoutPage.selecionarMetodoEntrega();

        verify(completeCheckoutPage)
                .clicar(shippingMethod);

        verify(completeCheckoutPage)
                .clicar(btnContinuarShippingMethod);
    }

    @Test
    void deveFazerExcecaoQuandoOMetodoDeEntregaNaoEstiverDisponivel() {
        By shippingMethod = By.id("shippingoption_0");

        doReturn(false)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(shippingMethod);

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> completeCheckoutPage
                                .selecionarMetodoEntrega()
                );

        assertEquals(
                "O método de entrega não está disponível. Verifique se o carrinho possui produto.",
                exception.getMessage()
        );

        verify(completeCheckoutPage, never())
                .clicar(any(By.class));
    }

    @Test
    void deveFazerExcecaoQuandoOPaymentMethodNaoForCarregado() {
        By shippingMethod = By.id("shippingoption_0");

        By btnContinuarShippingMethod = By.cssSelector(
                "#shipping-method-buttons-container input[value='Continue']"
        );

        By paymentMethodSection =
                By.id("checkout-step-payment-method");

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(shippingMethod);

        doReturn(false)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(paymentMethodSection);

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> completeCheckoutPage
                                .selecionarMetodoEntrega()
                );

        assertEquals(
                "A etapa Payment Method não foi carregada.",
                exception.getMessage()
        );

        verify(completeCheckoutPage)
                .clicar(shippingMethod);

        verify(completeCheckoutPage)
                .clicar(btnContinuarShippingMethod);
    }

    @Test
    void deveSelecionarMetodoDePagamentoQuandoDisponivel() {
        By paymentMethod = By.id("paymentmethod_0");

        By btnContinuarPaymentMethod = By.cssSelector(
                "#payment-method-buttons-container input[value='Continue']"
        );

        By paymentInformationSection =
                By.id("checkout-step-payment-info");

        WebElement paymentMethodElement =
                mock(WebElement.class);

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(paymentMethod);

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(paymentInformationSection);

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        when(driver.findElement(paymentMethod))
                .thenReturn(paymentMethodElement);

        when(paymentMethodElement.isSelected())
                .thenReturn(true);

        completeCheckoutPage.selecionarMetodoPagamento();

        verify(completeCheckoutPage)
                .clicar(paymentMethod);

        verify(completeCheckoutPage)
                .clicar(btnContinuarPaymentMethod);

        verify(paymentMethodElement)
                .isSelected();
    }

    @Test
    void deveFazerExcecaoQuandoMetodoDePagamentoNaoEstiverDisponivel() {
        By paymentMethod = By.id("paymentmethod_0");

        doReturn(false)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(paymentMethod);

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> completeCheckoutPage
                                .selecionarMetodoPagamento()
                );

        assertEquals(
                "O método de pagamento não está disponível.",
                exception.getMessage()
        );

        verify(completeCheckoutPage, never())
                .clicar(any(By.class));
    }

    @Test
    void deveFazerExcecaoQuandoOPaymentInformationNaoForCarregado() {
        By paymentMethod = By.id("paymentmethod_0");

        By btnContinuarPaymentMethod = By.cssSelector(
                "#payment-method-buttons-container input[value='Continue']"
        );

        By paymentInformationSection =
                By.id("checkout-step-payment-info");

        WebElement paymentMethodElement =
                mock(WebElement.class);

        doReturn(true)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(paymentMethod);

        doReturn(false)
                .when(completeCheckoutPage)
                .elementoEstaVisivel(paymentInformationSection);

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        when(driver.findElement(paymentMethod))
                .thenReturn(paymentMethodElement);

        when(paymentMethodElement.isSelected())
                .thenReturn(true);

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> completeCheckoutPage
                                .selecionarMetodoPagamento()
                );

        assertEquals(
                "A etapa Payment Information não foi carregada.",
                exception.getMessage()
        );

        verify(completeCheckoutPage)
                .clicar(paymentMethod);

        verify(completeCheckoutPage)
                .clicar(btnContinuarPaymentMethod);
    }

    @Test
    void deveConfirmarPedidoComSucesso() {
        By btnConfirmarPedido = By.cssSelector(
                "#confirm-order-buttons-container input[value='Confirm']"
        );

        By mensagemPedidoConcluido = By.cssSelector(
                ".section.order-completed .title strong"
        );

        WebElement botaoConfirmar =
                mock(WebElement.class);

        WebElement mensagemPedido =
                mock(WebElement.class);

        when(driver.findElement(btnConfirmarPedido))
                .thenReturn(botaoConfirmar);

        when(botaoConfirmar.isDisplayed())
                .thenReturn(true);

        when(botaoConfirmar.isEnabled())
                .thenReturn(true);

        when(driver.findElement(mensagemPedidoConcluido))
                .thenReturn(mensagemPedido);

        when(mensagemPedido.isDisplayed())
                .thenReturn(true);

        when(mensagemPedido.getText())
                .thenReturn(
                        "Your order has been successfully processed!"
                );

        completeCheckoutPage.confirmarPedido();

        verify(botaoConfirmar)
                .click();

        verify(mensagemPedido)
                .isDisplayed();

        verify(mensagemPedido)
                .getText();
    }

    @Test
    void deveContinuarInformacoesDePagamentoComSucesso() {
        By btnContinuarPaymentInformation = By.cssSelector(
                "#payment-info-buttons-container input[value='Continue']"
        );

        By confirmOrderSection =
                By.id("checkout-step-confirm-order");

        WebElement confirmOrderElement =
                mock(WebElement.class);

        doNothing()
                .when(completeCheckoutPage)
                .clicar(any(By.class));

        when(driver.findElement(confirmOrderSection))
                .thenReturn(confirmOrderElement);

        when(confirmOrderElement.isDisplayed())
                .thenReturn(true);

        completeCheckoutPage.continuarInformacoesPagamento();

        verify(completeCheckoutPage)
                .clicar(btnContinuarPaymentInformation);

        verify(driver)
                .findElement(confirmOrderSection);

        verify(confirmOrderElement)
                .isDisplayed();
    }
}
