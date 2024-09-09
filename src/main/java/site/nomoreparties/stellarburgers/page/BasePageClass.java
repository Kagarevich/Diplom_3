package site.nomoreparties.stellarburgers.page;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.html5.LocalStorage;
import org.openqa.selenium.html5.WebStorage;

public class BasePageClass {

    protected final WebDriver driver;

    public BasePageClass(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Предзаполнение local storage")
    public void setLocalStorage(String key, String value) {
        LocalStorage local = ((WebStorage) driver).getLocalStorage();
        local.setItem(key, value);
    }

    @Step("Обновление страницы")
    public void refresh() {
        driver.navigate().refresh();
    }
}
