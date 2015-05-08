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

package com.liferay.socialcoding.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialcoding.model.JIRAProject;

import java.util.List;

/**
 * The persistence utility for the j i r a project service. This utility wraps {@link com.liferay.socialcoding.service.persistence.impl.JIRAProjectPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see JIRAProjectPersistence
 * @see com.liferay.socialcoding.service.persistence.impl.JIRAProjectPersistenceImpl
 * @generated
 */
@ProviderType
public class JIRAProjectUtil {
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
	public static void clearCache(JIRAProject jiraProject) {
		getPersistence().clearCache(jiraProject);
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
	public static List<JIRAProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<JIRAProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<JIRAProject> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<JIRAProject> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel)
	 */
	public static JIRAProject update(JIRAProject jiraProject) {
		return getPersistence().update(jiraProject);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, ServiceContext)
	 */
	public static JIRAProject update(JIRAProject jiraProject,
		ServiceContext serviceContext) {
		return getPersistence().update(jiraProject, serviceContext);
	}

	/**
	* Returns the j i r a project where key = &#63; or throws a {@link NoSuchJIRAProjectException} if it could not be found.
	*
	* @param key the key
	* @return the matching j i r a project
	* @throws NoSuchJIRAProjectException if a matching j i r a project could not be found
	*/
	public static JIRAProject findByKey(java.lang.String key)
		throws com.liferay.socialcoding.NoSuchJIRAProjectException {
		return getPersistence().findByKey(key);
	}

	/**
	* Returns the j i r a project where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching j i r a project, or <code>null</code> if a matching j i r a project could not be found
	*/
	public static JIRAProject fetchByKey(java.lang.String key) {
		return getPersistence().fetchByKey(key);
	}

	/**
	* Returns the j i r a project where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching j i r a project, or <code>null</code> if a matching j i r a project could not be found
	*/
	public static JIRAProject fetchByKey(java.lang.String key,
		boolean retrieveFromCache) {
		return getPersistence().fetchByKey(key, retrieveFromCache);
	}

	/**
	* Removes the j i r a project where key = &#63; from the database.
	*
	* @param key the key
	* @return the j i r a project that was removed
	*/
	public static JIRAProject removeByKey(java.lang.String key)
		throws com.liferay.socialcoding.NoSuchJIRAProjectException {
		return getPersistence().removeByKey(key);
	}

	/**
	* Returns the number of j i r a projects where key = &#63;.
	*
	* @param key the key
	* @return the number of matching j i r a projects
	*/
	public static int countByKey(java.lang.String key) {
		return getPersistence().countByKey(key);
	}

	/**
	* Caches the j i r a project in the entity cache if it is enabled.
	*
	* @param jiraProject the j i r a project
	*/
	public static void cacheResult(JIRAProject jiraProject) {
		getPersistence().cacheResult(jiraProject);
	}

	/**
	* Caches the j i r a projects in the entity cache if it is enabled.
	*
	* @param jiraProjects the j i r a projects
	*/
	public static void cacheResult(List<JIRAProject> jiraProjects) {
		getPersistence().cacheResult(jiraProjects);
	}

	/**
	* Creates a new j i r a project with the primary key. Does not add the j i r a project to the database.
	*
	* @param jiraProjectId the primary key for the new j i r a project
	* @return the new j i r a project
	*/
	public static JIRAProject create(long jiraProjectId) {
		return getPersistence().create(jiraProjectId);
	}

	/**
	* Removes the j i r a project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProjectId the primary key of the j i r a project
	* @return the j i r a project that was removed
	* @throws NoSuchJIRAProjectException if a j i r a project with the primary key could not be found
	*/
	public static JIRAProject remove(long jiraProjectId)
		throws com.liferay.socialcoding.NoSuchJIRAProjectException {
		return getPersistence().remove(jiraProjectId);
	}

	public static JIRAProject updateImpl(JIRAProject jiraProject) {
		return getPersistence().updateImpl(jiraProject);
	}

	/**
	* Returns the j i r a project with the primary key or throws a {@link NoSuchJIRAProjectException} if it could not be found.
	*
	* @param jiraProjectId the primary key of the j i r a project
	* @return the j i r a project
	* @throws NoSuchJIRAProjectException if a j i r a project with the primary key could not be found
	*/
	public static JIRAProject findByPrimaryKey(long jiraProjectId)
		throws com.liferay.socialcoding.NoSuchJIRAProjectException {
		return getPersistence().findByPrimaryKey(jiraProjectId);
	}

	/**
	* Returns the j i r a project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraProjectId the primary key of the j i r a project
	* @return the j i r a project, or <code>null</code> if a j i r a project with the primary key could not be found
	*/
	public static JIRAProject fetchByPrimaryKey(long jiraProjectId) {
		return getPersistence().fetchByPrimaryKey(jiraProjectId);
	}

	public static java.util.Map<java.io.Serializable, JIRAProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the j i r a projects.
	*
	* @return the j i r a projects
	*/
	public static List<JIRAProject> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the j i r a projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a projects
	* @param end the upper bound of the range of j i r a projects (not inclusive)
	* @return the range of j i r a projects
	*/
	public static List<JIRAProject> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the j i r a projects.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link JIRAProjectModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of j i r a projects
	* @param end the upper bound of the range of j i r a projects (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of j i r a projects
	*/
	public static List<JIRAProject> findAll(int start, int end,
		OrderByComparator<JIRAProject> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the j i r a projects from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of j i r a projects.
	*
	* @return the number of j i r a projects
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static JIRAProjectPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (JIRAProjectPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.getServletContextName(),
					JIRAProjectPersistence.class.getName());

			ReferenceRegistry.registerReference(JIRAProjectUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated As of 6.2.0
	 */
	@Deprecated
	public void setPersistence(JIRAProjectPersistence persistence) {
	}

	private static JIRAProjectPersistence _persistence;
}