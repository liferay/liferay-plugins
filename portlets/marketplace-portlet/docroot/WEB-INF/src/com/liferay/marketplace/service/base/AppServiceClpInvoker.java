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

package com.liferay.marketplace.service.base;

import com.liferay.marketplace.service.AppServiceUtil;

import java.util.Arrays;

/**
 * @author Ryan Park
 * @generated
 */
public class AppServiceClpInvoker {
	public AppServiceClpInvoker() {
		_methodName22 = "getBeanIdentifier";

		_methodParameterTypes22 = new String[] {  };

		_methodName23 = "setBeanIdentifier";

		_methodParameterTypes23 = new String[] { "java.lang.String" };

		_methodName28 = "addApp";

		_methodParameterTypes28 = new String[] {
				"long", "java.lang.String", "java.io.File"
			};

		_methodName29 = "deleteApp";

		_methodParameterTypes29 = new String[] { "long" };

		_methodName30 = "installApp";

		_methodParameterTypes30 = new String[] { "long" };

		_methodName31 = "uninstallApp";

		_methodParameterTypes31 = new String[] { "long" };

		_methodName32 = "updateApp";

		_methodParameterTypes32 = new String[] {
				"long", "java.lang.String", "java.io.File"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName22.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes22, parameterTypes)) {
			return AppServiceUtil.getBeanIdentifier();
		}

		if (_methodName23.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes23, parameterTypes)) {
			AppServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName28.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes28, parameterTypes)) {
			return AppServiceUtil.addApp(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.io.File)arguments[2]);
		}

		if (_methodName29.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes29, parameterTypes)) {
			return AppServiceUtil.deleteApp(((Long)arguments[0]).longValue());
		}

		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			AppServiceUtil.installApp(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName31.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes31, parameterTypes)) {
			AppServiceUtil.uninstallApp(((Long)arguments[0]).longValue());

			return null;
		}

		if (_methodName32.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes32, parameterTypes)) {
			return AppServiceUtil.updateApp(((Long)arguments[0]).longValue(),
				(java.lang.String)arguments[1], (java.io.File)arguments[2]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName22;
	private String[] _methodParameterTypes22;
	private String _methodName23;
	private String[] _methodParameterTypes23;
	private String _methodName28;
	private String[] _methodParameterTypes28;
	private String _methodName29;
	private String[] _methodParameterTypes29;
	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName31;
	private String[] _methodParameterTypes31;
	private String _methodName32;
	private String[] _methodParameterTypes32;
}