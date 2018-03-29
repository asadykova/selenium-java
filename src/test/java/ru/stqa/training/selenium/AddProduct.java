package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;

import java.util.List;
import java.io.File;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class AddProduct extends MainClass {
    public void unhide(WebDriver driver, WebElement element) {
        String script = "arguments[0].style.opacity=1;"
                + "arguments[0].style['transform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['MozTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['WebkitTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['msTransform']='translate(0px, 0px) scale(1)';"
                + "arguments[0].style['OTransform']='translate(0px, 0px) scale(1)';"
                + "return true;";
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    public void attachFile(WebDriver driver, By locator, String file) {
        WebElement input = driver.findElement(locator);
        unhide(driver, input);
        input.sendKeys(file);
    }

    @Test
    public void AddProduct() {

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

        String productName = "Smart Duck";

        WebElement content = driver.findElement(By.id("content"));
        content.findElements(By.cssSelector("a.button")).get(1).click();
        content = driver.findElement(By.id("content"));
        WebElement general = content.findElement(By.id("tab-general"));
        general.findElements(By.cssSelector("tr:first-child label")).get(0).click();

        general.findElement(By.name("name[en]")).sendKeys(productName);
        general.findElement(By.name("code")).sendKeys("987654");
        general.findElements(By.name("categories[]")).get(0).click();
        general.findElements(By.name("categories[]")).get(1).click();

        general.findElements(By.name("product_groups[]")).get(2).click();
        general.findElement(By.name("quantity")).sendKeys(Keys.DELETE + "1");

        File directory = new File("images/image.jpg");
        //System.out.println(directory.getAbsolutePath());
        attachFile(driver, By.name("new_images[]"), directory.getAbsolutePath());

        general.findElement(By.name("date_valid_from")).sendKeys("03/06/2018");
        general.findElement(By.name("date_valid_to")).sendKeys("01/01/2020");

        content.findElements(By.cssSelector("ul.index li a")).get(1).click();

        WebElement information = driver.findElement(By.id("tab-information"));
        Select manufacturer = new Select(information.findElement(By.name("manufacturer_id")));
        manufacturer.selectByVisibleText("ACME Corp.");

        information.findElement(By.name("keywords")).sendKeys("test");
        information.findElement(By.name("short_description[en]")).sendKeys("a cup of coffee");
        information.findElement(By.cssSelector("div.trumbowyg-editor")).sendKeys("some text about coffee");
        information.findElement(By.name("head_title[en]")).sendKeys("large cup");
        information.findElement(By.name("meta_description[en]")).sendKeys("some text");

        content = driver.findElement(By.id("content"));
        content.findElements(By.cssSelector("ul.index li a")).get(3).click();

        WebElement prices = driver.findElement(By.id("tab-prices"));
        prices.findElement(By.name("purchase_price")).sendKeys(Keys.DELETE + "42");
        new Select(prices.findElement(By.name("purchase_price_currency_code"))).selectByVisibleText("US Dollars");
        prices.findElement(By.name("prices[USD]")).sendKeys("47");
        prices.findElement(By.name("prices[EUR]")).sendKeys("40");

        driver.findElement(By.cssSelector("button[name=save]")).click();

        //проверяем наличие добавленного товара
        List<WebElement> products = driver.findElements(By.cssSelector("table.dataTable tr.row"));
        String foundProduct = new String();
        for (i = 0; i < products.size(); i++) {
            List<WebElement> listOfProducts = products.get(i).findElements(By.cssSelector("td a"));
            int j;
            for (j = 0; j < listOfProducts.size(); j++) {
                if (listOfProducts.get(j).getAttribute("textContent").equals(productName)) {
                    foundProduct = listOfProducts.get(j).getAttribute("textContent");
                    //System.out.print(foundProduct);
                }
                else {
                    continue;
                }
            }
        }
        Assert.assertTrue(foundProduct.equals(productName));
    }
}
