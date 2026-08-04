package br.com.ezequias.automacao.pages;

import java.time.Duration;

import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    private final By botaoAdicionarCarrinho =
            By.cssSelector("input[value='Add to cart']");

    private final By linkCarrinho =
            By.cssSelector("a.ico-cart");

    private final By notificacaoProdutoAdicionado =
            By.cssSelector("#bar-notification.success");

    private final By fecharNotificacao =
            By.cssSelector("#bar-notification .close");

    private final By produtoCarrinho =
            By.cssSelector(".cart-item-row .product a");

    private final By produtoEsperadoCarrinho =
            By.xpath(
                    "//tr[contains(@class,'cart-item-row')]"
                            + "//td[contains(@class,'product')]"
                            + "//a[normalize-space()='14.1-inch Laptop']"
            );

    private final By paginaCarrinho =
            By.cssSelector(".shopping-cart-page");

    private final By mensagemCarrinhoVazio =
            By.cssSelector(".order-summary-content");

    private final By quantidadeCarrinho =
            By.cssSelector(".cart-qty");

    public void adicionarAoCarrinho() {
        tentarAdicionarProduto();

        if (processarErroAoAdicionarProduto()) {
            atualizarPagina();
            tentarAdicionarProduto();

            if (processarErroAoAdicionarProduto()) {
                throw new IllegalStateException(
                        "Não foi possível adicionar o produto "
                                + "ao carrinho após duas tentativas."
                );
            }
        }

        aguardarProdutoSerAdicionado();
        aguardarCarrinhoAtualizar();
        fecharNotificacaoProdutoAdicionado();
    }

    private void aguardarCarrinhoAtualizar() {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        wait.until(webDriver -> {
            String quantidade = webDriver
                    .findElement(quantidadeCarrinho)
                    .getText();

            return !quantidade.contains("(0)");
        });
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
                "Alerta apresentado pela aplicação: "
                        + mensagem
        );

        return mensagem.contains(
                "Failed to add the product to the cart"
        );
    }

    private void aguardarProdutoSerAdicionado() {
        new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        ).until(
                ExpectedConditions.visibilityOfElementLocated(
                        notificacaoProdutoAdicionado
                )
        );
    }

    private void fecharNotificacaoProdutoAdicionado() {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        try {
            wait.until(webDriver -> {
                try {
                    webDriver
                            .findElement(fecharNotificacao)
                            .click();

                    return true;

                } catch (
                        ElementClickInterceptedException
                        | StaleElementReferenceException exception
                ) {
                    return false;
                }
            });

            wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(
                            notificacaoProdutoAdicionado
                    )
            );

        } catch (TimeoutException exception) {
            throw new IllegalStateException(
                    "A notificação do produto não desapareceu "
                            + "dentro do tempo esperado.",
                    exception
            );
        }
    }

    public void acessarCarrinho() {
        clicar(linkCarrinho);

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        wait.until(
                ExpectedConditions.urlContains("/cart")
        );

        wait.until(
                ExpectedConditions.presenceOfElementLocated(
                        paginaCarrinho
                )
        );
    }

    public boolean produtoEstaNoCarrinho() {
        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(20)
        );

        try {
            wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            produtoEsperadoCarrinho
                    )
            );

            return true;

        } catch (TimeoutException exception) {
            System.out.println(
                    "Produto não encontrado no carrinho "
                            + "na primeira tentativa. "
                            + "Atualizando a página."
            );

            driver.navigate().refresh();

            try {
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                produtoEsperadoCarrinho
                        )
                );

                return true;

            } catch (TimeoutException segundaTentativa) {
                System.out.println(
                        "Produto não encontrado no carrinho "
                                + "após atualizar a página."
                );

                return false;
            }
        }
    }

    public String obterMensagemCarrinho() {
        return obterTexto(mensagemCarrinhoVazio);
    }

    public boolean carrinhoEstaVazio() {
        return obterMensagemCarrinho()
                .contains(
                        "Your Shopping Cart is empty!"
                );
    }
}