package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {

    private final static String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    private final WebDriver driver;

    private final By loginLink = By.className("Auth_link__1fOlj");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на ссылку перехода на страницу логина")
    public LoginPage loginLinkClick() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }
}
