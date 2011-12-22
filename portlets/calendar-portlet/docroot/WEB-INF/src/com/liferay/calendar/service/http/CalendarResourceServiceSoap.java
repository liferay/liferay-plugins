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

package com.liferay.calendar.service.http;

import com.liferay.calendar.service.CalendarResourceServiceUtil;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link com.liferay.calendar.service.CalendarResourceServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * ServiceBuilder follows certain rules in translating the methods. For example,
 * if the method in the service utility returns a {@link java.util.List}, that
 * is translated to an array of {@link com.liferay.calendar.model.CalendarResourceSoap}.
 * If the method in the service utility returns a
 * {@link com.liferay.calendar.model.CalendarResource}, that is translated to a
 * {@link com.liferay.calendar.model.CalendarResourceSoap}. Methods that SOAP cannot
 * safely wire are skipped.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Eduardo Lundgren
 * @see       CalendarResourceServiceHttp
 * @see       com.liferay.calendar.model.CalendarResourceSoap
 * @see       com.liferay.calendar.service.CalendarResourceServiceUtil
 * @generated
 */
public class CalendarResourceServiceSoap {
	public static void deleteCalendarResource(long calendarResourceId)
		throws RemoteException {
		try {
			CalendarResourceServiceUtil.deleteCalendarResource(calendarResourceId);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarResourceSoap getCalendarResource(
		long calendarResourceId) throws RemoteException {
		try {
			com.liferay.calendar.model.CalendarResource returnValue = CalendarResourceServiceUtil.getCalendarResource(calendarResourceId);

			return com.liferay.calendar.model.CalendarResourceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarResourceSoap getCalendarResource(
		java.lang.String className, long classPK) throws RemoteException {
		try {
			com.liferay.calendar.model.CalendarResource returnValue = CalendarResourceServiceUtil.getCalendarResource(className,
					classPK);

			return com.liferay.calendar.model.CalendarResourceSoap.toSoapModel(returnValue);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarResource[] getGroupCalendarResources(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.calendar.model.CalendarResource> returnValue =
				CalendarResourceServiceUtil.getGroupCalendarResources(groupId,
					active, start, end, orderByComparator);

			return returnValue.toArray(new com.liferay.calendar.model.CalendarResource[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static com.liferay.calendar.model.CalendarResource[] getGroupCalendarResources(
		long groupId, java.lang.String name, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			java.util.List<com.liferay.calendar.model.CalendarResource> returnValue =
				CalendarResourceServiceUtil.getGroupCalendarResources(groupId,
					name, active, start, end, orderByComparator);

			return returnValue.toArray(new com.liferay.calendar.model.CalendarResource[returnValue.size()]);
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getGroupCalendarResourcesCount(long groupId,
		boolean active) throws RemoteException {
		try {
			int returnValue = CalendarResourceServiceUtil.getGroupCalendarResourcesCount(groupId,
					active);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	public static int getGroupCalendarResourcesCount(long groupId,
		java.lang.String name, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws RemoteException {
		try {
			int returnValue = CalendarResourceServiceUtil.getGroupCalendarResourcesCount(groupId,
					name, active, start, end, orderByComparator);

			return returnValue;
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new RemoteException(e.getMessage());
		}
	}

	private static Log _log = LogFactoryUtil.getLog(CalendarResourceServiceSoap.class);
}