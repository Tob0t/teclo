package at.scch.teclo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import at.scch.teclo.pageobjects.LoggedInBasePage;

public abstract class BugzillaTest {
	protected LoggedInBasePage loggedInBasePage;

	@BeforeClass
	public static void setUpDriverStatic() {
		BugzillaSetup.openWebDriver();
	}

	@AfterClass
	public static void tearDownDriverStatic() {
		BugzillaSetup.closeWebDriver();
	}

	@Before
	public void setUpDriver() {
		BugzillaSetup.openWebDriver();

		// navigate to home base page
		loggedInBasePage = BugzillaSetup.login();
	}

	@After
	public void tearDownDriver() {
		// only close and quit selenium driver here
		// do not use driver to navigate, because if test failed with selenium
		// error, teardown will not execute correctly

		BugzillaSetup.logout();
		BugzillaSetup.closeWebDriver();
	}
}
