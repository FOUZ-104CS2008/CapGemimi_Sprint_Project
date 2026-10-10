package SwagLabLogin;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.baseclass;
import PomPages.LoginPage;
import Utility.ExcelUtility;

public class Login_Testcase extends baseclass {

	@Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
    public void validLogin() throws Exception {

        LoginPage loginPage =
                new LoginPage(driver);

        String username =
                ExcelUtility.getData("Login", 1, 1);

        String password =
                ExcelUtility.getData("Login", 1, 2);

        loginPage.login(username, password);

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html")
        );

        Reporter.log("Valid login successful",true);
    }


	@Test(priority = 1, groups = { "FT", "NT"})
    public void invalidLogin() throws Exception {

        LoginPage loginPage =
                new LoginPage(driver);

        String username =
                ExcelUtility.getData("Login", 2, 1);

        String password =
                ExcelUtility.getData("Login", 2, 2);

        loginPage.login(username, password);

        String error =
                loginPage.getErrorMessage();

        Reporter.log("Error Message: " + error,true);

        Assert.assertTrue(
                error.contains(
                        "Username and password do not match")
        );

        // Take screenshot
        takeScreenshot("Invalid_Login");

        Reporter.log("Invalid login verified",true);
    }
}