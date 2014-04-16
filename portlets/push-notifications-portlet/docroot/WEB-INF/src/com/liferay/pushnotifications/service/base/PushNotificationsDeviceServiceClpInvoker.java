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

package com.liferay.pushnotifications.service.base;

import com.liferay.pushnotifications.service.PushNotificationsDeviceServiceUtil;

import java.util.Arrays;

/**
 * @author Silvio Santos
 * @generated
 */
public class PushNotificationsDeviceServiceClpInvoker {
	public PushNotificationsDeviceServiceClpInvoker() {
		_methodName18 = "getBeanIdentifier";

		_methodParameterTypes18 = new String[] {  };

		_methodName19 = "setBeanIdentifier";

		_methodParameterTypes19 = new String[] { "java.lang.String" };

		_methodName24 = "addPushNotificationsDevice";

		_methodParameterTypes24 = new String[] {
				"java.lang.String", "java.lang.String"
			};

		_methodName25 = "deletePushNotificationsDevice";

		_methodParameterTypes25 = new String[] { "java.lang.String" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName18.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes18, parameterTypes)) {
			return PushNotificationsDeviceServiceUtil.getBeanIdentifier();
		}

		if (_methodName19.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes19, parameterTypes)) {
			PushNotificationsDeviceServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName24.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes24, parameterTypes)) {
			return PushNotificationsDeviceServiceUtil.addPushNotificationsDevice((java.lang.String)arguments[0],
				(java.lang.String)arguments[1]);
		}

		if (_methodName25.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes25, parameterTypes)) {
			return PushNotificationsDeviceServiceUtil.deletePushNotificationsDevice((java.lang.String)arguments[0]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName18;
	private String[] _methodParameterTypes18;
	private String _methodName19;
	private String[] _methodParameterTypes19;
	private String _methodName24;
	private String[] _methodParameterTypes24;
	private String _methodName25;
	private String[] _methodParameterTypes25;
}