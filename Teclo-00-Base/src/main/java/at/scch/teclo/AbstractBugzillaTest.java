package at.scch.teclo;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractBugzillaTest {
	private static final Logger Logger = LoggerFactory.getLogger(AbstractBugzillaTest.class);	
	
	// we have to be very careful with naming!
	// if a subclass has the same static @BeforeClass method (which is possible
	// as static methods cannot be overridden), this method will be ignored!
	@BeforeClass
	public static void staticOpenDriver() {
		BugzillaSetup.openWebDriver();
		
		// Make sure that test setup has been performed
		BugzillaSetup.gotoStartPage();
		if (!BugzillaSetup.isTestSetup()) {
			String errorMsg = "Wrong or missing test setup! Required: "
					+ BugzillaSetup.getTestConfigName() + ". Forgot to run BugzillaSetup?";
			Logger.error(errorMsg);
			throw new IllegalStateException(errorMsg);
		}
	}

	// we have to be very careful with naming!
	// if a subclass has the same static @AfterClass method (which is possible
	// as static methods cannot be overridden), this method will be ignored!
	@AfterClass
	public static void staticCloseDriver() {
		BugzillaSetup.closeWebDriver();
	}
}
