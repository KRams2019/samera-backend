package com.mindtree.encryption.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {
	/**
	 * @param date
	 * @return formated date in pattern "yyyy-MM-dd HH:mm:ss"
	 */
	public static String convertUtilDateToString(Date date) 
	{
		final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	public static boolean checkTimeDiff(String tokenAllocatedtime,String currentDateTime)
	{
		tokenAllocatedtime = tokenAllocatedtime.trim();
		currentDateTime = currentDateTime.trim();
		String splitTokenAllocatedtime[] = tokenAllocatedtime.split(" ");
		String splitCheckTokenExpiry[] = currentDateTime.split(" ");
//		==========================================================
		String splitTokenAllocatedtimeDay[] = splitTokenAllocatedtime[0].split("-"); 
		String splitCheckTokenExpirytimeDay[] = splitCheckTokenExpiry[0].split("-");
//		==========================================================		
		
		////check day
		if(!isDaySame(splitCheckTokenExpirytimeDay,splitTokenAllocatedtimeDay))
		{
			return false;
		}
		 splitTokenAllocatedtimeDay = splitTokenAllocatedtime[1].split(":"); 
		 splitCheckTokenExpirytimeDay = splitCheckTokenExpiry[1].split(":");
		if(!isTimeThreshold(splitCheckTokenExpirytimeDay,splitTokenAllocatedtimeDay))
		{
			return false;
		}
		return true;
	}

	private static boolean isTimeThreshold(String[] splitCheckTokenExpirytimeDay,
			String[] splitTokenAllocatedtimeDay) 
	{
		long splitCheckTokenExpirytimeDaySeconds =  convertToSeconds(splitCheckTokenExpirytimeDay);
		long splitTokenAllocatedtimeDaySeconds =  convertToSeconds(splitTokenAllocatedtimeDay);
		if(splitCheckTokenExpirytimeDaySeconds - splitTokenAllocatedtimeDaySeconds<=1800)
		{
			return true;
		}
		return false;
	}

	private static long convertToSeconds(String[] splitCheckTokenExpirytimeDay) {

		long sec = Long.parseLong(splitCheckTokenExpirytimeDay[2]);
		sec = sec + Long.parseLong(splitCheckTokenExpirytimeDay[1])*60;
		sec = sec + Long.parseLong(splitCheckTokenExpirytimeDay[0])*60*60;
		System.out.println(sec);
		return sec;
	}

	private static boolean isDaySame(String[] splitCheckTokenExpirytimeDay, String[] splitTokenAllocatedtimeDay) {
		for(int i=0;i<splitCheckTokenExpirytimeDay.length;i++)
		{
			if(Integer.parseInt(splitCheckTokenExpirytimeDay[i])-Integer.parseInt(splitTokenAllocatedtimeDay[i])!=0)
				return false;
		}
		return true;
	}
}
