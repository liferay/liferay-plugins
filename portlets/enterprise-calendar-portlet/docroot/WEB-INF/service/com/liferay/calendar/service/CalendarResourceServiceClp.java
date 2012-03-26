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
				"addCalendarResource", long.class, java.lang.String.class,
				long.class, java.lang.String.class, long.class,
				java.lang.String.class, java.util.Map.class,
				java.util.Map.class, java.lang.String.class, boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteCalendarResourceMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteCalendarResource", long.class);

		_getCalendarResourceMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCalendarResource", long.class);

		_searchMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"search", long.class, long[].class, long[].class,
				java.lang.String.class, boolean.class, boolean.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_searchMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"search", long.class, long[].class, long[].class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, boolean.class,
				boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_searchCountMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchCount", long.class, long[].class, long[].class,
				java.lang.String.class, boolean.class);

		_searchCountMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchCount", long.class, long[].class, long[].class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class, boolean.class,
				boolean.class);

		_updateCalendarResourceMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateCalendarResource", long.class, long.class,
				java.lang.String.class, java.util.Map.class,
				java.util.Map.class, java.lang.String.class, boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_updateCalendarResourceMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateCalendarResource", long.class, java.lang.String.class,
				java.util.Map.class, java.util.Map.class,
				java.lang.String.class, boolean.class,
				com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.calendar.model.CalendarResource addCalendarResource(
		long groupId, java.lang.String className, long classPK,
		java.lang.String classUuid, long defaultCalendarId,
		java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addCalendarResourceMethodKey0,
				groupId, ClpSerializer.translateInput(className), classPK,
				ClpSerializer.translateInput(classUuid), defaultCalendarId,
				ClpSerializer.translateInput(code),
				ClpSerializer.translateInput(nameMap),
				ClpSerializer.translateInput(descriptionMap),
				ClpSerializer.translateInput(type), active,
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

	public com.liferay.calendar.model.CalendarResource deleteCalendarResource(
		long calendarResourceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_deleteCalendarResourceMethodKey1,
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

	public java.util.List<com.liferay.calendar.model.CalendarResource> search(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String keywords, boolean active, boolean andOperator,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchMethodKey3,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(classNameIds),
				ClpSerializer.translateInput(keywords), active, andOperator,
				start, end, ClpSerializer.translateInput(orderByComparator));

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

	public java.util.List<com.liferay.calendar.model.CalendarResource> search(
		long companyId, long[] groupIds, long[] classNameIds,
		java.lang.String code, java.lang.String name,
		java.lang.String description, java.lang.String type, boolean active,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchMethodKey4,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(classNameIds),
				ClpSerializer.translateInput(code),
				ClpSerializer.translateInput(name),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(type), active, andOperator, start,
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

	public int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String keywords, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchCountMethodKey5,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(classNameIds),
				ClpSerializer.translateInput(keywords), active);

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

	public int searchCount(long companyId, long[] groupIds,
		long[] classNameIds, java.lang.String code, java.lang.String name,
		java.lang.String description, java.lang.String type, boolean active,
		boolean andOperator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchCountMethodKey6,
				companyId, ClpSerializer.translateInput(groupIds),
				ClpSerializer.translateInput(classNameIds),
				ClpSerializer.translateInput(code),
				ClpSerializer.translateInput(name),
				ClpSerializer.translateInput(description),
				ClpSerializer.translateInput(type), active, andOperator);

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
		long calendarResourceId, long defaultCalendarId, java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateCalendarResourceMethodKey7,
				calendarResourceId, defaultCalendarId,
				ClpSerializer.translateInput(code),
				ClpSerializer.translateInput(nameMap),
				ClpSerializer.translateInput(descriptionMap),
				ClpSerializer.translateInput(type), active,
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

	public com.liferay.calendar.model.CalendarResource updateCalendarResource(
		long calendarResourceId, java.lang.String code,
		java.util.Map<java.util.Locale, java.lang.String> nameMap,
		java.util.Map<java.util.Locale, java.lang.String> descriptionMap,
		java.lang.String type, boolean active,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateCalendarResourceMethodKey8,
				calendarResourceId, ClpSerializer.translateInput(code),
				ClpSerializer.translateInput(nameMap),
				ClpSerializer.translateInput(descriptionMap),
				ClpSerializer.translateInput(type), active,
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
	private MethodKey _searchMethodKey3;
	private MethodKey _searchMethodKey4;
	private MethodKey _searchCountMethodKey5;
	private MethodKey _searchCountMethodKey6;
	private MethodKey _updateCalendarResourceMethodKey7;
	private MethodKey _updateCalendarResourceMethodKey8;
}