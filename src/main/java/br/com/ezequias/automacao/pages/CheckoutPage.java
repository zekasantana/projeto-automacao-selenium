package br.com.ezequias.automacao.pages;

import org.openqa.selenium.By;

public class CheckoutPage extends BasePage {

    private By checkboxTermos =
            By.id("termsofservice");

    private By btnCheckout =
            By.id("checkout");

    private By tituloCheckout =
            By.cssSelector(".page-title");

    public void aceitarTermos() {
        clicar(checkboxTermos);
    }

    public void clicarCheckout() {
        clicar(btnCheckout);
    }

    public boolean estaNaPaginaCheckout() {
        return elementoEstaVisivel(tituloCheckout)
                && obterTexto(tituloCheckout).contains("Checkout");
    }
}
