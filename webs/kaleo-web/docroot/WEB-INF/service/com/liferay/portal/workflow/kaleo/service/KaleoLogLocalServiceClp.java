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

import com.liferay.portal.kernel.util.BooleanWrapper;
import com.liferay.portal.kernel.util.ClassLoaderProxy;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.LongWrapper;
import com.liferay.portal.kernel.util.NullWrapper;

/**
 * <a href="KaleoLogLocalServiceClp.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class KaleoLogLocalServiceClp implements KaleoLogLocalService {
	public KaleoLogLocalServiceClp(ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoLog);

		if (kaleoLog == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoLog");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addKaleoLog",
					new Object[] { paramObj0 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog createKaleoLog(
		long kaleoLogId) {
		Object paramObj0 = new LongWrapper(kaleoLogId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("createKaleoLog",
					new Object[] { paramObj0 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteKaleoLog(long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(kaleoLogId);

		try {
			_classLoaderProxy.invoke("deleteKaleoLog",
				new Object[] { paramObj0 });
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

	public void deleteKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoLog);

		if (kaleoLog == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoLog");
		}

		try {
			_classLoaderProxy.invoke("deleteKaleoLog",
				new Object[] { paramObj0 });
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

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0 });
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

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2 });
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

	@SuppressWarnings("unchecked")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object paramObj1 = new IntegerWrapper(start);

		Object paramObj2 = new IntegerWrapper(end);

		Object paramObj3 = ClpSerializer.translateInput(orderByComparator);

		if (orderByComparator == null) {
			paramObj3 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQuery",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
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
		Object paramObj0 = ClpSerializer.translateInput(dynamicQuery);

		if (dynamicQuery == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.kernel.dao.orm.DynamicQuery");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("dynamicQueryCount",
					new Object[] { paramObj0 });
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

	public com.liferay.portal.workflow.kaleo.model.KaleoLog getKaleoLog(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(kaleoLogId);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKaleoLog",
					new Object[] { paramObj0 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoLogs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new IntegerWrapper(start);

		Object paramObj1 = new IntegerWrapper(end);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKaleoLogs",
					new Object[] { paramObj0, paramObj1 });
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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKaleoLogsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKaleoLogsCount",
					new Object[0]);
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

	public com.liferay.portal.workflow.kaleo.model.KaleoLog updateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoLog);

		if (kaleoLog == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoLog");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateKaleoLog",
					new Object[] { paramObj0 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog updateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoLog);

		if (kaleoLog == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoLog");
		}

		Object paramObj1 = new BooleanWrapper(merge);

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("updateKaleoLog",
					new Object[] { paramObj0, paramObj1 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addActionExecutionKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoAction kaleoAction,
		long startTime, long endTime, java.lang.String comment,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoInstanceToken);

		if (kaleoInstanceToken == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken");
		}

		Object paramObj1 = ClpSerializer.translateInput(kaleoAction);

		if (kaleoAction == null) {
			paramObj1 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoAction");
		}

		Object paramObj2 = new LongWrapper(startTime);

		Object paramObj3 = new LongWrapper(endTime);

		Object paramObj4 = ClpSerializer.translateInput(comment);

		if (comment == null) {
			paramObj4 = new NullWrapper("java.lang.String");
		}

		Object paramObj5 = ClpSerializer.translateInput(serviceContext);

		if (serviceContext == null) {
			paramObj5 = new NullWrapper(
					"com.liferay.portal.service.ServiceContext");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addActionExecutionKaleoLog",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4,
						paramObj5
					});
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addNodeEntryKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoNode sourceKaleoNode,
		com.liferay.portal.workflow.kaleo.model.KaleoNode targetKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoInstanceToken);

		if (kaleoInstanceToken == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken");
		}

		Object paramObj1 = ClpSerializer.translateInput(sourceKaleoNode);

		if (sourceKaleoNode == null) {
			paramObj1 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoNode");
		}

		Object paramObj2 = ClpSerializer.translateInput(targetKaleoNode);

		if (targetKaleoNode == null) {
			paramObj2 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoNode");
		}

		Object paramObj3 = ClpSerializer.translateInput(serviceContext);

		if (serviceContext == null) {
			paramObj3 = new NullWrapper(
					"com.liferay.portal.service.ServiceContext");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addNodeEntryKaleoLog",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addNodeExitKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoNode departingKaleoNode,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoInstanceToken);

		if (kaleoInstanceToken == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken");
		}

		Object paramObj1 = ClpSerializer.translateInput(departingKaleoNode);

		if (departingKaleoNode == null) {
			paramObj1 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoNode");
		}

		Object paramObj2 = ClpSerializer.translateInput(serviceContext);

		if (serviceContext == null) {
			paramObj2 = new NullWrapper(
					"com.liferay.portal.service.ServiceContext");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addNodeExitKaleoLog",
					new Object[] { paramObj0, paramObj1, paramObj2 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskAssignmentKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken previousKaleoTaskInstanceToken,
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken newKaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(previousKaleoTaskInstanceToken);

		if (previousKaleoTaskInstanceToken == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken");
		}

		Object paramObj1 = ClpSerializer.translateInput(newKaleoTaskInstanceToken);

		if (newKaleoTaskInstanceToken == null) {
			paramObj1 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken");
		}

		Object paramObj2 = ClpSerializer.translateInput(comment);

		if (comment == null) {
			paramObj2 = new NullWrapper("java.lang.String");
		}

		Object paramObj3 = ClpSerializer.translateInput(workflowContext);

		if (workflowContext == null) {
			paramObj3 = new NullWrapper("java.util.Map");
		}

		Object paramObj4 = ClpSerializer.translateInput(serviceContext);

		if (serviceContext == null) {
			paramObj4 = new NullWrapper(
					"com.liferay.portal.service.ServiceContext");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addTaskAssignmentKaleoLog",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskCompletionKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoTaskInstanceToken);

		if (kaleoTaskInstanceToken == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken");
		}

		Object paramObj1 = ClpSerializer.translateInput(comment);

		if (comment == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(workflowContext);

		if (workflowContext == null) {
			paramObj2 = new NullWrapper("java.util.Map");
		}

		Object paramObj3 = ClpSerializer.translateInput(serviceContext);

		if (serviceContext == null) {
			paramObj3 = new NullWrapper(
					"com.liferay.portal.service.ServiceContext");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addTaskCompletionKaleoLog",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addTaskUpdateKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String comment,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoTaskInstanceToken);

		if (kaleoTaskInstanceToken == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken");
		}

		Object paramObj1 = ClpSerializer.translateInput(comment);

		if (comment == null) {
			paramObj1 = new NullWrapper("java.lang.String");
		}

		Object paramObj2 = ClpSerializer.translateInput(workflowContext);

		if (workflowContext == null) {
			paramObj2 = new NullWrapper("java.util.Map");
		}

		Object paramObj3 = ClpSerializer.translateInput(serviceContext);

		if (serviceContext == null) {
			paramObj3 = new NullWrapper(
					"com.liferay.portal.service.ServiceContext");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addTaskUpdateKaleoLog",
					new Object[] { paramObj0, paramObj1, paramObj2, paramObj3 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addWorkflowInstanceEndKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoInstanceToken);

		if (kaleoInstanceToken == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken");
		}

		Object paramObj1 = ClpSerializer.translateInput(serviceContext);

		if (serviceContext == null) {
			paramObj1 = new NullWrapper(
					"com.liferay.portal.service.ServiceContext");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addWorkflowInstanceEndKaleoLog",
					new Object[] { paramObj0, paramObj1 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog addWorkflowInstanceStartKaleoLog(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = ClpSerializer.translateInput(kaleoInstanceToken);

		if (kaleoInstanceToken == null) {
			paramObj0 = new NullWrapper(
					"com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken");
		}

		Object paramObj1 = ClpSerializer.translateInput(serviceContext);

		if (serviceContext == null) {
			paramObj1 = new NullWrapper(
					"com.liferay.portal.service.ServiceContext");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("addWorkflowInstanceStartKaleoLog",
					new Object[] { paramObj0, paramObj1 });
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoLog)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteKaleoDefinitionKaleoLogs(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(kaleoDefinitionId);

		try {
			_classLoaderProxy.invoke("deleteKaleoDefinitionKaleoLogs",
				new Object[] { paramObj0 });
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

	public void deleteKaleoInstanceKaleoLogs(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(kaleoInstanceId);

		try {
			_classLoaderProxy.invoke("deleteKaleoInstanceKaleoLogs",
				new Object[] { paramObj0 });
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

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoInstanceKaleoLogs(
		long kaleoInstanceId, java.util.List<java.lang.Integer> logTypes,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(kaleoInstanceId);

		Object paramObj1 = ClpSerializer.translateInput(logTypes);

		if (logTypes == null) {
			paramObj1 = new NullWrapper("java.util.List");
		}

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object paramObj4 = ClpSerializer.translateInput(orderByComparator);

		if (orderByComparator == null) {
			paramObj4 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKaleoInstanceKaleoLogs",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKaleoInstanceKaleoLogsCount(long kaleoInstanceId,
		java.util.List<java.lang.Integer> logTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(kaleoInstanceId);

		Object paramObj1 = ClpSerializer.translateInput(logTypes);

		if (logTypes == null) {
			paramObj1 = new NullWrapper("java.util.List");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKaleoInstanceKaleoLogsCount",
					new Object[] { paramObj0, paramObj1 });
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

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> getKaleoTaskKaleoLogs(
		long kaleoTaskId, java.util.List<java.lang.Integer> logTypes,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(kaleoTaskId);

		Object paramObj1 = ClpSerializer.translateInput(logTypes);

		if (logTypes == null) {
			paramObj1 = new NullWrapper("java.util.List");
		}

		Object paramObj2 = new IntegerWrapper(start);

		Object paramObj3 = new IntegerWrapper(end);

		Object paramObj4 = ClpSerializer.translateInput(orderByComparator);

		if (orderByComparator == null) {
			paramObj4 = new NullWrapper(
					"com.liferay.portal.kernel.util.OrderByComparator");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKaleoTaskKaleoLogs",
					new Object[] {
						paramObj0, paramObj1, paramObj2, paramObj3, paramObj4
					});
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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKaleoTaskKaleoLogsCount(long kaleoTaskId,
		java.util.List<java.lang.Integer> logTypes)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object paramObj0 = new LongWrapper(kaleoTaskId);

		Object paramObj1 = ClpSerializer.translateInput(logTypes);

		if (logTypes == null) {
			paramObj1 = new NullWrapper("java.util.List");
		}

		Object returnObj = null;

		try {
			returnObj = _classLoaderProxy.invoke("getKaleoTaskKaleoLogsCount",
					new Object[] { paramObj0, paramObj1 });
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

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
}