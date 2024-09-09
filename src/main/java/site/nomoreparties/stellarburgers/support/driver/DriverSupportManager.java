package site.nomoreparties.stellarburgers.support.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverSupportManager {

    @Step("Выбор браузера")
    public static WebDriver getDriver(String driverType) {
        switch (driverType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            default:
                throw new IllegalArgumentException("Wrong driver type");
        }
    }

    @Step("Открытие браузера")
    public static void open(WebDriver driver, String url) {
        driver.get(url);
    }

    @Step("Закрытие браузера")
    public static void close(WebDriver driver) {
        driver.quit();
    }
}
