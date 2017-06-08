package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTest;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.HomeBasePage;
import at.scch.teclo.pageobjects.LogInBasePage;
import at.scch.teclo.pageobjects.LogInErrorPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.LoggedOutBasePage;

public class LoginLogoutTest extends AbstractBugzillaTest {

	private HomeBasePage homeBasePage;

	@Before
	public void setUp() throws Exception {
		homeBasePage = BugzillaSetup.gotoHomeBasePage();
	}

	@Test
	public void testLoginLogout() {

		LogInBasePage logInBasePage = homeBasePage.navigateToLoginBasePage();
		LoggedInBasePage loggedInBasePage = logInBasePage.login("admin", "admin");

		// check if user is logged in
		assertTrue(loggedInBasePage.isLogoutLinkPresent());

		// check if correct user is logged in
		assertEquals("| Log out admin", loggedInBasePage.getLogoutTextPlusUserName());

		LoggedOutBasePage loggedOutBasePage = loggedInBasePage.logout();

		// check if login link is being displayed again
		assertTrue(loggedOutBasePage.isLoginLinkTopPresent());

		assertEquals("Logged Out", loggedOutBasePage.getDriverTitle());

		LogInErrorPage logInErrorPage = loggedOutBasePage.logInWithWrongUsernameAndWrongPassword("wrongUsername",
				"wrongPassword");

		assertEquals("Invalid Username Or Password", logInErrorPage.getDriverTitle());
	}
}
