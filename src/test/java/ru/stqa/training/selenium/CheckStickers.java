package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckStickers {

    private WebDriver driver;
    private WebDriverWait wait;


    @Before
    public void start() {

        FirefoxOptions options = new FirefoxOptions()
                .setLegacy(false);

        driver = new FirefoxDriver(options); /*init драйвера */
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void checkSticker() {

        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));

        List<WebElement> product = driver.findElements(By.cssSelector(".product"));

        int j;
        for (j = 0; j < product.size(); j++)
        {
            product = driver.findElements(By.cssSelector(".product"));
            assertTrue(product.get(j).findElements(By.cssSelector(".sticker")).size() == 1);
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
