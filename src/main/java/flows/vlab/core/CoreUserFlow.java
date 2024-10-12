package main.java.flows.vlab.core;

import org.openqa.selenium.WebDriver;

import main.java.base.TestBase;
import main.java.pages.vlab.core.CreateAccountPage;
import main.java.pages.vlab.core.PreFlightQuestionairePage;
import main.java.pages.www.EMedLoginPage;
import main.java.pages.www.NavicaPage;
import main.java.utils.CommonFunctions;
import main.java.utils.InvokeBrowser;
import main.java.utils.email.EmailVerification;

public class CoreUserFlow extends TestBase {
	
	/**
	 * @This method to open the core applications in new tab of same browser
	 * 
	 * @throws Exception
	 *
	 */

	public static void navigateToCore(WebDriver driver,String platform) throws Exception {
		CommonFunctions.openNewBrowserTab(InvokeBrowser.getBaseUrl(platform), driver);
	}

	/**
	 * @This method calls various methods for functional execution
	 * 
	 * @throws Exception
	 *
	 */

	public static void startCoreQuestionaire() throws Exception {
		verifyQuestionairePage();
		new PreFlightQuestionairePage().fillCoreQuestionaire(CommonFunctions.getdata("CorePreFlightQuestion"),
				CommonFunctions.getdata("AirlineInfo"));
		new PreFlightQuestionairePage().clickSubmit();
	}

	/**
	 * @This method verify Before you begin emed page
	 * 
	 * @throws Exception
	 *
	 */

	public static void verifyQuestionairePage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Before you begin... - eMed");
		CommonFunctions.logMessage("Before you begin... - eMed Page is displayed");
		CommonFunctions.logMessage("<--------------- Questionaire page ------------->");
	}

	/**
	 * 
	 * @This method is used to create an eMed core account
	 * 
	 * @param email
	 * @param password
	 * @throws Exception
	 * 
	 */
	public static void coreAccountCreationFlow(String email, String password) throws Exception {
		new EMedLoginPage().verifyLoginPage();
		new CreateAccountPage().clickCreateAccount();
		new CreateAccountPage().enterCoreNewEmail(email);
		new CreateAccountPage().enterCoreNewPassword(password);
		new CreateAccountPage().clickTermsAndConditions();
		new CreateAccountPage().clickCompleteCreateAccount();
		EmailVerification.getVerifyCodeForEmedSignUp();
		new CreateAccountPage().enterCoreVerifyCode(getVerifyCode());
		new CreateAccountPage().clickConfirmButton();
		new CreateAccountPage().clickCoreSignIn();
		new CreateAccountPage().clickContinue();
		new CreateAccountPage().clickSave();
		new CreateAccountPage().enterCoreFirstName(CommonFunctions.getdata("FirstName"));
		new CreateAccountPage().enterCoreLastName(CommonFunctions.getdata("LastName"));
		new CreateAccountPage().enterCoreDOB(CommonFunctions.getdata("DateofBirth"));
		new CreateAccountPage().enterCoreAddress(CommonFunctions.getdata("Address"));
		new CreateAccountPage().enterCoreCity(CommonFunctions.getdata("City"));
		new CreateAccountPage().selectCoreState(CommonFunctions.getdata("State"));
		new CreateAccountPage().enterCoreZipCode(CommonFunctions.getdata("ZipCode"));
		new CreateAccountPage().enterCorePhoneNumber(CommonFunctions.getdata("PhNumber"));
		new CreateAccountPage().selectCoreGender(CommonFunctions.getdata("Gender"));
		new CreateAccountPage().selectCoreEthnicity(CommonFunctions.getdata("Ethinicity"));
		new CreateAccountPage().selectCoreRace(CommonFunctions.getdata("Race"));
		new CreateAccountPage().clickSave();
		new CreateAccountPage().clickYes();
	}
	
	/**
	 * 
	 * @This flow is to create the core binax now (NAVICA) account
	 * 
	 * @param email
	 * @param password
	 * @throws Exception
	 */
	public static void coreBinaxNowAccountCreationFlow(String email, String password) throws Exception {
		new NavicaPage().verifyNavicaLandingPage();
		new NavicaPage().clickNavicaTermsAndUse();
		CommonFunctions.waitForPageLoad(getDriver());
		new NavicaPage().clickNavicaTermsAndUse();
		new NavicaPage().enterNavicaCreateEmail(email);
		new NavicaPage().clickNavicaSendCode();
		EmailVerification.getVerifyCodeForNavicaSignUp();
		new NavicaPage().enterNavicaVerificationCode(getVerifyCode());
		new NavicaPage().clickNavicaVerifyCode();
		new NavicaPage().enterNavicaCreatePassword(password);
		new NavicaPage().enterNavicaConfirmPassword(password);
		new NavicaPage().clickNavicaSetPassword();
		new NavicaPage().clickEnterManually();
		new NavicaPage().enterNavicaFirstName(CommonFunctions.getdata("FirstName"));
		new NavicaPage().enterNavicaLastName(CommonFunctions.getdata("LastName"));
		new NavicaPage().enterNavicaDOB(CommonFunctions.getdata("DateofBirth"));
		new NavicaPage().enterNavicaPhoneNumber(CommonFunctions.getdata("PhNumber"));
		new NavicaPage().clickNavicaNext();
		new NavicaPage().enterNavicaAddress(CommonFunctions.getdata("Address"));
		new NavicaPage().enterNavicaCity(CommonFunctions.getdata("City"));
		new NavicaPage().selectNavicaState(CommonFunctions.getdata("State"));
		new NavicaPage().enterNavicaZipCode(CommonFunctions.getdata("ZipCode"));
		new NavicaPage().clickNavicaNext();
		new NavicaPage().selectNavicaGender(CommonFunctions.getdata("Gender"));
		new NavicaPage().selectNavicaEthnicity(CommonFunctions.getdata("Ethinicity"));
		new NavicaPage().selectNavicaRace(CommonFunctions.getdata("Race"));
		new NavicaPage().selectNavicaParticipantType(CommonFunctions.getdata("ParticipantType"));
		new NavicaPage().clickNavicaSignupAccount();
	}
	
	/**
	 * 
	 * @This Core Account Login Flow Method
	 * 
	 * @param email
	 * @param password
	 * 
	 * @throws Exception
	 *
	 */

	public static void coreLoginFlow(String email, String password) throws Exception {
		new EMedLoginPage().verifyLoginPage();
		new CreateAccountPage().enterCoreEmail(email);
		new CreateAccountPage().enterCorePassword(password);
		new CreateAccountPage().clickCoreSignIn();
		new CreateAccountPage().verifyConfirmationPage();
	}
	
	/**
	 * 
	 * @This flow is to login into core binax now (NAVICA) account
	 * 
	 * @param email
	 * @param password
	 * 
	 * @throws Exception
	 */
	public static void coreBinaxNowLoginFlow(String email, String password) throws Exception {
		new EMedLoginPage().verifyLoginPage();
		new NavicaPage().enterNavicaEmail(email);
		new NavicaPage().enterNavicaPassword(password);
		new NavicaPage().clickNavicaSignIn();
		new NavicaPage().checkNavicaPermission();
	}
	
	/**
	 * @This method confirms the core login
	 * 
	 * @throws Exception
	 *
	 */

	public static void confirmLoginFlow() throws Exception {
		new CreateAccountPage().clickContinue();
		new CreateAccountPage().clickSave();
		new CreateAccountPage().clickYes();
	}


}
