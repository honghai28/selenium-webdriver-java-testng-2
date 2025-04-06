package webdriver;

import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Topic_15_Frame_Iframe {
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
    public void TC_01_Fix_Popup_In_Dom() throws InterruptedException {
        driver.get("https://ngoaingu24h.vn/");
        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
        By LoginPopup = By.xpath("//div/div[@role='dialog']");
        Assert.assertTrue(driver.findElement(LoginPopup).isDisplayed());
        driver.findElement(By.xpath("//input[@placeholder='Tài khoản đăng nhập']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//input[@placeholder='Mật khẩu']")).sendKeys("automationfc");
        driver.findElement(By.xpath("//div[contains(@style,'display')]/button[contains(text(),'Đăng nhập')]")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(text(),'Bạn đã nhập sai tài khoản hoặc mật khẩu!')]")).isDisplayed());
        driver.findElement(By.xpath("//button[contains(@class,'close')]")).click();
        Thread.sleep(3000);
       //Assert.assertFalse(driver.findElement(LoginPopup).isDisplayed());
    }
    @Test
    public void TC_02_Fix_Popup_In_Dom() throws InterruptedException {
        driver.get("https://skills.kynaenglish.vn/dang-nhap");
        By Loinpopup = By.xpath("//div[@id='k-popup-account-login']");
        Assert.assertTrue(driver.findElement(Loinpopup).isDisplayed());
        driver.findElement(By.xpath("//input[@id='user-login']")).sendKeys("automation@gmail.com");
        driver.findElement(By.xpath("//input[@id='user-password']")).sendKeys("123456");
        driver.findElement(By.xpath("//button[@id='btn-submit-login']")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//div[@id='password-form-login-message']")).getText(),"Sai tên đăng nhập hoặc mật khẩu");
    }
    @Test
    public void TC_03_Fix_Popup_Not_In_Dom() throws InterruptedException {
        driver.get("https://tiki.vn/");
        driver.findElement(By.xpath("//div[@id='VIP_BUNDLE']//img[@alt='close-icon']")).click();
        driver.findElement(By.xpath("//div[@data-view-id='header_header_account_container']")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class,'ReactModal__Content')]")).isDisplayed());
        driver.findElement(By.xpath("//p[@class='login-with-email']")).click();
        driver.findElement(By.xpath("//button[contains(text(),'Đăng nhập')]")).click();
        Thread.sleep(3000);
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[1]")).getText(),"Email không được để trống");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='email']/parent::div/following-sibling::span[2]")).getText(),"Mật khẩu không được để trống");
        driver.findElement(By.xpath("//img[@class='close-img']")).click();
        Thread.sleep(3000);
        //Assert.assertFalse(driver.findElement(By.xpath("//div[contains(@class,'ReactModal__Content')]")).isDisplayed());
        Assert.assertEquals(driver.findElements(By.xpath("//div[contains(@class,'ReactModal__Content')]")).size(),0);
    }

    @Test
    public void TC_04_Random_popup() throws InterruptedException {
        driver.get("https://vnk.edu.vn/");
        By popup = By.xpath("//div[contains(@class,'pum-container popmake')]");
        if (driver.findElements(popup).size()>0 && (driver.findElements(popup)).get(0).isDisplayed()){
            driver.findElement(By.xpath("//button[@class='pum-close popmake-close']")).click();
        }
        driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//li[contains(@id,'mega-menu-item')]/a[contains(text(),'Trang chủ')]")).isDisplayed());
    }
    @Test
    public void TC_05_Random_popup_Not_In_Dom() throws InterruptedException {
        driver.get("https://dehieu.vn/");
        By popup = By.xpath("//div[@id='modalPopupForm']");
        Thread.sleep(5000);
        if (driver.findElements(popup).size()>0 && (driver.findElements(popup)).get(0).isDisplayed()){
            driver.findElement(By.xpath("//button[@class='close']")).click();
        }
        driver.findElement(By.xpath("//a[contains(text(),' Đăng nhập')]/parent::li")).click();
        Thread.sleep(3000);
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='box-login']")).isDisplayed());
    }

    @Test
    public void TC_06_Shadow_dom() throws InterruptedException {
        driver.get("https://books-pwakit.appspot.com/");
        Thread.sleep(3000);
        WebElement firstshadowrootHostElement = driver.findElement(By.xpath("//book-app"));
        SearchContext firtshadowHost = firstshadowrootHostElement.getShadowRoot();

        WebElement SecondshadowHostElement = firtshadowHost.findElement(By.cssSelector("book-input-decorator"));
        SearchContext secondshadowhost = SecondshadowHostElement.getShadowRoot();

        firtshadowHost.findElement(By.cssSelector("input#input")).sendKeys("Harry poster");
        Thread.sleep(3000);
        secondshadowhost.findElement(By.cssSelector("div.icon")).click();
        Thread.sleep(3000);

        WebElement ThirdshadowHostElement = firtshadowHost.findElement(By.cssSelector("book-explore"));
        SearchContext thirdshadowhost = ThirdshadowHostElement.getShadowRoot();

        WebElement ForthshadowHostElement = thirdshadowhost.findElement(By.cssSelector("ul>li:nth-of-type(1)>book-item"));
        SearchContext forthshadowhost = ForthshadowHostElement.getShadowRoot();

        Assert.assertEquals(forthshadowhost.findElement(By.cssSelector("h2.title")).getText(),"Harry Potter and the Half-Blood Prince");
    }
    @Test
    public void TC_06_IFRAME() throws InterruptedException {
        driver.get("https://toidicodedao.com/");
        Thread.sleep(10000);
        Assert.assertTrue(driver.findElement(By.xpath("//iframe[@data-testid='fb:page Facebook Social Plugin']")).isDisplayed());
        //Assert.assertEquals(driver.findElement(By.xpath("//div[contains(text(),'followers')]")).getText(),"402,455 followers");
    }
    /*@Test
    public void TC_07_Windown() throws InterruptedException {
        driver.get("https://automationfc.github.io/basic-form/index.html");
        //Lay ID cua windown/tab hien tai
        String GithubwindownID = driver.getWindowHandle();

        driver.findElement(By.xpath("//a[@href='https://google.com.vn']")).click();
        Thread.sleep(3000);
        Set<String> allWindownIDs = driver.getWindowHandle();
    }*/
    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
