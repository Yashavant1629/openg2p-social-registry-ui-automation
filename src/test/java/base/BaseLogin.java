package base;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseLogin extends DriverCreator{


    public static Properties properties = new Properties();

    public static Properties locators = new Properties();

    public static FileReader fileReader1;
    public static FileReader fileReader2;


    @Test
    public static void login() throws IOException, InterruptedException {

        fileReader1 = new FileReader("testconfigs/configfile/config.properties");
        fileReader2 = new FileReader("src/main/resources/configfiles/locators.properties");
        fileReader3 = new FileReader("testconfigs/testdata/testdata.json");
        properties.load(fileReader1);
        locators.load(fileReader2);
//        testdata.load(fileReader3);
        Thread.sleep(2000);
        driver.findElement(By.name(locators.getProperty("username_field"))).sendKeys(properties.getProperty("username"));
        driver.findElement(By.id(locators.getProperty("password_field"))).sendKeys(properties.getProperty("password"));
        driver.findElement(By.xpath(locators.getProperty("login_button"))).click();
        Thread.sleep(5000);
    }
}
