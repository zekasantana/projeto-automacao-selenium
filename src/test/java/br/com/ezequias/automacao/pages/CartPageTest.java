package br.com.ezequias.automacao.pages;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;

import br.com.ezequias.automacao.factory.DriverFactory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

class CartPageTest {

    private WebDriver driver;
    private CartPage cartPage;
    private MockedStatic<DriverFactory> driverFactoryMock;

    @BeforeEach
    void setUp() {
        driver = mock(WebDriver.class);

        driverFactoryMock = mockStatic(DriverFactory.class);
        driverFactoryMock
                .when(DriverFactory::getDriver)
                .thenReturn(driver);

        cartPage = spy(new CartPage());
    }

    @AfterEach
    void tearDown() {
        driverFactoryMock.close();
    }

    @Test
    void deveObterMensagemCarrinho() {
        doReturn("Your Shopping Cart is empty!")
                .when(cartPage)
                .obterTexto(any(By.class));

        String mensagem = cartPage.obterMensagemCarrinho();

        assertTrue(
                mensagem.contains(
                        "Your Shopping Cart is empty!"
                )
        );
    }

    @Test
    void deveRetornarTrueQuandoCarrinhoEstaVazio() {
        doReturn("Your Shopping Cart is empty!")
                .when(cartPage)
                .obterMensagemCarrinho();

        boolean resultado = cartPage.carrinhoEstaVazio();

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoCarrinhoNaoEstaVazio() {
        doReturn("14.1-inch Laptop")
                .when(cartPage)
                .obterMensagemCarrinho();

        boolean resultado = cartPage.carrinhoEstaVazio();

        assertFalse(resultado);
    }

    @Test
    void deveRetornarTrueQuandoProdutoEstaNoCarrinho() {
        WebElement produto = mock(WebElement.class);

        @SuppressWarnings("unchecked")
        ExpectedCondition<WebElement> condition =
                mock(ExpectedCondition.class);

        when(condition.apply(driver))
                .thenReturn(produto);

        try (MockedStatic<ExpectedConditions> expectedConditionsMock =
                     mockStatic(ExpectedConditions.class)) {

            expectedConditionsMock
                    .when(() ->
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            any(By.class)
                                    )
                    )
                    .thenReturn(condition);

            boolean resultado =
                    cartPage.produtoEstaNoCarrinho();

            assertTrue(resultado);
        }
    }

