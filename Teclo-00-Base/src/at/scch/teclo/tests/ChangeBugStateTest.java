package at.scch.teclo.tests;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.BugCommitedPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.ResultsPage;

public class ChangeBugStateTest extends BugzillaTest {
	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;

	@Before
	public void setUp() throws Exception {
		
		// precondition: logged in
		loggedInBasePage = BugzillaSetup.login();
		System.out.println("login");

		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
		System.out.println("getExampleBug");
	}

	@Test
	public void testNormalStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		assertEquals("NEW", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("ASSIGNED");
		BugCommitedPage bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("RESOLVED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("VERIFIED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("VERIFIED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("CLOSED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("CLOSED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("REOPENED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("NEW");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("NEW", editBugPage.getCurrentBugState());	
	}

	@Test
	public void testFastStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		assertEquals("NEW", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("RESOLVED");
		BugCommitedPage bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("RESOLVED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("CLOSED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("CLOSED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("REOPENED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("NEW");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("NEW", editBugPage.getCurrentBugState());	
	}
	
	@Test
	public void testLongStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		assertEquals("NEW", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("ASSIGNED");
		BugCommitedPage bugCommitedPage = editBugPage.commitBug();
		
		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("NEW");
		bugCommitedPage = editBugPage.commitBug();
		
		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("NEW", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("ASSIGNED");
		bugCommitedPage = editBugPage.commitBug();
		
		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("RESOLVED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("REOPENED");
		bugCommitedPage = editBugPage.commitBug();
		
		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("ASSIGNED");
		bugCommitedPage = editBugPage.commitBug();
		
		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("RESOLVED");
		bugCommitedPage = editBugPage.commitBug();
		
		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("VERIFIED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("VERIFIED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("CLOSED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("CLOSED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("REOPENED");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("NEW");
		bugCommitedPage = editBugPage.commitBug();

		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		assertEquals("NEW", editBugPage.getCurrentBugState());	
	}

}
