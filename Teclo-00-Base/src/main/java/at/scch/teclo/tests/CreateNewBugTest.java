package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

public class CreateNewBugTest extends AbstractBugzillaTestWithLogin {
	private static final Logger Logger = LoggerFactory.getLogger(CreateNewBugTest.class);
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

	@Test
	public void testCreateNewBugDefaultValues() throws Exception {
		String nowText = LocalDateTime.now().format(formatter);
		String summary = "ExampleBugDefault_" + nowText;
		String comment = "This is an example description for ExampleBugDefault created at " + nowText;

		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		createNewBugPage.fillSummary(summary);
		
		NewBugCreatedPage newBugCreatedPage = createNewBugPage.commitBug();

		// Check if creating bug was successful
		int bugId = newBugCreatedPage.getBugID();
		assertEquals("Bug " + bugId + " has been added to the database", newBugCreatedPage.getBugWasAddedText());

		Logger.info("Created new default bug with summary " + summary + " and ID " + bugId);
		
		// verify changes including default values
		// don't verify default values for operating system and platform, which
		// are client values retrieved from browser by default

		assertEquals(summary, newBugCreatedPage.getSummary());
		
		assertEquals("TestProduct", newBugCreatedPage.getCurrentProduct());
		assertEquals("TestComponent", newBugCreatedPage.getCurrentComponent());
		assertEquals("unspecified", newBugCreatedPage.getCurrentVersion());

		assertEquals("P5", newBugCreatedPage.getCurrentPriority());
		assertEquals("enhancement", newBugCreatedPage.getCurrentSeverity());
		assertEquals("", newBugCreatedPage.getCurrentURL());
		assertEquals("", newBugCreatedPage.getCurrentDependsOn());
		assertEquals("", newBugCreatedPage.getCurrentBlocks());

		assertEquals("0.0", newBugCreatedPage.getTimeEstimatedTime());
		assertEquals("0.0", newBugCreatedPage.getTimeCurrentEstimation());
		assertEquals("0.0 +", newBugCreatedPage.getTimeWorkTime());
		assertEquals("0.0", newBugCreatedPage.getTimeRemainingTime());
		assertEquals("0", newBugCreatedPage.getTimeCompletedInPercent());
		assertEquals("0.0", newBugCreatedPage.getTimeGain());
		assertEquals("", newBugCreatedPage.getTimeDeadline());
		
		assertEquals("", newBugCreatedPage.getCurrentComment());
		assertEquals("NEW", newBugCreatedPage.getCurrentBugState());
		assertEquals("", newBugCreatedPage.getCommentOfFirstBug());
	}

	@Test
	public void testCreateEmptySummaryResultsInAlertPopup() throws Exception {
		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		createNewBugPage.fillSummary("");

		String alertMsg = createNewBugPage.commitBugWithEmptySummary();

		assertEquals("Please enter a summary sentence for this bug.", alertMsg);
	}

	@Test
	public void testCreateNewBugStandardFields() throws Exception {
		String nowText = LocalDateTime.now().format(formatter);
		String summary = "ExampleBugStandard_" + nowText;
		String comment = "This is an example description for ExampleBugStandard created at " + nowText;

		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		createNewBugPage.fillSummary(summary);
		createNewBugPage.fillComment(comment);

		createNewBugPage.fillPlatform("Other");
		createNewBugPage.fillOpSys("Linux");
		createNewBugPage.fillSeverity("major");

		NewBugCreatedPage newBugCreatedPage = createNewBugPage.commitBug();

		// Check if creating bug was successful
		int bugId = newBugCreatedPage.getBugID();
		assertEquals("Bug " + bugId + " has been added to the database", newBugCreatedPage.getBugWasAddedText());

		Logger.info("Created new standard bug with summary " + summary + " and ID " + bugId);

		// verify changes
		assertEquals(summary, newBugCreatedPage.getSummary());
		assertEquals("Other", newBugCreatedPage.getCurrentPlatform());
		assertEquals("Linux", newBugCreatedPage.getCurrentOpSys());
		assertEquals("major", newBugCreatedPage.getCurrentSeverity());
	}

	@Test
	public void testCreateNewBugAdvancedFields() {
		String nowText = LocalDateTime.now().format(formatter);
		String summary = "ExampleBugStandard_" + nowText;
		String comment = "This is an example description for ExampleBugAdvanced created at " + nowText;
			
		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		createNewBugPage.toggleAdvancedFields();
		
		createNewBugPage.fillSeverity("blocker");
		createNewBugPage.fillPlatform("All");
		createNewBugPage.fillOpSys("All");
		createNewBugPage.fillPriority("P1");
		
		createNewBugPage.changeBugState("ASSIGNED");
		createNewBugPage.fillCC("admin");
		
		createNewBugPage.fillTimeEstimatedTime(100);
		createNewBugPage.fillTimeDeadline("2016-06-12");
		createNewBugPage.fillURL("http://www.bugzilla.org");
		
		createNewBugPage.fillSummary(summary);
		createNewBugPage.fillComment(comment);
		
		NewBugCreatedPage newBugCreatedPage = createNewBugPage.commitBug();

		// Check if creating bug was successful
		int bugId = newBugCreatedPage.getBugID();
		assertEquals("Bug " + bugId + " has been added to the database", newBugCreatedPage.getBugWasAddedText());

		Logger.info("Created new default bug with summary " + summary + " and ID " + bugId);
		System.out.println(newBugCreatedPage.getCommentOfFirstBug());
		
		// verify changes including default values
		assertEquals(summary, newBugCreatedPage.getSummary());
		
		assertEquals("TestProduct", newBugCreatedPage.getCurrentProduct());
		assertEquals("TestComponent", newBugCreatedPage.getCurrentComponent());
		assertEquals("unspecified", newBugCreatedPage.getCurrentVersion());

		assertEquals("P1", newBugCreatedPage.getCurrentPriority());
		assertEquals("blocker", newBugCreatedPage.getCurrentSeverity());
		assertEquals("http://www.bugzilla.org", newBugCreatedPage.getCurrentURL());
		assertEquals("", newBugCreatedPage.getCurrentDependsOn());
		assertEquals("", newBugCreatedPage.getCurrentBlocks());

		assertEquals("100.0", newBugCreatedPage.getTimeEstimatedTime());
		assertEquals("100.0", newBugCreatedPage.getTimeCurrentEstimation());
		assertEquals("0.0 +", newBugCreatedPage.getTimeWorkTime());
		assertEquals("100.0", newBugCreatedPage.getTimeRemainingTime());
		assertEquals("0", newBugCreatedPage.getTimeCompletedInPercent());
		assertEquals("0.0", newBugCreatedPage.getTimeGain());
		assertEquals("2016-06-12", newBugCreatedPage.getTimeDeadline());
		
		assertEquals("", newBugCreatedPage.getCurrentComment());
		assertEquals("ASSIGNED", newBugCreatedPage.getCurrentBugState());
		assertEquals(comment, newBugCreatedPage.getCommentOfFirstBug());
		
		
	}
}
