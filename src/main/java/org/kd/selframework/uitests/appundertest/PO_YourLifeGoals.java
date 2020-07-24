package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.LocatorHelper;
import org.kd.selframework.core.pageobjects.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Optional;

public class PO_YourLifeGoals extends Page {

    private final By getInTouchBtnSelector = By
            .xpath("//a[contains(text(),'Get in touch') and contains(@href, '/wealth-management/contact-us/get-in-touch.html')]");

    private WebElement getInTouchBtn;

    public PO_YourLifeGoals(WebDriver driver) {
        super(driver, "https://www.ubs.com/global/en/wealth-management/life-goals.html");
    }

    @Override
    public void findElements() {
        this.getInTouchBtn = LocatorHelper.quietlyFindElement(this.driver, getInTouchBtnSelector);
    }

    public void clickGetInTouchBtn() {
        Optional.ofNullable(this.getInTouchBtn)
                .ifPresent(btn -> {
                    try {
                        btn.click();
                    } catch (ElementNotInteractableException e) {
                        logger.error("Could not click " + btn);
                    }

                    if (Optional.ofNullable(this.getInTouchBtn).isEmpty()) {
                        logger.error("Button ´Get in Touch´ not found");
                    }
                });
    }
}