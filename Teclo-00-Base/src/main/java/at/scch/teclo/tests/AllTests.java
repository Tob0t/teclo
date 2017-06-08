package at.scch.teclo.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.scch.teclo.AbstractBugzillaTest;

@RunWith(Suite.class)
@SuiteClasses({ LoginLogoutTest.class, CreateNewBugTest.class, EditBugTest.class, ChangeBugStateTest.class,
		FindQuickSearchTest.class, FindSpecificSearchTest.class, FindAdvancedSearchTest.class,
		GenerateTabularReportTest.class, SaveSearchTest.class })
public class AllTests extends AbstractBugzillaTest { // NOSONAR
	//
	// @BeforeClass
	// public static void setUpDriver() {
	// // load snapshot
	//
	// // initialize web driver
	// BugzillaSetup.openWebDriver();
	//
	// BugzillaSetup.login();
	// BugzillaSetup.createExampleBug();
	// }
	//
	// @AfterClass
	// public static void tearDownDriver() {
	// BugzillaSetup.logout();
	// BugzillaSetup.closeWebDriver();
	// }
}
