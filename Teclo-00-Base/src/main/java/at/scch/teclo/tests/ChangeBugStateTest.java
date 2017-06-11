package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.BugChangedPage;
import at.scch.teclo.pageobjects.EditBugPage;

public class ChangeBugStateTest extends AbstractBugzillaTestWithLogin {
	private int currentBugId;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugId = BugzillaSetup.getExampleBugID();
	}

	@Test
	public void testNormalStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);

		assertEquals("NEW", editBugPage.getBugStatus());
		editBugPage.setBugStatus("ASSIGNED");
		BugChangedPage bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("RESOLVED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("VERIFIED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("VERIFIED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("CLOSED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("REOPENED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("REOPENED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("NEW");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("NEW", editBugPage.getBugStatus());
	}

	@Test
	public void testFastStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);

		assertEquals("NEW", editBugPage.getBugStatus());
		editBugPage.setBugStatus("RESOLVED");
		BugChangedPage bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("RESOLVED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("CLOSED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("REOPENED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("REOPENED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("NEW");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("NEW", editBugPage.getBugStatus());
	}

	@Test
	public void testLongStateCycle() throws Exception {

		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);

		assertEquals("NEW", editBugPage.getBugStatus());
		editBugPage.setBugStatus("ASSIGNED");
		BugChangedPage bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("NEW");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("NEW", editBugPage.getBugStatus());
		editBugPage.setBugStatus("ASSIGNED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("RESOLVED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("REOPENED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("REOPENED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("ASSIGNED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("RESOLVED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("VERIFIED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("VERIFIED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("CLOSED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("REOPENED");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("REOPENED", editBugPage.getBugStatus());
		editBugPage.setBugStatus("NEW");
		bugChangedPage = editBugPage.commitBug();

		editBugPage = bugChangedPage.gotoChangedBug();
		assertEquals("NEW", editBugPage.getBugStatus());
	}

	@Test
	public void testMarkAsDuplicate() {
		
		// create second bug to mark as duplicate
		int duplicateBugID = BugzillaSetup.createExampleBug();
		
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);
				
		// mark the bug as duplicate of current bug
		editBugPage.clickMarkAsDuplicate();
		editBugPage.setBugDuplicateOf(duplicateBugID);
		
		// commit
		BugChangedPage bugChangedPage = editBugPage.commitBug();
		assertEquals("Changes submitted for bug " + currentBugId, bugChangedPage.getSuccessMsg());
		editBugPage = bugChangedPage.gotoChangedBug();

		
		// verify on duplicated bug
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("DUPLICATE", editBugPage.getBugResolution());
		assertEquals("*** This bug has been marked as a duplicate of bug "+duplicateBugID+" ***", editBugPage.getLastComment());
		
		// go to original bug
		editBugPage = BugzillaSetup.gotoBugPage(duplicateBugID);
		
		// verify on current bug
		assertEquals("*** Bug "+currentBugId+" has been marked as a duplicate of this bug. ***", editBugPage.getLastComment());
	}
}
