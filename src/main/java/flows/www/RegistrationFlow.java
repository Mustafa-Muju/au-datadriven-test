package main.java.flows.www;

import awdsdk.AuthenticationHelper;
import main.java.base.TestBase;
import main.java.pages.www.EMedLoginPage;
import main.java.pages.www.LandingPage;
import main.java.pages.www.NavicaPage;
import main.java.pages.www.SignUpSetUpPage;
import main.java.utils.CommonFunctions;
import main.java.utils.awssdk.AWSResteMedAccountCreation;
import main.java.utils.email.EmailVerification;

public class RegistrationFlow extends TestBase {

	/**
	 * 
	 * @This method to get till get-test
	 * 
	 * @throws Exception
	 * 
	 */
	public static void getTestFlow() throws Exception {
		new LandingPage().verifyLandingPage();
		new LandingPage().clickLoginTab();
		new EMedLoginPage().verifyLoginPage();
		new EMedLoginPage().clickGetTest();
	}

	/**
	 * 
	 * @This method to get till start-test
	 * 
	 * @throws Exception
	 * 
	 */
	public static void startNavicaTestFlow() throws Exception {
		new LandingPage().verifyLandingPage();
		new LandingPage().clickLoginTab();
		new EMedLoginPage().verifyLoginPage();
		new EMedLoginPage().clickStartTest();
	}

	/**
	 * eMedAccountSignUpFlow Method
	 * 
	 * @param email
	 * @param password
	 * 
	 * @throws Exception
	 *
	 */

	public static void eMedAccountSignUpFlow(String email, String password) throws Exception {

		new EMedLoginPage().verifyLoginPage();
		new EMedLoginPage().clickSignupButton();
		new EMedLoginPage().verifySignupTab();
		new EMedLoginPage().enterLoginEmail(email);
		new EMedLoginPage().enterLoginPassword(password);
		new EMedLoginPage().clickTermsAndConditions();
		new EMedLoginPage().clickCreateAccount();
		new EMedLoginPage().verifySignUpProcess();
		EmailVerification.getVerifyCodeForEmedSignUp();
		new EMedLoginPage().verificationCode(getVerifyCode());
		new EMedLoginPage().clickVerifyEmail();
		new EMedLoginPage().verifySuccessfulSignup();
	}

	/**
	 * This method create an eMed user account through rest api's
	 * 
	 * @param email
	 * @param password
	 * @throws Exception
	 */
	public static void eMedAPIUserCreation(String email, String password) throws Exception {
		String clientId = "";
		String userpoolId = "";

		switch (env.toLowerCase()) {
		case "stg":
			clientId = "6tlm7djcd6vs4rb4c5p6amb95l";
			userpoolId = "us-east-1_PWc8wtsbO";
			break;

		case "dev2":
		case "qa":
			clientId = "48b73p39p2lmv9blfs19vhbjv1";
			userpoolId = "us-east-1_qqiFTw9zB";
			break;

		case "prod":
			clientId = "65cguhdlh4q5mimmgufvg1d0d8";
			userpoolId = "us-east-1_CqSQ6eF5R";
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("Failed due to account not created in " + env + " env");
			break;

		}
		CommonFunctions.logMessage("Creating " + env + " eMed User account through rest api for " + email);
		AWSResteMedAccountCreation.registereMedNewUser(clientId, email, password);
		EmailVerification.getVerifyCodeForEmedSignUp();
		AWSResteMedAccountCreation.verifyeMedUser(clientId, email, getVerifyCode());
		String token = "";
		try {
			AuthenticationHelper aws = new AuthenticationHelper(userpoolId, clientId);
			token = aws.generateBearerToken(userpoolId, clientId, email, password);
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution(
					"Error while creating login authentication token. Pls check the inputs");
		}
		AWSResteMedAccountCreation.updateProfile(env.toLowerCase(), email, token);
		CommonFunctions.logMessage("Account created successfully through rest api's ====> " + email);
	}

