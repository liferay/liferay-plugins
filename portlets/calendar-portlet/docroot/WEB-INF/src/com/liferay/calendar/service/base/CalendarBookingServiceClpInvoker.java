/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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
 * @author Eduardo Lundgren
 * @generated
 */
public class CalendarBookingServiceClpInvoker {
	public CalendarBookingServiceClpInvoker() {
		_methodName74 = "getBeanIdentifier";

		_methodParameterTypes74 = new String[] {  };

		_methodName75 = "setBeanIdentifier";

		_methodParameterTypes75 = new String[] { "java.lang.String" };

		_methodName80 = "addCalendarBooking";

		_methodParameterTypes80 = new String[] {
				"long", "long[][]", "long", "java.util.Map", "java.util.Map",
				"java.lang.String", "long", "long", "boolean",
				"java.lang.String", "long", "java.lang.String", "long",
				"java.lang.String", "com.liferay.portal.service.ServiceContext"
			};

		_methodName81 = "deleteCalendarBooking";

		_methodParameterTypes81 = new String[] { "long" };

		_methodName82 = "deleteCalendarBookingInstance";

		_methodParameterTypes82 = new String[] { "long", "long", "boolean" };

		_methodName83 = "fetchCalendarBooking";

		_methodParameterTypes83 = new String[] { "long" };

		_methodName84 = "getCalendarBooking";

		_methodParameterTypes84 = new String[] { "long" };

		_methodName85 = "getCalendarBooking";

		_methodParameterTypes85 = new String[] { "long", "long" };

		_methodName86 = "getCalendarBookings";

		_methodParameterTypes86 = new String[] { "long", "long", "long" };

		_methodName87 = "getCalendarBookings";

		_methodParameterTypes87 = new String[] { "long", "long", "long", "int" };

		_methodName88 = "getCalendarBookingsRSS";

		_methodParameterTypes88 = new String[] {
				"long", "long", "long", "int", "java.lang.String", "double",
				"java.lang.String", "com.liferay.portal.theme.ThemeDisplay"
			};

		_methodName89 = "getChildCalendarBookings";

		_methodParameterTypes89 = new String[] { "long" };

		_methodName90 = "getChildCalendarBookings";

		_methodParameterTypes90 = new String[] { "long", "int" };

		_methodName91 = "invokeTransition";

		_methodParameterTypes91 = new String[] {
				"long", "java.lang.String",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName92 = "search";

		_methodParameterTypes92 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "long", "long", "boolean", "int[][]", "int",
				"int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName93 = "search";

		_methodParameterTypes93 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"long", "long", "boolean", "int[][]", "boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName94 = "searchCount";

		_methodParameterTypes94 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "long", "long", "boolean", "int[][]"
			};

		_methodName95 = "searchCount";

		_methodParameterTypes95 = new String[] {
				"long", "long[][]", "long[][]", "long[][]", "long",
				"java.lang.String", "java.lang.String", "java.lang.String",
				"long", "long", "boolean", "int[][]", "boolean"
			};

		_methodName96 = "updateCalendarBooking";

		_methodParameterTypes96 = new String[] {
				"long", "long", "long[][]", "java.util.Map", "java.util.Map",
				"java.lang.String", "long", "long", "boolean",
				"java.lang.String", "long", "java.lang.String", "long",
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName97 = "updateCalendarBooking";

		_methodParameterTypes97 = new String[] {
				"long", "long", "java.util.Map", "java.util.Map",
				"java.lang.String", "long", "long", "boolean",
				"java.lang.String", "long", "java.lang.String", "long",
				"java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName98 = "updateCalendarBookingInstance";

		_methodParameterTypes98 = new String[] {
				"long", "long", "long[][]", "java.util.Map", "java.util.Map",
				"java.lang.String", "long", "long", "boolean",
				"java.lang.String", "boolean", "long", "java.lang.String",
				"long", "java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName99 = "updateCalendarBookingInstance";

		_methodParameterTypes99 = new String[] {
				"long", "long", "java.util.Map", "java.util.Map",
				"java.lang.String", "long", "long", "boolean",
				"java.lang.String", "boolean", "long", "java.lang.String",
				"long", "java.lang.String", "int",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName74.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes74, parameterTypes)) {
			return CalendarBookingServiceUtil.getBeanIdentifier();
		}

		if (_methodName75.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes75, parameterTypes)) {
			CalendarBookingServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName80.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes80, parameterTypes)) {
			return CalendarBookingServiceUtil.addCalendarBooking(((Long)arguments[0]).longValue(),
				(long[])arguments[1], ((Long)arguments[2]).longValue(),
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
				(com.liferay.portal.service.ServiceContext)arguments[14]);
		}

		if (_methodName81.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes81, parameterTypes)) {
			return CalendarBookingServiceUtil.deleteCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			CalendarBookingServiceUtil.deleteCalendarBookingInstance(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Boolean)arguments[2]).booleanValue());

			return null;
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			return CalendarBookingServiceUtil.fetchCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName84.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes84, parameterTypes)) {
			return CalendarBookingServiceUtil.getCalendarBooking(((Long)arguments[0]).longValue());
		}

		if (_methodName85.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes85, parameterTypes)) {
			return CalendarBookingServiceUtil.getCalendarBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return CalendarBookingServiceUtil.getCalendarBookings(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue());
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return CalendarBookingServiceUtil.getCalendarBookings(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue());
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return CalendarBookingServiceUtil.getCalendarBookingsRSS(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				((Integer)arguments[3]).intValue(),
				(java.lang.String)arguments[4],
				((Double)arguments[5]).doubleValue(),
				(java.lang.String)arguments[6],
				(com.liferay.portal.theme.ThemeDisplay)arguments[7]);
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return CalendarBookingServiceUtil.getChildCalendarBookings(((Long)arguments[0]).longValue());
		}

		if (_methodName90.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes90, parameterTypes)) {
			return CalendarBookingServiceUtil.getChildCalendarBookings(((Long)arguments[0]).longValue(),
				((Integer)arguments[1]).intValue());
		}

		if (_methodName91.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes91, parameterTypes)) {
			CalendarBookingServiceUtil.invokeTransition(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1],
				(com.liferay.portal.service.ServiceContext)arguments[2]);

			return null;
		}

		if (_methodName92.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes92, parameterTypes)) {
			return CalendarBookingServiceUtil.search(((Long)arguments[0]).longValue(),
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

		if (_methodName93.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes93, parameterTypes)) {
			return CalendarBookingServiceUtil.search(((Long)arguments[0]).longValue(),
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

		if (_methodName94.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes94, parameterTypes)) {
			return CalendarBookingServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(long[])arguments[3], ((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5],
				((Long)arguments[6]).longValue(),
				((Long)arguments[7]).longValue(),
				((Boolean)arguments[8]).booleanValue(), (int[])arguments[9]);
		}

		if (_methodName95.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes95, parameterTypes)) {
			return CalendarBookingServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(long[])arguments[3], ((Long)arguments[4]).longValue(),
				(java.lang.String)arguments[5], (java.lang.String)arguments[6],
				(java.lang.String)arguments[7],
				((Long)arguments[8]).longValue(),
				((Long)arguments[9]).longValue(),
				((Boolean)arguments[10]).booleanValue(), (int[])arguments[11],
				((Boolean)arguments[12]).booleanValue());
		}

		if (_methodName96.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes96, parameterTypes)) {
			return CalendarBookingServiceUtil.updateCalendarBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (long[])arguments[2],
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

		if (_methodName97.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes97, parameterTypes)) {
			return CalendarBookingServiceUtil.updateCalendarBooking(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.lang.String)arguments[4],
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue(),
				((Boolean)arguments[7]).booleanValue(),
				(java.lang.String)arguments[8],
				((Long)arguments[9]).longValue(),
				(java.lang.String)arguments[10],
				((Long)arguments[11]).longValue(),
				(java.lang.String)arguments[12],
				((Integer)arguments[13]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[14]);
		}

		if (_methodName98.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes98, parameterTypes)) {
			return CalendarBookingServiceUtil.updateCalendarBookingInstance(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(), (long[])arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[4],
				(java.lang.String)arguments[5],
				((Long)arguments[6]).longValue(),
				((Long)arguments[7]).longValue(),
				((Boolean)arguments[8]).booleanValue(),
				(java.lang.String)arguments[9],
				((Boolean)arguments[10]).booleanValue(),
				((Long)arguments[11]).longValue(),
				(java.lang.String)arguments[12],
				((Long)arguments[13]).longValue(),
				(java.lang.String)arguments[14],
				((Integer)arguments[15]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[16]);
		}

		if (_methodName99.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes99, parameterTypes)) {
			return CalendarBookingServiceUtil.updateCalendarBookingInstance(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[3],
				(java.lang.String)arguments[4],
				((Long)arguments[5]).longValue(),
				((Long)arguments[6]).longValue(),
				((Boolean)arguments[7]).booleanValue(),
				(java.lang.String)arguments[8],
				((Boolean)arguments[9]).booleanValue(),
				((Long)arguments[10]).longValue(),
				(java.lang.String)arguments[11],
				((Long)arguments[12]).longValue(),
				(java.lang.String)arguments[13],
				((Integer)arguments[14]).intValue(),
				(com.liferay.portal.service.ServiceContext)arguments[15]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName74;
	private String[] _methodParameterTypes74;
	private String _methodName75;
	private String[] _methodParameterTypes75;
	private String _methodName80;
	private String[] _methodParameterTypes80;
	private String _methodName81;
	private String[] _methodParameterTypes81;
	private String _methodName82;
	private String[] _methodParameterTypes82;
	private String _methodName83;
	private String[] _methodParameterTypes83;
	private String _methodName84;
	private String[] _methodParameterTypes84;
	private String _methodName85;
	private String[] _methodParameterTypes85;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName89;
	private String[] _methodParameterTypes89;
	private String _methodName90;
	private String[] _methodParameterTypes90;
	private String _methodName91;
	private String[] _methodParameterTypes91;
	private String _methodName92;
	private String[] _methodParameterTypes92;
	private String _methodName93;
	private String[] _methodParameterTypes93;
	private String _methodName94;
	private String[] _methodParameterTypes94;
	private String _methodName95;
	private String[] _methodParameterTypes95;
	private String _methodName96;
	private String[] _methodParameterTypes96;
	private String _methodName97;
	private String[] _methodParameterTypes97;
	private String _methodName98;
	private String[] _methodParameterTypes98;
	private String _methodName99;
	private String[] _methodParameterTypes99;
}