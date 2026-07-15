package br.com.ezequias.automacao.stepdefinitions;

import br.com.ezequias.automacao.pages.HomePage;
import br.com.ezequias.automacao.pages.LoginPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginSteps {

    private final HomePage homePage = new HomePage();
    private final LoginPage loginPage = new LoginPage();

    @Given("que o usuário está na página inicial")
    public void queOUsuarioEstaNaPaginaInicial() {
        homePage.acessarHome();
    }

    @When("acessa a tela de login")
    public void acessaATelaDeLogin() {
        loginPage.acessarLogin();
    }

    @When("informa um usuário e senha válidos")
    public void informaUmUsuarioESenhaValidos() {
        loginPage.realizarLogin(
                "teste2022@teste.com.br",
                "teste@"
        );
    }

    @Then("deve visualizar a página inicial autenticada")
    public void deveVisualizarPaginaInicialAutenticada() {
        Assertions.assertTrue(
                loginPage.loginRealizadoComSucesso(),
                "O link de logout não foi exibido após o login."
        );
    }

    @When("informa um usuário e senha inválidos")
    public void informoUmEmailESenhaInvalidos() {
        loginPage.realizarLogin(
                "usuario.inexistente@teste.com",
                "senhaIncorreta123"
        );
    }

    @Then("devo visualizar uma mensagem de erro")
    public void devoVisualizarUmaMensagemDeErro() {
        String mensagemErro = loginPage.obterMensagemErro();

        assertTrue(
                mensagemErro.contains("Login was unsuccessful"),
                "A mensagem de erro de login não foi exibida."
        );
    }

    @And("realizar logout")
    public void realizarLogout() {
        loginPage.realizarLogout();
    }

    @Then("deve visualizar a pagina de login")
    public void deveVisualizarAPaginaDeLogin() {
        Assertions.assertTrue(loginPage.estaNaPaginaLogin());
    }


}
