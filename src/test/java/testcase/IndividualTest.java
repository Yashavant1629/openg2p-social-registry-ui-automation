package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public class IndividualTest extends BaseLogin {
    @Test(priority = 1)
    void individualCreation() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String individual = testData.getIndividual();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("individual")));
        Commons.click(driver,By.xpath(locators.getProperty("create_button")));
        Commons.enter(driver,By.xpath(locators.getProperty("individual_input_field")),individual);
        Commons.click(driver,By.xpath(locators.getProperty("individual_save")));
        String tableXPath = locators.getProperty("individual_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, individual);
        Assert.assertTrue(entryFound, "Expected entry with text '" + individual + "' not found");
    }

    @Test(priority = 2, dependsOnMethods = {"individualCreation"})
    void individualUpdation() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String individual = testData.getIndividual();
        String individualUpdated = testData.getIndividualUpdated();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("individual")));
        String tableXPath = locators.getProperty("individual_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.clickEntryInPaginatedTable(driver, tableXPath, individual);
        Assert.assertTrue(entryFound, "Expected entry with text '" + individual + "' not found");
        Commons.clearAndEnter(driver,By.xpath(locators.getProperty("individual_input_field")),individualUpdated);
        Commons.click(driver,By.xpath(locators.getProperty("save_update")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryUpdateFound = Commons.clickEntryInPaginatedTable(driver, tableXPath, individualUpdated);
        Assert.assertTrue(entryUpdateFound, "Expected entry with text '" + individualUpdated + "' not found");
    }

    @Test(priority = 3,dependsOnMethods = {"individualUpdation"})
    void individualDeletion() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String individualUpdated = testData.getIndividualUpdated();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("individual")));
        String tableXPath = locators.getProperty("individual_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, individualUpdated);
        Assert.assertTrue(entryFound, "Expected entry with text '" + individualUpdated + "' not found");
        String rowCheckboxXPath = "//tr[td[contains(text(),'" + individualUpdated + "')]]//input[@type='checkbox']";
        Commons.click(driver, By.xpath(rowCheckboxXPath));
        Commons.click(driver,By.xpath(locators.getProperty("actions")));
        Commons.click(driver,By.xpath(locators.getProperty("delete")));
        Commons.click(driver,By.xpath(locators.getProperty("delete_confirmation")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryStillExists = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, individualUpdated);
        Assert.assertFalse(entryStillExists, "Entry with text '" + individualUpdated + "' should be deleted but still exists.");
    }
}
