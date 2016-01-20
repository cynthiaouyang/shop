package com.graduation.project.hui.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import ognl.DefaultTypeConverter;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

public class DateConverter extends DefaultTypeConverter {

	private static final Logger logger = Logger.getLogger(DateConverter.class);

	private static final String[] DATE_PATTERNS = {"yyyy-MM-dd hh:mm:ss","yyyy-MM-dd"};
//	private static final String DATE_PATTERN = "yyyy-MM-dd";

	/**
	 * Convert value between types
	 */
	@SuppressWarnings("unchecked")
	public Object convertValue(Map ognlContext, Object value, Class toType) {
		Object result = null;
		if (toType == Date.class) {
			result = doConvertToDate(value);
		} else if (toType == String.class) {
			result = doConvertToString(value);
		}
		return result;
	}

	/**
	 * Convert String to Date
	 *
	 * @param value
	 * @return
	 */
	private Date doConvertToDate(Object value) {
		Date result = null;
		if (value instanceof String) {
			 for (String format : DATE_PATTERNS)
			    {
			        try
			        {
			            result=new SimpleDateFormat(format).parse((String) value);
			        }
			        catch (ParseException e1) {
			        	e1.printStackTrace();
			        }
			    }

//			try {
//				SimpleDateFormat dt = new SimpleDateFormat(DATE_PATTERNS);
//				result = (Date) dt.parse((String) value);
//				if(result==null){
//					dt = new SimpleDateFormat(DATE_PATTERN);
//					result = (Date) dt.parse((String) value);
//				}
//			} catch (ParseException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}

			// all patterns failed, try a milliseconds constructor
			if (result == null && StringUtils.isNotEmpty((String) value)) {

				try {
					result = new Date(new Long((String) value).longValue());
				} catch (Exception e) {
					logger.error("Converting from milliseconds to Date fails!");
					e.printStackTrace();
				}

			}

		} else if (value instanceof Object[]) {
			// let's try to convert the first element only
			Object[] array = (Object[]) value;

			if ((array != null) && (array.length >= 1)) {
				value = array[0];
				result = doConvertToDate(value);
			}

		} else if (Date.class.isAssignableFrom(value.getClass())) {
			result = (Date) value;
		}
		return result;
	}

	/**
	 * Convert Date to String
	 *
	 * @param value
	 * @return
	 */
	private String doConvertToString(Object value) {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
//				DATETIME_PATTERN);
		String result = null;
		if (value instanceof Date) {
			for (String format : DATE_PATTERNS)
		    {
		        result=new SimpleDateFormat(format).format(value);
		    }
//			result = simpleDateFormat.format(value);
//			if(result==null){
//				simpleDateFormat = new SimpleDateFormat(DATE_PATTERN);
//				result = simpleDateFormat.format(value);
//			}
		}
		return result;
	}
}
