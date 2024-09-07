package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.support.model.User;

import java.time.Duration;

public class LoginPage extends BasePageClass {

    private static final String URL = "https://stellarburgers.nomoreparties.site/login";

    private final By loginBtn = By.className("button_button__33qZ0 button_button_type_primary__1O7Bx " +
            "button_button_size_medium__3zxIa");
    private final By loadingDiv = By.className("Modal_modal_opened__3ISw4 Modal_modal__P3_V5");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Заполнить поля формы логина")
    public LoginPage fillLoginForm(User user) {
        emailInputSendKeys(user.getEmail());
        passwordInputSendKeys(user.getPassword());
        return this;
    }

    @Step("Нажать на кнопку \"Войти\"")
    public HomePage clickLoginBtn() {
        driver.findElement(loginBtn).click();
        return new HomePage(driver);
    }

    @Step("Ожидание прорисовки страницы до нужного элемента - кнопки войти")
    public LoginPage waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                driver.findElement(loginBtn).getText() != null
                        && !driver.findElement(loginBtn).getText().isEmpty()
                        && driver.findElements(loadingDiv).isEmpty());
        return this;
    }

    @Step("Проверка перехода на страницу логина")
    public void checkLoginPage() {
        Assert.assertEquals("Переход на страницу логина не произошёл", URL, driver.getCurrentUrl());
    }
}
