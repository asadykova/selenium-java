package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.List;
import java.util.Set;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckNewWindow extends MainClass {

    public ExpectedCondition<String> thereIsWindowOtherThan(final String currWin) {

        return new ExpectedCondition<String>() {
            public String apply(WebDriver webDriver) {
                Set<String> allWin = driver.getWindowHandles();
                for (String eachWin : allWin) {
                    if (!currWin.equals(eachWin)) {
                        return eachWin;
                    }
                }
                return currWin;
            }
        };
    }

    @Test
    public void CheckWindow() {

        driver.get("http://localhost/litecart/admin");
        wait.until(titleIs("My Store"));
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> country = driver.findElements(By.cssSelector("#app- span.name"));
        int i;
        for (i = 0; i < country.size(); i++) {
            country = driver.findElements(By.cssSelector("#app- span.name"));

            //идем на страницу стран
            if (country.get(i).getAttribute("textContent").equals("Countries")) {
                country.get(i).click();
            }
            else {
                continue;
            }
        }

        driver.findElement(By.cssSelector("#content a.button")).click();

        List<WebElement> extLink = driver.findElements(By.cssSelector("table i.fa.fa-external-link"));
        for (i = 0; i < extLink.size(); i++) {
            extLink = driver.findElements(By.cssSelector("table i.fa.fa-external-link"));
            String mainWin = driver.getWindowHandle();
            Set<String> exWin = driver.getWindowHandles();

            extLink.get(i).click();

            for(String windowHandle  : exWin)
            {
                String newWin = wait.until(thereIsWindowOtherThan(windowHandle));
                driver.switchTo().window(newWin);

                driver.close();
                driver.switchTo().window(mainWin);
            }

        }
    }
}
