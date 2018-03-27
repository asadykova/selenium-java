package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class CreateAccount extends MainClass {

    @Test
    public void CreateAccount() {

        driver.get("http://localhost/litecart");
        wait.until(titleIs("Online Store | My Store"));

        WebElement newAccount = driver.findElement(By.cssSelector("#box-account-login div.content tr:last-child"));
        newAccount.click();
        /*
        "#create-account div.content tr [name=firstname]"
        "#create-account div.content tr [name=lastname]"
        "#create-account div.content tr [name=address1]"
        "#create-account div.content tr [name=address2]"
        "#create-account div.content tr [name=postcode]" //формат индекса -- пять цифр
        "#create-account div.content tr [name=city]"
        "#create-account div.content tr span.select2" //страну выбрать United States
        "#create-account div.content tr select [name=zone_code]" //выбрать любую зону
        "#create-account div.content tr [name=email]" //должен быть уникален для каждого запуска теста
        "#create-account div.content tr [name=phone]"
        "#create-account div.content tr [name=newsletter]"
        "#create-account div.content tr [name=password]"
        "#create-account div.content tr [name=confirmed_password]"
        "#create-account div.content tr [name=create_account]"
        в java.io.File есть getCanonicalPath()
        http://phpfaq.ru/newbie/paths
        */
    }
}
