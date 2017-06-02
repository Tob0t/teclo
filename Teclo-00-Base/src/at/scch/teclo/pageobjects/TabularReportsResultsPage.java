package at.scch.teclo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class TabularReportsResultsPage {
	@FindBy(xpath = "//div[@id='bugzilla-body']/div/table/tbody/tr/td[2]/strong")
	private WebElement xAxesDescription;

	@FindBy(xpath = "//div[@id='bugzilla-body']/div/table/tbody/tr[2]/td/strong")
	private WebElement yAxesDescription;

	public TabularReportsResultsPage(WebDriver driver) {
	}

	public String getXAxesDescription() {
		return xAxesDescription.getText();
	}

	public String getYAxesDescription() {
		return yAxesDescription.getText();
	}

}
