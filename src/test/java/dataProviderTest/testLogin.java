package dataProviderTest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class testLogin {
    WebDriver driver;
    String SITE_URL = "https://testingcup.pgs-soft.com/task_6";

    @BeforeMethod
    public void start() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // инициализация объекта для Chrome драйвера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); // ожидание элемента на странице(если не виден)
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS); // ожидание загрузки страницы
    }

    @AfterMethod
    public void finish() {
        driver.quit();
    }

    @DataProvider(name = "LoginDataProvider")
    public Object[][] getData() {
        Object[][] data = {{"login_1","password_1"},{"login_2","password_2"},{"login_3","password_3"}};
        return data;
    }

    @Test(dataProvider = "LoginDataProvider")
    public void incorrectLogin(String login, String password) {
        driver.get(SITE_URL);
//        driver.findElement(By.xpath("//h2[text()='Zadanie " + 6 + "']")).click();
//        try {
//            sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        driver.findElement(By.id("LoginForm__username")).sendKeys(login);
        driver.findElement(By.name("LoginForm[_password]")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        //assertFalse(driver.findElements(By.linkText("Pobierz plik")).size() > 0);
        assertEquals("Nieprawidłowe dane logowania", driver.findElement(By.className("alert-danger")).getText());
    }

    @DataProvider(name = "LoginExcelDataProvider")
    public Object[][] excelData() throws IOException {
        return new ReadXls().readExcel();
    }
    @Test(dataProvider = "LoginExcelDataProvider")
    public void incorrectCredentials(String login, String password) {
        driver.get(SITE_URL);

        driver.findElement(By.id("LoginForm__username")).sendKeys(login);
        driver.findElement(By.name("LoginForm[_password]")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        assertEquals("Nieprawidłowe dane logowania", driver.findElement(By.className("alert-danger")).getText());
    }

    @Test(dataProvider = "LoginExcelDataProvider2", dataProviderClass = ReadXls.class)
    public void incorrectCredentials2(String login, String password) {
        driver.get(SITE_URL);

        driver.findElement(By.id("LoginForm__username")).sendKeys(login);
        driver.findElement(By.name("LoginForm[_password]")).sendKeys(password);
        driver.findElement(By.xpath("//button[text()='Login']")).click();
        assertEquals("Nieprawidłowe dane logowania", driver.findElement(By.className("alert-danger")).getText());
    }
}
