package main.java.utils;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import encryptusercredentials.EncryptCredentails;

import org.openqa.selenium.Proxy;
import io.github.bonigarcia.wdm.WebDriverManager;
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
	public static String getBaseUrl(String platform) throws Exception {
		String baseurl = "";

		switch (platform) {

		case "WWW":

			switch (env.toLowerCase()) {
			case "stg":
			case "dev2":
			case "qa":

				if (BrowserNeed.equalsIgnoreCase("chrome") || BrowserNeed.equalsIgnoreCase("msedge")) {
					baseurl = "https://"
							+ new EncryptCredentails()
									.decrypt(CommonFunctions.getPropertyValues().getProperty("wwwusername"))
							+ ":"
							+ new EncryptCredentails()
									.decrypt(CommonFunctions.getPropertyValues().getProperty("wwwpassword"))
							+ "@" + env + ".emed.com";

				} else if (BrowserNeed.equalsIgnoreCase("safari") || BrowserNeed.equalsIgnoreCase("firefox")) {
					baseurl = "https://" + env + ".emed.com";
				}
				break;

			case "prod":
				baseurl = "https://emed.com/app";
				break;
				
			case "training":
				baseurl = "https://training.emed.com/";
				break;

			default:
				CommonFunctions.logErrorMessageStopExecution("Incorrect environment is selected");
				break;

			}
			break;

		case "AWSConnectProctor":
			baseurl = "https://emed-develop.awsapps.com/connect/home";
			break;

		case "BinaxExpress":
			baseurl = "https://comingsoon.emed.com/express-qa";
			break;

		case "CoreBinaxNow":
			baseurl = "https://stg.core.emed.com/procedure/begin?scope=demo.navica&client_id=emed-qa";
			break;

		case "CoreBinaxExpress":
			baseurl = "https://stg.core.emed.com/procedure/begin?scope=binax_express&client_id=emed-qa";
			break;

		case "CoreQuidel":
			baseurl = "https://stg.core.emed.com/procedure/begin?scope=quidel_covid&client_id=emed-qa";
			break;

		case "CoreBinaxGovt":
			baseurl = "https://stg.core.emed.com/procedure/begin?scope=binax_govt&client_id=emed-qa";
			break;

		case "CoreBinaxOTC":
			baseurl = "https://stg.core.emed.com/procedure/begin?scope=binaxnow_selftest&client_id=emed-qa";
			break;
			
		case "CoreACON":
			baseurl = "https://stg.core.emed.com/procedure/begin?scope=acon&client_id=emed-qa";
			break;
			
		case "CoreBinaxForward":
			baseurl = "https://stg.core.emed.com/procedure/begin?scope=binax_forward&client_id=emed-qa";
			break;
			
		case "CoreLucira":
			baseurl = "https://stg.core.emed.com/procedure/begin?scope=lucira-checkit-covid19-athometest&client_id=emed-qa";
			break;
			
		case "CoreSDBioSensor":
			baseurl = "https://stg.core.emed.com/procedure/begin?scope=sdbiosensor_covid19_athometest&client_id=emed-qa";
			break;
			
		case "CoreiHealth":
			baseurl = "https://stg.core.emed.com/procedure/begin?scope=ihealthlabsinc_antigenrapidtest_covid19&client_id=emed-qa";
			break;

		case "LKTransfer":
			baseurl = "https://www.lktransfer.com/LKTransferDashboard";
			break;

		case "CSDB":
			if (env.equalsIgnoreCase("prod")) {
				baseurl = "https://admin.emed.com";
			} else if (env.equalsIgnoreCase("qa")) {
				baseurl = "https://admin.dev2.emed.com";
			} else {
				baseurl = "https://admin." + env + ".emed.com";
			}
			break;

		case "GogoMed":
			baseurl = "https://uat.gogomeds.com/GoGoClientSprint86/#/home";
			break;

		case "LifePoint":
			baseurl = "https://www.lptest40.com/EMED/LPI3_4_2/lpi3/index.cfm/auth/login";
			break;

		case "ElasticPath":
			baseurl = "https://dashboard.elasticpath.com/app";
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
					WebDriverManager.firefoxdriver().setup();
					DesiredCapabilities capabilities = DesiredCapabilities.firefox();
					capabilities.setCapability("browserName", "firefox");
					capabilities.setCapability("marionette", true);
					capabilities.setCapability("acceptInsecureCerts", true);
					capabilities.setCapability("javascriptEnabled", true);
					driver.set(new FirefoxDriver(capabilities));
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
					if (platform.equalsIgnoreCase("WWW") || platform.equalsIgnoreCase("BinaxExpress")
							|| platform.equalsIgnoreCase("AWSConnectProctor")) {
						options.addExtensions(new File(
								System.getProperty("user.dir") + "/src/main/resources/ext/basic-auth/extension.crx"));
						options.addArguments("--no-sandbox");
					}
					options.addArguments("use-fake-ui-for-media-stream");
					options.addArguments("test-type");
					options.addArguments("ignore-certificate-errors");
					options.setAcceptInsecureCerts(true);
					WebDriverManager.chromedriver().setup();
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
					if (platform.equalsIgnoreCase("WWW") || platform.equalsIgnoreCase("BinaxExpress")
							|| platform.equalsIgnoreCase("AWSConnectProctor")) {
						options.addExtensions(new File(
								System.getProperty("user.dir") + "/src/main/resources/ext/basic-auth/extension.crx"));
					}
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
					options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
					options.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
					options.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
					options.setCapability(ChromeOptions.CAPABILITY, options);
					driver.set(new RemoteWebDriver(new URL("http://selenium-hub.selenium:4444/wd/hub"), options));
				}

			} else if (BrowserNeed.toLowerCase().contains("msedge")) {
				if (os.contains("Windows") || os.contains("OS X")) {

					WebDriverManager.edgedriver().setup();
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

			if (platform.equalsIgnoreCase("WWW")) {
				if (env.equalsIgnoreCase("prod")) {
					CommonFunctions.logMessage("Navigated to the site ===> " + "https://emed.com");
				} else {
					CommonFunctions.logMessage("Navigated to the site ===> " + "https://" + env + ".emed.com");
				}

			} else {
				CommonFunctions.logMessage("Navigated to the site ===> " + url);
			}

		} catch (Exception e) {
			e.printStackTrace();
			CommonFunctions.logErrorMessageStopExecution("Error Browser not invoked");
		}

	}

}