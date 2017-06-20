package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.BugChangedPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.ErrorCommentRequiredPage;

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
		BugChangedPage bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		
		editBugPage.setBugStatus("RESOLVED");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());
		
		editBugPage.setBugStatus("VERIFIED");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("VERIFIED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());
		
		editBugPage.setBugStatus("CLOSED");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());
		
		editBugPage.setBugStatus("REOPENED");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("REOPENED", editBugPage.getBugStatus());
		
		editBugPage.setBugStatus("NEW");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("NEW", editBugPage.getBugStatus());
	}

	@Test
	public void testFastStatusCycle() {

		EditBugPage editBugPage = BugzillaSetup.gotoEditBugPage(currentBugId);
		assertEquals("NEW", editBugPage.getBugStatus());

		editBugPage.setBugStatus("RESOLVED");
		BugChangedPage bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());

		editBugPage.setBugStatus("CLOSED");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());
		
		editBugPage.setBugStatus("REOPENED");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("REOPENED", editBugPage.getBugStatus());
		
		editBugPage.setBugStatus("NEW");
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
		BugChangedPage bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("WORKSFORME", editBugPage.getBugResolution());

		editBugPage.setBugResolution("FIXED");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("FIXED", editBugPage.getBugResolution());	

		editBugPage.setBugStatus("CLOSED");
		editBugPage.setBugResolution("WONTFIX");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		assertEquals("WONTFIX", editBugPage.getBugResolution());	
		
		editBugPage.setBugResolution("INVALID");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("CLOSED", editBugPage.getBugStatus());
		assertEquals("INVALID", editBugPage.getBugResolution());
		
		editBugPage.setBugStatus("REOPENED");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("REOPENED", editBugPage.getBugStatus());
		
		editBugPage.setBugStatus("NEW");
		bugChangedPage = editBugPage.commitBug();
		editBugPage = bugChangedPage.gotoChangedBugPage();
		assertEquals("NEW", editBugPage.getBugStatus());
	}
	
	@Test
	public void testMarkAsDuplicate() {
		
		// create second bug to mark as duplicate
		int duplicateBugId = BugzillaSetup.createExampleBug();
		
		EditBugPage editBugPage = BugzillaSetup.gotoEditBugPage(currentBugId);
				
		// mark the bug as duplicate of current bug
		editBugPage.clickMarkAsDuplicate();
		editBugPage.setBugDuplicateOf(duplicateBugId);
		
		// commit
		String comment = "Example comment for marking duplicate.";
		editBugPage.setComment(comment);
		BugChangedPage bugChangedPage = editBugPage.commitBug();
		assertEquals("Changes submitted for bug " + currentBugId, bugChangedPage.getSuccessMsg());
		editBugPage = bugChangedPage.gotoChangedBugPage();

		
		// verify on duplicated bug
		String automaticallyInsertedLinebreaks = "  ";
		assertEquals("RESOLVED", editBugPage.getBugStatus());
		assertEquals("DUPLICATE", editBugPage.getBugResolution());
		assertEquals(comment+automaticallyInsertedLinebreaks+"*** This bug has been marked as a duplicate of bug "+duplicateBugId+" ***", editBugPage.getLastComment());
		
		// go to original bug
		editBugPage = BugzillaSetup.gotoEditBugPage(duplicateBugId);
		
		// verify on current bug
		assertEquals("*** Bug "+currentBugId+" has been marked as a duplicate of this bug. ***", editBugPage.getLastComment());
	}
	
	@Test
	public void testMarkAsDuplicateWithouComment() {
		
		// create second bug to mark as duplicate
		int duplicateBugId = BugzillaSetup.createExampleBug();
		
		EditBugPage editBugPage = BugzillaSetup.gotoEditBugPage(currentBugId);
				
		// mark the bug as duplicate of current bug
		editBugPage.clickMarkAsDuplicate();
		editBugPage.setBugDuplicateOf(duplicateBugId);
		
		// commit
		ErrorCommentRequiredPage errorCommentRequiredPage = editBugPage.commitBugWithEmptyComment();
		assertEquals("You have to specify a comment on this change.",
				errorCommentRequiredPage.getErrorMsg());
	}
}
