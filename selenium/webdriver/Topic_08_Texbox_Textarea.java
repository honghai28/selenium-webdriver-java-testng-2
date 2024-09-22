package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Topic_08_Texbox_Textarea {
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
    public void Login_01_Empty_Email_And_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        SleepInseccond(3);
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(),"This is a required field.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(),"This is a required field.");
    }
    @Test
    public void Login_02_Invalid_Email() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        SleepInseccond(3);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hai@123.345");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Abc123");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(),"Please enter a valid email address. For example johndoe@domain.com.");
    }
    @Test
    public void Login_03_Invalid_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        SleepInseccond(3);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hai@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Abc");
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(),"Please enter 6 or more characters without leading or trailing spaces.");

    }
    @Test
    public void Login_04_Incorrect_Email_Or_Password() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        SleepInseccond(3);
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("hai@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Abc123456");
        driver.findElement(By.xpath("//button[@title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");
        driver.findElement(By.xpath("//input[@id='email']")).clear();
        driver.findElement(By.xpath("//input[@id='pass']")).clear();
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys("haihong@gmail.com");
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("Abc123");
        driver.findElement(By.xpath("//button[@title='Login']")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(),"Invalid login or password.");


    }
    @Test
    public void Login_05_Success() {
        driver.get("http://live.techpanda.org/");
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        SleepInseccond(3);

        //Register account
        driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
        SleepInseccond(3);
        String firstname = "Hai";
        String lastname = "Hong";
        String email =getEmailAddress() ;
        String password = "Abc123";
        String fullname = firstname + " " + lastname;

        driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstname);
        driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastname);
        driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys(password);
        driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Register']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='success-msg']//span")).getText(),"Thank you for registering with Main Website Store.");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullname + "!");
        String Contact = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(Contact.contains(fullname));
        Assert.assertTrue(Contact.contains(email));

        //Logout
        driver.findElement(By.xpath("//a[@class='skip-link skip-account']")).click();
        SleepInseccond(2);
        driver.findElement(By.xpath("//a[@title='Log Out']")).click();
        SleepInseccond(5);
        driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
        SleepInseccond(3);

        //Login account
        driver.findElement(By.xpath("//input[@id='email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@title='Login']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).getText(),"Hello, " + fullname + "!");
        Contact = driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div/following-sibling::div/p")).getText();
        Assert.assertTrue(Contact.contains(fullname));
        Assert.assertTrue(Contact.contains(email));

        //Account information
        driver.findElement(By.xpath("//a[text()='Account Information']")).click();
        SleepInseccond(3);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='firstname']")).getAttribute("value"),firstname);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='lastname']")).getAttribute("value"),lastname);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@id='email']")).getAttribute("value"),email);

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
