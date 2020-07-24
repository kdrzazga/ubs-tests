package org.kd.selframework.core.pageobjects;

import com.google.common.collect.Lists;
import org.kd.selframework.core.utils.TestLogger;
import org.kd.selframework.core.utils.TestLoggerSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public final class LocatorHelper {

    private static final int TIMEOUT = 8;
    private static final TestLogger logger = TestLoggerSingleton.getInstance();

    private LocatorHelper() {
    }

    public static WebElement quietlyFindElement(final WebDriver driver, final By locator) {
        return quietlyFindElement(driver, locator, TIMEOUT);
    }

    public static WebElement quietlyFindElement(WebDriver driver, By locator, int timeout) {
        ExpectedCondition<WebElement> elementLocated;
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        WebElement element;
        try {
            elementLocated = ExpectedConditions.presenceOfElementLocated(locator);
            element = wait.until(elementLocated);
            logger.log("Found element: " + element.getClass() + " with locator " + locator);
            return element;

        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Element " + locator.toString() + " not found on page " + driver.getCurrentUrl());
            return null;
        }
    }

    public static WebElement quietlyFindElementWithinElement(WebDriver driver, By locator, WebElement parent, int timeout) {
        WebElement element;

        try {
            element = parent.findElement(locator);
            logger.log("Found element: " + element.getClass() + " within parent " + parent);
            return element;

        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Element " + locator.toString() + " not found on page " + driver.getCurrentUrl());
            return null;
        }
    }

    public static List<WebElement> quietlyFindElements(WebDriver driver, By locator) {
        List<WebElement> elements;
        try {
            elements = driver.findElements(locator);
            logger.log("Found " + elements.size() + " elements.");
            return elements;

        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Element " + locator.toString() + " not found on page " + driver.getCurrentUrl());
            return null;
        }
    }

    public static List<WebElement> quietlyFindElementsWithin(WebDriver driver, WebElement parent, By locator, int timeout) {
        List<WebElement> elements;
        try {
            elements = parent.findElements(locator);
            logger.log("Found " + elements.size() + " elements.");
            return elements;

        } catch (NoSuchElementException | TimeoutException e) {
            logger.error("Element " + locator.toString() + " not found on page " + driver.getCurrentUrl());
            return Lists.newArrayList();
        }
    }

    public static List<WebElement> quietlyFindElementsWithin(WebDriver driver, WebElement parent, By locator) {
        return quietlyFindElementsWithin(driver, parent, locator, TIMEOUT);
    }
}
