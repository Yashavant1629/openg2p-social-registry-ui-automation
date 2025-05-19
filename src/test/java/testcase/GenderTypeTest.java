package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;
import java.lang.ref.SoftReference;
import java.time.Duration;

public class GenderTypeTest extends BaseLogin {
    @Test(priority = 1)
    public static void genderTypeCreation() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String gender = testData.getGender();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("gender_type")));
        Commons.click(driver,By.xpath(locators.getProperty("create_button")));
        Commons.enter(driver,By.xpath(locators.getProperty("gender_type_data_input_code")),gender);
        Commons.enter(driver,By.xpath(locators.getProperty("gender_type_data_input_value")),gender);
        Commons.click(driver,By.xpath(locators.getProperty("gender_type_save_button")));
        String tableXPath = locators.getProperty("gender_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, gender);
        Assert.assertTrue(entryFound, "Expected entry with text '" + gender + "' not found");
    }

    @Test(priority = 2, dependsOnMethods = {"genderTypeCreation"})
    void genderTypeUpdation() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String gender = testData.getGender();
        String updatedGender = testData.getGenderUpdated();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("gender_type")));
        String tableXPath = locators.getProperty("gender_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.clickEntryInPaginatedTable(driver, tableXPath, gender);
        Assert.assertTrue(entryFound, "Expected entry with text '" + gender + "' not found");
        Commons.clearAndEnter(driver,By.xpath(locators.getProperty("gender_type_data_input_code")),updatedGender);
        Commons.clearAndEnter(driver,By.xpath(locators.getProperty("gender_type_data_input_value")),updatedGender);
        Commons.click(driver,By.xpath(locators.getProperty("save_update")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryUpdateFound = Commons.clickEntryInPaginatedTable(driver, tableXPath, updatedGender);
        Assert.assertTrue(entryUpdateFound, "Expected entry with text '" + updatedGender + "' not found");

    }

    @Test(priority = 3, dependsOnMethods = {"genderTypeUpdation"})
    void genderTypeDeletion() throws IOException, InterruptedException {
        login();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        String updatedGender = testData.getGenderUpdated();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("gender_type")));
        String tableXPath = locators.getProperty("gender_table");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, updatedGender);
        Assert.assertTrue(entryFound, "Expected entry with text '" + updatedGender + "' not found");
        String rowCheckboxXPath = "//tr[td[contains(text(),'" + updatedGender + "')]]//input[@type='checkbox']";
        Commons.click(driver, By.xpath(rowCheckboxXPath));
        Commons.click(driver,By.xpath(locators.getProperty("actions")));
        Commons.click(driver,By.xpath(locators.getProperty("delete")));
        Commons.click(driver,By.xpath(locators.getProperty("delete_confirmation")));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(tableXPath)));
        boolean entryStillExists = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, updatedGender);
        Assert.assertFalse(entryStillExists, "Entry with text '" + updatedGender + "' should be deleted but still exists.");

    }
}
