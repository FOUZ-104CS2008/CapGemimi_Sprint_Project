package swagLabHomePage;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.baseclass;
import PomPages.HomePage;
import PomPages.LoginPage;
import Utility.ExcelUtility;

public class HomePage_Testcase extends baseclass {
	@Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
    private void login() throws Exception {

        LoginPage loginPage = new LoginPage(driver);

        String username = ExcelUtility.getData("Login", 1, 1);
        String password = ExcelUtility.getData("Login", 1, 2);

        loginPage.login(username, password);

        Reporter.log("Login successful",true);
    }

    @Test
    public void verifyHomePage() throws Exception {

        login();

        HomePage homePage = new HomePage(driver);

        Assert.assertTrue(
                homePage.getHamburger().isDisplayed(),
                "Hamburger menu is not displayed"
        );

        Assert.assertTrue(
                homePage.getCart().isDisplayed(),
                "Shopping cart is not displayed"
        );

        Reporter.log("Home page verified successfully",true);
    }

    @Test
    public void verifyAllItemsMenu() throws Exception {

        login();

        HomePage homePage = new HomePage(driver);

        homePage.clickHamburger();
        homePage.clickAllItems();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("inventory.html"),
                "All Items navigation failed"
        );

        Reporter.log("All Items menu verified",true);
    }

    @Test
    public void verifyLogout() throws Exception {

        login();

        HomePage homePage = new HomePage(driver);

        homePage.clickHamburger();
        homePage.clickLogout();

        Assert.assertTrue(
                driver.getCurrentUrl().contains("saucedemo.com"),
                "Logout was not successful"
        );

        Reporter.log("Logout verified successfully",true);
    }
}