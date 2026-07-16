package br.com.ezequias.automacao.pages;

import org.openqa.selenium.By;

public class RegisterPage extends BasePage {

    private By linkRegister = By.className("ico-register");

    private By campoNome = By.id("FirstName");
    private By campoSobrenome = By.id("LastName");
    private By campoEmail = By.id("Email");
    private By campoSenha = By.id("Password");
    private By campoConfirmacaoSenha = By.id("ConfirmPassword");

    private By botaoRegistrar = By.id("register-button");

    private By mensagemSucesso =
            By.className ("result");

    public void acessarTelaCadastro() {
        driver.get("https://demowebshop.tricentis.com");
        clicar(linkRegister);
    }

    public void preencherCadastro(
            String nome,
            String sobrenome,
            String email,
            String senha) {

        escrever(campoNome, nome);
        escrever(campoSobrenome, sobrenome);
        escrever(campoEmail, email);
        escrever(campoSenha, senha);
        escrever(campoConfirmacaoSenha, senha);
    }

    public void clicarRegistrar() {
        clicar(botaoRegistrar);
    }

    public boolean cadastroRealizadoComSucesso() {
        return elementoEstaVisivel(mensagemSucesso);
    }



}
