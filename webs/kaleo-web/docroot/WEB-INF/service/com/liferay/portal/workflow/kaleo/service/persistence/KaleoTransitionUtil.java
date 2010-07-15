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
import com.liferay.portal.workflow.kaleo.model.KaleoTransition;

import java.util.List;

/**
 * @author    Brian Wing Shun Chan
 * @see       KaleoTransitionPersistence
 * @see       KaleoTransitionPersistenceImpl
 * @generated
 */
public class KaleoTransitionUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(KaleoTransition kaleoTransition) {
		getPersistence().clearCache(kaleoTransition);
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
	public static List<KaleoTransition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoTransition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoTransition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoTransition remove(KaleoTransition kaleoTransition)
		throws SystemException {
		return getPersistence().remove(kaleoTransition);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoTransition update(KaleoTransition kaleoTransition,
		boolean merge) throws SystemException {
		return getPersistence().update(kaleoTransition, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoTransition update(KaleoTransition kaleoTransition,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kaleoTransition, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition) {
		getPersistence().cacheResult(kaleoTransition);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> kaleoTransitions) {
		getPersistence().cacheResult(kaleoTransitions);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition create(
		long kaleoTransitionId) {
		return getPersistence().create(kaleoTransitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition remove(
		long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence().remove(kaleoTransitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoTransition, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByPrimaryKey(
		long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence().findByPrimaryKey(kaleoTransitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByPrimaryKey(
		long kaleoTransitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoTransitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition[] findByCompanyId_PrevAndNext(
		long kaleoTransitionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoTransitionId, companyId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoDefinitionId(kaleoDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoDefinitionId(
		long kaleoDefinitionId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoDefinitionId(kaleoDefinitionId, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoDefinitionId_First(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoDefinitionId_First(kaleoDefinitionId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoDefinitionId_Last(
		long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoDefinitionId_Last(kaleoDefinitionId,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition[] findByKaleoDefinitionId_PrevAndNext(
		long kaleoTransitionId, long kaleoDefinitionId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoDefinitionId_PrevAndNext(kaleoTransitionId,
			kaleoDefinitionId, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoNodeId(kaleoNodeId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByKaleoNodeId(kaleoNodeId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findByKaleoNodeId(
		long kaleoNodeId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByKaleoNodeId(kaleoNodeId, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoNodeId_First(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoNodeId_First(kaleoNodeId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKaleoNodeId_Last(
		long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoNodeId_Last(kaleoNodeId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition[] findByKaleoNodeId_PrevAndNext(
		long kaleoTransitionId, long kaleoNodeId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence()
				   .findByKaleoNodeId_PrevAndNext(kaleoTransitionId,
			kaleoNodeId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKNI_N(
		long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence().findByKNI_N(kaleoNodeId, name);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_N(
		long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKNI_N(kaleoNodeId, name);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_N(
		long kaleoNodeId, java.lang.String name, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKNI_N(kaleoNodeId, name, retrieveFromCache);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition findByKNI_DT(
		long kaleoNodeId, boolean defaultTransition)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		return getPersistence().findByKNI_DT(kaleoNodeId, defaultTransition);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_DT(
		long kaleoNodeId, boolean defaultTransition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByKNI_DT(kaleoNodeId, defaultTransition);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoTransition fetchByKNI_DT(
		long kaleoNodeId, boolean defaultTransition, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByKNI_DT(kaleoNodeId, defaultTransition,
			retrieveFromCache);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> findAll(
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

	public static void removeByKaleoNodeId(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByKaleoNodeId(kaleoNodeId);
	}

	public static void removeByKNI_N(long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		getPersistence().removeByKNI_N(kaleoNodeId, name);
	}

	public static void removeByKNI_DT(long kaleoNodeId,
		boolean defaultTransition)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchTransitionException {
		getPersistence().removeByKNI_DT(kaleoNodeId, defaultTransition);
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

	public static int countByKaleoNodeId(long kaleoNodeId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKaleoNodeId(kaleoNodeId);
	}

	public static int countByKNI_N(long kaleoNodeId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKNI_N(kaleoNodeId, name);
	}

	public static int countByKNI_DT(long kaleoNodeId, boolean defaultTransition)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByKNI_DT(kaleoNodeId, defaultTransition);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static KaleoTransitionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoTransitionPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoTransitionPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(KaleoTransitionPersistence persistence) {
		_persistence = persistence;
	}

	private static KaleoTransitionPersistence _persistence;
}