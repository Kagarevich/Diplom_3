package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ClassTest {
    private WebDriver driver;

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\feizu\\OneDrive\\Рабочий стол\\Яндекс.Практикум\\Diplom_Damir_Feyzulov_36\\Diplom_3\\src\\main\\resources\\yandexDriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:\\Users\\feizu\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        //WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);
        // Открой страницу тестового стенда
        driver.get("https://qa-mesto.praktikum-services.ru/");
    }

    @After
    public void teardown() {
        // Закрой браузер
        //driver.quit();
    }
}
