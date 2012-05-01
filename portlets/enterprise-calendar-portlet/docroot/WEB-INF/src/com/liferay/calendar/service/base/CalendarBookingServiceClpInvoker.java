/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.calendar.service.base;

import com.liferay.calendar.service.CalendarBookingServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class CalendarBookingServiceClpInvoker {
	public CalendarBookingServiceClpInvoker() {
		_methodName38 = "getBeanIdentifier";

		_methodParameterTypes38 = new String[] {  };

		_methodName39 = "setBeanIdentifier";

		_methodParameterTypes39 = new String[] { "java.lang.String" };

		_methodName44 = "addCalendarBooking";

		_methodParameterTypes44 = new String[] {
				"long", "long", "java.util.Map", "java.util.Map",
				"java.util.Map", "java.lang.String", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "boolean",
				"java.lang.String", "java.lang.Integer", "boolean", "int", "int",
				"boolean", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName45 = "deleteCalendarBooking";

		_methodParameterTypes45 = new String[] { "long" };

		_methodName46 = "getCalendarBooking";

		_methodParameterTypes46 = new String[] { "long" };

		_methodName47 = "getCalendarBookings";

		_methodParameterTypes47 = new String[] {
				"long", "java.util.Date", "java.util.Date"
			};

		_methodName48 = "search";

		_methodParameterTypes48 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "java.util.Date", "java.util.Date",
				"java.lang.Integer", "int", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName49 = "search";

		_methodParameterTypes49 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.util.Date", "java.util.Date",
				"java.lang.Integer", "int", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName50 = "searchCount";

		_methodParameterTypes50 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "java.util.Date", "java.util.Date",
				"java.lang.Integer", "int"
			};

		_methodName51 = "searchCount";

		_methodParameterTypes51 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"java.lang.String", "java.util.Date", "java.util.Date",
				"java.lang.Integer", "int", "boolean"
			};

		_methodName52 = "updateCalendarBooking";

		_methodParameterTypes52 = new String[] {
				"long", "long", "java.util.Map", "java.util.Map",
				"java.util.Map", "java.lang.String", "int", "int", "int", "int",
				"int", "int", "int", "int", "int", "int", "int", "boolean",
				"java.lang.String", "java.lang.Integer", "boolean", "int", "int",
				"boolean", "java.lang.String", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName38.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes38, parameterTypes)) {
			return CalendarBookingServiceUtil.getBeanIdentifier();
		}

		if (_methodName39.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes39, parameterTypes)) {
			CalendarBookingServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName44.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes44, parameterTypes)) {
			return CalendarBookingServiceUtil.addCalendarBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				((Integer)arguments[15]).intValue(),
				((Boolean)arguments[16]).booleanValue(),
				(java.lang.String)arguments[17],
				(java.lang.Integer)arguments[18],
				((Boolean)arguments[19]).booleanValue(),
				((Integer)arguments[20]).intValue(),
				((Integer)arguments[21]).intValue(),
				((Boolean)arguments[22]).booleanValue(),
				(java.lang.String)arguments[23],
				(java.lang.String)arguments[24],
				(com.liferay.portal.service.ServiceContext)arguments[25]);
		}

		if (_methodName45.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes45, parameterTypes)) {
			return CalendarBookingServiceUtil.deleteCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName46.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes46, parameterTypes)) {
			return CalendarBookingServiceUtil.getCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName47.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes47, parameterTypes)) {
			return CalendarBookingServiceUtil.getCalendarBookings(((Long)arguments[0]).longValue(),
				(java.util.Date)arguments[1], (java.util.Date)arguments[2]);
		}

		if (_methodName48.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes48, parameterTypes)) {
			return CalendarBookingServiceUtil.search(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(long[])arguments[3], ((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5], (java.util.Date)arguments[6],
				(java.util.Date)arguments[7], (java.lang.Integer)arguments[8],
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[12]);
		}

		if (_methodName49.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes49, parameterTypes)) {
			return CalendarBookingServiceUtil.search(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(long[])arguments[3], ((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				(java.util.Date)arguments[9], (java.util.Date)arguments[10],
				(java.lang.Integer)arguments[11],
				((Integer)arguments[12]).intValue(),
				((Boolean)arguments[13]).booleanValue(),
				((Integer)arguments[14]).intValue(),
				((Integer)arguments[15]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[16]);
		}

		if (_methodName50.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes50, parameterTypes)) {
			return CalendarBookingServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(long[])arguments[3], ((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5], (java.util.Date)arguments[6],
				(java.util.Date)arguments[7], (java.lang.Integer)arguments[8],
				((Integer)arguments[9]).intValue());
		}

		if (_methodName51.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes51, parameterTypes)) {
			return CalendarBookingServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(long[])arguments[3], ((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7], (java.lang.String)arguments[8],
				(java.util.Date)arguments[9], (java.util.Date)arguments[10],
				(java.lang.Integer)arguments[11],
				((Integer)arguments[12]).intValue(),
				((Boolean)arguments[13]).booleanValue());
		}

		if (_methodName52.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes52, parameterTypes)) {
			return CalendarBookingServiceUtil.updateCalendarBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(java.lang.String)arguments[5],
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				((Integer)arguments[12]).intValue(),
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				((Integer)arguments[15]).intValue(),
				((Integer)arguments[16]).intValue(),
				((Boolean)arguments[17]).booleanValue(),
				(java.lang.String)arguments[18],
				(java.lang.Integer)arguments[19],
				((Boolean)arguments[20]).booleanValue(),
				((Integer)arguments[21]).intValue(),
				((Integer)arguments[22]).intValue(),
				((Boolean)arguments[23]).booleanValue(),
				(java.lang.String)arguments[24],
				(java.lang.String)arguments[25],
				(com.liferay.portal.service.ServiceContext)arguments[26]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName38;
	private String[] _methodParameterTypes38;
	private String _methodName39;
	private String[] _methodParameterTypes39;
	private String _methodName44;
	private String[] _methodParameterTypes44;
	private String _methodName45;
	private String[] _methodParameterTypes45;
	private String _methodName46;
	private String[] _methodParameterTypes46;
	private String _methodName47;
	private String[] _methodParameterTypes47;
	private String _methodName48;
	private String[] _methodParameterTypes48;
	private String _methodName49;
	private String[] _methodParameterTypes49;
	private String _methodName50;
	private String[] _methodParameterTypes50;
	private String _methodName51;
	private String[] _methodParameterTypes51;
	private String _methodName52;
	private String[] _methodParameterTypes52;
}