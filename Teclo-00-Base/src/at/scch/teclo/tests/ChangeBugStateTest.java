package at.scch.teclo.tests;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.LoggedInBasePage;
import at.scch.teclo.pageobjects.MyBugsPage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

public class ChangeBugStateTest {
	private WebDriver driver;
	private StringBuffer verificationErrors = new StringBuffer();

	private NewBugCreatedPage newBugCreatedPage;
	private LoggedInBasePage loggedInBasePage;

	@Before
	public void setUp() throws Exception {
		driver = BugzillaSetup.getWebDriver();

		// precondition: logged in
		loggedInBasePage = BugzillaSetup.LogIn();

		// precondition: bug inserted
		newBugCreatedPage = BugzillaSetup.CreateExampleBug(loggedInBasePage);
	}

	@Test
	public void testChangeBugState() throws Exception {
		// TODO determine id automatically from createExampleBug
		String currentBugId = "1";
		
		MyBugsPage myBugsPage = loggedInBasePage.navigateToMyBugsPage();
		

		int currentAmountOfBugs = myBugsPage.getAmountOfBugs();
		
		EditBugPage editBugPage = myBugsPage.selectBug(currentBugId);
		
		try {
			assertEquals("NEW", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		editBugPage.changeBugState("ASSIGNED");
		editBugPage = editBugPage.commitBug();
		
		editBugPage = editBugPage.selectCommitedBug(currentBugId);
		
		try {
			assertEquals("ASSIGNED", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		editBugPage.changeBugState("RESOLVED");
		editBugPage = editBugPage.commitBug();
		
		editBugPage = editBugPage.selectCommitedBug(currentBugId);
		
		try {
			assertEquals("RESOLVED", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		editBugPage.changeBugState("VERIFIED");
		editBugPage = editBugPage.commitBug();
		
		editBugPage = editBugPage.selectCommitedBug(currentBugId);
		
		try {
			assertEquals("VERIFIED", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		editBugPage.changeBugState("CLOSED");
		editBugPage = editBugPage.commitBug();
		
		editBugPage = editBugPage.selectCommitedBug(currentBugId);
		
		try {
			assertEquals("CLOSED", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		// TODO: search for the bug to access it when its closed
		/* 
		myBugsPage = editBugPage.navigateToMyBugsPage();
		
		try {
			assertEquals(currentAmountOfBugs-1, myBugsPage.getAmountOfBugs());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		editBugPage = editBugPage.selectCommitedBug(currentBugId);
		*/
		
		editBugPage.changeBugState("REOPENED");
		editBugPage = editBugPage.commitBug();
		
		editBugPage = editBugPage.selectCommitedBug(currentBugId);
		
		try {
			assertEquals("REOPENED", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		editBugPage.changeBugState("NEW");
		editBugPage = editBugPage.commitBug();
		
		editBugPage = editBugPage.selectCommitedBug(currentBugId);
		
		try {
			assertEquals("NEW", editBugPage.getCurrentBugState());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
		myBugsPage = editBugPage.navigateToMyBugsPage();
		
		try {
			assertEquals(currentAmountOfBugs, myBugsPage.getAmountOfBugs());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}

}
