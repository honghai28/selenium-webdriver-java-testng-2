package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown {
    WebDriver driver;
    WebDriverWait explicitWait;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    @BeforeClass
    public void beforeClass() {
        if (osName.contains("Windows")) {
            System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
        } else {
            System.setProperty("webdriver.gecko.driver", projectPath + "/browserDrivers/geckodriver");
        }
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    @Test
    public void TC_01_Custom_Dropdown() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        driver.findElement(By.cssSelector("span#number-button")).click();
        SleepInseccond(5);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@id='number-menu']//div")));
        List<WebElement> alItems = driver.findElements(By.xpath("//ul[@id='number-menu']//div"));
        for (WebElement item : alItems){
          String textitem =  item.getText();
          if(textitem.equals(15)){
              item.click();
              break;
          }
        }
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }

    public void SleepInseccond(long timeInsecond) {
        try {
            Thread.sleep(timeInsecond * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
