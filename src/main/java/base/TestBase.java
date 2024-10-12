package main.java.base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TimeZone;

import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import main.java.utils.CommonFunctions;
import main.java.utils.TestCreator;
import main.java.utils.email.MailBody;
import main.java.utils.email.MailSender;
import main.java.utils.reportutil.ExtentManager;
import main.java.utils.xlservice.DataReader;

public class TestBase {

	/**
	 * All Web-test variables
	 * 
	 */
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public static WebDriver getDriver() {
		return driver.get();
	}
	
	public static ThreadLocal<Long> testId = new ThreadLocal<Long>();
	public static Long getTestThreadId() {
		return testId.get();
	}
	
	public static ThreadLocal<String> sheetName = new ThreadLocal<String>();
	public static String getSheetName() {
		return sheetName.get();
	}

	public static String Excelpath = "";
	public static String downloadFilePath = "";
	public static String uploadFilePath = "";
	public static String excelPath = "";
	public static String extentReportPath = "";
	public static String mailText = "";
	public static String tagName = System.getenv("gTag");

	/**
	 * Browser Configuration
	 */
	public static String[] browser = System.getenv("Browser").split(":");
	public static String BrowserNeed = browser[0];
	public static String mode = browser[1];

	/**
	 * Email report & Selecting the env type
	 */
	public static String env = System.getenv("ENV");

	public static int isFirst = 1;
	public static ThreadLocal<Integer> totalClassTest = ThreadLocal.withInitial(
	        () -> { return Integer.valueOf(0); } );
	public static Integer getClassTestCount() {
		return totalClassTest.get();
	}
	
	public static ThreadLocal<Integer> errorLogCount = ThreadLocal.withInitial(
	        () -> { return Integer.valueOf(0); } );
	public static Integer getErrorLogCount() {
		return errorLogCount.get();
	}
	public static int passed = 0;
	public static int failure = 0;
	public static int skipping = 0;

	/**
	 * Email and report variables
	 */
	public static String emailReport = System.getenv("mail");
	public static String mailVerbose = "";
	public static String toReport = "";

	public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
	public static ExtentTest getExtentTest() {
		return test.get();
	}

	public static ExtentTest logger;
	public static ExtentReports extent;
	public static LocalTime startTime;
	
	public static ThreadLocal<LinkedList<String>> scenariosCount = ThreadLocal.withInitial(LinkedList::new);
	public static LinkedList<String> getScenariosCount() {
		return scenariosCount.get();
	}
	
	public static ThreadLocal<String> scenarioComments = new ThreadLocal<String>();
	public static String getScenarioComments() {
		return scenarioComments.get();
	}
	
	public static ThreadLocal<String> scenarioNo = new ThreadLocal<String>();
	public static String getScenarioNumber() {
		return scenarioNo.get();
	}
	
	public static ThreadLocal<String> scenarioName = new ThreadLocal<String>();	
	public static String getScenarioName() {
		return scenarioName.get();
	}
	
	public static ThreadLocal<String> scenarioDescription = new ThreadLocal<String>();
	public static String getScenarioDescription() {
		return scenarioDescription.get();
	}
	
	public static ThreadLocal<String> scenarioStatus = new ThreadLocal<String>();
	public static String getScenarioStatus() {
		return scenarioStatus.get();
	}
	
	public static ThreadLocal<Map<String,String>> testData = ThreadLocal.withInitial(HashMap<String,String>::new);
	public static Map<String, String> getTestData() {
        return testData.get();
	}
	
	public static ThreadLocal<JSONObject> scenarioList = ThreadLocal.withInitial(JSONObject::new);
	public static JSONObject getScenarioList() {
        return scenarioList.get();
	}
	
	public static JSONObject testResults = new JSONObject();

	/**
	 * Global variables for Email verification
	 */
	public static ThreadLocal<String> verifyCode = new ThreadLocal<String>();
	public static String getVerifyCode() {
		return verifyCode.get();
	}
	
	public static ThreadLocal<String> Code = new ThreadLocal<String>();
	public static String getCode() {
		return Code.get();
	}

	/**
	 * This Method initializes test process
	 * 
	 * @throws Exception
	 */
	@BeforeSuite(alwaysRun = true)
	public void testCreator() throws Exception {
		startTime = LocalTime.now();
		TestCreator.browserClean();
		TestCreator.reportCreator();
		pathInitialize();
	}

	/**
	 * 
	 * @Method that execute before each test
	 * 
	 */
	@BeforeMethod(alwaysRun = true)
	public void executeBeforeMethod() {
		totalClassTest.set(getClassTestCount()+1);
		errorLogCount.set(0);
		getTestData().clear();
	}

	/**
	 * Initializing the path for the resource files
	 */
	public void pathInitialize() {

		String osName = System.getProperty("os.name");
		if (osName.contains("Win")) {
			downloadFilePath = System.getProperty("user.dir") + "\\downloadDirectory";
			uploadFilePath = System.getProperty("user.dir") + "\\src\\main\\resources\\uploadFiles\\";
			excelPath = System.getProperty("user.dir") + "\\src\\main\\resources\\xlSheets\\";

		} else if (osName.contains("Mac") || osName.contains("Linux")) {
			downloadFilePath = System.getProperty("user.dir") + "/downloadDirectory";
			uploadFilePath = System.getProperty("user.dir") + "/src/main/resources/uploadFiles/";
			excelPath = System.getProperty("user.dir") + "/src/main/resources/xlSheets/";
		}
	}

