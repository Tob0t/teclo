package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.BugResultsPage;

public class SaveSearchTest extends BugzillaTest {
	public ErrorCollector errors = new ErrorCollector();

	private int currentBugID;
	private String currentBugSummary;
	private BugResultsPage bugResultsPage;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
		currentBugSummary = BugzillaSetup.getExampleBugSummary();
	}

	@Test
	public void testSaveSearch() throws Exception {
		bugResultsPage = loggedInBasePage.searchFor(currentBugSummary);

		assertEquals("Not exactly one bug found!", 1, bugResultsPage.getAmountOfBugs());
		assertEquals(currentBugSummary, bugResultsPage.getSummaryOfFirstBug());

		// optional replacing asserts by errors:
		// errors.checkThat(myBugsPage.getSummaryOfFirstBug(),
		// is(BugzillaSetup.getExampleBugName()));

		bugResultsPage = bugResultsPage.saveSearch("SearchFor_" + currentBugSummary);
		loggedInBasePage = bugResultsPage.navigateToHome();
		bugResultsPage = loggedInBasePage.getSavedSearch("SearchFor_" + currentBugSummary);

		assertEquals("Not exactly one bug found!", 1, bugResultsPage.getAmountOfBugs());
		assertEquals(currentBugSummary, bugResultsPage.getSummaryOfFirstBug());

		// forget saved search
		bugResultsPage.forgetSavedSearch("SearchFor_" + currentBugSummary);
	}

}
