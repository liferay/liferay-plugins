/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.socialnetworking.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Brian Wing Shun Chan
 */
public class MeetupsRegistrationLocalServiceClp
	implements MeetupsRegistrationLocalService {
	public MeetupsRegistrationLocalServiceClp(String className,
		ClassLoaderProxy classLoaderProxy) {
		_className = className;
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.socialnetworking.model.MeetupsRegistration addMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addMeetupsRegistrationMethodKey0,
				meetupsRegistration);

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

		return (com.liferay.socialnetworking.model.MeetupsRegistration)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialnetworking.model.MeetupsRegistration createMeetupsRegistration(
		long meetupsRegistrationId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createMeetupsRegistrationMethodKey1,
				meetupsRegistrationId);

		try {
			returnObj = _classLoaderProxy.invoke(methodHandler);
		}
		catch (Throwable t) {
			if (t instanceof RuntimeException) {
				throw (RuntimeException)t;
			}
			else {
				throw new RuntimeException(t.getClass().getName() +
					" is not a valid exception");
			}
		}

		return (com.liferay.socialnetworking.model.MeetupsRegistration)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteMeetupsRegistration(long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteMeetupsRegistrationMethodKey2,
				meetupsRegistrationId);

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

	public void deleteMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteMeetupsRegistrationMethodKey3,
				meetupsRegistration);

		try {
			_classLoaderProxy.invoke(methodHandler);
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
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
				dynamicQuery);

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

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey5,
				dynamicQuery, start, end);

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

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey6,
				dynamicQuery, start, end, orderByComparator);

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

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
				dynamicQuery);

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

		return ((Long)returnObj).longValue();
	}

	public com.liferay.socialnetworking.model.MeetupsRegistration getMeetupsRegistration(
		long meetupsRegistrationId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMeetupsRegistrationMethodKey8,
				meetupsRegistrationId);

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

		return (com.liferay.socialnetworking.model.MeetupsRegistration)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> getMeetupsRegistrations(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMeetupsRegistrationsMethodKey9,
				start, end);

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

		return (java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration>)ClpSerializer.translateOutput(returnObj);
	}

	public int getMeetupsRegistrationsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMeetupsRegistrationsCountMethodKey10);

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

	public com.liferay.socialnetworking.model.MeetupsRegistration updateMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateMeetupsRegistrationMethodKey11,
				meetupsRegistration);

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

		return (com.liferay.socialnetworking.model.MeetupsRegistration)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialnetworking.model.MeetupsRegistration updateMeetupsRegistration(
		com.liferay.socialnetworking.model.MeetupsRegistration meetupsRegistration,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateMeetupsRegistrationMethodKey12,
				meetupsRegistration, merge);

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

		return (com.liferay.socialnetworking.model.MeetupsRegistration)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration> getMeetupsRegistrations(
		long meetupsEntryId, int status, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMeetupsRegistrationsMethodKey13,
				meetupsEntryId, status, start, end);

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

		return (java.util.List<com.liferay.socialnetworking.model.MeetupsRegistration>)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.socialnetworking.model.MeetupsRegistration getMeetupsRegistration(
		long userId, long meetupsEntryId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMeetupsRegistrationMethodKey14,
				userId, meetupsEntryId);

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

		return (com.liferay.socialnetworking.model.MeetupsRegistration)ClpSerializer.translateOutput(returnObj);
	}

	public int getMeetupsRegistrationsCount(long meetupsEntryId, int status)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getMeetupsRegistrationsCountMethodKey15,
				meetupsEntryId, status);

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

	public com.liferay.socialnetworking.model.MeetupsRegistration updateMeetupsRegistration(
		long userId, long meetupsEntryId, int status, java.lang.String comments)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateMeetupsRegistrationMethodKey16,
				userId, meetupsEntryId, status, comments);

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

		return (com.liferay.socialnetworking.model.MeetupsRegistration)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private String _className;
	private ClassLoaderProxy _classLoaderProxy;
	private final MethodKey _addMeetupsRegistrationMethodKey0 = new MethodKey(_className,
			"addMeetupsRegistration",
			com.liferay.socialnetworking.model.MeetupsRegistration.class);
	private final MethodKey _createMeetupsRegistrationMethodKey1 = new MethodKey(_className,
			"createMeetupsRegistration", long.class);
	private final MethodKey _deleteMeetupsRegistrationMethodKey2 = new MethodKey(_className,
			"deleteMeetupsRegistration", long.class);
	private final MethodKey _deleteMeetupsRegistrationMethodKey3 = new MethodKey(_className,
			"deleteMeetupsRegistration",
			com.liferay.socialnetworking.model.MeetupsRegistration.class);
	private final MethodKey _dynamicQueryMethodKey4 = new MethodKey(_className,
			"dynamicQuery", com.liferay.portal.kernel.dao.orm.DynamicQuery.class);
	private final MethodKey _dynamicQueryMethodKey5 = new MethodKey(_className,
			"dynamicQuery",
			com.liferay.portal.kernel.dao.orm.DynamicQuery.class, int.class,
			int.class);
	private final MethodKey _dynamicQueryMethodKey6 = new MethodKey(_className,
			"dynamicQuery",
			com.liferay.portal.kernel.dao.orm.DynamicQuery.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class);
	private final MethodKey _dynamicQueryCountMethodKey7 = new MethodKey(_className,
			"dynamicQueryCount",
			com.liferay.portal.kernel.dao.orm.DynamicQuery.class);
	private final MethodKey _getMeetupsRegistrationMethodKey8 = new MethodKey(_className,
			"getMeetupsRegistration", long.class);
	private final MethodKey _getMeetupsRegistrationsMethodKey9 = new MethodKey(_className,
			"getMeetupsRegistrations", int.class, int.class);
	private final MethodKey _getMeetupsRegistrationsCountMethodKey10 = new MethodKey(_className,
			"getMeetupsRegistrationsCount");
	private final MethodKey _updateMeetupsRegistrationMethodKey11 = new MethodKey(_className,
			"updateMeetupsRegistration",
			com.liferay.socialnetworking.model.MeetupsRegistration.class);
	private final MethodKey _updateMeetupsRegistrationMethodKey12 = new MethodKey(_className,
			"updateMeetupsRegistration",
			com.liferay.socialnetworking.model.MeetupsRegistration.class,
			boolean.class);
	private final MethodKey _getMeetupsRegistrationsMethodKey13 = new MethodKey(_className,
			"getMeetupsRegistrations", long.class, int.class, int.class,
			int.class);
	private final MethodKey _getMeetupsRegistrationMethodKey14 = new MethodKey(_className,
			"getMeetupsRegistration", long.class, long.class);
	private final MethodKey _getMeetupsRegistrationsCountMethodKey15 = new MethodKey(_className,
			"getMeetupsRegistrationsCount", long.class, int.class);
	private final MethodKey _updateMeetupsRegistrationMethodKey16 = new MethodKey(_className,
			"updateMeetupsRegistration", long.class, long.class, int.class,
			java.lang.String.class);
}