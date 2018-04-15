package ru.stqa.training.selenium.app;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import ru.stqa.training.selenium.tests.TestBase;
import ru.stqa.training.selenium.pages.OnlineStorePage;
import ru.stqa.training.selenium.pages.ProductPage;
import ru.stqa.training.selenium.pages.CartPage;

public class Application {

    public EventFiringWebDriver driver;
    public WebDriverWait wait;

    private OnlineStorePage onlineStorePage;
    private ProductPage productPage;
    private CartPage cartPage;

    public Application() {

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new EventFiringWebDriver(new ChromeDriver(cap));
        driver.register(new TestBase.MyListener());
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        driver.manage().window().maximize();
        onlineStorePage = new OnlineStorePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
    }

    public void quit() {
        driver.quit();
    }

    public void addProductToCart() {

        onlineStorePage.open();
        onlineStorePage.moveToCart();

        onlineStorePage.cartButton.click();
        cartPage.removeFromCart();
    }
}