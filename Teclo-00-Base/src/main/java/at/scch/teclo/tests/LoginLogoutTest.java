package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.HomeBasePage;
import at.scch.teclo.pageobjects.LogInBasePage;
import at.scch.teclo.pageobjects.LogInErrorPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.LoggedOutBasePage;

public class LoginLogoutTest extends BugzillaTest {

	private HomeBasePage homeBasePage;

	@Before
	public void setUp() throws Exception {
		homeBasePage = BugzillaSetup.navigateToHomeBasePage();
		// precondition: logged out
		homeBasePage.logoutAdmin();
	}

	@Test
	public void testLoginLogout() {

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

		LogInErrorPage logInErrorPage = loggedOutBasePage.logInWithWrongUsernameAndWrongPassword("wrongUsername",
				"wrongPassword");

		assertEquals("Invalid Username Or Password", logInErrorPage.getDriverTitle());
	}

	@After
	public void tearDown() {
		BugzillaSetup.navigateToHomeBasePage();
		homeBasePage.loginAdmin();
	}

}
