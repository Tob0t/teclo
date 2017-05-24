package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.MyBugsPage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;
import at.scch.teclo.pageobjects.SearchPage;

public class FindSpecificSearchTest {

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	private NewBugCreatedPage newBugCreatedPage;
	private LoggedInBasePage loggedInBasePage;

	@Before
	public void setUp() throws Exception {
		driver = BugzillaSetup.getWebDriver();

		// precondition: logged in
		loggedInBasePage = BugzillaSetup.LogIn();

		// precondition: bug inserted
		//newBugCreatedPage = BugzillaSetup.CreateExampleBug(loggedInBasePage);
	}

	@Test
	public void testChangeBugState() throws Exception {
		SearchPage searchPage = loggedInBasePage.navigateToSearchPage();
		searchPage = searchPage.navigateToSpecificSearchPage();
		
		searchPage.selectBugState("Open");
		MyBugsPage myBugsPage = searchPage.searchFor("ExampleBug*");
		
	    try {
	      assertTrue("Less bug founds, than the minimum required amount",0 < myBugsPage.getAmountOfBugs());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    
	    try {
	      assertEquals("ExampleBug01", myBugsPage.getSummaryOfFirstBug());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
		
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
