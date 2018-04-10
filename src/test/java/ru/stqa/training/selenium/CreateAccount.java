package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CreateAccount extends MainClass {

    @Test
    public void CreateAccount() {

        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));

        WebElement newAccount = driver.findElement(By.cssSelector("#box-account-login div.content tr:last-child"));
        newAccount.click();

        WebElement account = driver.findElement(By.cssSelector("#create-account div.content"));
        account.findElement(By.name("firstname")).sendKeys("Aida");
        account.findElement(By.name("lastname")).sendKeys("Sadykova");
        account.findElement(By.name("address1")).sendKeys("Abay st.");
        account.findElement(By.name("postcode")).sendKeys("00057");
        account.findElement(By.name("city")).sendKeys("New York");
        WebElement country = driver.findElement(By.cssSelector(".select2"));
        country.click();
        WebElement ff = driver.findElement(By.cssSelector(".select2-search__field"));
        ff.sendKeys("United States" + Keys.ENTER);
        WebElement state = account.findElement(By.name("zone_code"));
        Select stateName = new Select(state);
        stateName.selectByVisibleText("Arizona");


        Date d = new Date();
        String randomString = "asadykova_" + d.getDate() + d.getTime() + "@mail.com";
        account.findElement(By.name("email")).sendKeys(randomString);

        account.findElement(By.name("phone")).sendKeys("+77777777777");
        account.findElement(By.name("newsletter")).click();
        account.findElement(By.name("password")).sendKeys("123456");
        account.findElement(By.name("confirmed_password")).sendKeys("123456");
        account.findElement(By.cssSelector("button[name=create_account]")).click();

        //выход
        WebElement logOut = driver.findElement(By.cssSelector("#box-account div.content li:last-child a"));
        Actions mouse = new Actions(driver);
        mouse.moveToElement(logOut).click().build().perform();
        //logOut.click(); //просто клик без actions не работал для фаерфокс почему-то...

        //авторизация
        WebElement login = driver.findElement(By.cssSelector("#box-account-login [name=email]"));
        login.sendKeys(randomString + Keys.TAB + "123456");

        WebElement loginButton =  driver.findElement(By.cssSelector("#box-account-login [name=login]"));
        loginButton.click();

        //снова выход
        logOut = driver.findElement(By.cssSelector("#box-account div.content li:last-child a"));
        mouse.moveToElement(logOut).click().build().perform();
    }
}
