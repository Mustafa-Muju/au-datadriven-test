package main.java.utils;

import java.io.IOException;

import main.java.base.TestBase;
import main.java.utils.reportutil.ExtentManager;

/**
 * 
 * @author Mohammed Mustafa
 * 
 * This class runs before all test scenarios
 *
 */
public class TestCreator extends TestBase{
	
	public static void browserClean() throws Exception {
		//CommonFunctions.killBrowser();
	}
	
	
	public static void reportCreator() throws IOException {
		new TestBase().deleteReportFiles();
		extent = ExtentManager.createInstance();
		extent.flush();
	}
}
