package main.java.pages.vlab.core;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class LabProcedureInitPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//h1[contains(text(),\"Let's make sure\")]")
	private WebElement eleDeviceReadinessHeader;

	@FindBy(xpath = "//button[text()='Start']")
	private WebElement eleReadinessStart;

	@FindBy(xpath = "//button[text()='Skip']")
	private WebElement eleReadinessSkip;

	@FindBy(xpath = "//*[contains(text(),\"Yes, let's start the test\")]")
	private WebElement eleStartLabTest;

	@FindBy(xpath = "//*[text()='Choose an agent to connect to:']//following::tr//a")
	private List<WebElement> eleChooseAgent;

	@FindBy(xpath = "//*[contains(text(),'Please hold while we connect you')]")
	private WebElement eleWaitingForGuide;

	public LabProcedureInitPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * 
	 * @Method is to verify the core lab procedure
	 * 
	 * @throws Exception
	 */
	public void verifyCoreLabReadinessCheck(String coreProcedurePageTitle) throws Exception {
		CommonFunctions.checkCurrentPageTitle(coreProcedurePageTitle);
		boolean readinessCheckHeader = CommonFunctions.elementVisibleToCheck(eleDeviceReadinessHeader, 10);
		if (readinessCheckHeader) {
			CommonFunctions.logMessage("Device readiness checker page is displayed");
		} else {
			CommonFunctions.logErrorMessageStopExecution("Error device readiness checker page is not displayed");
		}
	}

	/**
	 * 
	 * @This method is used to click start button for the readiness checker
	 * 
	 * @throws Exception
	 */
	public void clickStart() throws Exception {
		CommonFunctions.clickWebelement(eleReadinessStart, "user readiness start");
	}

	/**
	 * 
	 * @This method is used to click skip button for the readiness checker
	 * 
	 * @throws Exception
	 */
	public void clickSkip() throws Exception {
		CommonFunctions.clickWebelement(eleReadinessSkip, "user readiness skip");
	}

	/**
	 * 
	 * @This method is used to click start the lab test
	 * 
	 * @throws Exception
	 */
	public void clickStartLabTest() throws Exception {
		CommonFunctions.clickWebelement(eleStartLabTest, "start the lab test");
	}

	/**
	 * 
	 * @This method to select the agent from the training list
	 * 
	 * @throws Exception
	 */
	public void chooseTheAgent(String agentName) throws Exception {
		try {
			for (int agent = 0; agent < eleChooseAgent.size(); agent++) {
				if (eleChooseAgent.get(agent).getText().equalsIgnoreCase(agentName)) {
					CommonFunctions.clickWebelement(eleChooseAgent.get(agent),
							"Agent " + eleChooseAgent.get(agent).getText());
				}
			}
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error unable to choose the agent from the training list");
		}
	}

	/**
	 * 
	 * @This method is verify that the waiting for the guide page is displayed
	 * 
	 * @throws Exception
	 */
	public void verifyWaitingForGuide() throws Exception {
		boolean flag = CommonFunctions.elementVisibleToCheck(eleWaitingForGuide, 20);
		if (flag) {
			CommonFunctions.logMessage("Waiting for the certified guide is displayed");
		} else {
			CommonFunctions
					.logErrorMessageStopExecution("Error, waiting for the certified guide page is not displayed..");
		}
	}

	/**
	 * 
	 * @This method is used to get the sessionId for the lab session from the lab
	 *       url
	 * 
	 * @throws Exception
	 * 
	 */
	public void getLabSessionId() throws Exception {
		String url = getDriver().getCurrentUrl();
		String id = url.split("/")[url.split("/").length - 1];
		if(id.contains("?")) {
			labSessionId.set(CommonFunctions.regexText("(.*)\\?", id).replace("?", "").replaceAll("\\:(\\d)", ""));
		} else {
			labSessionId.set(id.replaceAll("\\:(\\d)", ""));
		}
		
		if (!getLabSessnId().equals("")) {
			CommonFunctions.logMessage("Session Id for the lab test - " + getLabSessnId());
		} else {
			CommonFunctions.logErrorMessageStopExecution("Failed to get the session Id for the lab test.");
		}
	}

}
