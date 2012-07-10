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

import com.liferay.calendar.service.CalendarBookingLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 */
public class CalendarBookingLocalServiceClpInvoker {
	public CalendarBookingLocalServiceClpInvoker() {
		_methodName0 = "addCalendarBooking";

		_methodParameterTypes0 = new String[] {
				"com.liferay.calendar.model.CalendarBooking"
			};

		_methodName1 = "createCalendarBooking";

		_methodParameterTypes1 = new String[] { "long" };

		_methodName2 = "deleteCalendarBooking";

		_methodParameterTypes2 = new String[] { "long" };

		_methodName3 = "deleteCalendarBooking";

		_methodParameterTypes3 = new String[] {
				"com.liferay.calendar.model.CalendarBooking"
			};

		_methodName4 = "dynamicQuery";

		_methodParameterTypes4 = new String[] {  };

		_methodName5 = "dynamicQuery";

		_methodParameterTypes5 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName6 = "dynamicQuery";

		_methodParameterTypes6 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int"
			};

		_methodName7 = "dynamicQuery";

		_methodParameterTypes7 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName8 = "dynamicQueryCount";

		_methodParameterTypes8 = new String[] {
				"com.liferay.portal.kernel.dao.orm.DynamicQuery"
			};

		_methodName9 = "fetchCalendarBooking";

		_methodParameterTypes9 = new String[] { "long" };

		_methodName10 = "getCalendarBooking";

		_methodParameterTypes10 = new String[] { "long" };

		_methodName11 = "getPersistedModel";

		_methodParameterTypes11 = new String[] { "java.io.Serializable" };

		_methodName12 = "getCalendarBookingByUuidAndGroupId";

		_methodParameterTypes12 = new String[] { "java.lang.String", "long" };

		_methodName13 = "getCalendarBookings";

		_methodParameterTypes13 = new String[] { "int", "int" };

		_methodName14 = "getCalendarBookingsCount";

		_methodParameterTypes14 = new String[] {  };

		_methodName15 = "updateCalendarBooking";

		_methodParameterTypes15 = new String[] {
				"com.liferay.calendar.model.CalendarBooking"
			};

		_methodName16 = "updateCalendarBooking";

		_methodParameterTypes16 = new String[] {
				"com.liferay.calendar.model.CalendarBooking", "boolean"
			};

		_methodName55 = "getBeanIdentifier";

		_methodParameterTypes55 = new String[] {  };

		_methodName56 = "setBeanIdentifier";

		_methodParameterTypes56 = new String[] { "java.lang.String" };

		_methodName61 = "addCalendarBooking";

		_methodParameterTypes61 = new String[] {
				"long", "long", "long[][]", "long", "java.util.Map",
				"java.util.Map", "java.lang.String", "long", "long", "boolean",
				"java.lang.String", "long", "java.lang.String", "long",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName62 = "checkCalendarBookings";

		_methodParameterTypes62 = new String[] {  };

		_methodName63 = "deleteCalendarBooking";

		_methodParameterTypes63 = new String[] {
				"com.liferay.calendar.model.CalendarBooking"
			};

		_methodName64 = "deleteCalendarBooking";

		_methodParameterTypes64 = new String[] { "long" };

		_methodName65 = "deleteCalendarBookings";

		_methodParameterTypes65 = new String[] { "long" };

		_methodName66 = "fetchCalendarBooking";

		_methodParameterTypes66 = new String[] { "java.lang.String", "long" };

		_methodName67 = "getCalendarBooking";

		_methodParameterTypes67 = new String[] { "long" };

		_methodName68 = "getCalendarBooking";

		_methodParameterTypes68 = new String[] { "long", "long" };

		_methodName69 = "getCalendarBookings";

		_methodParameterTypes69 = new String[] { "long" };

		_methodName70 = "getCalendarBookings";

		_methodParameterTypes70 = new String[] { "long", "long", "long" };

		_methodName71 = "getCalendarBookingsCount";

		_methodParameterTypes71 = new String[] { "long", "long" };

		_methodName72 = "getChildCalendarBookings";

		_methodParameterTypes72 = new String[] { "long" };

		_methodName73 = "getChildCalendarBookings";

		_methodParameterTypes73 = new String[] { "long", "int" };

		_methodName74 = "search";

		_methodParameterTypes74 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "long", "long", "boolean", "int[][]", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName75 = "search";

		_methodParameterTypes75 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"long", "long", "boolean", "int[][]", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName76 = "searchCount";

		_methodParameterTypes76 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "long", "long", "int[][]"
			};

