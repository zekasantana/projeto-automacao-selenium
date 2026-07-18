package br.com.ezequias.automacao.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    private final By botaoAdicionarCarrinho =
            By.cssSelector("input[value='Add to cart']");

    private final By linkCarrinho =
            By.cssSelector("span.cart-label");

    private final By notificacaoProdutoAdicionado =
            By.cssSelector("#bar-notification.success");

    private final By fecharNotificacao =
            By.cssSelector("#bar-notification .close");

    private final By produtoCarrinho =
            By.linkText("14.1-inch Laptop");

    private final By mensagemCarrinhoVazio =
            By.cssSelector(".order-summary-content");

    public void adicionarAoCarrinho() {
        tentarAdicionarProduto();

        if (processarErroAoAdicionarProduto()) {
            atualizarPagina();
            tentarAdicionarProduto();

            if (processarErroAoAdicionarProduto()) {
                throw new IllegalStateException(
                        "Não foi possível adicionar o produto ao carrinho após duas tentativas."
                );
            }
        }

        aguardarProdutoSerAdicionado();
        fecharNotificacaoProdutoAdicionado();
    }

    private void tentarAdicionarProduto() {
        clicar(botaoAdicionarCarrinho);
    }

    private boolean processarErroAoAdicionarProduto() {
        if (!alertaEstaPresente()) {
            return false;
        }

        String mensagem = obterTextoEFecharAlerta();

        System.out.println(
                "Alerta apresentado pela aplicação: " + mensagem
        );

        return mensagem.contains(
                "Failed to add the product to the cart"
        );
    }

    private void aguardarProdutoSerAdicionado() {
        new WebDriverWait(driver, Duration.ofSeconds(15))
                .until(
                        ExpectedConditions.visibilityOfElementLocated(
                                notificacaoProdutoAdicionado
                        )
                );
    }

    private void fecharNotificacaoProdutoAdicionado() {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(10)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        fecharNotificacao
                )
        ).click();

        wait.until(
                ExpectedConditions.invisibilityOfElementLocated(
                        notificacaoProdutoAdicionado
                )
        );
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