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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;

/**
 * <a href="KaleoTaskAssignmentPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentPersistenceImpl
 * @see       KaleoTaskAssignmentUtil
 * @generated
 */
public interface KaleoTaskAssignmentPersistence extends BasePersistence<KaleoTaskAssignment> {
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment);

	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments);

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment create(
		long kaleoTaskAssignmentId);

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment remove(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByPrimaryKey(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByPrimaryKey(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoTaskId(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoTaskId(
		long kaleoTaskId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoTaskId(
		long kaleoTaskId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoTaskId_First(
		long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoTaskId_Last(
		long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByKaleoTaskId_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKTI_DA(
		long kaleoTaskId, boolean defaultAssignment)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByKTI_DA(
		long kaleoTaskId, boolean defaultAssignment)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByKTI_DA(
		long kaleoTaskId, boolean defaultAssignment, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByACN_ACP(
		java.lang.String assigneeClassName, long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByACN_ACP(
		java.lang.String assigneeClassName, long assigneeClassPK, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByACN_ACP(
		java.lang.String assigneeClassName, long assigneeClassPK, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByACN_ACP_First(
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByACN_ACP_Last(
		java.lang.String assigneeClassName, long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByACN_ACP_PrevAndNext(
		long kaleoTaskAssignmentId, java.lang.String assigneeClassName,
		long assigneeClassPK,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKaleoTaskId(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKTI_DA(long kaleoTaskId, boolean defaultAssignment)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException;

	public void removeByACN_ACP(java.lang.String assigneeClassName,
		long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKaleoTaskId(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKTI_DA(long kaleoTaskId, boolean defaultAssignment)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByACN_ACP(java.lang.String assigneeClassName,
		long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}