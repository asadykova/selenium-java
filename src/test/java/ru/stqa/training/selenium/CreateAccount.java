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

        List<WebElement> account = driver.findElements(By.cssSelector("#create-account div.content tr"));
        WebElement firstName = account.get(1).findElement(By.cssSelector("[name=firstname]"));
        firstName.sendKeys("Aida" + Keys.TAB + "Sadykova" + Keys.TAB
        + "Abay st." + Keys.TAB + Keys.TAB + "00057" + Keys.TAB + "New York");
        WebElement country = driver.findElement(By.cssSelector(".select2"));
        country.click();
        WebElement ff = driver.findElement(By.cssSelector(".select2-search__field"));
        ff.sendKeys("United States" + Keys.ENTER);
        WebElement state = account.get(4).findElement(By.cssSelector("select[name=zone_code]"));
        Select stateName = new Select(state);
        stateName.selectByVisibleText("Arizona");


        WebElement email = account.get(5).findElement(By.cssSelector("[name=email]"));
        Date d = new Date();
        String randomString = "asadykova_" + d.getDate() + d.getTime() + "@mail.com";
        System.out.print(randomString);
        email.sendKeys(randomString + Keys.TAB + "+77777777777" + Keys.TAB
        + Keys.SPACE + Keys.TAB + "123456" + Keys.TAB + "123456" + Keys.TAB + Keys.ENTER);

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
