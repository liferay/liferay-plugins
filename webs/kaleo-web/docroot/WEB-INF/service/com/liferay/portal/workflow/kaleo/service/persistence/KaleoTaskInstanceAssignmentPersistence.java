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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;

/**
 * <a href="KaleoTaskInstanceAssignmentPersistence.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceAssignmentPersistenceImpl
 * @see       KaleoTaskInstanceAssignmentUtil
 * @generated
 */
public interface KaleoTaskInstanceAssignmentPersistence extends BasePersistence<KaleoTaskInstanceAssignment> {
	public void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment);

	public void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment> kaleoTaskInstanceAssignments);

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment create(
		long kaleoTaskInstanceAssignmentId);

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment remove(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment findByPrimaryKey(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment fetchByPrimaryKey(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment fetchByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment fetchByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment findByKTITI_ACPK_ACN(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment fetchByKTITI_ACPK_ACN(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment fetchByKTITI_ACPK_ACN(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException;

	public void removeByKTITI_ACPK_ACN(long kaleoTaskInstanceTokenId,
		java.lang.String assigneeClassName, long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByKTITI_ACPK_ACN(long kaleoTaskInstanceTokenId,
		java.lang.String assigneeClassName, long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}