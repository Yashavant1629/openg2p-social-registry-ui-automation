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
        Commons.click(driver, By.xpath(locators.getProperty("individuals")));
        Commons.click(driver, By.xpath(locators.getProperty("new_button")));
        Commons.enter(driver, By.id(locators.getProperty("family_name")), "Auto");
        Commons.enter(driver, By.id(locators.getProperty("given_name")), "Test");
        Commons.enter(driver, By.id(locators.getProperty("additional_name")), "Individual");
        Commons.dropDownByValue(driver, By.id(locators.getProperty("tags")), "AutoTestTag");
        Commons.enter(driver, By.id(locators.getProperty("address")), "Bangalore");
        Commons.enter(driver, By.id(locators.getProperty("email")), "autotestuser@gmail.com");
        Commons.dropDownByValue(driver, By.id(locators.getProperty("district_dropdown")),"AutoTestDistrict");
        Commons.dropDownByValue(driver, By.id(locators.getProperty("region_dropdown")),"AutoTestRegion");
        Commons.enter(driver,By.id(locators.getProperty("birth_place")),"Bangalore");
        Commons.enter(driver,By.id(locators.getProperty("date_of_birth")),"16/06/2000");
        Commons.enter(driver,By.id(locators.getProperty("civil_status")),"single");
        Commons.enter(driver,By.id(locators.getProperty("occupation")),"Software Engineer");
        Commons.enter(driver,By.id(locators.getProperty("income")),"1000000");
        Commons.dropDownByValue(driver,By.id(locators.getProperty("gender_dropdown")),"autotestgender");
//        Commons.click(driver, By.xpath(locators.getProperty("add_a_line")));
//        Commons.enter(driver, By.xpath(locators.getProperty("phone_number")),"");
//        Commons.click(driver, By.xpath(locators.getProperty("save&close")));
        Commons.click(driver,By.xpath(locators.getProperty("save")));
        Commons.click(driver, By.xpath(locators.getProperty("individuals")));
        String familyName = "Auto";
        String firstName = "Test";
        String additionalName = "Individual";
        List<String> individualName = Arrays.asList(familyName,firstName+" "+additionalName);
        String tableXPath = "//table[@class='o_list_table table table-sm table-hover position-relative mb-0 o_list_table_ungrouped table-striped']";
        Thread.sleep(3000);
        boolean entryFound = Commons.isEntryPresentInPaginatedTable(driver, tableXPath, individualName.toString());
        Assert.assertTrue(entryFound, "Expected entry with text '" + individualName + "' not found");
    }


    public static void individualUpdate() throws IOException, InterruptedException {
        login();
        Commons.click(driver, By.xpath(locators.getProperty("individuals")));

    }
}
