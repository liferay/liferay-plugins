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

package com.liferay.sync.service.base;

import aQute.bnd.annotation.ProviderType;

import com.liferay.sync.service.SyncPreferencesLocalServiceUtil;

import java.util.Arrays;

/**
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class SyncPreferencesLocalServiceClpInvoker {
	public SyncPreferencesLocalServiceClpInvoker() {
		_methodName30 = "getOSGiServiceIdentifier";

		_methodParameterTypes30 = new String[] {  };

		_methodName33 = "enableOAuth";

		_methodParameterTypes33 = new String[] {
				"long", "com.liferay.portal.kernel.service.ServiceContext"
			};

		_methodName34 = "getPortletPreferences";

		_methodParameterTypes34 = new String[] { "long" };

		_methodName35 = "isOAuthApplicationAvailable";

		_methodParameterTypes35 = new String[] { "long" };
	}

	public Object invokeMethod(String name, String[] parameterTypes,
		Object[] arguments) throws Throwable {
		if (_methodName30.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes30, parameterTypes)) {
			return SyncPreferencesLocalServiceUtil.getOSGiServiceIdentifier();
		}

		if (_methodName33.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes33, parameterTypes)) {
			SyncPreferencesLocalServiceUtil.enableOAuth(((Long)arguments[0]).longValue(),
				(com.liferay.portal.kernel.service.ServiceContext)arguments[1]);

			return null;
		}

		if (_methodName34.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes34, parameterTypes)) {
			return SyncPreferencesLocalServiceUtil.getPortletPreferences(((Long)arguments[0]).longValue());
		}

		if (_methodName35.equals(name) &&
				Arrays.deepEquals(_methodParameterTypes35, parameterTypes)) {
			return SyncPreferencesLocalServiceUtil.isOAuthApplicationAvailable(((Long)arguments[0]).longValue());
		}

		throw new UnsupportedOperationException();
	}

	private String _methodName30;
	private String[] _methodParameterTypes30;
	private String _methodName33;
	private String[] _methodParameterTypes33;
	private String _methodName34;
	private String[] _methodParameterTypes34;
	private String _methodName35;
	private String[] _methodParameterTypes35;
}