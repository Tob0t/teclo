package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTest;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.StartPage;

public class LoginLogoutTest extends AbstractBugzillaTest {

	@Test
	public void testLoginLogoutSuccessful() {
		StartPage startPage = BugzillaSetup.gotoStartPage();
		assertFalse(startPage.isLoggedin());
		
		assertTrue(startPage.login("admin", "admin"));
		
		assertTrue(startPage.isLoggedin());
		assertEquals("admin", startPage.getLoggedinUser());
		
		startPage = startPage.logout();
		
		assertFalse(startPage.isLoggedin());
	}
	
	@Test
	public void testLoginWrongUser() {
		StartPage startPage = BugzillaSetup.gotoStartPage();
		assertFalse(startPage.isLoggedin());
		
		assertFalse(startPage.login("wrongUsername", "wrongPassword"));
		
		assertFalse(startPage.isLoggedin());
		
//		LoginErrorPage loginErrorPage = new LoginErrorPage();
//		assertEquals("Invalid Username Or Password", loginErrorPage.getTitle());
	}
	
}
