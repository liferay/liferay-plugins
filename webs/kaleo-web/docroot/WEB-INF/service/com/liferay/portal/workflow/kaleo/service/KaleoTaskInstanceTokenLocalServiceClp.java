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
public class KaleoTaskInstanceTokenLocalServiceClp
	implements KaleoTaskInstanceTokenLocalService {
	public KaleoTaskInstanceTokenLocalServiceClp(
		ClassLoaderProxy classLoaderProxy) {
		_classLoaderProxy = classLoaderProxy;

		_addKaleoTaskInstanceTokenMethodKey0 = new MethodKey(_classLoaderProxy.getClassName(),
				"addKaleoTaskInstanceToken",
				com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken.class);

		_createKaleoTaskInstanceTokenMethodKey1 = new MethodKey(_classLoaderProxy.getClassName(),
				"createKaleoTaskInstanceToken", long.class);

		_deleteKaleoTaskInstanceTokenMethodKey2 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteKaleoTaskInstanceToken", long.class);

		_deleteKaleoTaskInstanceTokenMethodKey3 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteKaleoTaskInstanceToken",
				com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken.class);

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

		_getKaleoTaskInstanceTokenMethodKey8 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceToken", long.class);

		_getKaleoTaskInstanceTokensMethodKey9 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceTokens", int.class, int.class);

		_getKaleoTaskInstanceTokensCountMethodKey10 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceTokensCount");

		_updateKaleoTaskInstanceTokenMethodKey11 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateKaleoTaskInstanceToken",
				com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken.class);

		_updateKaleoTaskInstanceTokenMethodKey12 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateKaleoTaskInstanceToken",
				com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken.class,
				boolean.class);

		_addKaleoTaskInstanceTokenMethodKey13 = new MethodKey(_classLoaderProxy.getClassName(),
				"addKaleoTaskInstanceToken", long.class, long.class,
				java.lang.String.class, java.util.Collection.class,
				java.util.Date.class, java.util.Map.class,
				com.liferay.portal.service.ServiceContext.class);

		_assignKaleoTaskInstanceTokenMethodKey14 = new MethodKey(_classLoaderProxy.getClassName(),
				"assignKaleoTaskInstanceToken", long.class,
				java.lang.String.class, long.class, java.util.Map.class,
				com.liferay.portal.service.ServiceContext.class);

		_completeKaleoTaskInstanceTokenMethodKey15 = new MethodKey(_classLoaderProxy.getClassName(),
				"completeKaleoTaskInstanceToken", long.class,
				com.liferay.portal.service.ServiceContext.class);

		_deleteCompanyKaleoTaskInstanceTokensMethodKey16 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteCompanyKaleoTaskInstanceTokens", long.class);

		_deleteKaleoDefinitionKaleoTaskInstanceTokensMethodKey17 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteKaleoDefinitionKaleoTaskInstanceTokens", long.class);

		_deleteKaleoInstanceKaleoTaskInstanceTokensMethodKey18 = new MethodKey(_classLoaderProxy.getClassName(),
				"deleteKaleoInstanceKaleoTaskInstanceTokens", long.class);

		_getCompanyKaleoTaskInstanceTokensMethodKey19 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCompanyKaleoTaskInstanceTokens", long.class, int.class,
				int.class);

		_getCompanyKaleoTaskInstanceTokensCountMethodKey20 = new MethodKey(_classLoaderProxy.getClassName(),
				"getCompanyKaleoTaskInstanceTokensCount", long.class);

		_getKaleoTaskInstanceTokensMethodKey21 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceTokens", java.lang.Boolean.class,
				int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class,
				com.liferay.portal.service.ServiceContext.class);

		_getKaleoTaskInstanceTokensMethodKey22 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceTokens", java.util.List.class,
				java.lang.Boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class,
				com.liferay.portal.service.ServiceContext.class);

		_getKaleoTaskInstanceTokensMethodKey23 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceTokens", long.class,
				java.lang.Boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class,
				com.liferay.portal.service.ServiceContext.class);

		_getKaleoTaskInstanceTokensMethodKey24 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceTokens", java.lang.String.class,
				long.class, java.lang.Boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class,
				com.liferay.portal.service.ServiceContext.class);

		_getKaleoTaskInstanceTokensCountMethodKey25 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceTokensCount", java.lang.Boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_getKaleoTaskInstanceTokensCountMethodKey26 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceTokensCount", java.util.List.class,
				java.lang.Boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_getKaleoTaskInstanceTokensCountMethodKey27 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceTokensCount", long.class,
				java.lang.Boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_getKaleoTaskInstanceTokensCountMethodKey28 = new MethodKey(_classLoaderProxy.getClassName(),
				"getKaleoTaskInstanceTokensCount", java.lang.String.class,
				long.class, java.lang.Boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_getSubmittingUserKaleoTaskInstanceTokensMethodKey29 = new MethodKey(_classLoaderProxy.getClassName(),
				"getSubmittingUserKaleoTaskInstanceTokens", long.class,
				java.lang.Boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class,
				com.liferay.portal.service.ServiceContext.class);

		_getSubmittingUserKaleoTaskInstanceTokensCountMethodKey30 = new MethodKey(_classLoaderProxy.getClassName(),
				"getSubmittingUserKaleoTaskInstanceTokensCount", long.class,
				java.lang.Boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_searchMethodKey31 = new MethodKey(_classLoaderProxy.getClassName(),
				"search", java.lang.String.class, java.lang.Boolean.class,
				java.lang.Boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class,
				com.liferay.portal.service.ServiceContext.class);

		_searchMethodKey32 = new MethodKey(_classLoaderProxy.getClassName(),
				"search", java.lang.String.class, java.lang.String.class,
				java.util.Date.class, java.util.Date.class,
				java.lang.Boolean.class, java.lang.Boolean.class,
				boolean.class, int.class, int.class,
				com.liferay.portal.kernel.util.OrderByComparator.class,
				com.liferay.portal.service.ServiceContext.class);

		_searchCountMethodKey33 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchCount", java.lang.String.class, java.lang.Boolean.class,
				java.lang.Boolean.class,
				com.liferay.portal.service.ServiceContext.class);

		_searchCountMethodKey34 = new MethodKey(_classLoaderProxy.getClassName(),
				"searchCount", java.lang.String.class, java.lang.String.class,
				java.util.Date.class, java.util.Date.class,
				java.lang.Boolean.class, java.lang.Boolean.class,
				boolean.class, com.liferay.portal.service.ServiceContext.class);

		_updateDueDateMethodKey35 = new MethodKey(_classLoaderProxy.getClassName(),
				"updateDueDate", long.class, java.util.Date.class,
				com.liferay.portal.service.ServiceContext.class);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken addKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKaleoTaskInstanceTokenMethodKey0,
				ClpSerializer.translateInput(kaleoTaskInstanceToken));

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken createKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId) {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_createKaleoTaskInstanceTokenMethodKey1,
				kaleoTaskInstanceTokenId);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteKaleoTaskInstanceToken(long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoTaskInstanceTokenMethodKey2,
				kaleoTaskInstanceTokenId);

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

	public void deleteKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoTaskInstanceTokenMethodKey3,
				ClpSerializer.translateInput(kaleoTaskInstanceToken));

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

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken getKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokenMethodKey8,
				kaleoTaskInstanceTokenId);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokensMethodKey9,
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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKaleoTaskInstanceTokensCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokensCountMethodKey10);

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

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKaleoTaskInstanceTokenMethodKey11,
				ClpSerializer.translateInput(kaleoTaskInstanceToken));

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateKaleoTaskInstanceToken(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken kaleoTaskInstanceToken,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateKaleoTaskInstanceTokenMethodKey12,
				ClpSerializer.translateInput(kaleoTaskInstanceToken), merge);

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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken addKaleoTaskInstanceToken(
		long kaleoInstanceTokenId, long kaleoTaskId,
		java.lang.String kaleoTaskName,
		java.util.Collection<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments,
		java.util.Date dueDate,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_addKaleoTaskInstanceTokenMethodKey13,
				kaleoInstanceTokenId, kaleoTaskId,
				ClpSerializer.translateInput(kaleoTaskName),
				ClpSerializer.translateInput(kaleoTaskAssignments),
				ClpSerializer.translateInput(dueDate),
				ClpSerializer.translateInput(workflowContext),
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken assignKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK,
		java.util.Map<java.lang.String, java.io.Serializable> workflowContext,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_assignKaleoTaskInstanceTokenMethodKey14,
				kaleoTaskInstanceTokenId,
				ClpSerializer.translateInput(assigneeClassName),
				assigneeClassPK, ClpSerializer.translateInput(workflowContext),
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)ClpSerializer.translateOutput(returnObj);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken completeKaleoTaskInstanceToken(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_completeKaleoTaskInstanceTokenMethodKey15,
				kaleoTaskInstanceTokenId,
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)ClpSerializer.translateOutput(returnObj);
	}

	public void deleteCompanyKaleoTaskInstanceTokens(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteCompanyKaleoTaskInstanceTokensMethodKey16,
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

	public void deleteKaleoDefinitionKaleoTaskInstanceTokens(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoDefinitionKaleoTaskInstanceTokensMethodKey17,
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

	public void deleteKaleoInstanceKaleoTaskInstanceTokens(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		MethodHandler methodHandler = new MethodHandler(_deleteKaleoInstanceKaleoTaskInstanceTokensMethodKey18,
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

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getCompanyKaleoTaskInstanceTokens(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCompanyKaleoTaskInstanceTokensMethodKey19,
				companyId, start, end);

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken>)ClpSerializer.translateOutput(returnObj);
	}

	public int getCompanyKaleoTaskInstanceTokensCount(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getCompanyKaleoTaskInstanceTokensCountMethodKey20,
				companyId);

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

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokensMethodKey21,
				ClpSerializer.translateInput(completed), start, end,
				ClpSerializer.translateInput(orderByComparator),
				ClpSerializer.translateInput(serviceContext));

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokensMethodKey22,
				ClpSerializer.translateInput(roleIds),
				ClpSerializer.translateInput(completed), start, end,
				ClpSerializer.translateInput(orderByComparator),
				ClpSerializer.translateInput(serviceContext));

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		long kaleoInstanceId, java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokensMethodKey23,
				kaleoInstanceId, ClpSerializer.translateInput(completed),
				start, end, ClpSerializer.translateInput(orderByComparator),
				ClpSerializer.translateInput(serviceContext));

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getKaleoTaskInstanceTokens(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokensMethodKey24,
				ClpSerializer.translateInput(assigneeClassName),
				assigneeClassPK, ClpSerializer.translateInput(completed),
				start, end, ClpSerializer.translateInput(orderByComparator),
				ClpSerializer.translateInput(serviceContext));

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken>)ClpSerializer.translateOutput(returnObj);
	}

	public int getKaleoTaskInstanceTokensCount(java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokensCountMethodKey25,
				ClpSerializer.translateInput(completed),
				ClpSerializer.translateInput(serviceContext));

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

	public int getKaleoTaskInstanceTokensCount(
		java.util.List<java.lang.Long> roleIds, java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokensCountMethodKey26,
				ClpSerializer.translateInput(roleIds),
				ClpSerializer.translateInput(completed),
				ClpSerializer.translateInput(serviceContext));

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

	public int getKaleoTaskInstanceTokensCount(long kaleoInstanceId,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokensCountMethodKey27,
				kaleoInstanceId, ClpSerializer.translateInput(completed),
				ClpSerializer.translateInput(serviceContext));

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

	public int getKaleoTaskInstanceTokensCount(
		java.lang.String assigneeClassName, long assigneeClassPK,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getKaleoTaskInstanceTokensCountMethodKey28,
				ClpSerializer.translateInput(assigneeClassName),
				assigneeClassPK, ClpSerializer.translateInput(completed),
				ClpSerializer.translateInput(serviceContext));

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

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> getSubmittingUserKaleoTaskInstanceTokens(
		long userId, java.lang.Boolean completed, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getSubmittingUserKaleoTaskInstanceTokensMethodKey29,
				userId, ClpSerializer.translateInput(completed), start, end,
				ClpSerializer.translateInput(orderByComparator),
				ClpSerializer.translateInput(serviceContext));

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken>)ClpSerializer.translateOutput(returnObj);
	}

	public int getSubmittingUserKaleoTaskInstanceTokensCount(long userId,
		java.lang.Boolean completed,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_getSubmittingUserKaleoTaskInstanceTokensCountMethodKey30,
				userId, ClpSerializer.translateInput(completed),
				ClpSerializer.translateInput(serviceContext));

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

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String keywords, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchMethodKey31,
				ClpSerializer.translateInput(keywords),
				ClpSerializer.translateInput(completed),
				ClpSerializer.translateInput(searchByUserRoles), start, end,
				ClpSerializer.translateInput(orderByComparator),
				ClpSerializer.translateInput(serviceContext));

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken>)ClpSerializer.translateOutput(returnObj);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken> search(
		java.lang.String taskName, java.lang.String assetType,
		java.util.Date dueDateGT, java.util.Date dueDateLT,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		boolean andOperator, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchMethodKey32,
				ClpSerializer.translateInput(taskName),
				ClpSerializer.translateInput(assetType),
				ClpSerializer.translateInput(dueDateGT),
				ClpSerializer.translateInput(dueDateLT),
				ClpSerializer.translateInput(completed),
				ClpSerializer.translateInput(searchByUserRoles), andOperator,
				start, end, ClpSerializer.translateInput(orderByComparator),
				ClpSerializer.translateInput(serviceContext));

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

		return (java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken>)ClpSerializer.translateOutput(returnObj);
	}

	public int searchCount(java.lang.String keywords,
		java.lang.Boolean completed, java.lang.Boolean searchByUserRoles,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchCountMethodKey33,
				ClpSerializer.translateInput(keywords),
				ClpSerializer.translateInput(completed),
				ClpSerializer.translateInput(searchByUserRoles),
				ClpSerializer.translateInput(serviceContext));

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

	public int searchCount(java.lang.String taskName,
		java.lang.String assetType, java.util.Date dueDateGT,
		java.util.Date dueDateLT, java.lang.Boolean completed,
		java.lang.Boolean searchByUserRoles, boolean andOperator,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_searchCountMethodKey34,
				ClpSerializer.translateInput(taskName),
				ClpSerializer.translateInput(assetType),
				ClpSerializer.translateInput(dueDateGT),
				ClpSerializer.translateInput(dueDateLT),
				ClpSerializer.translateInput(completed),
				ClpSerializer.translateInput(searchByUserRoles), andOperator,
				ClpSerializer.translateInput(serviceContext));

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

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken updateDueDate(
		long kaleoTaskInstanceTokenId, java.util.Date dueDate,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		Object returnObj = null;

		MethodHandler methodHandler = new MethodHandler(_updateDueDateMethodKey35,
				kaleoTaskInstanceTokenId,
				ClpSerializer.translateInput(dueDate),
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

		return (com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceToken)ClpSerializer.translateOutput(returnObj);
	}

	public ClassLoaderProxy getClassLoaderProxy() {
		return _classLoaderProxy;
	}

	private ClassLoaderProxy _classLoaderProxy;
	private MethodKey _addKaleoTaskInstanceTokenMethodKey0;
	private MethodKey _createKaleoTaskInstanceTokenMethodKey1;
	private MethodKey _deleteKaleoTaskInstanceTokenMethodKey2;
	private MethodKey _deleteKaleoTaskInstanceTokenMethodKey3;
	private MethodKey _dynamicQueryMethodKey4;
	private MethodKey _dynamicQueryMethodKey5;
	private MethodKey _dynamicQueryMethodKey6;
	private MethodKey _dynamicQueryCountMethodKey7;
	private MethodKey _getKaleoTaskInstanceTokenMethodKey8;
	private MethodKey _getKaleoTaskInstanceTokensMethodKey9;
	private MethodKey _getKaleoTaskInstanceTokensCountMethodKey10;
	private MethodKey _updateKaleoTaskInstanceTokenMethodKey11;
	private MethodKey _updateKaleoTaskInstanceTokenMethodKey12;
	private MethodKey _addKaleoTaskInstanceTokenMethodKey13;
	private MethodKey _assignKaleoTaskInstanceTokenMethodKey14;
	private MethodKey _completeKaleoTaskInstanceTokenMethodKey15;
	private MethodKey _deleteCompanyKaleoTaskInstanceTokensMethodKey16;
	private MethodKey _deleteKaleoDefinitionKaleoTaskInstanceTokensMethodKey17;
	private MethodKey _deleteKaleoInstanceKaleoTaskInstanceTokensMethodKey18;
	private MethodKey _getCompanyKaleoTaskInstanceTokensMethodKey19;
	private MethodKey _getCompanyKaleoTaskInstanceTokensCountMethodKey20;
	private MethodKey _getKaleoTaskInstanceTokensMethodKey21;
	private MethodKey _getKaleoTaskInstanceTokensMethodKey22;
	private MethodKey _getKaleoTaskInstanceTokensMethodKey23;
	private MethodKey _getKaleoTaskInstanceTokensMethodKey24;
	private MethodKey _getKaleoTaskInstanceTokensCountMethodKey25;
	private MethodKey _getKaleoTaskInstanceTokensCountMethodKey26;
	private MethodKey _getKaleoTaskInstanceTokensCountMethodKey27;
	private MethodKey _getKaleoTaskInstanceTokensCountMethodKey28;
	private MethodKey _getSubmittingUserKaleoTaskInstanceTokensMethodKey29;
	private MethodKey _getSubmittingUserKaleoTaskInstanceTokensCountMethodKey30;
	private MethodKey _searchMethodKey31;
	private MethodKey _searchMethodKey32;
	private MethodKey _searchCountMethodKey33;
	private MethodKey _searchCountMethodKey34;
	private MethodKey _updateDueDateMethodKey35;
}