package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BugResultsPage extends AbstractLoggedinBugzillaPage {

	@FindBy(css = "span.bz_result_count")
	private WebElement amountOfBugs;

	@FindBy(xpath = "//div[@id='bugzilla-body']/table/tbody/tr[2]/td[1]")
	private WebElement firstBugID;

	@FindBy(xpath = "//div[@id='bugzilla-body']/table/tbody/tr[2]/td[8]")
	private WebElement firstBugSummary;

	@FindBy(xpath = "//div[@id='bugzilla-body']/table/tbody/tr[2]/td[6]")
	private WebElement firstBugState;

	@FindBy(xpath = "//div[@id='bugzilla-body']/table/tbody/tr[2]/td[3]")
	private WebElement firstBugPriority;

	@FindBy(id = "save_newqueryname")
	private WebElement saveSearchField;

	@FindBy(id = "remember")
	private WebElement saveSearchButton;

	@FindBy(linkText = "Home")
	private WebElement homeLink;

	
	public BugResultsPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean matchingPageIsDisplayed() {
		return getTitle().matches("Bug List.*|Search created|Search is gone");
	}
	
	
	public EditBugPage goToEditBug(int currentBugID) {
		driver.findElement(By.linkText(String.valueOf(currentBugID))).click();

		return PageFactory.initElements(driver, EditBugPage.class);
	}

	public int getAmountOfBugs() {
		// Special Case if 0 or 1 bug is found
		if (amountOfBugs.getText().contentEquals("Zarro Boogs found.")) {
			return 0;
		} else if (amountOfBugs.getText().contentEquals("One bug found.")) {
			return 1;
		}

		return Integer.parseInt(amountOfBugs.getText().replaceAll("[^0-9]", ""));
	}

	public String getAmountOfBugsText() {
		return amountOfBugs.getText();
	}

	public int getIDOfFirstBug() {
		return Integer.parseInt(firstBugID.getText());
	}

	public String getSummaryOfFirstBug() {
		return firstBugSummary.getText();
	}

	public String getStateOfFirstBug() {
		return firstBugState.getText();
	}

	public String getPriorityOfFirstBug() {
		return firstBugPriority.getText();
	}

	public BugResultsPage saveSearch(String nameOfSearch) {
		saveSearchField.clear();
		saveSearchField.sendKeys(nameOfSearch);

		saveSearchButton.click();
		return PageFactory.initElements(driver, BugResultsPage.class);
	}

	public BugResultsPage forgetSavedSearch(String savedSearchName) {
		driver.findElement(By.linkText(savedSearchName)).click();
		driver.findElement(By.linkText("Forget Search '" + savedSearchName + "'")).click();
		return PageFactory.initElements(driver, BugResultsPage.class);
	}

}
