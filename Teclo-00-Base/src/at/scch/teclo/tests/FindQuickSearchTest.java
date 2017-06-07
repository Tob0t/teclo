package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.BugResultsPage;
import at.scch.teclo.pageobjects.EditBugPage;

public class FindQuickSearchTest extends BugzillaTest {
	private int currentBugID;
	private String currentBugSummary;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
		currentBugSummary = BugzillaSetup.getExampleBugSummary();
	}

	@Test
	public void testFindBugZarro() throws Exception {
		BugResultsPage bugResultsPage = loggedInBasePage.searchFor(currentBugSummary.replace("_", "-"));
		assertEquals("More than 0 bugs found!", 0, bugResultsPage.getAmountOfBugs());
	}
	
	@Test
	public void testFindBugSingleByName() throws Exception {
		BugResultsPage bugResultsPage = loggedInBasePage.searchFor(currentBugSummary);

		assertEquals("No bug found!", 1, bugResultsPage.getAmountOfBugs());
		assertEquals(currentBugSummary, bugResultsPage.getSummaryOfFirstBug());
	}
	
	@Test
	public void testFindBugSingleByID() throws Exception {
		EditBugPage editBugPage = loggedInBasePage.searchFor(currentBugID);
		assertEquals("Bug not found by ID", currentBugSummary, editBugPage.getSummary());
	}
	
	@Test
	public void testFindBugMultiple() throws Exception {
		// add one more bug to make sure that there are at least 2 or more bugs in the database
		BugzillaSetup.createExampleBug();
		
		BugResultsPage bugResultsPage = loggedInBasePage.searchFor("Bug");
		assertTrue("No multiple bugs found", 1 < bugResultsPage.getAmountOfBugs());
	}

}
