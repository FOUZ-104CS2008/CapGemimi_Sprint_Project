package PomPages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    WebDriver driver;

    @FindBy(id = "user-name")
    private WebElement usernameTextField;

    @FindBy(id = "password")
    private WebElement passwordTextField;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {

        this.driver = driver;

        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {

        usernameTextField.clear();

        usernameTextField.sendKeys(username);
    }

    public void enterPassword(String password) {

        passwordTextField.clear();

        passwordTextField.sendKeys(password);
    }

    public void clickLogin() {

        loginButton.click();
    }

    public String getErrorMessage() {

        return errorMessage.getText();
    }

    public void login(String username, String password) {

        enterUsername(username);

        enterPassword(password);

        clickLogin();
    }
}