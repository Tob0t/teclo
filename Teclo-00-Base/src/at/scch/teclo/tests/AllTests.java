package at.scch.teclo.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.scch.teclo.BugzillaSetup;

@RunWith(Suite.class)
@SuiteClasses({ LoginLogoutTest.class, CreateNewBugTest.class, EditBugTest.class,
		ChangeBugStateTest.class, FindQuickSearchTest.class, FindSpecificSearchTest.class,
		FindAdvancedSearchTest.class, FindBooleanChartTest.class, GenerateTabularReportTest.class,
		SaveSearchTest.class })
public class AllTests {

	@BeforeClass
	public static void setUpDriver(){
		// load snapshot
		
		// initialize web driver
		BugzillaSetup.getWebDriver();
		
		// create example Bug
		BugzillaSetup.createExampleBug();
				
	}
	
	@AfterClass
	public static void tearDownDriver(){
		BugzillaSetup.ungetWebDriver();
	}
	
}
