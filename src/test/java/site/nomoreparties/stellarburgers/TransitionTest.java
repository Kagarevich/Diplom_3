package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.page.HomePage;
import site.nomoreparties.stellarburgers.support.api.client.UserClient;
import site.nomoreparties.stellarburgers.support.driver.DriverSupportManager;
import site.nomoreparties.stellarburgers.support.model.AuthUser;
import site.nomoreparties.stellarburgers.support.model.User;
import site.nomoreparties.stellarburgers.support.model.generator.UserGenerator;

@RunWith(Parameterized.class)
@DisplayName("Тесты \"Переход из личного кабинета в конструктор\" и \"Переход в личный кабинет\"")
public class TransitionTest {

    private HomePage homePage;
    private User user;
    private WebDriver driver;
    private String accessToken;
    private String refreshToken;

    private final String browser;

    public TransitionTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][] {{"chrome"}, {"firefox"}};
    }

    @Before
    public void setUp() {
        driver = DriverSupportManager.getDriver(browser);
        user = UserGenerator.create();
        UserClient.register(user);
        accessToken = UserClient.login(user).as(AuthUser.class).getAccessToken();
        refreshToken = UserClient.login(user).as(AuthUser.class).getRefreshToken();
    }

    @Test
    @DisplayName("Тест \"Переход в личный кабинет\" с главной")
    public void transitionToPersonalAccountTest() {
        DriverSupportManager.open(driver, HomePage.getUrl());
        homePage = new HomePage(driver);
        homePage.setLocalStorage("accessToken", accessToken);
        homePage.setLocalStorage("refreshToken", refreshToken);
        homePage.refresh();
        homePage
                .waitForLoad()
                .personalAccountLinkToPersonalAccountPageClick()
                .waitForLoad()
                .checkPersonalAccountPage();
    }

    @Test
    @DisplayName("Тест \"Переход из личного кабинета в конструктор\" через кнопку")
    public void transitionToConstructorWithButtonTest() {
        DriverSupportManager.open(driver, HomePage.getUrl());
        homePage = new HomePage(driver);
        homePage.setLocalStorage("accessToken", accessToken);
        homePage.setLocalStorage("refreshToken", refreshToken);
        homePage.refresh();
        homePage
                .waitForLoad()
                .personalAccountLinkToPersonalAccountPageClick()
                .waitForLoad()
                .constructorLinkClick()
                .checkHomePage();
    }

    @Test
    @DisplayName("Тест \"Переход из личного кабинета в конструктор\" через логотип")
    public void transitionToConstructorWithLogoTest() {
        DriverSupportManager.open(driver, HomePage.getUrl());
        homePage = new HomePage(driver);
        homePage.setLocalStorage("accessToken", accessToken);
        homePage.setLocalStorage("refreshToken", refreshToken);
        homePage.refresh();
        homePage
                .waitForLoad()
                .personalAccountLinkToPersonalAccountPageClick()
                .waitForLoad()
                .logoLinkClick()
                .checkHomePage();
    }

    @After
    public void tearDown() {
        DriverSupportManager.close(driver);
        UserClient.delete(user);
    }
}
