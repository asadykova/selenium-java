package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import ru.stqa.training.selenium.app.Application;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;


public class TestBase {

    public static ThreadLocal<ru.stqa.training.selenium.app.Application> tlApp = new ThreadLocal<Application>();
    public ru.stqa.training.selenium.app.Application app;

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.print(by + "\n");
        }

        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.print(by + " found" + "\n");
        }

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            System.out.print(throwable);
        }
    }


    @Before
    public void start() {

        if (tlApp.get() != null) {
            app = tlApp.get();
            return;
        }

        app = new Application();
        tlApp.set(app);

        Runtime.getRuntime().addShutdownHook(
                new Thread(() -> { app.quit(); app = null; }));
    }
}