package br.com.ezequias.automacao.pages;

import java.time.Duration;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import br.com.ezequias.automacao.factory.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class BasePage {

    protected WebDriver driver;

    public BasePage() {
        this.driver = DriverFactory.getDriver();
    }

    protected void clicar(By elemento) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.ignoring(StaleElementReferenceException.class)
                .until(ExpectedConditions.elementToBeClickable(elemento))
                .click();
    }
    protected void escrever(By elemento, String texto) {
        driver.findElement(elemento).clear();
        driver.findElement(elemento).sendKeys(texto);
    }

    protected String obterTexto(By elemento) {
        return driver.findElement(elemento).getText();
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