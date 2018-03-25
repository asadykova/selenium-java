package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AlphabeticalSort extends MainClass {

    @Test
    public void checkCountryName() {

        driver.get("http://localhost/litecart/admin");
        wait.until(titleIs("My Store"));
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> country = driver.findElements(By.cssSelector("#app- span.name"));
        int i;
        for (i = 0; i < country.size(); i++) {
            country = driver.findElements(By.cssSelector("#app- span.name"));

            if (country.get(i).getAttribute("textContent").equals("Countries")) {
                country.get(i).click();
            }
            else {
                continue;
            }
        }

        WebElement content = driver.findElement(By.cssSelector("#content"));
        List<WebElement> countries = content.findElements(By.cssSelector("tr.row"));
        ArrayList<String> checkList = new ArrayList<String>();

        /*for (WebElement rows:countries) {
            List<WebElement> columns = rows.findElements(By.tagName("td"));
            checkList.add(columns.get(4).getAttribute("textContent"));
        }
*/
        ArrayList<String> sortedList = new ArrayList<String>();
        /*for (String sorted:checkList
             ) {
            sortedList.add(sorted);
        }
        Collections.sort(sortedList);
        Assert.assertTrue(sortedList.equals(checkList));*/
        for (i = 0; i < countries.size(); i++) {
            content = driver.findElement(By.cssSelector("#content"));
            countries = content.findElements(By.cssSelector("tr.row"));
            List<WebElement> columns = countries.get(i).findElements(By.tagName("td"));
            if (columns.get(5).getAttribute("textContent").equals("0")){
                continue;
            }
            else {
                columns.get(4).findElement(By.tagName("a")).click();

                List<WebElement> zones = driver.findElements(By.cssSelector("#table-zones.dataTable tr:not(.header)"));
                for (i = 0; i < zones.size() - 1; i++) {
                    List<WebElement> columns1 = zones.get(i).findElements(By.tagName("td"));
                    System.out.print(columns1.get(2).getAttribute("textContent"));
                    System.out.print("\n");
                    /*checkList.add(columns1.get(2).getAttribute("textContent"));*/
                }
                WebElement cancel = driver.findElement(By.cssSelector(".button-set [name=cancel]"));
                cancel.click();
            }
        }
        for (String sorted:checkList
                ) {
            sortedList.add(sorted);
        }
        Collections.sort(sortedList);
        System.out.print(sortedList);
        System.out.print("\n");
        System.out.print(checkList);
        System.out.print("\n");
        Assert.assertTrue(sortedList.equals(checkList));
    }
}
