package at.scch.teclo;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Helper {

	public static final long TIIMEOUT_SECONDS = 10;
	
	/***
	 * Helper method to read the selected option
	 * 
	 * @param web
	 *            element
	 * @return selected option as string
	 */
	public static String getSelectedOptionValue(WebElement element) {
		Select select = new Select(element);
		WebElement selectedElement = select.getFirstSelectedOption();
		String selectedOption = selectedElement.getAttribute("value");
		return selectedOption;
	}
	
	public static void setTimeout(WebDriver driver) {
		driver.manage().timeouts().implicitlyWait(TIIMEOUT_SECONDS, TimeUnit.SECONDS);
	}
	
	public static boolean isElementPresent(WebDriver driver, By by) {
		boolean elementFound = false;
		// change the waiting time 0 seconds if the element is not found
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			driver.findElement(by);
			elementFound = true;
		} catch (NoSuchElementException e) {
			elementFound = false;
		} finally {
			// change the waiting time back to 10 seconds
			driver.manage().timeouts().implicitlyWait(TIIMEOUT_SECONDS, TimeUnit.SECONDS);
		}
		return elementFound;
	}

}
