package br.com.ezequias.automacao.pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

import br.com.ezequias.automacao.factory.DriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.MockedStatic;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

class SearchPageTest {

    private WebDriver driver;
    private SearchPage searchPage;
    private MockedStatic<DriverFactory> driverFactoryMock;

    @BeforeEach
    void setUp() {
        driver = mock(WebDriver.class);

        driverFactoryMock = mockStatic(DriverFactory.class);
        driverFactoryMock
                .when(DriverFactory::getDriver)
                .thenReturn(driver);

        searchPage = spy(new SearchPage());
    }

    @AfterEach
    void tearDown() {
        driverFactoryMock.close();
    }

    @Test
    void deveBuscarProduto() {
        String produto = "14.1-inch Laptop";

        doNothing()
                .when(searchPage)
                .escreverComRetry(
                        any(By.class),
                        anyString()
                );

        doNothing()
                .when(searchPage)
                .clicar(any(By.class));

        searchPage.buscarProduto(produto);

        ArgumentCaptor<By> campoCaptor =
                ArgumentCaptor.forClass(By.class);

        ArgumentCaptor<String> produtoCaptor =
                ArgumentCaptor.forClass(String.class);

        verify(searchPage)
                .escreverComRetry(
                        campoCaptor.capture(),
                        produtoCaptor.capture()
                );

        assertEquals(
                "By.id: small-searchterms",
                campoCaptor.getValue().toString()
        );

        assertEquals(
                produto,
                produtoCaptor.getValue()
        );

        ArgumentCaptor<By> botaoCaptor =
                ArgumentCaptor.forClass(By.class);

        verify(searchPage)
                .clicar(botaoCaptor.capture());

        assertEquals(
                "By.cssSelector: input[value='Search']",
                botaoCaptor.getValue().toString()
        );
    }

    @Test
    void deveRetornarTrueQuandoProdutoEncontrado() {
        doReturn(true)
                .when(searchPage)
                .elementoEstaVisivel(any(By.class));

        boolean resultado =
                searchPage.produtoEncontrado();

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoProdutoNaoEncontrado() {
        doReturn(false)
                .when(searchPage)
                .elementoEstaVisivel(any(By.class));

        boolean resultado =
                searchPage.produtoEncontrado();

        assertFalse(resultado);
    }

    @Test
    void deveClicarNoProduto() {
        doNothing()
                .when(searchPage)
                .clicar(any(By.class));

        searchPage.clicarProduto();

        ArgumentCaptor<By> locatorCaptor =
                ArgumentCaptor.forClass(By.class);

        verify(searchPage)
                .clicar(locatorCaptor.capture());

        assertEquals(
                "By.linkText: 14.1-inch Laptop",
                locatorCaptor.getValue().toString()
        );
    }

    @Test
    void deveRetornarTrueQuandoMensagemProdutoNaoEncontradoVisivel() {
        doReturn(true)
                .when(searchPage)
                .elementoEstaVisivel(any(By.class));

        boolean resultado =
                searchPage
                        .mensagemProdutoNaoEncontradoEstaVisivel();

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoMensagemProdutoNaoEncontradoNaoVisivel() {
        doReturn(false)
                .when(searchPage)
                .elementoEstaVisivel(any(By.class));

        boolean resultado =
                searchPage
                        .mensagemProdutoNaoEncontradoEstaVisivel();

        assertFalse(resultado);
    }

    @Test
    void deveObterMensagemProdutoNaoEncontrado() {
        String mensagem =
                "No products were found that matched your criteria.";

        doReturn(mensagem)
                .when(searchPage)
                .obterTexto(any(By.class));

        String resultado =
                searchPage.obterMensagemProdutoNaoEncontrado();

        assertEquals(
                mensagem,
                resultado
        );
    }
}
