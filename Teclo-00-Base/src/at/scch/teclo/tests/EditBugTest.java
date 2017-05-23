package at.scch.teclo.tests;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.LogInBasePage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.LoggedOutBasePage;
import at.scch.teclo.pageobjects.MyBugsPage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

public class EditBugTest {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  private NewBugCreatedPage newBugCreatedPage;
  private LoggedInBasePage loggedInBasePage;
  
  @Before
  public void setUp() throws Exception {
	  driver = BugzillaSetup.getWebDriver();
	  
	  // precondition: logged in
	  loggedInBasePage = BugzillaSetup.LogIn();
	  
	  // precondition: bug inserted
	  newBugCreatedPage = BugzillaSetup.CreateExampleBug(loggedInBasePage);
	  
  }

  @Test
  public void testEditBug() throws Exception {
	  MyBugsPage myBugsPage = newBugCreatedPage.goToMyBugsPage();
	  
	  EditBugPage editBugPage = myBugsPage.goToEditBug();
	  
	  myBugsPage = editBugPage.editBug();
	  myBugsPage.checkEditedBugChanges();
	  
  }

  @After
  public void tearDown() throws Exception {
	// postcondition: logout
	BugzillaSetup.LogOut(loggedInBasePage);
		
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  
}
