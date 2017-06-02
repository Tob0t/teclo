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
import at.scch.teclo.pageobjects.SearchBasePage;
import at.scch.teclo.pageobjects.SpecificSearchPage;

public class FindSpecificSearchTest {

	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	private int currentBugID;
	private LoggedInBasePage loggedInBasePage;

	@Before
	public void setUp() throws Exception {
		driver = BugzillaSetup.getWebDriver();

		// precondition: logged in
		loggedInBasePage = BugzillaSetup.login();

		// precondition: bug inserted
		currentBugID = BugzillaSetup.getExampleBug(loggedInBasePage);
	}

	@Test
	public void testSpecificSearch() throws Exception {
		SearchBasePage searchPage = loggedInBasePage.navigateToSearchBasePage();
		SpecificSearchPage specificSearchPage = searchPage.navigateToSpecificSearchPage();

		specificSearchPage.selectBugState("Open");
		MyBugsPage myBugsPage = specificSearchPage.searchFor("ExampleBug*");

		assertTrue("Bug not found!", 0 < myBugsPage.getAmountOfBugs());

		try {
			assertEquals("ExampleBug01", myBugsPage.getSummaryOfFirstBug());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

	}

	@After
	public void tearDown() throws Exception {
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
