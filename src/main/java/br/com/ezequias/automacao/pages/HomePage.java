package br.com.ezequias.automacao.pages;

import org.openqa.selenium.By;

public class HomePage extends BasePage {

    private final By loginMenu = By.linkText("Log in");

    public void acessarHome() {
        driver.get("https://demowebshop.tricentis.com/");
    }


}