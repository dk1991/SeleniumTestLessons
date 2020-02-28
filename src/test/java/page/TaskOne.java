package page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class TaskOne extends BasePage {
    public TaskOne(WebDriver driver) {
        super(driver);
    }

    public void checkPageIsCorrect() {
        isElementDisplayed(By.xpath("//li[text()='Zadanie 1']"));
    }

    public void scrollToElement(String element) {
        //driver.findElement(By.xpath("//h4[text()='Kostka']"));

        // 1 способ
//        WebElement element1 = driver.findElement(By.xpath("//h4[text()='" + element + "']"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(element1);
//        actions.perform();


        // 2 способ - Скролл в самый низ страницы
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,250)", "element2");
    }
}
