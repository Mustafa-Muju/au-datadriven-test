package main.java.utils.postgresql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

/**
 * 
 * @author Mohammed Mustafa
 * 
 *         This Class connects AWS PostgresSQL and query data for testing
 *
 */
public class PostgresSQLConnector extends TestBase {

	private static Connection connection = null;
	private static PreparedStatement statement = null;
	private static ResultSet resultSet = null;

	/**
	 * Initializing PostgresSQL driver
	 */
	public PostgresSQLConnector() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.err.println("postgresql class not found. Stop running ");
			throw new Error(e.getMessage());
		}
	}

	/**
	 * Establishing AWS PostgresSQL connection
	 * 
	 * @param dbName - DB name to be connect (e.g) medoasis, core
	 * 
	 * @throws Exception
	 */
	public void initiateDBConnection(String dbName) throws Exception {
		String host = "";
		String port = "";
		String db_name = "";
		String username = "";
		String password = "";

		switch (dbName.toLowerCase()) {
		case "medoasis":
			port = "5432";
			db_name = "emed_db";
			username = "medoasis_app";

			switch (env.toLowerCase()) {
			case "stg":
				host = "staging-medoasis-db.cluster-coin2meakmvt.us-east-1.rds.amazonaws.com";
				password = "DXSsrU9Fwa";
				break;

			case "dev2":
				host = "dev-medoasis-db.cluster-c9okq3makatu.us-east-1.rds.amazonaws.com";
				password = "sD2x1G58mU";
				break;

//			case "prod":
//				host = "prod-medoasis-db.cluster-ro-csxwwwylprko.us-east-1.rds.amazonaws.com";
//				password = "vZFJSOI1KD";
//				break;

			default:
				break;
			}
			break;

		case "core":
			port = "5432";
			db_name = "emed_core";
			username = "core_app";

			switch (env.toLowerCase()) {
			case "stg":
				host = "staging-core-services-db.cluster-ro-coin2meakmvt.us-east-1.rds.amazonaws.com";
				password = "UQOwqUhx6o";
				break;

			case "dev2":
				host = "dev-core-services-db.cluster-ro-c9okq3makatu.us-east-1.rds.amazonaws.com";
				password = "cVJ6IH5DXv";
				break;

//			case "prod":
//				host = "prod-core-services-db.cluster-ro-csxwwwylprko.us-east-1.rds.amazonaws.com";
//				password = "XJ71_GE3Fz";
//				break;

			default:
				break;
			}
			break;

		default:
			CommonFunctions.logErrorMessageStopExecution("Failed due to account not created in " + env + " env");
			break;

		}

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://" + host + ":" + port + "/" + db_name + "",
					"" + username + "", "" + password + "");
			if (connection != null) {
				System.out.println("Connected to the aws postgresql");
			}
		} catch (SQLException e) {
			CommonFunctions.logErrorMessageStopExecution("Fail to establish a connection with aws postgresql");
		}
	}

	/**
	 * Fetch the first available result data set after the DB connected successfully
	 * 
	 * @param sqlString - query to be executed
	 * @return
	 */
	public ResultSet getFirstRow(String sqlString) {
		statement = null;
		resultSet = null;

		try {
			statement = connection.prepareStatement(sqlString);
			resultSet = statement.executeQuery();
			resultSet.next();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Fail to execute the query");
			throw new Error(e.getMessage());
		}

		return resultSet;
	}

	/**
	 * Disconnect's AWS PostgresSQL DB connection
	 */
	public void closeDBConnection() {
		try {
			resultSet.close();
			statement.close();
			connection.close();
			System.out.println("Closed all connections");
		} catch (SQLException e) {
			System.err.println("Fail to close aws postgresql connection");
			throw new Error(e.getMessage());
		}
	}

}
