package main.java.pages.vlab.lktransfer;

import java.util.regex.Pattern;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class LKTransactionPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(id = "json-renderer-pickup")
	private WebElement eleEllkyInfo;

	public LKTransactionPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * 
	 * @This method to verify the LK Transfer Transaction page is displayed
	 * 
	 * @throws Exception
	 *
	 */
	public void verifyLKTransferTransactionPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Transaction Detail");
		CommonFunctions.logMessage("LK Transaction Page is Displayed");
	}

	/**
	 * 
	 * @This method to verify the customer ellky information
	 * 
	 * @throws Exception
	 */
	public void verifyEllkyInfo() throws Exception {
		String info = CommonFunctions.getTextOfElement(eleEllkyInfo, "ellky info");
		
		String firstName = CommonFunctions.restCorrelateJSON(info, "patient.firstName");

		String fName = CommonFunctions.getdata("FirstName").split(Pattern.quote("|"))[0];
		if (firstName.equalsIgnoreCase(fName)) {
			CommonFunctions.logMessage("Expected First name matched the actual first name in Ellky ===> " + firstName);
		} else {
			CommonFunctions.logErrorMessage("Expected first name " + firstName
					+ " not matched actual first name in Ellky " + fName);
		}

		String lastName = CommonFunctions.restCorrelateJSON(info, "patient.lastName");

		String lName = CommonFunctions.getdata("LastName").split(Pattern.quote("|"))[0];
		if (lastName.equalsIgnoreCase(lName)) {
			CommonFunctions.logMessage("Expected Last name matched the actual last name in Ellky ===> " + lastName);
		} else {
			CommonFunctions.logErrorMessage("Expected last name " + lastName + " not matched actual last name in Ellky "
					+ lName);
		}

		String address = CommonFunctions.restCorrelateJSON(info, "patient.address1");

		if (address.equalsIgnoreCase(CommonFunctions.getdata("Address"))) {
			CommonFunctions.logMessage("Expected address matched the actual address in Ellky ===> " + address);
		} else {
			CommonFunctions.logErrorMessage("Expected address " + address + " not matched actual address in Ellky "
					+ CommonFunctions.getdata("Address"));
		}

		String city = CommonFunctions.restCorrelateJSON(info, "patient.city");

		if (city.equalsIgnoreCase(CommonFunctions.getdata("City"))) {
			CommonFunctions.logMessage("Expected city matched the actual city in Ellky ===> " + city);
		} else {
			CommonFunctions.logErrorMessage(
					"Expected city " + city + " not matched actual city in Ellky " + CommonFunctions.getdata("City"));
		}

		String state = CommonFunctions.restCorrelateJSON(info, "patient.state");

		String acronymnState = CommonFunctions.getUSStateCode(CommonFunctions.getdata("State"));

		if (state.equalsIgnoreCase(acronymnState)) {
			CommonFunctions.logMessage("Expected state matched the actual state in Ellky ===> " + state);
		} else {
			CommonFunctions
					.logErrorMessage("Expected state " + state + " not matched actual state in Ellky " + acronymnState);
		}

		String county = CommonFunctions.restCorrelateJSON(info, "patient.county");

		if (county.equalsIgnoreCase(CommonFunctions.getdata("Country"))) {
			CommonFunctions.logMessage("Expected county matched the actual county in Ellky ===> " + county);
		} else {
			CommonFunctions.logErrorMessage("Expected county " + county + " not matched actual county in Ellky "
					+ CommonFunctions.getdata("Country"));
		}

		String zip = CommonFunctions.restCorrelateJSON(info, "patient.zip");

		if (zip.equalsIgnoreCase(CommonFunctions.getdata("ZipCode"))) {
			CommonFunctions.logMessage("Expected zip code matched the actual zip code in Ellky ===> " + zip);
		} else {
			CommonFunctions.logErrorMessage("Expected zip code " + zip + " not matched actual zip code in Ellky "
					+ CommonFunctions.getdata("ZipCode"));
		}

		String dob = CommonFunctions.restCorrelateJSON(info, "patient.dob").split("T")[0];
		String dateOfBirth = CommonFunctions.getdata("DateofBirth").split("/")[2] + "-"
				+ CommonFunctions.getdata("DateofBirth").split("/")[0] + "-"
				+ CommonFunctions.getdata("DateofBirth").split("/")[1];

		if (dob.equalsIgnoreCase(dateOfBirth)) {
			CommonFunctions.logMessage("Expected date of birth matched the actual date of birth in Ellky ===> " + dob);
		} else {
			CommonFunctions.logErrorMessage("Expected date of birth " + dob
					+ " not matched actual date of birth in Ellky " + dateOfBirth);
		}

//		String sex = CommonFunctions.restCorrelateJSON(info, "patient.sex");
//
//		if (sex.equalsIgnoreCase(CommonFunctions.getdata("Gender").substring(0, 1))) {
//			CommonFunctions.logMessage("Expected gender matched the actual gender in Ellky ===> " + sex);
//		} else {
//			CommonFunctions.logErrorMessage("Expected gender " + sex + " not matched actual gender in Ellky "
//					+ CommonFunctions.getdata("Gender"));
//		}

		String homePhone = CommonFunctions.restCorrelateJSON(info, "patient.homePhone").replaceAll("\\(|\\)|-|\\s+", "");

		if (homePhone.equalsIgnoreCase(CommonFunctions.getdata("PhNumber"))) {
			CommonFunctions.logMessage("Expected ph number matched the actual ph number in Ellky ===> " + homePhone);
		} else {
			CommonFunctions.logErrorMessage("Expected ph number " + homePhone
					+ " not matched actual ph number in Ellky " + CommonFunctions.getdata("PhNumber"));
		}

//		String ethnicity = CommonFunctions.restCorrelateJSON(info, "patient.ethnicity");
//
//		if (ethnicity.equalsIgnoreCase(CommonFunctions.getdata("Ethinicity"))) {
//			CommonFunctions.logMessage("Expected ethnicity matched the actual ethnicity in Ellky ===> " + ethnicity);
//		} else {
//			CommonFunctions.logErrorMessage("Expected ethnicity " + ethnicity
//					+ " not matched actual ethnicity in Ellky " + CommonFunctions.getdata("Ethinicity"));
//		}
//
//		String race = CommonFunctions.restCorrelateJSON(info, "patient.race");
//
//		if (race.equalsIgnoreCase(CommonFunctions.getdata("Race"))) {
//			CommonFunctions.logMessage("Expected race matched the actual race in Ellky ===> " + race);
//		} else {
//			CommonFunctions.logErrorMessage(
//					"Expected race " + race + " not matched actual race in Ellky " + CommonFunctions.getdata("Race"));
//		}

		String result = CommonFunctions.restCorrelateJSON(info, "test[0].result[0].value");

		if (result.equalsIgnoreCase(CommonFunctions.getdata("Result"))) {
			CommonFunctions
					.logMessage("Expected session result matched the actual session result in Ellky ===> " + result);
		} else {
			CommonFunctions.logErrorMessage("Expected session result " + result
					+ " not matched actual session result in Ellky " + CommonFunctions.getdata("Result"));
		}
	}

}
