package main.java.flows.vlab.proctorlab;

import main.java.base.TestBase;
import main.java.pages.vlab.proctorlab.ProctorLabHomePage;
import main.java.pages.vlab.proctorlab.ProctorLabProcedureFirstSession;
import main.java.pages.vlab.proctorlab.ProctorLabProcedureSecondSession;
import main.java.utils.CommonFunctions;

public class ProctorLabFlow extends TestBase {

	/**
	 * 
	 * @This flow skip the readiness checker for proctor and verifies the proctor
	 *       logged in
	 * 
	 * @throws Exception
	 */
	public static void skipReadinessChecker() throws Exception {
		new ProctorLabHomePage().verifyVLab2ProctorPage();
		new ProctorLabHomePage().clickManualSettings();
		new ProctorLabHomePage().clickFinish();
		new ProctorLabHomePage().verifyProctorLoggedIn();
	}

	/**
	 * 
	 * @This flow moves to first window of proctor and connect with requested user
	 *       for first lab session
	 * 
	 * @throws Exception
	 */
	public static void connectProctorForFirstSession() throws Exception {
		CommonFunctions.moveToSpecifiedWindow(getDriver(), 0);
		new ProctorLabHomePage().connectProctorWithUser(
				CommonFunctions.getdata("FirstName") + " " + CommonFunctions.getdata("LastName"));
	}

	/**
	 * 
	 * @This flow moves to first window of proctor and connect with requested user
	 *       for second lab session
	 * 
	 * @throws Exception
	 */
	public static void connectProctorForSecondSession() throws Exception {
		CommonFunctions.moveToSpecifiedWindow(getDriver(), 0);
		new ProctorLabHomePage().closePreviousChat();
		new ProctorLabHomePage().connectProctorWithUser(
				CommonFunctions.getdata("FirstName") + " " + CommonFunctions.getdata("LastName"));
	}

	/**
	 * 
	 * @This method happy path flow for procedure binax now,express, govt and
	 *       forward lab test for first session
	 * 
	 * @throws Exception
	 */
	public static void startFirstSessionBinax() throws Exception {
		new ProctorLabProcedureFirstSession().verifyTheContinueAsk();
		new ProctorLabProcedureFirstSession().verifyUserDetails(
				CommonFunctions.getdata("FirstName") + " " + CommonFunctions.getdata("LastName"),
				CommonFunctions.getdata("DateofBirth"));
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickIHearAndSee();
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickNextStep();
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickNotExpired();
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickIdReady();
		new ProctorLabProcedureFirstSession().verifyUserForId(CommonFunctions.getdata("FirstName"),
				CommonFunctions.getdata("LastName"), CommonFunctions.getdata("DateofBirth"));
		new ProctorLabProcedureFirstSession().clickIdMatches();
		for (int steps = 0; steps < 13; steps++) {
			new ProctorLabProcedureFirstSession().printAgentWord();
			new ProctorLabProcedureFirstSession().clickNextStep();
		}
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickStartTimer();
		new ProctorLabProcedureFirstSession().verifyFirstSessionEnded();
	}

	/**
	 * 
	 * @This method happy path flow for procedure binax now, express, govt and
	 *       forward lab test for second session
	 * 
	 * @throws Exception
	 */
	public static void startSecondSessionBinax() throws Exception {
		new ProctorLabProcedureSecondSession().verifyTheContinueAsk();
		new ProctorLabProcedureSecondSession().verifyUserDetails(
				CommonFunctions.getdata("FirstName") + " " + CommonFunctions.getdata("LastName"),
				CommonFunctions.getdata("DateofBirth"));
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickIHearAndSee();
		for (int steps = 0; steps < 4; steps++) {
			new ProctorLabProcedureSecondSession().printAgentWord();
			new ProctorLabProcedureSecondSession().clickNextStep();
		}
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickTwoPinkLines();
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickNextStep();
		new ProctorLabProcedureSecondSession().submitTestResult("negative");
		new ProctorLabProcedureSecondSession().confirmTheResults("yes");
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickSubmitResults();
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickFinish();
		new ProctorLabProcedureSecondSession().verifySecondSessionEnded();
	}

	/**
	 * 
	 * @This method is to verify and compete the 1st session of Core-ACON procedure
	 * 
	 * @throws Exception
	 * 
	 */
	public static void startFirstSessionAcon() throws Exception {
		CommonFunctions.logMessage("<=======ACON 1st Session is started=======>");
		new ProctorLabProcedureFirstSession().verifyTheContinueAsk();
		new ProctorLabProcedureFirstSession().verifyUserDetails(
				CommonFunctions.getdata("FirstName") + " " + CommonFunctions.getdata("LastName"),
				CommonFunctions.getdata("DateofBirth"));
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickIHearAndSee();
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickNextStep();
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickYesContinue();
		new ProctorLabProcedureFirstSession().verifyUserForId(CommonFunctions.getdata("FirstName"),
				CommonFunctions.getdata("LastName"), CommonFunctions.getdata("DateofBirth"));
		new ProctorLabProcedureFirstSession().clickIdMatches();
		for (int steps = 0; steps < 17; steps++) {
			new ProctorLabProcedureFirstSession().printAgentWord();
			new ProctorLabProcedureFirstSession().clickNextStep();
		}
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickStartTimer();
		new ProctorLabProcedureFirstSession().verifyFirstSessionEnded();

	}

