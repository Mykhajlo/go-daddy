package com.github.automation.godaddy.tests;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.automation.godaddy.HomePageMy.clickSignOutButton;
import static com.github.automation.godaddy.LoginPageMy.clickLogMeInButton;
import static com.github.automation.godaddy.LoginPageMy.no_password_provided;
import static com.github.automation.godaddy.LoginPageMy.no_username_or_mail_address_provided;
import static com.github.automation.godaddy.MainPageMy.control_panel_button;
import static com.github.automation.godaddy.Utils.*;

/**
 * @author mykhail  on 10.12.17.
 */
public class LoginTo123regWithoutCredentials {
    private static final Logger LOGGER = Logger.getLogger(LoginTo123regWithoutCredentials.class);

    public static WebDriver driver;

    @BeforeClass
    public void setUp() throws InterruptedException {
        driver = setupEnvironment();
    }

    @BeforeMethod
    public void openPage() throws InterruptedException {
        openLoginPage(driver);
    }

    @AfterClass
    public void tearDown() {
//      driver.close();
        driver.quit();
    }

    @Test
    public void LoginTo123regWithoutCredentialsTest () throws Exception {

        String login = "";
        String password = "";
        //String username = "Testing Account";
       // String expected_url = "https://www.123-reg.co.uk/";

        enterLoginAndPassword(driver,login, password);
        clickLogMeInButton(driver);
        Assert.assertTrue(driver.findElement(no_username_or_mail_address_provided).isDisplayed());
        LOGGER.info("Error message displayed : " + driver.findElement(no_username_or_mail_address_provided).getText());
        Assert.assertTrue(driver.findElement(no_password_provided).isDisplayed());
        LOGGER.info("Error message displayed : " + driver.findElement(no_password_provided).getText());
        login = "testemail";
        enterLoginAndPassword(driver,login, password);
        clickLogMeInButton(driver);
        Assert.assertTrue(driver.findElement(no_username_or_mail_address_provided).getText().equals("Error! No password provided."));
        LOGGER.info("Error message displayed : " + driver.findElement(no_username_or_mail_address_provided).getText());
        password="somepassword";
        enterLoginAndPassword(driver,login, password);
        clickLogMeInButton(driver);
        Assert.assertTrue(driver.findElement(no_username_or_mail_address_provided).getText().equals("Error! Your credentials appear to be wrong. Please try again."));
        LOGGER.info("Error message displayed : " + driver.findElement(no_username_or_mail_address_provided).getText());

        LOGGER.info("Test is finished");

    }
}
