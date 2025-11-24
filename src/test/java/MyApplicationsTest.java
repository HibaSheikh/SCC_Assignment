
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import models.ApplicationRecord;
import pages.LoginPage;
import pages.MyApplicationsPage;
import utils.CsvDataReader;

public class MyApplicationsTest {
    private final Duration WAIT_TIMEOUT = Duration.ofSeconds(10);
    private WebDriver driver;
    private WebDriverWait wait;

    @DataProvider(name = "users")
    public Object[][] users() {
        return new Object[][] {
                { "user1@test.com", "Password1" },
                { "user2@test.com", "Password2" }
        };
    }

    @BeforeMethod
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://scc-apps-url.com");
        wait = new WebDriverWait(driver, WAIT_TIMEOUT);
    }

    @Test(dataProvider = "users", testName = "Validate Only Logged-in User's Applications Displayed")
    public void validateLoggedInUserRecordsDisplayed(String userEmail, String password) {
        // Step 1: Login and validate dashboard
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(userEmail, password);
        loginPage.clickLoginBtn();

        // Step 2: Navigate to My Applications page and make sure everything is loaded
        MyApplicationsPage myApplicationsPage = new MyApplicationsPage(driver);
        myApplicationsPage.openMyApplicationsPage();
        myApplicationsPage.getAppRows();

        // Step 3: Get displayed records from UI
        List<ApplicationRecord> actual = myApplicationsPage.getDisplayedRecords();

        // Step 4: Get expected records from CSV for this user
        List<ApplicationRecord> expected = CsvDataReader.getApplicationsForUser(userEmail);

        // Step 5: Validate row count matches expected records (email used only for
        // filtering)
        Assert.assertEquals(actual.size(), expected.size(),
                "Number of applications displayed does not match expected for user: " + userEmail);
    }

    @Test(dataProvider = "users", testName = "Validate All Application Details for Logged-in User")
    public void validateApplicationsForUser(String userEmail, String password) {
        // Step 1: Login and validate dashboard
        LoginPage loginPage = new LoginPage(driver);
        loginPage.enterCredentials(userEmail, password);
        loginPage.clickLoginBtn();

        // Step 2: Navigate to My Applications page and make sure everything is loaded
        MyApplicationsPage myApplicationsPage = new MyApplicationsPage(driver);
        myApplicationsPage.openMyApplicationsPage();
        myApplicationsPage.getAppRows();

        // Step 3: Get displayed records from UI
        List<ApplicationRecord> actual = myApplicationsPage.getDisplayedRecords();

        // Step 4: Get expected records from CSV for this user
        List<ApplicationRecord> expected = CsvDataReader.getApplicationsForUser(userEmail);

        // Step 5: Validate each application field
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertEquals(actual.get(i).getStatus(), expected.get(i).getStatus());
            Assert.assertEquals(actual.get(i).getReferenceNumber(), expected.get(i).getReferenceNumber());
            Assert.assertEquals(actual.get(i).getApplicationNumber(), expected.get(i).getApplicationNumber());
            Assert.assertEquals(actual.get(i).getPermitType(), expected.get(i).getPermitType());
            Assert.assertEquals(actual.get(i).getAddress(), expected.get(i).getAddress());
            Assert.assertEquals(actual.get(i).getDate(), expected.get(i).getDate());
            Assert.assertEquals(actual.get(i).getOwner(), expected.get(i).getOwner());
            Assert.assertEquals(actual.get(i).getAction(), expected.get(i).getAction());
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}
