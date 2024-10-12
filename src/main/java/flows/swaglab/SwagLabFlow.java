package main.java.flows.swaglab;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.pages.swaglab.SwagLabCheckoutPage;
import main.java.pages.swaglab.SwagLabFinishPage;
import main.java.pages.swaglab.SwagLabLandingPage;
import main.java.pages.swaglab.SwagLabPersonalPage;
import main.java.pages.swaglab.SwagLabProductPage;
import main.java.utils.CommonFunctions;

public class SwagLabFlow extends TestBase {

	/**
	 * 
	 * This method for swag lab login page
	 * 
	 * @throws Exception
	 *
	 */

	public static void swagLabLoginFlow() throws Exception {
		new SwagLabLandingPage().verifySwagLoginPage();
		new SwagLabLandingPage().enterUsername(new EncryptCredentails()
				.decrypt(CommonFunctions.getPropertyValues().getProperty("swagusername")));
		new SwagLabLandingPage().enterPassword(new EncryptCredentails()
				.decrypt(CommonFunctions.getPropertyValues().getProperty("swagpassword")));
		new SwagLabLandingPage().clickLoginButton();

	}
	
	/**
	 * 
	 * This method for swag lab select product page
	 * 
	 * @throws Exception
	 *
	 */
	public static void selectProductCartFlow() throws Exception {
		new SwagLabProductPage().verifySwagProductPage();
		new SwagLabProductPage().selectProduct(CommonFunctions.getdata("ProductName"));
		new SwagLabProductPage().clickCartButton();
		new SwagLabProductPage().verifyCartProduct();
		new SwagLabProductPage().clickCheckoutButton();
	}
	
	/**
	 * 
	 * This method for swag lab personal detail filling
	 * 
	 * @throws Exception
	 *
	 */
	public static void fillPersonalDetails() throws Exception {
		new SwagLabPersonalPage().verifySwagProductPage();
		new SwagLabPersonalPage().enterFirstName(CommonFunctions.getdata("FirstName"));
		new SwagLabPersonalPage().enterLastName(CommonFunctions.getdata("LastName"));
		new SwagLabPersonalPage().enterPostalCode(CommonFunctions.getdata("ZipCode"));
		new SwagLabPersonalPage().clickPersonalContinueButton();
	}
	
	/**
	 * 
	 * This method for swag lab completing the checkout
	 * 
	 * @throws Exception
	 *
	 */
	public static void verifyAndContinueCheckoutPage() throws Exception {
		new SwagLabCheckoutPage().verifySwagCheckoutPage();
		new SwagLabCheckoutPage().verifyProductAtCheckout();
		new SwagLabCheckoutPage().clickFinishButton();
	}
	
	/**
	 * 
	 * This method for verifying the successful order creation
	 * 
	 * @throws Exception
	 *
	 */
	public static void verifyOrderCreation() throws Exception {
		new SwagLabFinishPage().verifySwagFinishPage();
		new SwagLabFinishPage().verifyThankyouBanner();
	}

}
