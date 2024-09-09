package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePageClass {

    private static final String URL = "https://stellarburgers.nomoreparties.site/";
    private static final String SELECTED_BUTTON_CLASS =
            "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect";

    private final By personalAccountLink = By.xpath(".//p[text()='Личный Кабинет']/parent::a");
    private final By loginBtn = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By bunsBtn = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesBtn = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By toppingsBtn = By.xpath(".//span[text()='Начинки']/parent::div");
    private final By loadingDiv = By.xpath(".//div[@class='Modal_modal__P3_V5']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public static String getUrl() {
        return URL;
    }

    public By getBunsBtn() {
        return bunsBtn;
    }

    public By getSaucesBtn() {
        return saucesBtn;
    }

    public By getToppingsBtn() {
        return toppingsBtn;
    }

    @Step("Нажатие кнопки")
    public HomePage btnClick(By btn) {
        driver.findElement(btn).click();
        return this;
    }

    @Step("Проверка перехода к разделу конструктора, если он ещё не выбран")
    public void checkConstructorBtnClick(By btn) {
        Assert.assertEquals(
                "Раздел не выбран",
                SELECTED_BUTTON_CLASS,
                driver.findElement(btn).getAttribute("class"));
    }

    @Step("Ожидание выбора нужного раздела")
    public HomePage waitForBtnClick(By btn) {
        new WebDriverWait(this.driver, Duration.ofSeconds(60))
                .until(driver ->
                        driver.findElement(btn)
                                .getAttribute("class")
                                .equals(SELECTED_BUTTON_CLASS));
        return this;
    }

    @Step("Ожидание прорисовки страницы до нужного элемента - до последнего видимого раздела")
    public HomePage waitForLoad() {
        new WebDriverWait(this.driver, Duration.ofSeconds(60))
                .until(driver ->
                        !driver.findElement(loadingDiv).isDisplayed());
        return this;
    }

    @Step("Переход на страницу личного кабинета")
    public PersonalAccountPage personalAccountLinkToPersonalAccountPageClick() {
        driver.findElement(personalAccountLink).click();
        return new PersonalAccountPage(driver);
    }

    @Step("Переход на страницу логина при попытке попасть в личный кабинет")
    public LoginPage personalAccountLinkToLoginPageClick() {
        driver.findElement(personalAccountLink).click();
        return new LoginPage(driver);
    }

    @Step("Переход на страницу логина")
    public LoginPage loginBtnClick() {
        driver.findElement(loginBtn).click();
        return new LoginPage(driver);
    }

    @Step("Проверка перехода на главную")
    public void checkHomePage() {
        Assert.assertEquals(
                "Переход на главную не произошёл",
                URL,
                driver.getCurrentUrl());
    }
}
