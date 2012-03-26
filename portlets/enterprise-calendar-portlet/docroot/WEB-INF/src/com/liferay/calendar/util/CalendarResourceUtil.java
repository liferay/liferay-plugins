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

package com.liferay.calendar.util;

import com.liferay.calendar.util.comparator.CalendarResourceCodeComparator;
import com.liferay.calendar.util.comparator.CalendarResourceNameComparator;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.User;
import com.liferay.portal.service.GroupLocalServiceUtil;

/**
 * @author Eduardo Lundgren
 * @author Fabio Pezzutto
 */
public class CalendarResourceUtil {

	public static long getGlobalResourceUserId(String className, long classPK)
		throws PortalException, SystemException {

		long userId = 0;

		if (className.equals(Group.class.getName())) {
			Group group = GroupLocalServiceUtil.getGroup(classPK);

			userId = group.getCreatorUserId();
		}
		else if (className.equals(User.class.getName())) {
			userId = classPK;
		}

		return userId;
	}

	public static OrderByComparator getOrderByComparator(
		String orderByCol, String orderByType) {

		boolean orderByAsc = false;

		if (orderByType.equals("asc")) {
			orderByAsc = true;
		}

		OrderByComparator orderByComparator = null;

		if (orderByCol.equals("name")) {
			orderByComparator = new CalendarResourceNameComparator(orderByAsc);
		}
		else {
			orderByComparator = new CalendarResourceCodeComparator(orderByAsc);
		}

		return orderByComparator;
	}

	public static boolean isGlobalResource(String className) {
		if (className.equals(Group.class.getName()) ||
			className.equals(User.class.getName())) {

			return true;
		}

		return false;
	}

}