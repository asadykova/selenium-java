package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import static org.junit.Assert.assertTrue;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class RunMozilla extends MainClass {

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

}
