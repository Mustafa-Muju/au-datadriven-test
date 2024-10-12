package main.java.utils.reportutil;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.MediaEntityBuilder;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

/**
 * 
 * @author Mohammed Mustafa
 * 
 *         Test Listener class that listens the test scenarios status and
 *         generate extent report
 *
 */
public class TestListener extends TestBase implements ITestListener {

	@Override
	public synchronized void onStart(ITestContext context) {

	}

	@Override
	public synchronized void onFinish(ITestContext context) {

	}

	@Override
	public synchronized void onTestStart(ITestResult result) {

	}

	@Override
	public synchronized void onTestSuccess(ITestResult result) {

		if (getScenarioStatus() == null) {
			try {
				getScenariosCount().add(CommonFunctions.getdata("Iteration"));
				scenarioNo.set(CommonFunctions.getdata("Iteration"));
				scenarioName.set(CommonFunctions.getdata("ScenarioTitle"));
				scenarioDescription.set(CommonFunctions.getdata("Scenario"));

			} catch (Exception e) {
				e.printStackTrace();
			}

			scenarioStatus.set("Passed");
			if(!getTestData().keySet().isEmpty()) {
				scenarioComments.set("Scenario passed. Test data used - "+getTestData());
			} else {
				scenarioComments.set("Scenario passed requires no test data.");
			}
			passed++;
			getExtentTest().pass("Scenario Passed");

		}
	}

	@Override
	public synchronized void onTestFailure(ITestResult result) {

		String temp = "";
		try {
			if (!getDriver().toString().contains("(null)")) {
				temp = CommonFunctions.getScreenShot();
				getExtentTest().fail("Screen Shot for reference",
						MediaEntityBuilder.createScreenCaptureFromPath(temp).build());
			} else {
				System.err.println("Unable to get screenshot due to no driver open!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		getExtentTest().fail(result.getThrowable());

	}

	@Override
	public synchronized void onTestSkipped(ITestResult result) {

		try {
			skipping++;
			getScenariosCount().add(CommonFunctions.getdata("Iteration"));
			scenarioNo.set(CommonFunctions.getdata("Iteration"));
			scenarioName.set(CommonFunctions.getdata("ScenarioTitle"));
			scenarioDescription.set(CommonFunctions.getdata("Scenario"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		scenarioStatus.set("Skipped");
		getExtentTest().skip("This scenario has been skipped by user");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

}
