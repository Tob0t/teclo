package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

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

		assertEquals("NEW", editBugPage.getBugStatus());
		editBugPage.setBugStatus("ASSIGNED");
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("RESOLVED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("VERIFIED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("VERIFIED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("CLOSED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("CLOSED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("REOPENED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("NEW");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("NEW", editBugPage.getBugStatus());
	}

	@Test
	public void testFastStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);

		assertEquals("NEW", editBugPage.getBugStatus());
		editBugPage.setBugStatus("RESOLVED");
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("RESOLVED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("CLOSED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("CLOSED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("REOPENED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("NEW");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("NEW", editBugPage.getBugStatus());
	}

	@Test
	public void testLongStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);

		assertEquals("NEW", editBugPage.getBugStatus());
		editBugPage.setBugStatus("ASSIGNED");
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("NEW");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("NEW", editBugPage.getBugStatus());
		editBugPage.setBugStatus("ASSIGNED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("RESOLVED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("REOPENED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("ASSIGNED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("RESOLVED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("VERIFIED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("VERIFIED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("CLOSED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("CLOSED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("REOPENED");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("REOPENED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("NEW");
		bugCommittedPage = editBugPage.commitBug();

		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("NEW", editBugPage.getBugStatus());
	}

	@Test
	public void testMarkAsDuplicate() {
		
		// create second bug to mark as duplicate
		int duplicateBugID = BugzillaSetup.createExampleBug();
		
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);
				
		// mark the bug as duplicate of current bug
		editBugPage.clickMarkAsDuplicate();
		editBugPage.setBugDuplicateOf(duplicateBugID);
		
		// commit
		editBugPage = editBugPage.commitBug().selectCommittedBug(currentBugID);
		
		// verify on duplicated bug
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("DUPLICATE", editBugPage.getBugResolution());
		assertEquals("*** This bug has been marked as a duplicate of bug "+duplicateBugID+" ***", editBugPage.getLastComment());
		
		// go to original bug
		editBugPage = BugzillaSetup.gotoBugPage(duplicateBugID);
		
		// verify on current bug
		assertEquals("*** Bug "+currentBugID+" has been marked as a duplicate of this bug. ***", editBugPage.getLastComment());
	}
}
