package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;

public class GroupTest extends BaseLogin {

    @Test
    public static void groupCreation() throws IOException, InterruptedException {
        login();
        for (String groupName : testData.getGroupname()) {
            Commons.click(driver, By.xpath(locators.getProperty("new_button")));
//            Thread.sleep(3000);
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
            String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";
//            Thread.sleep(3000);
            boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, groupName);
            Assert.assertTrue(entryFound, "Expected entry with text '" + groupName + "' not found");

        }
    }

    @Test
    public static void groupUpdate() throws IOException, InterruptedException {
        login();
        String updatedgroupname = testData.getUpdatedgroupname();
        WebElement row = Commons.clickEntryInTable(driver, By.xpath("//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']"),"AutoTestGroup");
        if (row != null) {
            System.out.println("Record found: " + row.getText());
        } else {
            System.out.println("Record not found.");
        }
        Commons.clearAndEnter(driver,By.xpath(locators.getProperty("group_name_field")),updatedgroupname);
        Commons.click(driver, By.xpath(locators.getProperty("contact_details")));
        Commons.clearAndEnter(driver, By.id(locators.getProperty("address")),testData.getAddress());
        Commons.click(driver,By.xpath(locators.getProperty("save")));
        Commons.click(driver, By.xpath(locators.getProperty("groups")));
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";
        Thread.sleep(3000);
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, updatedgroupname);
        Assert.assertTrue(entryFound, "Expected entry with text '" + updatedgroupname + "' not found");

    }
}
