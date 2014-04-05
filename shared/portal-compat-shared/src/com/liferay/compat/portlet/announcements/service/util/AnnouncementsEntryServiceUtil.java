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

package com.liferay.compat.portlet.announcements.service.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portlet.announcements.model.AnnouncementsEntry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Gergely Mathe
 */
public class AnnouncementsEntryServiceUtil
	extends com.liferay.portlet.announcements.service.
		AnnouncementsEntryServiceUtil {

	public static AnnouncementsEntry addEntry(
			long plid, long classNameId, long classPK, String title,
			String content, String url, String type, int displayDateMonth,
			int displayDateDay, int displayDateYear, int displayDateHour,
			int displayDateMinute, boolean autoDisplayDate,
			int expirationDateMonth, int expirationDateDay,
			int expirationDateYear, int expirationDateHour,
			int expirationDateMinute, int priority, boolean alert)
		throws PortalException, SystemException {

		Object[] arguments = new Object[] {
			plid, classNameId, classPK, title, content, url, type,
			displayDateMonth, displayDateDay, displayDateYear, displayDateHour,
			displayDateMinute, autoDisplayDate, expirationDateMonth,
			expirationDateDay, expirationDateYear, expirationDateHour,
			expirationDateMinute, priority, alert
		};

		if (!_supportsAutoDisplayDate) {
			arguments = new Object[] {
				plid, classNameId, classPK, title, content, url, type,
				displayDateMonth, displayDateDay, displayDateYear,
				displayDateHour, displayDateMinute, expirationDateMonth,
				expirationDateDay, expirationDateYear, expirationDateHour,
				expirationDateMinute, priority, alert
			};
		}

		AnnouncementsEntry announcementsEntry = null;

		try {
			announcementsEntry = (AnnouncementsEntry)_addEntryMethod.invoke(
				null, arguments);
		}
		catch (InvocationTargetException ite) {
			_log.error(ite.getCause());
		}
		catch (Exception e) {
			_log.error(e);
		}

		return announcementsEntry;
	}

	private static Log _log = LogFactoryUtil.getLog(
		AnnouncementsEntryServiceUtil.class);

	private static Method _addEntryMethod;
	private static boolean _supportsAutoDisplayDate;

	static {
		try {
			_addEntryMethod = ReflectionUtil.getDeclaredMethod(
				com.liferay.portlet.announcements.service.
					AnnouncementsEntryServiceUtil.class,
				"addEntry",
				new Class<?>[] {
					long.class, long.class, long.class, String.class,
					String.class, String.class, String.class, int.class,
					int.class, int.class, int.class, int.class, boolean.class,
					int.class, int.class, int.class, int.class, int.class,
					int.class, boolean.class
				});

			_supportsAutoDisplayDate = true;
		}
		catch (NoSuchMethodException nsme) {
			try {
				_addEntryMethod = ReflectionUtil.getDeclaredMethod(
					com.liferay.portlet.announcements.service.
						AnnouncementsEntryServiceUtil.class,
					"addEntry",
					new Class<?>[] {
						long.class, long.class, long.class, String.class,
						String.class, String.class, String.class, int.class,
						int.class, int.class, int.class, int.class, int.class,
						int.class, int.class, int.class, int.class, int.class,
						boolean.class
					});

				if (_log.isDebugEnabled()) {
					_log.debug("Falling back to " + _addEntryMethod);
				}
			}
			catch (Exception e) {
				_log.error(e);
			}

			_supportsAutoDisplayDate = false;
		}
		catch (Exception e) {
			_log.error(e);

			_supportsAutoDisplayDate = false;
		}
	}

}