package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.BugResultsPage;

public class FindQuickSearchTest extends BugzillaTest {
	private StringBuffer verificationErrors = new StringBuffer();

	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;

	@Before
	public void setUp() throws Exception {

		// precondition: logged in
		loggedInBasePage = BugzillaSetup.login();

		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
	}

	// TODO: Create three test cases
	// testFindBugZarro()
	// testFindBugSingle()
	// testFindBugMultiple()
	@Test
	public void testFindBugSingle() throws Exception {
		BugResultsPage bugResultsPage = loggedInBasePage.searchFor(BugzillaSetup.getExampleBugName());

		assertEquals("No bug found!", 1, bugResultsPage.getAmountOfBugs());

		assertEquals(BugzillaSetup.getExampleBugName(), bugResultsPage.getSummaryOfFirstBug());
	}
	
	@Test
	public void testFindBugZarro() throws Exception {
		BugResultsPage bugResultsPage = loggedInBasePage.searchFor(BugzillaSetup.getExampleBugName().replace("_", "-"));
		assertEquals("More than 0 bugs found!", 0, bugResultsPage.getAmountOfBugs());
	}
	
	@Test
	public void testFindBugMultiple() throws Exception {
		// add one more bug to make sure that there are at least 2 or more bugs in the database
		BugzillaSetup.createExampleBug();
		
		BugResultsPage bugResultsPage = loggedInBasePage.searchFor("Bug");
		assertTrue("No multiple bugs found", 1 < bugResultsPage.getAmountOfBugs());
	}

}
