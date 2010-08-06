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
public class KaleoTaskAssignmentInstanceLocalServiceClp
	implements KaleoTaskAssignmentInstanceLocalService {
	public KaleoTaskAssignmentInstanceLocalServiceClp(String className,
		ClassLoaderProxy classLoaderProxy) {
		_className = className;
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKaleoTaskAssignmentInstanceMethodKey0,
				kaleoTaskAssignmentInstance);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance createKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createKaleoTaskAssignmentInstanceMethodKey1,
				kaleoTaskAssignmentInstanceId);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoTaskAssignmentInstanceMethodKey2,
				kaleoTaskAssignmentInstanceId);

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

	public void deleteKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoTaskAssignmentInstanceMethodKey3,
				kaleoTaskAssignmentInstance);

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

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance getKaleoTaskAssignmentInstance(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskAssignmentInstanceMethodKey8,
				kaleoTaskAssignmentInstanceId);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskAssignmentInstancesMethodKey9,
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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKaleoTaskAssignmentInstancesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskAssignmentInstancesCountMethodKey10);

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

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance updateKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKaleoTaskAssignmentInstanceMethodKey11,
				kaleoTaskAssignmentInstance);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance updateKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKaleoTaskAssignmentInstanceMethodKey12,
				kaleoTaskAssignmentInstance, merge);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance addKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKaleoTaskAssignmentInstanceMethodKey13,
				kaleoTaskInstanceToken, assigneeClassName, assigneeClassPK,
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> addTaskAssignmentInstances(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addTaskAssignmentInstancesMethodKey14,
				kaleoTaskInstanceToken, kaleoTaskAssignments, serviceContext);

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance assignKaleoTaskAssignmentInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_assignKaleoTaskAssignmentInstanceMethodKey15,
				kaleoTaskInstanceToken, assigneeClassName, assigneeClassPK,
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance completeKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_completeKaleoTaskInstanceTokenMethodKey16,
				kaleoTaskInstanceTokenId, serviceContext);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteCompanyKaleoTaskAssignmentInstances(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteCompanyKaleoTaskAssignmentInstancesMethodKey17,
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

	public void deleteKaleoDefinitionKaleoTaskAssignmentInstances(
		long kaleoDefintionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoDefinitionKaleoTaskAssignmentInstancesMethodKey18,
				kaleoDefintionId);

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

	public void deleteKaleoInstanceKaleoTaskAssignmentInstances(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoInstanceKaleoTaskAssignmentInstancesMethodKey19,
				kaleoInstanceId);

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

	public void deleteKaleoTaskAssignmentInstances(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoTaskAssignmentInstancesMethodKey20,
				kaleoTaskInstanceToken);

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

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> getKaleoTaskAssignmentInstances(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskAssignmentInstancesMethodKey21,
				kaleoTaskInstanceTokenId);

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance>)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private String _className;
	private ClassLoaderProxy _classLoaderProxy;
	private final MethodKey _addKaleoTaskAssignmentInstanceMethodKey0 = new MethodKey(_className,
			"addKaleoTaskAssignmentInstance",
			com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance.class);
	private final MethodKey _createKaleoTaskAssignmentInstanceMethodKey1 = new MethodKey(_className,
			"createKaleoTaskAssignmentInstance", long.class);
	private final MethodKey _deleteKaleoTaskAssignmentInstanceMethodKey2 = new MethodKey(_className,
			"deleteKaleoTaskAssignmentInstance", long.class);
	private final MethodKey _deleteKaleoTaskAssignmentInstanceMethodKey3 = new MethodKey(_className,
			"deleteKaleoTaskAssignmentInstance",
			com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance.class);
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
	private final MethodKey _getKaleoTaskAssignmentInstanceMethodKey8 = new MethodKey(_className,
			"getKaleoTaskAssignmentInstance", long.class);
	private final MethodKey _getKaleoTaskAssignmentInstancesMethodKey9 = new MethodKey(_className,
			"getKaleoTaskAssignmentInstances", int.class, int.class);
	private final MethodKey _getKaleoTaskAssignmentInstancesCountMethodKey10 = new MethodKey(_className,
			"getKaleoTaskAssignmentInstancesCount");
	private final MethodKey _updateKaleoTaskAssignmentInstanceMethodKey11 = new MethodKey(_className,
			"updateKaleoTaskAssignmentInstance",
			com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance.class);
	private final MethodKey _updateKaleoTaskAssignmentInstanceMethodKey12 = new MethodKey(_className,
			"updateKaleoTaskAssignmentInstance",
			com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance.class,
			boolean.class);
	private final MethodKey _addKaleoTaskAssignmentInstanceMethodKey13 = new MethodKey(_className,
			"addKaleoTaskAssignmentInstance",
			com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken.class,
			java.lang.String.class, long.class,
			com.liferay.portal.service.ServiceContext.class);
	private final MethodKey _addTaskAssignmentInstancesMethodKey14 = new MethodKey(_className,
			"addTaskAssignmentInstances",
			com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken.class,
			java.util.Collection.class,
			com.liferay.portal.service.ServiceContext.class);
	private final MethodKey _assignKaleoTaskAssignmentInstanceMethodKey15 = new MethodKey(_className,
			"assignKaleoTaskAssignmentInstance",
			com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken.class,
			java.lang.String.class, long.class,
			com.liferay.portal.service.ServiceContext.class);
	private final MethodKey _completeKaleoTaskInstanceTokenMethodKey16 = new MethodKey(_className,
			"completeKaleoTaskInstanceToken", long.class,
			com.liferay.portal.service.ServiceContext.class);
	private final MethodKey _deleteCompanyKaleoTaskAssignmentInstancesMethodKey17 =
		new MethodKey(_className, "deleteCompanyKaleoTaskAssignmentInstances",
			long.class);
	private final MethodKey _deleteKaleoDefinitionKaleoTaskAssignmentInstancesMethodKey18 =
		new MethodKey(_className,
			"deleteKaleoDefinitionKaleoTaskAssignmentInstances", long.class);
	private final MethodKey _deleteKaleoInstanceKaleoTaskAssignmentInstancesMethodKey19 =
		new MethodKey(_className,
			"deleteKaleoInstanceKaleoTaskAssignmentInstances", long.class);
	private final MethodKey _deleteKaleoTaskAssignmentInstancesMethodKey20 = new MethodKey(_className,
			"deleteKaleoTaskAssignmentInstances",
			com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken.class);
	private final MethodKey _getKaleoTaskAssignmentInstancesMethodKey21 = new MethodKey(_className,
			"getKaleoTaskAssignmentInstances", long.class);
}