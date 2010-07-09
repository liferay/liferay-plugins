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
import com.liferay.portal.workflow.kaleo.model.KaleoDefinition;

import java.util.List;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoDefinitionPersistence
 * @see       KaleoDefinitionPersistenceImpl
 * @generated
 */
public class KaleoDefinitionUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(KaleoDefinition kaleoDefinition) {
		getPersistence().clearCache(kaleoDefinition);
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
	public static List<KaleoDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<KaleoDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<KaleoDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static KaleoDefinition remove(KaleoDefinition kaleoDefinition)
		throws SystemException {
		return getPersistence().remove(kaleoDefinition);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static KaleoDefinition update(KaleoDefinition kaleoDefinition,
		boolean merge) throws SystemException {
		return getPersistence().update(kaleoDefinition, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static KaleoDefinition update(KaleoDefinition kaleoDefinition,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(kaleoDefinition, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition) {
		getPersistence().cacheResult(kaleoDefinition);
	}

	public static void cacheResult(
		java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> kaleoDefinitions) {
		getPersistence().cacheResult(kaleoDefinitions);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition create(
		long kaleoDefinitionId) {
		return getPersistence().create(kaleoDefinitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition remove(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence().remove(kaleoDefinitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition updateImpl(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(kaleoDefinition, merge);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByPrimaryKey(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence().findByPrimaryKey(kaleoDefinitionId);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByPrimaryKey(
		long kaleoDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(kaleoDefinitionId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByCompanyId(
		long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByCompanyId(
		long companyId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByCompanyId(companyId, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByCompanyId(
		long companyId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByCompanyId(companyId, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByCompanyId_First(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByCompanyId_First(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByCompanyId_Last(
		long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByCompanyId_Last(companyId, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition[] findByCompanyId_PrevAndNext(
		long kaleoDefinitionId, long companyId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByCompanyId_PrevAndNext(kaleoDefinitionId, companyId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N(
		long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_N(companyId, name);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N(
		long companyId, java.lang.String name, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_N(companyId, name, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N(
		long companyId, java.lang.String name, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_N(companyId, name, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_N_First(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_First(companyId, name, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_N_Last(
		long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_Last(companyId, name, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition[] findByC_N_PrevAndNext(
		long kaleoDefinitionId, long companyId, java.lang.String name,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_PrevAndNext(kaleoDefinitionId, companyId, name,
			orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_A(
		long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_A(companyId, active);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_A(
		long companyId, boolean active, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_A(companyId, active, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_A(
		long companyId, boolean active, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_A(companyId, active, start, end, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_A_First(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_A_First(companyId, active, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_A_Last(
		long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_A_Last(companyId, active, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition[] findByC_A_PrevAndNext(
		long kaleoDefinitionId, long companyId, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_A_PrevAndNext(kaleoDefinitionId, companyId, active,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_N_V(
		long companyId, java.lang.String name, int version)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence().findByC_N_V(companyId, name, version);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByC_N_V(
		long companyId, java.lang.String name, int version)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByC_N_V(companyId, name, version);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition fetchByC_N_V(
		long companyId, java.lang.String name, int version,
		boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByC_N_V(companyId, name, version, retrieveFromCache);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N_A(
		long companyId, java.lang.String name, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_N_A(companyId, name, active);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N_A(
		long companyId, java.lang.String name, boolean active, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByC_N_A(companyId, name, active, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findByC_N_A(
		long companyId, java.lang.String name, boolean active, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByC_N_A(companyId, name, active, start, end,
			orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_N_A_First(
		long companyId, java.lang.String name, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_A_First(companyId, name, active, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition findByC_N_A_Last(
		long companyId, java.lang.String name, boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_A_Last(companyId, name, active, orderByComparator);
	}

	public static com.liferay.portal.workflow.kaleo.model.KaleoDefinition[] findByC_N_A_PrevAndNext(
		long kaleoDefinitionId, long companyId, java.lang.String name,
		boolean active,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		return getPersistence()
				   .findByC_N_A_PrevAndNext(kaleoDefinitionId, companyId, name,
			active, orderByComparator);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoDefinition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByCompanyId(companyId);
	}

	public static void removeByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_N(companyId, name);
	}

	public static void removeByC_A(long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_A(companyId, active);
	}

	public static void removeByC_N_V(long companyId, java.lang.String name,
		int version)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.portal.workflow.kaleo.NoSuchDefinitionException {
		getPersistence().removeByC_N_V(companyId, name, version);
	}

	public static void removeByC_N_A(long companyId, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByC_N_A(companyId, name, active);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByCompanyId(long companyId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByCompanyId(companyId);
	}

	public static int countByC_N(long companyId, java.lang.String name)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_N(companyId, name);
	}

	public static int countByC_A(long companyId, boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_A(companyId, active);
	}

	public static int countByC_N_V(long companyId, java.lang.String name,
		int version) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_N_V(companyId, name, version);
	}

	public static int countByC_N_A(long companyId, java.lang.String name,
		boolean active)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByC_N_A(companyId, name, active);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> getKaleoNodes(
		long pk) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoNodes(pk);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> getKaleoNodes(
		long pk, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoNodes(pk, start, end);
	}

	public static java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoNode> getKaleoNodes(
		long pk, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoNodes(pk, start, end, orderByComparator);
	}

	public static int getKaleoNodesSize(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().getKaleoNodesSize(pk);
	}

	public static boolean containsKaleoNode(long pk, long kaleoNodePK)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsKaleoNode(pk, kaleoNodePK);
	}

	public static boolean containsKaleoNodes(long pk)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().containsKaleoNodes(pk);
	}

	public static KaleoDefinitionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (KaleoDefinitionPersistence)PortletBeanLocatorUtil.locate(com.liferay.portal.workflow.kaleo.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					KaleoDefinitionPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(KaleoDefinitionPersistence persistence) {
		_persistence = persistence;
	}

	private static KaleoDefinitionPersistence _persistence;
}