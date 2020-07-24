package org.kd.selframework.core.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WebDriverFactory {

    private WebDriverFactory() {
    }

    static {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\kdrzazga\\webdriver\\chromedriver.exe");
    }

    public static WebDriver createChromeDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("test-type");

        return new ChromeDriver(options);
    }

}
