package br.com.ezequias.automacao.stepdefinitions;

import br.com.ezequias.automacao.pages.CartPage;
import br.com.ezequias.automacao.pages.CheckoutPage;
import br.com.ezequias.automacao.pages.LoginPage;
import br.com.ezequias.automacao.pages.SearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutNegativoSteps {

    private final LoginPage loginPage = new LoginPage();
    private final SearchPage searchPage = new SearchPage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();

    @Given("que possuo um produto no carrinho para o checkout negativo")
    public void quePossuoUmProdutoNoCarrinhoParaOCheckoutNegativo() {

        loginPage.acessarLogin();

        loginPage.realizarLogin(
                "teste2022@teste.com.br",
                "123456"
        );

        searchPage.buscarProduto("14.1-inch Laptop");
        searchPage.clicarProduto();

        cartPage.adicionarAoCarrinho();
        cartPage.acessarCarrinho();
    }

    @When("tento realizar o checkout sem aceitar os termos de serviço")
    public void tentoRealizarOCheckoutSemAceitarOsTermosDeServico() {

        checkoutPage.clicarCheckout();
    }

    @Then("devo visualizar a mensagem de obrigatoriedade dos termos")
    public void devoVisualizarAMensagemDeObrigatoriedadeDosTermos() {

        assertTrue(
                checkoutPage.mensagemTermosEstaVisivel(),
                "A mensagem de obrigatoriedade dos termos não foi exibida."
        );

        assertEquals(
                "Please accept the terms of service before the next step.",
                checkoutPage.obterMensagemTermos(),
                "A mensagem exibida está diferente da esperada."
        );
    }

    @Given("que o usuário está logado")
    public void queOUsuarioEstaLogado() {

        loginPage.acessarLogin();

        loginPage.realizarLogin(
                "teste2022@teste.com.br",
                "123456"
        );
    }

    @When("acessa o carrinho sem produtos")
    public void acessaOCarrinhoSemProdutos() {

        cartPage.acessarCarrinho();
    }

    @Then("o carrinho deve estar vazio")
    public void oCarrinhoDeveEstarVazio() {

        assertTrue(
                cartPage.carrinhoEstaVazio(),
                "O carrinho não está vazio."
        );
    }
}
