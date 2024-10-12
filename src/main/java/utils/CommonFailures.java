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


		if (verificationCodeError.size() > 0) {
			flag = true;
			CommonFunctions.logErrorMessage("(P1) New User account registration sends bad verification code DP-1021");
			throw new Exception();

		}

		return flag;
	}

}
