package org.property.rest.controllers;




import java.net.URLEncoder;

import org.junit.Test;

import junit.framework.TestCase;
import junit.framework.TestSuite;

public class PropertyControllerTest extends TestCase {

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
	@Test
	public void testHandlePropertyInfo() throws Exception {
		String account = "yanglujuncc@gmail.com";
		String propertyId = "126121637";
		String url = URLTest.getURL() + API_PATH.Property_Info + "?propertyId=" + propertyId;
		URLTest.testURLGet(url);
	}
	
	
	@Test
	public void testHandlePropertySaledDetail() throws Exception {
		String account = "yanglujuncc@gmail.com";
		String propertyId = "126121637";
		String fromDate="2016-06-23";
		String toDate="2016-06-26";
		String url = URLTest.getURL() + API_PATH.Property_Saled_Detail + "?propertyId=" + propertyId+ "&&fromDate=" + fromDate+ "&&toDate=" + toDate;
		URLTest.testURLGet(url);
	}
	@Test
	public void testHandlePropertyInfoSearchName() throws Exception {
	
		String keywords="河滨";
		int n=10;
	;
		
			
		String url = URLTest.getURL() + API_PATH.Property_Info_Search_Name + "?keywords=" + URLEncoder.encode(keywords,"UTF-8")+ "&&n=" + n;
		System.out.println("url:"+url);
		URLTest.testURLGet(url);
	}
	
	public static void main(String[] args) {
		TestSuite suite = new TestSuite("Sample Tests");
		/* 逐一添加testCase类 */
		suite.addTestSuite(PropertyControllerTest.class);

		junit.textui.TestRunner.run(suite);

	}
}
