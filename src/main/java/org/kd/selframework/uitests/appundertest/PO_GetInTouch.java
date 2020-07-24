package org.kd.selframework.uitests.appundertest;

import org.kd.selframework.core.pageobjects.LocatorHelper;
import org.kd.selframework.core.pageobjects.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.kd.selframework.core.pageobjects.QuietSelect;

import java.util.Optional;

public class PO_GetInTouch extends Page {

    private final By howCanWeHelpSelector = By.id("text1_sfcollection");
    private final By over1MioSelector = By.id("text2_sfcollection");
    private final By requestMemoSelector = By.id("memo1");
    private final By firstNameSelector = By.id("firstName");
    private final By lastNameSelector = By.id("lastName");
    private final By emailSelector = By.id("email");
    private final By phoneNoSelector = By.id("phoneNumber");
    private final By countrySelector = By.id("domicile_sfcollection");
    private final By submitSelector = By.xpath("//button[@type='submit' and @class='actionbutton__button']");
    private final By confirmationChkboxselector = By.xpath("//input[@type='checkbox']");
    private final By titleSelectorMrs = By.xpath("//li[@class='radio__item']/input[@id='gender_sfcollection_0']");
    private final By titleSelectorMr = By.xpath("//li[@class='radio__item']/input[@id='gender_sfcollection_1']");

    private QuietSelect howCanWeHelp;
    private WebElement requestMemo;
    private WebElement titleMr;
    private WebElement titleMrs;
    private WebElement firstName;
    private WebElement lastName;
    private WebElement email;
    private WebElement phoneNo;
    private WebElement submitButton;
    private WebElement confirmationChkbox;

    @Override
    public void findElements() {
        this.howCanWeHelp = new QuietSelect(LocatorHelper.quietlyFindElement(this.driver, this.howCanWeHelpSelector));
        this.requestMemo = LocatorHelper.quietlyFindElement(this.driver, this.requestMemoSelector);
        this.firstName = LocatorHelper.quietlyFindElement(this.driver, this.firstNameSelector);
        this.lastName = LocatorHelper.quietlyFindElement(this.driver, this.lastNameSelector);
        this.email = LocatorHelper.quietlyFindElement(this.driver, this.emailSelector);
        this.phoneNo = LocatorHelper.quietlyFindElement(this.driver, this.phoneNoSelector);
        this.submitButton = LocatorHelper.quietlyFindElement(this.driver, this.submitSelector);
        this.confirmationChkbox = LocatorHelper.quietlyFindElement(this.driver, this.confirmationChkboxselector);
        this.titleMr = LocatorHelper.quietlyFindElement(this.driver, this.titleSelectorMr);
        this.titleMrs = LocatorHelper.quietlyFindElement(this.driver, this.titleSelectorMrs);
    }

    public PO_GetInTouch(WebDriver driver) {
        super(driver, "https://www.ubs.com/global/en/wealth-management/contact-us/get-in-touch.html");
    }

    public void enterRequest(String howCanWeHelp, boolean over1Mio, String request) {
        Optional.ofNullable(this.requestMemo).ifPresent(memo -> memo.sendKeys(request));
        Optional.ofNullable(this.howCanWeHelp).ifPresent(combobox -> combobox.selectByValue(howCanWeHelp));
    }

    public void enterData(boolean isMr, String firstName, String lastName, String email, String phoneNo, String country) {
        Optional.ofNullable(this.firstName).ifPresent(textBox -> textBox.sendKeys(firstName));
        Optional.ofNullable(this.lastName).ifPresent(textBox -> textBox.sendKeys(lastName));
        Optional.ofNullable(this.email).ifPresent(textBox -> textBox.sendKeys(email));
        Optional.ofNullable(this.phoneNo).ifPresent(textBox -> textBox.sendKeys(phoneNo));
        //TODO click doesn't check the radiobutton
        var titleRadio = isMr ? this.titleMr : this.titleMrs;
        Optional.ofNullable(titleRadio).ifPresent(WebElement::click);
    }

    public void submit() {
        Optional.ofNullable(this.confirmationChkbox).ifPresent(WebElement::click);

        Optional.ofNullable(this.submitButton).ifPresent(WebElement::click);
        if (Optional.ofNullable(this.submitButton).isEmpty()) {
            logger.error("Cannot click 'Submit'");
        }
    }
}
