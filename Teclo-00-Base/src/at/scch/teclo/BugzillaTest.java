package at.scch.teclo;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BugzillaTest {
	protected WebDriver driver;

	@Before
	public void setUpDriver(){
		driver = BugzillaSetup.getWebDriver();
	}
	
	@After
	public void tearDownDriver(){
		BugzillaSetup.ungetWebDriver();
	}

}
