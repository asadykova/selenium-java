package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class ProductPage extends Page {

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public String productTitle() {
        WebElement prTitle = driver.findElement(By.cssSelector("h1.title"));
        return prTitle.getAttribute("textContent");
    }

    @FindBy(name = "add_cart_product")
    public WebElement addToCartButton;

    public void waitUntilCart(int i) {
        wait.until(textToBe(By.cssSelector("#cart-wrapper span.quantity"), Integer.toString(i+1)));
        driver.navigate().back();
    }

    public void selectSize() {
        new Select(driver.findElement(By.name("options[Size]"))).selectByVisibleText("Medium +$2.50");
    }
}