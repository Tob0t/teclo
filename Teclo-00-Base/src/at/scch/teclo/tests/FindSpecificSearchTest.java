package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.ParametersRequiredErrorPage;
import at.scch.teclo.pageobjects.BugResultsPage;
import at.scch.teclo.pageobjects.SearchBasePage;
import at.scch.teclo.pageobjects.SpecificSearchPage;

public class FindSpecificSearchTest extends BugzillaTest {

	private LoggedInBasePage loggedInBasePage;
	private String currentBugSummary;

	@Before
	public void setUp() throws Exception {
		// precondition: logged in
		loggedInBasePage = homeBasePage.loginAdmin();

		// precondition: bug inserted
		currentBugSummary = BugzillaSetup.getExampleBugSummary();
		
		// go to home base page
		BugzillaSetup.navigateToHomeBasePage();
	}

	@Test
	public void testFindBugZarro() throws Exception {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		SpecificSearchPage specificSearchPage = searchPage.navigateToSpecificSearchPage();

		specificSearchPage.selectBugState("Closed");
		BugResultsPage bugResultsPage = specificSearchPage.searchFor(currentBugSummary.replace("_", "-"));

		assertEquals("More than 0 bugs found!", 0, bugResultsPage.getAmountOfBugs());
	}
	
	@Test
	public void testFindBugSingle() throws Exception {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		SpecificSearchPage specificSearchPage = searchPage.navigateToSpecificSearchPage();

		specificSearchPage.selectBugState("All");
		BugResultsPage bugResultsPage = specificSearchPage.searchFor(currentBugSummary);

		assertEquals("Not exactly one bug found!", 1, bugResultsPage.getAmountOfBugs());
		assertEquals(BugzillaSetup.getExampleBugSummary(), bugResultsPage.getSummaryOfFirstBug());
	}
	
	@Test
	public void testFindBugMultiple() throws Exception {
		// add one more bug to make sure that there are at least 2 or more bugs in the database
		BugzillaSetup.createExampleBug();
		
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		SpecificSearchPage specificSearchPage = searchPage.navigateToSpecificSearchPage();

		specificSearchPage.selectBugState("All");
		BugResultsPage bugResultsPage = specificSearchPage.searchFor("Bug*");

		assertTrue("No multiple bugs found", 1 < bugResultsPage.getAmountOfBugs());
	}
	
	@Test
	public void testSearchBlanksResultsInError() {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		SpecificSearchPage specificSearchPage = searchPage.navigateToSpecificSearchPage();

		ParametersRequiredErrorPage parametersRequiredErrorPage = specificSearchPage.searchForBlanks();

		assertEquals("You may not search, or create saved searches, without any search terms.", parametersRequiredErrorPage.getErrorMsg());
	}
	
	@Test
	public void testSearchEmptyResultsInAlertPopup() {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		SpecificSearchPage specificSearchPage = searchPage.navigateToSpecificSearchPage();

		String alertMsg = specificSearchPage.searchForEmpty();

		assertEquals("The Words field cannot be empty. You have to enter at least one word in your search criteria.", alertMsg);
	}
}
