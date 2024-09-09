package site.nomoreparties.stellarburgers;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.page.ForgotPasswordPage;
import site.nomoreparties.stellarburgers.page.HomePage;
import site.nomoreparties.stellarburgers.page.RegisterPage;
import site.nomoreparties.stellarburgers.support.api.client.UserClient;
import site.nomoreparties.stellarburgers.support.driver.DriverSupportManager;
import site.nomoreparties.stellarburgers.support.model.User;
import site.nomoreparties.stellarburgers.support.model.generator.UserGenerator;

@RunWith(Parameterized.class)
@DisplayName("Тесты \"Вход \"")
public class LoginTest {

    private HomePage homePage;
    private User user;
    private WebDriver driver;

    private final String browser;

    public LoginTest(String browser) {
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
    }

    @Test
    @DisplayName("Тест \"вход по кнопке \"Войти в аккаунт\" на главной\"")
    public void successfulLoginFromHomePageLoginButtonTest() {
        DriverSupportManager.open(driver, HomePage.getUrl());
        homePage = new HomePage(driver);
        homePage
                .waitForLoad()
                .loginBtnClick()
                .waitForLoad()
                .fillLoginForm(user)
                .clickLoginBtn()
                .waitForLoad()
                .checkHomePage();
    }

    @Test
    @DisplayName("Тест \"вход через кнопку \"Личный кабинет\" на главной")
    public void successfulLoginFromHomePagePersonalAccountButtonTest() {
        DriverSupportManager.open(driver, HomePage.getUrl());
        homePage = new HomePage(driver);
        homePage
                .waitForLoad()
                .personalAccountLinkToLoginPageClick()
                .waitForLoad()
                .fillLoginForm(user)
                .clickLoginBtn()
                .waitForLoad()
                .checkHomePage();
    }

    @Test
    @DisplayName("Тест \"вход через кнопку в форме регистрации\"")
    public void successfulLoginFromRegisterPageTest() {
        DriverSupportManager.open(driver, RegisterPage.getUrl());
        RegisterPage registerPage = new RegisterPage(driver);
        registerPage
                .waitForLoad()
                .loginLinkClick()
                .waitForLoad()
                .fillLoginForm(user)
                .clickLoginBtn()
                .waitForLoad()
                .checkHomePage();
    }

    @Test
    @DisplayName("Тест \"вход через кнопку в форме восстановления пароля\"")
    public void successfulLoginFromForgotPasswordPageTest() {
        DriverSupportManager.open(driver, ForgotPasswordPage.getUrl());
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(driver);
        forgotPasswordPage
                .waitForLoad()
                .loginLinkClick()
                .waitForLoad()
                .fillLoginForm(user)
                .clickLoginBtn()
                .waitForLoad()
                .checkHomePage();
    }

    @After
    public void tearDown() {
        DriverSupportManager.close(driver);
        UserClient.delete(user);
    }
}
