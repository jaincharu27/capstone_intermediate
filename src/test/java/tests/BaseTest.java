package tests;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import configs.PropertiesFile;
import io.github.bonigarcia.wdm.WebDriverManager;
import static io.qameta.allure.Allure.addAttachment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {

	private WebDriver driver;
	public static String url = null;
	public static Logger log = LogManager.getLogger(BaseTest.class);

	@BeforeClass
	@Parameters("browser")
	public void setUp(String browser) throws Exception {

		// Check if parameter passed from TestNG is 'firefox'
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			log.info("Firefox driver instantiated");
			log.warn("Firefox have existing issues with content security policy due to which website doesnt work properly. Search will not work.");
		}
		// Check if parameter passed as 'chrome'
		else if (browser.equalsIgnoreCase("chrome")) {
			// set path to chromedriver.exe
			WebDriverManager.chromedriver().setup();
			ChromeOptions op = new ChromeOptions();
			op.setBrowserVersion("131.0.6778.85");
			op.addArguments("--disable-blink-features=AutomationControlled");
			// create chrome instance
			driver = new ChromeDriver(op);
			log.info("Chrome driver instantiated");
		}
		// Check if parameter passed as 'Edge'
		else if (browser.equalsIgnoreCase("Edge")) {
			// set path to Edge.exe
			WebDriverManager.edgedriver().driverVersion("131.0.2903.51").setup();
			EdgeOptions op = new EdgeOptions();
			op.addArguments("--disable-blink-features=AutomationControlled");
			// create Edge instance
			driver = new EdgeDriver(op);
			log.info("Edge driver instantiated");
		} else {
			log.error("Browser found incorrectly setup");
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		log.info("Implicit wait applied on the driver for 1 seconds");
		PropertiesFile.getProperties();
		log.info("Url fetched from config properties");
		driver.get(url);
		log.info("Web application NSE launched");
	}

	@AfterMethod
	public void take_screenshot(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				Object currentClass = result.getInstance();
				WebDriver webDriver = ((BaseTest) currentClass).getDriver();
				if (webDriver != null) {
					// Use TakesScreenshot method to capture screenshot
					TakesScreenshot screenshot = (TakesScreenshot) webDriver;
					// Saving the screenshot in desired location
					File source = screenshot.getScreenshotAs(OutputType.FILE);
					log.info("Screenshots taken as test failed");
					// Path to the location to save screenshot
					DateFormat dateFormat = new SimpleDateFormat("dd-mm-yy h-m-s");
					Date date = new Date();
					FileUtils.copyFile(source,
							new File("./Screenshots/" + result.getName() + "_" + dateFormat.format(date) + ".png"));
					log.info("Screenshots saved at Screenshot folder");
					System.out.println(result.getName() + ": Screenshot is captured");
					addAttachment("Screenshot", FileUtils.openInputStream(source));
					log.info("Screenshots attached at allure report");
				}
			} catch (Exception e) {
				log.error("Take Screenshot malfunctioned");
				e.getMessage();
			}
		}
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		log.info("Browser closed");
	}

	public WebDriver getDriver() {
		return driver;
	}

}
