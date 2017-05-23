package at.scch.teclo.pageobjects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoggedInBasePage {

	private StringBuffer verificationErrors = new StringBuffer();
	private final WebDriver driver;
	
	@FindBy(id="quicksearch_top")
	private WebElement quickSearch;
	
	@FindBy(id="find_top")
	private WebElement quickFindButton;
	
	
	public LoggedInBasePage(WebDriver driver){
		this.driver = driver;
	}

	public LoggedInBasePage checkLogInStatus(){
		 try {
		      assertTrue(isElementPresent(By.linkText("Log out")));
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }
		    try {
		      assertEquals("| Log out admin", driver.findElement(By.xpath("//div[@id='header']/ul/li[9]")).getText());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }		    
	    
	    return PageFactory.initElements(driver, LoggedInBasePage.class);
	    
	}
	
	public LoggedOutBasePage logOut(){
		driver.findElement(By.linkText("Log out")).click();
		
		return PageFactory.initElements(driver, LoggedOutBasePage.class);
	}
	
	public CreateNewBugPage navigateToCreateNewBugPage() {
		driver.findElement(By.linkText("New")).click();
	    
	    return PageFactory.initElements(driver, CreateNewBugPage.class);
	}
	
	public MyBugsPage navigateToMyBugsPage() {
		driver.findElement(By.linkText("My Bugs")).click();
		
		return PageFactory.initElements(driver, MyBugsPage.class);
	}
	
	public MyBugsPage searchFor(String searchTerm){
		quickSearch.clear();
		quickSearch.sendKeys(searchTerm);
		quickFindButton.click();
		
		return PageFactory.initElements(driver, MyBugsPage.class);
	}
	
	
	private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }
	
}
