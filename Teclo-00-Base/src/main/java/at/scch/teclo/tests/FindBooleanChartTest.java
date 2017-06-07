package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.pageobjects.AdvancedSearchPage;
import at.scch.teclo.pageobjects.BugResultsPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.SearchBasePage;

public class FindBooleanChartTest extends AbstractBugzillaTestWithLogin {
	private int currentBugID;
	private String currentBugSummary;
	private String originalBugPrio;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
		currentBugSummary = BugzillaSetup.getExampleBugSummary();

		// precondition: bug changed to Priority P3
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);
		originalBugPrio = editBugPage.getCurrentPriority();
		editBugPage.editPriority("P3");
		editBugPage.commitBug();
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
		// postcondition: change bug back to Priority P5
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);
		editBugPage.editPriority(originalBugPrio);
		editBugPage.commitBug();
	}

}
