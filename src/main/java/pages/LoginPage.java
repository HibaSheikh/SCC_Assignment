package pages;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Duration TIMEOUT = Duration.ofSeconds(15);

    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(id = "loginBtn")
    private WebElement loginButton;
    @FindBy(id = "dashboard-menu")
    private WebElement navMenu;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, TIMEOUT);
        PageFactory.initElements(driver, this);
    }

    // If the project is extensive, we can create a separate helper class for waits
    private WebElement waitForVisibility(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    private WebElement waitForClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void enterCredentials(String email, String password) {
        waitForVisibility(emailInput).sendKeys(email);
        waitForVisibility(passwordInput).sendKeys(password);
    }

    public void clickLoginBtn() {
        waitForClickable(loginButton).click();

        try {
            // Wait until navigation menu appears to confirm successful login
            waitForVisibility(navMenu);
            // login fails if menu doesn't appear
        } catch (Exception e) {
            throw new RuntimeException("Login failed: " + e.getMessage());
        }

    }
}
