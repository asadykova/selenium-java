package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.logging.LogEntry;

import java.util.ArrayList;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckLog extends MainClass {

    @Test
    public void checkLog() {

        driver.get("http://localhost/litecart/admin");
        wait.until(titleIs("My Store"));
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> catalog = driver.findElements(By.cssSelector("#app- span.name"));
        int i;
        for (i = 0; i < catalog.size(); i++) {
            catalog = driver.findElements(By.cssSelector("#app- span.name"));

            //идем на страницу Catalog
            if (catalog.get(i).getAttribute("textContent").equals("Catalog")) {
                catalog.get(i).click();
            }
            else {
                continue;
            }
        }

        System.out.print(driver.manage().logs().getAvailableLogTypes());

        driver.findElements(By.cssSelector(".dataTable tr.row")).get(1).findElements(By.tagName("td")).get(2).findElement(By.tagName("a")).click();

        List<WebElement> ducks = driver.findElements(By.cssSelector(".dataTable tr.row"));
        for (i = 3; i < ducks.size(); i++) {
            ducks = driver.findElements(By.cssSelector(".dataTable tr.row"));
            ducks.get(i).findElements(By.tagName("td")).get(2).findElement(By.tagName("a")).click();

            for (LogEntry l : driver.manage().logs().get("browser").getAll()) {
                List<String> logList = new ArrayList<String>();
                logList.add(l.getMessage());
                Assert.assertTrue(logList.isEmpty());
            }
            driver.navigate().back();
        }
    }
}
