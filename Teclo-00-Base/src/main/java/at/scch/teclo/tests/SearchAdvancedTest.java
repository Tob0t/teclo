package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.SearchAdvancedPage;
import at.scch.teclo.pageobjects.SearchResultsPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.SearchBasePage;

public class SearchAdvancedTest extends AbstractBugzillaTestWithLogin {
	private int currentBugID;
	private String currentBugSummary;
	private SearchResultsPage searchResultsPage;

	private String currentBugStatus;
	private String currentBugPriority;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
		currentBugSummary = BugzillaSetup.getExampleBugSummary();

		// precondition: bug changed
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);
		currentBugStatus = editBugPage.getBugStatus();
		editBugPage.setBugStatus("ASSIGNED");
		currentBugPriority = editBugPage.getPriority();
		editBugPage.setPriority("P3");
		editBugPage.commitBug();
	}

	@Test
	public void testFindBugZarro() throws Exception {
		SearchBasePage searchPage = startPage.gotoSearchBasePage();
		SearchAdvancedPage searchAdvancedPage = searchPage.gotoAdvancedSearchPage();

		searchAdvancedPage.deselectBugStatus("NEW");
		searchAdvancedPage.deselectBugStatus("ASSIGNED");
		searchAdvancedPage.deselectBugStatus("REOPENED");

		searchAdvancedPage.selectBugStatus("RESOLVED");
		searchAdvancedPage.fillSummary(currentBugSummary.replace("_", "-"));
		searchResultsPage = searchAdvancedPage.submitSearch();

		assertEquals("More than 0 bugs found!", 0, searchResultsPage.getAmountOfBugs());
	}

	@Test
	public void testFindBugSingle() throws Exception {
		SearchBasePage searchPage = startPage.gotoSearchBasePage();
		SearchAdvancedPage searchAdvancedPage = searchPage.gotoAdvancedSearchPage();

		searchAdvancedPage.deselectBugStatus("NEW");
		searchAdvancedPage.deselectBugStatus("ASSIGNED");
		searchAdvancedPage.deselectBugStatus("REOPENED");

		searchAdvancedPage.selectBugStatus("ASSIGNED");
		searchAdvancedPage.fillSummary(currentBugSummary);
		searchResultsPage = searchAdvancedPage.submitSearch();

		assertEquals("Not exactly one bug found!", 1, searchResultsPage.getAmountOfBugs());
		assertEquals("ASSI", searchResultsPage.getStatusOfFirstBug());
		assertEquals(currentBugSummary, searchResultsPage.getSummaryOfFirstBug());
	}

	@Test
	public void testFindBugAll() throws Exception {
		SearchBasePage searchPage = startPage.gotoSearchBasePage();
		SearchAdvancedPage searchAdvancedPage = searchPage.gotoAdvancedSearchPage();

		searchAdvancedPage.deselectBugStatus("NEW");
		searchAdvancedPage.deselectBugStatus("ASSIGNED");
		searchAdvancedPage.deselectBugStatus("REOPENED");

		searchAdvancedPage.setSummarySearchType("matches regular expression");
		searchAdvancedPage.fillSummary(".*");

		searchResultsPage = searchAdvancedPage.submitSearch();

		assertTrue("No bugs found", 1 < searchResultsPage.getAmountOfBugs());
	}

	@Test
	public void testBooleanChart() throws Exception {
		SearchBasePage searchPage = startPage.gotoSearchBasePage();
		SearchAdvancedPage searchAdvancedPage = searchPage.gotoAdvancedSearchPage();

		searchAdvancedPage.fillBooleanChart("Priority", "is equal to", "P3");
		searchAdvancedPage.fillSummary(currentBugSummary);
		SearchResultsPage searchResultsPage = searchAdvancedPage.submitSearch();

		assertEquals("No bug found!", 1, searchResultsPage.getAmountOfBugs());
		assertEquals("P3", searchResultsPage.getPriorityOfFirstBug());
		assertEquals(currentBugSummary, searchResultsPage.getSummaryOfFirstBug());
	}

	@After
	public void tearDown() throws Exception {

		// postcondition: change bug back
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);
		editBugPage.setBugStatus(currentBugStatus);
		editBugPage.setPriority(currentBugPriority);
		editBugPage.commitBug();
	}

}
