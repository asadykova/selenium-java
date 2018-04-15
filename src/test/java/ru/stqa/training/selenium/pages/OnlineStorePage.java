package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class OnlineStorePage extends Page {

    private ProductPage productPage;

    public OnlineStorePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));
    }

    public WebElement firstProduct() {
        return driver.findElements(By.cssSelector("li.product")).get(0);
    }

    @FindBy(css = "#cart a.link")
    public WebElement cartButton;

    public void moveToCart() {

        productPage = new ProductPage(driver);

        for (int i = 0; i < 3; i++) {

            firstProduct().click();
            if (productPage.productTitle().equals("Yellow Duck")) {
                productPage.selectSize();
            }

            productPage.addToCartButton.click();
            productPage.waitUntilCart(i);
        }
    }
}