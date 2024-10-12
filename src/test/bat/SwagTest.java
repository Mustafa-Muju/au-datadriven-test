package test.bat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;
import main.java.utils.InvokeBrowser;

public class SwagTest extends TestBase {

	/**
	 * 
	 * @Method runs before each test and provide data sheet sheets
	 * 
	 * @throws Exception
	 */
	@BeforeClass(alwaysRun = true)
	public void executeBeforeTest() throws Exception {
		sheetName.set("swaglab");
		testId.set(Thread.currentThread().getId());
		System.out.println(CommonFunctions.capitalize(getSheetName()) + " is started");
	}

	/**
	 * This is the swaglabs scenarios
	 * 
	 * @throws Exception
	 */
	@Test(groups = { "swagtest" })
	public void a_swaglab() throws Exception {
		getMethodData(1);
		InvokeBrowser.invokeApplication(CommonFunctions.getdata("Platform"));

	}

}
