package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class EditBugPage {

private final WebDriver driver;
	
	@FindBy(id="bug_status")
	private WebElement bugState;
	
	@FindBy(id="commit")
	private WebElement commitButton;
	

	public EditBugPage(WebDriver driver){
		this.driver = driver;
	}
	
	public MyBugsPage editBug(){

		driver.findElement(By.id("editme_action")).click();
	    driver.findElement(By.id("short_desc")).clear();
	    driver.findElement(By.id("short_desc")).sendKeys("EditedBug");
	    new Select(driver.findElement(By.id("rep_platform"))).selectByVisibleText("Other");
	    new Select(driver.findElement(By.id("op_sys"))).selectByVisibleText("Linux");
	    new Select(driver.findElement(By.id("priority"))).selectByVisibleText("P1");
	    new Select(driver.findElement(By.id("bug_severity"))).selectByVisibleText("critical");
	    driver.findElement(By.id("commit")).click();
	    driver.findElement(By.linkText("My Bugs")).click();
	    
	    return PageFactory.initElements(driver, MyBugsPage.class);
	}
	
	public String getCurrentBugState(){
		return getSelectedOptionValue(bugState);
	}
	
	public void changeBugState(String bugStateString){
		new Select(bugState).selectByVisibleText(bugStateString);
	}
	
	public EditBugPage commitBug(){
		commitButton.click();
		return PageFactory.initElements(driver, EditBugPage.class);
	}
	
	public EditBugPage selectCommitedBug(String currentBugId){
		driver.findElement(By.linkText("bug "+currentBugId)).click();
		return PageFactory.initElements(driver, EditBugPage.class);
	}
	
	public LoggedInBasePage navigateToMyHomePage() {
		driver.findElement(By.linkText("Home")).click();
		return PageFactory.initElements(driver, LoggedInBasePage.class);
	}
	
	public MyBugsPage navigateToMyBugsPage() {
		driver.findElement(By.linkText("My Bugs")).click();
		
		return PageFactory.initElements(driver, MyBugsPage.class);
	}
	
	
	/***
	 * Helper method to read the selected option
	 * @param web element
	 * @return selected option as string
	 */
	private String getSelectedOptionValue(WebElement element) {
		Select select = new Select(element);
		WebElement selectedElement = select.getFirstSelectedOption();
		String selectedOption = selectedElement.getAttribute("value");
		return selectedOption;
	}
	
}
