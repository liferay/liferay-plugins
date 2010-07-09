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

package com.liferay.so.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import com.liferay.so.model.ProjectsEntry;

import java.util.List;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ProjectsEntryPersistence
 * @see       ProjectsEntryPersistenceImpl
 * @generated
 */
public class ProjectsEntryUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(com.liferay.portal.model.BaseModel)
	 */
	public static void clearCache(ProjectsEntry projectsEntry) {
		getPersistence().clearCache(projectsEntry);
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
	public static List<ProjectsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProjectsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProjectsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static ProjectsEntry remove(ProjectsEntry projectsEntry)
		throws SystemException {
		return getPersistence().remove(projectsEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static ProjectsEntry update(ProjectsEntry projectsEntry,
		boolean merge) throws SystemException {
		return getPersistence().update(projectsEntry, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static ProjectsEntry update(ProjectsEntry projectsEntry,
		boolean merge, ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(projectsEntry, merge, serviceContext);
	}

	public static void cacheResult(
		com.liferay.so.model.ProjectsEntry projectsEntry) {
		getPersistence().cacheResult(projectsEntry);
	}

	public static void cacheResult(
		java.util.List<com.liferay.so.model.ProjectsEntry> projectsEntries) {
		getPersistence().cacheResult(projectsEntries);
	}

	public static com.liferay.so.model.ProjectsEntry create(
		long projectsEntryId) {
		return getPersistence().create(projectsEntryId);
	}

	public static com.liferay.so.model.ProjectsEntry remove(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException {
		return getPersistence().remove(projectsEntryId);
	}

	public static com.liferay.so.model.ProjectsEntry updateImpl(
		com.liferay.so.model.ProjectsEntry projectsEntry, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(projectsEntry, merge);
	}

	public static com.liferay.so.model.ProjectsEntry findByPrimaryKey(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException {
		return getPersistence().findByPrimaryKey(projectsEntryId);
	}

	public static com.liferay.so.model.ProjectsEntry fetchByPrimaryKey(
		long projectsEntryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(projectsEntryId);
	}

	public static java.util.List<com.liferay.so.model.ProjectsEntry> findByUserId(
		long userId) throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId);
	}

	public static java.util.List<com.liferay.so.model.ProjectsEntry> findByUserId(
		long userId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByUserId(userId, start, end);
	}

	public static java.util.List<com.liferay.so.model.ProjectsEntry> findByUserId(
		long userId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	public static com.liferay.so.model.ProjectsEntry findByUserId_First(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	public static com.liferay.so.model.ProjectsEntry findByUserId_Last(
		long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	public static com.liferay.so.model.ProjectsEntry[] findByUserId_PrevAndNext(
		long projectsEntryId, long userId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.so.NoSuchProjectsEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(projectsEntryId, userId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.so.model.ProjectsEntry> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.so.model.ProjectsEntry> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.so.model.ProjectsEntry> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByUserId(userId);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByUserId(long userId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByUserId(userId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static ProjectsEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ProjectsEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.so.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					ProjectsEntryPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(ProjectsEntryPersistence persistence) {
		_persistence = persistence;
	}

	private static ProjectsEntryPersistence _persistence;
}