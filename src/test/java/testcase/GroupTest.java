package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;
import java.time.Duration;

public class GroupTest extends BaseLogin {
    @Test(priority = 1)
    void groupCreation() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String group = testData.getGroup();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("group")));
        Commons.click(driver,By.xpath(locators.getProperty("create_button")));
        Commons.enter(driver,By.xpath(locators.getProperty("group_input_field")),group);
        Commons.click(driver,By.xpath(locators.getProperty("group_save")));
        String tableXPath = locators.getProperty("group_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, group);
        Assert.assertTrue(entryFound, "Expected entry with text '" + group + "' not found");
    }

    @Test(priority = 2, dependsOnMethods = {"groupCreation"})
    void groupUpdation() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String group = testData.getGroup();
        String groupUpdated = testData.getGroupUpdated();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("group")));
        String tableXPath = locators.getProperty("group_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.clickEntryInPaginatedTable(driver, tableXPath, group);
        Assert.assertTrue(entryFound, "Expected entry with text '" + group + "' not found");
        Commons.clearAndEnter(driver,By.xpath(locators.getProperty("group_input_field")),groupUpdated);
        Commons.click(driver,By.xpath(locators.getProperty("save_update")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryUpdateFound = Commons.clickEntryInPaginatedTable(driver, tableXPath, groupUpdated);
        Assert.assertTrue(entryUpdateFound, "Expected entry with text '" + groupUpdated + "' not found");
    }

    @Test(priority = 3,dependsOnMethods = {"groupUpdation"})
    void groupDeletion() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String groupUpdated = testData.getGroupUpdated();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("group")));
        String tableXPath = locators.getProperty("group_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, groupUpdated);
        Assert.assertTrue(entryFound, "Expected entry with text '" + groupUpdated + "' not found");
        String rowCheckboxXPath = "//tr[td[contains(text(),'" + groupUpdated + "')]]//input[@type='checkbox']";
        Commons.click(driver, By.xpath(rowCheckboxXPath));
        Commons.click(driver,By.xpath(locators.getProperty("actions")));
        Commons.click(driver,By.xpath(locators.getProperty("delete")));
        Commons.click(driver,By.xpath(locators.getProperty("delete_confirmation")));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryStillExists = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, groupUpdated);
        Assert.assertFalse(entryStillExists, "Entry with text '" + groupUpdated + "' should be deleted but still exists.");
    }
}
