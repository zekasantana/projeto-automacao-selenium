package br.com.ezequias.automacao.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import br.com.ezequias.automacao.pages.CartPage;
import br.com.ezequias.automacao.pages.CheckoutPage;
import br.com.ezequias.automacao.pages.HomePage;
import br.com.ezequias.automacao.pages.LoginPage;
import br.com.ezequias.automacao.pages.SearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckoutSteps {

    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();
    private final SearchPage searchPage = new SearchPage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();

    @Given("que o usuário possui um produto no carrinho")
    public void queOUsuarioPossuiUmProdutoNoCarrinho() {

        homePage.acessarHome();

        loginPage.acessarLogin();
        loginPage.realizarLogin(
                "teste2022@teste.com.br",
                "teste@"
        );

        searchPage.buscarProduto("14.1-inch Laptop");

        cartPage.adicionarAoCarrinho();
        cartPage.acessarCarrinho();
    }

    @When("aceita os termos de serviço")
    public void aceitaOsTermosDeServico() {
        checkoutPage.aceitarTermos();
    }

    @When("clica no botão checkout")
    public void clicaNoBotaoCheckout() {
        checkoutPage.clicarCheckout();
    }

    @Then("deve visualizar a página de checkout")
    public void deveVisualizarAPaginaDeCheckout() {
        assertTrue(
                checkoutPage.estaNaPaginaCheckout(),
                "Página de checkout não foi exibida"
        );
    }
}
