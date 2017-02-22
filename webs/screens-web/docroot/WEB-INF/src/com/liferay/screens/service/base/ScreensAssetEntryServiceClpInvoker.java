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
		_methodName82 = "getBeanIdentifier";

		_methodParameterTypes82 = new String[] {  };

		_methodName83 = "setBeanIdentifier";

		_methodParameterTypes83 = new String[] { "java.lang.String" };

		_methodName86 = "getAssetEntries";

		_methodParameterTypes86 = new String[] {
				"com.liferay.portlet.asset.service.persistence.AssetEntryQuery",
				"java.util.Locale"
			};

		_methodName87 = "getAssetEntries";

		_methodParameterTypes87 = new String[] {
				"long", "long", "java.lang.String", "java.util.Locale", "int"
			};

		_methodName88 = "getAssetEntry";

		_methodParameterTypes88 = new String[] { "long", "java.util.Locale" };

		_methodName89 = "getAssetEntry";

		_methodParameterTypes89 = new String[] {
				"java.lang.String", "long", "java.util.Locale"
			};
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName82.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes82, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getBeanIdentifier();
		}

		if (_methodName83.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes83, parameterTypes)) {
			ScreensAssetEntryServiceUtil.setBeanIdentifier((java.lang.String)arguments[0]);

			return null;
		}

		if (_methodName86.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes86, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getAssetEntries((com.liferay.portlet.asset.service.persistence.AssetEntryQuery)arguments[0],
				(java.util.Locale)arguments[1]);
		}

		if (_methodName87.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes87, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getAssetEntries(((Long)arguments[0]).longValue(),
				((Long)arguments[1]).longValue(),
				(java.lang.String)arguments[2], (java.util.Locale)arguments[3],
				((Integer)arguments[4]).intValue());
		}

		if (_methodName88.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes88, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getAssetEntry(((Long)arguments[0]).longValue(),
				(java.util.Locale)arguments[1]);
		}

		if (_methodName89.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes89, parameterTypes)) {
			return ScreensAssetEntryServiceUtil.getAssetEntry((java.lang.String)arguments[0],
				((Long)arguments[1]).longValue(), (java.util.Locale)arguments[2]);
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName82;
	private String[] _methodParameterTypes82;
	private String _methodName83;
	private String[] _methodParameterTypes83;
	private String _methodName86;
	private String[] _methodParameterTypes86;
	private String _methodName87;
	private String[] _methodParameterTypes87;
	private String _methodName88;
	private String[] _methodParameterTypes88;
	private String _methodName89;
	private String[] _methodParameterTypes89;
}