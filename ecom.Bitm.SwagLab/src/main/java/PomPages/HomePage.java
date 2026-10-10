package PomPages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "react-burger-menu-btn")
    private WebElement hamburger;

    @FindBy(id = "inventory_sidebar_link")
    private WebElement allItems;

    @FindBy(id = "about_sidebar_link")
    private WebElement about;

    @FindBy(id = "logout_sidebar_link")
    private WebElement logout;

    @FindBy(id = "reset_sidebar_link")
    private WebElement reset;

    @FindBy(className = "shopping_cart_link")
    private WebElement cart;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        PageFactory.initElements(driver, this);
    }

    public WebElement getHamburger() {
        return hamburger;
    }

    public WebElement getAllItems() {
        return allItems;
    }

    public WebElement getAbout() {
        return about;
    }

    public WebElement getLogout() {
        return logout;
    }

    public WebElement getReset() {
        return reset;
    }

    public WebElement getCart() {
        return cart;
    }

    public void clickHamburger() {
        wait.until(
                ExpectedConditions.elementToBeClickable(hamburger)
        ).click();
    }

    public void clickAllItems() {
        wait.until(
                ExpectedConditions.visibilityOf(allItems)
        ).click();
    }

    public void clickLogout() {
        wait.until(
                ExpectedConditions.visibilityOf(logout)
        ).click();
    }
}