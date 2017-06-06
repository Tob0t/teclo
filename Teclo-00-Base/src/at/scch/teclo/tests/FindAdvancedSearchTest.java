package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.AdvancedSearchPage;
import at.scch.teclo.pageobjects.BugCommitedPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.BugResultsPage;
import at.scch.teclo.pageobjects.SearchBasePage;

public class FindAdvancedSearchTest extends BugzillaTest {
	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;
	private BugResultsPage bugResultsPage;

	@Before
	public void setUp() throws Exception {
		// precondition: logged in
		loggedInBasePage = homeBasePage.loginAdmin();

		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();

		// precondition: bug changed to RESOLVED
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);
		editBugPage.changeBugState("RESOLVED");
		editBugPage.commitBug();
		
		// go to home base page
		BugzillaSetup.navigateToHomeBasePage();
	}
	
	@Test
	public void testFindBugZarro() throws Exception {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		AdvancedSearchPage advancedSearchPage = searchPage.navigateToAdvancedSearchPage();

		advancedSearchPage.deselectBugState("NEW");
		advancedSearchPage.deselectBugState("ASSIGNED");
		advancedSearchPage.deselectBugState("REOPENED");

		advancedSearchPage.selectBugState("RESOLVED");
		advancedSearchPage.fillSummary(BugzillaSetup.getExampleBugName().replace("_", "-"));
		bugResultsPage = advancedSearchPage.submitSearch();

		assertEquals("More than 0 bugs found!", 0, bugResultsPage.getAmountOfBugs());
	}

	@Test
	public void testFindBugSingle() throws Exception {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		AdvancedSearchPage advancedSearchPage = searchPage.navigateToAdvancedSearchPage();

		advancedSearchPage.deselectBugState("NEW");
		advancedSearchPage.deselectBugState("ASSIGNED");
		advancedSearchPage.deselectBugState("REOPENED");

		advancedSearchPage.selectBugState("RESOLVED");
		advancedSearchPage.fillSummary(BugzillaSetup.getExampleBugName());
		bugResultsPage = advancedSearchPage.submitSearch();

		assertEquals("Not exactly one bug found!", 1, bugResultsPage.getAmountOfBugs());
		assertEquals("RESO", bugResultsPage.getStateOfFirstBug());
		assertEquals(BugzillaSetup.getExampleBugName(), bugResultsPage.getSummaryOfFirstBug());
	}
	
	@Test
	public void testFindBugMultiple() throws Exception {
		// add one more bug to make sure that there are at least 2 or more bugs in the database
		BugzillaSetup.createExampleBug();
		
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		AdvancedSearchPage advancedSearchPage = searchPage.navigateToAdvancedSearchPage();

		advancedSearchPage.selectBugState("RESOLVED");
		bugResultsPage = advancedSearchPage.submitSearch();

		assertTrue("No multiple bugs found", 1 < bugResultsPage.getAmountOfBugs());
	}
	


	@After
	public void tearDown() throws Exception {

		// postcondition: change bug back to state NEW
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);
		editBugPage.changeBugState("REOPENED");
		BugCommitedPage bugCommitedPage = editBugPage.commitBug();
		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		editBugPage.changeBugState("NEW");
		editBugPage.commitBug();
	}

}
