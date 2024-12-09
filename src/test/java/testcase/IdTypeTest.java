package testcase;

import base.BaseLogin;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utilities.Commons;
import utilities.TestData;

import java.io.File;
import java.io.IOException;

import static base.BaseLogin.login;
import static base.DriverCreator.driver;

public class IdTypeTest extends BaseLogin {

    @Test()
    public void idTypeCreation() throws IOException, InterruptedException {
        login();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver, By.xpath(locators.getProperty("id_type")));
        Commons.click(driver, By.xpath(locators.getProperty("create_button")));
        for (String idType : testData.getIdType()) {
            Commons.click(driver, By.xpath(locators.getProperty("create_button")));
            Commons.enter(driver, By.xpath(locators.getProperty("configurations_data_input")), idType);
            Commons.click(driver, By.xpath(locators.getProperty("id_type_save_button")));
            String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";
            boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, idType);
            Assert.assertTrue(entryFound, "Expected entry with text '" + idType + "' not found");

            // OPTIONAL: Navigate back or reset if necessary to start the next iteration
            // For example, you may need to go back to the ID Type list or reset the form.
            // driver.navigate().back();  // Use if you need to return to the previous page
            // Commons.click(driver, By.xpath(locators.getProperty("id_type")));  // Re-select the ID Type section
        }
    }
}

