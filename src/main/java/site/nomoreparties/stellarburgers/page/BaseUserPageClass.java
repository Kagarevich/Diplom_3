package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BaseUserPageClass extends BasePageClass {

    private final By emailInput = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By passwordInput = By.xpath(".//input[@type='password']");

    public BaseUserPageClass(WebDriver driver) {
        super(driver);
    }

    @Step("Вводим email пользователя")
    public void emailInputSendKeys(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Вводим password пользователя")
    public void passwordInputSendKeys(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
}
