package br.com.ezequias.automacao.stepdefinitions;

import br.com.ezequias.automacao.pages.CartPage;
import br.com.ezequias.automacao.pages.LoginPage;
import br.com.ezequias.automacao.pages.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompraSteps {

    private final LoginPage loginPage = new LoginPage();
    private final SearchPage searchPage = new SearchPage();
    private final CartPage cartPage = new CartPage();

    @Given("que o usuário realizou login para efetuar uma compra")
    public void queOUsuarioRealizouLoginParaEfetuarUmaCompra() {
        loginPage.acessarLogin();

        loginPage.realizarLogin(
                "teste2022@teste.com.br",
                "teste@"
        );

        assertTrue(
                loginPage.loginRealizadoComSucesso(),
                "O usuário não conseguiu realizar o login."
        );
    }

    @When("busca pelo produto {string}")
    public void buscaPeloProduto(String produto) {
        searchPage.buscarProduto(produto);
    }

    @And("adiciona o produto ao carrinho")
    public void adicionaOProdutoAoCarrinho() {
        cartPage.adicionarAoCarrinho();
    }

    @And("acessa o carrinho")
    public void acessaOCarrinho() {
        cartPage.acessarCarrinho();
    }

    @Then("o produto deve estar no carrinho")
    public void oProdutoDeveEstarNoCarrinho() {
        assertTrue(
                cartPage.produtoEstaNoCarrinho(),
                "O produto não foi encontrado no carrinho."
        );
    }


}
