package org.kd.selframework.uitests.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.kd.selframework.core.utils.TestLogger;
import org.kd.selframework.core.utils.TestLoggerSingleton;
import org.kd.selframework.core.utils.WebDriverFactory;
import org.kd.selframework.core.utils.WindowUtils;
import org.kd.selframework.uitests.appundertest.PO_GetInTouch;
import org.kd.selframework.uitests.appundertest.PO_MainPage;
import org.kd.selframework.uitests.appundertest.PO_YourLifeGoals;
import org.openqa.selenium.WebDriver;

import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Stepdefs {

    private final TestLogger logger = TestLoggerSingleton.getInstance();
    private final WebDriver driver = WebDriverFactory.createChromeDriver();

    private final PO_MainPage mainPage = new PO_MainPage(driver);
    private final PO_YourLifeGoals yourLifeGoals = new PO_YourLifeGoals(driver);
    private final PO_GetInTouch getInTouch = new PO_GetInTouch(driver);

    @Before
    public void maximizeSite() {
        WindowUtils.maximizeWindow(driver);
    }

    @When("^I navigate to index site$")
    public void navigate() {
        logger.log("navigate");
        this.mainPage.navigateTo();
    }

    @Then("^I expect the side menu contains the following positions: (.*)$")
    public void verifySideMenuPositions(List<String> positions) {
        var items = this.mainPage.getMainMenuItemCaptions();

        assertThat("Main Menu items not found", items, not(hasSize(0)));
        positions.forEach(position -> assertTrue(items.contains(position)));
    }

    @When("^Through main menu I go to Your life goals and then to Get in touch site")
    public void navigateFromMenuToGetInTouchsSite() {
        logger.log("navigate");

        this.mainPage.navigateTo();
        this.mainPage.findElements();
        this.mainPage.clickMainMenuOption("Wealth Management");
        this.mainPage.findSubMenuElements();
        this.mainPage.clickSubMenuOption("Your life goals");

        this.yourLifeGoals.findElements();
        this.yourLifeGoals.clickGetInTouchBtn();
    }

    @When("^I submit an incomplete Wealth Management Req$")
    public void enter() {
        this.getInTouch.enterData(true, "Jan", "Kowalski", "jan.kowalski@ubs.com"
                , "", "Poland");
        this.getInTouch.enterRequest("Wealth Management Advice", true, "I want to buy Bitcoins");
    }

    @Then("^I cannot submit$")
    public void verifyConfirmation() {
        this.getInTouch.submit();
    }

    @After
    public void tearDown() {
        this.driver.close();
    }
}
