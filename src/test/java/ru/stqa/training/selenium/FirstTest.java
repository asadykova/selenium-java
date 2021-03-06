package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class FirstTest {

    private WebDriver driver;
    private WebDriverWait wait;

    @Before
    public void start() {
        /*System.setProperty("webdriver.chrome.driver", "/usr/local/share/chromedriver");*/
        driver = new ChromeDriver(); /*init драйвера */
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void firstTest() {

        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("webdriver\n");
        driver.findElement(By.name("btnG")).click();
        wait.until(titleIs("webdriver - Google-мен іздеу"));
    }

    @After
    public void stop() {
        driver.quit();
        driver = null;
    }
}
