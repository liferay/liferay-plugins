/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.wsrp.util;

import oasis.names.tc.wsrp.v2.types.LocalizedString;

/**
 * @author Brian Wing Shun Chan
 */
public class LocalizedStringUtil {

	public static String getLocalizedStringValue(
		LocalizedString localizedString) {

		return getLocalizedStringValue(localizedString, null);
	}

	public static String getLocalizedStringValue(
		LocalizedString localizedString, String defaultValue) {

		if (localizedString == null) {
			return defaultValue;
		}

		return localizedString.getValue();
	}

	public static String[] getLocalizedStringValues(
		LocalizedString[] localizedStrings) {

		return getLocalizedStringValues(localizedStrings, null);
	}

	public static String[] getLocalizedStringValues(
		LocalizedString[] localizedStrings, String[] defaultValue) {

		if (localizedStrings == null) {
			return defaultValue;
		}

		String[] values = new String[localizedStrings.length];

		for (int i = 0; i < localizedStrings.length; i++) {
			values[i] = getLocalizedStringValue(localizedStrings[i]);
		}

		return values;
	}

}