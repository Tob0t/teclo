package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.SearchResultsPage;
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
		SearchResultsPage searchResultsPage = startPage.searchFor(currentBugSummary.replace("_", "-"));
		assertEquals("More than 0 bugs found!", 0, searchResultsPage.getAmountOfBugs());
	}

	@Test
	public void testFindBugSingleByName() throws Exception {
		SearchResultsPage searchResultsPage = startPage.searchFor(currentBugSummary);

		assertEquals("No bug found!", 1, searchResultsPage.getAmountOfBugs());
		assertEquals(currentBugSummary, searchResultsPage.getSummaryOfFirstBug());
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

		SearchResultsPage searchResultsPage = startPage.searchFor("Bug");
		assertTrue("No multiple bugs found", 1 < searchResultsPage.getAmountOfBugs());
	}

	@Test
	public void testSaveSearch() throws Exception {
		SearchResultsPage searchResultsPage = startPage.searchFor(currentBugSummary);

		int numberFoundBugs = searchResultsPage.getAmountOfBugs();
		String firstBugSummary = searchResultsPage.getSummaryOfFirstBug();

		searchResultsPage = searchResultsPage.rememberSavedSearch("SearchFor_" + currentBugSummary);
		searchResultsPage.gotoStartPage();
		searchResultsPage = startPage.performSavedSearch("SearchFor_" + currentBugSummary);

		assertEquals(numberFoundBugs, searchResultsPage.getAmountOfBugs());
		assertEquals(firstBugSummary, searchResultsPage.getSummaryOfFirstBug());

		// forget saved search
		searchResultsPage.forgetSavedSearch("SearchFor_" + currentBugSummary);
	}

}
