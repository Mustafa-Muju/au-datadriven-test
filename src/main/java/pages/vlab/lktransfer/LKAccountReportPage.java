package main.java.pages.vlab.lktransfer;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class LKAccountReportPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(tagName = "th")
	private List<WebElement> eleAccountTableHeader;

	@FindBy(xpath = "//td//a")
	private List<WebElement> eleAccountEllkyResult;

	public LKAccountReportPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * 
	 * @This method to verify the LK Transfer Advanced Search page is displayed
	 * 
	 * @throws Exception
	 *
	 */
	public void verifyLKTransferAdvancedSearchPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Advanced Search");
		CommonFunctions.logMessage("LK Transfer Advanced Search Page is Displayed");
	}

	/**
	 * 
	 * @This method to select the ellky result from the account table
	 * 
	 * @param result
	 * @throws Exception
	 */
	public void selectAccountResult(String result) throws Exception {
		boolean flag = true;

		result = result.replaceAll(":(\\d)", "") + ".json";
		
		int count = 0;

		while (flag && count<20) {

			CommonFunctions.logMessage("Searching for the ellky result in table");

			try {

				for (int i = 0; i < eleAccountEllkyResult.size(); i++) {
					String content = CommonFunctions.getTextOfElement(eleAccountEllkyResult.get(i),
							"account result value");

					if (content.equalsIgnoreCase(result)) {
						CommonFunctions.clickJSE(eleAccountEllkyResult.get(i), result + " ellky result");
						flag = false;
						break;
					}
				}
			} catch (Exception e) {
				CommonFunctions.logErrorMessageStopExecution(
						"Error, while searching the column file name for the ellky result - " + result);
			}

			if (flag) {
				CommonFunctions.refreshPage(getDriver());
				Thread.sleep(10000);
			}
			
			count++;
		}

		if (flag) {
			CommonFunctions.logErrorMessageStopExecution(
					"Error, unable to find the session file submitted for the ellky - " + result);
		}

	}

}
