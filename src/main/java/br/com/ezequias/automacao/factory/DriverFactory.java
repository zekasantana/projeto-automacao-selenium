package br.com.ezequias.automacao.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

    private static WebDriver driver;

    public static WebDriver getDriver() {

        if (driver == null) {

            ChromeOptions options = new ChromeOptions();

            if (estaExecutandoNoCI()) {
                options.addArguments(
                        "--headless=new",
                        "--no-sandbox",
                        "--disable-dev-shm-usage",
                        "--window-size=1920,1080"
                );
            }

            driver = new ChromeDriver(options);

            if (!estaExecutandoNoCI()) {
                driver.manage().window().maximize();
            }
        }

        return driver;
    }

    private static boolean estaExecutandoNoCI() {
        return "true".equalsIgnoreCase(System.getenv("CI"));
    }

    public static void quitDriver() {

        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
