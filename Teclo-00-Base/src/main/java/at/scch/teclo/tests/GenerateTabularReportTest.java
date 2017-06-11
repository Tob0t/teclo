package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.ReportsBasePage;
import at.scch.teclo.pageobjects.TabularReportResultsPage;
import at.scch.teclo.pageobjects.TabularReportGeneratePage;

public class GenerateTabularReportTest extends AbstractBugzillaTestWithLogin {
	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		BugzillaSetup.getExampleBugId();
	}

	@Test
	public void testGenerateTabularReport() throws Exception {
		ReportsBasePage reportsBasePage = startPage.gotoReportsBasePage();
		TabularReportGeneratePage tabularReportGeneratePage = reportsBasePage.gotoGenerateTabularReportPage();
		tabularReportGeneratePage.setHorizontalAxis("Status");
		tabularReportGeneratePage.setVeritcalAxis("Assignee");

		TabularReportResultsPage tabularReportResultsPage = tabularReportGeneratePage.generateReport();

		assertEquals("Status", tabularReportResultsPage.getHorizontalAxisLabel());
		assertEquals("Assignee", tabularReportResultsPage.getVerticalAxisLabel());
	}
}
