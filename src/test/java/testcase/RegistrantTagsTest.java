package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;

public class RegistrantTagsTest extends BaseLogin {

    @Test
    public static void registrantTagCreation() throws IOException, InterruptedException {
        login();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("registrant_tags")));
        Commons.click(driver,By.xpath(locators.getProperty("create_button")));
        Commons.enter(driver,By.xpath(locators.getProperty("configurations_data_input")),"AutoTestTag");
        Commons.click(driver,By.xpath(locators.getProperty("save_registrant_tag")));
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, "AutoTestTag");
        Assert.assertTrue(entryFound, "Expected entry with text '" + "AutoTestTag" + "' not found");
        Thread.sleep(3000);


    }
}
