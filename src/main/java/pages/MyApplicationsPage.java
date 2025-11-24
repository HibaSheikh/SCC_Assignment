package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import models.ApplicationRecord;

public class MyApplicationsPage {

    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Duration TIMEOUT = Duration.ofSeconds(15);

    private final By appRows = By.cssSelector(".appgrid tbody tr");

    // Constructor to initialize the driver and PageFactory
    public MyApplicationsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
        PageFactory.initElements(driver, this);

    }

    // Left navigation menu element to click My Applications
    @FindBy(id = "myApplicationsMenu")
    private WebElement menuElement;

    // Opens My Applications page
    public void openMyApplicationsPage() {
        wait.until(ExpectedConditions.elementToBeClickable(menuElement)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(appRows));
    }

    public List<WebElement> getAppRows() {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(appRows));
    }

    public List<ApplicationRecord> getDisplayedRecords() {
        List<WebElement> rows = getAppRows();
        List<ApplicationRecord> records = new ArrayList<>();

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            // Getting UI columns
            ApplicationRecord record = new ApplicationRecord(
                    columns.get(0).getText(),
                    columns.get(1).getText(),
                    columns.get(2).getText(),
                    columns.get(3).getText(),
                    columns.get(4).getText(),
                    columns.get(5).getText(),
                    columns.get(6).getText(),
                    columns.get(7).getText());
            records.add(record);
        }

        return records;
    }
}