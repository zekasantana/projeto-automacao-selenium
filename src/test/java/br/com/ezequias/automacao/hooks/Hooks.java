package br.com.ezequias.automacao.hooks;

import br.com.ezequias.automacao.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class Hooks {

    @Before
    public void setUp() {
        DriverFactory.getDriver();
    }

    @After
    public void after(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                File screenshot =
                        ((TakesScreenshot) DriverFactory.getDriver())
                                .getScreenshotAs(OutputType.FILE);

                FileUtils.copyFile(
                        screenshot,
                        new File(
                                "evidencias/"
                                        + scenario.getName()
                                        .replaceAll("[^a-zA-Z0-9-]", "")
                                        + ".png"
                        )
                );
            }
        } catch (IOException e) {
            System.err.println(
                    "Não foi possível salvar a evidência do cenário: "
                            + scenario.getName()
            );
            e.printStackTrace();
        } finally {
            DriverFactory.quitDriver();

        }
    }
}
