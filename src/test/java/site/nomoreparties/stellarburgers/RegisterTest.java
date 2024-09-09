package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.page.RegisterPage;
import site.nomoreparties.stellarburgers.support.api.client.UserClient;
import site.nomoreparties.stellarburgers.support.driver.DriverSupportManager;
import site.nomoreparties.stellarburgers.support.model.User;
import site.nomoreparties.stellarburgers.support.model.generator.UserGenerator;

@RunWith(Parameterized.class)
@DisplayName("Тесты \"Регистрация\"")
public class RegisterTest {

    private RegisterPage registerPage;
    private User user;
    private WebDriver driver;

    private final String browser;

    public RegisterTest(String browser) {
        this.browser = browser;
    }

    @Parameterized.Parameters
    public static Object[][] getBrowser() {
        return new Object[][] {{"chrome"}, {"firefox"}};
    }

    @Before
    public void setUp() {
        driver = DriverSupportManager.getDriver(browser);
        DriverSupportManager.open(driver, RegisterPage.getUrl());
        registerPage = new RegisterPage(driver);
    }

    @Test
    @DisplayName("Тест успешной регистрации")
    public void successfulRegistrationTest() {
        user = UserGenerator.create();
        registerPage
                .waitForLoad()
                .fillRegisterForm(user)
                .registerBtnClick()
                .waitForLoad()
                .checkLoginPage();
        UserClient.delete(user);
    }

    @Test
    @DisplayName("Тест неуспешной регистрации. Некорректный пароль")
    @Description("Ошибка для некорректного пароля. Минимальный пароль — шесть символов")
    public void incorrectPasswordTest() {
        user = UserGenerator.createCustomPasswordLength(5);
        registerPage
                .waitForLoad()
                .fillRegisterForm(user)
                .errorRegisterBtnClick()
                .checkPasswordInputSendWrongKeysRegister();
    }

    @After
    public void tearDown() {
        DriverSupportManager.close(driver);
    }
}
