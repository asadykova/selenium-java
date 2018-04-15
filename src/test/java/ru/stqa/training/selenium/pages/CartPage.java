package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartPage extends Page {

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "#order_confirmation-wrapper .dataTable tr td.item")
    private List<WebElement> productList;

    @FindBy(name = "remove_cart_item")
    private WebElement removeCartItem;

    public void removeFromCart() {
        while (productList.size() > 0) {
            wait.until(elementToBeClickable(By.name("remove_cart_item")));
            removeCartItem.click();
            wait.until(stalenessOf(productList.get(0)));
        }
    }
}
