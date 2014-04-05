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

import com.liferay.calendar.service.CalendarImporterLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Eduardo Lundgren
 * @generated
 */
public class CalendarImporterLocalServiceClpInvoker {
	public CalendarImporterLocalServiceClpInvoker() {
		_methodName134 = "getBeanIdentifier";

		_methodParameterTypes134 = new String[] {  };

		_methodName135 = "setBeanIdentifier";

		_methodParameterTypes135 = new String[] { "java.lang.String" };

		_methodName138 = "importCalEvent";

		_methodParameterTypes138 = new String[] {
				"com.liferay.portlet.calendar.model.CalEvent"
			};

		_methodName139 = "importCalEvents";

		_methodParameterTypes139 = new String[] {  };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName134.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes134, parameterTypes)) {
			return CalendarImporterLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName135.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes135, parameterTypes)) {
			CalendarImporterLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName138.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes138, parameterTypes)) {
			CalendarImporterLocalServiceUtil.importCalEvent((com.liferay.portlet.calendar.model.CalEvent)arguments[0]);

			return null;
		}

		if (_methodName139.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes139, parameterTypes)) {
			CalendarImporterLocalServiceUtil.importCalEvents();

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName134;
	private String[] _methodParameterTypes134;
	private String _methodName135;
	private String[] _methodParameterTypes135;
	private String _methodName138;
	private String[] _methodParameterTypes138;
	private String _methodName139;
	private String[] _methodParameterTypes139;
}