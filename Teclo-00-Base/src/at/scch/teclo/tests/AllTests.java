package at.scch.teclo.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.scch.teclo.BugzillaSetup;

@RunWith(Suite.class)
@SuiteClasses({ LoginLogoutTest.class, CreateNewBugTest.class, EditBugTest.class,
		ChangeBugStateTest.class, FindBugSuccessTest.class, FindBugZarroTest.class,
		FindSpecificSearchTest.class, FindAdvancedSearchTest.class, FindBooleanChartTest.class,
		GenerateTabularReportTest.class, SaveSearchTest.class })
public class AllTests {

	@BeforeClass
	public void setUpDriver(){
		BugzillaSetup.getWebDriver();
	}
	
	@AfterClass
	public void tearDownDriver(){
		BugzillaSetup.ungetWebDriver();
	}
	
}
