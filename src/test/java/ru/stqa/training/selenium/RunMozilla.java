package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class RunMozilla {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {

        FirefoxOptions options = new FirefoxOptions()
                .setLegacy(false);

        driver = new FirefoxDriver(options); /*init драйвера */
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void firstTest() {

        driver.get("http://localhost/litecart/admin");
        wait.until(titleIs("My Store"));
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
