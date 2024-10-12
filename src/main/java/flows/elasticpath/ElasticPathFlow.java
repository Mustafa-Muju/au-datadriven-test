package main.java.flows.elasticpath;

import encryptusercredentials.EncryptCredentails;
import main.java.base.TestBase;
import main.java.pages.elasticpath.ElasticPathLandingPage;
import main.java.pages.elasticpath.ElasticPathLoginPage;
import main.java.pages.elasticpath.ElasticPathOrderDetailsPage;
import main.java.utils.CommonFunctions;

public class ElasticPathFlow extends TestBase {

	/**
	 * 
	 * This method for elastic path Login flow
	 * 
	 * @throws Exception
	 *
	 */

	public static void elasticPathLoginFlow() throws Exception {
		new ElasticPathLoginPage().verifyElasticPathLoginPage();
		new ElasticPathLoginPage().enterElasticUser(
				new EncryptCredentails().decrypt(CommonFunctions.getPropertyValues().getProperty("elasticpathuserid")));
		new ElasticPathLoginPage().enterElasticPassword(new EncryptCredentails()
				.decrypt(CommonFunctions.getPropertyValues().getProperty("elasticpathpassword")));
		new ElasticPathLoginPage().clickElasticLogin();

	}

	/**
	 * 
	 * This method for to verify the ecommerce order details
	 * 
	 * @throws Exception
	 *
	 */

	public static void elasticPathOrderverifications(String emailId) throws Exception {
		new ElasticPathLandingPage().selectingElasticPathEnv();
		new ElasticPathOrderDetailsPage().verifyElasticPathOrderDetailPage();
		new ElasticPathOrderDetailsPage().clickOrder();
		new ElasticPathOrderDetailsPage().searchUsername(emailId);
		new ElasticPathOrderDetailsPage().verifyshippingAddressInElasticPathPage();
		new ElasticPathOrderDetailsPage().verifyshippingAmmountInElasticPathPage();
	}

	/**
	 * 
	 * This method for to verify the shipping method in elastic path
	 * 
	 * @throws Exception
	 *
	 */
	public void elasticPathShippingMethodverifications(String emailId) throws Exception {
		elasticPathLoginFlow();
		new ElasticPathLandingPage().selectingElasticPathEnv();
		new ElasticPathOrderDetailsPage().verifyElasticPathOrderDetailPage();
		new ElasticPathOrderDetailsPage().clickOrder();
		new ElasticPathOrderDetailsPage().searchUsername(emailId);
		new ElasticPathOrderDetailsPage().verifyshippingMethodInElasticPathPage();

	}

	/**
	 * 
	 * This method for to get order Id in elasticpath
	 * 
	 * @throws Exception
	 *
	 */
	public static void elasticPathOrderId(String emailId) throws Exception {
		elasticPathLoginFlow();
		new ElasticPathLandingPage().selectingElasticPathEnv();
		new ElasticPathOrderDetailsPage().verifyElasticPathOrderDetailPage();
		new ElasticPathOrderDetailsPage().clickOrder();
		new ElasticPathOrderDetailsPage().searchUsername(emailId);
		new ElasticPathOrderDetailsPage().getOrderIdInElasticPath();
	}

	/**
	 * 
	 * This method is to verify the emed user status in elasticpath
	 * 
	 * @throws Exception
	 *
	 */
	public void validateEmedUserStatus(String emailId) throws Exception {
		elasticPathLoginFlow();
		new ElasticPathLandingPage().selectingElasticPathEnv();
		new ElasticPathOrderDetailsPage().verifyElasticPathOrderDetailPage();
		new ElasticPathOrderDetailsPage().clickOrder();
		new ElasticPathOrderDetailsPage().searchUsername(emailId);
		new ElasticPathOrderDetailsPage().verifyOrderStatusInElasticPathPage("complete", "paid", "fulfilled");
	}

	/**
	 * 
	 * @throws Exception
	 * 
	 * @This method is to verify B2B Oder details with Elastic-Path
	 * 
	 */
	public static void elasticPathB2bVerification(String emailId) throws Exception {
		new ElasticPathLandingPage().selectingElasticPathEnv();
		new ElasticPathOrderDetailsPage().verifyElasticPathOrderDetailPage();
		new ElasticPathOrderDetailsPage().clickOrder();
		new ElasticPathOrderDetailsPage().searchUsername(emailId);
		new ElasticPathOrderDetailsPage().verifyElasticPathOrderID();
		new ElasticPathOrderDetailsPage().verifyshippingAddressInElasticPathPage();
		new ElasticPathOrderDetailsPage().B2BAmountVerification();
	}

}
