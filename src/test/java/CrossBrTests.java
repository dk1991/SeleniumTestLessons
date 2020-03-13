import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.AssertJUnit.assertTrue;

public class CrossBrTests {
    WebDriver driver;
    final String SITE_URL = "https://testingcup.pgs-soft.com/";
    final String USER_PROFILE_LINK = "https://testingcup.pgs-soft.com/task_6/logged";
    Set<Cookie> cookies;

    @BeforeClass
    @Parameters("browser")
    public void initialization(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.setHeadless(true);
            driver = new ChromeDriver(options);
        }

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // ожидание элемента на странице(если не виден)
        driver.get(SITE_URL);
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                driver.manage().addCookie(cookie);
            }
        }
    }

    @AfterClass
    public void teardown() {
        driver.quit();
    }

    @Test(description = "Login and see logout link", priority = 1) // приоритет теста - каким по счёту выполнится
    public void testOne() throws InterruptedException {
        driver.findElement(By.linkText("Zadanie 6")).click();
        sleep(1000);
        driver.findElement(By.id("LoginForm__username")).sendKeys("tester");
        sleep(1000);
        driver.findElement(By.name("LoginForm[_password]")).sendKeys("123-xyz");
        sleep(1000);
        driver.findElement(By.cssSelector(".btn-default.btn")).click();
        sleep(1000);
        assertTrue(driver.findElement(By.id("logout")).isDisplayed());
        cookies = driver.manage().getCookies();
    }

    @Test(description = "Use cookies from previous test and go to profile. Check if logout link is displayed",
            priority = 2)
    public void loginWithoutLogin() throws InterruptedException {
        driver.get(USER_PROFILE_LINK);
        sleep(1000);
        assertTrue(driver.findElement(By.id("logout")).isDisplayed());
        driver.findElement(By.id("logout")).click();
    }

    @Test(description = "Use cookies from first test to go to profile and logout. Check logout link",
            priority = 3)
    public void loginSecondAfterLogout() throws InterruptedException {
        driver.get(USER_PROFILE_LINK);
        sleep(1000);
        assertTrue(driver.findElements(By.id("logout")).isEmpty());
    }
}
