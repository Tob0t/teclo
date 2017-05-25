package at.scch.teclo.tests;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.LogInBasePage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.LoggedOutBasePage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

public class CreateNewBugTest {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  private LoggedInBasePage loggedInBasePage;
  
  @Before
  public void setUp() throws Exception {
    driver = BugzillaSetup.getWebDriver();
    
    // precondition: logged in
    loggedInBasePage = BugzillaSetup.login();
  }

  @Test
  public void testCreateNewBug() throws Exception {
    CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
    
    NewBugCreatedPage newBugCreatedPage = createNewBugPage.createNewBug("ExampleBug01", "This is an example description for ExampleBug01");
    newBugCreatedPage.checkCreatedBug();
    
  }

  @After
  public void tearDown() throws Exception {
	String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }
}
