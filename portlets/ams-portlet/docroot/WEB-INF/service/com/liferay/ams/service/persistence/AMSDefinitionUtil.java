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

package com.liferay.ams.service.persistence;

import com.liferay.ams.model.AMSDefinition;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;

import java.util.List;

/**
 * <a href="AMSDefinitionUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       AMSDefinitionPersistence
 * @see       AMSDefinitionPersistenceImpl
 * @generated
 */
public class AMSDefinitionUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(AMSDefinition)
	 */
	public static void clearCache(AMSDefinition amsDefinition) {
		getPersistence().clearCache(amsDefinition);
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
	public static List<AMSDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<AMSDefinition> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static AMSDefinition remove(AMSDefinition amsDefinition)
		throws SystemException {
		return getPersistence().remove(amsDefinition);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static AMSDefinition update(AMSDefinition amsDefinition,
		boolean merge) throws SystemException {
		return getPersistence().update(amsDefinition, merge);
	}

	public static void cacheResult(
		com.liferay.ams.model.AMSDefinition amsDefinition) {
		getPersistence().cacheResult(amsDefinition);
	}

	public static void cacheResult(
		java.util.List<com.liferay.ams.model.AMSDefinition> amsDefinitions) {
		getPersistence().cacheResult(amsDefinitions);
	}

	public static com.liferay.ams.model.AMSDefinition create(
		long assetDefinitionId) {
		return getPersistence().create(assetDefinitionId);
	}

	public static com.liferay.ams.model.AMSDefinition remove(
		long assetDefinitionId)
		throws com.liferay.ams.NoSuchDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(assetDefinitionId);
	}

	public static com.liferay.ams.model.AMSDefinition updateImpl(
		com.liferay.ams.model.AMSDefinition amsDefinition, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(amsDefinition, merge);
	}

	public static com.liferay.ams.model.AMSDefinition findByPrimaryKey(
		long assetDefinitionId)
		throws com.liferay.ams.NoSuchDefinitionException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(assetDefinitionId);
	}

	public static com.liferay.ams.model.AMSDefinition fetchByPrimaryKey(
		long assetDefinitionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(assetDefinitionId);
	}

	public static java.util.List<com.liferay.ams.model.AMSDefinition> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.ams.model.AMSDefinition> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.ams.model.AMSDefinition> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static AMSDefinitionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (AMSDefinitionPersistence)PortletBeanLocatorUtil.locate(com.liferay.ams.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					AMSDefinitionPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(AMSDefinitionPersistence persistence) {
		_persistence = persistence;
	}

	private static AMSDefinitionPersistence _persistence;
}