package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AddToCart extends MainClass {

    @Test
    public void AddToCart() {

        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));

        for (int i = 0; i < 3; i++) {
            driver.findElements(By.cssSelector("li.product")).get(0).click();

            WebElement prTitle = driver.findElement(By.cssSelector("h1.title"));
            if (prTitle.getAttribute("textContent").equals("Yellow Duck")) {
                new Select(driver.findElement(By.name("options[Size]"))).selectByVisibleText("Medium +$2.50");
            }

            WebElement quantity = driver.findElement(By.cssSelector("#cart span.quantity"));
            System.out.print(quantity.getAttribute("textContent") + "\n");
            driver.findElement(By.name("add_cart_product")).click();

            wait.until(textToBe(By.cssSelector("#cart-wrapper span.quantity"), Integer.toString(i+1)));
            quantity = driver.findElement(By.cssSelector("#cart span.quantity"));
            System.out.print(quantity.getAttribute("textContent") + "\n");

            driver.findElement(By.id("logotype-wrapper")).click();
        }

        driver.findElement(By.cssSelector("#cart a.link")).click();

        List<WebElement> products = driver.findElements(By.cssSelector("#order_confirmation-wrapper .dataTable tr td.item"));
        while (products.size() > 0) {
            wait.until(elementToBeClickable(By.name("remove_cart_item")));
            driver.findElement(By.name("remove_cart_item")).click();
            wait.until(stalenessOf(products.get(0)));
            products = driver.findElements(By.cssSelector("#order_confirmation-wrapper .dataTable tr td.item"));
        }
    }
}
