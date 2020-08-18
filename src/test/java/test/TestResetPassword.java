package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.PageResetPassword;

public class TestResetPassword extends TestBase {
    String lucidEmail = "lucid.test.post@gmail.com";

    @Test
    public void resetPasswordTest(){
        PageResetPassword pageResetPassword = new PageResetPassword(webDriver);
        // goto reset page, assert if loaded
        pageResetPassword.getNewPassword(lucidEmail);

        // assert url
        // assert //div[@class='messages status']

        //System.out.print("********************************** /n" + pageResetPassword.gmailMessage);



    }






}
