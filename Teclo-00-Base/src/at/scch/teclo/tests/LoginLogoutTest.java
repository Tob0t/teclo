package at.scch.teclo.tests;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.LogInBasePage;
import at.scch.teclo.pageobjects.LogInErrorPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.LoggedOutBasePage;

public class LoginLogoutTest extends BugzillaTest {

	@Before
	public void setUp() throws Exception {
		
		// precondition: logged out
		homeBasePage.logoutAdmin();
	}
	
	@Test
	public void testLoginLogout() throws Exception {

		LogInBasePage logInBasePage = homeBasePage.navigateToLoginBasePage();
		LoggedInBasePage loggedInBasePage = logInBasePage.logIn("admin", "admin");
		
		// check if user is logged in
		assertTrue(loggedInBasePage.isLogoutLinkPresent());
		
		// check if correct user is logged in
		assertEquals("| Log out admin", loggedInBasePage.getLogoutTextPlusUserName());

		LoggedOutBasePage loggedOutBasePage = loggedInBasePage.logOut();
		
		// check if login link is being displayed again
		assertTrue(loggedOutBasePage.isLoginLinkTopPresent());
		
		assertEquals("Logged Out", loggedOutBasePage.getDriverTitle());

		LogInErrorPage logInErrorPage = loggedOutBasePage
				.logInWithWrongUsernameAndWrongPassword("wrongUsername", "wrongPassword");
	
		assertEquals("Invalid Username Or Password", logInErrorPage.getDriverTitle());

	}
	

}
