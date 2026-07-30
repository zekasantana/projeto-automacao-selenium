package br.com.ezequias.automacao.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    private final By email = By.id("Email");
    private final By senha = By.id("Password");
    private final By btnLogin = By.cssSelector("input.login-button");
    private final By linkLogout = By.className("ico-logout");
    private final By linkLogin = By.className("ico-login");

    private final By mensagemErro =
            By.cssSelector(".validation-summary-errors");

    public void acessarLogin() {
        driver.get("https://demowebshop.tricentis.com/login");
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

    public void realizarLogin(String usuario, String password) {
        informarEmail(usuario);
        informarSenha(password);
        clicarEntrar();
    }

    public void aguardarLoginRealizado() {

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        linkLogout
                )
        );
    }

    public boolean loginRealizadoComSucesso() {
        return elementoEstaVisivel(linkLogout);
    }

    public String obterMensagemErro() {
        return driver.findElement(mensagemErro).getText();
    }

    public void realizarLogout() {
        clicar(linkLogout);
    }

    public boolean estaNaPaginaLogin() {
        return elementoEstaVisivel(linkLogin);
    }
}