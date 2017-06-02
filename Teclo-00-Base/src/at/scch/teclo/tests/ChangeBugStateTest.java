package at.scch.teclo.tests;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.MyBugsPage;

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

	@Test
	public void testChangeBugState() throws Exception {

		MyBugsPage myBugsPage = loggedInBasePage.navigateToMyBugsPage();

		int currentAmountOfBugs = myBugsPage.getAmountOfBugs();

		EditBugPage editBugPage = myBugsPage.selectBug(currentBugID);

		try {
			assertEquals("NEW", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		editBugPage.changeBugState("ASSIGNED");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		try {
			assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		editBugPage.changeBugState("RESOLVED");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		try {
			assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		editBugPage.changeBugState("VERIFIED");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		try {
			assertEquals("VERIFIED", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		editBugPage.changeBugState("CLOSED");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		try {
			assertEquals("CLOSED", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

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

		try {
			assertEquals("REOPENED", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		editBugPage.changeBugState("NEW");
		editBugPage = editBugPage.commitBug();

		editBugPage = editBugPage.selectCommitedBug(currentBugID);

		try {
			assertEquals("NEW", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		myBugsPage = editBugPage.navigateToMyBugsPage();

		try {
			assertEquals(currentAmountOfBugs, myBugsPage.getAmountOfBugs());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@After
	public void tearDown() throws Exception {
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
