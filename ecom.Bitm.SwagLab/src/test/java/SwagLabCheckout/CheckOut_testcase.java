package SwagLabCheckout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import BaseClass.baseclass;
import PomPages.AddToCart;
import PomPages.CheckOut;
import PomPages.CheckOut2;
import PomPages.CheckOut3;
import PomPages.LoginPage;
import PomPages.ProductPage;
import Utility.ExcelUtility;

public class CheckOut_testcase extends baseclass {

    // =========================================================
    // LOGIN
    // =========================================================
	
	@Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
    private void login() throws Exception {

        LoginPage loginPage =
                new LoginPage(driver);

        String username =
                ExcelUtility.getData("Login", 1, 1);

        String password =
                ExcelUtility.getData("Login", 1, 2);

        loginPage.login(username, password);

        Reporter.log("Login successful", true);
    }

    // =========================================================
    // WAIT FOR CHECKOUT INFORMATION PAGE
    // =========================================================

    private void waitForCheckoutInformation() {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(15)
                );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("continue")
                )
        );
    }

    // =========================================================
    // WAIT FOR CHECKOUT OVERVIEW PAGE
    // =========================================================

    private void waitForCheckoutOverview() {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(20)
                );

        wait.until(
                ExpectedConditions.urlContains(
                        "checkout-step-two.html"
                )
        );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("finish")
                )
        );

        System.out.println(
                "Checkout Overview loaded"
        );
    }

    // =========================================================
    // WAIT FOR ERROR MESSAGE
    // =========================================================

    private String waitForErrorMessage() {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(10)
                );

        By errorLocator =
                By.cssSelector(
                        "[data-test='error']"
                );

        WebElement error =
                wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                errorLocator
                        )
                );

        String message =
                error.getText();

        System.out.println(
                "Checkout Error Message: "
                        + message
        );

        return message;
    }

    // =========================================================
    // WAIT FOR CHECKOUT COMPLETE
    // =========================================================

    private void waitForCheckoutComplete() {

        WebDriverWait wait =
                new WebDriverWait(
                        driver,
                        Duration.ofSeconds(15)
                );

        wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.className("complete-header")
                )
        );

        wait.until(
                ExpectedConditions.textToBePresentInElementLocated(
                        By.className("complete-header"),
                        "Thank you for your order!"
                )
        );
    }

    // =========================================================
    // CHECKOUT TEST 1
    // VALID CHECKOUT
    // =========================================================

    @Test(priority = 1, groups = {"ST", "FT", "IT", "YT"})
    public void checkoutTest1() throws Exception {

        // Login
        login();

        // Product page
        ProductPage productPage =
                new ProductPage(driver);

        // Add Backpack
        productPage.addBackpackToCart();

        Reporter.log(
                "Backpack added to cart",
                true
        );

        // Open cart
        productPage.clickCart();

        Reporter.log(
                "Cart opened",
                true
        );

        // Checkout
        AddToCart addToCart =
                new AddToCart(driver);

        addToCart.clickCheckout();

        Reporter.log(
                "Checkout page opened",
                true
        );

        // Checkout information
        CheckOut checkOut =
                new CheckOut(driver);

        waitForCheckoutInformation();

        Reporter.log(
                "Checkout information page loaded",
                true
        );

        // Read checkout data from Excel
        String firstName =
                ExcelUtility.getData(
                        "Checkout",
                        1,
                        0
                );

        String lastName =
                ExcelUtility.getData(
                        "Checkout",
                        1,
                        1
                );

        String postalCode =
                ExcelUtility.getData(
                        "Checkout",
                        1,
                        2
                );

        System.out.println(
                "First Name: " + firstName
        );

        System.out.println(
                "Last Name: " + lastName
        );

        System.out.println(
                "Postal Code: " + postalCode
        );

        // Enter customer details
        checkOut.enterFirstName(firstName);

        checkOut.enterLastName(lastName);

        checkOut.enterPostalCode(postalCode);

        System.out.println(
                "All checkout fields entered successfully"
        );

        Reporter.log(
                "All customer details entered",
                true
        );

        Thread.sleep(1000);

        // Continue
        checkOut.clickContinue();

        Reporter.log(
                "Continue button clicked",
                true
        );

        // Wait for overview
        waitForCheckoutOverview();

        // Checkout overview page
        CheckOut2 checkOut2 =
                new CheckOut2(driver);

        String title =
                checkOut2.getPageTitle();

        System.out.println(
                "Page Title: " + title
        );

        Assert.assertEquals(
                title,
                "Checkout: Overview",
                "Checkout Overview page was not loaded"
        );

        Reporter.log(
                "Checkout Overview verified",
                true
        );

        // Finish
        checkOut2.clickFinish();

        Reporter.log(
                "Finish button clicked",
                true
        );

        // Wait for completion
        waitForCheckoutComplete();

        // Confirmation page
        CheckOut3 checkOut3 =
                new CheckOut3(driver);

        String confirmation =
                checkOut3.getConfirmationMessage();

        System.out.println(
                "Confirmation Message: "
                        + confirmation
        );

        Assert.assertEquals(
                confirmation,
                "Thank you for your order!",
                "Incorrect confirmation message"
        );

        Reporter.log(
                "Checkout Test 1 Passed",
                true
        );
    }

    // =========================================================
    // CHECKOUT TEST 2
    // FIRST NAME MISSING
    // =========================================================

    @Test(priority = 1, groups = {"FT", "NT"})
    public void checkoutTest2() throws Exception {

        // Login
        login();

        // Product page
        ProductPage productPage =
                new ProductPage(driver);

        // Add Bike Light
        productPage.addBikeLightToCart();

        Reporter.log(
                "Bike Light added to cart",
                true
        );

        // Open cart
        productPage.clickCart();

        Reporter.log(
                "Cart opened",
                true
        );

        // Checkout
        AddToCart addToCart =
                new AddToCart(driver);

        addToCart.clickCheckout();

        Reporter.log(
                "Checkout page opened",
                true
        );

        // Checkout information
        CheckOut checkOut =
                new CheckOut(driver);

        waitForCheckoutInformation();

        Reporter.log(
                "Checkout information page loaded",
                true
        );

        // Read last name and postal code
        String lastName =
                ExcelUtility.getData(
                        "Checkout",
                        1,
                        1
                );

        String postalCode =
                ExcelUtility.getData(
                        "Checkout",
                        1,
                        2
                );

        // First name intentionally NOT entered
        checkOut.enterLastName(lastName);

        checkOut.enterPostalCode(postalCode);

        Reporter.log(
                "Last Name and Postal Code entered",
                true
        );

        Thread.sleep(500);

        // Continue
        checkOut.clickContinue();

        Reporter.log(
                "Continue button clicked",
                true
        );

        // Get validation error
        String errorMessage =
                waitForErrorMessage();

        System.out.println(
                "Error Message: "
                        + errorMessage
        );

        // Verify error
        Assert.assertEquals(
                errorMessage,
                "Error: First Name is required",
                "Incorrect validation message"
        );

        // Screenshot
        takeScreenshot(
                "Checkout_FirstName_Missing"
        );

        Reporter.log(
                "Checkout Test 2 Passed",
                true
        );
    }

    // =========================================================
    // CHECKOUT TEST 3
    // ALL DETAILS MISSING
    // =========================================================

    @Test(priority = 1, groups = {"FT", "NT"})
    public void checkoutTest3() throws Exception {

        // Login
        login();

        // Product page
        ProductPage productPage =
                new ProductPage(driver);

        // Add Bolt T-Shirt
        productPage.addBoltTshirtToCart();

        Reporter.log(
                "Bolt T-Shirt added to cart",
                true
        );

        // Open cart
        productPage.clickCart();

        Reporter.log(
                "Cart opened",
                true
        );

        // Checkout
        AddToCart addToCart =
                new AddToCart(driver);

        addToCart.clickCheckout();

        Reporter.log(
                "Checkout page opened",
                true
        );

        // Checkout information
        CheckOut checkOut =
                new CheckOut(driver);

        waitForCheckoutInformation();

        Reporter.log(
                "Checkout information page loaded",
                true
        );

        // Do not enter any details

        // Click Continue
        checkOut.clickContinue();

        Reporter.log(
                "Continue button clicked",
                true
        );

        // Get validation error
        String errorMessage =
                waitForErrorMessage();

        System.out.println(
                "Error Message: "
                        + errorMessage
        );

        // Verify error
        Assert.assertEquals(
                errorMessage,
                "Error: First Name is required",
                "Incorrect validation message"
        );

        // Screenshot
        takeScreenshot(
                "Checkout_Empty_Details"
        );

        Reporter.log(
                "Checkout Test 3 Passed",
                true
        );
    }
}