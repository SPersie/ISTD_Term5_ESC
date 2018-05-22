package CC7;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.ThreadLocalRandom;


public class MonkeyTestISTD {
    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "/Users/study/Desktop/HW4/geckodriver");
        WebDriver driver =new FirefoxDriver();

        driver.get("https://istd.sutd.edu.sg");

        driver.manage().window().maximize();

        while (true) {
            //get all the links
            java.util.List<WebElement> links =driver.findElements(By.tagName("a"));
            System.out.println(links.size());

            //select a random URL
            int choice = ThreadLocalRandom.current().nextInt(0, links.size() -1);

            try {
                driver.navigate().to(links.get(choice).getAttribute("href"));
                Thread.sleep(3000);
            } catch (Exception e) {

            }
        }
    }

}
