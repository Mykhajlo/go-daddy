package com.github.automation.godaddy;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.github.automation.godaddy.HomePageMy.hello_username;
import static com.github.automation.godaddy.LoginPageMy.password_field;
import static com.github.automation.godaddy.LoginPageMy.username_field;
import static java.util.Optional.ofNullable;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.openqa.selenium.By.className;

/**
 * @author mykhail on 21.11.17.
 */
public class Utils {
    private static final Logger LOGGER = Logger.getLogger(Utils.class);

    public static void switchToIframe(WebDriver driver) throws InterruptedException {
        driver.switchTo().frame(driver.findElement(By.id("iframeContainer")));
        LOGGER.info("Bingo! You are at iframeContainer");
        try {
            WebDriverWait wait = new WebDriverWait(driver, 5, 10000);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"game\"]/div[1]/main-header/header/div/div/div[2]/div[2]"))); // find Balance
            LOGGER.info("Balance is present");
        } catch (Throwable e) {
            LOGGER.info("Error while switching to the frame. " + e.getMessage());
        }
        // Thread.sleep(1000);
    }

    public static ChromeDriver setupEnvironment() {
        //System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver.dmg");
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            System.setProperty("webdriver.chrome.driver", "src//test//resources//chromedriver_win.exe");
        }
        if (osName.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", "src//test//resources//chromedriver");
        }
        //linux
        ChromeDriver driver = new ChromeDriver();
        Dimension d = new Dimension(1400, 900); // > HD resolution
        driver.manage().window().setSize(d);
        //driver.manage().window().maximize(); // full size  of screen
        return driver;
    }

    public static void openLoginPage(WebDriver driver) throws InterruptedException {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(500, MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        driver.get("https://www.123-reg.co.uk/public/login");
        driver.getWindowHandle();
        //Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cms\"]/div/div/div[1]/div/h2")));// "Existing user?" title is visible

        Assert.assertTrue(ofNullable(driver.findElement((username_field))).isPresent());
        LOGGER.info("Username field is present");
        Assert.assertTrue(ofNullable(driver.findElement((password_field))).isPresent());
        LOGGER.info("Password field is present");

    }
    public static void enterLoginAndPassword (WebDriver driver, String login, String password)throws InterruptedException{
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(500, MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"cms\"]/div/div/div[1]/div/h2")));// "Existing user?" title is visible
        driver.findElement(username_field).clear();
        driver.findElement(username_field).sendKeys(login);
        driver.findElement(password_field).clear();
        driver.findElement(password_field).sendKeys(password);
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.SECONDS);

        for (String handleGame : driver.getWindowHandles()) {
            driver.switchTo().window(handleGame);
        }
        LOGGER.info(driver.getCurrentUrl());
        //Open url
    }

    public static void checkUsername (WebDriver driver, String username){
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(500, MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(hello_username));// "Hello + username" title is visible
        LOGGER.info("Bingo ->>>>>" + driver.findElement(hello_username).getText().substring(6));
        Assert.assertTrue(driver.findElement(hello_username).getText().substring(6).contains(username));
        LOGGER.info("Hello " + username + " displayed correctly");
    }
}
