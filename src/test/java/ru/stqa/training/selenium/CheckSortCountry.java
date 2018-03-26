package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckSortCountry extends MainClass {

    //сценарий проверки расположения стран в алфавитном порядке
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

            //идем на страницу стран
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
        //для каждого элемента с наименование страны добавляем его в массив
        for (WebElement rows:countries) {
            List<WebElement> columns = rows.findElements(By.tagName("td"));
            checkList.add(columns.get(4).getAttribute("textContent"));
        }

        ArrayList<String> sortedList = new ArrayList<String>();
        for (String sorted:checkList
             ) {
            sortedList.add(sorted);
        }
        Collections.sort(sortedList);
        //проверяем
        Assert.assertTrue(sortedList.equals(checkList));
    }

    //сценарий проверки тех стран, у которых количество зон отлично от нуля -- открыть страницу этой страны и там проверить, что зоны расположены в алфавитном порядке
    @Test
    public void checkGeozonesName() {
        ArrayList<String> checkList = new ArrayList<String>();

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

        List<WebElement> countries = driver.findElements(By.cssSelector("#content tr.row"));

        for (i = 0; i < countries.size(); i++) {
            checkList.clear();
            List<WebElement> countries1 = driver.findElements(By.cssSelector("tr.row"));
            List<WebElement> columns = countries1.get(i).findElements(By.tagName("td"));
            if (columns.get(5).getAttribute("textContent").equals("0")) {
                continue;
            }
            else {
                //переходим на страницу той страны, для которой количество зон больше нуля
                columns.get(4).findElement(By.tagName("a")).click();
                List<WebElement> zones1 = driver.findElements(By.cssSelector("#table-zones.dataTable tr:not(.header)"));
                int j;
                for (j = 0; j < zones1.size()-1; j++) {
                    List<WebElement> nameZones = zones1.get(j).findElements(By.tagName("td"));
                    //добавляем наименование каждой зоны в массив
                    checkList.add(nameZones.get(2).getAttribute("textContent"));
                }
                ArrayList<String> sortedList = new ArrayList<String>();
                for (String sorted:checkList) {
                    sortedList.add(sorted);
                }
                Collections.sort(sortedList);
                //проверяем
                Assert.assertTrue(sortedList.equals(checkList));
                driver.navigate().back();
            }
        }
    }
}