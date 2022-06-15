package org.TodayTix.Tests;

import io.restassured.RestAssured;
import org.TodayTix.BaseTest;
import org.TodayTix.PageObjects.Encore_bookingPage;
import org.TodayTix.PageObjects.Encore_homePage;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.*;

public class BookTickets extends BaseTest {
    private Encore_homePage encoreHomePage = new Encore_homePage();
    private Encore_bookingPage encoreBookingPage = new Encore_bookingPage();

    @Test
    public void getApi() throws Exception {

        RestAssured.get("https://inventory-service.tixuk.io/api/v4//availability/products/6362/quantity/2/from/20220410/to/20220510").equals(200);

        String[] getProductAvailability = {"curl", "-L", "GET", "http://inventory-service.tixuk.io/api/v4/availability/products/6362/quantity/2/from/20220616/to/20220630?test=98",
                "-H", "affiliateId: encoretickets"};

        // This block will get product availability for the dates passed in the above url
        ProcessBuilder processBuilder = new ProcessBuilder(getProductAvailability);
        Process process = processBuilder.start();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line2;
        while ((line2 = bufferedReader.readLine()) != null) {
            stringBuilder.append(line2);
        }

        // This block will get the response value from above payload, convert into string and get random datetime from index
        JSONObject jsonObject = new JSONObject(stringBuilder.toString());
        JSONArray productAvailabilityResponseValue = jsonObject.getJSONArray("response");
        Random r = new Random();
        int index = r.nextInt(productAvailabilityResponseValue.length()); //range is 0~jsonArray.length()
        JSONObject availableDays = productAvailabilityResponseValue.getJSONObject(index);

        String dateAndTimeFromAvailableDays = availableDays.getString("datetime");

        // This block will convert the date and time into required format fetched from dateAndTimeFromAvailableDays
        SimpleDateFormat dateFormatFromDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        SimpleDateFormat setNewFormatForDate = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat setNewFormatForTime = new SimpleDateFormat("HH:mm");
        String newDateFormat = null;
        String newTimeFormat = null;
        try {
            Date date = dateFormatFromDateTime.parse(dateAndTimeFromAvailableDays.substring(0, 19));
            newDateFormat = setNewFormatForDate.format(date);
            newTimeFormat = setNewFormatForTime.format(date);
        } catch (ParseException ex) {
            System.out.println("Exception " + ex);
        }
        String[] getAvailabilityFromDate = {"curl", "-L", "GET", "https://inventory-service.tixuk.io/api/v4/products/6362/areas?date=" + newDateFormat + "&time=" + newTimeFormat + "&quantity=2",
        };

        // This block will get availability for specific date and time and convert it to string
        ProcessBuilder processBuilder1 = new ProcessBuilder(getAvailabilityFromDate);
        Process pr1 = processBuilder1.start();
        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(pr1.getInputStream()));
        StringBuilder stringBuilder1 = new StringBuilder();
        String line3;
        while ((line3 = bufferedReader1.readLine()) != null) {
            stringBuilder1.append(line3);
        }

        // This block will convert the date fetched from random value to be able to use in UI Test
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("MMMM dd, yyyy");
        String dateFormatUiTest = null;
        try {
            Date date = dateFormatFromDateTime.parse(dateAndTimeFromAvailableDays);
            dateFormatUiTest = simpleDateFormat2.format(date);
        } catch (ParseException ex) {
            System.out.println("Exception " + ex);
        }


        // UI Test
        encoreHomePage.selectProductAndFindTickets();
        encoreBookingPage.selectDateAndTimeAndProceedToPayment(dateFormatUiTest, newTimeFormat);
    }
}
