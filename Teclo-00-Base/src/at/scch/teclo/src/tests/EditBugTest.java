package at.scch.teclo.src.tests;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import at.scch.teclo.src.BugzillaSetup;
import at.scch.teclo.src.pageobjects.CreateNewBugPage;
import at.scch.teclo.src.pageobjects.EditBugPage;
import at.scch.teclo.src.pageobjects.LogInBasePage;
import at.scch.teclo.src.pageobjects.LoggedInBasePage;
import at.scch.teclo.src.pageobjects.MyBugsPage;
import at.scch.teclo.src.pageobjects.NewBugCreatedPage;

public class EditBugTest {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  private NewBugCreatedPage newBugCreatedPage;
  
  @Before
  public void setUp() throws Exception {
	  driver = BugzillaSetup.getWebDriver();
	  
	  // precondition: logged in && bug inserted
	  LogInBasePage logInBasePage = LogInBasePage.navigateTo(driver);
	  
	  LoggedInBasePage loggedInBasePage = logInBasePage.logIn("admin", "admin");
	  
	  CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
	    
	  newBugCreatedPage = createNewBugPage.createNewBug("ExampleBug01", "This is an example description for ExampleBug01");
	  
	  
	  
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
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  
}
