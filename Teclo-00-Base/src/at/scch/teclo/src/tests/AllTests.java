package at.scch.teclo.src.tests;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import at.scch.teclo.src.BugzillaSetup;

@RunWith(Suite.class)
@SuiteClasses({LoginLogoutTest.class})
public class AllTests {
		
    @ClassRule
    public static ExternalResource setUpRule = new ExternalResource(){
            @Override
            protected void before() throws Throwable{
                BugzillaSetup.setUp();
            };

            @Override
            protected void after(){
                BugzillaSetup.tearDown();
            };
        };
        
	
	

}
