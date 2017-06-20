package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;

public class ErrorCommentRequiredPage extends AbstractErrorPage {

	public ErrorCommentRequiredPage(WebDriver driver) {
		super(driver);
	}

	@Override
	protected boolean isMatchingPage() {
		return "Comment Required".equals(getTitle());
	}

}