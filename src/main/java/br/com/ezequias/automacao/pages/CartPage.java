package br.com.ezequias.automacao.pages;

import org.openqa.selenium.By;

public class CartPage extends BasePage {

    private By botaoAdicionarCarrinho =
            By.cssSelector("input[value='Add to cart']");

    private By linkCarrinho =
            By.cssSelector("span.cart-label");

    private By produtoCarrinho =
            By.linkText("14.1-inch Laptop");

    private By mensagemCarrinhoVazio =
            By.cssSelector(".order-summary-content");

    public void adicionarAoCarrinho() {
        clicar(botaoAdicionarCarrinho);
    }

    public void acessarCarrinho() {
        clicar(linkCarrinho);
    }

    public boolean produtoEstaNoCarrinho() {
        return elementoEstaVisivel(produtoCarrinho);
    }

    public String obterMensagemCarrinho() {
        return obterTexto(mensagemCarrinhoVazio);
    }

    public boolean carrinhoEstaVazio() {
        return obterMensagemCarrinho()
                .contains("Your Shopping Cart is empty!");
    }

}
