package at.scch.teclo.src.tests;

import org.junit.Test;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import at.scch.teclo.src.BugzillaSetup;
import at.scch.teclo.src.pageobjects.LogInErrorPage;
import at.scch.teclo.src.pageobjects.LoggedInBasePage;
import at.scch.teclo.src.pageobjects.LoggedOutBasePage;
import at.scch.teclo.src.pageobjects.LogInBasePage;

public class LoginLogoutTest {
  private WebDriver driver;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = BugzillaSetup.getWebDriver();
    
  }
  
  @Test
  public void testLoginLogout() throws Exception {
	  
	  LogInBasePage logInBasePage = LogInBasePage.navigateTo(driver);
	  LoggedInBasePage loggedInBasePage = logInBasePage.logIn("admin", "admin");
	  loggedInBasePage.checkLogInStatus();
	  
	  LoggedOutBasePage loggedOutBasePage = loggedInBasePage.logOut();
	  loggedOutBasePage.checkLogOutStatus();
	  
	  LogInErrorPage logInErrorPage = loggedOutBasePage.logInWithWrongUsernameAndWrongPassword("wrong", "wrong");
	  logInErrorPage.checkLogInErrorStatus();
  
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

}
