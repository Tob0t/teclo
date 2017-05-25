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

public class SaveSearchTest {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	private NewBugCreatedPage newBugCreatedPage;
	private LoggedInBasePage loggedInBasePage;
	private MyBugsPage myBugsPage;

	@Before
	public void setUp() throws Exception {
		driver = BugzillaSetup.getWebDriver();

		// precondition: logged in
		loggedInBasePage = BugzillaSetup.LogIn();

		// precondition: bug inserted
		newBugCreatedPage = BugzillaSetup.CreateExampleBug(loggedInBasePage);
	}

	@Test
	public void testChangeBugState() throws Exception {
		myBugsPage = loggedInBasePage.searchFor("ExampleBug01");
		
	    assertTrue("Less bug founds, than the minimum required amount",0 < myBugsPage.getAmountOfBugs());
	    
	    try {
	      assertEquals("ExampleBug01", myBugsPage.getSummaryOfFirstBug());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    
	    myBugsPage = myBugsPage.saveSearch("ExampleSearch01");
	    loggedInBasePage = myBugsPage.navigateToHome();
	    myBugsPage = loggedInBasePage.getSavedSearch("ExampleSearch01");
	    
	    assertTrue("Less bug founds, than the minimum required amount",0 < myBugsPage.getAmountOfBugs());
	    
	    try {
	      assertEquals("ExampleBug01", myBugsPage.getSummaryOfFirstBug());
	    } catch (Error e) {
	      verificationErrors.append(e.toString());
	    }
	    
		
	}

	@After
	public void tearDown() throws Exception {
		
		// post condition: forget saved search
		myBugsPage.forgetSavedSearch("ExampleSearch01");
		
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
