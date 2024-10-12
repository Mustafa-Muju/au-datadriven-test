package main.java.utils.postgresql;

import java.sql.ResultSet;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

/**
 * 
 * @author Mohammed Mustafa
 * 
 *         Class contains methods that query database for test data
 *
 */
public class QueryExecutor extends TestBase {
	
	private PostgresSQLConnector pgdb = new PostgresSQLConnector();

	/**
	 * Method fetches the order_id using eMed_id as input
	 * 
	 * @param dbName
	 * @param sqlStr
	 * @return
	 * @throws Exception
	 */
	public String getOrderIdByPostgresSQL(String eMedId) throws Exception {
		
		pgdb.initiateDBConnection("medoasis");

		ResultSet resultData = pgdb.getFirstRow("select ep_id from orders\r\n" + "where id = '" + eMedId + "'");

		String epId = "";
		if (!resultData.wasNull()) {
			epId = resultData.getString("ep_id");
		} else {
			CommonFunctions
					.logErrorMessageStopExecution("Order Id is not available in database for the eMed Id " + eMedId);
		}

		pgdb.closeDBConnection();

		return epId;
	}

}
