package org.property.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Jackson2TLUtil {
	   private static final ThreadLocal<ObjectMapper> objectMapperTL = new ThreadLocal<ObjectMapper>() {
           @Override
           protected ObjectMapper initialValue() {
               return new ObjectMapper();
           }
       };
       
       public static final  ObjectMapper getObjectMapper(){
    	   return objectMapperTL.get();
       }
       
       public static final String toString(Object obj) throws JsonProcessingException{
    	
    	   ObjectMapper objectMapper= objectMapperTL.get();
    	   
    	   return objectMapper.writeValueAsString(obj);
       }
       public static final  <T> T fromString(String jsonString,Class<T> classObj) throws IOException{
       	
    	   ObjectMapper objectMapper= objectMapperTL.get();
    	   
    	   return objectMapper.readValue(jsonString, classObj);
       } 
       

}
