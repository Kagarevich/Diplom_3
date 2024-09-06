package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class BaseClass extends BaseTest {

    //решил не дописывать options для драйвера, чтобы всё было наглядно
    protected WebDriver driver;

    @Before
    public void startUp() {
        getDriver("yandex");
        WebDriverManager.chromedriver().setup();
        this.driver = new ChromeDriver();
        this.driver.get("https://qa-scooter.praktikum-services.ru/");
        //this.driver.get(HomePage.URL);
    }

    @After
    public void teardown() {
        this.driver.quit();
    }
}
