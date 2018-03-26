package ru.stqa.training.selenium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CheckProduct extends MainClass {

    @Test
    public void checkYellowDuck() {

        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));

        //Главная страница
        WebElement duckName1 = driver.findElement(By.cssSelector("#box-campaigns.box .product div.name")); //наименование товара
        WebElement price1 = driver.findElement(By.cssSelector("#box-campaigns.box .product div.price-wrapper"));
        WebElement regularPrice1 = price1.findElement(By.cssSelector("s.regular-price")); //обычная цена
        WebElement campaignPrice1 = price1.findElement(By.cssSelector("strong.campaign-price")); //акционная цена

        String[] colorGray = Color.fromString(regularPrice1.getCssValue("color")).asRgb().split("\\(", 2)[1].split(", ", 3);
        String[] colorRed = Color.fromString(campaignPrice1.getCssValue("color")).asRgb().split("\\(", 2)[1].split(", ", 3);

        //обычная цена серого цвета
        Assert.assertTrue((Integer.parseInt(colorGray[0]) == Integer.parseInt(colorGray[1])) && (Integer.parseInt(colorGray[1]) == Integer.parseInt(colorGray[2].split("\\)", 2)[0])));
        //обычная цена зачеркнута
        Assert.assertTrue(regularPrice1.getTagName().equals("s"));

        //акционная цена красного цвета
        Assert.assertTrue((Integer.parseInt(colorRed[1]) == 0) && (Integer.parseInt(colorRed[2].split("\\)", 2)[0])) == 0);
        //акционная цена выделена жирным
        Assert.assertTrue(campaignPrice1.getTagName().equals("strong"));

        String dName1 = duckName1.getAttribute("textContent");
        String rPrice1 = regularPrice1.getAttribute("textContent");
        String cPrice1 = campaignPrice1.getAttribute("textContent");
        System.out.print(regularPrice1.getTagName());

        //акционная цена крупнее
        Assert.assertTrue((campaignPrice1.getSize().height > regularPrice1.getSize().height) && (campaignPrice1.getSize().width > regularPrice1.getSize().width));

        //Переход на страницу товара
        WebElement link = driver.findElement(By.cssSelector("#box-campaigns.box .product a.link"));
        link.click();
        WebElement duckName2 = driver.findElement(By.cssSelector("h1.title"));
        WebElement price2 = driver.findElement(By.cssSelector(".content .price-wrapper"));
        WebElement regularPrice2 = price2.findElement(By.cssSelector("s.regular-price"));
        WebElement campaignPrice2 = price2.findElement(By.cssSelector("strong.campaign-price"));
        String dName2 = duckName2.getAttribute("textContent");
        String rPrice2 = regularPrice2.getAttribute("textContent");
        String cPrice2 = campaignPrice2.getAttribute("textContent");

        String[] colorGray2 = Color.fromString(regularPrice2.getCssValue("color")).asRgb().split("\\(", 2)[1].split(", ", 3);
        String[] colorRed2 = Color.fromString(campaignPrice2.getCssValue("color")).asRgb().split("\\(", 2)[1].split(", ", 3);

        //обычная цена серого цвета
        Assert.assertTrue((Integer.parseInt(colorGray2[0]) == Integer.parseInt(colorGray2[1])) && (Integer.parseInt(colorGray2[1]) == Integer.parseInt(colorGray2[2].split("\\)", 2)[0])));
        //обычная цена зачеркнута
        Assert.assertTrue(regularPrice2.getTagName().equals("s"));

        //акционная цена красного цвета
        Assert.assertTrue((Integer.parseInt(colorRed2[1]) == 0) && (Integer.parseInt(colorRed2[2].split("\\)", 2)[0])) == 0);
        //акционная цена выделена жирным
        Assert.assertTrue(campaignPrice2.getTagName().equals("strong"));

        //акционная цена крупнее
        Assert.assertTrue((campaignPrice2.getSize().height > regularPrice2.getSize().height) && (campaignPrice2.getSize().width > regularPrice2.getSize().width));
        Assert.assertTrue(dName1.equals(dName2)); //совпадают ли названия товара
        Assert.assertTrue(rPrice1.equals(rPrice2)); //совпадают ли обычные цены
        Assert.assertTrue(cPrice1.equals(cPrice2)); //совпадают ли акционные цены

    }
}