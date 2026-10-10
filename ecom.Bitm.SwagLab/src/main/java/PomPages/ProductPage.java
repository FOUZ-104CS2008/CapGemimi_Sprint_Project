package PomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(className = "product_sort_container")
    private WebElement sortDropdown;

    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement backpackAddToCart;

    @FindBy(id = "add-to-cart-sauce-labs-bike-light")
    private WebElement bikeLightAddToCart;

    @FindBy(id = "add-to-cart-sauce-labs-bolt-t-shirt")
    private WebElement boltTshirtAddToCart;

    @FindBy(className = "shopping_cart_link")
    private WebElement cartIcon;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public ProductPage(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        PageFactory.initElements(driver, this);
    }

    // =========================================================
    // PAGE TITLE
    // =========================================================

    public String getPageTitle() {

        wait.until(
                ExpectedConditions.visibilityOf(pageTitle)
        );

        return pageTitle.getText();
    }

    // =========================================================
    // SORTING
    // =========================================================

    public void sortByNameAToZ() {

        wait.until(
                ExpectedConditions.visibilityOf(sortDropdown)
        );

        new Select(sortDropdown)
                .selectByValue("az");
    }

    public void sortByNameZToA() {

        wait.until(
                ExpectedConditions.visibilityOf(sortDropdown)
        );

        new Select(sortDropdown)
                .selectByValue("za");
    }

    public void sortByPriceLowToHigh() {

        wait.until(
                ExpectedConditions.visibilityOf(sortDropdown)
        );

        new Select(sortDropdown)
                .selectByValue("lohi");
    }

    public void sortByPriceHighToLow() {

        wait.until(
                ExpectedConditions.visibilityOf(sortDropdown)
        );

        new Select(sortDropdown)
                .selectByValue("hilo");
    }

    // =========================================================
    // ADD BACKPACK
    // =========================================================

    public void addBackpackToCart() {

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.id("add-to-cart-sauce-labs-backpack")
                        )
                );

        button.click();

        System.out.println(
                "Backpack added to cart"
        );
    }

    // =========================================================
    // ADD BIKE LIGHT
    // =========================================================

    public void addBikeLightToCart() {

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.id("add-to-cart-sauce-labs-bike-light")
                        )
                );

        button.click();

        System.out.println(
                "Bike Light added to cart"
        );
    }

    // =========================================================
    // ADD BOLT T-SHIRT
    // =========================================================

    public void addBoltTshirtToCart() {

        WebElement button =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.id("add-to-cart-sauce-labs-bolt-t-shirt")
                        )
                );

        button.click();

        System.out.println(
                "Bolt T-Shirt added to cart"
        );
    }

    // =========================================================
    // CLICK CART
    // =========================================================

    public void clickCart() {

        WebElement cart =
                wait.until(
                        ExpectedConditions.elementToBeClickable(
                                By.cssSelector(
                                        "a.shopping_cart_link"
                                )
                        )
                );

        try {

            cart.click();

        } catch (Exception e) {

            System.out.println(
                    "Normal cart click failed. " +
                    "Using JavaScript click."
            );

            JavascriptExecutor js =
                    (JavascriptExecutor) driver;

            js.executeScript(
                    "arguments[0].click();",
                    cart
            );
        }

        wait.until(
                ExpectedConditions.urlContains(
                        "cart.html"
                )
        );

        System.out.println(
                "Cart opened"
        );
    }
}