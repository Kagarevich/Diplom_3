package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.support.model.User;

import java.time.Duration;

public class LoginPage extends BaseUserPageClass {

    private static final String URL = "https://stellarburgers.nomoreparties.site/login";

    private final By loginBtn = By.xpath(".//button[text()='Войти']");
    private final By loadingDiv = By.xpath(".//div[@class='Modal_modal__P3_V5']");

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
        new WebDriverWait(this.driver, Duration.ofSeconds(60))
                .until(driver ->
                        !driver.findElement(loadingDiv).isDisplayed());
        //иногда страница не успевает прогрузиться на тестах регистрации, поэтому добавил это
        new WebDriverWait(this.driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.elementToBeClickable(loginBtn));
        return this;
    }

    @Step("Проверка перехода на страницу логина")
    public void checkLoginPage() {
        Assert.assertEquals("Переход на страницу логина не произошёл", URL, driver.getCurrentUrl());
    }
}
