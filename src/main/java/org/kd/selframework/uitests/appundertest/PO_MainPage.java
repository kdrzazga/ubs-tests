package org.kd.selframework.uitests.appundertest;

import com.google.common.collect.Lists;
import org.kd.selframework.core.pageobjects.LocatorHelper;
import org.kd.selframework.core.pageobjects.Page;
import org.openqa.selenium.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class PO_MainPage extends Page {

    private final By privateSettingsWindowSelector = By.id("doc");
    private final By agreeBtnSelector = //By.className("actionbutton__button");
            //By.className("actionbutton__txtWrapper");
            By.xpath("//button[@name='senddata']/span/span/span/span");
    // By.className("actionbutton__overflowWrapper");

    private final By mainMenuItemsSelector = By.xpath("//div/div/ul[@id='mainmenu']/li");
    private final By wealthMgmtSubmenuWrapperSelector = By.xpath("//div/ul[@id='mainmenu-navContent0']/li");
    private final By wealthMgmtSubmenuSelector = By.xpath("//span[@class='catNav__label']/a");


    private WebElement agreeBtn;
    private WebElement privateSettingsWindow;
    private List<WebElement> mainMenuItems;
    private List<WebElement> wealthMgmtSubmenu;

    public PO_MainPage(WebDriver driver) {
        super(driver, "http://www.ubs.com");
    }

    public void navigateTo() {
        super.navigateTo();
        findElements();
        logger.log("Navigating to " + this.url);
        acceptConditions();
    }

    @Override
    public void findElements() {
        this.privateSettingsWindow = LocatorHelper.quietlyFindElement(this.driver, privateSettingsWindowSelector);
        this.agreeBtn = LocatorHelper.quietlyFindElementWithinElement(this.driver, By.xpath("//button[@name='senddata']")
                , privateSettingsWindow, 8);

        logger.log("Button text: " + this.agreeBtn.getText());
        this.mainMenuItems = LocatorHelper.quietlyFindElements(this.driver, mainMenuItemsSelector);
    }


    public List<WebElement> getSubMenuItems() {
        if (this.wealthMgmtSubmenu != null) {
            return this.wealthMgmtSubmenu;
        } else {
            logger.error("Menu or menu items not found");
            return Lists.newArrayList();
        }
    }

    public List<String> getMainMenuItemCaptions() {
        return this.mainMenuItems.stream().map(WebElement::getText).collect(Collectors.toList());
    }

    public void clickMainMenuOption(String optionName) {
        clickMenuOption(this.mainMenuItems, optionName);
    }

    public void clickSubMenuOption(String optionName) {
        clickMenuOption(this.getSubMenuItems(), optionName);
    }

    private void clickMenuOption(List<WebElement> menuItems, String optionName) {
        var menuOption = menuItems.stream()
                .filter(element -> element.getText().equalsIgnoreCase(optionName))
                .findFirst();

        menuOption.ifPresent(WebElement::click);

        if (menuOption.isEmpty())
            logger.error(optionName + " couldn´t be clicked");
    }

    private void acceptConditions() {
        Optional.ofNullable(this.agreeBtn).ifPresent(
                button -> {
                    //TODO Cannot click 'Agree to all' button
                    logger.log("Accepting cookies");

                    try {
                        this.agreeBtn.click();
                    } catch (ElementNotInteractableException ex) {
                        logger.error("Couldn't click " + this.agreeBtn);
                    }
                }
        );
    }

    public void findSubMenuElements() {

        if (!this.mainMenuItems.isEmpty()) {
            var wrappedSubmenu = LocatorHelper.quietlyFindElements(this.driver, this.wealthMgmtSubmenuWrapperSelector);

            Optional.ofNullable(wrappedSubmenu).ifPresent(menu -> this.wealthMgmtSubmenu =
                    LocatorHelper.quietlyFindElementsWithin(this.driver, menu.get(0), wealthMgmtSubmenuSelector)
                            .stream()
                            .filter(item -> !item.getText().equals(""))
                            .collect(Collectors.toList()));

            logger.log("Found " + this.wealthMgmtSubmenu.size() + " submenu items: "
                    + this.wealthMgmtSubmenu.stream()
                    .map(WebElement::getText)
                    .collect(Collectors.joining(", ")));
        }
        if (mainMenuItems.isEmpty()) logger.error("Main menu not present, cannot search for submenu");

    }
}