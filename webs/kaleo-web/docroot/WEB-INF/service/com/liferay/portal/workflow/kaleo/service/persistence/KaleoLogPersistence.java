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

package com.liferay.portal.workflow.kaleo.service.persistence;

import com.liferay.portal.service.persistence.BasePersistence;
import com.liferay.portal.workflow.kaleo.model.KaleoLog;

/**
 * @author    Brian Wing Shun Chan
 * @see       KaleoLogPersistenceImpl
 * @see       KaleoLogUtil
 * @generated
 */
public interface KaleoLogPersistence extends BasePersistence<KaleoLog> {
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog);

	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> kaleoLogs);

	public com.liferay.portal.workflow.kaleo.model.KaleoLog create(
		long kaleoLogId);

	public com.liferay.portal.workflow.kaleo.model.KaleoLog remove(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByPrimaryKey(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog fetchByPrimaryKey(
		long kaleoLogId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog[] findByCompanyId_PrevAndNext(
		long kaleoLogId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoLogId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKaleoInstanceId(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog[] findByKaleoInstanceId_PrevAndNext(
		long kaleoLogId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByKaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByKaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog[] findByKaleoTaskInstanceTokenId_PrevAndNext(
		long kaleoLogId, long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKITI_T(
		long kaleoInstanceTokenId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKITI_T(
		long kaleoInstanceTokenId, java.lang.String type, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKITI_T(
		long kaleoInstanceTokenId, java.lang.String type, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByKITI_T_First(
		long kaleoInstanceTokenId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByKITI_T_Last(
		long kaleoInstanceTokenId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog[] findByKITI_T_PrevAndNext(
		long kaleoLogId, long kaleoInstanceTokenId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKITI_KNI_T(
		long kaleoInstanceTokenId, long kaleoNodeId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKITI_KNI_T(
		long kaleoInstanceTokenId, long kaleoNodeId, java.lang.String type,
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findByKITI_KNI_T(
		long kaleoInstanceTokenId, long kaleoNodeId, java.lang.String type,
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByKITI_KNI_T_First(
		long kaleoInstanceTokenId, long kaleoNodeId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog findByKITI_KNI_T_Last(
		long kaleoInstanceTokenId, long kaleoNodeId, java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public com.liferay.portal.workflow.kaleo.model.KaleoLog[] findByKITI_KNI_T_PrevAndNext(
		long kaleoLogId, long kaleoInstanceTokenId, long kaleoNodeId,
		java.lang.String type,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchLogException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoLog> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKITI_T(long kaleoInstanceTokenId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKITI_KNI_T(long kaleoInstanceTokenId, long kaleoNodeId,
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKITI_T(long kaleoInstanceTokenId, java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKITI_KNI_T(long kaleoInstanceTokenId, long kaleoNodeId,
		java.lang.String type)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}