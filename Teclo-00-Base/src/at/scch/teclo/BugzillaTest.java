package at.scch.teclo;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

public abstract class BugzillaTest {
	protected WebDriver driver;
	protected final StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUpDriver(){
		driver = BugzillaSetup.getWebDriver();
	}
	
	@After
	public void tearDownDriver(){
		BugzillaSetup.ungetWebDriver();
		
		String verificationErrorString = verificationErrors .toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
