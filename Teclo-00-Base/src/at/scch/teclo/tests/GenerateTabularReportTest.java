package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.ReportsBasePage;
import at.scch.teclo.pageobjects.TabularReportsResultsPage;
import at.scch.teclo.pageobjects.TabularReportsSearchPage;

public class GenerateTabularReportTest extends BugzillaTest {
	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		BugzillaSetup.getExampleBugID();
	}

	@Test
	public void testGenerateTabularReport() throws Exception {
		ReportsBasePage reportsBasePage = loggedInBasePage.navigateToReportsBasePage();
		TabularReportsSearchPage tabularReportsPage = reportsBasePage.navigateToTabularReportsPage();
		tabularReportsPage.selectHorizontalAxes("Status");
		tabularReportsPage.selectVeritcalAxes("Assignee");

		TabularReportsResultsPage tabularReportsResultsPage = tabularReportsPage.generateReport();

		assertEquals("Status", tabularReportsResultsPage.getXAxesDescription());
		assertEquals("Assignee", tabularReportsResultsPage.getYAxesDescription());
	}
}
