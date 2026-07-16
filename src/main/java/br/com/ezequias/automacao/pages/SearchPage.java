package br.com.ezequias.automacao.pages;

import org.openqa.selenium.By;

public class SearchPage extends BasePage {

    private By campoBusca = By.id("small-searchterms");
    private By botaoBuscar = By.cssSelector("input[value='Search']");
    private By resultadoProduto = By.linkText("14.1-inch Laptop");

    public void buscarProduto(String produto) {
        escrever(campoBusca, produto);
        clicar(botaoBuscar);
    }

    public boolean produtoEncontrado() {
        return elementoEstaVisivel(resultadoProduto);
    }


}
