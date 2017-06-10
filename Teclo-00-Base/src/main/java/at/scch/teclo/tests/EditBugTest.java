package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.BugCommittedPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.SummaryNeededErrorPage;

public class EditBugTest extends AbstractBugzillaTestWithLogin {
	private int currentBugId;
	private String currentBugSummary;
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugId = BugzillaSetup.getExampleBugID();
		currentBugSummary = BugzillaSetup.getExampleBugSummary();
	}

	@Test
	public void testEditSummary() throws Exception {
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);

		editBugPage.setSummary("Test Summary !\"�$%&/(=?\\#*1234567890\'.:;,");
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugId);
		assertEquals("Test Summary !\"�$%&/(=?\\#*1234567890\'.:;,", editBugPage.getSummary());

		editBugPage.setSummary(currentBugSummary);
		bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugId);
		assertEquals(currentBugSummary, editBugPage.getSummary());
	}

	@Test
	public void testEditEmptySummary() throws Exception {
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);

		editBugPage.setSummary("");
		SummaryNeededErrorPage summaryNeededErrorPage = editBugPage.commitBugWithEmptySummary();
		assertEquals("You must enter a summary for this bug.", summaryNeededErrorPage.getErrorMsg());

		// verify no changes were made
		editBugPage = BugzillaSetup.gotoBugPage(currentBugId);
		assertEquals(currentBugSummary, editBugPage.getSummary());
	}

	@Test
	public void testEditBugFields() throws Exception {
		// browse to the current bug
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);

		// edit bug
		editBugPage.setPlatform("Other");
		editBugPage.setOpSys("Linux");
		editBugPage.setPriority("P1");
		editBugPage.setSeverity("critical");

		// commit bug
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugId);

		// verify changes
		assertEquals("Other", editBugPage.getPlatform());
		assertEquals("Linux", editBugPage.getOpSys());
		assertEquals("P1", editBugPage.getPriority());
		assertEquals("critical", editBugPage.getSeverity());
	}

	@Test
	public void testAddComment() {
		String nowText = LocalDateTime.now().format(formatter);
		String comment = "This is an example comment for testAddComment created at " + nowText;
				
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);
		int currentAmountOfComments = editBugPage.getNumberOfComments();
		
		editBugPage.setComment(comment);
		editBugPage = editBugPage.commitBug().selectCommittedBug(currentBugId);
		
		assertEquals(currentAmountOfComments+1, editBugPage.getNumberOfComments());
		assertEquals(comment, editBugPage.getLastComment());
	}

	@Test
	public void testEditTimes() throws Exception {
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);

		// set time
		editBugPage.setTimeEstimated(100);
		editBugPage.setTimeHoursLeft(100);
		editBugPage = editBugPage.commitBug().selectCommittedBug(currentBugId);
		
		// verify
		assertEquals("100.0", editBugPage.getTimeEstimated());
		assertEquals("100.0", editBugPage.getTimeCurrentEstimation());
		assertEquals("100.0", editBugPage.getTimeHoursLeft());
		
		// change time
		editBugPage.setTimeWorked(50.0);
		editBugPage.setComment("Edited hours left to 50.0!");
		editBugPage = editBugPage.commitBug().selectCommittedBug(currentBugId);
		
		// verify
		assertEquals("50.0 +", editBugPage.getTimeWorkCompleted());
		assertEquals("50.0", editBugPage.getTimeHoursLeft());
		assertEquals("50", editBugPage.getTimeCompletedInPercent());
		
		// change time
		editBugPage.setTimeHoursLeft(40.0);
		editBugPage = editBugPage.commitBug().selectCommittedBug(currentBugId);
		
		// verify
		assertEquals("90.0", editBugPage.getTimeCurrentEstimation());
		assertEquals("40.0", editBugPage.getTimeHoursLeft());
		assertEquals("55", editBugPage.getTimeCompletedInPercent());
		assertEquals("10.0", editBugPage.getTimeGain());
	}

	@Test
	public void testEditDeadline() throws Exception {
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);

		editBugPage.setTimeDeadline("1901-12-17");
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugId);
		assertEquals("1901-12-17", editBugPage.getTimeDeadline());

		editBugPage.setTimeDeadline("2038-01-16");
		bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugId);
		assertEquals("2038-01-16", editBugPage.getTimeDeadline());
	}

	@Test
	public void testEditUrl() {
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);
		
		editBugPage.setUrl("http://www.test-bugzilla.org");
		editBugPage = editBugPage.commitBug().selectCommittedBug(currentBugId);
		assertEquals("http://www.test-bugzilla.org", editBugPage.getUrl());
		
		editBugPage.setUrl("");
		editBugPage = editBugPage.commitBug().selectCommittedBug(currentBugId);
		assertEquals("", editBugPage.getUrl());
	}

	@Test
	public void testEditDependsOn() {
		int dependingOnBugId = BugzillaSetup.createExampleBug();
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(currentBugId);
		
		editBugPage.setDependsOn(dependingOnBugId);
		editBugPage = editBugPage.commitBug().selectCommittedBug(currentBugId);
		assertEquals("edited page (currentBugId) should show dependsOnId as depending on", 
				dependingOnBugId, Integer.parseInt(editBugPage.getDependsOn()));

		editBugPage = BugzillaSetup.gotoBugPage(dependingOnBugId);
		assertEquals("dependingOn page should show currentBugId (edited page) as blocks", 
				currentBugId, Integer.parseInt(editBugPage.getBlocks()));
		
		editBugPage.setBlocks("");
		editBugPage = editBugPage.commitBug().selectCommittedBug(dependingOnBugId);
		assertEquals("dependingOn page should have cleared blocks", 
				"", editBugPage.getBlocks());
		
		editBugPage = BugzillaSetup.gotoBugPage(currentBugId);
		assertEquals("edited page (currentBugId) should have cleared depending on", 
				"", editBugPage.getDependsOn());
	}
	
	@After
	public void tearDownEditedBug() throws Exception {
		// postcondition: leave changes as they are as long as there is no interference
	}
}
