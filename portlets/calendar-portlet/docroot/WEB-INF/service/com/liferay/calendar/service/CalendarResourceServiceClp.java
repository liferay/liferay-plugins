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

package com.liferay.calendar.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Eduardo Lundgren
 */
public class CalendarResourceServiceClp implements CalendarResourceService {
	public CalendarResourceServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addCalendarResourceMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addCalendarResource", java.lang.String.class, long.class,
				java.util.Map.class, java.util.Map.class, boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteCalendarResourceMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteCalendarResource", long.class);

		_getCalendarResourceMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarResource", long.class);

		_getCalendarResourceMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarResource", java.lang.String.class, long.class);

		_getGroupCalendarResourcesMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupCalendarResources", long.class, boolean.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getGroupCalendarResourcesMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupCalendarResources", long.class,
				java.lang.String.class, boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_getGroupCalendarResourcesCountMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupCalendarResourcesCount", long.class, boolean.class);

		_getGroupCalendarResourcesCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"getGroupCalendarResourcesCount", long.class,
				java.lang.String.class, boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_updateCalendarResourceMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateCalendarResource", long.class, java.util.Map.class,
				java.util.Map.class, boolean.class,
				com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.calendar.model.CalendarResource addCalendarResource(
		java.lang.String className, long classPK,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addCalendarResourceMethodKey0,
				ClpSerializer.translateInput(className), classPK,
				ClpSerializer.translateInput(nameMap),
				ClpSerializer.translateInput(descriptionMap), active,
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarResource)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteCalendarResource(long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteCalendarResourceMethodKey1,
				calendarResourceId);

		try {
			_classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}
	}

	public com.liferay.calendar.model.CalendarResource getCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarResourceMethodKey2,
				calendarResourceId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarResource)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.calendar.model.CalendarResource getCalendarResource(
		java.lang.String className, long classPK)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCalendarResourceMethodKey3,
				ClpSerializer.translateInput(className), classPK);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarResource)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.calendar.model.CalendarResource> getGroupCalendarResources(
		long groupId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupCalendarResourcesMethodKey4,
				groupId, active, start, end,
				ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.calendar.model.CalendarResource>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.calendar.model.CalendarResource> getGroupCalendarResources(
		long groupId, java.lang.String name, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupCalendarResourcesMethodKey5,
				groupId, ClpSerializer.translateInput(name), active, start,
				end, ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (java.util.List<com.liferay.calendar.model.CalendarResource>)ClpSerializer.translateOutput(returnObj);
	}

	public int getGroupCalendarResourcesCount(long groupId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupCalendarResourcesCountMethodKey6,
				groupId, active);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public int getGroupCalendarResourcesCount(long groupId,
		java.lang.String name, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getGroupCalendarResourcesCountMethodKey7,
				groupId, ClpSerializer.translateInput(name), active, start,
				end, ClpSerializer.translateInput(orderByComparator));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return ((Integer)returnObj).intValue();
	}

	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		long calendarResourceId,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		boolean active, com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateCalendarResourceMethodKey8,
				calendarResourceId, ClpSerializer.translateInput(nameMap),
				ClpSerializer.translateInput(descriptionMap), active,
				ClpSerializer.translateInput(serviceContext));

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof com.liferay.portal.kernel.exception.PortalException) {
				throw (com.liferay.portal.kernel.exception.PortalException)t;
			}

			if (t instanceof com.liferay.portal.kernel.exception.SystemException) {
				throw (com.liferay.portal.kernel.exception.SystemException)t;
			}

			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.calendar.model.CalendarResource)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addCalendarResourceMethodKey0;
	private MethodKey _deleteCalendarResourceMethodKey1;
	private MethodKey _getCalendarResourceMethodKey2;
	private MethodKey _getCalendarResourceMethodKey3;
	private MethodKey _getGroupCalendarResourcesMethodKey4;
	private MethodKey _getGroupCalendarResourcesMethodKey5;
	private MethodKey _getGroupCalendarResourcesCountMethodKey6;
	private MethodKey _getGroupCalendarResourcesCountMethodKey7;
	private MethodKey _updateCalendarResourceMethodKey8;
}