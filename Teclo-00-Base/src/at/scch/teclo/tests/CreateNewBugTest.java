package at.scch.teclo.tests;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

public class CreateNewBugTest extends BugzillaTest {

	private LoggedInBasePage loggedInBasePage;

	@Before
	public void setUp() throws Exception {
		
		// precondition: logged in
		loggedInBasePage = BugzillaSetup.login();
	}

	@Test
	public void testCreateNewBugDefaultFields() throws Exception {
		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();

		NewBugCreatedPage newBugCreatedPage = createNewBugPage.createNewBug("ExampleBug01",
				"This is an example description for ExampleBug01");
		
		try {
			assertEquals("Bug " + newBugCreatedPage.getBugID() + " has been added to the database", newBugCreatedPage.getBugWasAddedText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
			
		// TODO: Check if creating bug is successful
		
		// TODO: Check created bug including every single field, maybe default values as well
		

	}
	
	// TODO: testCreateNewBugAdvancedFields()

}
