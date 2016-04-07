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

package com.liferay.screens.service.base;

import com.liferay.screens.service.ScreensAssetEntryServiceUtil;

import java.util.Arrays;

/**
 * @author José Manuel Navarro
 * @generated
 */
public class ScreensAssetEntryServiceClpInvoker {
	public ScreensAssetEntryServiceClpInvoker() {
		_methodName72 = "getBeanIdentifier";

		_methodParameterTypes72 = new String[] {  };

		_methodName73 = "setBeanIdentifier";

		_methodParameterTypes73 = new String[] { "java.lang.String" };

		_methodName76 = "getAssetEntries";

		_methodParameterTypes76 = new String[] {
				"com.liferay.portlet.asset.service.persistence.AssetEntryQuery",
				"java.util.Locale"
			};

		_methodName77 = "getAssetEntries";

		_methodParameterTypes77 = new String[] {
				"long", "long", "java.lang.String", "java.util.Locale", "int"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName72.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes72, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName73.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes73, parameterTypes)) {
			ScreensAssetEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName76.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes76, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getAssetEntries((com.liferay.portlet.asset.service.persistence.AssetEntryQuery)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName77.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes77, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getAssetEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.util.Locale)arguments[3],
				((Integer)arguments[4]).intValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName72;
	private String[] _methodParameterTypes72;
	private String _methodName73;
	private String[] _methodParameterTypes73;
	private String _methodName76;
	private String[] _methodParameterTypes76;
	private String _methodName77;
	private String[] _methodParameterTypes77;
}