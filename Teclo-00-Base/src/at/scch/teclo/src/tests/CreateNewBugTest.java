package at.scch.teclo.src.tests;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import at.scch.teclo.src.BugzillaSetup;
import at.scch.teclo.src.pageobjects.CreateNewBugPage;
import at.scch.teclo.src.pageobjects.LogInBasePage;
import at.scch.teclo.src.pageobjects.LoggedInBasePage;
import at.scch.teclo.src.pageobjects.NewBugCreatedPage;

public class CreateNewBugTest {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  private LoggedInBasePage loggedInBasePage;
  
  @Before
  public void setUp() throws Exception {
    driver = BugzillaSetup.getWebDriver();
    
    // precondition: logged in
    LogInBasePage logInBasePage = LogInBasePage.navigateTo(driver);
    loggedInBasePage = logInBasePage.logIn("admin", "admin");
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
