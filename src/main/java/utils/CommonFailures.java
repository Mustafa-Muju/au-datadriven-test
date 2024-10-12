package main.java.utils;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import main.java.base.TestBase;

public class CommonFailures extends TestBase {

	private static JavascriptExecutor jse = null;

	public CommonFailures(WebDriver driver) {
		jse = (JavascriptExecutor) driver;
	}

	/**
	 * This Method includes all common failure
	 *
	 */

	public boolean commonErrorsCaptures() throws Exception {
		boolean flag = false;

		List<WebElement> verificationCodeError = getDriver()
				.findElements(By.xpath("//*[contains(text(),'Please resend a code to receive')]"));

		List<WebElement> requiredFieldsError = getDriver().findElements(
				By.xpath("//*[contains(text(),'Please fill in all of the required fields to continue')]"));

		List<WebElement> loginCredMisMatch = getDriver()
				.findElements(By.xpath("//*[contains(text(),'The email and password you’ve entered doesn’t match')]"));

		List<WebElement> meetingNotEstablished = getDriver()
				.findElements(By.xpath("//*[contains(text(),'Meeting could not be established')]"));

		List<WebElement> profileSubmitionError = getDriver().findElements(
				By.xpath("//*[contains(text(),'An error was encountered when submitting your profile data')]"));

		List<WebElement> elegibiltyError = getDriver()
				.findElements(By.xpath("//p[contains(text(),\"Sorry, we couldn't check your elegibility\")]"));

		if (verificationCodeError.size() > 0) {
			flag = true;
			CommonFunctions.logErrorMessage("(P1) New User account registration sends bad verification code DP-1021");
			throw new Exception();

		} else if (requiredFieldsError.size() > 0) {
			flag = true;
			CommonFunctions.logErrorMessage(
					"Error Please fill in all of the required fields to continue the sign up process..");
			throw new Exception();

		} else if (loginCredMisMatch.size() > 0) {
			flag = true;
			CommonFunctions.logErrorMessage("Error The email and password you’ve entered doesn’t match any account..");
			throw new Exception();

		} else if (meetingNotEstablished.size() > 0) {
			flag = true;
			CommonFunctions.logErrorMessage("Error Oops! Meeting could not be established..");
			throw new Exception();

		} else if (profileSubmitionError.size() > 0) {
			flag = true;
			CommonFunctions.logErrorMessage("ETIGER-213 (P1) Error while submitting data");
			throw new Exception();

		} else if (elegibiltyError.size() > 0) {
			flag = true;
			CommonFunctions.logErrorMessage("ETIGER-225 Getting something went wrong before Eligibility questionnaire");
			throw new Exception();

		}

		return flag;
	}

}
