package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.*;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.ResultsPage;

public class EditBugTest extends BugzillaTest {

	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;
	private ResultsPage myBugsPage;

	@Before
	public void setUp() throws Exception {
		// precondition: logged in
		loggedInBasePage = BugzillaSetup.login();

		// precondition: bug inserted
		// TODO: get new bug for every single test case
		currentBugID = BugzillaSetup.getExampleBug(loggedInBasePage);
		
	}

	// TODO: testEditBugTimes()
	@Test
	public void testEditBug() throws Exception {
		// TODO: get direct link to Bug ID
		myBugsPage = loggedInBasePage.navigateToMyBugsPage();

		EditBugPage editBugPage = myBugsPage.goToEditBug(currentBugID);

		// myBugsPage = editBugPage.editBug();
		editBugPage.editBug("EditedBug", "Other", "Linux", "P1", "critical");
		
		try {
			assertEquals("cri", myBugsPage.getCriticalLevel());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("P1", myBugsPage.getPriorityLevel());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("Linu", myBugsPage.getOperatingSystem());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals("EditedBug", myBugsPage.getBugTitle());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		// TODO: Commit
		// Check if changes submitted

	}

	@After
	public void tearDownEditedBug() throws Exception {
		// postcondition: change bug back to standard
		EditBugPage editBugPage = myBugsPage.goToEditBug(currentBugID);

		editBugPage.editBug("ExampleBug01", "PC", "Windows", "P5", "enhancement");

	}

}
