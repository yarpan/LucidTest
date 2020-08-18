package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class PageRegister extends PageBase {

    public PageRegister(WebDriver webDriver) {
        super(webDriver);
    }

    public void registerCorrectSmoke () {

        webDriver.findElement(By.id("edit-mail")).sendKeys("vedmedyar+17@gmail.com");
        webDriver.findElement(By.id("edit-pass-pass1")).sendKeys("123");
        webDriver.findElement(By.id("edit-pass-pass2")).sendKeys("123");

        System.out.println(webDriver.findElement(By.xpath("//span[@class='ok']")).getText());
        Assert.assertEquals(webDriver.findElement(By.xpath("//span[@class='ok']")).getText(), "да");

        webDriver.findElement(By.id("edit-profile-mystery-shopper-field-last-name-und-0-value")).sendKeys("Фамилия");
        webDriver.findElement(By.id("edit-profile-mystery-shopper-field-first-name-und-0-value")).sendKeys("Имя");
        webDriver.findElement(By.id("edit-profile-mystery-shopper-field-patronymic-und-0-value")).sendKeys("Отчество");

        WebElement radioSexM = webDriver.findElement(By.id("edit-profile-mystery-shopper-field-gender-und-m"));
        WebElement radioSexF = webDriver.findElement(By.id("edit-profile-mystery-shopper-field-gender-und-f"));
        radioSexM.click();
        //if radioSexM.isSelected()

        webDriver.findElement(By.id("edit-profile-mystery-shopper-field-birthday-date-und-0-value-datepicker-popup-0")).sendKeys("01.01.2000");
        webDriver.findElement(By.id("edit-profile-mystery-shopper-field-phone-1-und-0-value")).sendKeys("0991234567");

        Select phoneType1 = new Select(webDriver.findElement(By.id("edit-profile-mystery-shopper-field-phone-type-1-und")));
        phoneType1.selectByVisibleText("мобильный");

        new Select(webDriver.findElement(By.id("edit-profile-mystery-shopper-field-city-live-und-0-tid-select-1"))).selectByVisibleText("АР Крым");
        new Select(webDriver.findElement(By.id("edit-profile-mystery-shopper-field-city-live-und-0-tid-select-2"))).selectByValue("7");

        //webDriver.findElement(By.id("edit-profile-mystery-shopper-field-address-und-0-value")).sendKeys("Адрес (улица, дом)");
        webDriver.findElement(By.id("edit-profile-mystery-shopper-field-address-live-und-0-value")).sendKeys("Адрес (улица, дом)");
        webDriver.findElement(By.id("edit-profile-mystery-shopper-field-address-equal-und")).click();

        new Select(webDriver.findElement(By.id("edit-profile-mystery-shopper-field-recorder-und"))).selectByVisibleText("нет");
        new Select(webDriver.findElement(By.id("edit-profile-mystery-shopper-field-photo-und"))).selectByValue("camera_more_5Mpx");

        new Select(webDriver.findElement(By.id("edit-profile-mystery-shopper-field-work-experience-und"))).selectByValue("1-3");
        webDriver.findElement(By.id("edit-profile-mystery-shopper-field-agencies-und-0-value")).sendKeys("В каких агентствах (через запятую)");
        webDriver.findElement(By.id("edit-profile-mystery-shopper-field-branches-und-0-value")).sendKeys("В каких отраслях (через запятую)");

        webDriver.findElement(By.id("edit-profile-mystery-shopper-field-agreement-und")).click();
        //webDriver.findElement(By.xpath("//input[@type='button' and contains(@class,'multipage-link-next') and @style != 'display: none;']")).click();
        webDriver.findElement(By.xpath("//input[@type='button' and @value = 'Далее']")).click();
        //webDriver.findElement(By.id("edit-submit")).click();












    }




}
