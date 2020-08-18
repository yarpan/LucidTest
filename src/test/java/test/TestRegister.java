package test;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.PageLogin;
import page.PageRegister;

public class TestRegister extends TestBase  {

    @Test
    public void registerCorrectSmoke () {

        PageLogin pageLogin = new PageLogin(webDriver);
        Assert.assertEquals(pageLogin.getCurrentUrl(), baseUrl + "/user/login", "Login page URL is wrong");
        Assert.assertEquals(pageLogin.getCurrentTitle(), "Вход в кабинет | LUCID", "Login page title is wrong");
        pageLogin.register();

        PageRegister pageRegister = new PageRegister(webDriver);
        Assert.assertEquals(pageLogin.getCurrentUrl(), baseUrl + "/mystery/register", "Login page URL is wrong");
        Assert.assertEquals(pageLogin.getCurrentTitle(), "Регистрация тайного покупателя | LUCID", "Login page title is wrong");

        pageRegister.registerCorrectSmoke();


    }


}
