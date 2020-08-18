package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import util.GMailService;

public class PageResetPassword extends PageBase {

    String resetUrl = "https://www.lucid.com.ua/ms/user/password";
    String gmailMail = "lucid.test.post@gmail.com";
    String gmailPassword = "Passw0rd_LUCID";
    public String gmailMessage;
    String emailFieldId = "edit-name";
    String resetPasswordSubmitButtonID = "edit-submit";

    public PageResetPassword(WebDriver webDriver) {
        super(webDriver);
        webDriver.get(resetUrl);
    }

    public void getNewPassword(String lucidEmail) {
        webDriver.findElement(By.id(emailFieldId)).sendKeys(lucidEmail);
        webDriver.findElement(By.id(resetPasswordSubmitButtonID)).click();
        //webDriver.close();

        GMailService gMailService = new GMailService(gmailMail, gmailPassword);
        gMailService.connect();
        gmailMessage = gMailService.waitMessage(
                "Восстановление пароля на сайте LUCID",
                lucidEmail, "robot@lucid.com.ua", 60);

    //}


    //public void navigateToEmailLink() {
        String newPasswordLink = StringUtils.substringBetween(gmailMessage,"<a href="+'"', '"'+">");

        System.out.println();
        System.out.println("newPasswordLink - "+newPasswordLink);
        webDriver.get(newPasswordLink);
    }


}
