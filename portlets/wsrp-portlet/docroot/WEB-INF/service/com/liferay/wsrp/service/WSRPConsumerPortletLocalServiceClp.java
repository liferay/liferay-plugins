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

package com.liferay.wsrp.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Brian Wing Shun Chan
 */
public class WSRPConsumerPortletLocalServiceClp
	implements WSRPConsumerPortletLocalService {
	public WSRPConsumerPortletLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addWSRPConsumerPortletMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addWSRPConsumerPortlet",
				com.liferay.wsrp.model.WSRPConsumerPortlet.class);

		_createWSRPConsumerPortletMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"createWSRPConsumerPortlet", long.class);

		_deleteWSRPConsumerPortletMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteWSRPConsumerPortlet", long.class);

		_deleteWSRPConsumerPortletMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteWSRPConsumerPortlet",
				com.liferay.wsrp.model.WSRPConsumerPortlet.class);

		_dynamicQueryMethodKey4 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

		_dynamicQueryMethodKey5 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
				int.class, int.class);

		_dynamicQueryMethodKey6 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQuery",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class);

		_dynamicQueryCountMethodKey7 = new MethodKey(_classLoaderProxy.getClassName(),
				"dynamicQueryCount",
				com.liferay.portal.kernel.dao.orm.DynamicQuery.class);

		_getWSRPConsumerPortletMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"getWSRPConsumerPortlet", long.class);

		_getWSRPConsumerPortletsMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"getWSRPConsumerPortlets", int.class, int.class);

		_getWSRPConsumerPortletsCountMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"getWSRPConsumerPortletsCount");

		_updateWSRPConsumerPortletMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateWSRPConsumerPortlet",
				com.liferay.wsrp.model.WSRPConsumerPortlet.class);

		_updateWSRPConsumerPortletMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateWSRPConsumerPortlet",
				com.liferay.wsrp.model.WSRPConsumerPortlet.class, boolean.class);

		_addWSRPConsumerPortletMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
				"addWSRPConsumerPortlet", java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_addWSRPConsumerPortletMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
				"addWSRPConsumerPortlet", long.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteWSRPConsumerPortletMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteWSRPConsumerPortlet", java.lang.String.class);

		_deleteWSRPConsumerPortletsMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteWSRPConsumerPortlets", long.class);

		_destroyWSRPConsumerPortletMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
				"destroyWSRPConsumerPortlet", long.class,
				java.lang.String.class, java.lang.String.class);

		_destroyWSRPConsumerPortletsMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
				"destroyWSRPConsumerPortlets");

		_getWSRPConsumerPortletMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
				"getWSRPConsumerPortlet", java.lang.String.class);

		_getWSRPConsumerPortletMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
				"getWSRPConsumerPortlet", long.class, java.lang.String.class);

		_getWSRPConsumerPortletsMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
				"getWSRPConsumerPortlets", long.class, int.class, int.class);

		_getWSRPConsumerPortletsCountMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
				"getWSRPConsumerPortletsCount", long.class);

		_initFailedWSRPConsumerPortletsMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
				"initFailedWSRPConsumerPortlets");

		_initWSRPConsumerPortletMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
				"initWSRPConsumerPortlet", long.class, long.class, long.class,
				java.lang.String.class, java.lang.String.class,
				java.lang.String.class, java.lang.String.class);

		_initWSRPConsumerPortletsMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
				"initWSRPConsumerPortlets");

		_updateWSRPConsumerPortletMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateWSRPConsumerPortlet", long.class, java.lang.String.class);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addWSRPConsumerPortletMethodKey0,
				ClpSerializer.translateInput(wsrpConsumerPortlet));

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

		return (com.liferay.wsrp.model.WSRPConsumerPortlet)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet createWSRPConsumerPortlet(
		long wsrpConsumerPortletId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createWSRPConsumerPortletMethodKey1,
				wsrpConsumerPortletId);

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

		return (com.liferay.wsrp.model.WSRPConsumerPortlet)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteWSRPConsumerPortlet(long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteWSRPConsumerPortletMethodKey2,
				wsrpConsumerPortletId);

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

	public void deleteWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteWSRPConsumerPortletMethodKey3,
				ClpSerializer.translateInput(wsrpConsumerPortlet));

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

	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryMethodKey4,
				ClpSerializer.translateInput(dynamicQuery));

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
				ClpSerializer.translateInput(dynamicQuery), start, end);

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
				ClpSerializer.translateInput(dynamicQuery), start, end,
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

		return (java.util.List)ClpSerializer.translateOutput(returnObj);
	}

	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_dynamicQueryCountMethodKey7,
				ClpSerializer.translateInput(dynamicQuery));

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

	public com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerPortletId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getWSRPConsumerPortletMethodKey8,
				wsrpConsumerPortletId);

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

		return (com.liferay.wsrp.model.WSRPConsumerPortlet)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getWSRPConsumerPortletsMethodKey9,
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

		return (java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet>)ClpSerializer.translateOutput(returnObj);
	}

	public int getWSRPConsumerPortletsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getWSRPConsumerPortletsCountMethodKey10);

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

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateWSRPConsumerPortletMethodKey11,
				ClpSerializer.translateInput(wsrpConsumerPortlet));

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

		return (com.liferay.wsrp.model.WSRPConsumerPortlet)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		com.liferay.wsrp.model.WSRPConsumerPortlet wsrpConsumerPortlet,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateWSRPConsumerPortletMethodKey12,
				ClpSerializer.translateInput(wsrpConsumerPortlet), merge);

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

		return (com.liferay.wsrp.model.WSRPConsumerPortlet)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		java.lang.String wsrpConsumerUUID, java.lang.String name,
		java.lang.String portletHandle, java.lang.String userToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addWSRPConsumerPortletMethodKey13,
				ClpSerializer.translateInput(wsrpConsumerUUID),
				ClpSerializer.translateInput(name),
				ClpSerializer.translateInput(portletHandle),
				ClpSerializer.translateInput(userToken),
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

		return (com.liferay.wsrp.model.WSRPConsumerPortlet)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet addWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String name,
		java.lang.String portletHandle, java.lang.String userToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addWSRPConsumerPortletMethodKey14,
				wsrpConsumerId, ClpSerializer.translateInput(name),
				ClpSerializer.translateInput(portletHandle),
				ClpSerializer.translateInput(userToken),
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

		return (com.liferay.wsrp.model.WSRPConsumerPortlet)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteWSRPConsumerPortlet(
		java.lang.String wsrpConsumerPortletUUID)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteWSRPConsumerPortletMethodKey15,
				ClpSerializer.translateInput(wsrpConsumerPortletUUID));

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

	public void deleteWSRPConsumerPortlets(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteWSRPConsumerPortletsMethodKey16,
				wsrpConsumerId);

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

	public void destroyWSRPConsumerPortlet(long wsrpConsumerPortletId,
		java.lang.String uuid, java.lang.String url) {
		MethodHandler methodHandler = new MethodHandler(_destroyWSRPConsumerPortletMethodKey17,
				wsrpConsumerPortletId, ClpSerializer.translateInput(uuid),
				ClpSerializer.translateInput(url));

		try {
			_classLoaderProxy.invoke(methodHandler);
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
	}

	public void destroyWSRPConsumerPortlets()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_destroyWSRPConsumerPortletsMethodKey18);

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

	public com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		java.lang.String uuid)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getWSRPConsumerPortletMethodKey19,
				ClpSerializer.translateInput(uuid));

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

		return (com.liferay.wsrp.model.WSRPConsumerPortlet)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.wsrp.model.WSRPConsumerPortlet getWSRPConsumerPortlet(
		long wsrpConsumerId, java.lang.String portletHandle)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getWSRPConsumerPortletMethodKey20,
				wsrpConsumerId, ClpSerializer.translateInput(portletHandle));

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

		return (com.liferay.wsrp.model.WSRPConsumerPortlet)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet> getWSRPConsumerPortlets(
		long wsrpConsumerId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getWSRPConsumerPortletsMethodKey21,
				wsrpConsumerId, start, end);

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

		return (java.util.List<com.liferay.wsrp.model.WSRPConsumerPortlet>)ClpSerializer.translateOutput(returnObj);
	}

	public int getWSRPConsumerPortletsCount(long wsrpConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getWSRPConsumerPortletsCountMethodKey22,
				wsrpConsumerId);

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

	public void initFailedWSRPConsumerPortlets() {
		MethodHandler methodHandler = new MethodHandler(_initFailedWSRPConsumerPortletsMethodKey23);

		try {
			_classLoaderProxy.invoke(methodHandler);
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
	}

	public void initWSRPConsumerPortlet(long companyId, long wsrpConsumerId,
		long wsrpConsumerPortletId, java.lang.String uuid,
		java.lang.String name, java.lang.String portletHandle,
		java.lang.String userToken)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_initWSRPConsumerPortletMethodKey24,
				companyId, wsrpConsumerId, wsrpConsumerPortletId,
				ClpSerializer.translateInput(uuid),
				ClpSerializer.translateInput(name),
				ClpSerializer.translateInput(portletHandle),
				ClpSerializer.translateInput(userToken));

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

	public void initWSRPConsumerPortlets()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_initWSRPConsumerPortletsMethodKey25);

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

	public com.liferay.wsrp.model.WSRPConsumerPortlet updateWSRPConsumerPortlet(
		long wsrpConsumerPortletId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateWSRPConsumerPortletMethodKey26,
				wsrpConsumerPortletId, ClpSerializer.translateInput(name));

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

		return (com.liferay.wsrp.model.WSRPConsumerPortlet)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addWSRPConsumerPortletMethodKey0;
	private MethodKey _createWSRPConsumerPortletMethodKey1;
	private MethodKey _deleteWSRPConsumerPortletMethodKey2;
	private MethodKey _deleteWSRPConsumerPortletMethodKey3;
	private MethodKey _dynamicQueryMethodKey4;
	private MethodKey _dynamicQueryMethodKey5;
	private MethodKey _dynamicQueryMethodKey6;
	private MethodKey _dynamicQueryCountMethodKey7;
	private MethodKey _getWSRPConsumerPortletMethodKey8;
	private MethodKey _getWSRPConsumerPortletsMethodKey9;
	private MethodKey _getWSRPConsumerPortletsCountMethodKey10;
	private MethodKey _updateWSRPConsumerPortletMethodKey11;
	private MethodKey _updateWSRPConsumerPortletMethodKey12;
	private MethodKey _addWSRPConsumerPortletMethodKey13;
	private MethodKey _addWSRPConsumerPortletMethodKey14;
	private MethodKey _deleteWSRPConsumerPortletMethodKey15;
	private MethodKey _deleteWSRPConsumerPortletsMethodKey16;
	private MethodKey _destroyWSRPConsumerPortletMethodKey17;
	private MethodKey _destroyWSRPConsumerPortletsMethodKey18;
	private MethodKey _getWSRPConsumerPortletMethodKey19;
	private MethodKey _getWSRPConsumerPortletMethodKey20;
	private MethodKey _getWSRPConsumerPortletsMethodKey21;
	private MethodKey _getWSRPConsumerPortletsCountMethodKey22;
	private MethodKey _initFailedWSRPConsumerPortletsMethodKey23;
	private MethodKey _initWSRPConsumerPortletMethodKey24;
	private MethodKey _initWSRPConsumerPortletsMethodKey25;
	private MethodKey _updateWSRPConsumerPortletMethodKey26;
}