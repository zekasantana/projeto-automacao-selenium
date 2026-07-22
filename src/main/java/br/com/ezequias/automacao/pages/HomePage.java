package br.com.ezequias.automacao.pages;

public class HomePage extends BasePage {

    public void acessarHome() {
        driver.get("https://demowebshop.tricentis.com/");
    }

}