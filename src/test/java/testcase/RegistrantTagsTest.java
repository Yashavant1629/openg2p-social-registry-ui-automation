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

public class RegistrantTagsTest extends BaseLogin {

    @Test(priority = 1)
    void registrantTagCreation() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String registrantTag = testData.getRegistrantTag();
        
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("registrant_tags")));
        Commons.click(driver,By.xpath(locators.getProperty("create_button")));
        Commons.enter(driver,By.xpath(locators.getProperty("registrant_tag_input_field")),registrantTag);
        Commons.click(driver,By.xpath(locators.getProperty("registrant_tag_save")));
        
        String tableXPath = locators.getProperty("registrant_tag_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, registrantTag);
        Assert.assertTrue(entryFound, "Expected entry with text '" + registrantTag + "' not found");
    }

    @Test(priority = 2, dependsOnMethods = {"registrantTagCreation"})
    void registrantTagUpdation() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String registrantTag = testData.getRegistrantTag();
        String registrantTagUpdated = testData.getRegistrantTagUpdated();
        
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("registrant_tags")));
        String tableXPath = locators.getProperty("registrant_tag_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.clickEntryInPaginatedTable(driver, tableXPath, registrantTag);
        Assert.assertTrue(entryFound, "Expected entry with text '" + registrantTag + "' not found");
        Commons.clearAndEnter(driver,By.xpath(locators.getProperty("registrant_tag_input_field")),registrantTagUpdated);
        Commons.click(driver,By.xpath(locators.getProperty("save_update")));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryUpdateFound = Commons.clickEntryInPaginatedTable(driver, tableXPath, registrantTagUpdated);
        Assert.assertTrue(entryUpdateFound, "Expected entry with text '" + registrantTagUpdated + "' not found");
    }

    @Test(priority = 3,dependsOnMethods = {"registrantTagUpdation"})
    void registrantTagDeletion() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String registrantTagUpdated = testData.getRegistrantTagUpdated();
        
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("registrant_tags")));
        String tableXPath = locators.getProperty("registrant_tag_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, registrantTagUpdated);
        Assert.assertTrue(entryFound, "Expected entry with text '" + registrantTagUpdated + "' not found");
        String rowCheckboxXPath = "//tr[td[contains(text(),'" + registrantTagUpdated + "')]]//input[@type='checkbox']";
        Commons.click(driver, By.xpath(rowCheckboxXPath));
        Commons.click(driver,By.xpath(locators.getProperty("actions")));
        Commons.click(driver,By.xpath(locators.getProperty("delete")));
        Commons.click(driver,By.xpath(locators.getProperty("delete_confirmation")));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryStillExists = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, registrantTagUpdated);
        Assert.assertFalse(entryStillExists, "Entry with text '" + registrantTagUpdated + "' should be deleted but still exists.");
    }
}