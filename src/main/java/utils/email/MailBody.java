package main.java.utils.email;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.json.JSONObject;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

/**
 * 
 * @author Mohammed Mustafa
 *
 */
public class MailBody extends TestBase {
	
	/**
	 * 
	 * @This method is used to get the jenkins variables
	 * 
	 * @param key
	 * @return
	 * 
	 */
	public static String jenkinsVariables(String key) {
		Map<String, String> variables = new HashMap<String, String>();
		variables.put("triggername", (System.getenv("JOB_NAME") != null && System.getProperty("os.name").contains("Linux")) ? (CommonFunctions.capitalize(System.getenv("BUILD_USER"))):(CommonFunctions.capitalize(System.getProperty("user.name"))));
		variables.put("machinename", (System.getenv("JOB_NAME") != null && System.getProperty("os.name").contains("Linux")) ? ("Jenkins-"+System.getProperty("os.name")):(System.getProperty("os.name")));
		variables.put("jobname", System.getenv("JOB_NAME"));
		return variables.get(key);
	}

	/**
	 * @This Method creates the email body structure using html tags
	 * @return String
	 */

	public static String emailBodyCreator(String duration, String projectType) {

		String text = "<h4>Hello Team,</h4>" + "<p>Please find the results for the " + env.toUpperCase() + "-" + projectType.toUpperCase() + " execution,</p>";

		text = text
				+ "<table style='font-size: 12px; border-collapse: collapse;' width='30%' border='1' bordercolor='black'>"
				+ "<tr bgcolor='gray' align='center' style='color: white;'><td colspan='2'><b>Test Execution Details</b></td></tr>"
				+ "<tr bgcolor='lightgray'><td><b>Tag Name</b></td><td align='center'>" + projectType + "</td></tr>"
				+ "<tr><td><b>Triggered by</b></td><td align='center'>"
				+  jenkinsVariables("triggername") + "</td></tr>"
				+ "<tr bgcolor='lightgray'><td><b>Environment</b></td><td align='center'>"
				+ env.toUpperCase() + "</td></tr>" + "<tr><td><b>Browser</b></td><td align='center'>"
				+ CommonFunctions.capitalize(BrowserNeed) + "</td></tr>"
				+ "<tr bgcolor='lightgray'><td><b>Machine</b></td><td align='center'>" + jenkinsVariables("machinename")
				+ "</td></tr>";

		text = text
				+ "<tr bgcolor='gray' align='center' style='color: white;'><td colspan='2'><b>Test Case Details</b></td></tr>"
				+ "<tr><td><b>Overall Test Duration</b></td><td align='center'>" + duration + "</td></tr>"
				+ "<tr bgcolor='lightgray'><td><b>Overall Test case Executed</b></td><td align='center'>"
				+ (passed + failure + skipping) + "</td></tr>"
				+ "<tr><td><b>Test Passed</b></td><td style='color: green;' align='center'>" + passed + "</td></tr>"
				+ "<tr bgcolor='lightgray'><td><b>Test Failed</b></td><td style='color: red;' align='center'>" + failure
				+ "</td></tr>" + "<tr><td><b>Test Skipped</b></td><td style='color: orange;' align='center'>" + skipping
				+ "</td></tr>" + "</table><br>";

		text = text + mailText + "<p>Thank you</p>";
		
		if (System.getenv("JOB_NAME") != null && System.getProperty("os.name").contains("Linux")) {
//			text = text
//					+ "<p align=\"center\"><b>**Check full logs from the extent report**</b></p>"
//					+ "<div align=\"center\">"
//					+ "<a href=\"www.google.com\" style='background-color: rgb(162, 165, 159); border: 1px solid rgb(162, 165, 159); border-radius: 6px; color: rgb(255, 255, 255); display: inline-block; font-size: 12px; font-weight: bold; letter-spacing: 0px; line-height: normal; padding: 10px 14px; text-align: center; text-decoration: none;'>Extent Report</a>"
//					+ "</div>";
			text = text + "<p>For more details please look into the extent report attached below.</p>";
		} else {
			text = text + "<p>For more details please look into the extent report attached below.</p>";
		}
//			href=\""+FTPUploadReport.ftpTransfer()+"\"
		return text;
	}

