package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.page.HomePage;
import site.nomoreparties.stellarburgers.support.driver.DriverSupportManager;

@RunWith(Parameterized.class)
@DisplayName("Тесты \"Раздел \"Конструктор»\"")
public class ConstructorTransitionTest {

    private HomePage homePage;
    private WebDriver driver;

    private final String browser;

    public ConstructorTransitionTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][] {{"chrome"}, {"firefox"}};
    }

    @Before
    public void setUp() {
        driver = DriverSupportManager.getDriver(browser);
        DriverSupportManager.open(driver, HomePage.getUrl());
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Тест \"Соусы\"")
    public void saucesTest() {
        By saucesBtn = homePage.getSaucesBtn();
        homePage
                .waitForLoad()
                .btnClick(saucesBtn)
                .waitForBtnClick(saucesBtn)
                .checkConstructorBtnClick(saucesBtn);
    }

    @Test
    @DisplayName("Тест \"Булки\"")
    public void bunsTest() {
        By saucesBtn = homePage.getSaucesBtn();
        By bunsBtn = homePage.getBunsBtn();
        homePage
                .waitForLoad()
                .btnClick(saucesBtn)
                .btnClick(bunsBtn)
                .waitForBtnClick(bunsBtn)
                .checkConstructorBtnClick(bunsBtn);
    }

    @Test
    @DisplayName("Тест \"Начинки\"")
    public void toppingsTest() {
        By toppingsBtn = homePage.getToppingsBtn();
        homePage
                .waitForLoad()
                .btnClick(toppingsBtn)
                .waitForBtnClick(toppingsBtn)
                .checkConstructorBtnClick(toppingsBtn);
    }

    @After
    public void tearDown() {
        DriverSupportManager.close(driver);
    }
}
