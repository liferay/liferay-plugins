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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment;

import java.util.List;

/**
 * <a href="KaleoTaskInstanceAssignmentUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskInstanceAssignmentPersistence
 * @see       KaleoTaskInstanceAssignmentPersistenceImpl
 * @generated
 */
public class KaleoTaskInstanceAssignmentUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Object> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTaskInstanceAssignment remove(
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment)
		throws SystemException {
		return getPersistence().remove(kaleoTaskInstanceAssignment);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoTaskInstanceAssignment update(
		KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoTaskInstanceAssignment, merge);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment) {
		getPersistence().cacheResult(kaleoTaskInstanceAssignment);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment> kaleoTaskInstanceAssignments) {
		getPersistence().cacheResult(kaleoTaskInstanceAssignments);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment create(
		long kaleoTaskInstanceAssignmentId) {
		return getPersistence().create(kaleoTaskInstanceAssignmentId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment remove(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException {
		return getPersistence().remove(kaleoTaskInstanceAssignmentId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment kaleoTaskInstanceAssignment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoTaskInstanceAssignment, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment findByPrimaryKey(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException {
		return getPersistence().findByPrimaryKey(kaleoTaskInstanceAssignmentId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment fetchByPrimaryKey(
		long kaleoTaskInstanceAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoTaskInstanceAssignmentId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment findByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException {
		return getPersistence()
				   .findByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment fetchByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment fetchByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
			retrieveFromCache);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment findByKTITI_ACPK_ACN(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException {
		return getPersistence()
				   .findByKTITI_ACPK_ACN(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment fetchByKTITI_ACPK_ACN(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKTITI_ACPK_ACN(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment fetchByKTITI_ACPK_ACN(
		long kaleoTaskInstanceTokenId, java.lang.String assigneeClassName,
		long assigneeClassPK, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKTITI_ACPK_ACN(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK, retrieveFromCache);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskInstanceAssignment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException {
		getPersistence()
			.removeByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public static void removeByKTITI_ACPK_ACN(long kaleoTaskInstanceTokenId,
		java.lang.String assigneeClassName, long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskInstanceAssignmentException {
		getPersistence()
			.removeByKTITI_ACPK_ACN(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByKaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public static int countByKTITI_ACPK_ACN(long kaleoTaskInstanceTokenId,
		java.lang.String assigneeClassName, long assigneeClassPK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countByKTITI_ACPK_ACN(kaleoTaskInstanceTokenId,
			assigneeClassName, assigneeClassPK);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoTaskInstanceAssignmentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoTaskInstanceAssignmentPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskInstanceAssignmentPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(
		KaleoTaskInstanceAssignmentPersistence persistence) {
		_persistence = persistence;
	}

	private static KaleoTaskInstanceAssignmentPersistence _persistence;
}