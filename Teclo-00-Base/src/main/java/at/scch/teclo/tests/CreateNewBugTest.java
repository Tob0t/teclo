package at.scch.teclo.tests;

import static org.junit.Assert.assertEquals;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import at.scch.teclo.BugzillaTest;
import at.scch.teclo.pageobjects.CreateNewBugPage;
import at.scch.teclo.pageobjects.NewBugCreatedPage;

public class CreateNewBugTest extends BugzillaTest {

	@Test
	public void testCreateNewBugDefaultFields() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
		String summary = "ExampleBugDefault_" + dateFormat.format(new Date());
		String comment = "This is an example description for ExampleBugDefault created at "
				+ dateFormat.format(new Date());

		CreateNewBugPage createNewBugPage = loggedInBasePage.navigateToCreateNewBugPage();
		createNewBugPage.fillSummary(summary);
		createNewBugPage.fillComment(comment);

		NewBugCreatedPage newBugCreatedPage = createNewBugPage.commitBug();

		// Check if creating bug was successful
		int bugId = newBugCreatedPage.getBugID();
		assertEquals("Bug " + bugId + " has been added to the database", newBugCreatedPage.getBugWasAddedText());

		System.out.println("Created new default bug with summary " + summary + " and ID " + bugId);

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
	public void testCreateNewBugAdvancedFields() throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss_SSS");
		String summary = "ExampleBugAdvanced_" + dateFormat.format(new Date());
		String comment = "This is an example description for ExampleBugAdvanced created at "
				+ dateFormat.format(new Date());

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

		System.out.println("Created new adv.ced bug with summary " + summary + " and ID " + bugId);

		// verify changes
		assertEquals(summary, newBugCreatedPage.getSummary());
		assertEquals("Other", newBugCreatedPage.getCurrentPlatform());
		assertEquals("Linux", newBugCreatedPage.getCurrentOpSys());
		assertEquals("major", newBugCreatedPage.getCurrentSeverity());
	}
}
