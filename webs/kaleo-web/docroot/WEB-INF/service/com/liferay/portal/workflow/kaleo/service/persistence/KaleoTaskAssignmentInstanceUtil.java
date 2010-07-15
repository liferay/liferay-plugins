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
import com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance;

import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignmentInstancePersistence
 * @see       KaleoTaskAssignmentInstancePersistenceImpl
 * @generated
 */
public class KaleoTaskAssignmentInstanceUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		getPersistence().clearCache(kaleoTaskAssignmentInstance);
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
	public static List<KaleoTaskAssignmentInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoTaskAssignmentInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoTaskAssignmentInstance> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTaskAssignmentInstance remove(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance)
		throws SystemException {
		return getPersistence().remove(kaleoTaskAssignmentInstance);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoTaskAssignmentInstance update(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance, boolean merge)
		throws SystemException {
		return getPersistence().update(kaleoTaskAssignmentInstance, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoTaskAssignmentInstance update(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence()
				   .update(kaleoTaskAssignmentInstance, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		getPersistence().cacheResult(kaleoTaskAssignmentInstance);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> kaleoTaskAssignmentInstances) {
		getPersistence().cacheResult(kaleoTaskAssignmentInstances);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance create(
		long kaleoTaskAssignmentInstanceId) {
		return getPersistence().create(kaleoTaskAssignmentInstanceId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance remove(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence().remove(kaleoTaskAssignmentInstanceId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoTaskAssignmentInstance, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByPrimaryKey(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence().findByPrimaryKey(kaleoTaskAssignmentInstanceId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance fetchByPrimaryKey(
		long kaleoTaskAssignmentInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoTaskAssignmentInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByCompanyId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoTaskAssignmentInstanceId,
			companyId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoTaskAssignmentInstanceId,
			kaleoDefinitionId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoInstanceId(kaleoInstanceId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoInstanceId(kaleoInstanceId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findByKaleoInstanceId(
		long kaleoInstanceId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoInstanceId(kaleoInstanceId, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoInstanceId_First(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoInstanceId_First(kaleoInstanceId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findByKaleoInstanceId_Last(
		long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoInstanceId_Last(kaleoInstanceId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findByKaleoInstanceId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoInstanceId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findByKaleoInstanceId_PrevAndNext(kaleoTaskAssignmentInstanceId,
			kaleoInstanceId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
			start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId,
			start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findBykaleoTaskInstanceTokenId_First(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId_First(kaleoTaskInstanceTokenId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance findBykaleoTaskInstanceTokenId_Last(
		long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId_Last(kaleoTaskInstanceTokenId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance[] findBykaleoTaskInstanceTokenId_PrevAndNext(
		long kaleoTaskAssignmentInstanceId, long kaleoTaskInstanceTokenId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTaskAssignmentInstanceException {
		return getPersistence()
				   .findBykaleoTaskInstanceTokenId_PrevAndNext(kaleoTaskAssignmentInstanceId,
			kaleoTaskInstanceTokenId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> findAll(
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

	public static void removeByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoInstanceId(kaleoInstanceId);
	}

	public static void removeBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence()
			.removeBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
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

	public static int countByKaleoInstanceId(long kaleoInstanceId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoInstanceId(kaleoInstanceId);
	}

	public static int countBykaleoTaskInstanceTokenId(
		long kaleoTaskInstanceTokenId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .countBykaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoTaskAssignmentInstancePersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoTaskAssignmentInstancePersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTaskAssignmentInstancePersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(
		KaleoTaskAssignmentInstancePersistence persistence) {
		_persistence = persistence;
	}

	private static KaleoTaskAssignmentInstancePersistence _persistence;
}