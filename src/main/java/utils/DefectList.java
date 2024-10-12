package main.java.utils;

import java.util.HashMap;
import java.util.Map;

import main.java.base.TestBase;

public class DefectList extends TestBase {

	/**
	 * 
	 * @This method is used to store multiple email's and get desired domain based
	 *       on requirement
	 * 
	 * @param eName
	 * @return
	 */
	public static String getP1Defect(String p1Defect) {

		Map<String, String> p1 = new HashMap<String, String>();
		p1.put("DOC-737", "Sign Up | Missing Successful verification message DOC-737");
		p1.put("DP-1819", "(P1) credit card decline message received but order placed DP-1819");
		p1.put("ETIGER-410", "www DOB one day less than selected at Sign-Up - ETIGER-410");
		p1.put("DP-1912",
				"(P1) eMed account login returns \\\"account does not exist\\\", new account returns \\\"already existing account\\\" DP-1912");
		p1.put("DP-3096", "(P1) Customer Database not listed data's DP-3096");
		p1.put("DP-2274", "(P1) No error message for invalid payment cards.DP-2274");
		p1.put("DP-1629", "HOTFIX - (P1) order summary calculation total is incorrect DP-1629");
		p1.put("DP-1640",
				"User unable to continue shopping when Maine ,Wyoming ,Nevada state is entered as shipping address DP-1640");
		p1.put("ETIGER-790",
				"(Blocker) Shipping address displays not available while purchasing kit in eCommerce website ETIGER-790");
		p1.put("DP-2737",
				"P1-CSDB tool-when user selects forgot password link, no instructions are sent to reset password and no error handling DP-2737");
		p1.put("DP-1936", "Customers are not able to create an account via eMed to place an order  DP-1936");
		p1.put("ETIGER-352",
				"(P1) Update Eligibility Survey - User is Ineligible after selecting a special case and no symptom ETIGER-352");
		p1.put("CSS-20", "(P1) Forgot password link on homepage is not working as it should - CSS-20");
		p1.put("ETIGER-391", "Global update to checkout workflow UX when payment issues are detected.-ETIGER-391");
		p1.put("ETIGER-717",
				"(Blocker) User not able to complete order says,\"Could not complete order\" in stg and qa env ETIGER-717");
		p1.put("DP-2776",
				"(P1) Confirmation email not received to the patient inbox after successful purchase DP-2776");
		p1.put("DP-1915", "(P1) Blank screen encountered when attempting to shop DP-1915");
		p1.put("ETIGER-213", "ETIGER-213 (P1) Error while submitting data");
		p1.put("DP-1021", "(P1) New User account registration sends bad verification code DP-1021");
		p1.put("ETIGER-315",
				"(Blocker) Unable to proceed to purchase a kit due to next question button not working and date of birth questionnaire field is missing in dev2 env ETIGER-315");
		p1.put("ETIGER-517",
				"(Blocker) eMed login popup is blocking from proceeding to vlab questionnaire page in both stg and dev2 ETIGER-517");
		p1.put("ETIGER-754",
				"(P1) Navica Password reset accepts the account creation verify code to reset password in stg and prod -ETIGER-754");
		p1.put("ETIGER-715", "HOTFIX - (P1) - STG - GGM are not receiving orders ETIGER-715");

		return p1.get(p1Defect);

	}

	/**
	 * 
	 * @This method is used to store multiple email's and get desired domain based
	 *       on requirement
	 * 
	 * @param eName
	 * @return
	 */

	public static String getP2Defect(String p2Defect) {

		Map<String, String> p2 = new HashMap<String, String>();
		p2.put("ETIGER-392",
				"(P2)- When Purchasing Kit Card does not go through , Order still shows as Open - ETIGER-392");
		p2.put("DP-1841", "Cancelled order shows as approved by eMED - DP-1841");
		p2.put("DP-1983", "(P2) - Checkout Page - Shipping Page hangs DP-1983 ");
		p2.put("ETIGER-409", "P3 - Virtual Lab - Start Testing - Contact help link does not redirect ETIGER-409");
		p2.put("CSS-23", "(P2) Customer Service tool - Page is not refreshed after partial refund - CSS-23");
		p2.put("DOC-737", "Sign Up | Missing Successful verification message DOC-737");
		p2.put("ETIGER-437",
				"ETIGER-437 - (P2) My Order page and Order confirmation mail displays tax and total incorrectly for some addresses");
		p2.put("ETIGER-406",
				"(P2) (STG only) ElasticPath = Order, shipping, and payment status' are not updated after approving process in GoGomeds ETIGER-406");
		p2.put("ETIGER-666", "(P2) Receiving error when trying to reset emed account password ETIGER-666");
		p2.put("ETIGER-422",
				"(P2) - Intermittent issue Log in with Navica sends you back into log in with emed page ETIGER-422");
		p2.put("CSS-250", "(P2) - Replacement orders are failing for the priority address CSS-250");
		p2.put("CSS-204",
				"(P2) Password change module doesn't close after password changed successfully for eMed account CSS-204");
		p2.put("CSS-84", "CORS error when retrieving customer in CS admin tool CSS-84");
		p2.put("CSS-278", "(P2) PROD - B2B replacement page displays white page CSS-278");
		p2.put("CSS-486", "(P2) Customer personal and contact details are not updated after first save CSS-486");
		p2.put("CSS-485",
				"(P2) Warning Popup for fill the customer details floats along with the page scroller CSS-485");
		p2.put("CSS-496", "Front End of Admin tool shows date of birth default as \"January 1, 1970\" CSS-496");
		p2.put("CSS-190", "Bad Error Message when 'Adding User' - if user already exists");
		p2.put("CSS-249", "(P2) CS admin tool displays \"order created successfully\" for the invalid address");
		p2.put("CSS-233", "(P2) CS Admin tool says 'Account already exist' on creating another IT Profile admin");
		p2.put("EMED-1915", "(P2) My Profile - Cancel status not displaying");
		return p2.get(p2Defect);

	}

}
