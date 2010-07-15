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
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment;

import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentPersistence
 * @see       KaleoTaskAssignmentPersistenceImpl
 * @generated
 */
public class KaleoTaskAssignmentUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(KaleoTaskAssignment kaleoTaskAssignment) {
		getPersistence().clearCache(kaleoTaskAssignment);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public long countWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<KaleoTaskAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoTaskAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoTaskAssignment> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTaskAssignment remove(
		KaleoTaskAssignment kaleoTaskAssignment) throws SystemException {
		return getPersistence().remove(kaleoTaskAssignment);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoTaskAssignment update(
		KaleoTaskAssignment kaleoTaskAssignment, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoTaskAssignment, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoTaskAssignment update(
		KaleoTaskAssignment kaleoTaskAssignment, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(kaleoTaskAssignment, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment) {
		getPersistence().cacheResult(kaleoTaskAssignment);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> kaleoTaskAssignments) {
		getPersistence().cacheResult(kaleoTaskAssignments);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment create(
		long kaleoTaskAssignmentId) {
		return getPersistence().create(kaleoTaskAssignmentId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment remove(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence().remove(kaleoTaskAssignmentId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoTaskAssignment, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByPrimaryKey(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence().findByPrimaryKey(kaleoTaskAssignmentId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment fetchByPrimaryKey(
		long kaleoTaskAssignmentId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoTaskAssignmentId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByCompanyId_PrevAndNext(
		long kaleoTaskAssignmentId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoTaskAssignmentId,
			companyId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoTaskAssignmentId,
			kaleoDefinitionId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoTaskId(
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoTaskId(kaleoTaskId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoTaskId(
		long kaleoTaskId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoTaskId(kaleoTaskId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByKaleoTaskId(
		long kaleoTaskId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoTaskId(kaleoTaskId, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoTaskId_First(
		long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoTaskId_First(kaleoTaskId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByKaleoTaskId_Last(
		long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoTaskId_Last(kaleoTaskId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByKaleoTaskId_PrevAndNext(
		long kaleoTaskAssignmentId, long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByKaleoTaskId_PrevAndNext(kaleoTaskAssignmentId,
			kaleoTaskId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByACN_KTI(
		java.lang.String assigneeClassName, long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByACN_KTI(assigneeClassName, kaleoTaskId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByACN_KTI(
		java.lang.String assigneeClassName, long kaleoTaskId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByACN_KTI(assigneeClassName, kaleoTaskId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findByACN_KTI(
		java.lang.String assigneeClassName, long kaleoTaskId, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByACN_KTI(assigneeClassName, kaleoTaskId, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByACN_KTI_First(
		java.lang.String assigneeClassName, long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByACN_KTI_First(assigneeClassName, kaleoTaskId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment findByACN_KTI_Last(
		java.lang.String assigneeClassName, long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByACN_KTI_Last(assigneeClassName, kaleoTaskId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment[] findByACN_KTI_PrevAndNext(
		long kaleoTaskAssignmentId, java.lang.String assigneeClassName,
		long kaleoTaskId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentException {
		return getPersistence()
				   .findByACN_KTI_PrevAndNext(kaleoTaskAssignmentId,
			assigneeClassName, kaleoTaskId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoDefinitionId(kaleoDefinitionId);
	}

	public static void removeByKaleoTaskId(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoTaskId(kaleoTaskId);
	}

	public static void removeByACN_KTI(java.lang.String assigneeClassName,
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByACN_KTI(assigneeClassName, kaleoTaskId);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByKaleoDefinitionId(long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoDefinitionId(kaleoDefinitionId);
	}

	public static int countByKaleoTaskId(long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoTaskId(kaleoTaskId);
	}

	public static int countByACN_KTI(java.lang.String assigneeClassName,
		long kaleoTaskId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByACN_KTI(assigneeClassName, kaleoTaskId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoTaskAssignmentPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoTaskAssignmentPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskAssignmentPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(KaleoTaskAssignmentPersistence persistence) {
		_persistence = persistence;
	}

	private static KaleoTaskAssignmentPersistence _persistence;
}