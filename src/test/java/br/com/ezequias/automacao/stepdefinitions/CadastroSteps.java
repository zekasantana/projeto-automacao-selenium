package br.com.ezequias.automacao.stepdefinitions;

import br.com.ezequias.automacao.pages.RegisterPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CadastroSteps {

    private final RegisterPage registerPage = new RegisterPage();

    private String emailGerado;

    @Given("que o usuário acessa a tela de cadastro")
    @Given("que estou na página de cadastro")
    public void acessarTelaCadastro() {
        registerPage.acessarTelaCadastro();
    }

    @When("preencher os dados válidos")
    public void preencherDadosValidos() {

        emailGerado =
                "teste" + System.currentTimeMillis() + "@email.com";

        registerPage.preencherCadastro(
                "Ezequias",
                "Santana",
                emailGerado,
                "123456"
        );
    }

    @When("clicar em registrar")
    @When("clico no botão Register")
    public void clicarEmRegistrar() {
        registerPage.clicarRegistrar();
    }

    @Then("o cadastro deve ser realizado com sucesso")
    public void validarCadastro() {
        assertTrue(
                registerPage.cadastroRealizadoComSucesso(),
                "O cadastro não foi realizado com sucesso."
        );
    }

    @When("informo os dados de um usuário já cadastrado")
    public void informoOsDadosDeUmUsuarioJaCadastrado() {

        registerPage.preencherCadastro(
                "Teste",
                "Usuario",
                "teste2022@teste.com.br",
                "123456"
        );
    }

    @Then("devo visualizar a mensagem de erro de e-mail já existente")
    public void devoVisualizarAMensagemDeErroDeEmailJaExistente() {

        String mensagemErro =
                registerPage.obterMensagemEmailExistente();

        assertTrue(
                mensagemErro.contains("already exists"),
                "Mensagem de e-mail já existente não encontrada. "
                        + "Mensagem exibida: " + mensagemErro
        );
    }
}
