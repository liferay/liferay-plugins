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
public class KaleoInstanceLocalServiceClp implements KaleoInstanceLocalService {
	public KaleoInstanceLocalServiceClp(String className,
		ClassLoaderProxy classLoaderProxy) {
		_className = className;
		_classLoaderProxy = classLoaderProxy;
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance addKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKaleoInstanceMethodKey0,
				kaleoInstance);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoInstance)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance createKaleoInstance(
		long kaleoInstanceId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createKaleoInstanceMethodKey1,
				kaleoInstanceId);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoInstance)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteKaleoInstance(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoInstanceMethodKey2,
				kaleoInstanceId);

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

	public void deleteKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoInstanceMethodKey3,
				kaleoInstance);

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

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance getKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoInstanceMethodKey8,
				kaleoInstanceId);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoInstance)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoInstancesMethodKey9,
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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKaleoInstancesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoInstancesCountMethodKey10);

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

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance updateKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKaleoInstanceMethodKey11,
				kaleoInstance);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoInstance)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance updateKaleoInstance(
		com.liferay.portal.workflow.kaleo.model.KaleoInstance kaleoInstance,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKaleoInstanceMethodKey12,
				kaleoInstance, merge);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoInstance)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance addKaleoInstance(
		long kaleoDefinitionId, java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKaleoInstanceMethodKey13,
				kaleoDefinitionId, kaleoDefinitionName, kaleoDefinitionVersion,
				workflowContext, serviceContext);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoInstance)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance completeKaleoInstance(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_completeKaleoInstanceMethodKey14,
				kaleoInstanceId);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoInstance)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteCompanyKaleoInstances(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteCompanyKaleoInstancesMethodKey15,
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

	public void deleteKaleoDefinitionKaleoInstances(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoDefinitionKaleoInstancesMethodKey16,
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

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		java.lang.Long userId, java.lang.String assetClassName,
		java.lang.Long assetClassPK, java.lang.Boolean completed, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoInstancesMethodKey17,
				userId, assetClassName, assetClassPK, completed, start, end,
				orderByComparator, serviceContext);

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance> getKaleoInstances(
		java.lang.String kaleoDefinitionName, int kaleoDefinitionVersion,
		boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoInstancesMethodKey18,
				kaleoDefinitionName, kaleoDefinitionVersion, completed, start,
				end, orderByComparator, serviceContext);

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstance>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKaleoInstancesCount(long kaleoDefinitionId, boolean completed)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoInstancesCountMethodKey19,
				kaleoDefinitionId, completed);

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

	public int getKaleoInstancesCount(java.lang.Long userId,
		java.lang.String assetClassName, java.lang.Long assetClassPK,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoInstancesCountMethodKey20,
				userId, assetClassName, assetClassPK, completed, serviceContext);

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

	public int getKaleoInstancesCount(java.lang.String kaleoDefinitionName,
		int kaleoDefinitionVersion, boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoInstancesCountMethodKey21,
				kaleoDefinitionName, kaleoDefinitionVersion, completed,
				serviceContext);

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

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance updateKaleoInstance(
		long kaleoInstanceId,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKaleoInstanceMethodKey22,
				kaleoInstanceId, workflowContext, serviceContext);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoInstance)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private String _className;
	private ClassLoaderProxy _classLoaderProxy;
	private final MethodKey _addKaleoInstanceMethodKey0 = new MethodKey(_className,
			"addKaleoInstance",
			com.liferay.portal.workflow.kaleo.model.KaleoInstance.class);
	private final MethodKey _createKaleoInstanceMethodKey1 = new MethodKey(_className,
			"createKaleoInstance", long.class);
	private final MethodKey _deleteKaleoInstanceMethodKey2 = new MethodKey(_className,
			"deleteKaleoInstance", long.class);
	private final MethodKey _deleteKaleoInstanceMethodKey3 = new MethodKey(_className,
			"deleteKaleoInstance",
			com.liferay.portal.workflow.kaleo.model.KaleoInstance.class);
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
	private final MethodKey _getKaleoInstanceMethodKey8 = new MethodKey(_className,
			"getKaleoInstance", long.class);
	private final MethodKey _getKaleoInstancesMethodKey9 = new MethodKey(_className,
			"getKaleoInstances", int.class, int.class);
	private final MethodKey _getKaleoInstancesCountMethodKey10 = new MethodKey(_className,
			"getKaleoInstancesCount");
	private final MethodKey _updateKaleoInstanceMethodKey11 = new MethodKey(_className,
			"updateKaleoInstance",
			com.liferay.portal.workflow.kaleo.model.KaleoInstance.class);
	private final MethodKey _updateKaleoInstanceMethodKey12 = new MethodKey(_className,
			"updateKaleoInstance",
			com.liferay.portal.workflow.kaleo.model.KaleoInstance.class,
			boolean.class);
	private final MethodKey _addKaleoInstanceMethodKey13 = new MethodKey(_className,
			"addKaleoInstance", long.class, java.lang.String.class, int.class,
			java.util.Map.class, com.liferay.portal.service.ServiceContext.class);
	private final MethodKey _completeKaleoInstanceMethodKey14 = new MethodKey(_className,
			"completeKaleoInstance", long.class);
	private final MethodKey _deleteCompanyKaleoInstancesMethodKey15 = new MethodKey(_className,
			"deleteCompanyKaleoInstances", long.class);
	private final MethodKey _deleteKaleoDefinitionKaleoInstancesMethodKey16 = new MethodKey(_className,
			"deleteKaleoDefinitionKaleoInstances", long.class);
	private final MethodKey _getKaleoInstancesMethodKey17 = new MethodKey(_className,
			"getKaleoInstances", java.lang.Long.class, java.lang.String.class,
			java.lang.Long.class, java.lang.Boolean.class, int.class,
			int.class, com.liferay.portal.kernel.util.OrderByComparator.class,
			com.liferay.portal.service.ServiceContext.class);
	private final MethodKey _getKaleoInstancesMethodKey18 = new MethodKey(_className,
			"getKaleoInstances", java.lang.String.class, int.class,
			boolean.class, int.class, int.class,
			com.liferay.portal.kernel.util.OrderByComparator.class,
			com.liferay.portal.service.ServiceContext.class);
	private final MethodKey _getKaleoInstancesCountMethodKey19 = new MethodKey(_className,
			"getKaleoInstancesCount", long.class, boolean.class);
	private final MethodKey _getKaleoInstancesCountMethodKey20 = new MethodKey(_className,
			"getKaleoInstancesCount", java.lang.Long.class,
			java.lang.String.class, java.lang.Long.class,
			java.lang.Boolean.class,
			com.liferay.portal.service.ServiceContext.class);
	private final MethodKey _getKaleoInstancesCountMethodKey21 = new MethodKey(_className,
			"getKaleoInstancesCount", java.lang.String.class, int.class,
			boolean.class, com.liferay.portal.service.ServiceContext.class);
	private final MethodKey _updateKaleoInstanceMethodKey22 = new MethodKey(_className,
			"updateKaleoInstance", long.class, java.util.Map.class,
			com.liferay.portal.service.ServiceContext.class);
}