package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;

public class RelationshipTest extends BaseLogin {

    @Test
    public static void relationshipsCreation() throws IOException, InterruptedException {
        login();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("relationship")));
        Commons.click(driver,By.xpath(locators.getProperty("create_button")));
        Commons.enter(driver,By.xpath(locators.getProperty("relationship_name_data_input")),"AutoTestRelation1");
        Commons.enter(driver,By.xpath(locators.getProperty("relationship_inverse_name_data_input")),"AutoTestRelation2");
        Commons.click(driver,By.xpath(locators.getProperty("relationship_bidirectional_checkbox")));
        Commons.dropDownByValue(driver,By.xpath(locators.getProperty("source_partner_type_dropdown")),"Group");
        Commons.dropDownByValue(driver,By.xpath(locators.getProperty("destination_partner_type_dropdown")),"Individual");
        Commons.click(driver,By.xpath(locators.getProperty("save_relationship")));
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";

        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, "AutoTestRelation1");
        Assert.assertTrue(entryFound, "Expected entry with text '" + "AutoTestRelation1" + "' not found");
        Thread.sleep(3000);
    }
}
