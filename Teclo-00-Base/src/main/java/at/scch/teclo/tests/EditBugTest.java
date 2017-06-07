package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.AbstractBugzillaTestWithLogin;
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
	public void testEditBug() throws Exception {
		// browse to the current bug
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		// edit bug
		editBugPage.editSummary("EditedBug");
		editBugPage.editPlatform("Other");
		editBugPage.editOpSys("Linux");
		editBugPage.editPriority("P1");
		editBugPage.editSeverity("critical");

		// commit bug
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);

		// verify changes
		assertEquals("EditedBug", editBugPage.getSummary());
		assertEquals("Other", editBugPage.getCurrentPlatform());
		assertEquals("Linux", editBugPage.getCurrentOpSys());
		assertEquals("P1", editBugPage.getCurrentPriority());
		assertEquals("critical", editBugPage.getCurrentSeverity());
	}

	@Test
	public void testEditTimes() throws Exception {
		// browse to the current bug
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		// edit bug
		editBugPage.editTimeEstimatedTime(20);
		editBugPage.editTimeWorkTime(5);
		editBugPage.editTimeRemainigTime(2);
		editBugPage.editTimeDeadline("2017-01-01");
		editBugPage.editComment("Added time estimation!");

		// commit bug
		BugCommittedPage bugCommittedPage = editBugPage.commitBug();
		editBugPage = bugCommittedPage.selectCommittedBug(currentBugID);

		// verify changes
		assertEquals("20.0", editBugPage.getTimeEstimatedTime());
		assertEquals("5.0 +", editBugPage.getTimeWorkTime());
		assertEquals("2.0", editBugPage.getTimeRemainingTime());
		assertEquals("2017-01-01", editBugPage.getTimeDeadline());
	}

	@Test
	public void testEditEmptySummary() throws Exception {
		// browse to the current bug
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		// edit bug
		editBugPage.editSummary("");

		// commit bug
		SummaryNeededErrorPage summaryNeededErrorPage = editBugPage.commitBugWithEmptySummary();
		assertEquals("You must enter a summary for this bug.", summaryNeededErrorPage.getErrorMsg());

		editBugPage = BugzillaSetup.showBug(currentBugID);

		// verify no changes were made
		assertEquals(currentBugSummary, editBugPage.getSummary());
	}

	@After
	public void tearDownEditedBug() throws Exception {
		// postcondition: change bug back to standard
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);

		editBugPage.editSummary(currentBugSummary);
		editBugPage.editPlatform("PC");
		editBugPage.editOpSys("Windows");
		editBugPage.editPriority("P5");
		editBugPage.editSeverity("enhancement");

		// commit bug
		editBugPage.commitBug();

	}

}
