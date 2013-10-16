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

import com.liferay.calendar.service.CalendarImporterLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Eduardo Lundgren
 * @generated
 */
public class CalendarImporterLocalServiceClpInvoker {
	public CalendarImporterLocalServiceClpInvoker() {
		_methodName128 = "getBeanIdentifier";

		_methodParameterTypes128 = new String[] {  };

		_methodName129 = "setBeanIdentifier";

		_methodParameterTypes129 = new String[] { "java.lang.String" };

		_methodName132 = "importCalEvent";

		_methodParameterTypes132 = new String[] {
				"com.liferay.portlet.calendar.model.CalEvent"
			};

		_methodName133 = "importCalEvents";

		_methodParameterTypes133 = new String[] {  };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName128.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes128, parameterTypes)) {
			return CalendarImporterLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName129.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes129, parameterTypes)) {
			CalendarImporterLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName132.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes132, parameterTypes)) {
			CalendarImporterLocalServiceUtil.importCalEvent((com.liferay.portlet.calendar.model.CalEvent)arguments[0]);

			return null;
		}

		if (_methodName133.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes133, parameterTypes)) {
			CalendarImporterLocalServiceUtil.importCalEvents();

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName128;
	private String[] _methodParameterTypes128;
	private String _methodName129;
	private String[] _methodParameterTypes129;
	private String _methodName132;
	private String[] _methodParameterTypes132;
	private String _methodName133;
	private String[] _methodParameterTypes133;
}