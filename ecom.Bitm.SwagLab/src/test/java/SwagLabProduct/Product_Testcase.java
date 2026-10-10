package SwagLabProduct;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.baseclass;
import PomPages.LoginPage;
import PomPages.ProductPage;
import Utility.ExcelUtility;

public class Product_Testcase extends baseclass {
	@Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})

    private void login() throws Exception {

        LoginPage loginPage =
                new LoginPage(driver);

        String username =
                ExcelUtility.getData("Login", 1, 1);

        String password =
                ExcelUtility.getData("Login", 1, 2);

        loginPage.login(username, password);
    }


	@Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
    public void verifyProductPage() throws Exception {

        login();

        ProductPage productPage =
                new ProductPage(driver);

        Assert.assertEquals(
                productPage.getPageTitle(),
                "Products"
        );

        System.out.println("Product page verified");
    }


	@Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
    public void sortProducts() throws Exception {

        login();

        ProductPage productPage =
                new ProductPage(driver);


        // Name A-Z
        productPage.sortByNameAToZ();

        Reporter.log("Name A-Z sorting completed",true);


        // Name Z-A
        productPage.sortByNameZToA();

        Reporter.log("Name Z-A sorting completed",true);


        // Price Low to High
        productPage.sortByPriceLowToHigh();

        Reporter.log(
                "Price Low to High sorting completed",true);


        // Price High to Low
        productPage.sortByPriceHighToLow();
        Reporter.log(
                "Price High to Low sorting completed",true);
    }
}