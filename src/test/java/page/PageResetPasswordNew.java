package page;

import org.openqa.selenium.WebDriver;

public class PageResetPasswordNew extends PageBase {

    public PageResetPasswordNew(WebDriver webDriver) {
        super(webDriver);
    }


    public void submitNewPassword(String newPassword) {
        //newPasswordField.sendKeys(newPassword);
        //confirmNewPasswordField.sendKeys(newPassword);
        //resetPasswordSubmitButton.click();
    }
}