	/**
	 * 
	 * @This method is to verify and compete the 2nd session of Core-ACON procedure
	 * 
	 * @throws Exception
	 * 
	 */
	public static void startSecondSessionAcon() throws Exception {
		CommonFunctions.logMessage("<=======ACON 2nd Session is started=======>");
		new ProctorLabProcedureSecondSession().verifyTheContinueAsk();
		new ProctorLabProcedureFirstSession().verifyUserDetails(
				CommonFunctions.getdata("FirstName") + " " + CommonFunctions.getdata("LastName"),
				CommonFunctions.getdata("DateofBirth"));
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickIHearAndSee();
		for (int steps = 0; steps < 2; steps++) {
			new ProctorLabProcedureSecondSession().printAgentWord();
			new ProctorLabProcedureSecondSession().clickNextStep();
		}
		new ProctorLabProcedureSecondSession().clickNoTwoPinkLines();
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickSinglePinkLine();
		for (int steps = 0; steps < 2; steps++) {
			new ProctorLabProcedureSecondSession().printAgentWord();
			new ProctorLabProcedureSecondSession().clickNextStep();
		}
		new ProctorLabProcedureSecondSession().submitTestResult("negative");
		new ProctorLabProcedureSecondSession().confirmTheResults("yes");
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickSubmitResults();
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickFinish();
		new ProctorLabProcedureSecondSession().verifySecondSessionEnded();
	}

	/**
	 * @This method is to verify and compete the 1st session of Core-BinaxOTC
	 *       procedure
	 * 
	 * @throws Exception
	 * 
	 */
	public static void startFirstSessionOTC() throws Exception {
		CommonFunctions.logMessage("<=======OTC 1st Session is started=======>");
		new ProctorLabProcedureFirstSession().verifyTheContinueAsk();
		new ProctorLabProcedureFirstSession().verifyUserDetails(
				CommonFunctions.getdata("FirstName") + " " + CommonFunctions.getdata("LastName"),
				CommonFunctions.getdata("DateofBirth"));
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickIHearAndSee();
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickNextStep();
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickYesContinue();
		new ProctorLabProcedureFirstSession().verifyUserForId(CommonFunctions.getdata("FirstName"),
				CommonFunctions.getdata("LastName"), CommonFunctions.getdata("DateofBirth"));
		new ProctorLabProcedureFirstSession().clickIdMatches();
		for (int steps = 0; steps < 13; steps++) {
			new ProctorLabProcedureFirstSession().printAgentWord();
			new ProctorLabProcedureFirstSession().clickNextStep();
		}
		new ProctorLabProcedureFirstSession().printAgentWord();
		new ProctorLabProcedureFirstSession().clickStartTimer();
		new ProctorLabProcedureFirstSession().verifyFirstSessionEnded();
	}

	/**
	 * 
	 * @This method is to verify and compete the 2nd session of Core-BinaxOTC
	 *       procedure
	 * 
	 * @throws Exception
	 * 
	 */
	public static void startSecondSessionOTC() throws Exception {
		CommonFunctions.logMessage("<=======OTC 2nd Session is started=======>");
		new ProctorLabProcedureSecondSession().verifyTheContinueAsk();
		new ProctorLabProcedureFirstSession().verifyUserDetails(
				CommonFunctions.getdata("FirstName") + " " + CommonFunctions.getdata("LastName"),
				CommonFunctions.getdata("DateofBirth"));
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickIHearAndSee();
		for (int steps = 0; steps < 3; steps++) {
			new ProctorLabProcedureSecondSession().printAgentWord();
			new ProctorLabProcedureSecondSession().clickNextStep();
		}
		new ProctorLabProcedureSecondSession().clickNoTwoPinkLines();
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickSinglePinkLine();
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickNextStep();
		new ProctorLabProcedureSecondSession().submitTestResult("negative");
		new ProctorLabProcedureSecondSession().confirmTheResults("yes");
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickSubmitResults();
		new ProctorLabProcedureSecondSession().printAgentWord();
		new ProctorLabProcedureSecondSession().clickFinish();
		new ProctorLabProcedureSecondSession().verifySecondSessionEnded();
	}
}
