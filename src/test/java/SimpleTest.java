import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
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

    @Epic("TESTING FOR https://testingcup.pgs-soft.com/ tasks") // общее название теста
    @Feature(value = "Test for task 6") // название теста
    @Severity(SeverityLevel.BLOCKER) // Позволяет указать уровень критичности функционала, проверяемого автотестом
    @Description("In this test we will login with correct credentials. When we logged we can see link for file") // описание теста
    @Story(value = "Test for login with correct credentials")
    @Test
    public void testSixCheckLoginWithCorrectCredentials() throws InterruptedException {
        chooseTaskSix();
        fillInLogin("tester");
        fillInPassword("123-xyz");
        clickLoginButton();
        isLoginSuccessful();
    }

    @Epic("TESTING FOR https://testingcup.pgs-soft.com/ tasks")
    @Feature(value = "Test for task 6")
    @Severity(SeverityLevel.MINOR)
    @Description("In this test we will login with incorrect credentials. We will get error because of wrong password")
    @Story(value = "Test for login with incorrect credentials")
    @Test
    public void testSixCheckLoginWithIncorrectCredentials() throws InterruptedException {
        chooseTaskSix();
        fillInLogin("tester");
        fillInPassword("124-xyw");
        clickLoginButton();
        isLoginUnsuccessful();
    }

    @Step(value = "Fill in login with {0}") // Описание шага теста. Вместо {0} подставится login из метода
    public void fillInLogin(String login) throws InterruptedException {
        sleep(1000);
        driver.findElement(By.id("LoginForm__username")).sendKeys(login);
    }

    @Step(value = "Fill in password with {0}")
    public void fillInPassword(String password) throws InterruptedException {
        sleep(1000);
        driver.findElement(By.name("LoginForm[_password]")).sendKeys(password);
    }

    @Step(value = "Click button Login")
    public void clickLoginButton() throws InterruptedException {
        sleep(1000);
        driver.findElement(By.id("LoginForm_save")).click();
    }

    @Step(value = "Choose task 6 on main page")
    public void chooseTaskSix() throws InterruptedException {
        sleep(1000);
        driver.findElement(By.linkText("Zadanie 6")).click();
    }

    @Step(value = "Login was successful")
    public void isLoginSuccessful() {
        assertTrue(driver.findElement(By.linkText("Pobierz plik")).isDisplayed());
    }

    @Step(value = "Login was not successful")
    public void isLoginUnsuccessful() {
        assertTrue(driver.findElements(By.linkText("Pobierz plik")).isEmpty());
    }
}
