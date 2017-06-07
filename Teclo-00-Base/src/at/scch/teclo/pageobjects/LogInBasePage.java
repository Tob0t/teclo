package at.scch.teclo.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInBasePage {

	private final WebDriver driver;

	@FindBy(id = "login_link_top")
	private WebElement loginLink;

	@FindBy(id = "Bugzilla_login_top")
	private WebElement loginFieldUsername;

	@FindBy(id = "Bugzilla_password_top")
	private WebElement loginFieldPassword;

	@FindBy(id = "log_in_top")
	private WebElement loginButton;

	public LogInBasePage(WebDriver driver) {
		this.driver = driver;

		// Check that we're on the right page, the title jumps between "Logged
		// Out" and "Bugzilla Main Page" depending on the previous state
		if (!(("Logged Out").equals(driver.getTitle()) || ("Bugzilla Main Page").equals(driver.getTitle()))) {
			throw new IllegalStateException("This is not the log in page (Title: " + driver.getTitle() + ")!");
		}
	}

	public LoggedInBasePage logIn(String username, String password) {

		driver.findElement(By.id("login_link_top")).click();
		driver.findElement(By.id("Bugzilla_login_top")).click();

		// Note: the default text in the login_top field is "login"
		// for some reason if you call clear() only once the text is not deleted
		// and the username (e.g. "admin) is appended to "login"
		// then the drive tries to login with the username "loginadmin" which
		// obviously fails
		// this can be resolved by calling clear() multiple times
		// Always remember: if it looks stupid, but it works, it ain't stupid!
		driver.findElement(By.id("Bugzilla_login_top")).clear();
		driver.findElement(By.id("Bugzilla_login_top")).clear();
		driver.findElement(By.id("Bugzilla_login_top")).clear();
		driver.findElement(By.id("Bugzilla_login_top")).sendKeys(username);

		// sending Keys.TAB somehow triggers an change event for the
		// Bugzilla_password_top element and makes it visible
		driver.findElement(By.id("Bugzilla_login_top")).sendKeys(Keys.TAB);
		// driver.findElement(By.id("Bugzilla_password_top")).clear();

		driver.findElement(By.id("Bugzilla_password_top")).sendKeys(password);

		driver.findElement(By.id("log_in_top")).click();

		return PageFactory.initElements(driver, LoggedInBasePage.class);
	}

}
