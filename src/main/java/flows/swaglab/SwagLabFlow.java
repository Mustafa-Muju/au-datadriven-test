package main.java.flows.swaglab;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.pages.swaglab.SwagLabLandingPage;
import main.java.utils.CommonFunctions;

public class SwagLabFlow extends TestBase {

	/**
	 * 
	 * This method for elastic path Login flow
	 * 
	 * @throws Exception
	 *
	 */

	public static void swagLabLoginFlow() throws Exception {
		new SwagLabLandingPage().verifySwagLoginPage();
		new SwagLabLandingPage().enterUsername(new EncryptCredentails()
				.decrypt(CommonFunctions.getPropertyValues().getProperty("username")));
		new SwagLabLandingPage().enterPassword(new EncryptCredentails()
				.decrypt(CommonFunctions.getPropertyValues().getProperty("password")));
		new SwagLabLandingPage().clickLoginButton();

	}

}
