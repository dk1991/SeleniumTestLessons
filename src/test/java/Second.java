import org.junit.jupiter.api.Test;

public class Second extends TestBase {

    @Test
    public void firstTest() {
        driver.get(SITE_URL);
    }

    @Test
    public void secondTest() {
        driver.get(SITE_URL);
    }
}
