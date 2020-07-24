package org.kd.selframework.core.pageobjects;

import org.openqa.selenium.WebDriver;

public interface WebDriveable {
    void findElements();

    WebDriver getDriver();
    void setDriver(WebDriver driver);
}
