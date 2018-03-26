package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class MainClass {

    public WebDriver driver;
    public WebDriverWait wait;


    @Before
    public void start() {

        /*FirefoxOptions options = new FirefoxOptions()
                .setLegacy(false);

        driver = new FirefoxDriver(options); /*init драйвера */
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @After
    public void stop() {
        /*driver.quit();
        driver = null;*/
    }
}