		_methodName77 = "searchCount";

		_methodParameterTypes77 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"long", "long", "int[][]", "boolean"
			};

		_methodName78 = "updateCalendarBooking";

		_methodParameterTypes78 = new String[] {
				"long", "long", "long", "long[][]", "java.util.Map",
				"java.util.Map", "java.lang.String", "long", "long", "boolean",
				"java.lang.String", "long", "java.lang.String", "long",
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName79 = "updateCalendarBooking";

		_methodParameterTypes79 = new String[] {
				"long", "long", "long", "java.util.Map", "java.util.Map",
				"java.lang.String", "long", "long", "boolean",
				"java.lang.String", "long", "java.lang.String", "long",
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName80 = "updateStatus";

		_methodParameterTypes80 = new String[] {
				"long", "long", "int",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName0.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes0, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.addCalendarBooking((com.liferay.calendar.model.CalendarBooking)arguments[0]);
		}

		if (_methodName1.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes1, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.createCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName2.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes2, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.deleteCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName3.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes3, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.deleteCalendarBooking((com.liferay.calendar.model.CalendarBooking)arguments[0]);
		}

		if (_methodName4.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes4, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.dynamicQuery();
		}

		if (_methodName5.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes5, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName6.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes6, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue());
		}

		if (_methodName7.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes7, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.dynamicQuery((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0],
				((Integer)arguments[1]).intValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[3]);
		}

		if (_methodName8.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes8, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.dynamicQueryCount((com.liferay.portal.kernel.dao.orm.DynamicQuery)arguments[0]);
		}

		if (_methodName9.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes9, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.fetchCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName10.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes10, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName11.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes11, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getPersistedModel((java.io.Serializable)arguments[0]);
		}

		if (_methodName12.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes12, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getCalendarBookingByUuidAndGroupId((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName13.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes13, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getCalendarBookings(((Integer)arguments[0]).intValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName14.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes14, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getCalendarBookingsCount();
		}

		if (_methodName15.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes15, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.updateCalendarBooking((com.liferay.calendar.model.CalendarBooking)arguments[0]);
		}

		if (_methodName16.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes16, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.updateCalendarBooking((com.liferay.calendar.model.CalendarBooking)arguments[0],
				((Boolean)arguments[1]).booleanValue());
		}

		if (_methodName55.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes55, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			CalendarBookingLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);
		}

		if (_methodName61.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes61, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.addCalendarBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (long[])arguments[2],
				((Long)arguments[3]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[5],
				(java.lang.String)arguments[6],
				((Long)arguments[7]).longValue(),
				((Long)arguments[8]).longValue(),
				((Boolean)arguments[9]).booleanValue(),
				(java.lang.String)arguments[10],
				((Long)arguments[11]).longValue(),
				(java.lang.String)arguments[12],
				((Long)arguments[13]).longValue(),
				(java.lang.String)arguments[14],
				(com.liferay.portal.service.ServiceContext)arguments[15]);
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			CalendarBookingLocalServiceUtil.checkCalendarBookings();
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.deleteCalendarBooking((com.liferay.calendar.model.CalendarBooking)arguments[0]);
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.deleteCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			CalendarBookingLocalServiceUtil.deleteCalendarBookings(((Long)arguments[0]).longValue());
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.fetchCalendarBooking((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue());
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getCalendarBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getCalendarBookings(((Long)arguments[0]).longValue());
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getCalendarBookings(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName71.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes71, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getCalendarBookingsCount(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getChildCalendarBookings(((Long)arguments[0]).longValue());
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.getChildCalendarBookings(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(long[])arguments[3], ((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5],
				((Long)arguments[6]).longValue(),
				((Long)arguments[7]).longValue(),
				((Boolean)arguments[8]).booleanValue(), (int[])arguments[9],
				((Integer)arguments[10]).intValue(),
				((Integer)arguments[11]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[12]);
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.search(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(long[])arguments[3], ((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7],
				((Long)arguments[8]).longValue(),
				((Long)arguments[9]).longValue(),
				((Boolean)arguments[10]).booleanValue(), (int[])arguments[11],
				((Boolean)arguments[12]).booleanValue(),
				((Integer)arguments[13]).intValue(),
				((Integer)arguments[14]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[15]);
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(long[])arguments[3], ((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5],
				((Long)arguments[6]).longValue(),
				((Long)arguments[7]).longValue(), (int[])arguments[8]);
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(long[])arguments[3], ((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7],
				((Long)arguments[8]).longValue(),
				((Long)arguments[9]).longValue(), (int[])arguments[10],
				((Boolean)arguments[11]).booleanValue());
		}

		if (_methodName78.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes78, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.updateCalendarBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(), (long[])arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[5],
				(java.lang.String)arguments[6],
				((Long)arguments[7]).longValue(),
				((Long)arguments[8]).longValue(),
				((Boolean)arguments[9]).booleanValue(),
				(java.lang.String)arguments[10],
				((Long)arguments[11]).longValue(),
				(java.lang.String)arguments[12],
				((Long)arguments[13]).longValue(),
				(java.lang.String)arguments[14],
				((Integer)arguments[15]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[16]);
		}

		if (_methodName79.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes79, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.updateCalendarBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(java.lang.String)arguments[5],
				((Long)arguments[6]).longValue(),
				((Long)arguments[7]).longValue(),
				((Boolean)arguments[8]).booleanValue(),
				(java.lang.String)arguments[9],
				((Long)arguments[10]).longValue(),
				(java.lang.String)arguments[11],
				((Long)arguments[12]).longValue(),
				(java.lang.String)arguments[13],
				((Integer)arguments[14]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[15]);
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return CalendarBookingLocalServiceUtil.updateStatus(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Integer)arguments[2]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName0;
	private String[] _methodParameterTypes0;
	private String _methodName1;
	private String[] _methodParameterTypes1;
	private String _methodName2;
	private String[] _methodParameterTypes2;
	private String _methodName3;
	private String[] _methodParameterTypes3;
	private String _methodName4;
	private String[] _methodParameterTypes4;
	private String _methodName5;
	private String[] _methodParameterTypes5;
	private String _methodName6;
	private String[] _methodParameterTypes6;
	private String _methodName7;
	private String[] _methodParameterTypes7;
	private String _methodName8;
	private String[] _methodParameterTypes8;
	private String _methodName9;
	private String[] _methodParameterTypes9;
	private String _methodName10;
	private String[] _methodParameterTypes10;
	private String _methodName11;
	private String[] _methodParameterTypes11;
	private String _methodName12;
	private String[] _methodParameterTypes12;
	private String _methodName13;
	private String[] _methodParameterTypes13;
	private String _methodName14;
	private String[] _methodParameterTypes14;
	private String _methodName15;
	private String[] _methodParameterTypes15;
	private String _methodName16;
	private String[] _methodParameterTypes16;
	private String _methodName55;
	private String[] _methodParameterTypes55;
	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName61;
	private String[] _methodParameterTypes61;
	private String _methodName62;
	private String[] _methodParameterTypes62;
	private String _methodName63;
	private String[] _methodParameterTypes63;
	private String _methodName64;
	private String[] _methodParameterTypes64;
	private String _methodName65;
	private String[] _methodParameterTypes65;
	private String _methodName66;
	private String[] _methodParameterTypes66;
	private String _methodName67;
	private String[] _methodParameterTypes67;
	private String _methodName68;
	private String[] _methodParameterTypes68;
	private String _methodName69;
	private String[] _methodParameterTypes69;
	private String _methodName70;
	private String[] _methodParameterTypes70;
	private String _methodName71;
	private String[] _methodParameterTypes71;
	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
	private String _methodName78;
	private String[] _methodParameterTypes78;
	private String _methodName79;
	private String[] _methodParameterTypes79;
	private String _methodName80;
	private String[] _methodParameterTypes80;
}