package at.scch.teclo.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.scch.teclo.AbstractBugzillaTest;

@RunWith(Suite.class)
@SuiteClasses({ LoginLogoutTest.class, CreateNewBugTest.class, EditBugTest.class, ChangeBugStatusTest.class,
		SearchQuickTest.class, SearchSpecificTest.class, SearchAdvancedTest.class, GenerateTabularReportTest.class })
public class AllTests extends AbstractBugzillaTest { // NOSONAR

}
