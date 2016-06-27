package org.property.rest.controllers;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;

import junit.framework.Assert;

public class URLTest {
	
	
	public static String Protocol="http"; //http://
	public static String Host="localhost"; 
	public static int Port=8080; 
	
	public static String PathHome="property-all"; 

	public static String getURL(){
		if(PathHome==null)
			return Protocol+"://"+Host+":"+Port;		
		else
			return Protocol+"://"+Host+":"+Port+"/"+PathHome;	
	}
	
	public static void testURLGet(String url) throws Exception {
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
		System.out.println(JSON.toJSONString(JSON.parseObject(content), true));

		response.close();
	}
	public static void main(String[] args){
		System.out.println(getURL());
	}
}
