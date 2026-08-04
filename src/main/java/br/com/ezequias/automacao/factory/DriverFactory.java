package br.com.ezequias.automacao.factory;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Locale;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public final class DriverFactory {

    private static final Duration PAGE_LOAD_TIMEOUT =
            Duration.ofSeconds(30);

    private static final String BROWSER_PADRAO = "chrome";

    private static final String GRID_URL_PADRAO =
            "http://localhost:4444";

    /*
     * Cada thread possui sua própria instância do WebDriver.
     * Isso evita o compartilhamento do navegador entre cenários paralelos.
     */
    private static final ThreadLocal<WebDriver> DRIVER =
            new ThreadLocal<>();

    private DriverFactory() {
    }

    public static WebDriver getDriver() {
        if (DRIVER.get() == null) {
            DRIVER.set(criarDriver());
        }

        return DRIVER.get();
    }

    private static WebDriver criarDriver() {
        String browser = obterBrowser();
        boolean executandoNoCI = estaExecutandoNoCI();
        boolean headless = obterConfiguracaoHeadless(executandoNoCI);
        boolean grid = Boolean.parseBoolean(
                System.getProperty("grid", "false")
        );

        String gridUrl = System.getProperty(
                "grid.url",
                GRID_URL_PADRAO
        );

        exibirConfiguracoes(
                browser,
                headless,
                executandoNoCI,
                grid,
                gridUrl
        );

        WebDriver navegador;

        if (grid) {
            navegador = criarRemoteDriver(
                    browser,
                    headless,
                    gridUrl
            );
        } else {
            navegador = criarDriverLocal(browser, headless);
        }

        configurarNavegador(navegador, headless);

        return navegador;
    }

    private static String obterBrowser() {
        return System.getProperty("browser", BROWSER_PADRAO)
                .toLowerCase(Locale.ROOT)
                .trim();
    }

    private static boolean estaExecutandoNoCI() {
        return Boolean.parseBoolean(
                System.getenv().getOrDefault("CI", "false")
        );
    }

    private static boolean obterConfiguracaoHeadless(
            boolean executandoNoCI
    ) {
        return Boolean.parseBoolean(
                System.getProperty(
                        "headless",
                        String.valueOf(executandoNoCI)
                )
        );
    }

    private static WebDriver criarDriverLocal(
            String browser,
            boolean headless
    ) {
        return switch (browser) {
            case "chrome" -> criarChromeDriver(headless);
            case "edge" -> criarEdgeDriver(headless);
            case "firefox" -> criarFirefoxDriver(headless);
            default -> throw navegadorNaoSuportado(browser, false);
        };
    }

    private static void configurarNavegador(
            WebDriver navegador,
            boolean headless
    ) {
        navegador.manage()
                .timeouts()
                .implicitlyWait(Duration.ZERO);

        navegador.manage()
                .timeouts()
                .pageLoadTimeout(PAGE_LOAD_TIMEOUT);

        if (!headless) {
            navegador.manage().window().maximize();
        }
    }

    private static ChromeOptions criarChromeOptions(
            boolean headless
    ) {
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
        return new ChromeDriver(criarChromeOptions(headless));
    }

    private static EdgeOptions criarEdgeOptions(
            boolean headless
    ) {
        EdgeOptions options = new EdgeOptions();

        /*
         * Mantido para reduzir problemas de carregamento
         * do Edge em execução no CI.
         */
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
        return new EdgeDriver(criarEdgeOptions(headless));
    }

    private static FirefoxOptions criarFirefoxOptions(
            boolean headless
    ) {
        FirefoxOptions options = new FirefoxOptions();

        if (headless) {
            options.addArguments("-headless");
        }

        options.addArguments(
                "--width=1920",
                "--height=1080"
        );

        return options;
    }

    private static WebDriver criarFirefoxDriver(boolean headless) {
        return new FirefoxDriver(criarFirefoxOptions(headless));
    }

    private static WebDriver criarRemoteDriver(
            String browser,
            boolean headless,
            String gridUrl
    ) {
        try {
            URL remoteUrl = new URL(gridUrl);

            return switch (browser) {
                case "chrome" -> new RemoteWebDriver(
                        remoteUrl,
                        criarChromeOptions(headless)
                );

                case "edge" -> new RemoteWebDriver(
                        remoteUrl,
                        criarEdgeOptions(headless)
                );

                case "firefox" -> new RemoteWebDriver(
                        remoteUrl,
                        criarFirefoxOptions(headless)
                );

                default -> throw navegadorNaoSuportado(
                        browser,
                        true
                );
            };

        } catch (MalformedURLException e) {
            throw new IllegalArgumentException(
                    "URL do Selenium Grid inválida: " + gridUrl,
                    e
            );
        }
    }

    private static IllegalArgumentException navegadorNaoSuportado(
            String browser,
            boolean grid
    ) {
        String contexto = grid
                ? " no Selenium Grid"
                : "";

        return new IllegalArgumentException(
                "Navegador não suportado"
                        + contexto
                        + ": "
                        + browser
                        + ". Utilize chrome, edge ou firefox."
        );
    }

    private static void exibirConfiguracoes(
            String browser,
            boolean headless,
            boolean executandoNoCI,
            boolean grid,
            String gridUrl
    ) {
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
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER.get();

        if (driver == null) {
            return;
        }

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
