package at.scch.teclo;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.HomeBasePage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

/**
 * @author fabianbouchal
 */
public class BugzillaSetup {
	private static final Logger Logger = LoggerFactory.getLogger(BugzillaSetup.class);

	private static final String CHROME_DRIVER_PROPERTY = "webdriver.chrome.driver";
	private static String baseUrl = "";
	private static WebDriver driver;
	private static int driverUsageCounter;

	private static int loginUsageCounter;
	private static LoggedInBasePage loggedInBasePage;
	private static int currentbugID;
	private static String exampleBugSummary;

	/***
	 * singleton pattern static constructor for first call
	 */
	static {
		loadConfig();

		// set the variable BASE_URL received from the props file
		baseUrl = System.getProperty("BASE_URL");

		String webDriver = System.getProperty(CHROME_DRIVER_PROPERTY);
		if (webDriver == null) {
			String os = System.getProperty("os.name");

			if (os.toLowerCase().contains("win")) {
				System.setProperty(CHROME_DRIVER_PROPERTY, "./../chromdriver/chromedriver.exe");
			} else {
				// assuming OS is UNIX based (OSX, Linux, etc.) or at least is
				// able to execute shell scripts
				System.setProperty(CHROME_DRIVER_PROPERTY, "./../chromdriver/chromedriver");
			}
		}
	}

	private BugzillaSetup() {
		/* empty */
	}

	/***
	 * Set IP Adress on config file according to your VM! (config.properties)
	 */
	public static void loadConfig() {
		String filename = "config.properties";

		try (InputStream input = BugzillaSetup.class.getClassLoader().getResourceAsStream(filename)) {
			if (input == null) {
				Logger.info("Unable to find {}.", filename);
				return;
			}

			// load a properties file from class path, inside static method
			Properties prop = new Properties();
			prop.load(input);
			prop.putAll(System.getProperties());

			System.setProperties(prop);
		} catch (IOException ex) {
			Logger.error("Could not load config file.", ex);
		}
	}

	public static String getBaseURL() {
		return baseUrl;
	}

	public static WebDriver openWebDriver() {
		driverUsageCounter++;
		// set up new driver
		if (driver == null) {
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		return driver;
	}

	public static void closeWebDriver() {
		driverUsageCounter--;

		if (driverUsageCounter > 0) {
			return;
		}

		if (driver != null) {
			driver.close();
			driver.quit();
			driver = null;
		}
	}

	public static void createExampleBug() {
		// set the bug summary
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
		exampleBugSummary = "Bug_" + dateFormat.format(new Date());

		// create bug
		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		NewBugCreatedPage newBugCreatedPage = createNewBugPage.createNewBugSimple(exampleBugSummary);

		// save the id of the bug
		currentbugID = newBugCreatedPage.getBugID();
		Logger.info("Created new example bug with summary {} and ID {}.", exampleBugSummary, currentbugID);
	}

	public static int getExampleBugID() {
		// if there is no bug created yet do it now
		if (currentbugID == 0) {
			createExampleBug();
		}
		return currentbugID;
	}

	public static String getExampleBugSummary() {
		// if there is no bug created yet do it now
		if (currentbugID == 0) {
			createExampleBug();
		}
		return exampleBugSummary;
	}

	public static EditBugPage showBug(int bugID) {
		driver.get(baseUrl + "/show_bug.cgi?id=" + bugID);
		return PageFactory.initElements(driver, EditBugPage.class);
	}

	public static HomeBasePage navigateToHomeBasePage() {
		driver.get(baseUrl);
		return PageFactory.initElements(driver, HomeBasePage.class);
	}

	public static LoggedInBasePage login() {
		// if not logged in, do it now
		if (loggedInBasePage == null) {
			HomeBasePage homeBasePage = navigateToHomeBasePage();
			loggedInBasePage = homeBasePage.loginAdmin();
		}
		loginUsageCounter++;
		return loggedInBasePage;
	}

	public static void logout() {
		loginUsageCounter--;
		// if logged in, do logout
		if (loginUsageCounter == 0 && loggedInBasePage != null) {
			HomeBasePage homeBasePage = navigateToHomeBasePage();
			homeBasePage.logoutAdmin();
			loggedInBasePage = null;
		}
	}

}
