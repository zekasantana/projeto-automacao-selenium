package br.com.ezequias.automacao.factory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

class DriverFactoryTest {

    @AfterEach
    void limparConfiguracoes() {
        System.clearProperty("browser");
        System.clearProperty("headless");
        System.clearProperty("grid");
        System.clearProperty("grid.url");

        DriverFactory.quitDriver();
    }

    @Test
    void deveUtilizarChromeComoNavegadorPadrao() {
        String navegador = invocarMetodoPrivado(
                "obterBrowser",
                new Class<?>[0]
        );

        assertEquals("chrome", navegador);
    }

    @Test
    void deveNormalizarNomeDoNavegador() {
        System.setProperty("browser", "  FIREFOX  ");

        String navegador = invocarMetodoPrivado(
                "obterBrowser",
                new Class<?>[0]
        );

        assertEquals("firefox", navegador);
    }

    @Test
    void deveAtivarHeadlessQuandoConfigurado() {
        System.setProperty("headless", "true");

        boolean headless = invocarMetodoPrivado(
                "obterConfiguracaoHeadless",
                new Class<?>[]{boolean.class},
                false
        );

        assertTrue(headless);
    }

    @Test
    void deveDesativarHeadlessQuandoConfigurado() {
        System.setProperty("headless", "false");

        boolean headless = invocarMetodoPrivado(
                "obterConfiguracaoHeadless",
                new Class<?>[]{boolean.class},
                true
        );

        assertFalse(headless);
    }

    @Test
    void deveUtilizarConfiguracaoDoCIQuandoHeadlessNaoForInformado() {
        boolean headless = invocarMetodoPrivado(
                "obterConfiguracaoHeadless",
                new Class<?>[]{boolean.class},
                true
        );

        assertTrue(headless);
    }

    @Test
    void deveCriarChromeOptionsEmModoHeadless() {
        ChromeOptions options = invocarMetodoPrivado(
                "criarChromeOptions",
                new Class<?>[]{boolean.class},
                true
        );

        List<String> argumentos = obterArgumentos(
                options,
                "goog:chromeOptions"
        );

        assertTrue(argumentos.contains("--headless=new"));
        assertTrue(argumentos.contains("--disable-dev-shm-usage"));
        assertTrue(argumentos.contains("--no-sandbox"));
        assertTrue(argumentos.contains("--disable-gpu"));
        assertTrue(argumentos.contains("--window-size=1920,1080"));
    }

    @Test
    void naoDeveAdicionarHeadlessAoChromeQuandoDesativado() {
        ChromeOptions options = invocarMetodoPrivado(
                "criarChromeOptions",
                new Class<?>[]{boolean.class},
                false
        );

        List<String> argumentos = obterArgumentos(
                options,
                "goog:chromeOptions"

        );

        assertFalse(argumentos.contains("--headless=new"));

    }

    @Test
    void deveCriarEdgeOptionsComPageLoadStrategyNormal() {
        EdgeOptions options = invocarMetodoPrivado(
                "criarEdgeOptions",
                new Class<?>[]{boolean.class},
                true
        );

        Capabilities capabilities = options;

        assertEquals(
                PageLoadStrategy.NORMAL,
                capabilities.getCapability("pageLoadStrategy")
        );

        List<String> argumentos = obterArgumentos(
                options,
                "ms:edgeOptions"

        );

         assertTrue(argumentos.contains("--headless=new"));

    }

    @Test
    void deveCriarFirefoxOptionsEmModoHeadless() {
        FirefoxOptions options = invocarMetodoPrivado(
                "criarFirefoxOptions",
                new Class<?>[]{boolean.class},
                true
        );

        List<String> argumentos = obterArgumentos(
                options,
                "moz:firefoxOptions"

        );

        assertTrue(argumentos.contains("-headless"));
        assertTrue(argumentos.contains("--width=1920"));
        assertTrue(argumentos.contains("--height=1080"));
    }

    @Test
    void deveInformarErroParaNavegadorLocalNaoSuportado() {
        IllegalArgumentException excecao =
                invocarMetodoPrivado(
                        "navegadorNaoSuportado",
                        new Class<?>[]{
                                String.class,
                                boolean.class
                        },
                        "safari",
                        false
                );

        assertEquals(
                "Navegador não suportado: safari. "
                        + "Utilize chrome, edge ou firefox.",
                excecao.getMessage()
        );
    }

    @Test
    void deveInformarErroParaNavegadorGridNaoSuportado() {
        IllegalArgumentException excecao =
                invocarMetodoPrivado(
                        "navegadorNaoSuportado",
                        new Class<?>[]{
                                String.class,
                                boolean.class
                        },
                        "safari",
                        true
                );

        assertEquals(
                "Navegador não suportado no Selenium Grid: safari. "
                        + "Utilize chrome, edge ou firefox.",
                excecao.getMessage()
        );
    }

    @Test
    void deveRejeitarUrlInvalidaDoSeleniumGrid() {
        IllegalArgumentException excecao =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> invocarMetodoPrivado(
                                "criarRemoteDriver",
                                new Class<?>[]{
                                        String.class,
                                        boolean.class,
                                        String.class
                                },
                                "chrome",
                                true,
                                "url-invalida"
                        )
                );

        assertTrue(
                excecao.getMessage().contains(
                        "URL do Selenium Grid inválida"
                )
        );
    }

    @Test
    void naoDeveFalharAoEncerrarDriverInexistente() {
        assertDoesNotThrow(DriverFactory::quitDriver);
    }

    @SuppressWarnings("unchecked")
    private static List<String> obterArgumentos(
            Capabilities capabilities,
            String nomeCapability
    ) {
        Object configuracao =
                capabilities.getCapability(nomeCapability);

        if (!(configuracao instanceof Map<?, ?> mapa)) {
            return Collections.emptyList();
        }

        Object argumentos = mapa.get("args");

        if (!(argumentos instanceof List<?> lista)) {
            return Collections.emptyList();
        }

        return (List<String>) lista;
    }

    @SuppressWarnings("unchecked")
    private static <T> T invocarMetodoPrivado(
            String nomeMetodo,
            Class<?>[] tiposParametros,
            Object... argumentos
    ) {
        try {
            Method metodo = DriverFactory.class.getDeclaredMethod(
                    nomeMetodo,
                    tiposParametros
            );

            metodo.setAccessible(true);

            return (T) metodo.invoke(null, argumentos);

        } catch (InvocationTargetException e) {
            Throwable causa = e.getCause();

            if (causa instanceof RuntimeException runtimeException) {
                throw runtimeException;
            }

            throw new IllegalStateException(
                    "Erro ao executar o método: " + nomeMetodo,
                    causa
            );

        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException(
                    "Não foi possível acessar o método: "
                            + nomeMetodo,
                    e
            );
        }
    }
}
