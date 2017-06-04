package at.scch.teclo;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import at.scch.teclo.pageobjects.HomeBasePage;

public abstract class BugzillaTest {
	protected WebDriver driver;
	protected HomeBasePage homeBasePage;
	protected final StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUpDriver(){
		driver = BugzillaSetup.getWebDriver();
		
		// navigate to home base page
		homeBasePage = BugzillaSetup.navigateToHomeBasePage();

	}
	
	@After
	public void tearDownDriver(){
		// navigate to home base page
		homeBasePage = BugzillaSetup.navigateToHomeBasePage();
		
		BugzillaSetup.ungetWebDriver();
		
		String verificationErrorString = verificationErrors .toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
