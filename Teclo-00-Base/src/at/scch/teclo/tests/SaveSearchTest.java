package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.MyBugsPage;

public class SaveSearchTest {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;
	private MyBugsPage myBugsPage;

	@Before
	public void setUp() throws Exception {
		driver = BugzillaSetup.getWebDriver();

		// precondition: logged in
		loggedInBasePage = BugzillaSetup.login();

		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBug(loggedInBasePage);
	}

	@Test
	public void testSaveSearch() throws Exception {
		myBugsPage = loggedInBasePage.searchFor("ExampleBug01");

		assertTrue("Less bug founds, than the minimum required amount",
				0 < myBugsPage.getAmountOfBugs());

		try {
			assertEquals("ExampleBug01", myBugsPage.getSummaryOfFirstBug());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		myBugsPage = myBugsPage.saveSearch("ExampleSearch01");
		loggedInBasePage = myBugsPage.navigateToHome();
		myBugsPage = loggedInBasePage.getSavedSearch("ExampleSearch01");

		assertTrue("Less bug founds, than the minimum required amount",
				0 < myBugsPage.getAmountOfBugs());

		try {
			assertEquals("ExampleBug01", myBugsPage.getSummaryOfFirstBug());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@After
	public void tearDown() throws Exception {

		// post condition: forget saved search
		myBugsPage.forgetSavedSearch("ExampleSearch01");

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
