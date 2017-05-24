package at.scch.teclo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Helper {
	
	/***
	 * Helper method to read the selected option
	 * @param web element
	 * @return selected option as string
	 */
	public static String getSelectedOptionValue(WebElement element) {
		Select select = new Select(element);
		WebElement selectedElement = select.getFirstSelectedOption();
		String selectedOption = selectedElement.getAttribute("value");
		return selectedOption;
	}

}
