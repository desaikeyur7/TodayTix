package org.TodayTix;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.NoSuchElementException;

public class Utils extends BasePage{

    public void waitForElementDisplay(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (NoSuchElementException e) {
            System.out.println("Invalid Locator OR Element is not present within the given wait time: " + by);
            throw (e);
        }
    }

    public void assertTextOfElement(String expected, By by) {
        try {
            String actual = driver.findElement(by).getText().toLowerCase();
            Assert.assertEquals(expected, actual);
        } catch (NoSuchElementException e) {
            System.out.println("Invalid Locator OR Element or Text in element is not present: " + by);
            throw (e);
        }
    }
}
