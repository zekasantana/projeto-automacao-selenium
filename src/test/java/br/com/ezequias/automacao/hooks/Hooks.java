package br.com.ezequias.automacao.hooks;

import br.com.ezequias.automacao.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
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
        if (scenario.isFailed()) {

            File screenshot =
                    ((TakesScreenshot) DriverFactory.getDriver())
                            .getScreenshotAs(OutputType.FILE);
            try {

                FileUtils.copyFile(
                        screenshot,
                        new File(
                                "evidencias/"
                                        + scenario.getName()
                                        .replace(" ", "_")
                                        + ".png"
                        )
                );

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        DriverFactory.quitDriver();
    }
}