	/**
	 * @return This method for getting the current date in GMT and return the date
	 *         and time
	 */
	public static String currentDateTimeInGMT() {

		Date now = new Date();
		DateFormat converter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");

		// getting GMT timezone, you can get any timezone e.g. UTC
		converter.setTimeZone(TimeZone.getTimeZone("GMT"));
		String dateTime1 = converter.format(now);

		return dateTime1;
	}

	/**
	 * @return This method for getting the current date,Time and return the date and
	 *         time
	 */
	public static String currentDateTime() {

		Date now = new Date();
		DateFormat converter = new SimpleDateFormat("DD/MM/YYYY:HH:mm:ss");

		// getting GMT timezone, you can get any timezone e.g. UTC
		converter.setTimeZone(TimeZone.getTimeZone("GMT"));
		String dateTime1 = converter.format(now);

		return dateTime1;
	}

	/**
	 * @author Mohammed Mustafa This method will delete the eMed automation report
	 *         and other unwanted files present in test-output folder
	 * 
	 */
	public void deleteReportFiles() throws IOException {

		File directory = new File(System.getProperty("user.dir") + "//test-output//");

		FileUtils.deleteDirectory(directory);

		if (!directory.exists()) {
			System.out.println("Test-output files are deleted successfully");
			directory.mkdir();
		} else {
			System.err.println("Test-output folder and files are not deleted");
		}
	}

	/**
	 * 
	 * @Method to get the XL data and create extent report
	 * 
	 * @param scenarioNumber
	 * @throws Exception
	 */
	public void getMethodData(int scenarioNumber) throws Exception {
		DataReader.XlsReader(excelPath + getSheetName() + ".xlsx", getSheetName(), scenarioNumber);
		test.set(extent.createTest(CommonFunctions.getdata("Scenario")));
		getExtentTest().assignCategory(getSheetName());
		ExtentManager.scenarioLogger(CommonFunctions.getdata("Iteration"), CommonFunctions.getdata("Scenario"),
				"started");
	}
	
	/**
	 * 
	 * @Method runs after each method to update test before next execution
	 * 
	 * @throws Exception
	 * 
	 */
	@AfterMethod(alwaysRun = true)
	public void runAfterEachMethod() throws Exception {
		ExtentManager.scenarioLogger(CommonFunctions.getdata("Iteration"), CommonFunctions.getdata("Scenario"),
				"finished");

		if (!getScenariosCount().contains(CommonFunctions.getdata("Iteration"))) {
			getScenariosCount().add(CommonFunctions.getdata("Iteration"));
			scenarioNo.set(CommonFunctions.getdata("Iteration"));
			scenarioName.set(CommonFunctions.getdata("ScenarioTitle"));
			scenarioDescription.set(CommonFunctions.getdata("Scenario"));
			scenarioStatus.set("Failed");
			scenarioComments.set("Scenario failed abruptly with no reason...");
			failure++;
			getExtentTest().log(Status.FAIL, MarkupHelper.createLabel("Scenario failed abruptly with no reason...", ExtentColor.RED));
		}
		
		Map<Object, Object> testAttrb = new HashMap<Object, Object>();
        testAttrb.put("scenario_no", getScenarioNumber());
        testAttrb.put("scenario_title", getScenarioName());
        testAttrb.put("scenario_description", getScenarioDescription());
        testAttrb.put("scenario_status", getScenarioStatus());
        testAttrb.put("scenario_comment", getScenarioComments());

        getScenarioList().put(getScenarioNumber(), testAttrb);

        scenarioNo.remove();
        scenarioName.remove();
        scenarioDescription.remove();
        scenarioStatus.remove();
        scenarioComments.remove();
        
	}

	/**
	 * 
	 * This method closes driver and extent report
	 * 
	 * @throws Exception
	 */
	@AfterClass(alwaysRun = true)
	public void reportFlush() throws Exception {
		testResults.put(getSheetName(), getScenarioList());
		scenarioList.remove();
		if (getDriver() != null && !getDriver().toString().contains("(null)")) {
			getDriver().quit();
			driver.remove();
		}
		extent.flush();
		totalClassTest.set(0);
	}

	/**
	 * This method triggers the email report
	 * 
	 * @throws Exception
	 */
	@AfterSuite(alwaysRun = true)
	public void emailReport() throws Exception {
		MailBody.emailScenarioTableCreate();
		if (emailReport != null) {
			mailVerbose = emailReport.split(":")[0];
			toReport = emailReport.split(":")[1];
			
			if (mailVerbose.equals("1") || mailVerbose.equals("2") || mailVerbose.equals("3")) {
				MailSender.fileZip();
				//MailSender.sendReport();
			}
		}
	}

}
