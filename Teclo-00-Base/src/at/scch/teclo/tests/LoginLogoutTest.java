package at.scch.teclo.tests;

import org.junit.Test;

import static org.junit.Assert.*;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.LogInBasePage;
import at.scch.teclo.pageobjects.LogInErrorPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.LoggedOutBasePage;

public class LoginLogoutTest extends BugzillaTest {

	@Test
	public void testLoginLogout() throws Exception {

		LogInBasePage logInBasePage = LogInBasePage.navigateTo(driver);
		LoggedInBasePage loggedInBasePage = logInBasePage.logIn("admin", "admin");
		
		try {
			// check if user is logged in
			assertTrue(loggedInBasePage.isLogoutLinkPresent());
			
			// check if correct user is logged in
			assertEquals("| Log out admin", loggedInBasePage.getLogoutTextPlusUserName());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		LoggedOutBasePage loggedOutBasePage = loggedInBasePage.logOut();
		
		try {
			// check if login link is being displayed again
			assertTrue(loggedOutBasePage.isLoginLinkTopPresent());
			
			assertEquals("Logged Out", loggedOutBasePage.getDriverTitle());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		

		LogInErrorPage logInErrorPage = loggedOutBasePage
				.logInWithWrongUsernameAndWrongPassword("wrongUsername", "wrongPassword");
		
		assertEquals("Invalid Username Or Password", logInErrorPage.getDriverTitle());

	}
	

}
