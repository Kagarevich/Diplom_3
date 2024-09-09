package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.support.model.User;

import java.time.Duration;

public class RegisterPage extends BaseUserPageClass {

    private static final String URL = "https://stellarburgers.nomoreparties.site/register";
    private static final String ERROR_MESSAGE = "Некорректный пароль";

    private final By loginLink = By.className("Auth_link__1fOlj");
    private final By registerBtn = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By nameInput = By.xpath(".//label[text()='Имя']/parent::div/input");
    private final By loadingDiv = By.xpath(".//div[@class='Modal_modal__P3_V5']");
    private final By errorMessage = By.xpath(".//p[text()='Некорректный пароль']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public static String getUrl() {
        return URL;
    }

    @Step("Ввести имя пользователя")
    public void nameInputSendKeys(String name) {
        this.driver.findElement(nameInput).sendKeys(name);
    }

    @Step("Заполнить поля формы регистрации")
    public RegisterPage fillRegisterForm(User user) {
        nameInputSendKeys(user.getName());
        emailInputSendKeys(user.getEmail());
        passwordInputSendKeys(user.getPassword());
        return this;
    }

    @Step("Нажать кнопку \"Зарегистрироваться\"")
    public RegisterPage errorRegisterBtnClick() {
        this.driver.findElement(registerBtn).click();
        return this;
    }

    @Step("Нажать кнопку \"Зарегистрироваться\" с ошибкой")
    public LoginPage registerBtnClick() {
        this.driver.findElement(registerBtn).click();
        return new LoginPage(driver);
    }

    @Step("Кликнуть на ссылку перехода на страницу логина")
    public LoginPage loginLinkClick() {
        this.driver.findElement(loginLink).click();
        return new LoginPage(this.driver);
    }

    @Step("Ожидание прорисовки страницы до нужного элемента - ссылки на страницу логина")
    public RegisterPage waitForLoad() {
        new WebDriverWait(this.driver, Duration.ofSeconds(60))
                .until(driver ->
                        !driver.findElement(loadingDiv).isDisplayed());
        return this;
    }

    @Step("Проверка ввода некорректного пароля")
    public void checkPasswordInputSendWrongKeysRegister() {
        Assert.assertEquals(
                "Некорректное поведения при некорректном пароле",
                this.driver.findElement(errorMessage).getText(),
                ERROR_MESSAGE);
    }
}
