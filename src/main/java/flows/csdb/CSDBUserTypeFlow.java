package main.java.flows.csdb;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.pages.csdb.CSDBMyAccountPage;
import main.java.pages.csdb.CSDBUserManagementPage;
import main.java.utils.CommonFunctions;

public class CSDBUserTypeFlow extends TestBase {

	/**
	 * @This method is to verify User Management header
	 * 
	 * @throws Exception
	 * 
	 */
	public static void verifyManagementPage() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		new CSDBUserManagementPage().clickUserManagement();
		Boolean flag = CommonFunctions.isExist(getDriver(), "//*[@id='header']//*[contains(text(),'User Management')]");
		if (flag) {
			CommonFunctions.logMessage("<-----CSDB User Management Page----->");
		} else {
			CommonFunctions.logErrorMessageStopExecution("User Management header failed to display");
		}
	}

	/**
	 * @This method is to verify Consultant Management profile
	 * 
	 * @param emailID
	 * 
	 * @throws Exception
	 */
	public static void verifyConsultantProfile(String emailID) throws Exception {
		new CSDBUserManagementPage().clickConsultTab();
		String username = new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(emailID));
		new CSDBUserManagementPage().searchManagementProfile(username, "consultant search");
		new CSDBUserManagementPage().verifyManagementProfile(username);
	}

	/**
	 * @This method is to verify Vendor-QA management profile
	 * 
	 * @param emailID
	 * 
	 * @throws Exception
	 */
	public static void verifyVendorQAProfile(String emailID) throws Exception {
		new CSDBUserManagementPage().clickVendorQaTab();
		String username = new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(emailID));
		new CSDBUserManagementPage().searchManagementProfile(username, "vendor-qa search");
		new CSDBUserManagementPage().verifyManagementProfile(username);
	}

	/**
	 * @This method is to verify Agent management profile
	 * 
	 * @param emailID
	 * 
	 * @throws Exception
	 */
	public static void verifyAgentProfile(String emailID) throws Exception {
		new CSDBUserManagementPage().clickAgentTab();
		String username = new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(emailID));
		new CSDBUserManagementPage().searchManagementProfile(username, "agent search");
		new CSDBUserManagementPage().verifyManagementProfile(username);
	}

	/**
	 * @This method is to verify Vendor-Admin management profile
	 * 
	 * @param emailID
	 * 
	 * @throws Exception
	 */
	public static void verifyVendorAdminProfile(String emailID) throws Exception {
		new CSDBUserManagementPage().clickVendorAdminTab();
		String username = new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(emailID));
		new CSDBUserManagementPage().searchManagementProfile(username, "vendor admin search");
		new CSDBUserManagementPage().verifyManagementProfile(username);
	}

	/**
	 * @This method is to verify Internal-Admin management profile
	 * 
	 * @param emailID
	 * 
	 * @throws Exception
	 */
	public static void verifyInternalAdminProfile(String emailID) throws Exception {
		new CSDBUserManagementPage().clickInternalAdminTab();
		String username = new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(emailID));
		new CSDBUserManagementPage().searchManagementProfile(username, "internal admin");
		new CSDBUserManagementPage().verifyManagementProfile(username);
	}

	/**
	 * @This method is to verify Lab-Admin management profile
	 * 
	 * @param emailID
	 * 
	 * @throws Exception
	 */
	public static void verifyLabAdminProfile(String emailID) throws Exception {
		new CSDBUserManagementPage().clickLabAdminTab();
		String username = new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(emailID));
		new CSDBUserManagementPage().searchManagementProfile(username, "lab admin search");
		new CSDBUserManagementPage().verifyManagementProfile(username);
	}

	/**
	 * @This method is to verify IT-Admin management profile
	 * 
	 * @param emailID
	 * 
	 * @throws Exception
	 */
	public static void verifyITAdminProfile(String emailID) throws Exception {
		new CSDBUserManagementPage().clickITAdminTab();
		String username = new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(emailID));
		new CSDBUserManagementPage().searchManagementProfile(username, "IT admin search");
		new CSDBUserManagementPage().verifyManagementProfile(username);
	}

	/**
	 * @This method is to verify Voucher-Manager management profile
	 * 
	 * @param emailID
	 * 
	 * @throws Exception
	 */
	public static void verifyVoucherManagerProfile(String emailID) throws Exception {
		new CSDBUserManagementPage().clickVoucherManagerTab();
		String username = new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(emailID));
		new CSDBUserManagementPage().searchManagementProfile(username, "Voucher-Manager search");
		new CSDBUserManagementPage().verifyManagementProfile(username);
	}

	/**
	 * 
	 * @This method is for change password in CSDB from the my account page
	 * 
	 * @throws Exception
	 */
	public static void userTypeMyAccountChangePassword() throws Exception {
		new CSDBMyAccountPage().clickMyAccount();
		new CSDBMyAccountPage().clickEdit();
		new CSDBMyAccountPage().changePasswordInCSDBMyAccount();
	}

	/**
	 * 
	 * @This method is for validating the existing user in CSDB
	 * 
	 * @throws Exception
	 */
	public static void userTypeErrorValidatingForExistingUser() throws Exception {		
		String fName = CommonFunctions.getdata("FirstName");
		String lName = CommonFunctions.getdata("LastName");
		String[] roles = {"csdbconsultantusername", "csdbvendorqausername", "csdbagentusername", "csdbvendoradminusername",  "csdbinternaladminusername", "csdblabadminusername", "csdbitadminusername"};
		for(int i=0;i<roles.length;i++) {
			new CSDBUserManagementPage().clickStartAddUser();
			CommonFunctions.waitForPageLoad(getDriver());
			emailId.set(new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty(roles[i])));
			new CSDBUserManagementPage().enterNewUserDetails(fName, lName, getEmailId(), roles[i]);
			new CSDBUserManagementPage().verifyingErrorMsg();
			emailId.set("");
		}
	}
	
}
