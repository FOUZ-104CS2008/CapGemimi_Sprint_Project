package PomPages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckOut {

    WebDriver driver;
    WebDriverWait wait;

    @FindBy(id = "first-name")
    private WebElement firstName;

    @FindBy(id = "last-name")
    private WebElement lastName;

    @FindBy(id = "postal-code")
    private WebElement postalCode;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

    public CheckOut(WebDriver driver) {

        this.driver = driver;

        wait = new WebDriverWait(
                driver,
                Duration.ofSeconds(15)
        );

        PageFactory.initElements(driver, this);
    }

  
    private void enterText(
            WebElement element,
            String value,
            String fieldName) {

        WebElement input =wait.until( ExpectedConditions.visibilityOf(element));
         

        JavascriptExecutor js =
                (JavascriptExecutor) driver;

        js.executeScript(
                "arguments[0].scrollIntoView({block:'center'});",
                input
        );

        // First try normal Selenium
        input.click();

        input.sendKeys(
                Keys.CONTROL,
                "a"
        );

        input.sendKeys(value);

        String currentValue =
                input.getAttribute("value");

        System.out.println(
                fieldName +
                " after Selenium: [" +
                currentValue +
                "]"
        );

        // React fallback
        if (!value.equals(currentValue)) {

            System.out.println(fieldName +" Selenium input failed. " +"Using React setter.");
      

            js.executeScript(

                    "var input = arguments[0];" +
                    "var value = arguments[1];" +

                    "var prototype = " +
                    "window.HTMLInputElement.prototype;" +

                    "var setter = " +
                    "Object.getOwnPropertyDescriptor(" +
                    "prototype, 'value').set;" +

                    "var tracker = input._valueTracker;" +

                    "if (tracker) {" +
                    "    tracker.setValue('');" +
                    "}" +

                    "setter.call(input, value);" +

                    "input.dispatchEvent(" +
                    "new Event('input', {bubbles:true})" +
                    ");" +

                    "input.dispatchEvent(" +
                    "new Event('change', {bubbles:true})" +
                    ");",

                    input,
                    value
            );
        }

        // Verify value
        wait.until(ExpectedConditions.attributeToBe(
                
                        input,
                        "value",
                        value
                )
        );

        String finalValue =
                input.getAttribute("value");

        System.out.println(fieldName +" final value: [" +finalValue +"]");

        if (!value.equals(finalValue)) {

            throw new RuntimeException(fieldName +" was not entered correctly. " +"Expected: [" +value +"] " +"Actual: [" +finalValue +"]");

        }
    }

 

    public void enterFirstName(String name) {

        enterText(firstName,name,"First Name");
    }


    public void enterLastName(String name) {

        enterText( lastName,name,"Last Name");
  
    }

  
    public void enterPostalCode(String code) {

        enterText(postalCode,code,"Postal Code");
       
    }



    public void clickContinue() {

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue")));
        button.click();

        System.out.println( "Continue button clicked");
              
      
    }



    public String getPageTitle() {

        return wait.until(ExpectedConditions.visibilityOf(pageTitle)).getText();
                
                       
     
    }


    public String getErrorMessage() {

        return wait.until(ExpectedConditions.visibilityOf(errorMessage)).getText();
     
    }
}