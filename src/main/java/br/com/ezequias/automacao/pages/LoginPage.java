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

    private static final Duration TIMEOUT_LOGOUT =
            Duration.ofSeconds(20);

    private final By email =
            By.id("Email");

    private final By senha =
            By.id("Password");

    private final By btnLogin =
            By.cssSelector("input.login-button");

    private final By linkLogout =
            By.className("ico-logout");

    private final By linkLogin =
            By.className("ico-login");

    private final By mensagemErroLogin =
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

    public boolean loginRealizadoComSucesso() {

        WebDriverWait wait =
                new WebDriverWait(driver, TIMEOUT_LOGIN);

        wait.ignoring(
                StaleElementReferenceException.class
        );

        try {
            return wait.until(webDriver -> {

                boolean saiuDaPaginaLogin =
                        !webDriver.getCurrentUrl()
                                .contains("/login");

                boolean logoutVisivel =
                        !webDriver.findElements(linkLogout)
                                .isEmpty()
                                && webDriver.findElement(linkLogout)
                                .isDisplayed();

                return saiuDaPaginaLogin && logoutVisivel;
            });

        } catch (TimeoutException exception) {
            registrarFalhaLogin();
            return false;
        }
    }

    public void aguardarLoginRealizado() {

        if (!loginRealizadoComSucesso()) {
            throw new TimeoutException(
                    "O login não foi realizado com sucesso. "
                            + "URL atual: "
                            + driver.getCurrentUrl()
            );
        }
    }

    public String obterMensagemErro() {

        WebDriverWait wait =
                new WebDriverWait(driver, TIMEOUT_LOGIN);

        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        mensagemErroLogin
                )
        ).getText();
    }

    public void realizarLogout() {

        WebDriverWait wait =
                new WebDriverWait(driver, TIMEOUT_LOGOUT);

        wait.ignoring(
                StaleElementReferenceException.class
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        linkLogout
                )
        ).click();

        wait.until(
                ExpectedConditions.or(
                        ExpectedConditions.urlContains("/login"),
                        ExpectedConditions.visibilityOfElementLocated(
                                linkLogin
                        )
                )
        );
    }

    public boolean estaNaPaginaLogin() {

        boolean urlLogin =
                driver.getCurrentUrl().contains("/login");

        boolean linkLoginVisivel =
                elementoEstaVisivel(linkLogin);

        return urlLogin || linkLoginVisivel;
    }

    private void registrarFalhaLogin() {

        System.out.println(
                "URL após tentativa de login: "
                        + driver.getCurrentUrl()
        );

        if (!driver.findElements(mensagemErroLogin).isEmpty()) {

            String mensagem =
                    driver.findElement(mensagemErroLogin)
                            .getText();

            System.out.println(
                    "Mensagem apresentada pelo site: "
                            + mensagem
            );
        }
    }
}