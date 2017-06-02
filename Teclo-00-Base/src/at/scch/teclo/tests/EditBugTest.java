package at.scch.teclo.tests;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.ResultsPage;

public class EditBugTest {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;
	private ResultsPage myBugsPage;

	@Before
	public void setUp() throws Exception {
		driver = BugzillaSetup.getWebDriver();

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
		myBugsPage.checkEditedBugChanges();
		// TODO: Commit
		// Check if changes submitted

	}

	@After
	public void tearDown() throws Exception {
		// postcondition: change bug back to standard
		EditBugPage editBugPage = myBugsPage.goToEditBug(currentBugID);

		editBugPage.editBug("ExampleBug01", "PC", "Windows", "P5", "enhancement");

		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
