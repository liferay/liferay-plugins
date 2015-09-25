/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.so.model.ProjectsEntry;

import java.util.List;

/**
 * The persistence utility for the projects entry service. This utility wraps {@link com.liferay.so.service.persistence.impl.ProjectsEntryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ProjectsEntryPersistence
 * @see com.liferay.so.service.persistence.impl.ProjectsEntryPersistenceImpl
 * @generated
 */
@ProviderType
public class ProjectsEntryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

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
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<ProjectsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<ProjectsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<ProjectsEntry> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<ProjectsEntry> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static ProjectsEntry update(ProjectsEntry projectsEntry) {
		return getPersistence().update(projectsEntry);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static ProjectsEntry update(ProjectsEntry projectsEntry,
		ServiceContext serviceContext) {
		return getPersistence().update(projectsEntry, serviceContext);
	}

	/**
	* Returns all the projects entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the matching projects entries
	*/
	public static List<ProjectsEntry> findByUserId(long userId) {
		return getPersistence().findByUserId(userId);
	}

	/**
	* Returns a range of all the projects entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @return the range of matching projects entries
	*/
	public static List<ProjectsEntry> findByUserId(long userId, int start,
		int end) {
		return getPersistence().findByUserId(userId, start, end);
	}

	/**
	* Returns an ordered range of all the projects entries where userId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userId the user ID
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching projects entries
	*/
	public static List<ProjectsEntry> findByUserId(long userId, int start,
		int end, OrderByComparator<ProjectsEntry> orderByComparator) {
		return getPersistence()
				   .findByUserId(userId, start, end, orderByComparator);
	}

	/**
	* Returns the first projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching projects entry
	* @throws NoSuchProjectsEntryException if a matching projects entry could not be found
	*/
	public static ProjectsEntry findByUserId_First(long userId,
		OrderByComparator<ProjectsEntry> orderByComparator)
		throws com.liferay.so.NoSuchProjectsEntryException {
		return getPersistence().findByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the first projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching projects entry, or <code>null</code> if a matching projects entry could not be found
	*/
	public static ProjectsEntry fetchByUserId_First(long userId,
		OrderByComparator<ProjectsEntry> orderByComparator) {
		return getPersistence().fetchByUserId_First(userId, orderByComparator);
	}

	/**
	* Returns the last projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching projects entry
	* @throws NoSuchProjectsEntryException if a matching projects entry could not be found
	*/
	public static ProjectsEntry findByUserId_Last(long userId,
		OrderByComparator<ProjectsEntry> orderByComparator)
		throws com.liferay.so.NoSuchProjectsEntryException {
		return getPersistence().findByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the last projects entry in the ordered set where userId = &#63;.
	*
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching projects entry, or <code>null</code> if a matching projects entry could not be found
	*/
	public static ProjectsEntry fetchByUserId_Last(long userId,
		OrderByComparator<ProjectsEntry> orderByComparator) {
		return getPersistence().fetchByUserId_Last(userId, orderByComparator);
	}

	/**
	* Returns the projects entries before and after the current projects entry in the ordered set where userId = &#63;.
	*
	* @param projectsEntryId the primary key of the current projects entry
	* @param userId the user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next projects entry
	* @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	*/
	public static ProjectsEntry[] findByUserId_PrevAndNext(
		long projectsEntryId, long userId,
		OrderByComparator<ProjectsEntry> orderByComparator)
		throws com.liferay.so.NoSuchProjectsEntryException {
		return getPersistence()
				   .findByUserId_PrevAndNext(projectsEntryId, userId,
			orderByComparator);
	}

	/**
	* Removes all the projects entries where userId = &#63; from the database.
	*
	* @param userId the user ID
	*/
	public static void removeByUserId(long userId) {
		getPersistence().removeByUserId(userId);
	}

	/**
	* Returns the number of projects entries where userId = &#63;.
	*
	* @param userId the user ID
	* @return the number of matching projects entries
	*/
	public static int countByUserId(long userId) {
		return getPersistence().countByUserId(userId);
	}

	/**
	* Caches the projects entry in the entity cache if it is enabled.
	*
	* @param projectsEntry the projects entry
	*/
	public static void cacheResult(ProjectsEntry projectsEntry) {
		getPersistence().cacheResult(projectsEntry);
	}

	/**
	* Caches the projects entries in the entity cache if it is enabled.
	*
	* @param projectsEntries the projects entries
	*/
	public static void cacheResult(List<ProjectsEntry> projectsEntries) {
		getPersistence().cacheResult(projectsEntries);
	}

	/**
	* Creates a new projects entry with the primary key. Does not add the projects entry to the database.
	*
	* @param projectsEntryId the primary key for the new projects entry
	* @return the new projects entry
	*/
	public static ProjectsEntry create(long projectsEntryId) {
		return getPersistence().create(projectsEntryId);
	}

	/**
	* Removes the projects entry with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry that was removed
	* @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	*/
	public static ProjectsEntry remove(long projectsEntryId)
		throws com.liferay.so.NoSuchProjectsEntryException {
		return getPersistence().remove(projectsEntryId);
	}

	public static ProjectsEntry updateImpl(ProjectsEntry projectsEntry) {
		return getPersistence().updateImpl(projectsEntry);
	}

	/**
	* Returns the projects entry with the primary key or throws a {@link NoSuchProjectsEntryException} if it could not be found.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry
	* @throws NoSuchProjectsEntryException if a projects entry with the primary key could not be found
	*/
	public static ProjectsEntry findByPrimaryKey(long projectsEntryId)
		throws com.liferay.so.NoSuchProjectsEntryException {
		return getPersistence().findByPrimaryKey(projectsEntryId);
	}

	/**
	* Returns the projects entry with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param projectsEntryId the primary key of the projects entry
	* @return the projects entry, or <code>null</code> if a projects entry with the primary key could not be found
	*/
	public static ProjectsEntry fetchByPrimaryKey(long projectsEntryId) {
		return getPersistence().fetchByPrimaryKey(projectsEntryId);
	}

	public static java.util.Map<java.io.Serializable, ProjectsEntry> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the projects entries.
	*
	* @return the projects entries
	*/
	public static List<ProjectsEntry> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the projects entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @return the range of projects entries
	*/
	public static List<ProjectsEntry> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the projects entries.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ProjectsEntryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of projects entries
	* @param end the upper bound of the range of projects entries (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of projects entries
	*/
	public static List<ProjectsEntry> findAll(int start, int end,
		OrderByComparator<ProjectsEntry> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the projects entries from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of projects entries.
	*
	* @return the number of projects entries
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static java.util.Set<java.lang.String> getBadColumnNames() {
		return getPersistence().getBadColumnNames();
	}

	public static ProjectsEntryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (ProjectsEntryPersistence)PortletBeanLocatorUtil.locate(com.liferay.so.service.ClpSerializer.getServletContextName(),
					ProjectsEntryPersistence.class.getName());

			ReferenceRegistry.registerReference(ProjectsEntryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(ProjectsEntryPersistence persistence) {
	}

	private static ProjectsEntryPersistence _persistence;
}