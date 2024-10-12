package main.java.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import io.github.sukgu.Shadow;
import main.java.base.TestBase;
import main.java.utils.reportutil.ExtentManager;
import main.java.utils.xlservice.DataReader;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteExecuteMethod;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.html5.RemoteWebStorage;
import org.openqa.selenium.*;
import org.openqa.selenium.html5.LocalStorage;

public class CommonFunctions extends TestBase {
	private static JavascriptExecutor jse = null;

	/**
	 * This method to get the date from todays date
	 * 
	 * @param days We have to pass the current date by passing the number of days
	 * @return
	 */
	public static String getDatefromToday(int days) {

		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return dateFormat.format(cal.getTime());
	}

	/**
	 * @author Mohammed Mustafa This method to get the date based on day and format
	 * 
	 * @param days We have to pass the current date by passing the number of days
	 * @param Need to pass format to get it
	 * @return
	 */
	public static String getDateBasedonFormat(String format, int days) {

		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, days);
		return dateFormat.format(cal.getTime());
	}

	/**
	 * This method will generate the date based on the GMT Time which help us to
	 * create a flight on the GMT Time
	 * 
	 * @param days
	 * @return
	 */
	public static String getPSTtime(int days) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, days);
		cal.getTime();
		String text = sdf.format(cal.getTime());
		System.out.println(sdf.format(cal.getTime()));
		return text;
	}

	/**
	 * This method will generate the date based on the GMT Time which help us to
	 * create a flight on the GMT Time
	 * 
	 * @param days and date
	 * @return
	 */
	public static String getPSTtime(String date, int days) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("" + date + "'T'HH:mm:ss'Z'");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		cal.setTime(new Date());
		cal.add(Calendar.HOUR_OF_DAY, days);
		cal.getTime();
		String text = sdf.format(cal.getTime());
		System.out.println(sdf.format(cal.getTime()));
		return text;
	}

	/**
	 *
	 * @param messageToLog what are the message we passing that is printing in
	 *                     console
	 */
	public static void logMessage(String messageToLog) {

		try {
			System.out.println(messageToLog);
			getExtentTest().log(Status.INFO, MarkupHelper.createLabel(messageToLog, ExtentColor.GREEN));
		} catch (Exception e) {
		}
	}

	/**
	 *
	 * @param messageToLog errors that is printing in console
	 */
	public static void logErrorMessage(String messageToLog) {

		try {
			System.err.println(messageToLog);
			if (getErrorLogCount() == 0) {
                scenarioComments.set(messageToLog);
                scenarioNo.set(getdata("Iteration"));
                scenarioName.set(CommonFunctions.getdata("ScenarioTitle"));
                getScenariosCount().add(CommonFunctions.getdata("Iteration"));
                scenarioDescription.set(getdata("Scenario"));
                scenarioStatus.set("Failed");
                failure++;
                errorLogCount.set(getErrorLogCount() + 1);
            } else {
                scenarioComments.set(messageToLog);
            }
			getExtentTest().log(Status.FAIL, MarkupHelper.createLabel(messageToLog, ExtentColor.RED));
		} catch (Exception e) {
		}
	}

	/**
	 * This method for prints the error messages and stop the execution
	 *
	 * @param messageToLog this is for print the error message and stop the
	 *                     execution
	 * @throws Exception
	 */
	public static void logErrorMessageStopExecution(String messageToLog) throws Exception {
		boolean flag = false;
		if (!getDriver().toString().contains("(null)")) {
			flag = new CommonFailures(getDriver()).commonErrorsCaptures();
		}
		if (!flag) {
			logErrorMessage(messageToLog);
			throw new Exception();
		}
	}

	/**
	 * @This method for prints the skip messages and skip the execution
	 * 
	 * @param messageToLog this is for print the error message and stop the
	 *                     execution
	 */
	public static void logMessageSkipExecution(String messageToLog) {
		scenarioComments.set(messageToLog);
		System.err.println(messageToLog);
		getExtentTest().log(Status.SKIP, MarkupHelper.createLabel(messageToLog, ExtentColor.ORANGE));
		throw new SkipException(messageToLog);

	}

	/**
	 * @author Mohammed Mustafa This method for scrolling the webpage for the
	 *         webelement specified
	 * 
	 * @param ele - the element to be scrolled to view
	 * @throws Exception
	 */
	public static void scrollIntoView(WebElement ele) {
		try {
			((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", ele);
		} catch (Exception e) {
			CommonFunctions.logErrorMessage("Exception while scrolling the window");
		}
	}

	/**
	 * @author Mohammed Mustafa This method for take the screenshot for failed test
	 *         cases
	 * 
	 * @param msg it is for storing the screenshot with the error message
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static String getScreenShot() throws Exception {
		Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
				.takeScreenshot(getDriver());
		String imgPath = ExtentManager.getFilePath().replace("\\eMed.html", "").replace("/eMed.html", "") + "\\";
		String imageName = "image-" + getTestThreadId() + "_" + currentMilliseconds() + ".png";
		String screenShotPath = imgPath + imageName;
		try {

			ImageIO.write(screenshot.getImage(), "PNG", new File(screenShotPath));
		} catch (IOException e) {
			System.err.println("Screen Shot failed " + e.getMessage());
		}

		return imageName;
	}

	/**
	 * This method for generating current milliseconds
	 * 
	 * @return
	 */
	public static long currentMilliseconds() throws Exception {

		Date date = new Date();
		SimpleDateFormat DateFor = new SimpleDateFormat();
		long currentMillis = timeMillis(DateFor.format(date));
		return currentMillis;
	}

	/**
	 * This method for generating the milliseconds for the given date and time
	 * 
	 * @throws Exception
	 * @return
	 */
	public static long timeMillis(String dateStr) throws Exception {

		DateFormat dateFormat = new SimpleDateFormat();
		Date date = dateFormat.parse(dateStr);
		long millis = date.getTime();
		return millis;
	}

	/**
	 * This method for action click
	 * 
	 * @param webele
	 * @param Ele_name
	 * 
	 * @throws Exception
	 **/

	public static void actionClick(WebElement webele, String Ele_name) throws Exception {
		try {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.elementToBeClickable(webele));
			Actions ob = new Actions(getDriver());
			Action action = ob.moveToElement(webele).click().build();
			action.perform();
			logMessage("The " + Ele_name + " is clicked");
		} catch (Exception e) {
			logErrorMessageStopExecution("The " + Ele_name + " is not avilable pls refer the report\n");
		}
	}

	/**
	 * This method for Clicking the Web element
	 * 
	 * @throws Exception
	 **/
	public static void clickWebelement(WebElement webele, String Ele_name) throws Exception {
		try {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.elementToBeClickable(webele));
			webele.click();
			logMessage("The " + Ele_name + " is clicked");
		} catch (Exception e) {
			logErrorMessageStopExecution("The " + Ele_name + " is not avilable pls refer the screenshot\n");
		}
	}

	/**
	 * This method for Sending the values to the Web element
	 * 
	 * @throws Exception
	 **/
	public static void Sendkeys(WebElement webele, String value, String Ele_name) throws Exception {
		try {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.visibilityOf(webele));
			webele.click();
			webele.clear();
			webele.sendKeys(value);
			logMessage(value + " is entered into the " + Ele_name + " textbox");
		} catch (Exception e) {
			logErrorMessageStopExecution("\nThe " + Ele_name + " is not avilable pls refer the screenshot\n");
		}
	}

	/**
	 * @author Mohammed Mustafa
	 *
	 * @apiNote This method for Sending the values to the Web element by clearing
	 *          field using attribute
	 *
	 * @param webele
	 * @param attrib
	 * @param value
	 * @param Ele_name
	 *
	 * @throws Exception
	 **/
	public static void SendkeysAttrib(WebElement webele, String attrib, String value, String Ele_name)
			throws Exception {
		try {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.visibilityOf(webele));
			webele.click();
			while (!webele.getAttribute(attrib).equals("")) {
				webele.sendKeys(Keys.BACK_SPACE);
			}
			webele.sendKeys(value);
			logMessage(value + " is entered into the " + Ele_name + " textbox");
		} catch (Exception e) {
			logErrorMessageStopExecution("\nThe " + Ele_name + " is not avilable pls refer the screenshot\n");
		}

	}

	/**
	 * 
	 * @author Mohammed Mustafa
	 * 
	 * @apiNote This method for Sending the values to the Web element by clearing
	 *          field using delete all key press
	 * 
	 * @throws Exception
	 **/
	public static void sendKeysWithDeleteAll(WebElement webele, String value, String Ele_name) throws Exception {
		try {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.visibilityOf(webele));
			while (!webele.getAttribute("value").equals("")) {
				if (System.getProperty("os.name").contains("OS X")) {
					webele.sendKeys(Keys.COMMAND + "a");
				} else {
					webele.sendKeys(Keys.CONTROL + "a");
				}
				Thread.sleep(500);
				webele.sendKeys(Keys.DELETE);
			}
			webele.sendKeys(value);
			logMessage(value + " is entered into the " + Ele_name + " textbox");
		} catch (Exception e) {
			logErrorMessageStopExecution("\nThe " + Ele_name + " is not avilable pls refer the screenshot\n");
		}
	}

	/**
	 * @author Mohammed Mustafa This method for Sending the values to the Web
	 *         element and without any log message
	 * 
	 * @throws Exception
	 **/
	public static void SendkeysWithoutInputLog(WebElement webele, String value, String Ele_name) throws Exception {
		try {
			new WebDriverWait(getDriver(), 20).until(ExpectedConditions.visibilityOf(webele));
			webele.click();
			webele.clear();
			webele.sendKeys(value);
			logMessage("Authentic " + Ele_name + " is entered into the " + Ele_name + " textbox");
		} catch (Exception e) {
			logErrorMessageStopExecution("\nThe " + Ele_name + " is not avilable pls refer the report\n");
		}

	}

	/**
	 * @author Mohammed Mustafa This method for selecting the dropdown value using
	 *         select class
	 * 
	 * @throws Exception
	 **/
	public static void selectDropDownValue(WebElement webele, String value, String Ele_name) throws Exception {
		try {
			new WebDriverWait(getDriver(), 20).until(ExpectedConditions.visibilityOf(webele));
			Select select = new Select(webele);
			select.selectByValue(value);
			logMessage(value + " is selected into the " + Ele_name + " dropdown");
		} catch (Exception e) {
			logErrorMessageStopExecution("\nThe " + Ele_name + " is not avilable pls refer the report\n");
		}

	}

	/**
	 * @author Mohammed Mustafa This method for selecting the dropdown text using
	 *         select class
	 * 
	 * @throws Exception
	 **/
	public static void selectDropDownText(WebElement webele, String value, String Ele_name) throws Exception {
		try {
			new WebDriverWait(getDriver(), 20).until(ExpectedConditions.visibilityOf(webele));
			Select select = new Select(webele);
			select.selectByVisibleText(value);
			logMessage(value + " is selected into the " + Ele_name + " dropdown");
		} catch (Exception e) {
			logErrorMessageStopExecution("\nThe " + Ele_name + " is not avilable pls refer the report\n");
		}

	}

	/**
	 * This method for checking the page is displayed or Not
	 * 
	 * @param webele We have to pass the web element to check that is Clickable or
	 *               not
	 * @return
	 */
	public static boolean checkCurrentPage(WebElement webele) {
		try {
			new WebDriverWait(getDriver(), 7).until(ExpectedConditions.elementToBeClickable(webele));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This Method for checking the page is loaded or not and return the boolean
	 * value true or false
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void waitForPageLoad(WebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}

		};
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(pageLoadCondition);
	}

	/**
	 * This method for getting the data from the hash map and returns the value
	 * 
	 * @param Name It is the name of the column
	 * @return
	 * @throws Exception
	 */
	public static String getdata(String Name) throws Exception {

		String data = "";
		if (DataReader.hashmap().containsKey(Name)) {
			data = DataReader.hashmap().get(Name);
		} else {
			CommonFunctions.logErrorMessageStopExecution("Given Column name is not available in the Excel " + Name);
		}
		return data;

	}

	/*
	 * This method for generating the Email randomly based on timestampMilliseconds
	 */
	public static String EmailGenTimeStamp(String labelName, String domainName) {
		SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy-hhmmsss");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 0);
		String eMail = labelName + sdf.format(cal.getTime()) + "-" +getTestThreadId() +"@" + domainName;
		
	/*	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String eMail = labelName + timestamp.getTime() + "@" + domainName;*/
		return eMail;
	}

	/**
	 * This method for switching the one window to next window
	 * 
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void switchNextWindow(WebDriver driver) throws InterruptedException {
		Thread.sleep(4000);
		int i = 0;
		for (String Child_Window : driver.getWindowHandles()) {
			if (i > 0) {
				driver.switchTo().window(Child_Window);
			}
			i++;
		}
	}

	/**
	 * This Method for checking the web element is visible or not
	 * 
	 * @param webele pass the web element.
	 * @param name   we have to pass the name of the element if it is not displayed
	 *               it prints the error message
	 * @throws Exception
	 */
	public static boolean elementIsVisible(WebElement webele) throws Exception {
		try {
			new WebDriverWait(getDriver(), 20).until(ExpectedConditions.visibilityOf(webele));
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	/**
	 * This Method for checking the web element is invisible or not
	 * 
	 * @param webele pass the web element.
	 * 
	 * @throws Exception
	 */
	public static boolean elementIsInVisible(WebElement webele) throws Exception {
		try {
			new WebDriverWait(getDriver(), 15).until(ExpectedConditions.invisibilityOf(webele));
			return false;
		} catch (Exception e) {
			return true;
		}

	}

	/**
	 * This Method for checking the web element is clickable or not
	 * 
	 * @param ele     pass the web element.
	 * @param objname we have to pass the name of the element if it is not displayed
	 *                it prints the error message
	 * @throws Exception
	 */
	public static void elementToBeClickable(WebElement ele, String objname) throws Exception {

		try {
			new WebDriverWait(getDriver(), 20).until(ExpectedConditions.elementToBeClickable(ele));
		} catch (Exception e) {
			logErrorMessageStopExecution("The object " + objname + " is not clickable");
		}
	}

	/**
	 * @author Mohammed Mustafa This method for checks for element exist or not
	 * 
	 * @return
	 */
	public static boolean isExist(WebDriver driverPass, String string) throws Exception {
		boolean check = false;
		try {
			WebDriverWait wait = new WebDriverWait(driverPass, 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(string)));
			wait.ignoring(NoSuchElementException.class);
			check = true;
		} catch (TimeoutException e) {
			check = false;
		}
		return check;
	}

	/**
	 * This method for converting the date from one format to another format
	 * 
	 * @param OldFormat   We have to pass the current date format dd/MM/YYYY
	 * @param NewFormat   We have to pass the Expected date format MM/dd/YYYY
	 * @param PaasingDate We have to pass the date
	 * @return
	 * @throws Exception
	 */
	public static String convertDateOneFormatToAnother(String OldFormat, String NewFormat, String PaasingDate)
			throws Exception {
		try {
			String result = "";
			SimpleDateFormat OldDateFormate = new SimpleDateFormat(OldFormat);
			SimpleDateFormat NewDateFormate = new SimpleDateFormat(NewFormat);
			result = NewDateFormate.format(OldDateFormate.parse(PaasingDate));
			return result;
		} catch (Exception e) {
			logErrorMessageStopExecution("Error While converting the date pls check the format of the date");
			return PaasingDate;
		}

	}

	/**
	 * This method for checking the page is displayed or Not using page title
	 *
	 *
	 * @throws Exception
	 */
	public static void checkCurrentPageTitle(String pageName) throws Exception {
		try {
			if (!getDriver().getTitle().contains(pageName)) {
				waitForPageLoad(getDriver());
			}
		} catch (Exception e) {
			logErrorMessageStopExecution("Failed due to page not loaded");
		}
	}

	/**
	 * This method for checking the page is displayed or Not
	 * 
	 * @param webele We have to pass the web element to check that is Clickable or
	 *               not
	 * @throws Exception
	 */
	public static void checkCurrentPage(WebElement webele, String pageName) throws Exception {
		try {
			new WebDriverWait(getDriver(), 10).until(ExpectedConditions.visibilityOf(webele));
			if (!webele.getText().contains(pageName)) {
				waitForPageLoad(getDriver());
			}
		} catch (Exception e) {
			logErrorMessageStopExecution("Failed due to page not loaded");
		}
	}

	/**
	 * This method for checking the passing dynamic xpath is available or not and
	 * returns the web element element
	 * 
	 * @param driver
	 * @param attributevalue We have to pass the dynamic xpath to check if it is
	 *                       available or not
	 * @param objName        we have to pass the name of the element if it is not
	 *                       displayed it prints the error message
	 * @return
	 * @throws Exception
	 */
	public static WebElement findElementByXpath(WebDriver driver, String attributevalue, String objName)
			throws Exception {
		WebElement element = null;
		try {
			element = driver.findElement(By.xpath(attributevalue));
			return element;
		} catch (Exception ex) {
			CommonFunctions.logErrorMessageStopExecution("The Object " + objName + " is not available.");
			return null;
		}
	}

	/**
	 * @param webele
	 * @param objName
	 * @throws Exception
	 */
	public static String getTextOfElement(WebElement webele, String objName) throws Exception {
		String elementText = null;
		try {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.visibilityOf(webele));
			elementText = webele.getText();
		} catch (Exception e) {
			logErrorMessageStopExecution("The Object " + objName + " is not available.");
		}
		return elementText;
	}

	/**
	 * @param webele
	 * @param objName
	 * @param attributeName This method will Get the Attribute of the Element
	 * @return
	 */
	public static String getAttributeOfElement(WebElement webele, String objName, String attributeName) {
		String element = null;
		try {
			new WebDriverWait(getDriver(), 30).until(ExpectedConditions.visibilityOf(webele));
			element = webele.getAttribute(attributeName);
		} catch (Exception e) {
			logErrorMessage("The Object " + objName + " is not available.");
		}
		return element;
	}

	/**
	 * This method for Switching from one frame another frame using frame name
	 * 
	 *
	 * @param framename We have to pass the frameId to check if it is available or
	 *                  not
	 * @return
	 * @throws Exception
	 */
	public static void switchFrame(String framename) {

		try {
			getDriver().switchTo().defaultContent();
			getDriver().switchTo().frame(framename);
		} catch (Throwable ex) {
			System.out.println("Error occured while switching the frame");
			System.out.println(ex.getMessage());
		}
	}

	/**
	 * This Method for checking the web element is visible or not
	 * 
	 * @param attributeValue pass the web element.
	 * @param name           we have to pass the name of the element if it is not
	 *                       displayed it prints the error message
	 * @throws Exception
	 */
	@SuppressWarnings("unused")
	public static boolean dynamicElementIsVisible(String attributeValue, String name) throws Exception {
		WebElement element = null;
		try {
			element = getDriver().findElement(By.xpath(attributeValue));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * This Method for generating random string
	 * 
	 * @param chars we have to pass the number of characters to generate random
	 *              string
	 * 
	 */

	public static String getRamdonString(int chars) {
		String CharSet = "abcdefghijkmnopqrstuvwxyz";
		String name = "";
		for (int a = 1; a <= chars; a++) {
			name += CharSet.charAt(new Random().nextInt(CharSet.length()));
		}
		return name;
	}

	/**
	 * This Method for switch to the Active Element
	 */

	public static void switchToActiveElement() {
		getDriver().switchTo().activeElement();
	}

	/**
	 * @param password --> Send the Encoded password
	 * @return It's return the decoded Password This method is used for decode the
	 *         password and return to the application
	 */
	public static String decodepass(String password) {
		String token = null;
		if (!password.equals(null))
			token = new String(Base64.decodeBase64(password.getBytes()));

		if (!token.equals(null))
			password = token;
		return password;

	}

	public static void typeTextWithTimeOut(WebDriver driver, WebElement locator, String text, Integer timeOut) {
		try {
			WebDriverWait wait = new WebDriverWait(driver, timeOut);
			wait.until(ExpectedConditions.elementToBeClickable(locator));

			Actions builder = new Actions(driver);
			Action seriesOfActions = builder.moveToElement(locator).click().sendKeys(locator, text).click()
					.sendKeys(locator, Keys.TAB).build();
			seriesOfActions.perform();
			logMessage(text + " is entered into the " + locator + " textbox");
		} catch (ElementNotVisibleException e) {
			logErrorMessage(String.format("Expected element %s was not found", locator.toString()));
		}
	}

	public static void typeText(WebDriver driver, WebElement locator, String text, String Ele_name) {
		try {
			Actions builder = new Actions(driver);
			Action seriesOfActions = builder.moveToElement(locator).click().sendKeys(locator, text).click()
					.sendKeys(locator, Keys.TAB).build();
			seriesOfActions.perform();
			logMessage(text + " is entered into the " + Ele_name + " textbox");
		} catch (ElementClickInterceptedException e) {
			logErrorMessage(String.format("Expected element %s was not found", locator.toString()));
		}
	}

	/**
	 * This method for clicking the element using javascript click
	 *
	 * @throws WebDriverException
	 */
	public static void clickJSE(WebElement ele, String elementName) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			wait.until(ExpectedConditions.visibilityOf(ele));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].click();", ele);
			logMessage("iClick on the element: " + elementName);
		} catch (WebDriverException e) {
			logErrorMessage("i failed to Click on the element: " + elementName);
		}
	}
	
	/**
	 * This method clicks the element by passing locator as string using javascript executor
	 * 
	 * @param ele
	 * @param elementName
	 * 
	 * @throws WebDriverException
	 */
	public static void clickJSEString(String ele, String elementName) {
		try {
			new WebDriverWait(getDriver(), 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath(ele)));
			WebElement webele = getDriver().findElement(By.xpath(ele));
			jse = (JavascriptExecutor) getDriver();
			jse.executeScript("arguments[0].click();", webele);
			logMessage("iClick on the element: " + elementName);
		} catch (WebDriverException e) {
			e.printStackTrace();
			logErrorMessage("i failed to Click on the element: " + elementName);
		}
	}

	/**
	 * @author Mohammed Mustafa This method used to iterate string on given
	 *         condition and click the element
	 * @exception Exception
	 **/
	public static void iterateElementClick(List<WebElement> webele, String cond) throws Exception {
		boolean flag = false;
		for (int dte = 0; dte < webele.size(); dte++) {
			// System.out.println(webele.get(dte).getText());
			if (webele.get(dte).getText().contains(cond)) {
				Thread.sleep(300);
				logMessage(webele.get(dte).getText() + " is selected");
				webele.get(dte).click();
				flag = true;
				break;
			}
		}
		if (!flag) {
			logErrorMessageStopExecution("Element " + cond + " searched is not available");
		}
	}

	/**
	 * @author Amirudeen
	 * 
	 * @This method is to iterate list of String with expected condition using
	 *       equalsIgnoreCase
	 * 
	 * @param webele
	 * 
	 * @param cond
	 * 
	 * @throws Exception
	 * 
	 */
	public static void iterateElementClickIgnoreCase(List<WebElement> webele, String cond) throws Exception {
		boolean flag = false;
		for (int dte = 0; dte < webele.size(); dte++) {
			// System.out.println(webele.get(dte).getText());
			if (webele.get(dte).getText().equalsIgnoreCase(cond)) {
				Thread.sleep(300);
				logMessage(webele.get(dte).getText() + " is selected");
				webele.get(dte).click();
				flag = true;
				break;
			}
		}
		if (!flag) {
			logErrorMessageStopExecution("Element " + cond + " searched is not available");
		}
	}

	/**
	 * @author Mohammed Mustafa
	 * 
	 * @apiNote Method is used to enter the string individually by character
	 * 
	 * @param ele         --> WebElement
	 * @param elementName --> String that to be individually entered
	 * @param strName     --> String to be printed as output
	 */
	public static void sendKeysIndividual(WebElement ele, String elementName, String strName) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), 10);
			wait.until(ExpectedConditions.visibilityOf(ele));
			wait.until(ExpectedConditions.elementToBeClickable(ele));
			ele.click();
			for (int i = 0; i < elementName.length(); i++) {
				String strChar = new StringBuilder().append(elementName.charAt(i)).toString();
				ele.sendKeys(strChar);
			}
			logMessage(elementName + " is entered into the " + strName + " textbox");
		} catch (Exception e) {
			logErrorMessage("\nThe " + strName + " is not avilable pls refer the screenshot\n");
		}
	}
	
	/**
	 * 
	 * This method is used to load the new page in current browser tab.
	 * @param url
	 * @param driver
	 * @throws InterruptedException
	 */
	public static void openNewPage(String url, WebDriver driver) throws InterruptedException {
		driver.get(url);
		logMessage("Navigated to " + url);
		
	}

	/**
	 * @author Mohammed Mustafa This method is to invoke new webpage in new tab
	 * @param url    --> url to be invoked
	 * @param driver --> driver value for the current invoked driver
	 */
	public static void openNewBrowserTab(String url, WebDriver driver) throws InterruptedException {
		((JavascriptExecutor) driver).executeScript("window.open('" + url + "')");
		logMessage("Navigated to " + url);
		switchNextWindow(driver);
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
	}

	/**
	 * @author Mohammed Mustafa This method is to switch browser tab for a specified
	 *         index
	 * @param window --> window index value to be switched
	 * @param driver --> driver value for the current invoked driver
	 */
	public static void moveToSpecifiedWindow(WebDriver driver, int window) {
		Set<String> winSet = driver.getWindowHandles();
		List<String> winList = new ArrayList<String>(winSet);
		String newTab = winList.get(window);
		driver.switchTo().window(newTab);
	}

	/**
	 *
	 * This Method is used for Copy clipboard using robot key
	 * 
	 * @param strName --> text to be copied
	 */

	public static void robotKeyClipboard(String text) throws AWTException {
		StringSelection stringSelection = new StringSelection(text);
		Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
		clipboard.setContents(stringSelection, stringSelection);

		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	}

	/**
	 *
	 * This Method is used for Browser authenticate using robot key
	 * 
	 * @param strName --> Name of the userName
	 * @param strName --> Password
	 */

	public static void robotBrowserAuthenticate(String userName, String password)
			throws InterruptedException, AWTException {
		Thread.sleep(5000);
		Robot robot = new Robot();
		CommonFunctions.robotKeyClipboard(userName);
		CommonFunctions.logMessage("Basic authorization username entered");
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_TAB);
		Thread.sleep(1000);
		CommonFunctions.robotKeyClipboard(password);
		CommonFunctions.logMessage("Basic authorization password entered");
		Thread.sleep(1000);
		robot.keyPress(KeyEvent.VK_ENTER);
		Thread.sleep(500);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_TAB);
	}

	/**
	 * @author Mohammed Mustafa This method for kill the all opened browsers
	 * 
	 * @throws IOException
	 */
	public static void killBrowser() throws IOException {
		try {
			String os = System.getProperty("os.name");
			CommonFunctions.logMessage("OS Name - " + os);
			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
				Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
				Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			}
			if (os.contains("Mac")) {
				Runtime.getRuntime().exec("pkill firefox");
				Runtime.getRuntime().exec("pkill safari");
				Runtime.getRuntime().exec("pkill chrome");
			}
			CommonFunctions.logMessage("Killing the browsers which already opened...");
			Thread.sleep(2000);
		} catch (Exception e) {
			CommonFunctions.logErrorMessage("Exception while closing the browsers");
		}
	}

	/**
	 * @author Mohammed Mustafa This method for fetching the values from the
	 *         Credentials properties file
	 * 
	 * @throws IOException
	 */
	public static Properties getPropertyValues() {

		File file = new File(System.getProperty("user.dir") + "/src/main/resources/Credentials.properties");

		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		Properties prop = new Properties();

		// loads properties file
		try {
			prop.load(fileInput);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;
	}

	/**
	 * @author Mohammed Mustafa This method for store the text month in hash map and
	 *         get by passing key value of month in number
	 * 
	 * @param keyMonthNo
	 * 
	 **/
	public static String monthHashMap(String keyMonthNo) {
		HashMap<String, String> month = new HashMap<String, String>();
		month.put("01", "January");
		month.put("02", "February");
		month.put("03", "March");
		month.put("04", "April");
		month.put("05", "May");
		month.put("06", "June");
		month.put("07", "July");
		month.put("08", "August");
		month.put("09", "September");
		month.put("10", "October");
		month.put("11", "November");
		month.put("12", "December");
		return month.get(keyMonthNo);

	}

	/**
	 * @author Mohammed Mustafa
	 * 
	 * @apiNote This method is for rounding off the float value based on the decimal
	 *          places
	 * 
	 * @param floatValue
	 * @param places
	 * @return
	 */
	public static float roundFloatAfterDecimal(float floatValue, int places) {

		BigDecimal bigDecimal = new BigDecimal(Float.toString(floatValue));
		bigDecimal = bigDecimal.setScale(places, RoundingMode.HALF_UP);
		return bigDecimal.floatValue();
	}

	/**
	 * @author Mohammed Mustafa
	 * 
	 * @apiNote Method used to extract the value from the string using the regex
	 *          pattern
	 * 
	 * @throws Exception
	 * @param regexPattern
	 * @param matchStr
	 * 
	 * @return
	 */
	public static String regexText(String regexPattern, String matchStr) throws Exception {
		String result = "";
		try {
			Pattern regex = Pattern.compile(regexPattern);
			Matcher match = regex.matcher(matchStr);
			if (match.find()) {
				result = match.group(0);
			}
			return result;
		} catch (Exception e) {
			CommonFunctions
					.logErrorMessageStopExecution("Error while regexing/mismatch with text and regex pattern given");
		}
		return result;
	}

	/**
	 * @author Mohammed Mustafa
	 *
	 *         This method is used to move the file to specific path
	 *
	 * @param srcPath
	 * @param targetPath
	 *
	 * @throws {@link NoSuchFileException}, {@link DirectoryNotEmptyException},
	 *                {@link IOException}
	 */

	public static void moveFileToSpecificPath(String srcPath, String targetPath) {
		try {
			Path source = Paths.get(srcPath);
			Path target = Paths.get(targetPath);
			Files.move(source, target);
		} catch (NoSuchFileException x) {
			System.err.format("No such file exist");
		} catch (DirectoryNotEmptyException x) {
			System.err.format("Directory is empty");
		} catch (IOException x) {
			System.err.println(x);
		}
	}

	/**
	 * @author Mohammed Mustafa
	 * 
	 *         This Method conatins US states name and their corresponding code in
	 *         Map
	 * @param keyStateName
	 * @return
	 * @throws Exception
	 */
	public static String getUSStateCode(String keyStateName) throws Exception {
		Map<String, String> states = new HashMap<String, String>();
		states.put("Alabama", "AL");
		states.put("Alaska", "AK");
		states.put("Alberta", "AB");
		states.put("American Samoa", "AS");
		states.put("Arizona", "AZ");
		states.put("Arkansas", "AR");
		states.put("Armed Forces (AE)", "AE");
		states.put("Armed Forces Americas", "AA");
		states.put("Armed Forces Pacific", "AP");
		states.put("British Columbia", "BC");
		states.put("California", "CA");
		states.put("Colorado", "CO");
		states.put("Connecticut", "CT");
		states.put("Delaware", "DE");
		states.put("District Of Columbia", "DC");
		states.put("Florida", "FL");
		states.put("Georgia", "GA");
		states.put("Guam", "GU");
		states.put("Hawaii", "HI");
		states.put("Idaho", "ID");
		states.put("Illinois", "IL");
		states.put("Indiana", "IN");
		states.put("Iowa", "IA");
		states.put("Kansas", "KS");
		states.put("Kentucky", "KY");
		states.put("Louisiana", "LA");
		states.put("Maine", "ME");
		states.put("Manitoba", "MB");
		states.put("Maryland", "MD");
		states.put("Massachusetts", "MA");
		states.put("Michigan", "MI");
		states.put("Minnesota", "MN");
		states.put("Mississippi", "MS");
		states.put("Missouri", "MO");
		states.put("Montana", "MT");
		states.put("Nebraska", "NE");
		states.put("Nevada", "NV");
		states.put("New Brunswick", "NB");
		states.put("New Hampshire", "NH");
		states.put("New Jersey", "NJ");
		states.put("New Mexico", "NM");
		states.put("New York", "NY");
		states.put("Newfoundland", "NF");
		states.put("North Carolina", "NC");
		states.put("North Dakota", "ND");
		states.put("Northwest Territories", "NT");
		states.put("Nova Scotia", "NS");
		states.put("Nunavut", "NU");
		states.put("Ohio", "OH");
		states.put("Oklahoma", "OK");
		states.put("Ontario", "ON");
		states.put("Oregon", "OR");
		states.put("Pennsylvania", "PA");
		states.put("Prince Edward Island", "PE");
		states.put("Puerto Rico", "PR");
		states.put("Quebec", "QC");
		states.put("Rhode Island", "RI");
		states.put("Saskatchewan", "SK");
		states.put("South Carolina", "SC");
		states.put("South Dakota", "SD");
		states.put("Tennessee", "TN");
		states.put("Texas", "TX");
		states.put("Utah", "UT");
		states.put("Vermont", "VT");
		states.put("Virgin Islands", "VI");
		states.put("Virginia", "VA");
		states.put("Washington", "WA");
		states.put("West Virginia", "WV");
		states.put("Wisconsin", "WI");
		states.put("Wyoming", "WY");
		states.put("Yukon Territory", "YT");
		String stateCode = states.get(keyStateName);
		if (stateCode == null) {
			logErrorMessageStopExecution(
					"No US state name matched in the list provided\nCheck the state name provided ===> "
							+ keyStateName);
		}
		return stateCode;
	}

	/**
	 * @author Mohammed Mustafa This Method for checking the web element is visible
	 *         or not for given timer
	 *
	 * @param webele  pass the web element.
	 * @param timeOut Timeout for the element to visible
	 *
	 * @return boolean value
	 * @throws Exception
	 */
	public static boolean elementVisibleToCheck(WebElement webele, int timeOut) throws Exception {
		try {
			new WebDriverWait(getDriver(), timeOut).until(ExpectedConditions.visibilityOf(webele));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 
	 * @author Mohammed Mustafa
	 * 
	 *         Method uses robot framework to do keyboard move click on element
	 * 
	 * @param webele - Element to be clicked
	 * 
	 * @exception @throws Exception
	 */
	public static void robotKeyMoveClick(WebElement webele, int clicks, String eleName) throws Exception {
		try {
			new WebDriverWait(getDriver(), 20).until(ExpectedConditions.visibilityOf(webele));
			Thread.sleep(1000);
			Robot robot = new Robot();
			for (int click = 0; click < clicks; click++) {
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
			}
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			logMessage(eleName + " is clicked");
		} catch (Exception e) {
			logErrorMessageStopExecution("Error while clicking the element " + eleName + " using robot mouse click");
		}
	}

	/**
	 * 
	 * @author Mohammed Mustafa
	 * 
	 * @This Method capitalize the string case provided
	 * 
	 * @param str - String to be capitalize
	 * 
	 */
	public static String capitalize(String str) {
		if (str == null || str.isEmpty()) {
			return str;
		}
		return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
	}

	/**
	 * 
	 * @author Mohammed Mustafa
	 * 
	 * @This Method to click the keyboard num pad using selenium key presses
	 *       function
	 * 
	 * @param webEle
	 * @param numKeys
	 * @throws Exception
	 */
	public static void selNumKeyPress(WebElement webEle, String numKeys, String eleName) throws Exception {

		try {
			elementIsVisible(webEle);
			for (String eachKey : numKeys.split("")) {
				switch (Integer.parseInt(eachKey)) {

				case 0:
					webEle.sendKeys(Keys.NUMPAD0);
					break;

				case 1:
					webEle.sendKeys(Keys.NUMPAD1);
					break;

				case 2:
					webEle.sendKeys(Keys.NUMPAD2);
					break;

				case 3:
					webEle.sendKeys(Keys.NUMPAD3);
					break;

				case 4:
					webEle.sendKeys(Keys.NUMPAD4);
					break;

				case 5:
					webEle.sendKeys(Keys.NUMPAD5);
					break;

				case 6:
					webEle.sendKeys(Keys.NUMPAD6);
					break;

				case 7:
					webEle.sendKeys(Keys.NUMPAD7);
					break;

				case 8:
					webEle.sendKeys(Keys.NUMPAD8);
					break;

				case 9:
					webEle.sendKeys(Keys.NUMPAD9);
					break;

				default:
					break;
				}
				Thread.sleep(300);
			}
			logMessage(numKeys + " is entered into the " + eleName + " textbox");
		} catch (Exception e) {
			logErrorMessageStopExecution("\nThe " + eleName + " is not avilable pls refer the screenshot\n");
		}

	}

	/**
	 * 
	 * @Method to convert the text response into JSON
	 * 
	 * @param textString
	 * @return
	 * 
	 */
	public static JSONObject restConvertTextAsJson(String textString) {
		return new JSONObject(textString);
	}

	/**
	 * 
	 * @This method is used to convert json array string to json array object and
	 *       get all the array value in list
	 * 
	 * @param jsonPath
	 * @param jsonString
	 * 
	 * @return List<String>
	 */
	public static List<String> getArrayValueFromJSON(String jsonString, String jsonPath) {

		JSONObject jsonArray = restConvertTextAsJson(jsonString);
		List<String> jsonStr = new LinkedList<String>();

		for (int i = 0; i < jsonArray.getJSONArray(jsonPath).length(); i++) {
			jsonStr.add(jsonArray.getJSONArray(jsonPath).get(i).toString());
		}
		return jsonStr;
	}

	/**
	 * 
	 * @Method to get the correlate parameter value from the array object by giving
	 *         rest response object as input
	 * 
	 * @param response
	 * @param jsonPath
	 * @return
	 * 
	 */
	public static String restCorrelateJSON(String jsonString, String jsonPath) {

		boolean flag = true;
		JSONObject jsonObj = null;
		Iterator<String> jsonItr = null;

		String[] jsonPathSplit = jsonPath.split(Pattern.quote("."));

		for (String matchKey : jsonPathSplit) {

			if (matchKey.contains("[")) {
				jsonObj = restConvertTextAsJson(jsonString);
				int strLen = matchKey.length();
				jsonString = jsonObj.getJSONArray(matchKey.replaceAll("\\[\\d\\]", ""))
						.getJSONObject(Integer.parseInt(matchKey.substring(strLen - 2, strLen - 1))).toString();
			}

			jsonObj = restConvertTextAsJson(jsonString);
			jsonItr = jsonObj.keys();

			while (jsonItr.hasNext()) {
				String keyvalue = jsonItr.next().toString();
				if (keyvalue.equals(matchKey)) {
					jsonString = jsonObj.get(keyvalue).toString();
					flag = false;
					break;
				}
			}
		}
		
		if (flag) {
			System.err.println("No value found");
		}

		return jsonString;

	}

	/**
	 * 
	 * @This Method to convert a byte array to a hexadecimal string.
	 * 
	 * @param bytes
	 * @return
	 * 
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		for (byte hashByte : bytes) {
			int intVal = 0xff & hashByte;
			if (intVal < 0x10) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(intVal));
		}
		return sb.toString();
	}

	/**
	 * 
	 * @This method to generate the random unique id (UUID) using unique key
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 * 
	 * @return
	 * 
	 */
	public static String randomUUID() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest salt = MessageDigest.getInstance("SHA-256");
		salt.update(UUID.randomUUID().toString().getBytes("UTF-8"));
		String digest = bytesToHex(salt.digest());
		String result = UUID.nameUUIDFromBytes(digest.getBytes()).toString();
		return result;
	}

	/**
	 * 
	 * @This method is used to add the item value to the browser local storage
	 * 
	 * @param key       --> Key of the item
	 * @param itemValue - Value of the item
	 * @throws Exception
	 * 
	 */
	public static void addLocalStorageItem(String key, String itemValue) throws Exception {
		try {
			RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) getDriver());
			RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
			LocalStorage storage = webStorage.getLocalStorage();
			storage.setItem(key, itemValue);
		} catch (Exception e) {
			logErrorMessageStopExecution("Error while adding the item to the browser local storage!");
		}
	}

	/**
	 * 
	 * @This method is used to get the item value from the browser local storage
	 * 
	 * @param key --> Key of the item
	 * @return
	 * @throws Exception
	 * 
	 */
	public static String getLocalStorageItem(String key) throws Exception {
		String itemValue = "";
		try {
			RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) getDriver());
			RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
			LocalStorage storage = webStorage.getLocalStorage();
			storage.getItem(key);
			itemValue = storage.toString();
		} catch (Exception e) {
			logErrorMessageStopExecution("Error while getting the item value from the browser local storage!");
		}
		return itemValue;
	}

	/**
	 * 
	 * @This method is used to remove the item from the browser local storage
	 * 
	 * @param key --> Key of the item
	 * @return
	 * @throws Exception
	 * 
	 */
	public static void removeLocalStorageItem(String key) throws Exception {
		try {
			RemoteExecuteMethod executeMethod = new RemoteExecuteMethod((RemoteWebDriver) getDriver());
			RemoteWebStorage webStorage = new RemoteWebStorage(executeMethod);
			LocalStorage storage = webStorage.getLocalStorage();
			storage.removeItem(key);
		} catch (Exception e) {
			logErrorMessageStopExecution("Error while removing the item from the browser local storage!");
		}
	}

	/**
	 * 
	 * @This method return web-element from the shadow DOM
	 * 
	 * @param driver
	 * @param cssSelector
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static WebElement getShadowDomElement(WebDriver driver, String cssSelector, String objName)
			throws Exception {
		WebElement ele = null;
		try {
			Shadow shadow = new Shadow(driver);
			ele = shadow.findElement(cssSelector);
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("The Object " + objName + " is not available.");
		}
		return ele;
	}

	/**
	 * 
	 * @This method return list of web-element from the shadow DOM
	 * 
	 * @param driver
	 * @param cssSelector
	 * 
	 * @return
	 * 
	 * @throws Exception
	 */
	public static List<WebElement> getShadowDomElements(WebDriver driver, String cssSelector, String objName)
			throws Exception {
		List<WebElement> ele = null;
		try {
			Shadow shadow = new Shadow(driver);
			ele = shadow.findElements(cssSelector);
		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("The Object " + objName + " is not available.");
		}
		return ele;
	}
	
	/***
	 * This method is for page refresh
	 */
	public static void refreshPage(WebDriver driver){
		driver.navigate().refresh();
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}
	
	/**
	 * 
	 * @This method is used to calculate the age from the date of birth
	 * 
	 * @param dob as format - YYYY-MM-DD
	 * @return
	 */
	public static int calculateAgeFromDob(String dob) {
		LocalDate dateOfBirth = LocalDate.parse(dob);  
		LocalDate currentDate = LocalDate.now();  
		int years = Period.between(dateOfBirth, currentDate).getYears();
		return years;
	}
	
	/**
	 * This method is used to verify the title of the current page.
	 * @param driver
	 * @param title
	 */
	public static void verifyPageTitle(WebDriver driver, String title) {
		if (driver.getTitle().equalsIgnoreCase(title)) {
			CommonFunctions.logMessage("The Expected page title matches the Actual page title ==> " + title);

		} else {
			CommonFunctions.logMessage("The Expected page title " + title + " does not matches the Actual page title "
					+ driver.getTitle());
		}
	}
}