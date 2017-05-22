package at.scch.teclo.src.pageobjects;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LogInErrorPage {
	
	private StringBuffer verificationErrors = new StringBuffer();
	private final WebDriver driver;
	
	public LogInErrorPage(WebDriver driver){
		this.driver = driver;
	}

	public LogInErrorPage checkLogInErrorStatus(){
		try {
		      assertEquals("Invalid Username Or Password", driver.getTitle());
		    } catch (Error e) {
		      verificationErrors.append(e.toString());
		    }		    
	    
	    return PageFactory.initElements(driver, LogInErrorPage.class);
	    
	}
	
}
