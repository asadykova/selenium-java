package ru.stqa.training.selenium;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

public class FirstTest {
    @Test
    public void firstTest() {

        System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");
        ChromeDriver driver = new ChromeDriver();

        driver.get("https://www.google.com");

        driver.quit();
    }
}
