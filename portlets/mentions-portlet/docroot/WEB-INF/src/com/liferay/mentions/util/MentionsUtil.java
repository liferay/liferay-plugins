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

package com.liferay.mentions.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PrefsPropsUtil;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.GroupLocalServiceUtil;

import javax.portlet.PortletPreferences;

/**
 * @author Sergio González
 */
public class MentionsUtil {

	public static boolean isMentionsEnabled(long siteGroupId)
		throws PortalException {

		Group group = GroupLocalServiceUtil.getGroup(siteGroupId);

		PortletPreferences companyPortletPreferences =
			PrefsPropsUtil.getPreferences(group.getCompanyId(), true);

		boolean companyMentionsEnabled = GetterUtil.getBoolean(
			companyPortletPreferences.getValue("mentionsEnabled", null), true);

		if (!companyMentionsEnabled) {
			return false;
		}

		return GetterUtil.getBoolean(
			group.getLiveParentTypeSettingsProperty("mentionsEnabled"), true);
	}

}