package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.SearchResultsPage;
import at.scch.teclo.pageobjects.ErrorParametersRequiredPage;
import at.scch.teclo.pageobjects.SearchBasePage;
import at.scch.teclo.pageobjects.SearchSpecificPage;

public class SearchSpecificTest extends AbstractBugzillaTestWithLogin {

	private String currentBugSummary;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugSummary = BugzillaSetup.getExampleBugSummary();
	}

	@Test
	public void testFindBugZarro() throws Exception {
		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();

		searchSpecificPage.setBugStatus("Closed");
		SearchResultsPage searchResultsPage = searchSpecificPage.searchFor(currentBugSummary.replace("_", "-"));

		assertEquals("More than 0 bugs found!", 0, searchResultsPage.getAmountOfBugs());
	}

	@Test
	public void testFindBugSingle() throws Exception {
		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();

		searchSpecificPage.setBugStatus("All");
		SearchResultsPage searchResultsPage = searchSpecificPage.searchFor(currentBugSummary);

		assertEquals("Not exactly one bug found!", 1, searchResultsPage.getAmountOfBugs());
		assertEquals(BugzillaSetup.getExampleBugSummary(), searchResultsPage.getSummaryOfFirstBug());
	}

	@Test
	public void testFindBugMultiple() throws Exception {
		// add one more bug to make sure that there are at least 2 or more bugs
		// in the database
		BugzillaSetup.createExampleBug();

		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();

		searchSpecificPage.setBugStatus("All");
		SearchResultsPage searchResultsPage = searchSpecificPage.searchFor("Bug*");

		assertTrue("No multiple bugs found", 1 < searchResultsPage.getAmountOfBugs());
	}

//	@Test
//	public void testSearchBlanksResultsInError() {
//		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
//		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();
//
//		ErrorParametersRequiredPage errorParametersRequiredPage = searchSpecificPage.searchForBlanks();
//
//		assertEquals("You may not search, or create saved searches, without any search terms.",
//				errorParametersRequiredPage.getErrorMsg());
//	}

	@Test
	public void testSearchEmptyResultsInMatchingAll() {
		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();

		SearchResultsPage searchResultsPage = searchSpecificPage.searchForEmpty();

		assertTrue("No multiple bugs found", 1 < searchResultsPage.getAmountOfBugs());
	}
}
