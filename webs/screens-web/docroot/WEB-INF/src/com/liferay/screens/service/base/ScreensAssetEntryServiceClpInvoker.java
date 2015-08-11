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
		_methodName36 = "getBeanIdentifier";

		_methodParameterTypes36 = new String[] {  };

		_methodName37 = "setBeanIdentifier";

		_methodParameterTypes37 = new String[] { "java.lang.String" };

		_methodName40 = "getAssetEntries";

		_methodParameterTypes40 = new String[] {
				"com.liferay.portlet.asset.service.persistence.AssetEntryQuery",
				"java.util.Locale"
			};

		_methodName41 = "getFilteredAssetEntries";

		_methodParameterTypes41 = new String[] {
				"long", "long", "java.lang.String", "java.util.Locale"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName36.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes36, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName37.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes37, parameterTypes)) {
			ScreensAssetEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName40.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes40, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getAssetEntries((com.liferay.portlet.asset.service.persistence.AssetEntryQuery)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName41.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes41, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getFilteredAssetEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.util.Locale)arguments[3]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName36;
	private String[] _methodParameterTypes36;
	private String _methodName37;
	private String[] _methodParameterTypes37;
	private String _methodName40;
	private String[] _methodParameterTypes40;
	private String _methodName41;
	private String[] _methodParameterTypes41;
}