import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TestBase {
    String SITE_URL = "https://mvnrepository.com/";
    WebDriver driver;

    // Выполнится перед каждым тестовым методом
    @BeforeEach
    public void start() {
        // проверяем ОС, выбираем тип драйвера Chrome и скачиваем последнюю версию драйвера
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(); // инициализация объекта для Chrome драйвера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS); // ожидание элемента на странице(если не виден)
    }

    // Выполнится после каждого тестового метода
    @AfterEach
    public void finish() {
        driver.quit();
    }
}
