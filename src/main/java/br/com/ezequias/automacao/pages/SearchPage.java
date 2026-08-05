package br.com.ezequias.automacao.pages;

import org.openqa.selenium.By;

public class SearchPage extends BasePage {

    private static final String PRODUTO_ESPERADO =
            "14.1-inch Laptop";

    private final By campoBusca =
            By.id("small-searchterms");

    private final By botaoBuscar =
            By.cssSelector("input[value='Search']");

    private final By resultadoProduto =
            By.linkText(PRODUTO_ESPERADO);

    private final By mensagemProdutoNaoEncontrado =
            By.cssSelector(".search-results .result");

    public void buscarProduto(String produto) {
        escreverComRetry(campoBusca, produto);
        clicar(botaoBuscar);
    }

    public boolean produtoEncontrado() {
        return elementoEstaVisivel(resultadoProduto);
    }

    public void clicarProduto() {
        clicar(resultadoProduto);
    }

    public boolean mensagemProdutoNaoEncontradoEstaVisivel() {
        return elementoEstaVisivel(
                mensagemProdutoNaoEncontrado
        );
    }

    public String obterMensagemProdutoNaoEncontrado() {
        return obterTexto(mensagemProdutoNaoEncontrado);
    }
}