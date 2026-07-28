package br.com.ezequias.automacao.factory;

import java.time.Duration;

import java.net.MalformedURLException;
import java.net.URL;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {

    /*
     * Cada thread terá sua própria instância do WebDriver.
     * Isso evita que cenários paralelos compartilhem o mesmo navegador.
     */
    private static final ThreadLocal<WebDriver> DRIVER = new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            DRIVER.set(criarDriver());
        }

        return DRIVER.get();
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

        boolean grid = Boolean.parseBoolean(
                System.getProperty("grid", "false")
        );

        String gridUrl = System.getProperty(
                "grid.url",
                "http://localhost:4444"
        );

        System.out.println(
                "Execução via Selenium Grid: " + grid

        );

        if (grid) {
            System.out.println(
                    "URL do Selenium Grid: " + gridUrl
            );
        }

        System.out.println(
                "Thread: " + Thread.currentThread().getName()
        );
        System.out.println(
                "Navegador selecionado: " + browser
        );
        System.out.println(
                "Execução headless: " + headless
        );
        System.out.println(
                "Ambiente CI: " + executandoNoCI
        );

        WebDriver navegador;

        if (grid) {
            navegador = criarRemoteDriver(
                    browser,
                    headless,
                    gridUrl
            );
        } else {
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

    private static ChromeOptions criarChromeOptions(boolean headless) {
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

        return options;
    }

    private static WebDriver criarChromeDriver(boolean headless) {
        return new ChromeDriver(
                criarChromeOptions(headless)
        );
    }

    private static EdgeOptions criarEdgeOptions(boolean headless) {
        EdgeOptions options = new EdgeOptions();

        options.setPageLoadStrategy(PageLoadStrategy.EAGER);

        if (headless) {
            options.addArguments("--headless=new");
        }

        options.addArguments(
                "--disable-dev-shm-usage",
                "--no-sandbox",
                "--disable-gpu",
                "--window-size=1920,1080"
        );

        return options;
    }

    private static WebDriver criarEdgeDriver(boolean headless) {
        return new EdgeDriver(
                criarEdgeOptions(headless)
        );
    }

    private static FirefoxOptions criarFirefoxOptions(boolean headless) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("-headless");
        }

        options.addArguments("--width=1920");
        options.addArguments("--height=1080");

        return options;
    }

    private static WebDriver criarFirefoxDriver(boolean headless) {
        return new FirefoxDriver(
                criarFirefoxOptions(headless)
        );
    }

    private static WebDriver criarRemoteDriver(
            String browser,
            boolean headless,
            String gridUrl
    ) {

        try {
            URL remoteUrl = new URL(gridUrl);

            switch (browser) {
                case "chrome":
                    ChromeOptions chromeOptions =
                            criarChromeOptions(headless);

                    return new RemoteWebDriver(
                            remoteUrl,
                            chromeOptions
                    );

                case "edge":
                    EdgeOptions edgeOptions =
                            criarEdgeOptions(headless);

                    return new RemoteWebDriver(
                            remoteUrl,
                            edgeOptions
                    );

                case "firefox":
                    FirefoxOptions firefoxOptions =
                            criarFirefoxOptions(headless);

                    return new RemoteWebDriver(
                            remoteUrl,
                            firefoxOptions
                    );

                default:
                    throw new IllegalArgumentException(
                            "Navegador não suportado no Grid: "
                                    + browser
                    );
            }

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(
                    "URL do Selenium Grid inválida: " + gridUrl,
                    e
            );
        }
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();

        if (driver != null) {
            try {
                driver.quit();
            } finally {
                /*
                 * Remove a referência da thread para evitar vazamento
                 * de memória e reutilização indevida do navegador.
                 */
                DRIVER.remove();


            }
        }
    }
}
