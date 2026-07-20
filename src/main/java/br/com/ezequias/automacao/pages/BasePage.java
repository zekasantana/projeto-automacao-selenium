package br.com.ezequias.automacao.pages;

import java.time.Duration;

import br.com.ezequias.automacao.factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.Alert;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.TimeoutException;

public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
    }

    protected void clicar(By elemento) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(3)
        );

        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(elemento))
                .click();
    }

    protected void escrever(By elemento, String texto) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(elemento))
                .clear();

        wait.until(ExpectedConditions.visibilityOfElementLocated(elemento))
                .sendKeys(texto);
    }

    protected String obterTexto(By elemento) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(elemento)
        ).getText();
    }

    protected void selecionarPorTexto(By elemento, String texto) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        Select select = new Select(
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(elemento)
                )
        );

        select.selectByVisibleText(texto);
    }

    protected boolean elementoExiste(By elemento) {
        return !driver.findElements(elemento).isEmpty();
    }

    protected boolean elementoEstaVisivel(By elemento) {
        try {
            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(10)
            );

            return wait.until(
                    ExpectedConditions.visibilityOfElementLocated(elemento)
            ).isDisplayed();

        } catch (TimeoutException e) {
            return false;
        }
    }

    protected boolean alertaEstaPresente() {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(3))
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
            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(30)
            );

            return wait.until(
                    ExpectedConditions.textToBePresentInElementLocated(
                            elemento,
                            textoEsperado
                    )
            );
        } catch (TimeoutException e) {
            return false;
        }
    }
}