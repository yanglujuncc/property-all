package org.property.rest.controllers;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import junit.framework.Assert;
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
	
	public void testHandlePropertyInfo() throws Exception {
		String account = "yanglujuncc@gmail.com";
		String propertyId = "126121637";
		String url = URLTest.getURL() + API_PATH.Property_Info + "?propertyId=" + propertyId;
		URLTest.testURLGet(url);
	}
	
	
	
	public void testHandlePropertySaledDetail() throws Exception {
		String account = "yanglujuncc@gmail.com";
		String propertyId = "126121637";
		String fromDate="2016-06-23";
		String toDate="2016-06-26";
		String url = URLTest.getURL() + API_PATH.Property_Saled_Detail + "?propertyId=" + propertyId+ "&&fromDate=" + fromDate+ "&&toDate=" + toDate;
		URLTest.testURLGet(url);
	}
	
	
	public static void main(String[] args) {
		TestSuite suite = new TestSuite("Sample Tests");
		/* 逐一添加testCase类 */
		suite.addTestSuite(PropertyControllerTest.class);

		junit.textui.TestRunner.run(suite);

	}
}
