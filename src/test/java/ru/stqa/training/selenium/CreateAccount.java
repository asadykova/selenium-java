package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import javax.swing.*;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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
        + "Abay st." + Keys.TAB + Keys.TAB + "00057" + Keys.TAB + "New York"
        + Keys.TAB + Keys.ENTER + "United States" + Keys.ENTER);

        /*WebElement state = account.get(4).findElement(By.cssSelector("select[name=zone_code]"));
        //state.isDisplayed();
        state.click();
        Actions mouse = new Actions(driver);
        WebElement stateName = state.findElement(By.cssSelector("option[value=AZ]"));
        System.out.print(stateName.getText());
        mouse.
                moveToElement(stateName).
                sendKeys(Keys.ENTER).
                perform();

*/
        WebElement email = account.get(5).findElement(By.cssSelector("[name=email]"));
        Date d = new Date();
        String randomString = "asadykova" + d.getYear() + d.getDate() + d.getTime() + "@mail.com";
        System.out.print(randomString);
        email.sendKeys(randomString + Keys.TAB + "+77777777777" + Keys.TAB
        + Keys.SPACE + Keys.TAB + "123456" + Keys.TAB + "123456" + Keys.TAB /*+ Keys.ENTER*/);
        /*
        "#create-account div.content tr select [name=zone_code]" //выбрать любую зону
        в java.io.File есть getCanonicalPath()
        http://phpfaq.ru/newbie/paths
        */
    }
}
