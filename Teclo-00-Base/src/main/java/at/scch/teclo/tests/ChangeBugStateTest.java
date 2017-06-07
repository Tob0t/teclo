package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.BugCommitedPage;
import at.scch.teclo.pageobjects.EditBugPage;

public class ChangeBugStateTest extends BugzillaTest {
	private int currentBugID;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
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
