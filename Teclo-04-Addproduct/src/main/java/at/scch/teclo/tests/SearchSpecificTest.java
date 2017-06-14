package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.SearchResultsPage;
import at.scch.teclo.pageobjects.BugCreatedPage;
import at.scch.teclo.pageobjects.CreateBugPage;
import at.scch.teclo.pageobjects.ErrorParametersRequiredPage;
import at.scch.teclo.pageobjects.SearchBasePage;
import at.scch.teclo.pageobjects.SearchSpecificPage;

public class SearchSpecificTest extends AbstractBugzillaTestWithLogin {
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

	private String currentBugSummary;

	@Before
	public void setUp() throws Exception {
		// precondition: bug inserted
		currentBugSummary = BugzillaSetup.getExampleBugSummary();
	}

	@Test
	public void testFindBugZarro() throws Exception {
		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();

		searchSpecificPage.selectBugStatus("Closed");
		SearchResultsPage searchResultsPage = searchSpecificPage.searchFor(currentBugSummary.replace("_", "-"));

		assertEquals("More than 0 bugs found!", 0, searchResultsPage.getAmountOfBugs());
	}

	@Test
	public void testFindBugSingle() throws Exception {
		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();

		searchSpecificPage.selectBugStatus("All");
		SearchResultsPage searchResultsPage = searchSpecificPage.searchFor(currentBugSummary);

		assertEquals("Not exactly one bug found!", 1, searchResultsPage.getAmountOfBugs());
		assertEquals(BugzillaSetup.getExampleBugSummary(), searchResultsPage.getSummaryOfFirstBug());
	}

	@Test
	public void testFindBugMultiple() throws Exception {
		// add one more bug to make sure that there are at least 2 or more bugs
		// in the database
		BugzillaSetup.createExampleBug();

		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();

		searchSpecificPage.selectBugStatus("All");
		searchSpecificPage.selectProduct("All");
		SearchResultsPage searchResultsPage = searchSpecificPage.searchFor("Bug*");

		assertTrue("No multiple bugs found", 1 < searchResultsPage.getAmountOfBugs());
	}
	
	@Test
	public void findBugInOneProduct() throws Exception {
		//to identify single bug among products
		String bugIdentifier = LocalDateTime.now().format(formatter);
		
		String summary = "ExampleBugDefault_" + bugIdentifier;

		CreateBugPage createBugPage = startPage.gotoCreateBugPage();
		
		createBugPage = createBugPage.selectProductForBug("Foo");
		
		createBugPage.setSummary(summary);
		
		BugCreatedPage bugCreatedPage = createBugPage.commitBug();
		
		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();

		searchSpecificPage.selectBugStatus("All");
		searchSpecificPage.selectProduct("Foo");
		
		SearchResultsPage searchResultsPage = searchSpecificPage.searchFor("ExampleBugDefault_" + bugIdentifier);
		
		//at least 1 bug found
		assertTrue("At least 1 bug found", searchResultsPage.getAmountOfBugs() > 0);
		
		searchBasePage = startPage.gotoSearchBasePage();
		searchSpecificPage = searchBasePage.gotoSpecificSearchPage();
		
		searchSpecificPage.selectBugStatus("All");
		searchSpecificPage.selectProduct("TestProduct");
		
		searchResultsPage = searchSpecificPage.searchFor("ExampleBugDefault_" + bugIdentifier);
		
		//0 bugs found
		assertEquals(0, searchResultsPage.getAmountOfBugs());
	}

	@Test
	public void testSearchBlanksResultsInError() {
		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();

		ErrorParametersRequiredPage errorParametersRequiredPage = searchSpecificPage.searchForBlanks();

		assertEquals("You may not search, or create saved searches, without any search terms.",
				errorParametersRequiredPage.getErrorMsg());
	}

	@Test
	public void testSearchEmptyResultsInAlertPopup() {
		SearchBasePage searchBasePage = startPage.gotoSearchBasePage();
		SearchSpecificPage searchSpecificPage = searchBasePage.gotoSpecificSearchPage();

		String alertMsg = searchSpecificPage.searchForEmpty();

		assertEquals("The Words field cannot be empty. You have to enter at least one word in your search criteria.",
				alertMsg);
	}
}
