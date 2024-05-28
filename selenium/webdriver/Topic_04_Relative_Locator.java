package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.locators.RelativeLocator;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_04_Relative_Locator {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");
    @BeforeClass
    public void beforeClass(){
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }

        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
    }
    @Test
    public void TC_01_Relative() {
        //login button
        By loginButton = By.cssSelector("button.login-button");
        //remembermecheckbox
        By remembermecheckbox = By.id("RememberMe");
        //forgotpassword
        WebElement forgotpassword = driver.findElement(By.cssSelector("span.forgot-password"));
        //textbox password
        By textboxPassword = By.cssSelector("input#Password");
        //RememberMe text
        WebElement RememberMeText = driver.findElement(RelativeLocator.with(By.tagName("label"))
                .above(loginButton)
                .toRightOf(remembermecheckbox)
                .toLeftOf(forgotpassword)
                .below(textboxPassword)
                .near(forgotpassword));
        System.out.println(RememberMeText.getText());
    }
    @Test
    public void TC_02_() {

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
