package main.java.flows.www;

import main.java.base.TestBase;
import main.java.pages.www.EMedLoginPage;
import main.java.pages.www.MyOrderPage;
import main.java.utils.CommonFunctions;

public class CancelationFlow extends TestBase {

	/**
	 * KIT cancellation flow method
	 * 
	 * @throws Exception
	 *
	 */

	public static void cancelOpenOrderFlow() throws Exception {
		CommonFunctions.logMessage("<----Order Cancelation Flow Started---->");
		new EMedLoginPage().clickUserAccount();
		new EMedLoginPage().clickMyOrder();
		new MyOrderPage().verifyMyOrderPage();
		new MyOrderPage().clickOrderNumber(getConfirmationNumber());
		new MyOrderPage().verifyOrderDetails(getConfirmationNumber());
		new MyOrderPage().clickCancelOrder();
		new MyOrderPage().confirmCancelOrder();
		new MyOrderPage().verifyOrderCancel();
		new MyOrderPage().clickGoToMyOrders();
		new MyOrderPage().verifyCanceledOrder(getConfirmationNumber());
	}
}
