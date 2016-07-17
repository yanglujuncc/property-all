package org.property.util;

import java.nio.charset.Charset;

public class Env {

	public static void displayEncode(){
		System.out.println("Charset.defaultCharset().name():"+Charset.defaultCharset().name()); 
		System.out.println("file.encoding:"+System.getProperty("file.encoding")); 
		System.out.println("chinese"+" -> "+"中文");
	}
	
	public static void main(String[] args){
		displayEncode();
	}
}
