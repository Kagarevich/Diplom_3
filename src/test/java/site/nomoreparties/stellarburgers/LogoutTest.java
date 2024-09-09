package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
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
@DisplayName("Тесты \"Выход из аккаунта\"")
public class LogoutTest {

    private HomePage homePage;
    private User user;
    private WebDriver driver;
    private String accessToken;
    private String refreshToken;

    private final String browser;

    public LogoutTest(String browser) {
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
    @DisplayName("Тест \"Проверь выход по кнопке \"Выйти\" в личном кабинете\"")
    @Description("Сам переход из главной в личный кабинет проверяется в тесте TransitionTest" +
            "Причина, по которой так сделал описал в README.md")
    public void logoutTest() {
        DriverSupportManager.open(driver, HomePage.getUrl());
        homePage = new HomePage(driver);
        homePage.setLocalStorage("accessToken", accessToken);
        homePage.setLocalStorage("refreshToken", refreshToken);
        homePage.refresh();
        homePage
                .waitForLoad()
                .personalAccountLinkToPersonalAccountPageClick()
                .waitForLoad()
                .logout()
                .waitForLoad()
                .checkLoginPage();
    }

    @After
    public void tearDown() {
        DriverSupportManager.close(driver);
        UserClient.delete(user);
    }
}
