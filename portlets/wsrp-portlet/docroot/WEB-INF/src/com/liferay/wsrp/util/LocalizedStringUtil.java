/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.wsrp.util;

import oasis.names.tc.wsrp.v2.types.LocalizedString;

/**
 * <a href="LocalizedStringUtil.java.html"><b><i>View Source</i></b></a>
 *
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