	/**
	 * @This method creates Skipped mail text body
	 * 
	 * @return
	 */
	public static String separateSkippedBody() {
		String emailText = "";

		LinkedList<String> testNames = new LinkedList<String>(testResults.keySet());

		for (int j = 0; j < testNames.size(); j++) {

			JSONObject restCorrelateJSON = CommonFunctions
					.restConvertTextAsJson(CommonFunctions.restCorrelateJSON(testResults.toString(), testNames.get(j)));
			List<String> ArrayTest = new LinkedList<String>(restCorrelateJSON.keySet());
			List<Integer> listOfTest = new LinkedList<Integer>();
			for (int i = 0; i < ArrayTest.size(); i++) {
				listOfTest.add(Integer.valueOf(ArrayTest.get(i)));
	        }
			Set<Integer> allTest = new TreeSet<Integer>(listOfTest);
			List<Integer> listTest = new LinkedList<Integer>(allTest);

			emailText = emailText
					+ "<br><table style='font-size: 12px; border-collapse: collapse;' width='100%' border='1' bordercolor='black'>"
					+ "<tr align='center' bgcolor='orange'>" + "<td colspan='4'><b>Skipped Scenario</b></th>" + "</tr>"
					+ "<tr align='center' bgcolor='lightgray'>" + "<th><b>#</b></th>"
					+ "<th><b>Scenario Description</b></th>" + "</tr>";

			for (int emailMsg = 0; emailMsg < listTest.size(); emailMsg++) {
				String sNo = CommonFunctions.restCorrelateJSON(testResults.toString(),
						testNames.get(j)+"." +listTest.get(emailMsg) + ".scenario_no");
				String sName = CommonFunctions.restCorrelateJSON(testResults.toString(),
						testNames.get(j)+"." +listTest.get(emailMsg)+ ".scenario_title");
				String sDescription = CommonFunctions.restCorrelateJSON(testResults.toString(),
						testNames.get(j)+"." +listTest.get(emailMsg)+ ".scenario_description");
				String sStatus = CommonFunctions.restCorrelateJSON(testResults.toString(),
						testNames.get(j)+"." +listTest.get(emailMsg)+ ".scenario_status");

				if (sStatus.equalsIgnoreCase("skipped")) {
					emailText = emailText + "<tr style='color: black; font-size: 12px;'>" + "<td align='center'>" + sNo
							+ "</td>" + "<td>" + sName + "</td>" + "<td>" + sDescription + "</td>" + "</tr>";
				}
			}
			emailText = emailText + "</table>";
		}
		return emailText;
	}

	/**
	 * 
	 * @Method create a test table data for email report
	 * 
	 * @return
	 */
	public static void emailScenarioTableCreate() {

		LinkedList<String> testNames = new LinkedList<String>(testResults.keySet());

		for (int j = 0; j < testNames.size(); j++) {

			JSONObject restCorrelateJSON = CommonFunctions
					.restConvertTextAsJson(CommonFunctions.restCorrelateJSON(testResults.toString(), testNames.get(j)));
			List<String> ArrayTest = new LinkedList<String>(restCorrelateJSON.keySet());
			List<Integer> listOfTest = new LinkedList<Integer>();
			for (int i = 0; i < ArrayTest.size(); i++) {
				listOfTest.add(Integer.valueOf(ArrayTest.get(i)));
	        }
			Set<Integer> allTest = new TreeSet<Integer>(listOfTest);
			List<Integer> listTest = new LinkedList<Integer>(allTest);

			mailText = mailText
					+ "<table style='font-size: 12px;' width='100%' border='1' bordercolor='black' align='center'>"
					+ "<tr align='center' bgcolor='gray'>" + "<td colspan='5' align='center' style='color: white;'><b>"
					+ testNames.get(j).toUpperCase() + "</b></td></tr>"
					+ "<tr align='center' bgcolor='lightgray'>" + "<th><b>#</b></th>" + "<th><b>Scenario Name</b></th>"
					+ "<th><b>Scenario Description</b></th>" + "<th><b>Status</b></th>" + "<th><b>Comments</b></th>"
					+ "</tr>";

			for (int emailMsg = 0; emailMsg < listTest.size(); emailMsg++) {

				String sNo = CommonFunctions.restCorrelateJSON(testResults.toString(),
						testNames.get(j)+"." +listTest.get(emailMsg)+ ".scenario_no");
				String sName = CommonFunctions.restCorrelateJSON(testResults.toString(),
						testNames.get(j)+"." +listTest.get(emailMsg)+ ".scenario_title");
				String sDescription = CommonFunctions.restCorrelateJSON(testResults.toString(),
						testNames.get(j)+"." +listTest.get(emailMsg)+ ".scenario_description");
				String sStatus = CommonFunctions.restCorrelateJSON(testResults.toString(),
						testNames.get(j)+"." +listTest.get(emailMsg)+ ".scenario_status");
				String sComments = CommonFunctions.restCorrelateJSON(testResults.toString(),
						testNames.get(j)+"." +listTest.get(emailMsg)+ ".scenario_comment");

				if (sStatus.equalsIgnoreCase("passed")) {
					mailText = mailText + "<tr style='color: green; font-size: 12px;'>" + "<td align='center'>" + sNo
							+ "</td>" + "<td>" + sName + "</td>" + "<td>" + sDescription + "</td>" + "<td>" + sStatus + "</td>" + "<td>"
							+ sComments + " </td>" + "</tr>";

				} else if (sStatus.equalsIgnoreCase("failed")) {
					mailText = mailText + "<tr style='color: red; font-size: 12px;'>" + "<td align='center'>" + sNo
							+ "</td>" + "<td>" + sName + "</td>" + "<td>" + sDescription + "</td>" + "<td>" + sStatus + "</td>" + "<td>"
							+ sComments + "</td>" + "</tr>";

				} else if (sStatus.equalsIgnoreCase("skipped") && !mailVerbose.equals("2")
						&& !mailVerbose.equals("3")) {
					mailText = mailText + "<tr style='color: orange; font-size: 12px;'>" + "<td align='center'>" + sNo
							+ "</td>" + "<td>" + sName + "</td>" + "<td>" + sDescription + "</td>" + "<td>" + sStatus + "</td>" + "<td>"
							+ sComments + "</td>" + "</tr>";
				}

			}

			mailText = mailText + "</table><br>";

			if (mailVerbose.equals("3")) {
				mailText = mailText + separateSkippedBody();
			}
		}
	}
	}

