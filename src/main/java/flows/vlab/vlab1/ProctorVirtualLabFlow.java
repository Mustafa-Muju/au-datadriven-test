package main.java.flows.vlab.vlab1;

import org.openqa.selenium.By;

import main.java.base.TestBase;
import main.java.pages.vlab.vlab1.ProctorLandingPage;
import main.java.pages.vlab.vlab1.ProctorVirtualRoomPage;
import main.java.utils.CommonFunctions;

public class ProctorVirtualLabFlow extends TestBase {

	/**
	 * 
	 * This method to click proctor visit room
	 * 
	 * @throws Exception
	 *
	 */

	public static void proctorVisitRoom() throws Exception {
		ProctorFlow.ProctorLoginProcess();
		new ProctorLandingPage().clickVirtualWaitingRoom();
	}

	/**
	 * This method to click the proctor Virtual Accept
	 * 
	 * @throws Exception
	 *
	 */

	public static void proctorVirtualAccept() throws Exception {
		CommonFunctions.moveToSpecifiedWindow(getDriver(), 0);
		new ProctorVirtualRoomPage().proctorVirtualAccept();
		CommonFunctions.switchNextWindow(getDriver());
		getDriver().manage().window().maximize();
		new ProctorVirtualRoomPage().clickMuteButton();
	}

	/**
	 * This method to start the Proctor test process
	 * 
	 * 
	 * @throws Exception
	 *
	 */

	public static void startProctorTestProcess() throws Exception {
		new ProctorVirtualRoomPage().verifySessionStarted(CommonFunctions.getdata("FirstName"),
				CommonFunctions.getdata("LastName"));
		boolean flag = true;
		int stepCount = 1;
		while (flag) {
			new ProctorVirtualRoomPage().clickNextStepButton();
			CommonFunctions.logMessage("Step " + (stepCount++) + " is completed successfully");
			if (getDriver().findElements(By.xpath("//*[text()='Next']//ancestor::button[@disabled]")).size() > 0) {
				new ProctorVirtualRoomPage().selectTestStatus(CommonFunctions.getdata("ResultStatus"));
				new ProctorVirtualRoomPage().clickSubmitButton();
				new ProctorVirtualRoomPage().clickEndCallButton();
				new ProctorVirtualRoomPage().verifySessionEnded();
				getDriver().close();
				CommonFunctions.moveToSpecifiedWindow(getDriver(), 1);
				getDriver().close();
				CommonFunctions.moveToSpecifiedWindow(getDriver(), 0);
				flag = false;
			}
		}

	}

	/**
	 * Method used to remove patient from queue and connect with certified patients
	 * 
	 * @return
	 * @throws Exception
	 * 
	 * 
	 */
	public static boolean verifyCertifiedPatient(String fName, String lName) throws Exception {

		boolean startFlag = true;

		startFlag = CommonFunctions.isExist(getDriver(), "//*[text()='" + fName + " " + lName + "']");
		if (startFlag) {
			CommonFunctions.logMessage("Virtual lab session has started with official patient");
		} else {
			getDriver().close();
			CommonFunctions.moveToSpecifiedWindow(getDriver(), 0);
		}
		return startFlag;

	}

}
