package main.java.utils.reportutil;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.Platform;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.aventstack.extentreports.reporter.configuration.ViewName;

import main.java.base.TestBase;

public class ExtentManager extends TestBase {

	private static ExtentReports extent;
	private static Platform platform;
	private static String reportFileName = "eMed.html";
	private static String macPath = System.getProperty("user.dir") + "/test-output/eMedReport/eMedTest" + startTime();
	private static String windowsPath = System.getProperty("user.dir") + "\\test-output\\eMedReport\\eMedTest"
			+ startTime();
	private static String macReportFileLoc = macPath + "/" + reportFileName;
	private static String winReportFileLoc = windowsPath + "\\" + reportFileName;

	public static ExtentReports getInstance() {
		if (extent == null)
			createInstance();
		return extent;
	}

	/**
	 * This method for file path
	 * 
	 * 
	 */

	public static String getFilePath() {
		extentReportPath = getReportFileLocation(platform);
		return extentReportPath;
	}

	/**
	 * This method for Create an extent report instance
	 * 
	 * 
	 */

	public static ExtentReports createInstance() {
		platform = getCurrentPlatform();
		String filePath = getFilePath();
		String operSys = System.getProperty("os.name").toLowerCase();
		String fileName = "";
		if (operSys.contains("win")) {

			fileName = filePath;
		} else {

			fileName = filePath;
		}
		ExtentSparkReporter htmlReporter = new ExtentSparkReporter(fileName);
		htmlReporter.viewConfigurer().viewOrder().as(new ViewName[] {ViewName.DASHBOARD, ViewName.CATEGORY, ViewName.TEST, ViewName.EXCEPTION}).apply();
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setDocumentTitle("eMed Automation Report");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setReportName("eMed Automation Report");

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		return extent;
	}

	/**
	 * This method to log the scenarios start
	 * 
	 * 
	 * @param message
	 * @throws Exception
	 *
	 */
	public static void scenarioLogger(String scenarioNum, String message, String scenarioAt) throws Exception {
		getExtentTest().log(Status.INFO, MarkupHelper.createLabel("****" + scenarioNum + "." + message + " " + scenarioAt + "****",
				ExtentColor.BLUE));
		System.out.println("****" + scenarioNum + "." + message + " " + scenarioAt + "****");
	}

	/**
	 * This method to get Report File Location
	 * 
	 * @param platform
	 * 
	 * 
	 */

	private static String getReportFileLocation(Platform platform) {
		String reportFileLocation = null;
		switch (platform) {
		case LINUX:
		case MAC:
			reportFileLocation = macReportFileLoc;
			createReportPath(macPath);
			System.out.println(
					"ExtentReport Path for " + System.getProperty("os.name").toUpperCase() + ": " + macPath + "\n");
			break;
		case WINDOWS:
			reportFileLocation = winReportFileLoc;
			createReportPath(windowsPath);
			System.out.println("ExtentReport Path for WINDOWS: " + windowsPath + "\n");
			break;
		default:
			System.out.println("ExtentReport path has not been set! There is a problem!\n");
			break;
		}
		return reportFileLocation;
	}

	/**
	 * This method to Create the report path if it does not exist
	 * 
	 * @param path
	 * 
	 * 
	 */

	private static void createReportPath(String path) {
		File testDirectory = new File(path);
		if (!testDirectory.exists()) {
			if (testDirectory.mkdirs()) {
				System.out.println("Directory: " + path + " is created!");
			} else {
				System.out.println("Failed to create directory: " + path);
			}
		} else {
			System.out.println("Directory already exists: " + path);
		}
	}

	/**
	 * This method to Get current platform
	 * 
	 * 
	 * 
	 */

	private static Platform getCurrentPlatform() {
		if (platform == null) {
			String operSys = System.getProperty("os.name").toLowerCase();
			if (operSys.contains("win")) {
				platform = Platform.WINDOWS;
			} else if (operSys.contains("nix") || operSys.contains("nux") || operSys.contains("aix")) {
				platform = Platform.LINUX;
			} else if (operSys.contains("mac")) {
				platform = Platform.MAC;
			}
		}
		return platform;
	}

	/**
	 * This method to Start Time
	 * 
	 * 
	 * 
	 */

	private static String startTime() {
		DateFormat dateFormat = new SimpleDateFormat("HHmmss");
		Date date = new Date();
		return dateFormat.format(date);
	}

}