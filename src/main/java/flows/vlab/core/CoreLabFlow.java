package main.java.flows.vlab.core;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.pages.vlab.core.LabProcedureInitPage;
import main.java.pages.vlab.core.UserLabProcedureSecondSession;
import main.java.utils.CommonFunctions;

public class CoreLabFlow extends TestBase{
	
	/**
	 * 
	 * @This flow is to pass the readiness checker and wait for certified guide
	 * 
	 * @throws Exception
	 */
	public static void startLabTest() throws Exception {
		new LabProcedureInitPage().verifyCoreLabReadinessCheck("Quidel QuickVue Covid-19 Home Test - eMed");
		new LabProcedureInitPage().clickSkip();
		new LabProcedureInitPage().clickStartLabTest();
		new LabProcedureInitPage().chooseTheAgent(new EncryptCredentails()
				.decrypt(CommonFunctions.getPropertyValues().getProperty("labproctorusername")));
		new LabProcedureInitPage().verifyWaitingForGuide();
		new LabProcedureInitPage().getLabSessionId();
	}
	
	/**
	 * 
	 * @This method is used to make ready of user for lab test of second session
	 * 
	 * @throws Exception
	 */
	public static void startSecondLabTest() throws Exception {
		CommonFunctions.moveToSpecifiedWindow(getDriver(), 1);
		new UserLabProcedureSecondSession().verifyTimerFinished();
		new UserLabProcedureSecondSession().clickImReady();
		new LabProcedureInitPage().chooseTheAgent(new EncryptCredentails()
				.decrypt(CommonFunctions.getPropertyValues().getProperty("labproctorusername")));
		new LabProcedureInitPage().verifyWaitingForGuide();
	}

}
