package org.property.rest.controllers;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class StatisticSaledControllerTest extends TestCase {

	/**
	 * 执行每一个测试方法的时候，先执行setUp()；
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}

	/**
	 * 执行每一个测试方法之后，执行tearDown();
	 */
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testHandleStatisticDailySaled() throws Exception {
	
		String date="2016-06-26";
		String url = URLTest.getURL() + API_PATH.Statistic_Daily_Saled + "?date=" + date;
		URLTest.testURLGet(url);
	}
	
	
	
	public void testHandleStatisticDailySaledDistric() throws Exception {
		
		String date="2016-06-26";
		String district="yh";
		String url = URLTest.getURL() + API_PATH.Statistic_Daily_Saled_District + "?date=" + date+"&&district="+district;
		URLTest.testURLGet(url);
	}
	
	
	public static void main(String[] args) {
		TestSuite suite = new TestSuite("Sample Tests");
		/* 逐一添加testCase类 */
		suite.addTestSuite(StatisticSaledControllerTest.class);

		junit.textui.TestRunner.run(suite);

	}

}
