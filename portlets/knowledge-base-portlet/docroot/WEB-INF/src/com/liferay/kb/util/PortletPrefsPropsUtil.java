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

package com.liferay.kb.util;

import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.PortletPreferencesLocalServiceUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.util.portlet.PortletProps;

import java.io.IOException;

import javax.portlet.PortletPreferences;

/**
 * <a href="PortletPrefsPropsUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Peter Shin
 *
 */
public class PortletPrefsPropsUtil {

	public static PortletPreferences getPreferences(long companyId)
		throws PortalException, SystemException {

		long ownerId = companyId;
		int ownerType = PortletKeys.PREFS_OWNER_TYPE_COMPANY;
		long plid = PortletKeys.PREFS_PLID_SHARED;
		String portletId = PortletKeys.LIFERAY_PORTAL;

		return PortletPreferencesLocalServiceUtil.getPreferences(
			companyId, ownerId, ownerType, plid, portletId);
	}

	public static boolean getBoolean(long companyId, String name)
		throws PortalException, SystemException {

		return GetterUtil.getBoolean(getString(companyId, name));
	}

	public static String getContent(
			long companyId, String name, ClassLoader classLoader)
		throws PortalException, SystemException, IOException {

		PortletPreferences prefs = getPreferences(companyId);

		String content = StringPool.BLANK;
		String value = prefs.getValue(name, StringPool.BLANK);

		if (Validator.isNotNull(value)) {
			content = value;
		}
		else {
			content = StringUtil.read(classLoader, PortletProps.get(name));
		}

		return content;
	}

	public static String getString(long companyId, String name)
		throws PortalException, SystemException {

		PortletPreferences prefs = getPreferences(companyId);

		String value = PortletProps.get(name);

		return prefs.getValue(name, value);
	}

}