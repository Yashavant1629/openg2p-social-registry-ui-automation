package testcase;

import base.BaseLogin;
import base.DriverCreator;
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

    String idTpye;
    @Test(priority = 1)
    void idTypeCreation() throws IOException, InterruptedException {
        login();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver, By.xpath(locators.getProperty("id_type")));

        for (String idType : testData.getIdType()) {
            Commons.click(driver, By.xpath(locators.getProperty("create_button")));
            Commons.enter(driver, By.xpath(locators.getProperty("configurations_data_input")), idType);
            Thread.sleep(5000);
            Commons.click(driver, By.xpath(locators.getProperty("id_type_save_button")));
            String tableXPath = locators.getProperty("idtype_table_xpath");
            boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, idType);
            Assert.assertTrue(entryFound, "Expected entry with text '" + idType + "' not found");

        }
    }

    @Test(priority = 2, dependsOnMethods = {"idTypeCreation"})
    void idTypeDeletion() throws IOException, InterruptedException {
        login();
        Commons.click(driver, By.xpath(locators.getProperty("registry_configuration")));
        Commons.click(driver, By.xpath(locators.getProperty("id_type")));
        String tableXPath = locators.getProperty("idtype_table_xpath");
        for(String idType : testData.getIdType()) {
            boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, idType);

            if(entryFound) {
                String rowCheckboxXPath = "//tr[td[contains(text(),'" + idType + "')]]//input[@type='checkbox']";
                Commons.click(driver, By.xpath(rowCheckboxXPath));
//                Commons.click(driver,By.xpath(locators.getProperty("select")));
                Commons.click(driver,By.xpath(locators.getProperty("actions")));
                Commons.click(driver,By.xpath(locators.getProperty("delete")));
                Commons.click(driver,By.xpath(locators.getProperty("delete_confirmation")));
            } else {
                System.out.println("Entry not found for deletion: " +idType );
            }
        }
    }
}

