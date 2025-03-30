package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_14_Usser_Interaction {
    WebDriver driver;
    Actions action;
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
        action = new Actions(driver);
        //action.moveByOffset(0,0).perform();
    }
    @Test
    public void TC_01_Tooltip_Hover() throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-tooltip/");
        WebElement agetexbox = driver.findElement(By.xpath("//input[@id='age']"));
        action.moveToElement(agetexbox).perform();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),"We ask for your age only for statistical purposes.");
    }
    @Test
    public void TC_02_HoverToElement() throws InterruptedException {
        driver.get("http://www.myntra.com/");
        Thread.sleep(2000);
        WebElement element = driver.findElement(By.xpath("//a[@data-group='kids']"));
        action.moveToElement(element).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@class='desktop-categoryName' and text()='Home & Bath']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class='breadcrumbs-crumb']")).getText(),"Kids Home Bath");
    }
    @Test
    public void TC_03_HoverToElement() throws InterruptedException {
        driver.get("https://www.fahasa.com/");
        //WebElement element = driver.findElement(By.xpath("//span[@class='icon_menu']"));
        //action.moveToElement(element).perform();

        action.moveToElement(driver.findElement(By.xpath("//span[@class='icon_menu']"))).perform();
        Thread.sleep(2000);
        action.moveToElement(driver.findElement(By.xpath("//span[@class='menu-title' and text()='Đồ Chơi']/parent::a[@title='Đồ Chơi']"))).perform();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//div[@class='fhs_column_stretch']//a[text()='Ô Tô']")).click();
        Thread.sleep(2000);
        Assert.assertEquals(driver.findElement(By.xpath("//ol[@class='breadcrumb']/li[4]/strong")).getText(),"Ô Tô - Xe Buýt");
    }
    @Test
    public void TC_04_Click_hole_element () throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> alnumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));

        Assert.assertEquals(alnumber.size(),20);
        action.clickAndHold(alnumber.get(0)).moveToElement(alnumber.get(3)).release().perform();
        List<WebElement> alnumberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(alnumberSelected.size(),4);

        Thread.sleep(2000);
    }
    @Test
    public void TC_05_Click_random_element () throws InterruptedException {
        driver.get("https://automationfc.github.io/jquery-selectable/");
        List<WebElement> alnumber = driver.findElements(By.xpath("//ol[@id='selectable']/li"));
        Assert.assertEquals(alnumber.size(),20);
        action.keyDown(Keys.CONTROL).perform();
        action.click(alnumber.get(0)).click(alnumber.get(3)).perform();
        List<WebElement> alnumberSelected = driver.findElements(By.xpath("//ol[@id='selectable']/li[contains(@class,'ui-selected')]"));
        Assert.assertEquals(alnumberSelected.size(),2);

        Thread.sleep(2000);
    }
    @Test
    public void TC_06_Double_Click () throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        action.doubleClick(driver.findElement(By.xpath("//button[@ondblclick='doubleClickMe()']"))).perform();
        Assert.assertEquals(driver.findElement(By.xpath("")).getText(),"Hello Automation Guys!");
        Thread.sleep(2000);
    }
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
