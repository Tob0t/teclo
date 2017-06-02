package at.scch.teclo.tests;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.ResultsPage;

public class ChangeBugStateTest extends BugzillaTest {
	//private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;

	@Before
	public void setUp() throws Exception {
		//driver = BugzillaSetup.getWebDriver();

		// precondition: logged in
		loggedInBasePage = BugzillaSetup.login();

		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBug(loggedInBasePage);
	}

	// TODO: multiple tests with different paths
	@Test
	public void testChangeBugState() throws Exception {

		// TODO: navigate directly to example bug
		ResultsPage myBugsPage = loggedInBasePage.navigateToMyBugsPage();

		int currentAmountOfBugs = myBugsPage.getAmountOfBugs();

		EditBugPage editBugPage = myBugsPage.selectBug(currentBugID);

		assertEquals("NEW", editBugPage.getCurrentBugState());
		
		editBugPage.changeBugState("ASSIGNED");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		

		editBugPage.changeBugState("RESOLVED");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());


		editBugPage.changeBugState("VERIFIED");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		assertEquals("VERIFIED", editBugPage.getCurrentBugState());

		editBugPage.changeBugState("CLOSED");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		assertEquals("CLOSED", editBugPage.getCurrentBugState());
		
		// TODO: search for the bug to access it when its closed
		/*
		 * myBugsPage = editBugPage.navigateToMyBugsPage();
		 * 
		 * try { assertEquals(currentAmountOfBugs-1,
		 * myBugsPage.getAmountOfBugs()); } catch (Error e) {
		 * verificationErrors.append(e.toString()); }
		 * 
		 * editBugPage = editBugPage.selectCommitedBug(currentBugId);
		 */

		editBugPage.changeBugState("REOPENED");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		
		assertEquals("REOPENED", editBugPage.getCurrentBugState());
		

		editBugPage.changeBugState("NEW");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		
		assertEquals("NEW", editBugPage.getCurrentBugState());
		

		//myBugsPage = editBugPage.navigateToMyBugsPage();

		
		//assertEquals(currentAmountOfBugs, myBugsPage.getAmountOfBugs());
		

	}

}
