package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.openqa.selenium.support.Color;


import java.awt.*;
import java.util.concurrent.TimeUnit;

public class Topic_11_Button_Radio_checkbox {
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
    public void TC_01_Button() {
    driver.get("https://egov.danang.gov.vn/reg");

        WebElement  registerbutton = driver.findElement(By.xpath("//input[@class='egov-button']"));

        //Verify that button is disable
        Assert.assertFalse(registerbutton.isEnabled());

        driver.findElement(By.xpath("//input[@id='chinhSach']")).click();
        //Verify that button is enable
        Assert.assertTrue(registerbutton.isEnabled());

        //Lay ra mau nen cua button
        String registerbuttonColorRGB = registerbutton.getCssValue("background-color");
        System.out.println("Background color ="+ registerbuttonColorRGB);
        Color registerbuttoncolor = Color.fromString(registerbuttonColorRGB);

        //Convert RGB to Hexa
        String registerbuttonColorHexa = registerbuttoncolor.asHex();
        System.out.println("Backgroundbutton color" + registerbuttonColorHexa);
        Assert.assertEquals(registerbuttonColorHexa,"#ef5a00");


    }
    @Test
    public void TC_02_Fahasha_Button() {
    driver.get("https://www.fahasa.com/customer/account/create");
    driver.findElement(By.xpath("//li[@class='popup-login-tab-item popup-login-tab-login']")).click();

    //Verify that button is disable
        WebElement Loginbutton = driver.findElement(By.xpath("//button[@class='fhs-btn-login']"));
        Assert.assertFalse(Loginbutton.isEnabled());
        // Verify that LoginbuttonColor is gray
        //String LoginbuttonBackgroundColor = Loginbutton.getCssValue("background-color");
        //System.out.println("Background color RGB="+ LoginbuttonBackgroundColor);
       // Color LoginbuttonBackgroundColorConvert = Color.fromString(LoginbuttonBackgroundColor);
        //String LoginbuttonBackgroundColorHexa = LoginbuttonBackgroundColorConvert.asHex();
        //System.out.println("Background color Hexa="+ LoginbuttonBackgroundColorHexa);
        //Assert.assertEquals(LoginbuttonBackgroundColorHexa,"#000000");
        Assert.assertEquals(Color.fromString(Loginbutton.getCssValue("background-color")).asHex(),"#000000");
//Input correct Email and password
        driver.findElement(By.xpath("//input[@id='login_username']")).sendKeys("haihong419@gmail.com");
        driver.findElement(By.xpath("//input[@id='login_password']")).sendKeys("Honghai2891998");
        // Verify that loginbutton is enable
        Assert.assertTrue(Loginbutton.isEnabled());
        SleepInseccond(2);
        //Verify that loginbuttonColor is red
        //String LoginbuttonBackgroundColor_enable = Loginbutton.getCssValue("background");
        //System.out.println("Background color RGB_enable ="+ LoginbuttonBackgroundColor_enable);
        //Color LoginbuttonBackgroundColorConvert_enable = Color.fromString(LoginbuttonBackgroundColor_enable);
        //String LoginbuttonBackgroundColorHexa_enable = LoginbuttonBackgroundColorConvert_enable.asHex();
        //System.out.println("Background color Hexa enable="+ LoginbuttonBackgroundColorHexa_enable);
        //Assert.assertEquals(LoginbuttonBackgroundColorHexa_enable,"#c92127");
        Assert.assertEquals(Color.fromString(Loginbutton.getCssValue("background")).asHex(),"#c92127");
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
