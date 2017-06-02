package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.ResultsPage;

public class FindQuickSearchTest extends BugzillaTest {
	//private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;

	@Before
	public void setUp() throws Exception {

		// precondition: logged in
		loggedInBasePage = BugzillaSetup.login();

		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBug(loggedInBasePage);
		
		// TODO: Create Bug with unique bug summary
	}

	// TODO: Create three test cases
	// testFindBugZarro()
	// testFindBugSingle()
	// testFindBugMultiple()
	@Test
	public void testFindBugSingle() throws Exception {
		ResultsPage resultsPage = loggedInBasePage.searchFor("ExampleBug01");

		assertEquals("No bug found", 0, resultsPage.getAmountOfBugs());

		try {
			assertEquals("ExampleBug01", resultsPage.getSummaryOfFirstBug());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

}
