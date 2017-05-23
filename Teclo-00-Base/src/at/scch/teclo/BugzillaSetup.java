package at.scch.teclo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.LogInBasePage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.LoggedOutBasePage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

/**
 * @author fabianbouchal
 */
public class BugzillaSetup {

	private static WebDriver driver;
	private static String BASE_URL="";
	
	private static LoggedInBasePage loggedInBasePage;

	/***
	 * Setup is done once to find out the right driver
	 */
	private static void setUp() {

		loadBaseURL();
		
		String os = System.getProperty("os.name");

		if (os.toLowerCase().contains("win")) {
			System.setProperty("webdriver.chrome.driver", "./chromdriver/chromedriver.exe");
		} else {
			// assuming OS is UNIX based (OSX, Linux, etc.) or at least is able to execute shell scripts
			System.setProperty("webdriver.chrome.driver", "./chromdriver/chromedriver");
		}

		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}
	
	/***
	 * Set IP Adress on config file according to your VM! (config.properties)
	 */
	public static void loadBaseURL() {
		Properties prop = new Properties();
		InputStream input = null;

		try {
			String filename = "config.properties";
			input = BugzillaSetup.class.getClassLoader().getResourceAsStream(filename);
			if(input==null){
				System.out.println("Sorry, unable to find " + filename);
				return;
			}

			// load a properties file from class path, inside static method
			prop.load(input);

			// set the variable BASE_URL received from the props file
			BASE_URL = prop.getProperty("BASE_URL").toString();
			System.out.println("Trying to connect to " +BASE_URL);

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally{
			if(input!=null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static String getBaseURL(){
		return BASE_URL;
	}

	public static WebDriver getWebDriver() {
		// tear down old driver
		//tearDown();

		// set up new driver
		if(driver == null){
			setUp();
		}

		return driver;
	}
	
	/***
	 * Helper method to log in the admin
	 * @return LoggedIn page
	 */
	public static LoggedInBasePage LogIn(){
		LogInBasePage logInBasePage = LogInBasePage.navigateTo(driver);
	    loggedInBasePage = logInBasePage.logIn("admin", "admin");
	    return loggedInBasePage;
	}
	
	/***
	 * Helper method to log out the current user
	 * @param loggedInBasePage
	 */
	public static void LogOut(LoggedInBasePage loggedInBasePage){
		LoggedOutBasePage loggedOutBasePage = loggedInBasePage.logOut();
	}
	
	/***
	 * Helper method to create a new example bug
	 * @param loggedInBasePage
	 * @return newBug created page
	 */
	public static NewBugCreatedPage CreateExampleBug(LoggedInBasePage loggedInBasePage){
		 CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		 NewBugCreatedPage newBugCreatedPage = createNewBugPage.createNewBug("ExampleBug01", "This is an example description for ExampleBug01");
		 return newBugCreatedPage;
	}

	

	private static void tearDown() {
		if (driver != null)
			driver.quit();
	}

}