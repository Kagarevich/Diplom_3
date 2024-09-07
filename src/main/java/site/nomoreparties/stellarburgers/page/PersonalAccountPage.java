package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAccountPage {

    private static final String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    private final WebDriver driver;

    private final By logoutBtn = By.className("Account_button__14Yp3 text text_type_main-medium text_color_inactive");
    private final By constructorLink = By.xpath(".//p[text()='Конструктор']/parent::a");
    private final By logoLink = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");
    private final By loadingDiv = By.className("Modal_modal_opened__3ISw4 Modal_modal__P3_V5");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
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
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver ->
                driver.findElement(logoutBtn).getText() != null
                        && !driver.findElement(logoutBtn).getText().isEmpty()
                        && driver.findElements(loadingDiv).isEmpty());
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
