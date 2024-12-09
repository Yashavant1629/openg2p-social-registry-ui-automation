package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;

public class GenderTypesTest extends BaseLogin {
    @Test
    public static void genderTypeCreation() throws IOException, InterruptedException {
        login();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver,By.xpath(locators.getProperty("gender_type")));
        Commons.click(driver,By.xpath(locators.getProperty("create_button")));
        Commons.enter(driver,By.xpath(locators.getProperty("gender_type_data_input_code")),"autotestgender");
        Commons.enter(driver,By.xpath(locators.getProperty("gender_type_data_input_value")),"AutoTestGender");
        Commons.click(driver,By.xpath(locators.getProperty("gender_type_save_button")));
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, "autotestgender");
        Assert.assertTrue(entryFound, "Expected entry with text '" + "autotestgender" + "' not found");
        Thread.sleep(2000);
    }
}
