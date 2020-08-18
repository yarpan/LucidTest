package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import java.util.concurrent.TimeUnit;


public class TestBase {
    WebDriver webDriver;
    String baseUrl = "https://www.lucid.com.ua/ms";


    @Parameters({"browserType", "envURL"})
    @BeforeMethod
    public void beforeTest(@Optional("chrome") String browserType,
                           @Optional("https://www.lucid.com.ua/ms") String envURL) {

        switch (browserType.toLowerCase()){
            case "firefox" :
                WebDriverManager.firefoxdriver().setup();
                webDriver = new FirefoxDriver();
                break;
            case "chrome" :
                WebDriverManager.chromedriver().setup();
                webDriver = new ChromeDriver();
                break;
            default :
                WebDriverManager.iedriver().setup();
                webDriver = new InternetExplorerDriver();
        }
        //System.setProperty("webdriver.chrome.driver", "D:\\OneDrive\\CODE\\Selenium_Drivers\\chromedriver.exe");
        //webDriver = new ChromeDriver();
        //webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        webDriver.manage().window().maximize();
        webDriver.navigate().to(envURL);
        //linkedinLoginPage = new LinkedinLoginPage(webDriver);
    }


    @AfterMethod
    public void afterTest() {

        //webDriver.close();
    }
}