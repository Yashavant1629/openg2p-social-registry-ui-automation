package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;

public class GroupMembershipKindTest extends BaseLogin {

    @Test(priority = 1)
    public static void groupMembershipKindCreation() throws IOException, InterruptedException {
        login();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("group_membership_kind")));
        Commons.click(driver,By.xpath(locators.getProperty("create_button")));
        Commons.enter(driver,By.xpath(locators.getProperty("group_membership_kind_data_input")),"AutoTestMemberKind");
        Commons.click(driver,By.xpath(locators.getProperty("save_group_membership_kind")));
        String expectedText = "AutoTestMemberKind";
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, expectedText);
        Assert.assertTrue(entryFound, "Expected entry with text '" + expectedText + "' not found");
        Thread.sleep(3000);

    }
}
