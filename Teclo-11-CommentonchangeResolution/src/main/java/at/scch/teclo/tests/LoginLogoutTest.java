package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTest;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.CreateBugPage;
import at.scch.teclo.pageobjects.ErrorLoginPage;
import at.scch.teclo.pageobjects.LoginPage;
import at.scch.teclo.pageobjects.StartPage;

public class LoginLogoutTest extends AbstractBugzillaTest {

	@Test
	public void testLoginLogoutTopOfPage() {
		StartPage startPage = BugzillaSetup.gotoStartPage();
		assertFalse(startPage.isLoggedin());
		
		assertTrue(startPage.login("admin", "admin"));
		
		assertTrue(startPage.isLoggedin());
		assertEquals("admin", startPage.getLoggedinUser());
		
		startPage = startPage.logout();
		
		assertFalse(startPage.isLoggedin());
	}
	
	@Test
	public void testLoginSuccessful() {
		StartPage startPage = BugzillaSetup.gotoStartPage();
		assertFalse(startPage.isLoggedin());
		
		LoginPage loginPage = startPage.gotoLoginPage();
		CreateBugPage createBugPage = loginPage.loginSuccessful("admin", "admin");
		
		assertTrue(createBugPage.isLoggedin());
		assertEquals("admin", createBugPage.getLoggedinUser());
		
		startPage = createBugPage.logout();
		
		assertFalse(startPage.isLoggedin());
	}
	
	@Test
	public void testLoginFailing() {
		StartPage startPage = BugzillaSetup.gotoStartPage();
		assertFalse(startPage.isLoggedin());
		
		LoginPage loginPage = startPage.gotoLoginPage();
		ErrorLoginPage errorLoginPage = loginPage.loginFailing("wrongUsername", "wrongPassword");
		
		assertEquals("The username or password you entered is not valid.", errorLoginPage.getErrorMsg());
		assertFalse(errorLoginPage.isLoggedin());
	}
	
}
