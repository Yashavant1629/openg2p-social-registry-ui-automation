package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;

public class GroupTest extends BaseLogin {

    @Test(priority = 1)
    public static void groupCreation() throws IOException, InterruptedException {
        login();
            String groupName = testData.getGroupName();
            Commons.click(driver, By.xpath(locators.getProperty("group")));
            Commons.click(driver, By.xpath(locators.getProperty("new_button")));
            Commons.enter(driver, By.xpath(locators.getProperty("group_name_field")), groupName);
            Commons.dropDownByValue(driver, By.id(locators.getProperty("tags")), testData.getTags());
            Commons.dropDownByValue(driver, By.id(locators.getProperty("kind")), testData.getGroupMembershipKind());
            Commons.click(driver, By.xpath(locators.getProperty("contact_details")));
            Commons.enter(driver, By.id(locators.getProperty("address")), testData.getAddress());
            Commons.dropDownByValue(driver, By.id(locators.getProperty("region_dropdown")), testData.getRegion());
//        Commons.click(driver, By.xpath(locators.getProperty("add_a_line")));
//        Commons.enter(driver, By.xpath(locators.getProperty("phone_number")),"");
//        Commons.click(driver, By.xpath(locators.getProperty("save&close")));
            Commons.click(driver, By.xpath(locators.getProperty("save")));
            Commons.click(driver, By.xpath(locators.getProperty("groups")));
            String tableXPath = locators.getProperty("group_table");
            boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, groupName);
            Assert.assertTrue(entryFound, "Expected entry with text '" + groupName + "' not found");


    }

    @Test(priority = 2)
    public static void groupUpdate() throws IOException, InterruptedException {
        login();
        String groupName = testData.getGroupName();
        String updatedGroupName = testData.getUpdatedgroupname();
        Commons.click(driver, By.xpath(locators.getProperty("group")));
        Thread.sleep(2000);
        String tableXPath = locators.getProperty("group_table");
        boolean entryClicked = Commons.clickEntryInPaginatedTable(driver, tableXPath, groupName);
        Assert.assertTrue(entryClicked, "Expected entry '" + groupName + "' not found and clicked.");
        Commons.clear(driver,By.xpath(locators.getProperty("group_name_field")));
        Commons.enter(driver,By.xpath(locators.getProperty("group_name_field")),updatedGroupName);
        Commons.click(driver, By.xpath(locators.getProperty("contact_details")));
        Commons.clearAndEnter(driver, By.id(locators.getProperty("address")),testData.getAddress());
        Commons.click(driver,By.xpath(locators.getProperty("save")));
        Commons.click(driver, By.xpath(locators.getProperty("groups")));
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, updatedGroupName);
        Assert.assertTrue(entryFound, "Expected entry with text '" + updatedGroupName + "' not found");

    }
}
