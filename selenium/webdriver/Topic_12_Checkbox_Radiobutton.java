package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_12_Checkbox_Radiobutton {
    WebDriver driver;
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
    }

    @Test
    public void TC_01_Checkbox() {
        driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
        SleepInseccond(5);
        //Click to Dual-zone air conditioning checkbox
        By Rearcheckbox = By.xpath("//input[@id='eq1']");
        By Dualcheckbox = By.xpath("//input[@id='eq5']");
        //WebElement Rearcheckbox = driver.findElement(By.xpath("//label[text()='Rear side airbags']"));
        //WebElement Dualcheckbox = driver.findElement(By.xpath("//label[text()='Dual-zone air conditioning']"));
        checkbox(Rearcheckbox);
        checkbox(Dualcheckbox);
        //Verify that Dual-zone air conditioning checkbox is clicked;
        Assert.assertTrue(driver.findElement(Rearcheckbox).isSelected());
        Assert.assertTrue(driver.findElement(Dualcheckbox).isSelected());

        uncheckbox(Rearcheckbox);
        uncheckbox(Dualcheckbox);
        //Verify that Dual-zone air conditioning checkbox us clicked;
        Assert.assertFalse(driver.findElement(Rearcheckbox).isSelected());
        Assert.assertFalse(driver.findElement(Dualcheckbox).isSelected());
    }

    @Test
    public void TC_02_Radiobutton() {
    driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
    SleepInseccond(5);

    //By Radio36 = By.xpath("//label[text()='3.6 Petrol, 191kW']/preceding-sibling::span/input");
    By Radio20 = By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::span/input");

       // Radiocheck(Radio36);
       // Assert.assertFalse(driver.findElement(Radio20).isSelected());
        //Assert.assertTrue(driver.findElement(Radio36).isSelected());

        Radiocheck(Radio20);
        //Assert.assertFalse(driver.findElement(Radio36).isSelected());
        Assert.assertTrue(driver.findElement(Radio20).isSelected());
    }
@Test
public void TC_03_AllCheckbox(){
        driver.get("https://automationfc.github.io/multiple-fields/");
        List<WebElement> AllCheckbox = driver.findElements(By.xpath("//div[@class='form-single-column']//input[@type='checkbox']"));
        //Chon het tat ca checkbox
        for(WebElement checkbox: AllCheckbox){
            if (!checkbox.isSelected()){
                checkbox.click();
                SleepInseccond(1);
            }
        }
        //Verify
    for(WebElement checkbox: AllCheckbox){
        Assert.assertTrue(checkbox.isSelected());
        }

    driver.manage().deleteAllCookies();
    driver.navigate().refresh();
    for (WebElement checkbox: AllCheckbox){
        if (checkbox.getAttribute("value").equals("Heart Disease")&&!checkbox.isSelected()){
            checkbox.click();
            SleepInseccond(1);
        }
    }
    for (WebElement checkbox: AllCheckbox){
        if (checkbox.getAttribute("value").equals("Heart Disease")){
        Assert.assertTrue(checkbox.isSelected());
        }
        else {
            Assert.assertFalse(checkbox.isSelected());
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

    public void checkbox(By byXpath) {
        if (!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            SleepInseccond(2);
        }
    }

    public void uncheckbox(By byXpath) {
        if (driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            SleepInseccond(2);
        }
    }

    public void Radiocheck(By byXpath) {
        if (!driver.findElement(byXpath).isSelected()) {
            driver.findElement(byXpath).click();
            SleepInseccond(2);
        }
    }
}


