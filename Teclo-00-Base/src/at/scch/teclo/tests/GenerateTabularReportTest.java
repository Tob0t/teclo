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
import at.scch.teclo.pageobjects.ReportsBasePage;
import at.scch.teclo.pageobjects.SearchBasePage;
import at.scch.teclo.pageobjects.TabularReportsResultsPage;
import at.scch.teclo.pageobjects.TabularReportsSearchPage;

public class GenerateTabularReportTest {
	
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
	public void testGenerateTabularReport() throws Exception {
		ReportsBasePage reportsBasePage = loggedInBasePage.navigateToReportsBasePage();
		TabularReportsSearchPage tabularReportsPage = reportsBasePage.navigateToTabularReportsPage();
		tabularReportsPage.selectHorizontalAxes("Status");
		tabularReportsPage.selectVeritcalAxes("Assignee");
		
		TabularReportsResultsPage tabularReportsResultsPage = tabularReportsPage.generateReport();
		
		try {
		  assertEquals("Status", tabularReportsResultsPage.getXAxesDescription());
		} catch (Error e) {
		  verificationErrors.append(e.toString());
		}
		
		try {
		  assertEquals("Assignee", tabularReportsResultsPage.getYAxesDescription());
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
