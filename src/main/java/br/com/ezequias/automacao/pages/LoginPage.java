package br.com.ezequias.automacao.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private static final Duration TIMEOUT_LOGIN =
            Duration.ofSeconds(30);

    private final By email = By.id("Email");
    private final By senha = By.id("Password");
    private final By btnLogin =
            By.cssSelector("input.login-button");

    private final By linkLogout =
            By.className("ico-logout");

    private final By linkLogin =
            By.className("ico-login");

    private final By mensagemErro =
            By.cssSelector(".validation-summary-errors");

    public void acessarLogin() {
        driver.get(
                "https://demowebshop.tricentis.com/login"
        );
    }

    public void informarEmail(String usuario) {
        escrever(email, usuario);
    }

    public void informarSenha(String password) {
        escrever(senha, password);
    }

    public void clicarEntrar() {
        clicar(btnLogin);
    }

    public void realizarLogin(
            String usuario,
            String password
    ) {
        informarEmail(usuario);
        informarSenha(password);
        clicarEntrar();
    }

    public void aguardarLoginRealizado() {
        WebDriverWait wait = new WebDriverWait(
                driver,
                TIMEOUT_LOGIN
        );

        wait.ignoring(
                StaleElementReferenceException.class
        );

        wait.until(
                ExpectedConditions.and(
                        ExpectedConditions.not(
                                ExpectedConditions.urlContains(
                                        "/login"
                                )
                        ),
                        ExpectedConditions.visibilityOfElementLocated(
                                linkLogout
                        )
                )
        );
    }

    public boolean loginRealizadoComSucesso() {
        try {
            aguardarLoginRealizado();
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public String obterMensagemErro() {
        return driver.findElement(
                mensagemErro
        ).getText();
    }

    public void realizarLogout() {
        aguardarLoginRealizado();
        clicar(linkLogout);
    }

    public boolean estaNaPaginaLogin() {
        return elementoEstaVisivel(linkLogin);
    }
}