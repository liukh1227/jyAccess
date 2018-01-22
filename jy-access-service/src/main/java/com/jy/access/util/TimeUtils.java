package com.jy.access.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jy.base.common.utils.CommonUtils;
import com.jy.entity.po.DeviceWorktraceMonthDate;

public class TimeUtils {
	
	public static List<DeviceWorktraceMonthDate> getDeviceWorktraceMonthDateList(String beginDate,String endDate){
		 List<DeviceWorktraceMonthDate>  dWorktraceMonthDateList = new ArrayList<DeviceWorktraceMonthDate>();
		 try{
		 String pattern = "yyyy-MM-dd";
		 Date maxDate =  CommonUtils.parseDate(endDate, pattern);
		 Date minDate =  CommonUtils.parseDate(beginDate, pattern);
		  Calendar maxCalendar = Calendar.getInstance();
		  maxCalendar.setTime(maxDate);
		  maxCalendar.set(maxCalendar.get(Calendar.YEAR), maxCalendar.get(Calendar.MONTH), 2);
	      Calendar minCalendar = Calendar.getInstance();
	        minCalendar.setTime(minDate);
	        minCalendar.set(minCalendar.get(Calendar.YEAR), minCalendar.get(Calendar.MONTH), 1);
	        Calendar currCalendar = minCalendar;
		  if(maxDate.getTime() > minDate.getTime() ){
		        List<Date> cacheDateList = new ArrayList<Date>();
		        while (currCalendar.before(maxCalendar)) {
		        	cacheDateList.add(currCalendar.getTime());
		            currCalendar.add(Calendar.MONTH, 1);
		        }
		        
		        int betWeenDay ;
		        Date minMothDate = null;
		        Date maxMothDate = null;
		        Calendar currMonthCal = Calendar.getInstance();
		        for (int index = 0; index< cacheDateList.size(); index++){
		        	DeviceWorktraceMonthDate dWorktraceMonthDate= new DeviceWorktraceMonthDate();
		            if (index == 0){  
		            	minMothDate = minDate;
		            	  currMonthCal.setTime(cacheDateList.get(index));
		            	if(cacheDateList.size() == 1){
		            		maxMothDate = maxDate;
		            		
		            	}else{
		            		//设置当前月最后一天
		                    currMonthCal.set(Calendar.DAY_OF_MONTH, currMonthCal.getActualMaximum(Calendar.DAY_OF_MONTH));
			                maxMothDate = currMonthCal.getTime();
			               
		            	}
		            	 dWorktraceMonthDate.setMonthStartDate(minMothDate);
			                dWorktraceMonthDate.setMonthEndDate(maxMothDate);
			                betWeenDay = CommonUtils.daysBetween(CommonUtils.getDateString(minMothDate, pattern),   CommonUtils.getDateString(maxMothDate, pattern));
		            	
		             
		            }else if (index == cacheDateList.size()-1){ //处理最后一次时间
		                maxMothDate = maxDate;
		                currMonthCal.setTime(cacheDateList.get(index));
		                currMonthCal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		                minMothDate = currMonthCal.getTime();
		                dWorktraceMonthDate.setMonthStartDate(minMothDate);
		                dWorktraceMonthDate.setMonthEndDate(maxMothDate);
		                betWeenDay = CommonUtils.daysBetween(CommonUtils.getDateString(minMothDate, pattern),   CommonUtils.getDateString(maxMothDate, pattern));
		            }else{
		                currMonthCal.setTime(cacheDateList.get(index));
		                currMonthCal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
		                minMothDate = currMonthCal.getTime();

		                //设置当前月最后一天
		                currMonthCal.set(Calendar.DAY_OF_MONTH, currMonthCal.getActualMaximum(Calendar.DAY_OF_MONTH));
		                maxMothDate = currMonthCal.getTime();
		                dWorktraceMonthDate.setMonthStartDate(minMothDate);
		                dWorktraceMonthDate.setMonthEndDate(maxMothDate);
		                betWeenDay = CommonUtils.daysBetween(CommonUtils.getDateString(minMothDate, pattern),   CommonUtils.getDateString(maxMothDate, pattern));
		            }
		            
		            System.out.println(currMonthCal.get(Calendar.YEAR )+"年"+(currMonthCal.get(Calendar.MONTH )+1)+"月共 "+(betWeenDay+1) +" 天");
		            dWorktraceMonthDate.setInYear(currMonthCal.get(Calendar.YEAR));
		            dWorktraceMonthDate.setInMonth(currMonthCal.get(Calendar.MONTH )+1);
		            dWorktraceMonthDate.setWorkDay(betWeenDay+1);
		            System.out.println("-----dWorktraceMonthDate-----:"+dWorktraceMonthDate);
		            dWorktraceMonthDateList.add(dWorktraceMonthDate);
		        }
		  }else if(maxDate.getTime() == minDate.getTime() ){
				DeviceWorktraceMonthDate dWorktraceMonthDate= new DeviceWorktraceMonthDate();
			  System.out.println(currCalendar.get(Calendar.YEAR )+"年"+(currCalendar.get(Calendar.MONTH )+1)+"月共 "+1+" 天");
			  dWorktraceMonthDate.setMonthStartDate(minDate);
			  dWorktraceMonthDate.setMonthEndDate(maxDate);
	            dWorktraceMonthDate.setInYear(currCalendar.get(Calendar.YEAR));
	            dWorktraceMonthDate.setInMonth(currCalendar.get(Calendar.MONTH )+1);
	            dWorktraceMonthDate.setWorkDay(1);
	            System.out.println("-----dWorktraceMonthDate-----:"+dWorktraceMonthDate);
	            dWorktraceMonthDateList.add(dWorktraceMonthDate);
		  }
	       
		 }catch(Exception e){
			 e.printStackTrace();
		 }
		 
		 return dWorktraceMonthDateList;
	}
	public static void main(String[] args) {
		  String startDatestr = "2017-12-27";
		  String endDatestr = "2017-12-27";
		
		  
		  List<DeviceWorktraceMonthDate> strlist =  getDeviceWorktraceMonthDateList(startDatestr,endDatestr);
		  System.out.println(strlist.size());
	      
	}

}
