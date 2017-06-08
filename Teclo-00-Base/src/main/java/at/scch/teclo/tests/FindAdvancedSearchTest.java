package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.pageobjects.AdvancedSearchPage;
import at.scch.teclo.pageobjects.BugCommittedPage;
import at.scch.teclo.pageobjects.BugResultsPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.SearchBasePage;

public class FindAdvancedSearchTest extends AbstractBugzillaTestWithLogin {
	private int currentBugID;
	private String currentBugSummary;
	private BugResultsPage bugResultsPage;
	
	private String currentBugState;
	private String currentBugPriority;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
		currentBugSummary = BugzillaSetup.getExampleBugSummary();
		
		// precondition: bug changed
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);
		currentBugState = editBugPage.getCurrentBugState();
		editBugPage.changeBugState("ASSIGNED");
		currentBugPriority = editBugPage.getCurrentPriority();
		editBugPage.editPriority("P3");
		editBugPage.commitBug();
	}

	@Test
	public void testFindBugZarro() throws Exception {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		AdvancedSearchPage advancedSearchPage = searchPage.navigateToAdvancedSearchPage();

		advancedSearchPage.deselectBugState("NEW");
		advancedSearchPage.deselectBugState("ASSIGNED");
		advancedSearchPage.deselectBugState("REOPENED");

		advancedSearchPage.selectBugState("RESOLVED");
		advancedSearchPage.fillSummary(currentBugSummary.replace("_", "-"));
		bugResultsPage = advancedSearchPage.submitSearch();

		assertEquals("More than 0 bugs found!", 0, bugResultsPage.getAmountOfBugs());
	}

	@Test
	public void testFindBugSingle() throws Exception {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		AdvancedSearchPage advancedSearchPage = searchPage.navigateToAdvancedSearchPage();

		advancedSearchPage.deselectBugState("NEW");
		advancedSearchPage.deselectBugState("ASSIGNED");
		advancedSearchPage.deselectBugState("REOPENED"
				);
		advancedSearchPage.selectBugState("ASSIGNED");
		advancedSearchPage.fillSummary(currentBugSummary);
		bugResultsPage = advancedSearchPage.submitSearch();

		assertEquals("Not exactly one bug found!", 1, bugResultsPage.getAmountOfBugs());
		assertEquals("ASSI", bugResultsPage.getStateOfFirstBug());
		assertEquals(currentBugSummary, bugResultsPage.getSummaryOfFirstBug());
	}

	@Test
	public void testFindBugAll() throws Exception {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		AdvancedSearchPage advancedSearchPage = searchPage.navigateToAdvancedSearchPage();

		advancedSearchPage.deselectBugState("NEW");
		advancedSearchPage.deselectBugState("ASSIGNED");
		advancedSearchPage.deselectBugState("REOPENED");
		
		advancedSearchPage.setSummarySearchType("matches regular expression");
		advancedSearchPage.fillSummary(".*");
		
		bugResultsPage = advancedSearchPage.submitSearch();

		assertTrue("No bugs found", 1 < bugResultsPage.getAmountOfBugs());
	}

	@Test
	public void testBooleanChart() throws Exception {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		AdvancedSearchPage advancedSearchPage = searchPage.navigateToAdvancedSearchPage();

		advancedSearchPage.fillBooleanChart("Priority", "is equal to", "P3");
		advancedSearchPage.fillSummary(currentBugSummary);
		BugResultsPage bugResultsPage = advancedSearchPage.submitSearch();

		assertEquals("No bug found!", 1, bugResultsPage.getAmountOfBugs());
		assertEquals("P3", bugResultsPage.getPriorityOfFirstBug());
		assertEquals(currentBugSummary, bugResultsPage.getSummaryOfFirstBug());
	}
	
	@After
	public void tearDown() throws Exception {

		// postcondition: change bug back
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);
		editBugPage.changeBugState(currentBugState);
		editBugPage.editPriority(currentBugPriority);
		editBugPage.commitBug();
	}

}
