package main.java.flows.vlab.vlab2;

import main.java.base.TestBase;
import main.java.pages.vlab.vlab2.BinaxExpressPage;
import main.java.pages.www.SignUpSetUpPage;
import main.java.utils.CommonFunctions;

public class VLab2Flow extends TestBase{
	
	/**
	 * 
	 * @This flow is to perform login for eMed binax express
	 * 
	 * @throws Exception
	 * 
	 */
	public static void binaxLoginFlow() throws Exception {
		new BinaxExpressPage().verifyLandingPage();
		new BinaxExpressPage().enterExpressPasscode("express1");
		new BinaxExpressPage().clickExpressSubmit();
		new BinaxExpressPage().selectBinaxEnvironment();
		new BinaxExpressPage().eMedConfirmShareResults();
	}
	
	/**
	 * 
	 * @This method is to fill other details required for binax session and submits
	 * 
	 * @throws Exception
	 */
	public static void fillOtherDetailsGetBinaxSession() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Start Testing - eMed");
		new SignUpSetUpPage().selectGender(CommonFunctions.getdata("Gender"));
		new SignUpSetUpPage().selectRaces(CommonFunctions.getdata("Race"));
		new SignUpSetUpPage().selectEthnicity(CommonFunctions.getdata("Ethinicity"));
		new BinaxExpressPage().clickExpressSubmitForOtherDetails();
	}

}
