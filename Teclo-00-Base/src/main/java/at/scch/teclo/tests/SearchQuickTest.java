package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.BugResultsPage;
import at.scch.teclo.pageobjects.EditBugPage;

public class SearchQuickTest extends AbstractBugzillaTestWithLogin {
	private int currentBugID;
	private String currentBugSummary;

	@Before
	public void setUp() throws Exception {
		currentBugID = BugzillaSetup.getExampleBugID();
		currentBugSummary = BugzillaSetup.getExampleBugSummary();
	}

	@Test
	public void testFindBugZarro() throws Exception {
		BugResultsPage bugResultsPage = startPage.searchFor(currentBugSummary.replace("_", "-"));
		assertEquals("More than 0 bugs found!", 0, bugResultsPage.getAmountOfBugs());
	}

	@Test
	public void testFindBugSingleByName() throws Exception {
		BugResultsPage bugResultsPage = startPage.searchFor(currentBugSummary);

		assertEquals("No bug found!", 1, bugResultsPage.getAmountOfBugs());
		assertEquals(currentBugSummary, bugResultsPage.getSummaryOfFirstBug());
	}

	@Test
	public void testFindBugSingleByID() throws Exception {
		EditBugPage editBugPage = startPage.searchFor(currentBugID);
		assertEquals("Bug not found by ID", currentBugSummary, editBugPage.getSummary());
	}

	@Test
	public void testFindBugMultiple() throws Exception {
		// add one more bug to make sure that there are at least 2 or more bugs
		// in the database
		BugzillaSetup.createExampleBug();

		BugResultsPage bugResultsPage = startPage.searchFor("Bug");
		assertTrue("No multiple bugs found", 1 < bugResultsPage.getAmountOfBugs());
	}

	@Test
	public void testSaveSearch() throws Exception {
		BugResultsPage bugResultsPage = startPage.searchFor(currentBugSummary);

		int numberFoundBugs = bugResultsPage.getAmountOfBugs();
		String firstBugSummary = bugResultsPage.getSummaryOfFirstBug();

		bugResultsPage = bugResultsPage.saveSearch("SearchFor_" + currentBugSummary);
		bugResultsPage.gotoStartPage();
		bugResultsPage = startPage.gotoSavedSearch("SearchFor_" + currentBugSummary);

		assertEquals(numberFoundBugs, bugResultsPage.getAmountOfBugs());
		assertEquals(firstBugSummary, bugResultsPage.getSummaryOfFirstBug());

		// forget saved search
		bugResultsPage.forgetSavedSearch("SearchFor_" + currentBugSummary);
	}

}
