package br.com.ezequias.automacao.pages;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.openqa.selenium.WebDriver;

import br.com.ezequias.automacao.factory.DriverFactory;

class HomePageTest {

    private WebDriver driver;
    private HomePage homePage;
    private MockedStatic<DriverFactory> driverFactoryMock;

    @BeforeEach
    void setUp() {
        driver = mock(WebDriver.class);

        driverFactoryMock = org.mockito.Mockito.mockStatic(DriverFactory.class);
        driverFactoryMock.when(DriverFactory::getDriver).thenReturn(driver);

        homePage = new HomePage();
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        driverFactoryMock.close();
    }

    @Test
    void deveAcessarAHome() {
        homePage.acessarHome();

        verify(driver).get("https://demowebshop.tricentis.com/");
    }
}
