package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.support.model.User;

import java.time.Duration;

public class RegisterPage extends BasePageClass {

    private static final String URL = "https://stellarburgers.nomoreparties.site/register";
    private static final String ERROR_MESSAGE = "Некорректный пароль";

    private final By loginLink = By.className("Auth_link__1fOlj");
    private final By registerBtn = By.className("button_button__33qZ0 button_button_type_primary__1O7Bx " +
            "button_button_size_medium__3zxIa");
    private final By nameInput = By.xpath(".//label[text()='Имя']/parent::div/input");
    private final By loadingDiv = By.className("Modal_modal_opened__3ISw4 Modal_modal__P3_V5");
    private final By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввести имя пользователя")
    public void nameInputSendKeys(String name) {
        driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Заполнить поля формы регистрации")
    public RegisterPage fillRegisterForm(User user) {
        nameInputSendKeys(user.getName());
        emailInputSendKeys(user.getEmail());
        passwordInputSendKeys(user.getPassword());
        return this;
    }

    @Step("Нажать кнопку \"Зарегистрироваться\"")
    public LoginPage registerBtnClick() {
        driver.findElement(registerBtn).click();
        return new LoginPage(driver);
    }

    @Step("Кликнуть на ссылку перехода на страницу логина")
    public LoginPage loginLinkClick() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Ожидание прорисовки страницы до нужного элемента - ссылки на страницу логина")
    public RegisterPage waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                driver.findElement(loginLink).getText() != null
                        && !driver.findElement(loginLink).getText().isEmpty()
                        && driver.findElements(loadingDiv).isEmpty());
        return this;
    }

    @Step("Проверка ввода некорректного пароля")
    public void checkPasswordInputSendWrongKeysRegister(String password) {
        passwordInputSendKeys(password);
        Assert.assertEquals(
                "Некорректное поведения при ошибке",
                driver.findElement(errorMessage).getText(),
                ERROR_MESSAGE);
    }
}
