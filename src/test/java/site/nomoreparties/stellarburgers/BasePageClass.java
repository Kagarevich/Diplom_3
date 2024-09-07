package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasePageClass {

    //решил не дописывать options для драйвера, чтобы всё было наглядно
    protected WebDriver driver;

    private WebDriver getDriver(String driverType) {
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

    @Before
    public void startUp() {
        driver = getDriver("chrome");
        driver.get("https://qa-scooter.praktikum-services.ru/");
        //this.driver.get(HomePage.URL);
    }

    @After
    public void teardown() {
        driver.quit();
    }
}
