package util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

    protected WebDriver webDriver;
    public Utils (WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    // waits

    // if exist

    public static void captureScreenShot(WebDriver webdriver){
        File src=((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
        DateFormat dateFormat = new SimpleDateFormat("yyMMdd_HHmmss");
        Date date = new Date();

        try {
            FileUtils.copyFile(src, new File("D:/OneDrive/CODE/Testresults/Screenshot_"+dateFormat.format(date)+".png"));}
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("Screenshot_"+dateFormat.format(date)+".png Taken");
    }

    public static void captureScreenShotName(WebDriver webdriver, String screenShotName){
        File src=((TakesScreenshot)webdriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(src, new File("D:/OneDrive/CODE/Testresults/"+screenShotName+".png"));}
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("ScreenShot "+screenShotName+".png Taken");
    }






}