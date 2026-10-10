package PomPages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    @FindBy(id = "continue-shopping")
    private WebElement continueShoppingButton;

    public AddToCart(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        PageFactory.initElements(driver, this);
    }

    public void clickCheckout() {

        wait.until(ExpectedConditions.urlContains("cart.html"));

        wait.until(ExpectedConditions.visibilityOf(checkoutButton));

        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));

        checkoutButton.click();

        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));
    }

    public void clickContinueShopping() {

        wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
                
        

        continueShoppingButton.click();
    }
}