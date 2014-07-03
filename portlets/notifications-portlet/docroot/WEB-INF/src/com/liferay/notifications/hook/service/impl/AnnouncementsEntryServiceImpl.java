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

package com.liferay.notifications.hook.service.impl;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.security.auth.PrincipalException;
import com.liferay.portal.security.permission.ActionKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.security.permission.PermissionThreadLocal;
import com.liferay.portal.service.permission.PortletPermissionUtil;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portal.util.PortletKeys;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;
import com.liferay.portlet.announcements.service.AnnouncementsEntryLocalServiceUtil;
import com.liferay.portlet.announcements.service.AnnouncementsEntryService;
import com.liferay.portlet.announcements.service.AnnouncementsEntryServiceWrapper;

/**
 * @author Jonathan Lee
 */
public class AnnouncementsEntryServiceImpl
	extends AnnouncementsEntryServiceWrapper {

	public AnnouncementsEntryServiceImpl(
		AnnouncementsEntryService announcementsEntryService) {

		super(announcementsEntryService);
	}

	@Override
	public AnnouncementsEntry addEntry(
			long plid, long classNameId, long classPK, String title,
			String content, String url, String type, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, int expirationDateMonth,
			int expirationDateDay, int expirationDateYear,
			int expirationDateHour, int expirationDateMinute, int priority,
			boolean alert)
		throws PortalException, SystemException {

		AnnouncementsEntry announcementEntry = null;

		try {
			announcementEntry = super.addEntry(
				plid, classNameId, classPK, title, content, url, type,
				displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, priority, alert);
		}
		catch (PrincipalException pe) {
			String className = PortalUtil.getClassName(classNameId);

			if (className.equals(Group.class.getName())) {
				PermissionChecker permissionChecker =
					PermissionThreadLocal.getPermissionChecker();

				PortletPermissionUtil.check(
					permissionChecker, plid, PortletKeys.ANNOUNCEMENTS,
					ActionKeys.ADD_ENTRY);

				announcementEntry = AnnouncementsEntryLocalServiceUtil.addEntry(
					permissionChecker.getUserId(), classNameId, classPK, title,
					content, url, type, displayDateMonth, displayDateDay,
					displayDateYear, displayDateHour, displayDateMinute,
					expirationDateMonth, expirationDateDay, expirationDateYear,
					expirationDateHour, expirationDateMinute, priority, alert);
			}
			else {
				throw pe;
			}
		}

		return announcementEntry;
	}

}