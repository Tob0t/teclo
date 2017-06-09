package at.scch.teclo.tests;

import static org.junit.Assert.*;

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
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);

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
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);
		
		editBugPage.editSummary("");
		SummaryNeededErrorPage summaryNeededErrorPage = editBugPage.commitBugWithEmptySummary();
		assertEquals("You must enter a summary for this bug.", summaryNeededErrorPage.getErrorMsg());
		
		// verify no changes were made
		editBugPage = BugzillaSetup.gotoBugPage(currentBugID);
		assertEquals(currentBugSummary, editBugPage.getSummary());
	}
	
	@Test
	public void testEditBugFields() throws Exception {
		// browse to the current bug
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);

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
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);

		// set time
		editBugPage.editTimeEstimatedTime(100);
		editBugPage.editTimeRemainigTime(100);
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		
		// verify
		assertEquals("100.0", editBugPage.getTimeEstimatedTime());
		assertEquals("100.0", editBugPage.getTimeCurrentEstimation());
		assertEquals("100.0", editBugPage.getTimeRemainingTime());
		
		// change time
		editBugPage.editTimeWorkTime(50.0);
		editBugPage.editComment("Edited hours left to 50.0!");
		bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		
		// verify
		assertEquals("50.0 +", editBugPage.getTimeWorkTime());
		assertEquals("50.0", editBugPage.getTimeRemainingTime());
		assertEquals("50", editBugPage.getTimeCompletedInPercent());
		
		// change time
		editBugPage.editTimeRemainigTime(40.0);
		bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);
		
		// verify
		assertEquals("90.0", editBugPage.getTimeCurrentEstimation());
		assertEquals("40.0", editBugPage.getTimeRemainingTime());
		assertEquals("55", editBugPage.getTimeCompletedInPercent());
		assertEquals("10.0", editBugPage.getTimeGain());
	}
	
	@Test
	public void testEditDeadline() throws Exception {
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);

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
		BugzillaSetup.createExampleBug();
		int dependingOnBugID = BugzillaSetup.getExampleBugID();
		
		// set dependency
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugID);
		editBugPage.editDependsOn(dependingOnBugID);
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		
		// select committed bug
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);

		// check if existing
		assertNotNull(editBugPage.verifyDependsOn(dependingOnBugID));
		
		// select depending bug
		editBugPage = editBugPage.clickDependsOn(dependingOnBugID);
		
		// check if existing
		assertNotNull(editBugPage.verifyBlocksOn(currentBugID));
		
		// remove blocks & commit
		editBugPage.clearBlocksOn();
		editBugPage.commitBug();
		
		// verify removed dependency
		editBugPage = BugzillaSetup.gotoBugPage(currentBugID);
		assertEquals("", editBugPage.getCurrentDependsOn());
		assertEquals("", editBugPage.getCurrentBlocks());
		
		editBugPage = BugzillaSetup.gotoBugPage(dependingOnBugID);
		assertEquals("", editBugPage.getCurrentDependsOn());
		assertEquals("", editBugPage.getCurrentBlocks());
		
	}

}
