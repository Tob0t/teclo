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

import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.HomeBasePage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

/**
 * @author fabianbouchal
 */
public class BugzillaSetup {

	private static WebDriver driver;
	private static int driverUsageCounter;
	private static String BASE_URL = "";

	private static LoggedInBasePage loggedInBasePage;
	private static int currentbugID;
	private static String exampleBugName;

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
			driver.quit();
			driver = null;
		}
	}

	public static void createExampleBug(){
		// precondition: logged in
		HomeBasePage homeBasePage = navigateToHomeBasePage();
		loggedInBasePage = homeBasePage.loginAdmin();
		
		// set the bug name
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		exampleBugName = "Bug_"+dateFormat.format(new Date());
		
		// create bug
		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		NewBugCreatedPage newBugCreatedPage = createNewBugPage.createNewBugSimple(exampleBugName);
		
		// save the id of the bug
		currentbugID = newBugCreatedPage.getBugID();
		System.out.println("Creating new example bug with the name "+exampleBugName+ " and ID "+currentbugID);
	}
	
	public static int getExampleBugID(){
		// if there is no bug created yet do it now
		if(currentbugID == 0){
			createExampleBug();
		}
		return currentbugID;
	}
	
	public static String getExampleBugName(){
		// if there is no bug created yet do it now
		if(currentbugID == 0){
			createExampleBug();
		}
		return exampleBugName;
	}
	
	public static void setExampleBugName(String newName){
		exampleBugName = newName;
	}
	
	public static EditBugPage showBug(int bugID) {
		driver.get(BASE_URL + "/show_bug.cgi?id="+bugID);
		return PageFactory.initElements(driver, EditBugPage.class);
	}

	public static HomeBasePage navigateToHomeBasePage() {
		driver.get(BASE_URL);
		return PageFactory.initElements(driver, HomeBasePage.class);
	}
}
