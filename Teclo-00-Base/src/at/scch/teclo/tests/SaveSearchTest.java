package at.scch.teclo.tests;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.hamcrest.Matchers;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ErrorCollector;
import org.openqa.selenium.WebDriver;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.ResultsPage;

public class SaveSearchTest {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();
	public ErrorCollector errors = new ErrorCollector();

	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;
	private ResultsPage myBugsPage;

	@Before
	public void setUp() throws Exception {
		driver = BugzillaSetup.getWebDriver();

		// precondition: logged in
		loggedInBasePage = BugzillaSetup.login();

		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBug(loggedInBasePage);
	}

	// TODO: Set unique string as search name
	@Test
	public void testSaveSearch() throws Exception {
		myBugsPage = loggedInBasePage.searchFor("ExampleBug01");

		assertTrue("Less bug founds, than the minimum required amount",
				0 < myBugsPage.getAmountOfBugs());

		//errors.checkThat(myBugsPage.getSummaryOfFirstBug(), is("ExampleBug01"));
		assertEquals("ExampleBug01", myBugsPage.getSummaryOfFirstBug());

		myBugsPage = myBugsPage.saveSearch("ExampleSearch01");
		loggedInBasePage = myBugsPage.navigateToHome();
		myBugsPage = loggedInBasePage.getSavedSearch("ExampleSearch01");

		assertTrue("Less bug founds, than the minimum required amount",
				0 < myBugsPage.getAmountOfBugs());

		assertEquals("ExampleBug01", myBugsPage.getSummaryOfFirstBug());
		
		// forget saved search
		myBugsPage.forgetSavedSearch("ExampleSearch01");


	}

	@After
	public void tearDown() throws Exception {

		
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
