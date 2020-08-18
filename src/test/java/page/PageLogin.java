package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageLogin extends PageBase {
    String loginUrl = "https://www.lucid.com.ua/ms/user/login";
    String loginNameId = "edit-name";
    String loginPassId = "edit-pass";
    String submitButtonId = "edit-submit";
    String registerLinkText = "Регистрация тайного покупателя";
    String logoutButton = "//span[@class='inner']";
    //i[@class='fa fa-2x fa-sign-out']";


    public PageLogin(WebDriver webDriver) {
        super(webDriver);
        webDriver.get(loginUrl);
    }


    public void login(String email, String password) {
        webDriver.findElement(By.id(loginNameId)).sendKeys(email);
        webDriver.findElement(By.id(loginPassId)).sendKeys(password);
        webDriver.findElement(By.id(submitButtonId)).click();
    }


    public void login2(String email, String password) {
        WebElement emailField = webDriver.findElement(By.id(loginNameId));
        WebElement passlField = webDriver.findElement(By.id(loginPassId));
        WebElement submitButton = webDriver.findElement(By.id(submitButtonId));
        emailField.sendKeys(email);
        passlField.sendKeys(password);
        submitButton.click();
    }

    public void logout() {
        webDriver.findElement(By.xpath(logoutButton)).click();
    }


    public void register (){
        WebElement registerLink = webDriver.findElement(By.linkText(registerLinkText));
        registerLink.click();

    }
}
