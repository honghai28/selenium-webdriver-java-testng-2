package webdriver;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_13_HandleAlert_AuthenticationAlert {
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
    }
    @Test
    public void TC_01_AcceptAlert() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    SleepInseccond(5);
    //1.Click to JS Alert button
    driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
    //2. Verify that message display in Alert is "I am ..."
     Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS Alert");
        alert.accept();

        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");
    }
    @Test
    public void TC_02_ConfirmAlert() {
    driver.get("https://automationfc.github.io/basic-form/index.html");
    SleepInseccond(5);
    driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
    Alert alert = driver.switchTo().alert();
    Assert.assertEquals(alert.getText(),"I am a JS Confirm");
    alert.dismiss();
    Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked: Cancel");
    }
    @Test
    public void TC_03_PromptAlert() {
        String texttosend = "Hong hai";
        driver.get("https://automationfc.github.io/basic-form/index.html");
        SleepInseccond(5);
        driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
        Alert alert = driver.switchTo().alert();
        Assert.assertEquals(alert.getText(),"I am a JS prompt");
        alert.sendKeys(texttosend);
        alert.accept();
        Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: " + texttosend);
    }
    @Test
    public void TC_04_AuthenticationAlert() {
        String ussername = "admin" ;
        String password = "admin";
        driver.get("http://" + ussername + ":" + password +"@" + "the-internet.herokuapp.com/basic_auth");
        SleepInseccond(5);
       Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']/p")).getText(),"Congratulations! You must have the proper credentials.");


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
