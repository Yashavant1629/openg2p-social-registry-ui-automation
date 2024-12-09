package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;

public class GroupTypeTest extends BaseLogin {
    @Test
    public static void groupTypeCreation() throws IOException, InterruptedException {
        login();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("group_type")));
        Commons.click(driver,By.xpath(locators.getProperty("create_button")));
        Commons.enter(driver,By.xpath(locators.getProperty("group_type_data_input")),"AutoTestKind");
        Commons.click(driver,By.xpath(locators.getProperty("save_group_type")));
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, "AutoTestKind");
        Assert.assertTrue(entryFound, "Expected entry with text '" + "AutoTestKind" + "' not found");


    }
}
