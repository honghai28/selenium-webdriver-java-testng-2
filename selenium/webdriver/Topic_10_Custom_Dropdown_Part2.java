package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Topic_10_Custom_Dropdown_Part2 {
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

    //@Test
    public void TC_01_Jquery() {
        driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
        SelectItemDropdown("//span[@id='speed-button']","//ul[@id='speed-menu']//div","Slow");
        SleepInseccond(3);
        SelectItemDropdown("//span[@id='files-button']","//ul[@id='files-menu']//div","ui.jQuery.js");
        SleepInseccond(3);
        SelectItemDropdown("//span[@id='number-button']","//ul[@id='number-menu']//div","6");
        SleepInseccond(3);
        SelectItemDropdown("//span[@id='salutation-button']","//ul[@id='salutation-menu']//div","Prof.");
        SleepInseccond(3);

        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='speed-button']//span[@class='ui-selectmenu-text']")).getText(),"Slow");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='files-button']//span[@class='ui-selectmenu-text']")).getText(),"ui.jQuery.js");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),"6");
        Assert.assertEquals(driver.findElement(By.xpath("//span[@id='salutation-button']//span[@class='ui-selectmenu-text']")).getText(),"Prof.");
    }
    //@Test
    public void TC_02_React() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
        SelectItemDropdown("//div[@class='ui fluid selection dropdown']","//div[@class='visible menu transition']//span","Elliot Fu");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Elliot Fu");
        SleepInseccond(3);
        SelectItemDropdown("//div[@class='ui fluid selection dropdown']","//div[@class='visible menu transition']//span","Christian");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Christian");
        SleepInseccond(3);
    }
    //@Test
    public void TC_03_VueJS() {
        driver.get("https://mikerodham.github.io/vue-dropdowns/");
        SelectItemDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","First Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"First Option");
        SleepInseccond(3);
        SelectItemDropdown("//li[@class='dropdown-toggle']","//ul[@class='dropdown-menu']//a","Second Option");
        Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(),"Second Option");
        SleepInseccond(3);
    }
    @Test
    public void TC_04_Editable() {
        driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
        SelectItemEditableDropdown("//input[@class='search']","//div[@class='visible menu transition']//div","Bahamas");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Bahamas");
        SleepInseccond(3);
        SelectItemEditableDropdown("//input[@class='search']","//div[@class='visible menu transition']//div","Anguilla");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Anguilla");
        SleepInseccond(3);
        SelectItemEditableDropdown("//input[@class='search']","//div[@class='visible menu transition']//div","Andorra");
        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='divider text']")).getText(),"Andorra");
        SleepInseccond(3);
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
    public void SelectItemDropdown(String parent, String ChildItem, String Itemtextexpected) {
        driver.findElement(By.xpath(parent)).click();
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ChildItem)));
        List<WebElement> alItems = driver.findElements(By.xpath(ChildItem));
        for (WebElement item : alItems){
            String textitem =  item.getText();
            if(textitem.equals(Itemtextexpected)){
                item.click();
                break;
            }
        }
    }
    public void SelectItemEditableDropdown(String parent, String ChildItem, String Itemtextexpected) {
        driver.findElement(By.xpath(parent)).clear();
        driver.findElement(By.xpath(parent)).sendKeys(Itemtextexpected);
        explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(ChildItem)));
        List<WebElement> alItems = driver.findElements(By.xpath(ChildItem));
        for (WebElement item : alItems){
            String textitem =  item.getText();
            if(textitem.equals(Itemtextexpected)){
                item.click();
                break;
            }
        }
    }
}
