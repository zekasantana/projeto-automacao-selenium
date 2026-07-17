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

public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
    }

    protected void clicar(By elemento) {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
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
}