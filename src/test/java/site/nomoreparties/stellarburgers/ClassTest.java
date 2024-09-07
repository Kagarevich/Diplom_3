package site.nomoreparties.stellarburgers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ClassTest extends BaseClass {

    @Test
    public void test() {
        System.out.println();
    }

    @After
    public void teardown() {
        // Закрой браузер
        //driver.quit();
    }
}
