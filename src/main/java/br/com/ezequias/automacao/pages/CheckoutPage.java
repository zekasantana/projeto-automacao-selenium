package br.com.ezequias.automacao.pages;

import org.openqa.selenium.By;
import java.time.Duration;
import br.com.ezequias.automacao.factory.DriverFactory;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage {

    private By checkboxTermos =
            By.id("termsofservice");

    private By btnCheckout =
            By.id("checkout");

    private By tituloCheckout =
            By.cssSelector(".page-title");

    private By mensagemTermos =
            By.id("terms-of-service-warning-box");

    public void aceitarTermos() {
        clicar(checkboxTermos);
    }

    public void clicarCheckout() {
        clicar(btnCheckout);
    }

    public boolean estaNaPaginaCheckout() {
        try {
            WebDriverWait wait = new WebDriverWait(
                    DriverFactory.getDriver(),
                    Duration.ofSeconds(30)
            );

            return wait.until(driver ->
                    driver.getCurrentUrl().contains("/onepagecheckout")
                            && elementoEstaVisivel(tituloCheckout)
                            && obterTexto(tituloCheckout)
                            .trim()
                            .equalsIgnoreCase("Checkout")
            );
        } catch (TimeoutException e) {
            return false;
        }
    }

    public boolean mensagemTermosEstaVisivel() {
        return elementoEstaVisivel(mensagemTermos);
    }

    public String obterMensagemTermos() {
        return obterTexto(mensagemTermos);
    }

}
