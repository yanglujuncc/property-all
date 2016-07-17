package org.property.rest.controllers;

import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import java.util.TimeZone;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpSession;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.property.rest.RestResponse;
import org.property.util.DateUtils;
import org.property.util.Jackson2TLUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import org.property.core.domain.Property;
import org.property.core.domain.PropertyDailySigned;
import org.property.core.domain.PropertyHouseSaleState;
import org.property.core.domain.mapper.PropertyDailySignedMapper;
import org.property.core.domain.mapper.PropertyHouseSaleStateMapper;
import org.property.core.domain.mapper.PropertyMapper;

@Controller("PropertyController")

public class PropertyController {

	static Logger logger = LoggerFactory.getLogger(PropertyController.class);

//	TimeZone zone = null;
//  SimpleDateFormat ISO_time_format = null;

//	@Autowired(required = true)
//	private SqlSessionFactory sqlSessionFactory;
	DecimalFormat DoubleFormat = new DecimalFormat("########.##");

	@Autowired(required = true)
	private SqlSession sqlSession;

	public PropertyController() throws Exception {

		System.out.println("PropertyController created .");

	
	}

	@SuppressWarnings("restriction")

	public void init() {
		System.out.println("DailySaleController init ...");

		// DOMConfigurator.configure(HouseController.class.getResource("/conf/log4j.xml"));

	}

	@SuppressWarnings("restriction")

	void destroy() {

	}

