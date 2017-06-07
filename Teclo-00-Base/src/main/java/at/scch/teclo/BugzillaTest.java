package at.scch.teclo;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import at.scch.teclo.pageobjects.LoggedInBasePage;

public abstract class BugzillaTest {
	protected WebDriver driver;
	protected LoggedInBasePage loggedInBasePage;
	protected final StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUpDriver() {
		driver = BugzillaSetup.getWebDriver();

		// navigate to home base page
		loggedInBasePage = BugzillaSetup.login();

	}

	@After
	public void tearDownDriver() {
		// only close and quit selenium driver here
		// do not use driver to navigate, because if test failed with selenium
		// error, teardown will not execute correctly

		BugzillaSetup.logout();
		BugzillaSetup.ungetWebDriver();

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
