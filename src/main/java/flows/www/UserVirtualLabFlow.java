package main.java.flows.www;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.flows.vlab.vlab1.ProctorVirtualLabFlow;
import main.java.pages.vlab.vlab1.ProctorLandingPage;
import main.java.pages.vlab.vlab1.ProctorVirtualRoomPage;
import main.java.pages.www.LandingPage;
import main.java.pages.www.VLabQuestionairePage;
import main.java.utils.CommonFunctions;

public class UserVirtualLabFlow extends TestBase {

	private JavascriptExecutor jse = null;

	public UserVirtualLabFlow() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * @Method to verify Scan QR Sticker page and enter product id number flow
	 * 
	 * @throws Exception
	 */
	public static void verifyScanQRSticker() throws Exception {

		boolean flag = new VLabQuestionairePage().verifyScanQRStickerHeader();
		if (flag) {
			new VLabQuestionairePage().verifyScanQRStickerVideoFrame();
			new VLabQuestionairePage().clickEnterProductId();
			new VLabQuestionairePage().verifyProductIdModule();
			new VLabQuestionairePage().enterFirstDigitProductId("1");
			new VLabQuestionairePage().clickNextProductDigit();
			new VLabQuestionairePage().enterSecondDigitProductId("2");
			new VLabQuestionairePage().clickNextProductDigit();
			new VLabQuestionairePage().enterthirdDigitProductId("139811");
			new VLabQuestionairePage().clickNextProductDigit();
		}
	}

	/**
	 * 
	 * Attend the VLab questionnaire to proceed for guide session
	 * 
	 * @throws Exception
	 *
	 */

	public static void verifyUserAttendSessionAssessment() throws Exception {

		new VLabQuestionairePage().verifyTestKitQuestionaireHeader();
		if (CommonFunctions.getdata("Dependent").equalsIgnoreCase("Y")) {
			new VLabQuestionairePage().selectDependentPatient();
		}
		new VLabQuestionairePage().virtualLabTestQuestion(CommonFunctions.getdata("VLabQuestion"),
				CommonFunctions.getdata("AirlineInfo"));
		new VLabQuestionairePage().clickPreTestTermsAndPrivacy();
		new VLabQuestionairePage().clickJoinGuideSession();

	}

	/**
	 * Method is used to take a virtual session with authorized patient
	 * 
	 * @throws Exception
	 */
	public static void connectAuthorizedPatientLabSession(int tries) throws Exception {

		boolean stopFlag = true;
		int count = 0;

		for (int proctorTry = 0; proctorTry <= tries; proctorTry++) {

			CommonFunctions.moveToSpecifiedWindow(getDriver(), 0);
			stopFlag = new ProctorVirtualRoomPage().removePatientInQueueAndAcceptCertifiedPatient();

			if (proctorTry != 0 && !stopFlag) {
				CommonFunctions.switchNextWindow(getDriver());
				getDriver().close();
				CommonFunctions.moveToSpecifiedWindow(getDriver(), 0);
			}

			if (proctorTry < tries) {

				if (!stopFlag) {

					new LandingPage().invokeWWWNewWindow();

					if (count == 0) {
						RegistrationFlow.navicaLogin(CommonFunctions.getdata("UserName"),
								CommonFunctions.getdata("Password"));
						count++;
					} else {
						new LandingPage().verifyLandingPage();
						new LandingPage().clickStartTesting();
					}

					UserVirtualLabFlow.verifyUserAttendSessionAssessment();

				} else {
					CommonFunctions.logMessage("Connected with the authorized patient");
					ProctorVirtualLabFlow.startProctorTestProcess();
					new ProctorLandingPage().clickProctorSignout();
					break;
				}
			}
		}

		if (!stopFlag) {
			CommonFunctions.logErrorMessageStopExecution(
					"Tired " + tries + " times for virtual lab session but failed to get an patient in queue");
		}
	}

}
