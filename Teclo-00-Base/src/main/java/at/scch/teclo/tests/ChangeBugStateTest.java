package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.BugCommittedPage;
import at.scch.teclo.pageobjects.EditBugPage;

public class ChangeBugStateTest extends AbstractBugzillaTestWithLogin {
	private int currentBugID;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
	}

	@Test
	public void testNormalStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);

		assertEquals("NEW", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("ASSIGNED");
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("RESOLVED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("VERIFIED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("VERIFIED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("CLOSED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("CLOSED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("REOPENED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("NEW");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("NEW", editBugPage.getCurrentBugState());
	}

	@Test
	public void testFastStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);

		assertEquals("NEW", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("RESOLVED");
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("RESOLVED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("CLOSED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("CLOSED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("REOPENED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("NEW");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("NEW", editBugPage.getCurrentBugState());
	}

	@Test
	public void testLongStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);

		assertEquals("NEW", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("ASSIGNED");
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("NEW");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("NEW", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("ASSIGNED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("RESOLVED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("REOPENED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("ASSIGNED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("RESOLVED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("VERIFIED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("VERIFIED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("CLOSED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("CLOSED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("REOPENED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getCurrentBugState());
		editBugPage.changeBugState("NEW");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("NEW", editBugPage.getCurrentBugState());
	}

	@Test
	public void testMarkAsDuplicate() {
		
		// create second bug to mark as duplicate
		BugzillaSetup.createExampleBug();
		int duplicateBugID = BugzillaSetup.getExampleBugID();
		
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);
				
		// mark the bug as duplicate of current bug
		editBugPage.clickMarkAsDuplicate();
		editBugPage.editBugDuplicateOf(duplicateBugID);
		
		// commit
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		
		// verify on duplicated bug
		assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		assertEquals("DUPLICATE", editBugPage.getCurrentBugResolution());
		assertEquals("*** This bug has been marked as a duplicate of bug "+duplicateBugID+" ***", editBugPage.getLastCommentContent());
		
		// go to original bug
		editBugPage = BugzillaSetup.gotoBugPage(duplicateBugID);
		
		// verify on current bug
		assertEquals("*** Bug "+currentBugID+" has been marked as a duplicate of this bug. ***", editBugPage.getLastCommentContent());
	}
}
