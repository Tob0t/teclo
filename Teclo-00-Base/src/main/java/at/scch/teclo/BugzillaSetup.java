package at.scch.teclo;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.scch.teclo.pageobjects.CreateBugPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;
import at.scch.teclo.pageobjects.StartPage;

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
	private static StartPage startPage;
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

	public static String getBaseUrl() {
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

	public static int createExampleBug() {
		checkDriver();
		checkLogin();

		// set the bug summary
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");
		exampleBugSummary = "Bug_" + LocalDateTime.now().format(formatter);

		// create bug
		CreateBugPage createBugPage = startPage.gotoCreateBugPage();
		NewBugCreatedPage newBugCreatedPage = createBugPage.createNewBug(exampleBugSummary, "test data");

		// save the id of the bug
		currentbugID = newBugCreatedPage.getNewBugId();
		Logger.info("Created new example bug with summary {} and ID {}.", exampleBugSummary, currentbugID);
		
		return currentbugID;
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
	
	public static StartPage gotoStartPage() {
		checkDriver();		
		
		driver.get(BugzillaSetup.getBaseUrl());
		return PageFactory.initElements(driver, StartPage.class);
	}

	public static EditBugPage gotoBugPage(int bugID) {
		checkDriver();

		driver.get(baseUrl + "/show_bug.cgi?id=" + bugID);
		return PageFactory.initElements(driver, EditBugPage.class);
	}

	public static StartPage login() {
		startPage = gotoStartPage();
		startPage.login("admin", "admin");
		return startPage;
	}
	
	public static StartPage logout() {
		startPage = gotoStartPage();
		return startPage.logout();
	}
	
	
//	public static LoggedInBasePage login() {
//		checkDriver();
//
//		// if not logged in, do it now
//		if (loggedInBasePage == null) {
//			HomeBasePage homeBasePage = gotoHomeBasePage();
//			loggedInBasePage = homeBasePage.login("admin", "admin");
//		}
//		loginUsageCounter++;
//		return loggedInBasePage;
//	}

//	public static void logout() {
//		checkDriver();
//
//		loginUsageCounter--;
//		if (loginUsageCounter > 0)
//			return;
//
//		// if logged in, do logout
//		if (loggedInBasePage != null) {
//			loggedInBasePage.logout();
//			loggedInBasePage = null;
//		}
//	}

	private static void checkLogin() {
		if (startPage == null) {
			throw new IllegalStateException("User not logged in!");
		}
	}

	private static void checkDriver() {
		if (driver == null) {
			throw new IllegalStateException("Driver not initialized!");
		}
	}
}
