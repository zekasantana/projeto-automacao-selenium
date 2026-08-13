package br.com.ezequias.automacao.pages;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ExtendWith(MockitoExtension.class)
class BasePageTest {

    @Mock
    private WebDriver driver;

    @Mock
    private WebElement elemento;

    @Mock
    private WebDriver.Navigation navigation;

    @Mock
    private WebDriver.TargetLocator targetLocator;

    @Mock
    private Alert alert;

    private BasePage basePage;

    @BeforeEach
    void setUp() {
        basePage = new BasePage(driver);
    }

    @Test
    void deveEscreverNoElemento() {
        By locator = By.id("email");

        when(driver.findElement(locator))
                .thenReturn(elemento);

        when(elemento.isDisplayed())
                .thenReturn(true);

        basePage.escrever(locator, "teste@teste.com");

        verify(elemento).clear();
        verify(elemento).sendKeys("teste@teste.com");
    }

    @Test
    void deveObterTextoDoElemento() {
        By locator = By.id("mensagem");

        when(driver.findElement(locator))
                .thenReturn(elemento);

        when(elemento.isDisplayed())
                .thenReturn(true);

        when(elemento.getText())
                .thenReturn("Operação realizada com sucesso");

        String resultado = basePage.obterTexto(locator);

        assertEquals(
                "Operação realizada com sucesso",
                resultado
        );
    }

    @Test
    void deveAtualizarPagina() {
        when(driver.navigate())
                .thenReturn(navigation);

        basePage.atualizarPagina();

        verify(navigation).refresh();
    }

    @Test
    void deveObterTextoEFecharAlerta() {
        when(driver.switchTo())
                .thenReturn(targetLocator);

        when(targetLocator.alert())
                .thenReturn(alert);

        when(alert.getText())
                .thenReturn("Operação confirmada");

        String resultado =
                basePage.obterTextoEFecharAlerta();

        assertEquals(
                "Operação confirmada",
                resultado
        );

        verify(alert).accept();
    }

    @Test
    void deveClicarNoElemento() {
        By locator = By.id("botao");

        when(driver.findElement(locator))
                .thenReturn(elemento);

        when(elemento.isDisplayed())
                .thenReturn(true);

        when(elemento.isEnabled())
                .thenReturn(true);

        basePage.clicar(locator);

        verify(elemento).click();
    }

    @Test
    void deveRetornarTrueQuandoElementoEstiverVisivel() {
        By locator = By.id("mensagem");

        when(driver.findElement(locator))
                .thenReturn(elemento);

        when(elemento.isDisplayed())
                .thenReturn(true);

        boolean resultado =
                basePage.elementoEstaVisivel(locator);

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoElementoNaoEstiverVisivel() {
        By locator = By.id("mensagem");

        when(driver.findElement(locator))
                .thenThrow(new org.openqa.selenium.NoSuchElementException(
                        "Elemento não encontrado"

                ));
        boolean resultado =
                basePage.elementoEstaVisivel(locator);

        assertFalse(resultado);

    }

    @Test
    void deveRetornarTrueQuandoAlertaEstiverPresente() {
        when(driver.switchTo())
                .thenReturn(targetLocator);

        when(targetLocator.alert())
                .thenReturn(alert);

        boolean resultado =
                basePage.alertaEstaPresente();

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoAlertaNaoEstiverPresente() {
        when(driver.switchTo())
                .thenReturn(targetLocator);

        when(targetLocator.alert())
                .thenThrow(new org.openqa.selenium.NoAlertPresentException());

        boolean resultado =
                basePage.alertaEstaPresente();

        assertFalse(resultado);
    }

    @Test
    void deveEscreverComRetryQuandoElementoEstiverDisponivel() {
        By locator = By.id("campo");

        when(driver.findElement(locator))
                .thenReturn(elemento);

        when(elemento.isDisplayed())
                .thenReturn(true);

        when(elemento.isEnabled())
                .thenReturn(true);

        basePage.escreverComRetry(
                locator,
                "texto de teste"
        );

        verify(elemento).clear();
        verify(elemento).sendKeys("texto de teste");

    }

    @Test
    void deveRetornarVazioQuandoNaoExistirAlerta() {
        when(driver.switchTo())
                .thenReturn(targetLocator);

        when(targetLocator.alert())
                .thenThrow(new org.openqa.selenium.NoAlertPresentException());

        String resultado =
                basePage.obterTextoEFecharAlerta();

        assertEquals("", resultado);

    }

    @Test
    void deveRetornarTrueQuandoTextoEsperadoEstiverVisivel() {
        By locator = By.id("mensagem");

        when(driver.findElement(locator))
                .thenReturn(elemento);

        when(elemento.getText())
                .thenReturn("Pedido realizado com sucesso");

        boolean resultado =
                basePage.aguardarTextoVisivel(
                        locator,
                        "sucesso"
                );

        assertTrue(resultado);
    }

    @Test
    void deveRetornarFalseQuandoTextoEsperadoNaoEstiverVisivel() {
        By locator = By.id("mensagem");

        when(driver.findElement(locator))
                .thenReturn(elemento);

        when(elemento.getText())
                .thenReturn("Pedido em processamento");

        boolean resultado =
                basePage.aguardarTextoVisivel(
                        locator,
                        "sucesso"
                );

        assertFalse(resultado);
    }

    @Test
    void deveSelecionarOpcaoPorTextoVisivel() {
        By locator = By.id("pais");

        WebElement opcao =
                org.mockito.Mockito.mock(WebElement.class);

        when(driver.findElement(locator))
                .thenReturn(elemento);

        when(elemento.isDisplayed())
                .thenReturn(true);

        when(elemento.isEnabled())
                .thenReturn(true);

        when(elemento.getTagName())
                .thenReturn("select");

        when(elemento.getCssValue("visibility"))
                .thenReturn("visible");

        when(elemento.getCssValue("display"))
                .thenReturn("block");

        when(elemento.getCssValue("opacity"))
                .thenReturn("1");

        when(elemento.findElements(
                By.xpath(".//option[normalize-space(.) = \"Brasil\"]")))
                .thenReturn(java.util.List.of(opcao));

        when(opcao.isEnabled())
                .thenReturn(true);

        when(opcao.isSelected())
                .thenReturn(false);

        when(opcao.getCssValue("visibility"))
                .thenReturn("visible");

        when(opcao.getCssValue("display"))
                .thenReturn("block");

        when(opcao.getCssValue("opacity"))
                .thenReturn("1");

        basePage.selecionarPorTexto(
                locator,
                "Brasil"
        );

        verify(opcao).click();
    }


}