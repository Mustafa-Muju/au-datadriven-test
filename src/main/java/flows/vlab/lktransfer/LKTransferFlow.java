package main.java.flows.vlab.lktransfer;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.pages.vlab.lktransfer.LKAccountReportPage;
import main.java.pages.vlab.lktransfer.LKLoginPage;
import main.java.pages.vlab.lktransfer.LKTransactionPage;
import main.java.pages.vlab.lktransfer.LKTransferActivitiesPage;
import main.java.utils.CommonFunctions;

public class LKTransferFlow extends TestBase {

	/**
	 * 
	 * @This method is used to perform the login flow for the LK Transfer
	 * 
	 * @throws Exception
	 */
	public static void lKUserLogin() throws Exception {
		new LKLoginPage().verifyLKLoginPage();
		new LKLoginPage().enterLKUsername(new EncryptCredentails()
				.decrypt(CommonFunctions.getPropertyValues().getProperty("lktransferusername")));
		new LKLoginPage().enterLKPassword(new EncryptCredentails()
				.decrypt(CommonFunctions.getPropertyValues().getProperty("lktransferpassword")));
		new LKLoginPage().clickLKSignIn();
	}

	/**
	 * 
	 * @This flow is used to get the account
	 * 
	 * @param accountName
	 * @param header
	 * @throws Exception
	 */
	public static void getAccount(String accountName, String header) throws Exception {
		new LKTransferActivitiesPage().verifyLKTransferActivitiesPage();
		new LKTransferActivitiesPage().enterAccountName(accountName);
		new LKTransferActivitiesPage().clickSearchBtn();
		new LKTransferActivitiesPage().selectAccount(header, accountName);
	}
	
	/**
	 * 
	 * @This flow is used to get the result and verify it
	 * 
	 * @param accountName
	 * @param header
	 * @throws Exception
	 */
	public static void getEllkyResults(String result) throws Exception {
		new LKAccountReportPage().verifyLKTransferAdvancedSearchPage();
		new LKAccountReportPage().selectAccountResult(result);
		new LKTransactionPage().verifyLKTransferTransactionPage();
		new LKTransactionPage().verifyEllkyInfo();
	}
}
