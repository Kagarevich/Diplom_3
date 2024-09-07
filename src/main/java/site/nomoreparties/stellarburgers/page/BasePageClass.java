package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePageClass {

    protected final WebDriver driver;

    private final By emailInput = By.xpath(".//label[text()='Email']/parent::div/input");
    private final By passwordInput = By.xpath(".//input[@type='password']");

    public BasePageClass(WebDriver driver) {
        this.driver = driver;
    }

    ;

    @Step("Вводим email пользователя")
    public void emailInputSendKeys(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }

    @Step("Вводим password пользователя")
    public void passwordInputSendKeys(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
}
