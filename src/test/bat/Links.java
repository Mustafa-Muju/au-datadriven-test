package test.bat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;
import main.java.utils.InvokeBrowser;

public class Links extends TestBase {

	/**
	 * 
	 * @Method runs before each test and provide data sheet sheets
	 * 
	 * @throws Exception
	 */
	@BeforeClass(alwaysRun = true)
	public void executeBeforeTest() throws Exception {
		sheetName.set("linkbat");
		testId.set(Thread.currentThread().getId());
		System.out.println(CommonFunctions.capitalize(getSheetName()) + " is started");
	}

	/**
	 * This scenario is to verify the title of the emed webpages links
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "linkbat", "fullbat" })
	public void a_links() throws Exception {
		getMethodData(1);
		if (env.equalsIgnoreCase("prod")) {
			InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
			CommonFunctions.openNewPage("https://www.emed.com/express", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "emed express");
			CommonFunctions.openNewPage("https://www.emed.com/walgreens", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "home");
			CommonFunctions.openNewPage("https://www.emed.com/airline-travel", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Airline Travel");
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the "+env+" env");
		}

	}

	/**
	 * This scenario is to verify the title of the emed states links
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "linkbat", "fullbat" })
	public void b_links() throws Exception {
		getMethodData(2);
		if (env.equalsIgnoreCase("prod")) {
			InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
			CommonFunctions.openNewPage("https://colorado.emed.com", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Colorado");
			CommonFunctions.openNewPage("https://massachusetts.emed.com/", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Massachusetts");
			CommonFunctions.openNewPage("https://ohio.emed.com/", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "emed ohio");
			CommonFunctions.openNewPage("https://ohio.emed.com/locations", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "locations");
			CommonFunctions.openNewPage("https://ohio.emed.com/beta", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "emed ohio beta");
			CommonFunctions.openNewPage("https://virginia.emed.com/", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "emed Virginia");
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the " + env + " env");
		}
	}

	/**
	 * This scenario is to verify the title of the emed partners links
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "linkbat", "fullbat" })
	public void c_links() throws Exception {
		getMethodData(3);
		if (env.equalsIgnoreCase("prod")) {
			InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
			CommonFunctions.openNewPage("https://united.emed.com/", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | United Airlines");
			CommonFunctions.openNewPage("https://delta.emed.com/", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Airline Travel");
			CommonFunctions.openNewPage("https://american.emed.com/", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Airline Travel");
			CommonFunctions.openNewPage("https://blace.emed.com/", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed - Blace");
			CommonFunctions.openNewPage("https://pg.emed.com/", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed - P&G");
			CommonFunctions.openNewPage("https://quest.emed.com", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Quest");
			CommonFunctions.openNewPage("https://www.emed.com/n", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | NAVICA");
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the " + env + " env");
		}
	}

	/**
	 * This scenario is to verify the title of the emed travel links
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "linkbat", "fullbat" })
	public void d_links() throws Exception {
		getMethodData(4);
		if (env.equalsIgnoreCase("prod")) {
			InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
			CommonFunctions.openNewPage("https://travel.emed.com", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Travel");
			CommonFunctions.openNewPage("https://travel.emed.com/expedia", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Travel");
			CommonFunctions.openNewPage("https://travel.emed.com/kayak", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Kayak");
			CommonFunctions.openNewPage("https://travel.emed.com/flight-aware", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Flight Aware");
			CommonFunctions.openNewPage("https://travel.emed.com/ivisa", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | iVisa");
			CommonFunctions.openNewPage("https://travel.emed.com/fit-2-fly", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Fit-2-Fly");
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the " + env + " env");
		}
	}

	/**
	 * This scenario is to verify the title of other emed links
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "linkbat", "fullbat" })
	public void e_links() throws Exception {
		getMethodData(5);
		if (env.equalsIgnoreCase("prod")) {
			InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));
			CommonFunctions.openNewPage("https://uhc.emed.com/", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | United Health Care");
			CommonFunctions.openNewPage("https://forward.emed.com", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "eMed | Forward");
			CommonFunctions.openNewPage("https://mypharmacy.emed.com", getDriver());
			CommonFunctions.verifyPageTitle(getDriver(), "My Pharmacy");
		} else {
			CommonFunctions.logMessageSkipExecution("This Scenario is not applicable for the " + env + " env");
		}
	}
}
