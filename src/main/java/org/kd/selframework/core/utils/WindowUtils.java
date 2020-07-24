package org.kd.selframework.core.utils;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;

public class WindowUtils {

    public static void switchWindow(WebDriver driver, String url, boolean switchToGivenURL) {
        for (String winHandle : driver
                .getWindowHandles()) {
            driver
                    .switchTo()
                    .window(winHandle);
            String currentURL = driver
                    .getCurrentUrl();
            boolean isSwitchedToGivenURL = currentURL.contains(url);
            if (isSwitchedToGivenURL == switchToGivenURL) {
                return;
            }
        }
    }

    public static void maximizeWindow(WebDriver driver) {
        driver.manage().window().setPosition(new Point(0, 0));
        java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        Dimension dim = new Dimension(Double.valueOf(screenSize.getWidth()).intValue(), Double.valueOf(screenSize.getHeight()).intValue());
        driver.manage().window().setSize(dim);
    }
}
