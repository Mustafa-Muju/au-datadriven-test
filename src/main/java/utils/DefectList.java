package main.java.utils;

import java.util.HashMap;
import java.util.Map;

import main.java.base.TestBase;

public class DefectList extends TestBase {

	/**
	 * 
	 * @This method is used to store the P1 level defect id
	 * 
	 * @param eName
	 * @return
	 */
	public static String getP1Defect(String p1Defect) {

		Map<String, String> p1 = new HashMap<String, String>();
		p1.put("ExampleP1-001", "Sign Up | Missing Successful verification message");

		return p1.get(p1Defect);

	}

	/**
	 * 
	 * @This method is used to store the P2 level defect id
	 * 
	 * @param eName
	 * @return
	 */

	public static String getP2Defect(String p2Defect) {

		Map<String, String> p2 = new HashMap<String, String>();
		p2.put("ExampleP1-001",
				"When Purchasing Kit Card does not go through , Order still shows as Open");

		return p2.get(p2Defect);

	}

}