	@RequestMapping(value = API_PATH.Property_Info, method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String handlePropertyInfo(@RequestParam(value = "propertyId", required = true) String propertyId) throws Exception {

		RestResponse response = null;
		try {
			PropertyMapper mapper = sqlSession.getMapper(PropertyMapper.class);
			Property property = mapper.queryPropertyByPropertyId(propertyId);
			
			response = RestResponse.createSuccessResponse();
			response.body=property;
			if(property!=null){
				
				logger.info("propertyId:"+propertyId+" -> "+property.propertyName);
				System.out.println("Charset.defaultCharset().name():"+Charset.defaultCharset().name()); 
				System.out.println("file.encoding:"+System.getProperty("file.encoding")); 			
				System.out.println("propertyId:"+propertyId+" -> "+property.propertyName);
				System.out.println("chinese"+" -> "+"中文");
			}
		} catch (Exception e) {
			logger.error("",e);
			response=RestResponse.createErrorResponse();
			response.msg=e.getMessage();
			response.body=e;
		}
		
		System.out.println("propertyId:"+propertyId+" -> "+JSON.toJSONString(response));
		return Jackson2TLUtil.toString(response);


	}
	
	@RequestMapping(value = API_PATH.Property_Info_Search_Name, method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String handlePropertyInfoSearchName(@RequestParam(value = "keywords", required = true) String keywords,@RequestParam(value = "n",defaultValue="10") int n) throws Exception {

		RestResponse response = null;
		try {
			System.out.println("Charset.defaultCharset().name():"+Charset.defaultCharset().name()); 
			System.out.println("file.encoding:"+System.getProperty("file.encoding")); 			
			System.out.println("keywords:"+keywords);
			System.out.println("chinese"+" -> "+"中文");
			
			PropertyMapper mapper = sqlSession.getMapper(PropertyMapper.class);
			List<Property> properties = mapper.queryPropertyByPropertyName(keywords, n);
			//mapper.		
			response = RestResponse.createSuccessResponse();
			response.body=properties;
			
		} catch (Exception e) {
			logger.error("",e);
			response=RestResponse.createErrorResponse();
			response.msg=e.getMessage();
			response.body=e;
		}
		
		System.out.println("keywords:"+keywords+" n:"+n+" -> "+JSON.toJSONString(response));
		return Jackson2TLUtil.toString(response);


	}
	
	public  class PropertyDailySigned2 extends PropertyDailySigned{
		public String signedMoney;
		public PropertyDailySigned2(){
			
		}
		public PropertyDailySigned2(PropertyDailySigned pds){
			
			this.propertyTypeCode=pds.propertyTypeCode;
			this.propertyId=pds. propertyId;
			this.propertyName=pds.propertyName;
			this.district=pds.district;
			this.signedNumber=pds.signedNumber;
			this.reservedNumber=pds.reservedNumber;
			
			this.signedArea=DoubleFormat.format(Double.parseDouble(pds.signedArea));
			this.signedAvgPrice=DoubleFormat.format(Double.parseDouble(pds.signedAvgPrice));
			
			this.signedDate=pds.signedDate;
			this.signedTime=pds.signedTime;
			
			this.signedMoney=null;
			if(this.signedArea!=null&&this.signedAvgPrice!=null){			
				
				double signedArea=Double.parseDouble(this.signedArea);
				double dignedAvgPrice=Double.parseDouble(this.signedAvgPrice);

				this.signedMoney=DoubleFormat.format((signedArea*dignedAvgPrice));
			}
		}
	}
	public  class DayRecordUnit{
		public List<PropertyDailySigned2> signeds;
		public List<PropertyHouseSaleState> houseSaleStates;
		
		public DayRecordUnit(){
			signeds=new LinkedList<PropertyDailySigned2> ();
			houseSaleStates=new LinkedList<PropertyHouseSaleState> ();
		}
	}
	
	private  void uniqueAndSort(Map<String,DayRecordUnit> dayRecordUnitMap){
		
		Comparator<PropertyDailySigned2> signedComparator=new  Comparator<PropertyDailySigned2>(){
			public int compare(PropertyDailySigned2 o1, PropertyDailySigned2 o2) {				
				return -o1.signedTime.compareTo(o2.signedTime);
			}			
		};
		Comparator<PropertyHouseSaleState> saleStateComparator=new Comparator<PropertyHouseSaleState>(){
			public int compare(PropertyHouseSaleState o1, PropertyHouseSaleState o2) {			
				return -o1.stateChangeTime.compareTo(o2.stateChangeTime);
			}		
		};
		
		for(Entry<String, DayRecordUnit> entry:dayRecordUnitMap.entrySet()){
			DayRecordUnit dayRecordUnit=entry.getValue();	
			
			dayRecordUnit.signeds=spiltSignedRecords(dayRecordUnit.signeds);
			
			Collections.sort(dayRecordUnit.signeds,signedComparator);
			Collections.sort(dayRecordUnit.houseSaleStates,saleStateComparator);
		}
	}
	private   List<PropertyDailySigned2> spiltSignedRecords(List<PropertyDailySigned2> signedRecords){
		
		PropertyDailySigned2[] signedRecordsArray=signedRecords.toArray(new PropertyDailySigned2[signedRecords.size()]);
		//signedRecordsArray.
		Arrays.sort(signedRecordsArray,new Comparator<PropertyDailySigned2>(){
			public int compare(PropertyDailySigned2 o1, PropertyDailySigned2 o2) {	
				return o1.signedTime.compareTo(o2.signedTime);
			}	
		});
		 List<PropertyDailySigned2> spiltSigned=new LinkedList<PropertyDailySigned2>();
		 
		 if(signedRecords.size()==0){
			 return spiltSigned;
		 }
		 
		spiltSigned.add(signedRecordsArray[0]);
		PropertyDailySigned2 preSigned=signedRecordsArray[0];
		
		for(int i=1;i<signedRecordsArray.length;i++){
			
			PropertyDailySigned2 thisSigned=signedRecordsArray[i];
			int thisSignedNumber=Integer.parseInt(thisSigned.signedNumber);
			int preSignedNumber=Integer.parseInt(preSigned.signedNumber);
			
			if(preSignedNumber==thisSignedNumber){
				preSigned=thisSigned;
				continue;
			}
			if(preSignedNumber>thisSignedNumber){
				logger.error("something is wrong , preSignedNumber("+preSignedNumber+")>thisSignedNumber("+thisSignedNumber+")");
				preSigned=thisSigned;
				continue;
			}
			
			PropertyDailySigned2 splitDailySigned=new PropertyDailySigned2();
			
			splitDailySigned.propertyTypeCode=thisSigned.propertyTypeCode;
			splitDailySigned.propertyId=thisSigned.propertyId;
			splitDailySigned.propertyName=thisSigned.propertyName;
			splitDailySigned.district=thisSigned.district;
			splitDailySigned.signedDate=thisSigned.signedDate;
			splitDailySigned.signedTime=thisSigned.signedTime;
				
			int splitSignedNumber=thisSignedNumber-preSignedNumber;
			int splitReservedNumber=Integer.parseInt(thisSigned.reservedNumber)-Integer.parseInt(preSigned.reservedNumber);
			
			double thisSignedArea=Double.parseDouble(thisSigned.signedArea);
			double preSignedArea=Double.parseDouble(preSigned.signedArea);		
			double thisSignedAvgPrice=Double.parseDouble(thisSigned.signedAvgPrice);
			double preSignedAvgPrice=Double.parseDouble(preSigned.signedAvgPrice);	
			double thisSignedMoney=thisSignedArea*thisSignedAvgPrice;
			double preSignedMoney=preSignedArea*preSignedAvgPrice;
			
			double splitSignedArea=thisSignedArea-preSignedArea;
			double splitSignedMoney=thisSignedMoney-preSignedMoney;
			double splitSignedAvgPrice=splitSignedMoney/splitSignedArea;
			
			splitDailySigned.reservedNumber=splitReservedNumber+"";
			splitDailySigned.signedNumber=splitSignedNumber+"";
			splitDailySigned.signedArea=DoubleFormat.format(splitSignedArea);
			splitDailySigned.signedAvgPrice=DoubleFormat.format(splitSignedAvgPrice);
			splitDailySigned.signedMoney=DoubleFormat.format(splitSignedMoney);
			
			spiltSigned.add(splitDailySigned);
			preSigned=thisSigned;
		}
		
		return spiltSigned;
		
	};
	

	@RequestMapping(value = API_PATH.Property_Saled_Detail, method = RequestMethod.GET, produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String handlePropertySaledDetail(
			@RequestParam(value = "fromDate", required = true) String fromDate,
			@RequestParam(value = "toDate", required = true) String toDate,
			@RequestParam(value = "propertyId", required = true) String propertyId) throws Exception {

		System.out.println("fromDate:"+fromDate+" toDate:"+toDate+" propertyId:"+propertyId);
		
		
		RestResponse response = null;
		try {
			
		//	String loginedAccount = (String) session.getAttribute("account");
		//	session.setAttribute("account", loginedAccount);

			// StringBuffer aStringBuffer=new StringBuffer();

			PropertyDailySignedMapper dailySignedMapper = sqlSession.getMapper(PropertyDailySignedMapper.class);
			PropertyHouseSaleStateMapper houseSaleStateMapper= sqlSession.getMapper(PropertyHouseSaleStateMapper.class);
			
			Map<String,DayRecordUnit> dayRecordUnitMap=new TreeMap<String,DayRecordUnit>();
			String[] everyDate=DateUtils.everyDate(fromDate, toDate);
			for(String date:everyDate){
				dayRecordUnitMap.put(date, new DayRecordUnit());
			}
			
			List<PropertyDailySigned> daySigneds = dailySignedMapper.queryPropertyDailySignedByDateRangePropertyId(fromDate, toDate, propertyId);
			List<PropertyHouseSaleState> dayHouseSaleStates=houseSaleStateMapper.queryHouseSaleStateByPropertyIdAndStateChangeTime(propertyId, fromDate+" 00:00:00", toDate+" 24:00:00");
			//	List<PropertyDailySigned> maxPropertyDailySigneds = maxSignNumber(propertyDailySigneds);
			
			for(PropertyDailySigned daySigned:daySigneds){	
				DayRecordUnit dayRecordUnit=dayRecordUnitMap.get(daySigned.signedDate);
				daySigned.signedTime=daySigned.signedTime.substring(11);
				dayRecordUnit.signeds.add(new PropertyDailySigned2(daySigned));
				
			}
			for(PropertyHouseSaleState dayHouseSaleState:dayHouseSaleStates){
				if(!"已　售".equals(dayHouseSaleState.houseStateName))
					continue;
				double area_inside_rate=Double.parseDouble(dayHouseSaleState.area_inside)/Double.parseDouble(dayHouseSaleState.area_builtup);
				dayHouseSaleState.area_inside_rate=""+DoubleFormat.format(area_inside_rate);
				DayRecordUnit dayRecordUnit=dayRecordUnitMap.get(dayHouseSaleState.stateChangeTime.substring(0,10));
				//2015-05-02 88
				dayHouseSaleState.stateChangeTime=dayHouseSaleState.stateChangeTime.substring(11);
				dayRecordUnit.houseSaleStates.add(dayHouseSaleState);
			}
			
			//sort 
			uniqueAndSort(dayRecordUnitMap);
			
			response = RestResponse.createSuccessResponse();
			response.body=dayRecordUnitMap;
			
		} catch (Exception e) {
			logger.error("",e);
			response=RestResponse.createErrorResponse();
			response.msg=e.getMessage();
			response.body=e;
		}
		
		System.out.println(Jackson2TLUtil.toString(response));
		return Jackson2TLUtil.toString(response);


	}
	
}
