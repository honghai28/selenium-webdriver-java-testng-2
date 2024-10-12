package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_09_Dropdown_list {
    WebDriver driver;
    String projectPath = System.getProperty("user.dir");
    String osName = System.getProperty("os.name");

    String Firstname = "Hai";
    String Lastname = "Hong";
    String email = getEmailAddress();
    String Password = "Abc@123";
    String ConfirmPassword = "Abc@123";
    String day = "28", month = "September", year = "1998";
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
        driver.get("https://demo.nopcommerce.com/");
    }
    @Test
    public void TC_01_Register() {
        driver.findElement(By.xpath("//a[@class='ico-register']")).click();
        driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys(Firstname);
        driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys(Lastname);

        Select daydropdown = new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
        daydropdown.selectByVisibleText(day);
        //Verify dropdown nay la single (khong phai multiple)
        Assert.assertFalse(daydropdown.isMultiple());
        //Verify size cua dropdown nay la 32
        List<WebElement> dayOption = daydropdown.getOptions();
        Assert.assertEquals(dayOption.size(),32);

        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).selectByVisibleText(month);
        new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).selectByVisibleText(year);
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(Password);
        driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys(ConfirmPassword);
        driver.findElement(By.xpath("//button[@id='register-button']")).click();
        SleepInseccond(2);



    }
    @Test
    public void TC_02_Login() {
        driver.get("https://demo.nopcommerce.com/");
        SleepInseccond(10);

        driver.findElement(By.xpath("")).click();
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(Password);
        driver.findElement(By.xpath("//button[@class='button-1 login-button']")).click();
        SleepInseccond(2);

        driver.findElement(By.xpath("//a[@class='ico-account']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='FirstName']")).getAttribute("value"),Firstname);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='LastName']")).getAttribute("value"),Lastname);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']"))).getFirstSelectedOption().getText(),day);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']"))).getFirstSelectedOption().getText(),month);
        Assert.assertEquals(new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']"))).getFirstSelectedOption().getText(),year);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='Email']")).getAttribute("value"),email);

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
    public  String getEmailAddress (){
        Random rand = new Random();
        return "hai" + rand.nextInt(99999) + "@gamil.com";
    }
}
