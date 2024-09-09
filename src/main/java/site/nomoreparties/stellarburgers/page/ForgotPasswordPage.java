package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordPage extends BasePageClass {

    private static final String URL = "https://stellarburgers.nomoreparties.site/forgot-password";

    private final By loginLink = By.xpath(".//a[text()='Войти']");

    //Тут пришлось изменить локатор, так как браузер firefox не реагирует на этой странице на лаудер как картинку
    private final By loadingDiv = By.xpath(".//div[@class='Modal_modal__P3_V5']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    public static String getUrl() {
        return URL;
    }

    @Step("Кликнуть на ссылку перехода на страницу логина")
    public LoginPage loginLinkClick() {
        driver.findElement(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Ожидание прорисовки страницы до нужного элемента - ссылки на страницу логина")
    public ForgotPasswordPage waitForLoad() {
        new WebDriverWait(this.driver, Duration.ofSeconds(60))
                .until(driver ->
                        !driver.findElement(loadingDiv).isDisplayed());
        return this;
    }


}
