package org.TodayTix;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BrowserSelector extends BasePage{

    LoadProp loadProp = new LoadProp();
    public void setUpBrowser() {
        String browser = loadProp.getProperty("browser");

        if (browser.equalsIgnoreCase(("chrome"))) {
            System.setProperty("webdriver.chrome.driver", "src/test/Resources/BrowserDrivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--incognito");
            options.addArguments("disable-infobars");
            options.addArguments("--disable-extensions");
            options.addArguments("start-maximized");
            options.addArguments("--disable-setUpBrowser-side-navigation-");
            options.addArguments("disable-blink-features=BlockCredentialedSubresources");
            driver = new ChromeDriver(options);
            // this is to manage your Implicity wait for 10 seconds
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        } else {
            throw new Error("Browser name is incorrect " + browser);
        }
    }
}
