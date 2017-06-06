package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.BugResultsPage;

public class SaveSearchTest extends BugzillaTest {
	public ErrorCollector errors = new ErrorCollector();

	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;
	private BugResultsPage bugResultsPage;

	@Before
	public void setUp() throws Exception {
		// precondition: logged in
		loggedInBasePage = homeBasePage.loginAdmin();

		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
		
		// go to home base page
		BugzillaSetup.navigateToHomeBasePage();
	}

	@Test
	public void testSaveSearch() throws Exception {
		bugResultsPage = loggedInBasePage.searchFor(BugzillaSetup.getExampleBugSummary());

		assertEquals("Not exactly one bug found!", 1, bugResultsPage.getAmountOfBugs());
		assertEquals(BugzillaSetup.getExampleBugSummary(), bugResultsPage.getSummaryOfFirstBug());

		// optional replacing asserts by errors:
		// errors.checkThat(myBugsPage.getSummaryOfFirstBug(), is(BugzillaSetup.getExampleBugName()));

		bugResultsPage = bugResultsPage.saveSearch("SearchFor_"+BugzillaSetup.getExampleBugSummary());
		loggedInBasePage = bugResultsPage.navigateToHome();
		bugResultsPage = loggedInBasePage.getSavedSearch("SearchFor_"+BugzillaSetup.getExampleBugSummary());

		assertEquals("Not exactly one bug found!", 1, bugResultsPage.getAmountOfBugs());
		assertEquals(BugzillaSetup.getExampleBugSummary(), bugResultsPage.getSummaryOfFirstBug());
		
		// forget saved search
		bugResultsPage.forgetSavedSearch("SearchFor_"+BugzillaSetup.getExampleBugSummary());
	}

}
