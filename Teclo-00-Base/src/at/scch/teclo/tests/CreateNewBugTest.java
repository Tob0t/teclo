package at.scch.teclo.tests;

import org.junit.*;
import static org.junit.Assert.*;

import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

public class CreateNewBugTest extends BugzillaTest {

	private LoggedInBasePage loggedInBasePage;

	@Before
	public void setUp() throws Exception {
		
		// precondition: logged in
		loggedInBasePage = homeBasePage.loginAdmin();
	}

	@Test
	public void testCreateNewBugDefaultFields() throws Exception {
		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		createNewBugPage.fillSummary("ExampleBugDefault");
		createNewBugPage.fillComment("This is an example description for ExampleBugDefault");
		NewBugCreatedPage newBugCreatedPage = createNewBugPage.commitBug();

		// Check if creating bug was successful
		assertEquals("Bug " + newBugCreatedPage.getBugID() + " has been added to the database", newBugCreatedPage.getBugWasAddedText());
		
		// verify changes including default values
		// TODO: verify default values according to operating system and platform
		//assertEquals("PC", newBugCreatedPage.getCurrentPlatform());
		//assertEquals("Windows", newBugCreatedPage.getCurrentOpSys());
		
		assertEquals("ExampleBugDefault", newBugCreatedPage.getSummary());
		assertEquals("P5", newBugCreatedPage.getCurrentPriority());
		assertEquals("enhancement", newBugCreatedPage.getCurrentSeverity());
		
		assertEquals("0.0", newBugCreatedPage.getTimeEstimatedTime());
		assertEquals("0.0 +", newBugCreatedPage.getTimeWorkTime());
		assertEquals("0.0", newBugCreatedPage.getTimeRemainingTime());
		assertEquals("", newBugCreatedPage.getTimeDeadline());
	}
	
	@Test
	public void testCreateNewBugAdvancedFields() throws Exception {
		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		createNewBugPage.fillSummary("ExampleBugAdvanced");
		createNewBugPage.fillPlatform("Other");
		createNewBugPage.fillOpSys("Linux");
		createNewBugPage.fillSeverity("major");
		
		createNewBugPage.fillComment("This is an example description for ExampleBugAdvanced");
		
		NewBugCreatedPage newBugCreatedPage = createNewBugPage.commitBug();

		// Check if creating bug was successful
		assertEquals("Bug " + newBugCreatedPage.getBugID() + " has been added to the database", newBugCreatedPage.getBugWasAddedText());
		
		// verify changes
		assertEquals("ExampleBugAdvanced", newBugCreatedPage.getSummary());
		assertEquals("Other", newBugCreatedPage.getCurrentPlatform());
		assertEquals("Linux", newBugCreatedPage.getCurrentOpSys());
		assertEquals("major", newBugCreatedPage.getCurrentSeverity());
	}
}
