package main.java.utils;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import org.openqa.selenium.Proxy;
import main.java.base.TestBase;

public class InvokeBrowser extends TestBase {
	private static DesiredCapabilities dc;

	/**
	 * 
	 * @This method stores all the base url for all the applications automated
	 * 
	 * @param platform
	 * @return
	 * 
	 * @throws Exception
	 * 
	 */
	public static String getBaseUrl(String env) throws Exception {
		String baseurl = "";

		switch (env) {

		case "test":
			baseurl ="https://www.saucedemo.com/v1/";
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("Incorrect platform selected");
			break;
		}
		return baseurl;
	}

	/**
	 * This method for invoke the Browser and pass the url to the browser
	 * 
	 * @param platform We have to pass the channel for invoking like www, proctor
	 *                 ,etc..
	 * 
	 * @throws Exception
	 */
	public static void invokeApplication(String platform) throws Exception {

		String url = getBaseUrl(platform);

		if (getDriver() != null && !getDriver().toString().contains("(null)")) {
			getDriver().quit();
			driver.remove();
			extent.flush();
		}

		try {
			String os = System.getProperty("os.name");
			CommonFunctions.logMessage("OS Name - " + os);

			if (BrowserNeed.toLowerCase().contains("firefox")) {
				if (os.contains("Windows") || os.contains("OS X")) {
					FirefoxOptions firefoxOptions = new FirefoxOptions();
					firefoxOptions.setCapability("browserName", "firefox");
					firefoxOptions.setCapability("marionette", true);
					firefoxOptions.setCapability("acceptInsecureCerts", true);
					firefoxOptions.setCapability("javascriptEnabled", true);
					driver.set(new FirefoxDriver(firefoxOptions));
				} else if (os.contains("Linux")) {
					CommonFunctions
							.logErrorMessageStopExecution("Currently firefox not supported for linux remote jenkins");
				}

			} else if (BrowserNeed.toLowerCase().contains("safari")) {
				if (System.getProperty("os.name").contains("OS X")) {

					System.setProperty("webdriver.safari.driver",
							System.getProperty("user.dir") + "/src/main/resources/drivers/SafariDriver.safariextz");
					driver.set(new SafariDriver());
				} else {
					System.err.println("Safari is only supported on MAC - OS X");
				}

			} else if (BrowserNeed.toLowerCase().contains("chrome")) {

				if (os.contains("Windows") || os.contains("OS X")) {
					ChromeOptions options = new ChromeOptions();
					HashMap<String, Object> chromePref = new HashMap<String, Object>();
					chromePref.put("profile.default_content_settings.popups", 0);
					chromePref.put("download.default_directory", downloadFilePath);
					options.setExperimentalOption("prefs", chromePref);
					options.addArguments("disable-infobars");
					if (mode.equalsIgnoreCase("incog")) {
						options.addArguments("--incognito");
					}
//					options.addExtensions(new File(
//							System.getProperty("user.dir") + "/src/main/resources/ext/extension.crx"));
//					options.addArguments("--no-sandbox");

					options.addArguments("use-fake-ui-for-media-stream");
					options.addArguments("test-type");
					options.addArguments("ignore-certificate-errors");
					options.setAcceptInsecureCerts(true);
					driver.set(new ChromeDriver(options));

				} else if (os.contains("Linux")) {
					ChromeOptions options = new ChromeOptions();
					Map<String, String> chromePreferences = new HashMap<>();
					if (mode.equalsIgnoreCase("incog")) {
						options.addArguments("--incognito");
					}
					options.addArguments("use-fake-ui-for-media-stream");
					options.addArguments("disable-infobars");
					chromePreferences.put("profile.password_manager_enabled", "false");

					Proxy proxy = new Proxy();
					proxy.setProxyType(Proxy.ProxyType.DIRECT);

//					options.addArguments(
//							"user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.124 Safari/537.36");
//					options.addArguments("--headless");
					
//					options.addExtensions(new File(
//								System.getProperty("user.dir") + "/src/main/resources/ext/extension.crx"));
					options.addArguments("--no-sandbox");
					options.addArguments("--disable-setuid-sandbox");
					options.addArguments("--disable-dev-shm-usage");
					options.addArguments("test-type");
					options.addArguments("ignore-certificate-errors");
					options.addArguments("--window-size=1325x744");
					options.setAcceptInsecureCerts(true);

					options.setCapability("chrome.switches", "--no-default-browser-check");
					options.setCapability("chrome.prefs", chromePreferences);
					options.setCapability(CapabilityType.PROXY, proxy);
					options.setAcceptInsecureCerts(true);
					options.setCapability(ChromeOptions.CAPABILITY, options);
					driver.set(new RemoteWebDriver(new URL("http://selenium-hub.selenium:4444/wd/hub"), options));
				}

			} else if (BrowserNeed.toLowerCase().contains("msedge")) {
				if (os.contains("Windows") || os.contains("OS X")) {

					driver.set(new EdgeDriver());
				} else if (os.contains("Linux")) {
					CommonFunctions.logErrorMessageStopExecution(
							"Currently edge browser not supported for linux remote jenkins");
				}
			} else {
				CommonFunctions.logErrorMessageStopExecution(
						"Invalid browser name specified! Please check the browser name provided..");
			}

			CommonFunctions.logMessage("Launched the " + BrowserNeed + " Browser Successfully....");
			getDriver().manage().window().maximize();
			getDriver().get(url);

			
			CommonFunctions.logMessage("Navigated to the site ===> " + url);


		} catch (Exception e) {
			e.printStackTrace();
			CommonFunctions.logErrorMessageStopExecution("Error Browser not invoked");
		}

	}

}