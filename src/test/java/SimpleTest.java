import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SimpleTest {
    final String SITE_URL = "https://testingcup.pgs-soft.com/";
    WebDriver driver;

    // Выполнится перед каждым тестовым методом
    @BeforeEach
    public void start() {
        // проверяем ОС, выбираем тип драйвера Chrome и скачиваем последнюю версию драйвера
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.setHeadless(true);
        driver = new ChromeDriver(options); // инициализация объекта для Chrome драйвера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // ожидание элемента на странице(если не виден)
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(SITE_URL);
    }

    // Выполнится после каждого тестового метода
    @AfterEach
    public void finish() {
        driver.quit();
    }

    @Test
    public void testSixCheckLoginWithCorrectCredentials() {
        driver.findElement(By.linkText("Zadanie 6")).click();
        driver.findElement(By.id("LoginForm__username")).sendKeys("tester");
        driver.findElement(By.name("LoginForm[_password]")).sendKeys("123-xyz");
        driver.findElement(By.id("LoginForm_save")).click();
        assertTrue(driver.findElements(By.linkText("Pobierz plik")).isEmpty());
    }

    @Test
    public void testSixCheckLoginWithIncorrectCredentials() throws InterruptedException {
        driver.findElement(By.linkText("Zadanie 6")).click();
        sleep(2000);
        driver.findElement(By.id("LoginForm__username")).sendKeys("tester");
        sleep(2000);
        driver.findElement(By.name("LoginForm[_password]")).sendKeys("12,4-xyz");
        sleep(2000);
        driver.findElement(By.id("LoginForm_save")).click();
        assertTrue(driver.findElements(By.linkText("Pobierz plik")).isEmpty());
    }
}
