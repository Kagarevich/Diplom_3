package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {

    private final static String URL = "https://stellarburgers.nomoreparties.site/account/profile";

    private final WebDriver driver;

    private final By logoutBtn = By.className("Account_button__14Yp3 text text_type_main-medium text_color_inactive");
    private final By constructorLink = By.xpath(".//p[text()='Конструктор']/parent::a");
    private final By logoLink = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Логаут пользователя и переход на страницу логина")
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
}
