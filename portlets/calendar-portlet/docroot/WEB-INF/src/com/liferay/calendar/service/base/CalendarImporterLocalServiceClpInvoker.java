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

import aQute.bnd.annotation.ProviderType;

import com.liferay.calendar.service.CalendarImporterLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Eduardo Lundgren
 * @generated
 */
@ProviderType
public class CalendarImporterLocalServiceClpInvoker {
	public CalendarImporterLocalServiceClpInvoker() {
		_methodName140 = "getBeanIdentifier";

		_methodParameterTypes140 = new String[] {  };

		_methodName141 = "setBeanIdentifier";

		_methodParameterTypes141 = new String[] { "java.lang.String" };

		_methodName144 = "importCalEvent";

		_methodParameterTypes144 = new String[] {
				"com.liferay.portlet.calendar.model.CalEvent"
			};

		_methodName145 = "importCalEvents";

		_methodParameterTypes145 = new String[] {  };

		_methodName146 = "importRolePermissions";

		_methodParameterTypes146 = new String[] {  };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName140.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes140, parameterTypes)) {
			return CalendarImporterLocalServiceUtil.getBeanIdentifier();
		}

		if (_methodName141.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes141, parameterTypes)) {
			CalendarImporterLocalServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName144.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes144, parameterTypes)) {
			CalendarImporterLocalServiceUtil.importCalEvent((com.liferay.portlet.calendar.model.CalEvent)arguments[0]);

			return null;
		}

		if (_methodName145.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes145, parameterTypes)) {
			CalendarImporterLocalServiceUtil.importCalEvents();

			return null;
		}

		if (_methodName146.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes146, parameterTypes)) {
			CalendarImporterLocalServiceUtil.importRolePermissions();

			return null;
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName140;
	private String[] _methodParameterTypes140;
	private String _methodName141;
	private String[] _methodParameterTypes141;
	private String _methodName144;
	private String[] _methodParameterTypes144;
	private String _methodName145;
	private String[] _methodParameterTypes145;
	private String _methodName146;
	private String[] _methodParameterTypes146;
}