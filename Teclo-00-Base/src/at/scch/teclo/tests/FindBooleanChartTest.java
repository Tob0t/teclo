package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.AdvancedSearchPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.MyBugsPage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;
import at.scch.teclo.pageobjects.SearchBasePage;

public class FindBooleanChartTest {
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
		newBugCreatedPage = BugzillaSetup.CreateExampleBug(loggedInBasePage);
		
		// precondition: bug changed to Priority P3
		newBugCreatedPage.changePriority("P3");
		newBugCreatedPage = newBugCreatedPage.commitBug();
	}

	@Test
	public void testChangeBugState() throws Exception {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		AdvancedSearchPage advancedSearchPage = searchPage.navigateToAdvancedSearchPage();
		
		advancedSearchPage.fillBooleanChart("Priority", "is equal to", "P3");
		MyBugsPage myBugsPage = advancedSearchPage.search();
		
	    assertTrue("Bug not found!",0 < myBugsPage.getAmountOfBugs());	    
	    
	    try {
	    	assertEquals("P3", myBugsPage.getPriorityOfFirstBug());
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
		
		// TODO:
		// postcondition: change bug back to Priority P5
		
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
