package CC9;

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

public class LoginWithInvalidUser {

    public static void main(String[] args) throws InterruptedException {
        String[] usernames =new String[]
                {"haha", "heiehi", "xxxwef12@gmail.com", "iwuegi9", "ESCisfun", ",aieui2u", "748b88huhsjd",
                "xingxun@mymail.sut.deu.sg", "iwuh9h9", "uwyegfg87g8u", "thisisa fake", "isudhiuhnc", "trytomakeit",
                "reallycannotthinkof", "anyother", "fakeemail", "adresses", "#$^*#&^$*", "&#*G&*#&7863847g8", "finallyfone"};


        System.setProperty("webdriver.gecko.driver","/Users/study/Desktop/ESC_HW/geckodriver");

        WebDriver driver = new FirefoxDriver();

        for (int i =0; i <20; i ++) {
//            username.clear();
            driver.get("https://accounts.google.com/ServiceLogin/identifier?elo=1&flowName=GlifWebSignIn&flowEntry=AddSession");

            WebElement username =driver.findElement(By.name("identifier"));
            username.sendKeys(usernames[i]);
            username.submit();
            Thread.sleep(1000);

        }
    }
}

