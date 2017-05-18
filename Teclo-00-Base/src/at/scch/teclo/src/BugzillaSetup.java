package at.scch.teclo.src;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author fabianbouchal
 */
public class BugzillaSetup {

	
	/**
	 * IP on which the bugzilla vm is running
	 */
	private static final String BASE_URL = "192.168.2.7";
	
	private static WebDriver driver;
	
	public static String getBaseURL() {
		return BASE_URL;
	}
	
	public static void setUp() {
		
		String os = System.getProperty("os.name");
		
		if (os.toLowerCase().contains("win")) {
			System.setProperty("webdriver.chrome.driver", "/Users/fabianbouchal/Downloads/chromedriver.exe");
		} else {
			// assuming OS is UNIX based (OSX, Linux, etc.) or at least is able to execute shell scripts
			System.setProperty("webdriver.chrome.driver", "/Users/fabianbouchal/Downloads/chromedriver");
		}
		
		driver = new ChromeDriver();
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	public static void tearDown() {
		driver.quit();
	}
	
}
