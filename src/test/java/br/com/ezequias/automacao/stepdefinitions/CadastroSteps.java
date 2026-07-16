package br.com.ezequias.automacao.stepdefinitions;

import br.com.ezequias.automacao.pages.RegisterPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class CadastroSteps {

    private final RegisterPage registerPage = new RegisterPage();

        RegisterPage register = new RegisterPage();

        String emailGerado;

        @Given("que o usuário acessa a tela de cadastro")
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
        public void clicarEmRegistrar() {

            registerPage.clicarRegistrar();

        }

        @Then("o cadastro deve ser realizado com sucesso")
        public void ValidarCadastro() {

            assertTrue(registerPage.cadastroRealizadoComSucesso());

        }

}
