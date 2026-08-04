package br.com.ezequias.automacao.pages;

import java.time.Duration;

import br.com.ezequias.automacao.factory.DriverFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

    private static final Duration TIMEOUT_PADRAO =
            Duration.ofSeconds(10);

    private static final Duration TIMEOUT_TEXTO =
            Duration.ofSeconds(30);

    private static final Duration TIMEOUT_ALERTA =
            Duration.ofSeconds(3);

    protected final WebDriver driver;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
    }

    protected void clicar(By elemento) {
        criarEspera(TIMEOUT_PADRAO)
                .ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(elemento))
                .click();
    }

    protected void escrever(By elemento, String texto) {
        WebElement campo = criarEspera(TIMEOUT_PADRAO)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                elemento
                        )
                );

        campo.clear();
        campo.sendKeys(texto);
    }

    protected String obterTexto(By elemento) {
        return criarEspera(TIMEOUT_PADRAO)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                elemento
                        )
                )
                .getText();
    }

    protected void selecionarPorTexto(
            By elemento,
            String texto
    ) {
        WebElement campoSelecao = criarEspera(TIMEOUT_PADRAO)
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                elemento
                        )
                );

        Select select = new Select(campoSelecao);
        select.selectByVisibleText(texto);
    }

    protected boolean elementoExiste(By elemento) {
        return !driver.findElements(elemento).isEmpty();
    }

    protected boolean elementoEstaVisivel(By elemento) {
        try {
            criarEspera(TIMEOUT_PADRAO)
                    .until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    elemento
                            )
                    );

            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean alertaEstaPresente() {
        try {
            criarEspera(TIMEOUT_ALERTA)
                    .until(ExpectedConditions.alertIsPresent());

            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    protected String obterTextoEFecharAlerta() {
        try {
            Alert alerta = driver.switchTo().alert();
            String mensagem = alerta.getText();

            alerta.accept();

            return mensagem;
        } catch (NoAlertPresentException e) {
            return "";
        }
    }

    protected void atualizarPagina() {
        driver.navigate().refresh();
    }

    protected boolean aguardarTextoVisivel(
            By elemento,
            String textoEsperado
    ) {
        try {
            return criarEspera(TIMEOUT_TEXTO)
                    .until(
                            ExpectedConditions
                                    .textToBePresentInElementLocated(
                                            elemento,
                                            textoEsperado
                                    )
                    );
        } catch (TimeoutException e) {
            return false;
        }
    }

    private WebDriverWait criarEspera(Duration timeout) {
        return new WebDriverWait(driver, timeout);
    }
}