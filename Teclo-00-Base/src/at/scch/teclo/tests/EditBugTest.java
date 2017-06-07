package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.BugCommitedPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;

public class EditBugTest extends BugzillaTest {

	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;
	private String originalBugName;

	@Before
	public void setUp() throws Exception {
		// precondition: logged in
		loggedInBasePage = homeBasePage.loginAdmin();

		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBugID();
		
		// save the original name of the bug temporary
		originalBugName = BugzillaSetup.getExampleBugSummary();
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
		BugCommitedPage bugCommitedPage = editBugPage.commitBug();
		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		
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
		BugCommitedPage bugCommitedPage = editBugPage.commitBug();
		editBugPage = bugCommitedPage.selectCommitedBug(currentBugID);
		
		// verify changes
		assertEquals("20.0", editBugPage.getTimeEstimatedTime());
		assertEquals("5.0 +", editBugPage.getTimeWorkTime());
		assertEquals("2.0", editBugPage.getTimeRemainingTime());
		assertEquals("2017-01-01", editBugPage.getTimeDeadline());
	}

	@After
	public void tearDownEditedBug() throws Exception {
		// postcondition: change bug back to standard
		EditBugPage editBugPage = BugzillaSetup.showBug(currentBugID);
		
		editBugPage.editSummary(originalBugName);
		editBugPage.editPlatform("PC");
		editBugPage.editOpSys("Windows");
		editBugPage.editPriority("P5");
		editBugPage.editSeverity("enhancement");
		
		// commit bug
		editBugPage.commitBug();

	}

}
