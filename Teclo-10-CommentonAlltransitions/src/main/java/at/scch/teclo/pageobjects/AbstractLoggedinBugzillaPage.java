package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;

public abstract class AbstractLoggedinBugzillaPage extends AbstractBugzillaPage {

	public AbstractLoggedinBugzillaPage(WebDriver driver) {
		super(driver);
		
		if (!isLoggedin()) {
			throw new IllegalStateException("Page object " + this.getClass().getName()
					+ " requires beeing logged in to use displayed page (title: " + driver.getTitle() + ")!");
		}
	}

}
