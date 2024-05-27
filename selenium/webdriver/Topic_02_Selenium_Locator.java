package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class Topic_02_Selenium_Locator {
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
        driver.get("https://demo.nopcommerce.com/register");
    }
    @Test
    public void TC_01_ID() {
    driver.findElement(By.id("FirstName"));
    //System.out.println(driver.findElement(By.id("FirstName")));
    }
    @Test
    public void TC_02_Class() {
    driver.findElement(By.className("header-logo"));
    }
    @Test
    public void TC_03_Name() {
    driver.findElement(By.name("DateOfBirthDay"));
    }
    @Test
    public void TC_04_Tagname() {
        driver.findElements(By.tagName("input"));
    }
    @Test
    public void TC_05_Linktext() {
    driver.findElement(By.linkText("Shipping & returns"));
    }
    @Test
    public void TC_06_Partial_Linktext() {
    driver.findElement(By.partialLinkText("vendor account"));
    }
    @Test
    public void TC_07_Css() {
//css với id
        driver.findElement(By.cssSelector("input[id='LastName']"));
        driver.findElement(By.cssSelector("input#LastName"));
        driver.findElement(By.cssSelector("#LastName"));
        //css với class
        driver.findElement(By.cssSelector("div[class='page-title']"));
        //driver.findElement(By.cssSelector("class#page-title"));
        //driver.findElement(By.cssSelector("#page-title"));
        //css với name
        driver.findElement(By.cssSelector("input[name='LastName']"));
        //css với tagname
        driver.findElement(By.cssSelector("input"));
        //css với link
        driver.findElement(By.cssSelector("a[href='/customer/addresses']"));
        //css với partial link
        driver.findElement(By.cssSelector("a[href*='addresses']"));
        //driver.findElement(By.cssSelector("a[href^='addresses']"));
        //driver.findElement(By.cssSelector("a[href$='addresses']"));
    }
    @Test
    public void TC_08_Xpath() {
        //xpath với id
        driver.findElement(By.xpath("//input[@id='LastName']"));
        //css với class
        driver.findElement(By.xpath("//div[@class='page-title']"));
        //css với name
        driver.findElement(By.xpath("//input[@name='LastName']"));
        //css với tagname
        driver.findElement(By.xpath("//input"));
        //css với link
        driver.findElement(By.xpath("//a[@href='/customer/addresses']"));
        driver.findElement(By.xpath("//a[text()='Addresses']"));
        //css với partial link
        driver.findElement(By.xpath("//a[contains(@href,'addresses')]"));
        //driver.findElement(By.xpath("//a[contains(@text,'Addresses')]"));
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
