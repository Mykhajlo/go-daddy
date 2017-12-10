package com.github.automation.godaddy;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import static com.github.automation.godaddy.HomePageMy.hello_username;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author mykhail  on 10.12.17.
 */
public class LoginPageMy {
    public static final By username_field = By.id("username");
    public static final By password_field = By.id("password");
    public static final By login_button = By.id("login");
    public static final By no_username_or_mail_address_provided = By.xpath("//*[@id=\"cms\"]/div/div/div[1]/div/div[1]");
    public static final By no_password_provided = By.xpath("//*[@id=\"cms\"]/div/div/div[1]/div/div[2]");

    public static void clickLogMeInButton(WebDriver driver) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(500, MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(login_button));// "Log Me In" title is clickable
        driver.findElement(login_button).click();// Click "Log Me In" Button
    }
}
