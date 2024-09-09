package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage extends BasePageClass {

    private static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    private final By logoutBtn = By.xpath(".//button[text()='Выход']");
    private final By constructorLink = By.xpath(".//p[text()='Конструктор']/parent::a");
    private final By logoLink = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private final By loadingDiv = By.xpath(".//*[@class='Modal_modal__P3_V5']");

    public PersonalAccountPage(WebDriver driver) {
        super(driver);
    }

    @Step("Выход пользователя и переход на страницу логина")
    public LoginPage logout() {
        driver.findElement(logoutBtn).click();
        return new LoginPage(driver);
    }

    @Step("Переход на главную по клику на \"Конструктор\"")
    public HomePage constructorLinkClick() {
        driver.findElement(constructorLink).click();
        return new HomePage(driver);
    }

    @Step("Переход на главную по клику на логотип")
    public HomePage logoLinkClick() {
        driver.findElement(logoLink).click();
        return new HomePage(driver);
    }

    @Step("Ожидание прорисовки страницы до нужного элемента - кнопки выхода из аккаунта")
    public PersonalAccountPage waitForLoad() {
        driver.findElements(loadingDiv).forEach(element -> {
            new WebDriverWait(this.driver, Duration.ofSeconds(60))
                    .until(driver ->
                            !driver.findElement(loadingDiv).isDisplayed());
        });
        new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.elementToBeClickable(logoutBtn));
        return this;
    }

    @Step("Проверка перехода в личный кабинет")
    public void checkPersonalAccountPage() {
        Assert.assertEquals(
                "Переход в личный кабинет не произошёл",
                URL,
                driver.getCurrentUrl());
    }
}
