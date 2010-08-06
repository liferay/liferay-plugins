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

package com.liferay.portal.workflow.kaleo.service;

import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.MethodHandler;
import com.liferay.portal.kernel.util.MethodKey;

/**
 * @author Brian Wing Shun Chan
 */
public class KaleoNotificationRecipientLocalServiceClp
	implements KaleoNotificationRecipientLocalService {
	public KaleoNotificationRecipientLocalServiceClp(String className,
		ClassLoaderProxy classLoaderProxy) {
		_className = className;
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient addKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKaleoNotificationRecipientMethodKey0,
				kaleoNotificationRecipient);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient createKaleoNotificationRecipient(
		long kaleoNotificationRecipientId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createKaleoNotificationRecipientMethodKey1,
				kaleoNotificationRecipientId);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoNotificationRecipientMethodKey2,
				kaleoNotificationRecipientId);

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

	public void deleteKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoNotificationRecipientMethodKey3,
				kaleoNotificationRecipient);

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

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient getKaleoNotificationRecipient(
		long kaleoNotificationRecipientId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoNotificationRecipientMethodKey8,
				kaleoNotificationRecipientId);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoNotificationRecipientsMethodKey9,
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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKaleoNotificationRecipientsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoNotificationRecipientsCountMethodKey10);

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

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKaleoNotificationRecipientMethodKey11,
				kaleoNotificationRecipient);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient updateKaleoNotificationRecipient(
		com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient kaleoNotificationRecipient,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKaleoNotificationRecipientMethodKey12,
				kaleoNotificationRecipient, merge);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient addKaleoNotificationRecipient(
		long kaleoDefinitionId, long kaleoNotificationId,
		com.liferay.portal.workflow.kaleo.definition.Recipient recipient,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKaleoNotificationRecipientMethodKey13,
				kaleoDefinitionId, kaleoNotificationId, recipient,
				serviceContext);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteCompanyKaleoNotificationRecipients(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteCompanyKaleoNotificationRecipientsMethodKey14,
				companyId);

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

	public void deleteKaleoDefinitionKaleoNotificationRecipients(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoDefinitionKaleoNotificationRecipientsMethodKey15,
				kaleoDefinitionId);

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

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient> getKaleoNotificationRecipients(
		long kaleoNotificationId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoNotificationRecipientsMethodKey16,
				kaleoNotificationId);

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient>)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private String _className;
	private ClassLoaderProxy _classLoaderProxy;
	private final MethodKey _addKaleoNotificationRecipientMethodKey0 = new MethodKey(_className,
			"addKaleoNotificationRecipient",
			com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient.class);
	private final MethodKey _createKaleoNotificationRecipientMethodKey1 = new MethodKey(_className,
			"createKaleoNotificationRecipient", long.class);
	private final MethodKey _deleteKaleoNotificationRecipientMethodKey2 = new MethodKey(_className,
			"deleteKaleoNotificationRecipient", long.class);
	private final MethodKey _deleteKaleoNotificationRecipientMethodKey3 = new MethodKey(_className,
			"deleteKaleoNotificationRecipient",
			com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient.class);
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
	private final MethodKey _getKaleoNotificationRecipientMethodKey8 = new MethodKey(_className,
			"getKaleoNotificationRecipient", long.class);
	private final MethodKey _getKaleoNotificationRecipientsMethodKey9 = new MethodKey(_className,
			"getKaleoNotificationRecipients", int.class, int.class);
	private final MethodKey _getKaleoNotificationRecipientsCountMethodKey10 = new MethodKey(_className,
			"getKaleoNotificationRecipientsCount");
	private final MethodKey _updateKaleoNotificationRecipientMethodKey11 = new MethodKey(_className,
			"updateKaleoNotificationRecipient",
			com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient.class);
	private final MethodKey _updateKaleoNotificationRecipientMethodKey12 = new MethodKey(_className,
			"updateKaleoNotificationRecipient",
			com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient.class,
			boolean.class);
	private final MethodKey _addKaleoNotificationRecipientMethodKey13 = new MethodKey(_className,
			"addKaleoNotificationRecipient", long.class, long.class,
			com.liferay.portal.workflow.kaleo.definition.Recipient.class,
			com.liferay.portal.service.ServiceContext.class);
	private final MethodKey _deleteCompanyKaleoNotificationRecipientsMethodKey14 =
		new MethodKey(_className, "deleteCompanyKaleoNotificationRecipients",
			long.class);
	private final MethodKey _deleteKaleoDefinitionKaleoNotificationRecipientsMethodKey15 =
		new MethodKey(_className,
			"deleteKaleoDefinitionKaleoNotificationRecipients", long.class);
	private final MethodKey _getKaleoNotificationRecipientsMethodKey16 = new MethodKey(_className,
			"getKaleoNotificationRecipients", long.class);
}