/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import com.liferay.calendar.service.CalendarResourceServiceUtil;

import java.util.Arrays;

/**
 * @author Eduardo Lundgren
 * @generated
 */
public class CalendarResourceServiceClpInvoker {
	public CalendarResourceServiceClpInvoker() {
		_methodName56 = "getBeanIdentifier";

		_methodParameterTypes56 = new String[] {  };

		_methodName57 = "setBeanIdentifier";

		_methodParameterTypes57 = new String[] { "java.lang.String" };

		_methodName62 = "addCalendarResource";

		_methodParameterTypes62 = new String[] {
				"long", "long", "long", "java.lang.String", "java.lang.String",
				"java.util.Map", "java.util.Map", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};

		_methodName63 = "deleteCalendarResource";

		_methodParameterTypes63 = new String[] { "long" };

		_methodName64 = "fetchCalendarResource";

		_methodParameterTypes64 = new String[] { "long", "long" };

		_methodName65 = "getCalendarResource";

		_methodParameterTypes65 = new String[] { "long" };

		_methodName66 = "search";

		_methodParameterTypes66 = new String[] {
				"long", "long[][]", "long[][]", "java.lang.String", "boolean",
				"boolean", "int", "int",
				"com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName67 = "search";

		_methodParameterTypes67 = new String[] {
				"long", "long[][]", "long[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "boolean",
				"int", "int", "com.liferay.portal.kernel.util.OrderByComparator"
			};

		_methodName68 = "searchCount";

		_methodParameterTypes68 = new String[] {
				"long", "long[][]", "long[][]", "java.lang.String", "boolean"
			};

		_methodName69 = "searchCount";

		_methodParameterTypes69 = new String[] {
				"long", "long[][]", "long[][]", "java.lang.String",
				"java.lang.String", "java.lang.String", "boolean", "boolean"
			};

		_methodName70 = "updateCalendarResource";

		_methodParameterTypes70 = new String[] {
				"long", "java.util.Map", "java.util.Map", "boolean",
				"com.liferay.portal.service.ServiceContext"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName56.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes56, parameterTypes)) {
			return CalendarResourceServiceUtil.getBeanIdentifier();
		}

		if (_methodName57.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes57, parameterTypes)) {
			CalendarResourceServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName62.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes62, parameterTypes)) {
			return CalendarResourceServiceUtil.addCalendarResource(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				((Long)arguments[2]).longValue(),
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[5],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[6],
				((Boolean)arguments[7]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[8]);
		}

		if (_methodName63.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes63, parameterTypes)) {
			return CalendarResourceServiceUtil.deleteCalendarResource(((Long)arguments[0]).longValue());
		}

		if (_methodName64.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes64, parameterTypes)) {
			return CalendarResourceServiceUtil.fetchCalendarResource(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue());
		}

		if (_methodName65.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes65, parameterTypes)) {
			return CalendarResourceServiceUtil.getCalendarResource(((Long)arguments[0]).longValue());
		}

		if (_methodName66.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes66, parameterTypes)) {
			return CalendarResourceServiceUtil.search(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue(),
				((Boolean)arguments[5]).booleanValue(),
				((Integer)arguments[6]).intValue(),
				((Integer)arguments[7]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[8]);
		}

		if (_methodName67.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes67, parameterTypes)) {
			return CalendarResourceServiceUtil.search(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				((Boolean)arguments[7]).booleanValue(),
				((Integer)arguments[8]).intValue(),
				((Integer)arguments[9]).intValue(),
				(com.liferay.portal.kernel.util.OrderByComparator)arguments[10]);
		}

		if (_methodName68.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes68, parameterTypes)) {
			return CalendarResourceServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(java.lang.String)arguments[3],
				((Boolean)arguments[4]).booleanValue());
		}

		if (_methodName69.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes69, parameterTypes)) {
			return CalendarResourceServiceUtil.searchCount(((Long)arguments[0]).longValue(),
				(long[])arguments[1], (long[])arguments[2],
				(java.lang.String)arguments[3], (java.lang.String)arguments[4],
				(java.lang.String)arguments[5],
				((Boolean)arguments[6]).booleanValue(),
				((Boolean)arguments[7]).booleanValue());
		}

		if (_methodName70.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes70, parameterTypes)) {
			return CalendarResourceServiceUtil.updateCalendarResource(((Long)arguments[0]).longValue(),
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[1],
				(java.util.Map<java.util.Locale, java.lang.String>)arguments[2],
				((Boolean)arguments[3]).booleanValue(),
				(com.liferay.portal.service.ServiceContext)arguments[4]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName56;
	private String[] _methodParameterTypes56;
	private String _methodName57;
	private String[] _methodParameterTypes57;
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
}