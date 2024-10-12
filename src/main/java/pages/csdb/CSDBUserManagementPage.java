package main.java.pages.csdb;

import java.util.List;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;
import main.java.utils.DefectList;

public class CSDBUserManagementPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[@id='header']//*[contains(text(),'User Management')]")
	private WebElement eleManagementHeader;

	@FindBy(xpath = "//*[contains(text(),'User Management')]")
	private WebElement eleUserManagement;

	@FindBy(xpath = "//div[contains(text(),'Consultant')]")
	private WebElement eleConsultantTab;

	@FindBy(xpath = "//div[contains(text(),'Vendor QA')]")
	private WebElement eleVendorQATab;

	@FindBy(xpath = "//div[contains(text(),'Agent')]")
	private WebElement eleAgentTab;

	@FindBy(xpath = "//div[contains(text(),'Vendor Admin')]")
	private WebElement eleVendorAdminTab;

	@FindBy(xpath = "//div[contains(text(),'Internal Admin')]")
	private WebElement eleInternalAdminTab;
	
	@FindBy(xpath = "//div[contains(text(),'Voucher Manager')]")
	private WebElement eleVoucherManagerTab;
	
	@FindBy(xpath = "//div[contains(text(),'Lab Admin')]")
	private WebElement eleLabAdminTab;

	@FindBy(xpath = "//div[contains(text(),'IT Admin')]")
	private WebElement eleITAdminTab;

	@FindBy(xpath = "//input[@placeholder='Search by Name']")
	private WebElement eleManagementSearch;

	@FindBy(xpath = "//tbody//tr//td")
	private List<WebElement> eleUserSearchResults;

	@FindBy(xpath = "//button[contains(text(),'Add a user')]")
	private WebElement eleStartAddUser;	

	@FindBy(xpath = "//button[contains(text(),'Add User')]")
	private WebElement eleAddUserButton;	

	@FindBy(xpath = "//input[contains(@value,'Agent')]")
	private WebElement eleAgentCheckBox;
	
	@FindBy(xpath = "//input['checked' and @value='Consultant']")
	private WebElement eleUnCheckConsultant;
	
	@FindBy(xpath = "//input[@value='Vendor-QA']")
	private WebElement eleVendorQACheckBox;
	
	@FindBy(xpath = "//input[@value='Vendor-Admin']")
	private WebElement eleVendorAdminCheckBox;
	
	@FindBy(xpath = "//input[@value='Internal-Admin']")
	private WebElement eleInternalAdminCheckBox;
	
	@FindBy(xpath = "//input[contains(@value,'Lab-Admin')]")
	private WebElement eleLabAdminCheckBox;
	
	@FindBy(xpath = "//input[contains(@value,'IT-Admin')]")
	private WebElement eleITAdminCheckBox;	
	
	@FindBy(xpath = "//input[@id='email']")
	private WebElement eleEnterEmail;
	
	@FindBy(id = "firstName")
	private WebElement elefirstName;
	
	@FindBy(id = "lastName")
	private WebElement elelastName;
	
	@FindBy(xpath = "//*[contains(text(),'given email already exists')]")
	private WebElement eleErrMsg;

	public CSDBUserManagementPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * @This method is to click UserManagement tab
	 * 
	 * @throws Exception
	 */
	public void clickUserManagement() throws Exception {
		CommonFunctions.actionClick(eleUserManagement, "csdb user management tab");
	}

	/**
	 * @This method is to verify entered user management profile with the filtered
	 *       result
	 * 
	 * @throws Exception
	 */
	public void verifyManagementProfile(String username) throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		String managementProfile = CommonFunctions.getTextOfElement(eleUserSearchResults.get(2),
				"first user management profile");
		if (managementProfile.contains(username)) {
			CommonFunctions.logMessage("Entered user management profile - " + username
					+ " matched with the filtered result profile - " + managementProfile);
		} else {
			CommonFunctions.logErrorMessage("Entered user management profile - " + username
					+ " failed to matched with the filtered result profile - " + managementProfile);
		}

	}

	/**
	 * @This method is to click Consultant tab
	 * 
	 * @throws Exception
	 */
	public void clickConsultTab() throws Exception {
		CommonFunctions.clickWebelement(eleConsultantTab, "consultant tab");
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * @This method is to search management profile
	 * 
	 * @param username
	 * 
	 * @param userType
	 * 
	 * @throws Exception
	 */
	public void searchManagementProfile(String username, String userType) throws Exception {
		CommonFunctions.sendKeysIndividual(eleManagementSearch, username, userType);
	}

	/**
	 * @This method is to click Vendor-QA tab
	 * 
	 * @throws Exception
	 */
	public void clickVendorQaTab() throws Exception {
		CommonFunctions.clickWebelement(eleVendorQATab, "VendorQA tab");
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * @This method is to click Agent tab
	 *
	 * @throws Exception
	 */
	public void clickAgentTab() throws Exception {
		CommonFunctions.clickWebelement(eleAgentTab, "Agent tab");
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * @This method is to click Vendor-Admin tab
	 * 
	 * @throws Exception
	 */
	public void clickVendorAdminTab() throws Exception {
		CommonFunctions.clickWebelement(eleVendorAdminTab, "VendorAdmint tab");
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * @This method is to click Internal-Admin tab
	 * 
	 * @throws Exception
	 */
	public void clickInternalAdminTab() throws Exception {
		CommonFunctions.clickWebelement(eleInternalAdminTab, "InternalAdmin tab");
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * @This method is to click Lab-Admin tab
	 * 
	 * @throws Exception
	 */
	public void clickLabAdminTab() throws Exception {
		CommonFunctions.clickWebelement(eleLabAdminTab, "LabAdmin tab");
		CommonFunctions.waitForPageLoad(getDriver());
	}

	/**
	 * @This method is to click on IT-Admin tab
	 * 
	 * @throws Exception
	 */
	public void clickITAdminTab() throws Exception {
		CommonFunctions.clickWebelement(eleITAdminTab, "IT-Admin tab");
		CommonFunctions.waitForPageLoad(getDriver());
	}
	
	/**
	 * @This method is to click on Voucher-Manager tab
	 * 
	 * @throws Exception
	 */
	public void clickVoucherManagerTab() throws Exception {
		CommonFunctions.clickWebelement(eleVoucherManagerTab, "Voucher-Manager tab");
		CommonFunctions.waitForPageLoad(getDriver());
	}
	
	/**
	 * This Method to click Add a user
	 * 
	 *
	 * @throws Exception
	 */

	public void clickStartAddUser() throws Exception {
		CommonFunctions.clickWebelement(eleStartAddUser, "Start Add a user");
	}

	/**
	 * This Method to click Add user button
	 * 
	 *
	 * @throws Exception
	 */

	public void clickAddUser() throws Exception {
		CommonFunctions.scrollIntoView(eleAddUserButton);
		CommonFunctions.clickWebelement(eleAddUserButton, "Add user button");
	}
	
	/**
	 * This Method to UnCheck Consultant
	 * 
	 *
	 * @throws Exception
	 */

	public void unCheckConsultant() throws Exception {
		CommonFunctions.clickWebelement(eleUnCheckConsultant, "UnChecked the Consultant!");
	}
	
	/**
	 * This Method to click Agent check box
	 * 
	 *
	 * @throws Exception
	 */

	public void clickAgentCheckBox() throws Exception {
		CommonFunctions.scrollIntoView(eleAgentCheckBox);
		CommonFunctions.clickWebelement(eleAgentCheckBox, "Agent check box");
	}
	
	/**
	 * This method used to enter new user details from CSDB
	 * 
	 * @throws Exception
	 */

	public void enterNewUserDetails(String fname, String lname, String email, String userType) throws Exception {
		CommonFunctions.sendKeysWithDeleteAll(elefirstName, fname, "First Name");
		CommonFunctions.sendKeysWithDeleteAll(elelastName, lname, "Last Name");

		switch (userType) {
		
		case "csdbconsultantusername":
			CommonFunctions.Sendkeys(eleEnterEmail, email, "email");
		break;
		
		case "csdbvendorqausername":
			CommonFunctions.Sendkeys(eleEnterEmail, email, "email");
			CommonFunctions.scrollIntoView(eleInternalAdminCheckBox);
			CommonFunctions.clickWebelement(eleVendorQACheckBox, "UnCheckConsultant");
			CommonFunctions.clickJSEString("//input['checked' and @value='Consultant']", "UnCheck Consultant");	
		break;
		
		case "csdbagentusername":
			CommonFunctions.Sendkeys(eleEnterEmail, email, "email");
			CommonFunctions.scrollIntoView(eleInternalAdminCheckBox);
			CommonFunctions.clickWebelement(eleAgentCheckBox, "Agent check box");
			CommonFunctions.clickJSEString("//input['checked' and @value='Consultant']", "UnCheck Consultant");	
		break;
		
		case "csdbvendoradminusername":
			CommonFunctions.Sendkeys(eleEnterEmail, email, "email");
			CommonFunctions.scrollIntoView(eleInternalAdminCheckBox);
			CommonFunctions.clickWebelement(eleVendorAdminCheckBox, "VendorAdminCheckBox");
			CommonFunctions.clickJSEString("//input['checked' and @value='Consultant']", "UnCheck Consultant");	
		break;
	
		case "csdbinternaladminusername":
			CommonFunctions.Sendkeys(eleEnterEmail, email, "email");
			CommonFunctions.scrollIntoView(eleInternalAdminCheckBox);
			CommonFunctions.clickWebelement(eleInternalAdminCheckBox, "InternalAdminCheckBox");
			CommonFunctions.clickJSEString("//input['checked' and @value='Consultant']", "UnCheck Consultant");	
		break;
		
		case "csdblabadminusername":
			CommonFunctions.Sendkeys(eleEnterEmail, email, "email");
			CommonFunctions.scrollIntoView(eleInternalAdminCheckBox);
			CommonFunctions.clickWebelement(eleLabAdminCheckBox, "LabAdminCheckBox");
			CommonFunctions.clickJSEString("//input['checked' and @value='Consultant']", "UnCheck Consultant");			
		break;
		
		case "csdbitadminusername":
			CommonFunctions.Sendkeys(eleEnterEmail, email, "email");
			CommonFunctions.scrollIntoView(eleInternalAdminCheckBox);
			CommonFunctions.clickWebelement(eleITAdminCheckBox, "ITAdminCheckBox");
			CommonFunctions.clickJSEString("//input['checked' and @value='Consultant']", "UnCheck Consultant");		
		break;

		}
		new CSDBUserManagementPage().clickAddUser();
	}
	
	/**
	 * This method used to change the password from CSDB
	 * 
	 * @throws Exception
	 */

	public void verifyingErrorMsg() throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		boolean flag = CommonFunctions.isExist(getDriver(), "//*[contains(text(),'given email already exists')]");
		String erroMsg = CommonFunctions.getTextOfElement(eleErrMsg, "Error msg");
		if (flag) {
			CommonFunctions.logMessage("Error message correctly displaying - "+ erroMsg);
		} else {
			CommonFunctions.logErrorMessage(erroMsg + DefectList.getP2Defect("CSS-190"));
		}
	}
	
	/**
	 * This method used to verify the Success PopUp for newly created user from CSDB
	 * 
	 * @throws Exception
	 */

	public void verifyingNewUserSuccessPopUp() throws Exception {
		
		CommonFunctions.waitForPageLoad(getDriver());
		boolean flag = CommonFunctions.isExist(getDriver(), "//*[contains(text(),'User added successfully')]");
		if (flag) {
			CommonFunctions.logMessage("User added successfully !!");
		} else {
			CommonFunctions.logErrorMessage("NO uer found! - "+DefectList.getP2Defect( "CSS-233")); 
		}
	}
	
	/**
	 * This method used to verify the newly created user from CSDB
	 * 
	 * @throws Exception
	 */

	public void verifyingNewUser(String userType) throws Exception {
		CommonFunctions.waitForPageLoad(getDriver());
		if (userType.equals("csdbconsultantusername")) {
			clickConsultTab();
		} else if (userType.equals("csdbvendorqausername")) {
			clickVendorQaTab();
		} else if (userType.equals("csdbagentusername")) {
			clickAgentTab();
		} else if (userType.equals("csdbvendoradminusername")) {
			clickVendorAdminTab();
		} else if (userType.equals("csdbinternaladminusername")) {
			clickInternalAdminTab();
		} else if (userType.equals("csdblabadminusername")) {
			clickLabAdminTab();
		} else if (userType.equals("csdbitadminusername")) {
			clickITAdminTab();
		}
		searchManagementProfile(getEmailId(), userType);
		verifyManagementProfile(getEmailId());
	}
}
