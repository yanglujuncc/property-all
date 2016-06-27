package org.property.rest.controllers;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.property.rest.RestResponse;
import org.property.util.Jackson2TLUtil;

import junit.framework.Assert;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class UserSubscribtionControllerTest extends TestCase {

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

	public void testHandleUserSubscribtionAdd() throws Exception {
		String account = "yanglujuncc@gmail.com";
		String propertyId = "126121637";
		String url = URLTest.getURL() + API_PATH.Subscribtion_Add + "?account=" + account + "&&propertyId=" + propertyId;
		System.out.println("url:" + url);

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		int timeOut = 6000;
		Builder builder = RequestConfig.custom();
		RequestConfig requestConfig = builder.setConnectionRequestTimeout(timeOut).setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
		httpGet.setConfig(requestConfig);

		CloseableHttpResponse response = httpclient.execute(httpGet);

		int status = response.getStatusLine().getStatusCode();

		System.out.println("status:" + status);
		Assert.assertEquals(200, status);

		String content = EntityUtils.toString(response.getEntity());
		// EntityUtils.toString(entity, defaultCharset)
		System.out.println(content);

		response.close();
	}

	public void testHandleUserSubscribtionRemove() throws Exception {
		String account = "yanglujuncc@gmail.com";
		String propertyId = "126121637";
		String url = URLTest.getURL() + API_PATH.Subscribtion_Remove + "?account=" + account + "&&propertyId=" + propertyId;
		System.out.println("url:" + url);

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		int timeOut = 6000;
		Builder builder = RequestConfig.custom();
		RequestConfig requestConfig = builder.setConnectionRequestTimeout(timeOut).setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
		httpGet.setConfig(requestConfig);

		CloseableHttpResponse response = httpclient.execute(httpGet);

		int status = response.getStatusLine().getStatusCode();

		System.out.println("status:" + status);
		Assert.assertEquals(200, status);

		String content = EntityUtils.toString(response.getEntity());
		// EntityUtils.toString(entity, defaultCharset)
		System.out.println(content);

		response.close();
	}

	public void testHandleUserSubscribtionAndSignedList() throws Exception {
		String account = "yanglujuncc@gmail.com";
		String dateStr="2016-06-25";
		String url = URLTest.getURL() + API_PATH.Subscribtion_List_Signed + "?account=" + account+"&&date="+dateStr;
		System.out.println("url:" + url);

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		int timeOut = 6000;
		Builder builder = RequestConfig.custom();
		RequestConfig requestConfig = builder.setConnectionRequestTimeout(timeOut).setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
		httpGet.setConfig(requestConfig);

		CloseableHttpResponse response = httpclient.execute(httpGet);

		int status = response.getStatusLine().getStatusCode();

		System.out.println("response:" + response.toString());
		System.out.println("status:" + status);
		Assert.assertEquals(200, status);
		
		String content = EntityUtils.toString(response.getEntity());
		// EntityUtils.toString(entity, defaultCharset)
		RestResponse restResponse=Jackson2TLUtil.fromString(content, RestResponse.class);
		Assert.assertEquals(restResponse.code, 0);
		
		System.out.println(content);

		response.close();
	}

	public void testHandleUserSubscribtionList() throws Exception {
		
		
		String account = "yanglujuncc@gmail.com";
		String url = URLTest.getURL() + API_PATH.Subscribtion_List + "?account=" + account;
		System.out.println("url:" + url);

		HttpGet httpGet = new HttpGet(url);
		CloseableHttpClient httpclient = HttpClients.createDefault();

		int timeOut = 6000;
		Builder builder = RequestConfig.custom();
		RequestConfig requestConfig = builder.setConnectionRequestTimeout(timeOut).setConnectTimeout(timeOut).setSocketTimeout(timeOut).build();
		httpGet.setConfig(requestConfig);

		CloseableHttpResponse response = httpclient.execute(httpGet);

		int status = response.getStatusLine().getStatusCode();

		System.out.println("response:" + response.toString());
		System.out.println("status:" + status);
		Assert.assertEquals(200, status);
		
		String content = EntityUtils.toString(response.getEntity());
		// EntityUtils.toString(entity, defaultCharset)
		RestResponse restResponse=Jackson2TLUtil.fromString(content, RestResponse.class);
		Assert.assertEquals(restResponse.code, 0);
		
		System.out.println(content);

		response.close();
	}

	public static void main(String[] args) {
		TestSuite suite = new TestSuite("Sample Tests");
		/* 逐一添加testCase类 */
		suite.addTestSuite(UserSubscribtionControllerTest.class);

		junit.textui.TestRunner.run(suite);

	}
}
