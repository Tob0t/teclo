package at.scch.teclo.pageobjects;

import static org.junit.Assert.assertEquals;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MyBugsPage {

	private StringBuffer verificationErrors = new StringBuffer();
	private final WebDriver driver;
	
	@FindBy(css="span.bz_result_count")
	private WebElement amountOfBugs;
	
	@FindBy(xpath="//div[@id='bugzilla-body']/table/tbody/tr[2]/td[8]")
	private WebElement firstBugSummary;
	
	@FindBy(xpath="//div[@id='bugzilla-body']/table/tbody/tr[2]/td[6]")
	private WebElement firstBugState;
	
	@FindBy(xpath="//div[@id='bugzilla-body']/table/tbody/tr[2]/td[3]")
	private WebElement firstBugPriority;
	
	@FindBy(id="save_newqueryname")
	private WebElement saveSearchField;
	
	@FindBy(id="remember")
	private WebElement saveSearchButton;
	
	@FindBy(linkText="Home")
	private WebElement homeLink;
	
	public MyBugsPage(WebDriver driver){
		this.driver = driver;
	}
	
	public EditBugPage goToEditBug(){

		driver.findElement(By.linkText("1")).click();
	    
	    return PageFactory.initElements(driver, EditBugPage.class);
	}
	
	public MyBugsPage checkEditedBugChanges() {
		
	    try {
	        assertEquals("cri", driver.findElement(By.cssSelector("span[title=\"critical\"]")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	      try {
	        assertEquals("P1", driver.findElement(By.cssSelector("span[title=\"P1\"]")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	      try {
	        assertEquals("Linu", driver.findElement(By.cssSelector("span[title=\"Linux\"]")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
	      try {
	        assertEquals("EditedBug", driver.findElement(By.xpath("//div[@id='bugzilla-body']/table/tbody/tr[2]/td[8]")).getText());
	      } catch (Error e) {
	        verificationErrors.append(e.toString());
	      }
		
		return PageFactory.initElements(driver, MyBugsPage.class);
	}
	
	public int getAmountOfBugs(){
		// Special Case if 0 or 1 bug is found
		if(amountOfBugs.getText().contentEquals("Zarro Boogs found.")){
			return 0;
		} else if(amountOfBugs.getText().contentEquals("One bug found.")){
			return 1;
		}
		
		return Integer.parseInt(amountOfBugs.getText().replaceAll("[^0-9]", ""));
	}
	
	public String getAmountOfBugsText(){
		return amountOfBugs.getText();
	}
	
	public String getSummaryOfFirstBug(){
		return firstBugSummary.getText();
	}
	
	public String getStateOfFirstBug(){
		return firstBugState.getText();
	}
	
	public String getPriorityOfFirstBug(){
		return firstBugPriority.getText();
	}
	
	public EditBugPage selectBug(String bugId){
		driver.findElement(By.linkText(bugId)).click();
	    return PageFactory.initElements(driver, EditBugPage.class);
	}
	
	public MyBugsPage saveSearch(String nameOfSearch){
		saveSearchField.clear();
		saveSearchField.sendKeys(nameOfSearch);
		
		saveSearchButton.click();
		return PageFactory.initElements(driver, MyBugsPage.class);
	}
	
	public MyBugsPage forgetSavedSearch(String savedSearchName){
		driver.findElement(By.linkText(savedSearchName)).click();
		driver.findElement(By.linkText("Forget Search '"+savedSearchName+"'")).click();
		return PageFactory.initElements(driver, MyBugsPage.class);
	}

	
	public LoggedInBasePage navigateToHome(){
		homeLink.click();
		return PageFactory.initElements(driver, LoggedInBasePage.class);
	}
	
}
