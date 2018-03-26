package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckSortZones extends MainClass {

    @Test
    public void checkZonesName() {

        driver.get("http://localhost/litecart/admin");
        wait.until(titleIs("My Store"));
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> country = driver.findElements(By.cssSelector("#app- span.name"));
        int i;
        for (i = 0; i < country.size(); i++) {
            country = driver.findElements(By.cssSelector("#app- span.name"));
            //идем на страницу геозон
            if (country.get(i).getAttribute("textContent").equals("Geo Zones")) {
                country.get(i).click();
            } else {
                continue;
            }
        }
        List<WebElement> zones = driver.findElements(By.cssSelector(".dataTable tr.row"));
        ArrayList<String> checkList = new ArrayList<String>();

        for (i = 0; i < zones.size(); i++) {
            checkList.clear();
            List<WebElement> aa = driver.findElements(By.cssSelector(".dataTable tr.row"));
            List<WebElement> aa1 = aa.get(i).findElements(By.tagName("td"));
            //для каждой страны идем на его страницу
            aa1.get(2).findElement(By.tagName("a")).click();
            List<WebElement> zones1 = driver.findElements(By.cssSelector("#table-zones.dataTable tr:not(.header)"));
            int j;
            for (j = 0; j < zones1.size()-1; j++) {
                List<WebElement> nameZones = zones1.get(j).findElements(By.tagName("td"));
                //добавляем наименование каждой зоны в массив
                checkList.add(nameZones.get(2).findElement(By.cssSelector("[selected=selected]")).getAttribute("textContent"));
            }
            ArrayList<String> sortedList = new ArrayList<String>();
            for (String sorted:checkList) {
                sortedList.add(sorted);
            }
            Collections.sort(sortedList);
            //сравниваем
            Assert.assertTrue(sortedList.equals(checkList));
            driver.navigate().back();
        }
    }
}