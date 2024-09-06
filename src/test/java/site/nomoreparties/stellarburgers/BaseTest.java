package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseTest {

    protected WebDriver getDriver(String driverType) {
        switch (driverType) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "yandex":
                WebDriverManager.firefoxdriver().setup();
                return new ChromeDriver();
            default:
                throw new IllegalArgumentException("Wrong driver type");
        }
    }
}
