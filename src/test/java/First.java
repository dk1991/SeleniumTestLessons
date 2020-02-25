import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class First extends TestBase {

    @Test
    public void firstTest() {
        driver.get(SITE_URL);
    }

    @Test
    public void secondTest() {
        driver.get(SITE_URL);
    }
}
