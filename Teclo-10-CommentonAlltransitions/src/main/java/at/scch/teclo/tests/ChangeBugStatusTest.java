package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.BugChangedPage;
import at.scch.teclo.pageobjects.EditBugPage;

public class ChangeBugStatusTest extends AbstractBugzillaTestWithLogin {
	private int currentBugId;

	@Before
	public void setUp() throws Exception {
		currentBugId = BugzillaSetup.getExampleBugId();
	}

	@Test
	public void testNormalStatusCycle() {

		EditBugPage editBugPage = BugzillaSetup.gotoEditBugPage(currentBugId);
		assertEquals("NEW", editBugPage.getBugStatus());

		editBugPage.setBugStatus("ASSIGNED");
		editBugPage.setComment("set assigned");
		BugChangedPage bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		
		editBugPage.setBugStatus("RESOLVED");
		editBugPage.setComment("set resolved");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());
		
		editBugPage.setBugStatus("VERIFIED");
		editBugPage.setComment("set verified");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("VERIFIED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());
		
		editBugPage.setBugStatus("CLOSED");
		editBugPage.setComment("set closed");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());
		
		editBugPage.setBugStatus("REOPENED");
		editBugPage.setComment("set reopened");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("REOPENED", editBugPage.getBugStatus());
		
		editBugPage.setBugStatus("NEW");
		editBugPage.setComment("set new");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("NEW", editBugPage.getBugStatus());
	}

	@Test
	public void testFastStatusCycle() {

		EditBugPage editBugPage = BugzillaSetup.gotoEditBugPage(currentBugId);
		assertEquals("NEW", editBugPage.getBugStatus());

		editBugPage.setBugStatus("RESOLVED");
		editBugPage.setComment("set resolved");
		BugChangedPage bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());

		editBugPage.setBugStatus("CLOSED");
		editBugPage.setComment("set closed");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());
		
		editBugPage.setBugStatus("REOPENED");
		editBugPage.setComment("set reopened");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("REOPENED", editBugPage.getBugStatus());
		
		editBugPage.setBugStatus("NEW");
		editBugPage.setComment("set new");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("NEW", editBugPage.getBugStatus());
	}
	
	@Test
	public void testResolutions() {
		EditBugPage editBugPage = BugzillaSetup.gotoEditBugPage(currentBugId);
		assertEquals("NEW", editBugPage.getBugStatus());

		editBugPage.setBugStatus("RESOLVED");
		editBugPage.setBugResolution("WORKSFORME");
		editBugPage.setComment("set resolved");
		BugChangedPage bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("WORKSFORME", editBugPage.getBugResolution());

		editBugPage.setBugResolution("FIXED");
		editBugPage.setComment("set fixed");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());	

		editBugPage.setBugStatus("CLOSED");
		editBugPage.setBugResolution("WONTFIX");
		editBugPage.setComment("set closed");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		assertEquals("WONTFIX", editBugPage.getBugResolution());	
		
		editBugPage.setBugResolution("INVALID");
		editBugPage.setComment("set invalid");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		assertEquals("INVALID", editBugPage.getBugResolution());
		
		editBugPage.setBugStatus("REOPENED");
		editBugPage.setComment("set reopened");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("REOPENED", editBugPage.getBugStatus());
		
		editBugPage.setBugStatus("NEW");
		editBugPage.setComment("set new");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("NEW", editBugPage.getBugStatus());
	}

	@Test
	public void testMarkAsDuplicate() {
		
		// create second bug to mark as duplicate
		int duplicateBugId = BugzillaSetup.createExampleBug();
		
		EditBugPage editBugPage = BugzillaSetup.gotoEditBugPage(currentBugId);
		
		editBugPage.setComment("marked as duplicate");
		
		// mark the bug as duplicate of current bug
		editBugPage.clickMarkAsDuplicate();
		editBugPage.setBugDuplicateOf(duplicateBugId);
		
		// commit
		BugChangedPage bugChangedPage = editBugPage.commitBug();
		assertEquals("Changes submitted for bug " + currentBugId, bugChangedPage.getSuccessMsg());
		editBugPage = bugChangedPage.gotoChangedBugPage();

		
		// verify on duplicated bug
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("DUPLICATE", editBugPage.getBugResolution());
		assertEquals("marked as duplicate  *** This bug has been marked as a duplicate of bug "+duplicateBugId+" ***", editBugPage.getLastComment());
		
		// go to original bug
		editBugPage = BugzillaSetup.gotoEditBugPage(duplicateBugId);
		
		// verify on current bug
		assertEquals("*** Bug "+currentBugId+" has been marked as a duplicate of this bug. ***", editBugPage.getLastComment());
	}
}
