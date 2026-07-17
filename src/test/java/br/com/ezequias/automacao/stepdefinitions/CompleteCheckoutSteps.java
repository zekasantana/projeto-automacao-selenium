package br.com.ezequias.automacao.stepdefinitions;

import br.com.ezequias.automacao.pages.CartPage;
import br.com.ezequias.automacao.pages.CheckoutPage;
import br.com.ezequias.automacao.pages.CompleteCheckoutPage;
import br.com.ezequias.automacao.pages.LoginPage;
import br.com.ezequias.automacao.pages.SearchPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CompleteCheckoutSteps {

    private final LoginPage loginPage = new LoginPage();
    private final SearchPage searchPage = new SearchPage();
    private final CartPage cartPage = new CartPage();
    private final CheckoutPage checkoutPage = new CheckoutPage();
    private final CompleteCheckoutPage completeCheckoutPage =
            new CompleteCheckoutPage();

    @Given("que o usuário está na página de checkout com um produto")
    public void usuarioEstaNaPaginaCheckoutComProduto() {

        loginPage.acessarLogin();
        loginPage.realizarLogin(
                "teste2022@teste.com.br",
                "teste@"
        );

        assertTrue(
                loginPage.loginRealizadoComSucesso(),
                "O login não foi realizado com sucesso."
        );

        searchPage.buscarProduto("14.1-inch Laptop");

        assertTrue(
                searchPage.produtoEncontrado(),
                "O produto não foi encontrado."
        );

        cartPage.adicionarAoCarrinho();
        cartPage.acessarCarrinho();

        assertTrue(
                cartPage.produtoEstaNoCarrinho(),
                "O produto não foi adicionado ao carrinho."
        );

        checkoutPage.aceitarTermos();
        checkoutPage.clicarCheckout();

        assertTrue(
                checkoutPage.estaNaPaginaCheckout(),
                "A página de checkout não foi exibida."
        );
    }

    @When("preenche os dados obrigatórios de cobrança")
    public void preencheDadosObrigatoriosCobranca() {
        completeCheckoutPage.preencherDadosCobranca();
    }

    @And("seleciona o endereço de entrega")
    public void selecionaEnderecoEntrega() {
        completeCheckoutPage.continuarEnderecoEntrega();
    }

    @And("seleciona o método de entrega")
    public void selecionaMetodoEntrega() {
        completeCheckoutPage.selecionarMetodoEntrega();
    }

    @And("seleciona o método de pagamento")
    public void selecionaMetodoPagamento() {
        completeCheckoutPage.selecionarMetodoPagamento();
    }

    @And("confirma as informações de pagamento")
    public void confirmaInformacoesPagamento() {
        completeCheckoutPage.continuarInformacoesPagamento();
    }

    @And("confirma o pedido")
    public void confirmaPedido() {
        completeCheckoutPage.confirmarPedido();
    }

    @Then("deve visualizar a mensagem de pedido realizado com sucesso")
    public void deveVisualizarMensagemPedidoRealizadoComSucesso() {
        assertTrue(
                completeCheckoutPage.pedidoRealizadoComSucesso(),
                "A mensagem de pedido realizado com sucesso não foi exibida."
        );
    }
}
