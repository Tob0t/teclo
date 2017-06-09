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
		createNewBugPage.fillComment(comment);

		NewBugCreatedPage newBugCreatedPage = createNewBugPage.commitBug();

		// Check if creating bug was successful
		int bugId = newBugCreatedPage.getBugID();
		assertEquals("Bug " + bugId + " has been added to the database", newBugCreatedPage.getBugWasAddedText());

		Logger.info("Created new default bug with summary " + summary + " and ID " + bugId);

		// TODO see email 8.6.
		fail("needs to be extended");

		// verify changes including default values
		// don't verify default values for operating system and platform, which
		// are client values retrieved from browser by default

		assertEquals(summary, newBugCreatedPage.getSummary());

		assertEquals("P5", newBugCreatedPage.getCurrentPriority());
		assertEquals("enhancement", newBugCreatedPage.getCurrentSeverity());

		assertEquals("0.0", newBugCreatedPage.getTimeEstimatedTime());
		assertEquals("0.0 +", newBugCreatedPage.getTimeWorkTime());
		assertEquals("0.0", newBugCreatedPage.getTimeRemainingTime());
		assertEquals("", newBugCreatedPage.getTimeDeadline());
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
		// TODO
		fail("not yet implemented");
	}
}
