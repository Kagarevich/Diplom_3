package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage {

    private static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    private final WebDriver driver;

    private final By loginLink = By.className("Auth_link__1fOlj");
    private final By loadingDiv = By.className("Modal_modal_opened__3ISw4 Modal_modal__P3_V5");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Кликнуть на ссылку перехода на страницу логина")
    public LoginPage loginLinkClick() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Ожидание прорисовки страницы до нужного элемента - ссылки на страницу логина")
    public ForgotPasswordPage waitForLoad() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                driver.findElement(loginLink).getText() != null
                        && !driver.findElement(loginLink).getText().isEmpty()
                        && driver.findElements(loadingDiv).isEmpty());
        return this;
    }
}