    @Test
    void deveRetornarTrueQuandoProdutoEstaNoCarrinhoAposRefresh() {
        WebElement produto = mock(WebElement.class);
        WebDriver.Navigation navigation =
                mock(WebDriver.Navigation.class);

        when(driver.navigate())
                .thenReturn(navigation);

        @SuppressWarnings("unchecked")
        ExpectedCondition<WebElement> condition =
                mock(ExpectedCondition.class);

        when(condition.apply(driver))
                .thenThrow(new TimeoutException())
                .thenReturn(produto);

        try (MockedStatic<ExpectedConditions> expectedConditionsMock =
                     mockStatic(ExpectedConditions.class)) {

            expectedConditionsMock
                    .when(() ->
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            any(By.class)
                                    )
                    )
                    .thenReturn(condition);

            boolean resultado =
                    cartPage.produtoEstaNoCarrinho();

            assertTrue(resultado);

            verify(navigation).refresh();
        }
    }

    @Test
    void deveRetornarFalseQuandoProdutoNaoEstaNoCarrinho() {
        WebDriver.Navigation navigation =
                mock(WebDriver.Navigation.class);

        when(driver.navigate())
                .thenReturn(navigation);

        @SuppressWarnings("unchecked")
        ExpectedCondition<WebElement> condition =
                mock(ExpectedCondition.class);

        when(condition.apply(driver))
                .thenThrow(new TimeoutException())
                .thenThrow(new TimeoutException());

        try (MockedStatic<ExpectedConditions> expectedConditionsMock =
                     mockStatic(ExpectedConditions.class)) {

            expectedConditionsMock
                    .when(() ->
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            any(By.class)
                                    )
                    )
                    .thenReturn(condition);

            boolean resultado =
                    cartPage.produtoEstaNoCarrinho();

            assertFalse(resultado);

            verify(navigation).refresh();
        }
    }

    @Test
    void deveAcessarCarrinho() {
        WebElement paginaCarrinho = mock(WebElement.class);

        @SuppressWarnings("unchecked")
        ExpectedCondition<Boolean> condicaoUrl =
                mock(ExpectedCondition.class);

        @SuppressWarnings("unchecked")
        ExpectedCondition<WebElement> condicaoPagina =
                mock(ExpectedCondition.class);

        when(condicaoUrl.apply(driver))
                .thenReturn(true);

        when(condicaoPagina.apply(driver))
                .thenReturn(paginaCarrinho);

        doNothing()
                .when(cartPage)
                .clicar(any(By.class));

        try (MockedStatic<ExpectedConditions> expectedConditionsMock =
                     mockStatic(ExpectedConditions.class)) {

            expectedConditionsMock
                    .when(() ->
                            ExpectedConditions.urlContains("/cart")
                    )
                    .thenReturn(condicaoUrl);

            expectedConditionsMock
                    .when(() ->
                            ExpectedConditions.presenceOfElementLocated(
                                    any(By.class)
                            )
                    )
                    .thenReturn(condicaoPagina);

            cartPage.acessarCarrinho();

            verify(cartPage)
                    .clicar(any(By.class));
        }
    }

    @Test
    void deveAdicionarProdutoAoCarrinhoComSucesso() {
        WebElement quantidade = mock(WebElement.class);
        WebElement botaoFechar = mock(WebElement.class);
        WebElement notificacao = mock(WebElement.class);

        doNothing()
                .when(cartPage)
                .clicar(any(By.class));

        doReturn(false)
                .when(cartPage)
                .alertaEstaPresente();

        when(quantidade.getText())
                .thenReturn("(1)");

        when(driver.findElement(any(By.class)))
                .thenReturn(quantidade, botaoFechar);

        @SuppressWarnings("unchecked")
        ExpectedCondition<WebElement> condicaoNotificacao =
                mock(ExpectedCondition.class);

        @SuppressWarnings("unchecked")
        ExpectedCondition<Boolean> condicaoInvisibilidade =
                mock(ExpectedCondition.class);

        when(condicaoNotificacao.apply(driver))
                .thenReturn(notificacao);

        when(condicaoInvisibilidade.apply(driver))
                .thenReturn(true);

        try (MockedStatic<ExpectedConditions> expectedConditionsMock =
                     mockStatic(ExpectedConditions.class)) {

            expectedConditionsMock
                    .when(() ->
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            any(By.class)
                                    )
                    )
                    .thenReturn(condicaoNotificacao);

            expectedConditionsMock
                    .when(() ->
                            ExpectedConditions
                                    .invisibilityOfElementLocated(
                                            any(By.class)
                                    )
                    )
                    .thenReturn(condicaoInvisibilidade);

            cartPage.adicionarAoCarrinho();

            verify(cartPage)
                    .clicar(any(By.class));

            verify(botaoFechar)
                    .click();
        }
    }

    @Test
    void deveTentarAdicionarProdutoNovamenteQuandoOcorrerErro() {
        WebElement quantidade = mock(WebElement.class);
        WebElement botaoFechar = mock(WebElement.class);
        WebElement notificacao = mock(WebElement.class);

        doNothing()
                .when(cartPage)
                .clicar(any(By.class));

        doNothing()
                .when(cartPage)
                .atualizarPagina();

        doReturn(true, false)
                .when(cartPage)
                .alertaEstaPresente();

        doReturn("Failed to add the product to the cart")
                .when(cartPage)
                .obterTextoEFecharAlerta();

        when(quantidade.getText())
                .thenReturn("(1)");

        when(driver.findElement(any(By.class)))
                .thenReturn(quantidade, botaoFechar);

        @SuppressWarnings("unchecked")
        ExpectedCondition<WebElement> condicaoNotificacao =
                mock(ExpectedCondition.class);

        @SuppressWarnings("unchecked")
        ExpectedCondition<Boolean> condicaoInvisibilidade =
                mock(ExpectedCondition.class);

        when(condicaoNotificacao.apply(driver))
                .thenReturn(notificacao);

        when(condicaoInvisibilidade.apply(driver))
                .thenReturn(true);

        try (MockedStatic<ExpectedConditions> expectedConditionsMock =
                     mockStatic(ExpectedConditions.class)) {

            expectedConditionsMock
                    .when(() ->
                            ExpectedConditions
                                    .visibilityOfElementLocated(
                                            any(By.class)
                                    )
                    )
                    .thenReturn(condicaoNotificacao);

            expectedConditionsMock
                    .when(() ->
                            ExpectedConditions
                                    .invisibilityOfElementLocated(
                                            any(By.class)
                                    )
                    )
                    .thenReturn(condicaoInvisibilidade);

            cartPage.adicionarAoCarrinho();

            verify(cartPage, times(2))
                    .clicar(any(By.class));

            verify(cartPage)
                    .atualizarPagina();

            verify(cartPage)
                    .obterTextoEFecharAlerta();

            verify(botaoFechar)
                    .click();
        }
    }

    @Test
    void deveLancarExcecaoQuandoFalharAoAdicionarProdutoDuasVezes() {
        doNothing()
                .when(cartPage)
                .clicar(any(By.class));

        doNothing()
                .when(cartPage)
                .atualizarPagina();

        doReturn(true, true)
                .when(cartPage)
                .alertaEstaPresente();

        doReturn("Failed to add the product to the cart")
                .when(cartPage)
                .obterTextoEFecharAlerta();

        IllegalStateException exception =
                assertThrows(
                        IllegalStateException.class,
                        () -> cartPage.adicionarAoCarrinho()
                );

        assertTrue(
                exception.getMessage().contains(
                        "Não foi possível adicionar o produto"
                )
        );

        verify(cartPage, times(2))
                .clicar(any(By.class));

        verify(cartPage)
                .atualizarPagina();

        verify(cartPage, times(2))
                .obterTextoEFecharAlerta();
    }
}
