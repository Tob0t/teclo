package at.scch.teclo;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author fabianbouchal
 */
public class BugzillaSetup {

	private static WebDriver driver;
	private static String BASE_URL="";

	/**
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
			System.out.println(prop.getProperty("Trying to connect to " +BASE_URL +".."));
			BASE_URL = prop.getProperty("BASE_URL").toString();
			

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
		tearDown();

		// set up new driver
		setUp();

		return driver;
	}

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

	private static void tearDown() {
		if (driver != null)
			driver.quit();
	}

}
