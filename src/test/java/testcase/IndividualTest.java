package testcase;

import base.BaseLogin;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.Commons;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class IndividualTest extends BaseLogin {

    @Test
    public static void individualCreation() throws IOException, InterruptedException {
        login();
        String familyName = testData.getFamilyName();
        String givenName = testData.getGivenName();
        String additionalName = testData.getAdditionalName();
        Commons.click(driver, By.xpath(locators.getProperty("individuals")));
        Commons.click(driver, By.xpath(locators.getProperty("new_button")));
        Commons.enter(driver, By.id(locators.getProperty("family_name")), familyName);
        Commons.enter(driver, By.id(locators.getProperty("given_name")), givenName);
        Commons.enter(driver, By.id(locators.getProperty("additional_name")), additionalName);
        Commons.dropDownByValue(driver, By.id(locators.getProperty("tags")), testData.getTags());
        Commons.enter(driver, By.id(locators.getProperty("address")), testData.getAddress());
        Commons.enter(driver, By.id(locators.getProperty("email")), testData.getIndividualEmail());
        Commons.dropDownByValue(driver, By.id(locators.getProperty("district_dropdown")),testData.getDistrict());
        Commons.dropDownByValue(driver, By.id(locators.getProperty("region_dropdown")), testData.getRegion());
        Commons.enter(driver,By.id(locators.getProperty("birth_place")), testData.getAddress());
        Commons.enter(driver,By.id(locators.getProperty("date_of_birth")),testData.getDOB());
        Commons.enter(driver,By.id(locators.getProperty("civil_status")), testData.getCivilStatus());
        Commons.enter(driver,By.id(locators.getProperty("occupation")),testData.getOccupation());
        Commons.enter(driver,By.id(locators.getProperty("income")),testData.getIncome());
        Commons.dropDownByValue(driver,By.id(locators.getProperty("gender_dropdown")), testData.getGender());
//        Commons.click(driver, By.xpath(locators.getProperty("add_a_line")));
//        Commons.enter(driver, By.xpath(locators.getProperty("phone_number")),"");
//        Commons.click(driver, By.xpath(locators.getProperty("save&close")));
        Commons.click(driver,By.xpath(locators.getProperty("save")));
        Commons.click(driver, By.xpath(locators.getProperty("individuals")));
        String familyname = "Auto";
        String givenname = "Test";
        String additionalname = "Individual";
        List<String> individualName = Arrays.asList(familyname,givenname+" "+additionalname);
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";
//        Thread.sleep(3000);
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, individualName.toString());
        System.out.println(entryFound);
        Assert.assertTrue(entryFound, "Expected entry with text '" + individualName + "' not found");
    }


    @Test
    public static void individualUpdate() throws IOException, InterruptedException {
        login();
        Commons.click(driver, By.xpath(locators.getProperty("individuals")));
        String familyname = "Auto";
        String givenname = "Test";
        String additionalname = "Individual";
        List<String> individualName = Arrays.asList(familyname,givenname+" "+additionalname);
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";
//        Thread.sleep(3000);
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, individualName.toString());

    }
}
