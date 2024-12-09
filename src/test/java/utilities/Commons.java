package utilities;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.lang.Thread.sleep;

public class Commons {


    public static WebElement click(WebDriver driver, By by) throws InterruptedException {
//        logger.info("Clicking " + by );
        try {
            (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.elementToBeClickable(by));
            sleep(500);
            driver.findElement(by).click();
//            sleep(500);
        } catch (StaleElementReferenceException sere) {
            sleep(500);
            driver.findElement(by).click();
        } catch (TimeoutException toe) {
            driver.findElement(by).click();
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Element identified by " + by.toString() + " was not clickable after 20 seconds");
        } catch (Exception e) {

            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(by));

        }
        Thread.sleep(500);
        return null;
    }

    public static WebElement enter(WebDriver driver, By by, String value) {
//        logger.info("Entering " + by +value);
        try {
            (new WebDriverWait(driver, Duration.ofSeconds(10))).until(ExpectedConditions.visibilityOfElementLocated(by));
            driver.findElement(by).clear();
            driver.findElement(by).sendKeys(value);
            try {
                sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (StaleElementReferenceException sere) {
            driver.findElement(by).sendKeys(value);
        } catch (TimeoutException toe) {
            driver.findElement(by).sendKeys(value);
            System.out.println("Element identified by " + by.toString() + " was not clickable after 20 seconds");
        }
        return null;
    }

    public static boolean isEntryPresentInPaginatedTable(WebDriver driver, String tableXpath, String expectedText) {
        boolean entryFound = false;

        while (true) {
            try {
                if (isEntryPresent(driver, tableXpath, expectedText)) {
                    entryFound = true;
                    System.out.println("Found expected text: " + expectedText);
                    break;
                }

                WebElement nextPageButton = driver.findElement(By.xpath("//button[@class='oi oi-chevron-right btn btn-secondary o_pager_next px-2 rounded-end']"));
                if (nextPageButton.isEnabled()) {
                    nextPageButton.click();

                } else {
                    System.out.println("Expected text not found on any page: " + expectedText);
                    break;
                }
            } catch (StaleElementReferenceException e) {
                System.out.println("Stale Element Reference Exception occurred. Retrying...");
                continue;
            }
        }
        return entryFound;
    }

    public static boolean isEntryPresent(WebDriver driver, String tableXpath, String expectedText) {
        WebElement table = driver.findElement(By.xpath(tableXpath));
        java.util.List<WebElement> rows = table.findElements(By.tagName("tr"));

        boolean entryFound = false;

        for (WebElement row : rows) {
            java.util.List<WebElement> cells = row.findElements(By.tagName("td"));

            for (WebElement cell : cells) {
                String cellText = cell.getText();
                if (cellText.equals(expectedText)) {
                    entryFound = true;
                    return entryFound;
                }
            }
        }

        return entryFound;
    }

    public static void dropdown(WebDriver driver, By by)
    {
//        logger.info("Selecting DropDown Index Zero Value " + by );

        try {
            sleep(500);
            click(driver,by);//REGION
            sleep(500);

            String att= driver.findElement(by).getAttribute("aria-owns");
            String[] list=att.split(" ");
            click( driver,By.id(list[0]));
            try {
                sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }catch(Exception e)
        {
            e.getMessage();
        }
    }

    public static void clearAndEnter(WebDriver driver, By by, String value) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(by));

        try {
            element.clear();
            element.sendKeys(Keys.CONTROL + "a");
            element.sendKeys(Keys.DELETE);
            element.sendKeys(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void dropDownByValue(WebDriver driver, By dropdownLocator, String valueToSelect) {
        try {
            List<WebElement> optionList = driver.findElements(dropdownLocator);
            for (WebElement ele : optionList) {
                String currentOption = ele.getText();
                if (currentOption.equals(valueToSelect)) {
                    ele.click();
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static WebElement clickEntryInTable(WebDriver driver, By tableLocator, String entryName) {
        WebElement table = driver.findElement(tableLocator);
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                if (cell.getText().trim().equals(entryName)) {
                    cell.click();
                    return table;
                }
            }
        }
        System.out.println("Entry '" + entryName + "' not found in the table.");
        return table;
    }
}
