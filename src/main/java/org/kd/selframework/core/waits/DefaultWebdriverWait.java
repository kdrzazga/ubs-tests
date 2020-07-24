package org.kd.selframework.core.waits;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DefaultWebdriverWait extends WebDriverWait {

    public DefaultWebdriverWait(WebDriver driver, long timeOutInSeconds) {
        super(driver, timeOutInSeconds);
    }
}