	/**
	 * eMedLogin Flow Method
	 * 
	 * @param email
	 * @param password
	 * 
	 * @throws Exception
	 *
	 */

	public static void eMedGetTestloginFlow(String email, String password) throws Exception {
		new EMedLoginPage().verifyLoginPage();
		new EMedLoginPage().enterLoginEmail(email);
		new EMedLoginPage().enterLoginPassword(password);
		new EMedLoginPage().clickLoginButton();
	}

	/**
	 * NavicaAccountSignup Flow Method
	 * 
	 * @param email
	 * @param password
	 * 
	 * @throws Exception
	 *
	 */

	public static void navicaAccountSignupFlow(String email, String password) throws Exception {
		new EMedLoginPage().verifyLoginPage();
		new NavicaPage().clickNavicaCreateAccount();
		new NavicaPage().verifyNavicaLandingPage();
		new NavicaPage().clickNavicaTermsAndUse();
		CommonFunctions.waitForPageLoad(getDriver());
		new NavicaPage().clickNavicaTermsAndUse();
		new NavicaPage().enterNavicaCreateEmail(email);
		new NavicaPage().clickNavicaSendCode();
		EmailVerification.getVerifyCodeForNavicaSignUp();
		new NavicaPage().enterNavicaVerificationCode(getVerifyCode());
		new NavicaPage().clickNavicaVerifyCode();
		// new NavicaPage().clickVerifyEmailContinue();
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
	 * NavicaAccount Login Flow Method
	 * 
	 * @param navicaEmail
	 * @param navicapassword
	 * 
	 * @throws Exception
	 *
	 */

	public static void navicaLogin(String navicaEmail, String navicapassword) throws Exception {
		new EMedLoginPage().verifyLoginPage();
		new NavicaPage().clickNavicaLogin();
		new NavicaPage().enterNavicaEmail(navicaEmail);
		new NavicaPage().enterNavicaPassword(navicapassword);
		new NavicaPage().clickNavicaSignIn();
		CommonFunctions.waitForPageLoad(getDriver());
		new NavicaPage().checkNavicaPermission();
	}

	/**
	 * Navica personal setup account method
	 * 
	 * @param fname
	 * @param lname
	 * @param dob
	 * @param gender
	 * @param pronoun
	 * @param race
	 * @param ethinicity
	 * 
	 * @throws Exception
	 *
	 */

	public static void signupAccountSetupPersonal(String fname, String lname, String dob, String gender, String pronoun,
			String race, String ethinicity) throws Exception {
		new SignUpSetUpPage().verifySetUpAccountPage();
		new SignUpSetUpPage().enterFirstName(fname);
		new SignUpSetUpPage().enterLastName(lname);
		new SignUpSetUpPage().enterDOB(dob);
		Thread.sleep(1000);
		new SignUpSetUpPage().clickProceedToContact();
	}

	/**
	 * Navica contact setup account method
	 * 
	 * @param zip
	 * @param city
	 * @param country
	 * @param address
	 * @param buildingNumber
	 * @param pNumber
	 * @param state
	 * 
	 * @throws Exception
	 *
	 */

	public static void signupAccountSetupContact(String zip, String city, String country, String address,
			String buildingNumber, String pNumber, String state) throws Exception {

		new SignUpSetUpPage().enterZipCode(zip);
		new SignUpSetUpPage().selectState(state);
		new SignUpSetUpPage().enterCity(city);
		new SignUpSetUpPage().enterCountry(country);
		new SignUpSetUpPage().enterAddress(address);
		new SignUpSetUpPage().enterBuildingNumber(buildingNumber);
		new SignUpSetUpPage().enterPhoneNumber(pNumber);
	}

	/**
	 * eMed ID uploading method
	 * 
	 * @param docType
	 * 
	 * @throws Exception
	 *
	 */

	public static void signupAccountSetupUploadId(String docType) throws Exception {
		new SignUpSetUpPage().uploadUserSelfie();
		new SignUpSetUpPage().clickProceedToTerms();
	}

	/**
	 * eMed create account method
	 * 
	 * @param fName
	 * @param lName
	 * 
	 * @throws Exception
	 *
	 */

	public static void signupAccountSetupCreateAccount(String fName, String lName) throws Exception {
		new SignUpSetUpPage().clickSignUpTerms();
		new SignUpSetUpPage().clickSignUpInfo();
		new SignUpSetUpPage().enterFirstNameSignature(fName);
		new SignUpSetUpPage().enterLastNameSignature(lName);
	}

	/**
	 * eMed account registration method
	 * 
	 * @param fName
	 * @param lName
	 * 
	 * @throws Exception
	 *
	 */

	public static void fillRegisterSetup() throws Exception {

		RegistrationFlow.signupAccountSetupPersonal(CommonFunctions.getdata("FirstName"),
				CommonFunctions.getdata("LastName"), CommonFunctions.getdata("DateofBirth"),
				CommonFunctions.getdata("Gender"), CommonFunctions.getdata("Pronoun"), CommonFunctions.getdata("Race"),
				CommonFunctions.getdata("Ethinicity"));
		RegistrationFlow.signupAccountSetupContact(CommonFunctions.getdata("ZipCode"), CommonFunctions.getdata("City"),
				CommonFunctions.getdata("Country"), CommonFunctions.getdata("Address"),
				CommonFunctions.getdata("BuildingNumber"), CommonFunctions.getdata("PhNumber"),
				CommonFunctions.getdata("State"));
		Thread.sleep(2000);
		new SignUpSetUpPage().clickProceedToCreateAccount();
		new SignUpSetUpPage().confirmYourAddress("0");
		new SignUpSetUpPage().verifyEMEDAccountCreateSuccess();
		new SignUpSetUpPage().clickAccountOkay();
	}

	/**
	 * eMed AccountForgetPasswordFlow
	 * 
	 * @param email
	 * @param resetPassword
	 * 
	 * @throws Exception
	 *
	 */

	public static void eMedAccountForgetPasswordFlow(String email, String resetPassword) throws Exception {
		new EMedLoginPage().verifyLoginPage();
		new EMedLoginPage().clickforgetPassword();
		new EMedLoginPage().verifyForgotPasswordPage();
		new EMedLoginPage().enterLoginEmail(email);
		new EMedLoginPage().clickSendResetCode();
		EmailVerification.getVerifyCodeForEmedReset();
		new EMedLoginPage().resetverificationCode(getVerifyCode());
		new EMedLoginPage().enterLoginPassword(resetPassword);
		new EMedLoginPage().clickUpdatePassword();
		new EMedLoginPage().verifyResetPasswordUpdate();
	}

	/**
	 * Navica AccountForgetPasswordFlow
	 * 
	 * @param email
	 * @param resetPassword
	 * 
	 * @throws Exception
	 *
	 */

	public static void navicaForgetPasswordFlow(String email, String resetPassword) throws Exception {
		new EMedLoginPage().verifyLoginPage();
		new NavicaPage().clickNavicaforgetPassword();
		new NavicaPage().verifyNavicaForgotPasswordPage();
		new NavicaPage().enterNavicaResetEmail(email);
		new NavicaPage().clickNavicaSendResetCode();
		EmailVerification.getVerifyCodeForNavicaReset();
		new NavicaPage().enterResetVerificationCode(getVerifyCode());
		new NavicaPage().clickVerifyCode();
		new NavicaPage().enterNavicaNewPassword(resetPassword);
		new NavicaPage().enterNavicaReenterPassword(resetPassword);
		new NavicaPage().clickNavicaResetContinue();
		new EMedLoginPage().verifyLoginPage();
		new NavicaPage().clickNavicaLogin();
		new NavicaPage().enterNavicaEmail(getNavicaEmail());
		new NavicaPage().enterNavicaPassword(resetPassword);
		new NavicaPage().clickNavicaSignIn();
		new NavicaPage().checkNavicaPermission();
	}

}
