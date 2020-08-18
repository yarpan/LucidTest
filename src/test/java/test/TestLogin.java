package test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.PageLogin;
import page.PageResetPassword;
import static java.lang.Thread.sleep;

public class TestLogin extends TestBase {

    @DataProvider
    public Object[][] validLoginDataProvider() {
        return new Object[][]{
                { "lucid.pro.test@gmail.com", "Testuserp@ssw0rd" },
                //{ "lucid_test_moder@outlook.com", "2biteyka4ME" },
                //{ "lucid.pro.test@gmail.com", "Mailforautotests" },
                //{ "LUCID.PRO.TEST@GMAIL.COM", "Mailforautotests" },
        };
    }

    @Test(dataProvider = "validLoginDataProvider")
    public void successLoginTest(String email, String password) {

        PageLogin pageLogin = new PageLogin(webDriver);
        Assert.assertEquals(pageLogin.getCurrentUrl(), baseUrl + "/user/login", "Login page URL is wrong");
        Assert.assertEquals(pageLogin.getCurrentTitle(), "Вход в кабинет | LUCID", "Login page title is wrong");

        pageLogin.login(email, password);
        Assert.assertEquals(pageLogin.getCurrentUrl(), baseUrl + "/projects/current", "Login page URL is wrong");
        Assert.assertEquals(pageLogin.getCurrentTitle(), "Мои визиты | LUCID", "Login page title is wrong");

        pageLogin.logout();
    }



    @AfterMethod
    public void after() {
        //webDriver.close();
        webDriver.quit();
    }



}
