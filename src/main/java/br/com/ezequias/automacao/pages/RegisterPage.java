package br.com.ezequias.automacao.pages;

import org.openqa.selenium.By;

public class RegisterPage extends BasePage {

    private final By linkRegister =
            By.className("ico-register");

    private final By campoNome =
            By.id("FirstName");

    private final By campoSobrenome =
            By.id("LastName");

    private final By campoEmail =
            By.id("Email");

    private final By campoSenha =
            By.id("Password");

    private final By campoConfirmacaoSenha =
            By.id("ConfirmPassword");

    private final By botaoRegistrar =
            By.id("register-button");

    private final By mensagemSucesso =
            By.className("result");

    private final By mensagemEmailExistente =
            By.cssSelector(".message-error");

    private final By mensagemErroCampoObrigatorio =
            By.cssSelector(".field-validation-error");

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

    public String obterMensagemEmailExistente() {
        return obterTexto(mensagemEmailExistente);
    }

    public boolean mensagemCampoObrigatorioVisivel() {
        return elementoEstaVisivel(mensagemErroCampoObrigatorio);
    }
}