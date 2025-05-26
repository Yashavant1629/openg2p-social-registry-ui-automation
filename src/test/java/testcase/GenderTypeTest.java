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

public class GenderTypeTest extends BaseLogin {

    @Test(priority = 1)
    void genderTypeCreation() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String genderType = testData.getGenderType();
        
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("gender_type")));
        Commons.click(driver,By.xpath(locators.getProperty("create_button")));
        Commons.enter(driver,By.xpath(locators.getProperty("gender_type_input_field")),genderType);
        Commons.click(driver,By.xpath(locators.getProperty("gender_type_save")));
        
        String tableXPath = locators.getProperty("gender_type_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, genderType);
        Assert.assertTrue(entryFound, "Expected entry with text '" + genderType + "' not found");
    }

    @Test(priority = 2, dependsOnMethods = {"genderTypeCreation"})
    void genderTypeUpdation() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String genderType = testData.getGenderType();
        String genderTypeUpdated = testData.getGenderTypeUpdated();
        
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("gender_type")));
        String tableXPath = locators.getProperty("gender_type_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.clickEntryInPaginatedTable(driver, tableXPath, genderType);
        Assert.assertTrue(entryFound, "Expected entry with text '" + genderType + "' not found");
        Commons.clearAndEnter(driver,By.xpath(locators.getProperty("gender_type_input_field")),genderTypeUpdated);
        Commons.click(driver,By.xpath(locators.getProperty("save_update")));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryUpdateFound = Commons.clickEntryInPaginatedTable(driver, tableXPath, genderTypeUpdated);
        Assert.assertTrue(entryUpdateFound, "Expected entry with text '" + genderTypeUpdated + "' not found");
    }

    @Test(priority = 3,dependsOnMethods = {"genderTypeUpdation"})
    void genderTypeDeletion() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String genderTypeUpdated = testData.getGenderTypeUpdated();
        
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("gender_type")));
        String tableXPath = locators.getProperty("gender_type_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, genderTypeUpdated);
        Assert.assertTrue(entryFound, "Expected entry with text '" + genderTypeUpdated + "' not found");
        String rowCheckboxXPath = "//tr[td[contains(text(),'" + genderTypeUpdated + "')]]//input[@type='checkbox']";
        Commons.click(driver, By.xpath(rowCheckboxXPath));
        Commons.click(driver,By.xpath(locators.getProperty("actions")));
        Commons.click(driver,By.xpath(locators.getProperty("delete")));
        Commons.click(driver,By.xpath(locators.getProperty("delete_confirmation")));
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryStillExists = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, genderTypeUpdated);
        Assert.assertFalse(entryStillExists, "Entry with text '" + genderTypeUpdated + "' should be deleted but still exists.");
    }
}
