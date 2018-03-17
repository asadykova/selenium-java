package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.NoSuchElementException;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertTrue;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class RunMozilla {

    private WebDriver driver;
    private WebDriverWait wait;

    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    /*public boolean areElementsPresent(WebDriver driver, By locator) {
        return driver.findElements(locator).size() > 0;
    }*/

    @Before
    public void start() {

        FirefoxOptions options = new FirefoxOptions()
                .setLegacy(false);

        driver = new FirefoxDriver(options); /*init драйвера */
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void firstTest() {

        driver.get("http://localhost/litecart/admin");
        wait.until(titleIs("My Store"));
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        WebElement box = driver.findElement(By.cssSelector("#box-apps-menu"));
        List<WebElement> apps = box.findElements(By.cssSelector("li#app-"));

        int i, j;
        for (i = 0; i < apps.size(); i++)
        {
            box = driver.findElement(By.cssSelector("#box-apps-menu"));
            apps = box.findElements(By.cssSelector("li#app-"));
            apps.get(i).click();
            assertTrue(isElementPresent(By.cssSelector("h1")));
            box = driver.findElement(By.cssSelector("#box-apps-menu"));
            apps = box.findElements(By.cssSelector("li#app-"));
            List<WebElement> docs = box.findElements(By.cssSelector("li#app- [id^=doc]"));
            for (j = 0; j < docs.size(); j++)
            {
                box = driver.findElement(By.cssSelector("#box-apps-menu"));
                docs = box.findElements(By.cssSelector("li#app- [id^=doc]"));
                docs.get(j).click();
                assertTrue(isElementPresent(By.cssSelector("h1")));
            }
        }
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
