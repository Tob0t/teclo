package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.BugCommittedPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.SummaryNeededErrorPage;

public class EditBugTest extends AbstractBugzillaTestWithLogin {
	private int currentBugID;
	private String currentBugSummary;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
		currentBugSummary = BugzillaSetup.getExampleBugSummary();
	}

	@Test
	public void testEditSummary() throws Exception {
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		editBugPage.editSummary("Test Summary !\"§$%&/(=?\\#*1234567890\'.:;,");
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("Test Summary !\"§$%&/(=?\\#*1234567890\'.:;,", editBugPage.getSummary());
		
		editBugPage.editSummary(currentBugSummary);
		bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals(currentBugSummary, editBugPage.getSummary());
	}
	
	@Test
	public void testEditEmptySummary() throws Exception {
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);
		
		editBugPage.editSummary("");
		SummaryNeededErrorPage summaryNeededErrorPage = editBugPage.commitBugWithEmptySummary();
		assertEquals("You must enter a summary for this bug.", summaryNeededErrorPage.getErrorMsg());
		
		// verify no changes were made
		editBugPage = BugzillaSetup.showBug(currentBugID);
		assertEquals(currentBugSummary, editBugPage.getSummary());
	}
	
	@Test
	public void testEditBugFields() throws Exception {
		// browse to the current bug
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		// edit bug
		editBugPage.editPlatform("Other");
		editBugPage.editOpSys("Linux");
		editBugPage.editPriority("P1");
		editBugPage.editSeverity("critical");

		// commit bug
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);

		// verify changes
		assertEquals("Other", editBugPage.getCurrentPlatform());
		assertEquals("Linux", editBugPage.getCurrentOpSys());
		assertEquals("P1", editBugPage.getCurrentPriority());
		assertEquals("critical", editBugPage.getCurrentSeverity());
	}
	
	@Test
	public void testAddComment() {
		// TODO
		fail("not yet implemented");
	}

	@Test
	public void testEditTimes() throws Exception {
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		// TODO: changes see email 8.7.
		fail("needs to be changed");
		
		editBugPage.editTimeEstimatedTime(20);
		editBugPage.editTimeWorkTime(5);
		editBugPage.editTimeRemainigTime(2);
		editBugPage.editComment("Added time estimation");

		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);

		assertEquals("20.0", editBugPage.getTimeEstimatedTime());
		assertEquals("5.0 +", editBugPage.getTimeWorkTime());
		assertEquals("2.0", editBugPage.getTimeRemainingTime());
	}
	
	@Test
	public void testEditDeadline() throws Exception {
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		editBugPage.editTimeDeadline("1901-12-17");
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("1901-12-17", editBugPage.getTimeDeadline());
		
		editBugPage.editTimeDeadline("2038-01-16");
		bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		assertEquals("2038-01-16", editBugPage.getTimeDeadline());
	}

	@After
	public void tearDownEditedBug() throws Exception {
		// postcondition: leave changes as they are as long as there is no interference
	}
	
	@Test
	public void testEditUrl() {
		// TODO
		fail("not yet implemented");
	}
	
	@Test
	public void testEditDependsOn() {
		// TODO
		fail("not yet implemented");
	}

}
