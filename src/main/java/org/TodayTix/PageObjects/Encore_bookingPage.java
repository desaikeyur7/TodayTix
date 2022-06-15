package org.TodayTix.PageObjects;

import org.TodayTix.Utils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class Encore_bookingPage extends Utils {
    Utils utils = new Utils();

    // Locators
    String selectDate;
    private By _calendarWidget = By.cssSelector("[class='full-fat-calendar__calendar full-fat-calendar__calendar--from-price-mode']");
    private By _bookingTime = By.cssSelector("label[class$='full-fat-calendar__timeslot-label'] span:first-child");
    private By _getTicketsButton = By.cssSelector("[class*='full-fat-calendar__redirect-button']");

    // Methods

    public void selectTime(String bookingTimeFromApiCall) {
        List<WebElement> availableTimes = driver.findElements(_bookingTime);
        for (WebElement element : availableTimes) {
            String getTime = element.getText();
            if (getTime.equals(bookingTimeFromApiCall)) {
                element.click();
            }
        }
    }
    public void selectDateAndTimeAndProceedToPayment(String selectDate, String bookingTimeFromApiCall) {
        utils.waitForElementDisplay(_calendarWidget);
        // Taken locator to click date directly to be able to pass the date param in test
        driver.findElement(By.cssSelector("[class='flatpickr-day t-affiliate-font-color'][aria-label='"+ selectDate +"']")).isEnabled();
        driver.findElement(By.cssSelector("[class='flatpickr-day t-affiliate-font-color'][aria-label='"+ selectDate +"']")).click();
        selectTime(bookingTimeFromApiCall);
        driver.findElement(_getTicketsButton).click();

    }
}
