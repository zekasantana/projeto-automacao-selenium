package br.com.ezequias.automacao.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends BasePage {

    private static final Duration TEMPO_ESPERA =
            Duration.ofSeconds(20);

    private final By botaoAdicionarCarrinho =
            By.cssSelector("input[value='Add to cart']");

    private final By linkCarrinho =
            By.cssSelector("a.ico-cart");

    private final By notificacaoProdutoAdicionado =
            By.cssSelector("#bar-notification.success");

    private final By fecharNotificacao =
            By.cssSelector("#bar-notification .close");

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
            By.cssSelector("span.cart-qty");

    private final By removeProduto =
            By.cssSelector("input[name='removefromcart']");

    private final By atualizarCarrinho =
            By.cssSelector("input[name='updatecart']");

    public void adicionarAoCarrinho() {

        int quantidadeAntes = obterQuantidadeCarrinho();

        clicar(botaoAdicionarCarrinho);

        aguardarProdutoSerAdicionado(quantidadeAntes);

        fecharNotificacaoProdutoAdicionado();
    }

    private void aguardarProdutoSerAdicionado(
            int quantidadeAntes
    ) {

        WebDriverWait wait = new WebDriverWait(
                driver,
                TEMPO_ESPERA
        );

        wait.ignoring(
                StaleElementReferenceException.class
        );

        wait.ignoring(
                NoSuchElementException.class
        );

        wait.until(webDriver -> {
            int quantidadeAtual = obterQuantidadeCarrinho();

            boolean quantidadeAumentou =
                    quantidadeAtual > quantidadeAntes;

            boolean notificacaoApresentada =
                    notificacaoEstaVisivel();

            return quantidadeAumentou
                    || notificacaoApresentada;
        });
    }

    private int obterQuantidadeCarrinho() {

        try {
            String textoQuantidade = driver
                    .findElement(quantidadeCarrinho)
                    .getAttribute("textContent");

            if (textoQuantidade == null
                    || textoQuantidade.isBlank()) {

                return 0;
            }

            String somenteNumeros = textoQuantidade
                    .replaceAll("[^0-9]", "");

            if (somenteNumeros.isBlank()) {
                return 0;
            }

            return Integer.parseInt(somenteNumeros);

        } catch (NoSuchElementException
                 | StaleElementReferenceException
                 | NumberFormatException exception) {

            return 0;
        }
    }

    private boolean notificacaoEstaVisivel() {

        try {
            return driver
                    .findElement(notificacaoProdutoAdicionado)
                    .isDisplayed();

        } catch (NoSuchElementException
                 | StaleElementReferenceException exception) {

            return false;
        }
    }

    private void fecharNotificacaoProdutoAdicionado() {

        try {
            WebDriverWait wait = new WebDriverWait(
                    driver,
                    Duration.ofSeconds(3)
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

        } catch (TimeoutException
                 | NoSuchElementException
                 | StaleElementReferenceException exception) {

            System.out.println(
                    "Notificação não apresentada "
                            + "ou já fechada."
            );
        }
    }

    public void acessarCarrinho() {

        clicar(linkCarrinho);

        WebDriverWait wait = new WebDriverWait(
                driver,
                TEMPO_ESPERA
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
                TEMPO_ESPERA
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
                    "Produto não encontrado na primeira "
                            + "tentativa. Atualizando a página."
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

    public void limparCarrinho() {

        acessarCarrinho();

        if (carrinhoEstaVazio()) {
            return;
        }

        WebDriverWait wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        wait.until(
                ExpectedConditions.elementToBeClickable(
                        removeProduto
                )
        );

        clicar(removeProduto);
        clicar(atualizarCarrinho);

        wait.until(webDriver ->
                carrinhoEstaVazio()
        );
    }

    public boolean carrinhoEstaVazio() {

        return obterMensagemCarrinho()
                .contains(
                        "Your Shopping Cart is empty!"
                );
    }
}