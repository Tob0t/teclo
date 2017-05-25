package at.scch.teclo.tests;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({LoginLogoutTest.class, CreateNewBugTest.class, EditBugTest.class,
	ChangeBugStateTest.class, FindBugSuccessTest.class, FindBugZarroTest.class,
	FindSpecificSearchTest.class, FindAdvancedSearchTest.class, FindBooleanChartTest.class,
	GenerateTabularReportTest.class, SaveSearchTest.class})
public class AllTests {
		

}
