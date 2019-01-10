package Frontend_uitest;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class uitestTest {

    static WebDriver driver;
    String username = "LucTestje";
    String password = "LucTestje";

    @BeforeClass
    public static void oneTimeSetup() {
        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
        System.setProperty("webdriver.gecko.driver", "D:/Downloads/geckodriver-v0.21.0-win64/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.navigate().to("http://localhost:8080/#/webAuth");
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void register() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/button")).click();
        Thread.sleep(300);
        // account name
        driver.findElement(By.xpath("//*[@id=\"inputSignupName\"]")).sendKeys(username);
        Thread.sleep(300);
        // username
        driver.findElement(By.xpath("//*[@id=\"inputSignupUsername\"]")).sendKeys(username);
        Thread.sleep(300);
        // email
        driver.findElement(By.xpath("//*[@id=\"inputSignupEmail\"]")).sendKeys("test@test.nl");
        Thread.sleep(300);
        // password
        driver.findElement(By.xpath("//*[@id=\"inputSignupPassword\"]")).sendKeys(password);
        Thread.sleep(300);
        // password confirmation
        driver.findElement(By.xpath("//*[@id=\"inputPasswordConfirmation\"]")).sendKeys(password);
        Thread.sleep(300);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/form/button")).click();
        Thread.sleep(300);
        String result;
        result = driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/div")).getText();
        System.out.println(result);
        if(result.contains("successfully")) {
            assertTrue(true);
        }
        else {
            setupInCaseOfFail();
            fail();
        }

    }

    public void setupInCaseOfFail() {
        driver.close();
//        System.setProperty("webdriver.gecko.driver", "D:/Downloads/geckodriver-v0.21.0-win64/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.navigate().to("http://localhost:8080/#/webAuth");
    }

    @Test
    public void login() throws InterruptedException {
        driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/ul/li[1]/a")).click();
        Thread.sleep(300);
        driver.findElement(By.xpath("/html/body/div[1]/nav/div/div/ul/li[2]/a")).click();
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"inputUsername\"]")).sendKeys(username);
        Thread.sleep(300);
        driver.findElement(By.xpath("//*[@id=\"inputPassword\"]")).sendKeys(password);
        Thread.sleep(300);
        driver.findElement(By.xpath("/html/body/div[1]/div/div/div/div/div/form/button")).click();
        Thread.sleep(300);
        String result = driver.findElement(By.xpath("/html/body/div[1]/div/h1")).getText();
        if(result.contains("about")) {
            assertTrue(true);
        }
        else {
            fail();
        }
    }

    @After
    public void tearDown() throws Exception {

    }
}