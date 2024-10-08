package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_07_Webbrowser_Element_02 {
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
    public void TC_01_Displayed() {
    driver.get("http://live.techpanda.org/");
    driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
        SleepInseccond(3);

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

    driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        SleepInseccond(3);

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");
    }
    @Test
    public void TC_02_Enable_Disale() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
        SleepInseccond(3);

        Assert.assertEquals(driver.getTitle(), "Customer Login");

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        SleepInseccond(3);

        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");
    }
    @Test
    public void TC_03_Selected() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
        SleepInseccond(3);

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        SleepInseccond(3);

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/create/");

        driver.navigate().back();
        SleepInseccond(3);

        Assert.assertEquals(driver.getCurrentUrl(),"http://live.techpanda.org/index.php/customer/account/login/");

        driver.navigate().forward();
        SleepInseccond(3);

        Assert.assertEquals(driver.getTitle(), "Create New Customer Account");

    }
    @Test
    public void TC_04_RegisterFunction() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class=\"footer\"]//a[@title=\"My Account\"]")).click();
        SleepInseccond(3);

        Assert.assertTrue(driver.getPageSource().contains("Login or Create an Account"));

        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        SleepInseccond(3);

        Assert.assertTrue(driver.getPageSource().contains("Create an Account"));

    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
    public void SleepInseccond(long timeInsecond) {
        try {
            Thread.sleep(timeInsecond*1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
