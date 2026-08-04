package br.com.ezequias.automacao.hooks;

import br.com.ezequias.automacao.factory.DriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Hooks {

    private static final String TIPO_IMAGEM = "image/png";
    private static final String NOME_EVIDENCIA =
            "Evidência do cenário com falha";

    @Before
    public void setUp() {
        DriverFactory.getDriver();
    }

    @After
    public void after(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                anexarScreenshot(scenario);
            }
        } finally {
            DriverFactory.quitDriver();
        }
    }

    private void anexarScreenshot(Scenario scenario) {
        WebDriver driver = DriverFactory.getDriver();

        if (!(driver instanceof TakesScreenshot screenshotDriver)) {
            return;
        }

        byte[] screenshot = screenshotDriver.getScreenshotAs(
                OutputType.BYTES
        );

        scenario.attach(
                screenshot,
                TIPO_IMAGEM,
                NOME_EVIDENCIA
        );
    }
}