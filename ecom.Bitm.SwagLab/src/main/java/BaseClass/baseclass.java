package BaseClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class baseclass {

    protected WebDriver driver;
    protected Properties p;

    @BeforeMethod(alwaysRun=true)
    @Parameters("browser")
    public void openBrowser(@Optional String browser) throws IOException, InterruptedException {
        Thread.sleep(3000);

        FileInputStream fis =
                new FileInputStream(
                        "./src/test/resources/Properties/commondata.properties");

        p = new Properties();
        p.load(fis);

        if (browser == null) {
            browser = p.getProperty("browser");
        }

        if (browser.equalsIgnoreCase("chrome")) {

            driver = new ChromeDriver();

        } else if (browser.equalsIgnoreCase("edge")) {

            driver = new EdgeDriver();

        } else {

            throw new RuntimeException("Invalid browser: " + browser);
        }

        driver.manage().window().maximize();

        driver.get(p.getProperty("url"));

        fis.close();

        System.out.println("Browser launched: " + browser);
    }

    // Screenshot method
    public void takeScreenshot(String fileName) throws IOException {

        TakesScreenshot ts = (TakesScreenshot) driver;

        File source = ts.getScreenshotAs(OutputType.FILE);

        File destination =
                new File("./screenshots/" + fileName + ".png");

        FileUtils.copyFile(source, destination);

        System.out.println(
                "Screenshot saved: " + destination.getAbsolutePath()
        );
    }

    @AfterMethod(alwaysRun=true)
    public void closeBrowser() {

        if (driver != null) {
            driver.quit();
        }

        System.out.println("Browser closed");
    }
}