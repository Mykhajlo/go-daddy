package com.github.automation.godaddy;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author mykhail on 10.12.17.
 */
public class HomePageMy {
    public static final By hello_username = By.xpath("//*[@id=\"body\"]/div/div[1]/h1");
    public static final By sign_out_button = By.xpath("//*[@id=\"body\"]/div/div[1]/h1/span/a");

    public static void clickSignOutButton(WebDriver driver) {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(30, SECONDS)
                .pollingEvery(500, MILLISECONDS)
                .ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.elementToBeClickable(sign_out_button));// "Sign Out" title is clickable
        driver.findElement(sign_out_button).click();// Click "Sign Out" Button
    }

}
