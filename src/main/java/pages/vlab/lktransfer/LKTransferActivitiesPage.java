package main.java.pages.vlab.lktransfer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class LKTransferActivitiesPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "AccountName")
	private WebElement eleAccountName;

	@FindBy(id = "btnSearch")
	private WebElement eleSearch;

	@FindBy(partialLinkText = "Clear")
	private WebElement eleClear;

	@FindBy(className = "spnInfo")
	private WebElement eleRecordCount;

	@FindBy(id = "txtcountid")
	private WebElement eleRecordTxt;

	@FindBy(tagName = "th")
	private List<WebElement> eleActivityTableHeader;

	public LKTransferActivitiesPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * 
	 * @This method to verify the LK Transfer Activities page is displayed
	 * 
	 * @throws Exception
	 *
	 */
	public void verifyLKTransferActivitiesPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Activities");
		CommonFunctions.logMessage("LK Transfer Activities Page is Displayed");
	}

	/**
	 * 
	 * @This method is to enter the account name
	 * 
	 * @param accountName
	 * @throws Exception
	 */
	public void enterAccountName(String accountName) throws Exception {
		CommonFunctions.Sendkeys(eleAccountName, accountName, "Account Name");
	}

	/**
	 * 
	 * @This method is used to click the Search Button
	 * 
	 * @throws Exception
	 */
	public void clickSearchBtn() throws Exception {
		CommonFunctions.clickWebelement(eleSearch, "Search Button");
	}

	/**
	 * 
	 * @This method is used to click the Clear Button
	 * 
	 * @throws Exception
	 */
	public void clickClearBtn() throws Exception {
		CommonFunctions.clickWebelement(eleClear, "Clear Button");
	}

	/**
	 * 
	 * @This method is to list all the record count
	 * 
	 * @throws Exception
	 */
	public void listAllRecords() throws Exception {
		String count = CommonFunctions.getTextOfElement(eleRecordCount, "record count").replaceAll("(.*)of|records|\\s",
				"");
		System.out.println(count);
		CommonFunctions.Sendkeys(eleRecordTxt, count, "record count");
		try {
			eleRecordTxt.sendKeys(Keys.ENTER);
			Thread.sleep(2000);
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution(
					"Error, While clicking the enter button over the element record count!");
		}

	}

	/**
	 * 
	 * @This method to select the account from the activity table
	 * 
	 * @param headerName
	 * @param tableValue
	 * @throws Exception
	 */
	public void selectAccount(String headerName, String tableValue) throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		Map<String, Integer> headers = new HashMap<String, Integer>();
		for (int h = 1; h < eleActivityTableHeader.size(); h++) {
			headers.put(eleActivityTableHeader.get((h - 1)).getText(), h);
		}

		boolean flag = true;

		try {
			List<WebElement> tableContents = getDriver().findElements(By.xpath("//td[" + headers.get(headerName) + "]//a"));

			for (int i = 0; i < tableContents.size(); i++) {
				String content = CommonFunctions.getTextOfElement(tableContents.get(i), "table content value");
				if (content.equalsIgnoreCase(tableValue)) {
					//Thread.sleep(2000);
					//CommonFunctions.actionClick(tableContents.get(i), tableValue + " table data");
					CommonFunctions.clickWebelement(tableContents.get(i), tableValue + " table data");
					flag = false;
					break;
				}
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution(
					"Error, while searching the column " + headerName + " and the table data " + tableValue);
		}

		if (flag) {
			CommonFunctions.logErrorMessageStopExecution(
					"Error unable to find the column " + headerName + " and the table data " + tableValue);
		}

	}

}
