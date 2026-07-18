package br.com.ezequias.automacao.factory;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    private static WebDriver driver;

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = criarDriver();
        }

        return driver;
    }

    private static WebDriver criarDriver() {
        String browser = System.getProperty("browser", "chrome")
                .toLowerCase()
                .trim();

        boolean executandoNoCI = Boolean.parseBoolean(
                System.getenv().getOrDefault("CI", "false")
        );

        boolean headless = Boolean.parseBoolean(
                System.getProperty(
                        "headless",
                        String.valueOf(executandoNoCI)
                )
        );

        // LOGS TEMPORÁRIOS
        System.out.println("Navegador selecionado: " + browser);
        System.out.println("Execução headless: " + headless);
        System.out.println("Ambiente CI: " + executandoNoCI);

        WebDriver navegador;

        switch (browser) {
            case "chrome":
                navegador = criarChromeDriver(headless);
                break;

            case "edge":
                navegador = criarEdgeDriver(headless);
                break;

            case "firefox":
                navegador = criarFirefoxDriver(headless);
                break;

            default:
                throw new IllegalArgumentException(
                        "Navegador não suportado: " + browser
                                + ". Utilize chrome, edge ou firefox."
                );
        }

        navegador.manage()
                .timeouts()
                .implicitlyWait(Duration.ofSeconds(0));

        navegador.manage()
                .timeouts()
                .pageLoadTimeout(Duration.ofSeconds(30));

        if (!headless) {
            navegador.manage().window().maximize();
        }

        return navegador;
    }

    private static WebDriver criarChromeDriver(boolean headless) {
        ChromeOptions options = new ChromeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments(
                "--disable-dev-shm-usage",
                "--no-sandbox",
                "--disable-gpu",
                "--window-size=1920,1080"
        );

        return new ChromeDriver(options);
    }

    private static WebDriver criarEdgeDriver(boolean headless) {
        EdgeOptions options = new EdgeOptions();

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments(
                "--disable-dev-shm-usage",
                "--no-sandbox",
                "--disable-gpu",
                "--window-size=1920,1080"
        );

        return new EdgeDriver(options);
    }

    private static WebDriver criarFirefoxDriver(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("-headless");
        }

        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        return new FirefoxDriver(options);
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
