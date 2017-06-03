package at.scch.teclo;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import at.scch.teclo.pageobjects.AdvancedSearchPage;
import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.LogInBasePage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.LoggedOutBasePage;
import at.scch.teclo.pageobjects.ResultsPage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;
import at.scch.teclo.pageobjects.SearchBasePage;

/**
 * @author fabianbouchal
 */
public class BugzillaSetup {

	private static WebDriver driver;
	private static int driverUsageCounter;
	private static String BASE_URL = "";
	public static String ExampleBugDescription = "This is an example description for ExampleBug01";

	private static LoggedInBasePage loggedInBasePage;
	private static int currentbugID;

	/***
	 * singleton pattern
	 * static constructor for first call
	 */
	static {
		loadConfig();
		
		// set the variable BASE_URL received from the props file
		BASE_URL = System.getProperty("BASE_URL").toString();
		System.out.println("Trying to connect to " + BASE_URL);

		String os = System.getProperty("os.name");

		if (os.toLowerCase().contains("win")) {
			System.setProperty("webdriver.chrome.driver", "./chromdriver/chromedriver.exe");
		} else {
			// assuming OS is UNIX based (OSX, Linux, etc.) or at least is able
			// to execute shell scripts
			System.setProperty("webdriver.chrome.driver", "./chromdriver/chromedriver");
		}
	}

	/***
	 * Set IP Adress on config file according to your VM! (config.properties)
	 */
	public static void loadConfig() {
		Properties prop = new Properties(System.getProperties());
		String filename = "config.properties";
		
		try(InputStream input = BugzillaSetup.class.getClassLoader().getResourceAsStream(filename)) {
			if (input == null) {
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);
			System.setProperties(prop);

		} catch (IOException ex) {
			ex.printStackTrace();
		} 
	}

	public static String getBaseURL() {
		return BASE_URL;
	}

	public static WebDriver getWebDriver() {
		driverUsageCounter++;
		// set up new driver
		if (driver == null) {
			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		}

		return driver;
	}
	
	public static void ungetWebDriver(){
		driverUsageCounter--;
		
		if(driverUsageCounter > 0){
			return;
		}
		
		if(driver != null){
			driver.close();
			driver = null;
		}
	}

	/***
	 * TODO: move to page object
	 * Helper method to log in the admin
	 * 
	 * @return LoggedIn page
	 */
	public static LoggedInBasePage login() {
		LogInBasePage logInBasePage = LogInBasePage.navigateTo(driver);

		// change the waiting time 0 seconds if the element is not found
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);

		// check if the log out button is existing
		if (!(driver.findElements(By.linkText("Log out")).size() > 0)) {
			loggedInBasePage = logInBasePage.logIn("admin", "admin");
		}

		// change the waiting time back to 10 seconds
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		return loggedInBasePage;
	}

	/***
	 * Helper method to log out the current user
	 * 
	 * @param loggedInBasePage
	 */
	public static void logOut(LoggedInBasePage loggedInBasePage) {
		LoggedOutBasePage loggedOutBasePage = loggedInBasePage.logOut();
	}
	
	public static void createExampleBug(){
		// set the bug name
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String exampleBugName = "Bug_"+dateFormat.format(new Date());
		
		// precondition: logged in
		loggedInBasePage = BugzillaSetup.login();
		
		// create bug
		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		NewBugCreatedPage newBugCreatedPage = createNewBugPage.createNewBugSimple(exampleBugName);
		
		// save the id of the bug
		currentbugID = newBugCreatedPage.getBugID();
	}
	
	public static int getExampleBugID(){
		// if there is no bug created yet do it now
		if(currentbugID == 0){
			createExampleBug();
		}
		return currentbugID;
	}
	
	public static EditBugPage showBug(int bugID) {
		driver.get(BASE_URL + "/show_bug.cgi?id="+bugID);
		return PageFactory.initElements(driver, EditBugPage.class);
	}

	/***
	 * Helper method to create a new example bug
	 * 
	 * @param loggedInBasePage
	 * @return newBug created page
	 */
	/*public static int getExampleBug(LoggedInBasePage loggedInBasePage) {
		int bugID = 0;
		// Search first if there is a bug already existing
		SearchBasePage searchBasePage = loggedInBasePage.navigateToSearchBasePage();
		AdvancedSearchPage advancedSearchPage = searchBasePage.navigateToAdvancedSearchPage();
		advancedSearchPage.deselectBugState("ASSIGNED");
		advancedSearchPage.deselectBugState("REOPENED");
		ResultsPage myBugsPage = advancedSearchPage.searchFor(ExampleBugSummary,
				ExampleBugDescription);

		// if the bug is existing just return the ID of the first found bug
		if (myBugsPage.getAmountOfBugs() > 1) {
			bugID = myBugsPage.getIDOfFirstBug();
		} else { // else create a new bug and return the ID
			CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
			NewBugCreatedPage newBugCreatedPage = createNewBugPage.createNewBug(ExampleBugSummary,
					ExampleBugDescription);
			bugID = newBugCreatedPage.getBugID();
		}
		return bugID;
		
	}
*/
}
