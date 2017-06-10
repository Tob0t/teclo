package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import at.scch.teclo.AbstractBugzillaTestWithLogin;
import at.scch.teclo.BugzillaSetup;
import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.EditBugPage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

public class CreateNewBugTest extends AbstractBugzillaTestWithLogin {
	private static final Logger Logger = LoggerFactory.getLogger(CreateNewBugTest.class);
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss_SSS");

	@Test
	public void testCreateNewBugDefaultValues() throws Exception {
		String summary = "ExampleBugDefault_" + LocalDateTime.now().format(formatter);

		CreateNewBugPage createNewBugPage = startPage.navigateToCreateNewBugPage();
		createNewBugPage.fillSummary(summary);
		
		NewBugCreatedPage newBugCreatedPage = createNewBugPage.commitBug();

		// Check if creating bug was successful
		int newBugId = newBugCreatedPage.getNewBugId();
		assertEquals("Bug " + newBugId + " has been added to the database", newBugCreatedPage.getCreateSuccessMessage());

		Logger.info("Created new default bug with summary " + summary + " and ID " + newBugId);
		
		// verify changes including default values
		// don't verify default values for operating system and platform, which
		// are client values retrieved from browser by default

		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(newBugId);
		
		assertEquals(summary, editBugPage.getSummary());
		
		assertEquals("TestProduct", editBugPage.getProduct());
		assertEquals("TestComponent", editBugPage.getComponent());
		assertEquals("unspecified", editBugPage.getVersion());

		assertEquals("P5", editBugPage.getPriority());
		assertEquals("enhancement", editBugPage.getSeverity());
		assertEquals("", editBugPage.getUrl());
		assertEquals("", editBugPage.getDependsOn());
		assertEquals("", editBugPage.getBlocks());

		assertEquals("0.0", editBugPage.getTimeEstimated());
		assertEquals("0.0", editBugPage.getTimeCurrentEstimation());
		assertEquals("0.0 +", editBugPage.getTimeWorkCompleted());
		assertEquals("0", editBugPage.getTimeWorked());
		assertEquals("0.0", editBugPage.getTimeHoursLeft());
		assertEquals("0", editBugPage.getTimeCompletedInPercent());
		assertEquals("0.0", editBugPage.getTimeGain());
		assertEquals("", editBugPage.getTimeDeadline());
		
		assertEquals("NEW", editBugPage.getBugStatus());
		
		assertEquals("", editBugPage.getComment());
		assertEquals("", editBugPage.getFirstComment());
		assertEquals("", editBugPage.getLastComment());
		assertEquals(1, editBugPage.getNumberOfComments());
	}

	@Test
	public void testCreateEmptySummaryResultsInAlertPopup() throws Exception {
		CreateNewBugPage createNewBugPage = startPage.navigateToCreateNewBugPage();
		createNewBugPage.fillSummary("");

		String alertMsg = createNewBugPage.commitBugWithEmptySummary();

		assertEquals("Please enter a summary sentence for this bug.", alertMsg);
	}

	@Test
	public void testCreateNewBugStandardFields() throws Exception {
		String nowText = LocalDateTime.now().format(formatter);
		String summary = "ExampleBugStandard_" + nowText;
		String comment = "This is an example description for ExampleBugStandard created at " + nowText;

		CreateNewBugPage createNewBugPage = startPage.navigateToCreateNewBugPage();
		createNewBugPage.fillSummary(summary);
		createNewBugPage.fillComment(comment);

		createNewBugPage.fillPlatform("Other");
		createNewBugPage.fillOpSys("Linux");
		createNewBugPage.fillSeverity("major");

		NewBugCreatedPage newBugCreatedPage = createNewBugPage.commitBug();

		// Check if creating bug was successful
		int newBugId = newBugCreatedPage.getNewBugId();
		assertEquals("Bug " + newBugId + " has been added to the database", newBugCreatedPage.getCreateSuccessMessage());

		Logger.info("Created new standard bug with summary " + summary + " and ID " + newBugId);

		// verify values of set fields
		
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(newBugId);
		
		assertEquals(summary, editBugPage.getSummary());
		assertEquals("Other", editBugPage.getPlatform());
		assertEquals("Linux", editBugPage.getOpSys());
		assertEquals("major", editBugPage.getSeverity());
	}

	@Test
	public void testCreateNewBugAdvancedFields() {
		String nowText = LocalDateTime.now().format(formatter);
		String summary = "ExampleBugStandard_" + nowText;
		String comment = "This is an example description for ExampleBugAdvanced created at " + nowText;
			
		CreateNewBugPage createNewBugPage = startPage.navigateToCreateNewBugPage();
		createNewBugPage.toggleAdvancedFields();
		
		createNewBugPage.fillSeverity("blocker");
		createNewBugPage.fillPlatform("All");
		createNewBugPage.fillOpSys("All");
		createNewBugPage.fillPriority("P1");
		
		createNewBugPage.changeBugState("ASSIGNED");
		createNewBugPage.fillCC("admin");
		
		createNewBugPage.fillTimeEstimatedTime(100);
		createNewBugPage.fillTimeDeadline("2016-06-12");
		createNewBugPage.fillURL("http://www.test-bugzilla.org");
		
		createNewBugPage.fillSummary(summary);
		createNewBugPage.fillComment(comment);
		
		NewBugCreatedPage newBugCreatedPage = createNewBugPage.commitBug();

		// Check if creating bug was successful
		int newBugId = newBugCreatedPage.getNewBugId();
		assertEquals("Bug " + newBugId + " has been added to the database", newBugCreatedPage.getCreateSuccessMessage());

		Logger.info("Created new default bug with summary " + summary + " and ID " + newBugId);
		
		// verify values including default values
		
		EditBugPage editBugPage = BugzillaSetup.gotoBugPage(newBugId);
		
		assertEquals(summary, editBugPage.getSummary());
		
		assertEquals("TestProduct", editBugPage.getProduct());
		assertEquals("TestComponent", editBugPage.getComponent());
		assertEquals("unspecified", editBugPage.getVersion());

		assertEquals("P1", editBugPage.getPriority());
		assertEquals("blocker", editBugPage.getSeverity());
		assertEquals("http://www.test-bugzilla.org", editBugPage.getUrl());
		assertEquals("", editBugPage.getDependsOn());
		assertEquals("", editBugPage.getBlocks());

		assertEquals("100.0", editBugPage.getTimeEstimated());
		assertEquals("100.0", editBugPage.getTimeCurrentEstimation());
		assertEquals("0.0 +", editBugPage.getTimeWorkCompleted());
		assertEquals("0", editBugPage.getTimeWorked());
		assertEquals("100.0", editBugPage.getTimeHoursLeft());
		assertEquals("0", editBugPage.getTimeCompletedInPercent());
		assertEquals("0.0", editBugPage.getTimeGain());
		assertEquals("2016-06-12", editBugPage.getTimeDeadline());
		
		assertEquals("", editBugPage.getComment());
		assertEquals("ASSIGNED", editBugPage.getBugStatus());
		assertEquals(comment, editBugPage.getFirstComment());
		assertEquals(comment, editBugPage.getLastComment());
		assertEquals(1, editBugPage.getNumberOfComments());
	}
}
