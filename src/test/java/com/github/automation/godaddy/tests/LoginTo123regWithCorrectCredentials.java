package com.github.automation.godaddy.tests;

import com.google.common.base.Equivalence;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.github.automation.godaddy.HomePageMy.clickSignOutButton;
import static com.github.automation.godaddy.LoginPageMy.clickLogMeInButton;
import static com.github.automation.godaddy.LoginPageMy.username_field;
import static com.github.automation.godaddy.MainPageMy.control_panel_button;
import static com.github.automation.godaddy.Utils.*;

/**
 * @author mykhail  on 10.12.17.
 */
public class LoginTo123regWithCorrectCredentials {
    private static final Logger LOGGER = Logger.getLogger(LoginTo123regWithCorrectCredentials.class);

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
    public void LoginTo123regWithCorrectCredentialsTest () throws Exception {

        String login = "test@testingthedomainnamesearchtoolagain.uk";
        String password = "Password@2";
        String username = "Testing Account";
        String expected_url = "https://www.123-reg.co.uk/";

        enterLoginAndPassword(driver,login, password);
        clickLogMeInButton(driver);
        checkUsername(driver, username);
        clickSignOutButton(driver);
        String url = driver.getCurrentUrl();
        Assert.assertTrue(url.equals(expected_url));
        LOGGER.info("URL is correct");
        Assert.assertTrue(driver.findElement(control_panel_button).isEnabled());
        LOGGER.info("Test is finished");
        //Thread.sleep(10000);
    }


}
