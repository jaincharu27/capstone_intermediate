package actions;

import java.time.Duration;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private static final int TIMEOUT = 5; // seconds

	protected WebDriver driver;
	private WebDriverWait wait;
	public static Logger log = LogManager.getLogger(BasePage.class);

	public BasePage(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(TIMEOUT));
		PageFactory.initElements(driver, this);
	}

	protected void waitForElementToAppear(WebElement locator) {
		wait.until(ExpectedConditions.visibilityOf(locator));
	}

	protected void waitForTextToAppear(WebElement locator, String id) {
		wait.until((ExpectedCondition<Boolean>) driver -> locator.getText() != "-");
		wait.until(ExpectedConditions.textMatches(By.id(id), Pattern.compile("\\-?\\d+\\,?\\d+\\.?\\d{0,3}")));
	}

}
