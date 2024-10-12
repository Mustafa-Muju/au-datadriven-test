package main.java.utils.rest;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

/**
 * @author Mohammed Mustafa
 * 
 * @This class contain methods that requires for gogomed flows
 *
 */
public class RestGogomeds extends TestBase {

	private static final ThreadLocal<Map<String,String>> headerMap = ThreadLocal.withInitial(HashMap<String,String>::new);
	private static Map<String, String> getHeaderMap() {
        return headerMap.get();
	}
	private static String userAgent = "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.141 Safari/537.36";

	/**
	 * This Method is to approve the order to ship
	 * 
	 * @param orderId    --> Order Id of the kit purchased
	 * @param customerId --> Customer Id of the kit purchased
	 * 
	 * @return
	 * @throws UnknownHostException
	 * @throws NullPointerException
	 */
	public static void approveOrder(String orderId, String customerId, String status) throws Exception {

		String[] orderStatus = status.split(Pattern.quote("|"));
		String[] eachOrderId = orderId.split(Pattern.quote("|"));
		
		String orderJson = "";
		for (int user = 0; user < orderStatus.length; user++) {
			if (user > 0) {
				orderJson += ",\r\n";
			}
			orderJson += "{\r\n" + 
					"            \"orderId\": \""+eachOrderId[user]+"\",\r\n" + 
					"            \"customerId\": \""+customerId+"\",\r\n" + 
					"            \"orderStatus\": \""+orderStatus[user]+"\",\r\n" + 
					"            \"batchOrderStatusChangedBy\": \"Admin, Admin\",\r\n" + 
					"            \"batchOrderStatusDate\": \"2021-07-02T19:40:13.05\"\r\n" + 
					"        }";
		}

		try {

			RestAssured.baseURI = "https://phoenix-ecom-api." + env + ".emed.com/api/v1/consult/status";

			getHeaderMap().put("api-key", "9b2fdc027e8140af83149301bd207e0b4ebf5e2d3e78179d39c");
			getHeaderMap().put("Connection", "keep-alive");
			getHeaderMap().put("Content-Type", "application/json");
			getHeaderMap().put("Accept-Encoding", "gzip, deflate, br");
			getHeaderMap().put("Accept", "*/*");
			getHeaderMap().put("User-Agent", userAgent);

			RequestSpecification httpRequest = RestAssured.given();

			Response response = httpRequest.headers(getHeaderMap()).body("{\r\n" + 
					"    \"batchId\": \"1612466779\",\r\n" + 
					"    \"batchStatus\": \"Processed\",\r\n" + 
					"    \"batchStatusChangedBy\": \"Admin, Admin\",\r\n" + 
					"    \"approvedCount\": 1,\r\n" + 
					"    \"batchDate\": \"2021-01-04T00:00:00\",\r\n" + 
					"    \"batchStatusDate\": \"2021-02-04T19:40:14.123\",\r\n" + 
					"    \"orderCount\": 1,\r\n" + 
					"    \"rejectedCount\": 0,\r\n" + 
					"    \"order\": [\r\n" + 
					"        "+orderJson+"\r\n" + 
					"    ],\r\n" + 
					"    \"affiliateId\": 10060\r\n" + 
					"}").post();

			if (response.getStatusCode() == 200) {
				CommonFunctions.logMessage("Gogomed api for send order status to EP is successfully sent.");

			} else {
				CommonFunctions.logErrorMessageStopExecution(
						"Failed due to invalid response status code ==>" + response.getStatusCode());
			}

		} catch (UnknownHostException e) {
			CommonFunctions.logErrorMessageStopExecution(
					"Failed due to network connection not available. \nPlease check your internet connectivity");

		} catch (NullPointerException e) {
			CommonFunctions.logErrorMessageStopExecution("Failed to fetch response may be due to invalid request data");

		} catch (Exception e) {
			CommonFunctions.logErrorMessageStopExecution("Error while sending order status to EP.");
		}
	}
	
	/**
	 * This method for fulfillment order confirmation
	 * 
	 * @throws Exception
	 *
	 */

	public static void fulfillmentConfirmation() throws Exception {
		Thread.sleep(1000);
		String customerOrderTrackNumber = CommonFunctions.randomUUID();
		RestAssured.baseURI = "https://fulfillment-api."+env+".emed.com/api/v1/fulfillment/ggm_confirmation";
		getHeaderMap().put("api-key", "9b2fdc027e8140af83149301bd207e0b4ebf5e2d3e78179d39c");
		getHeaderMap().put("Connection", "keep-alive");
		getHeaderMap().put("Content-Type", "application/json");
		getHeaderMap().put("Accept-Encoding", "gzip, deflate, br");
		getHeaderMap().put("Accept", "*/*");
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.headers(getHeaderMap())
				.body("{\r\n" + "  \"orderId\": \"" + getOrderId() + "\",\r\n" + "  \"orderstatus\": \"Accepted\",\r\n"
						+ "  \"orderdescription\": \"\",\r\n" + "  \"shipmentstatus\": \"\",\r\n"
						+ "  \"shipmentdescription\": \"\",\r\n" + "  \"shipmentnote\": \"\",\r\n"
						+ "  \"trackingnumber\": \""+customerOrderTrackNumber+"\",\r\n" + "  \"expecteddelivery\": \"\",\r\n"
						+ "  \"processtime\": \""+CommonFunctions.currentMilliseconds()+"\"\r\n" + "}")
				.post();

		if (response.getStatusCode() == 200 && JsonPath.from(response.getBody().asString()).get("this").equals("succeeded")) {
			CommonFunctions.logMessage("The Order is shipped using fulfillment confirmation api");
		} else {
			System.err.println("Response failed with code "+response.getStatusCode()+" and response body as "+response.getBody().asPrettyString());
			CommonFunctions.logErrorMessage("Error, Order is not in shipped using the fulfillment confirmation api.");
		}

	}

}
