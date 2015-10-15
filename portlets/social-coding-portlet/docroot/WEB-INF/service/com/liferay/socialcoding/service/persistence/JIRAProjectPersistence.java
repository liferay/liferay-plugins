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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialcoding.model.JIRAProject;

/**
 * The persistence interface for the j i r a project service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.socialcoding.service.persistence.impl.JIRAProjectPersistenceImpl
 * @see JIRAProjectUtil
 * @generated
 */
@ProviderType
public interface JIRAProjectPersistence extends BasePersistence<JIRAProject> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link JIRAProjectUtil} to access the j i r a project persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the j i r a project where key = &#63; or throws a {@link NoSuchJIRAProjectException} if it could not be found.
	*
	* @param key the key
	* @return the matching j i r a project
	* @throws NoSuchJIRAProjectException if a matching j i r a project could not be found
	*/
	public JIRAProject findByKey(java.lang.String key)
		throws com.liferay.socialcoding.NoSuchJIRAProjectException;

	/**
	* Returns the j i r a project where key = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param key the key
	* @return the matching j i r a project, or <code>null</code> if a matching j i r a project could not be found
	*/
	public JIRAProject fetchByKey(java.lang.String key);

	/**
	* Returns the j i r a project where key = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param key the key
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching j i r a project, or <code>null</code> if a matching j i r a project could not be found
	*/
	public JIRAProject fetchByKey(java.lang.String key,
		boolean retrieveFromCache);

	/**
	* Removes the j i r a project where key = &#63; from the database.
	*
	* @param key the key
	* @return the j i r a project that was removed
	*/
	public JIRAProject removeByKey(java.lang.String key)
		throws com.liferay.socialcoding.NoSuchJIRAProjectException;

	/**
	* Returns the number of j i r a projects where key = &#63;.
	*
	* @param key the key
	* @return the number of matching j i r a projects
	*/
	public int countByKey(java.lang.String key);

	/**
	* Caches the j i r a project in the entity cache if it is enabled.
	*
	* @param jiraProject the j i r a project
	*/
	public void cacheResult(JIRAProject jiraProject);

	/**
	* Caches the j i r a projects in the entity cache if it is enabled.
	*
	* @param jiraProjects the j i r a projects
	*/
	public void cacheResult(java.util.List<JIRAProject> jiraProjects);

	/**
	* Creates a new j i r a project with the primary key. Does not add the j i r a project to the database.
	*
	* @param jiraProjectId the primary key for the new j i r a project
	* @return the new j i r a project
	*/
	public JIRAProject create(long jiraProjectId);

	/**
	* Removes the j i r a project with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param jiraProjectId the primary key of the j i r a project
	* @return the j i r a project that was removed
	* @throws NoSuchJIRAProjectException if a j i r a project with the primary key could not be found
	*/
	public JIRAProject remove(long jiraProjectId)
		throws com.liferay.socialcoding.NoSuchJIRAProjectException;

	public JIRAProject updateImpl(JIRAProject jiraProject);

	/**
	* Returns the j i r a project with the primary key or throws a {@link NoSuchJIRAProjectException} if it could not be found.
	*
	* @param jiraProjectId the primary key of the j i r a project
	* @return the j i r a project
	* @throws NoSuchJIRAProjectException if a j i r a project with the primary key could not be found
	*/
	public JIRAProject findByPrimaryKey(long jiraProjectId)
		throws com.liferay.socialcoding.NoSuchJIRAProjectException;

	/**
	* Returns the j i r a project with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param jiraProjectId the primary key of the j i r a project
	* @return the j i r a project, or <code>null</code> if a j i r a project with the primary key could not be found
	*/
	public JIRAProject fetchByPrimaryKey(long jiraProjectId);

	@Override
	public java.util.Map<java.io.Serializable, JIRAProject> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the j i r a projects.
	*
	* @return the j i r a projects
	*/
	public java.util.List<JIRAProject> findAll();

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
	public java.util.List<JIRAProject> findAll(int start, int end);

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
	public java.util.List<JIRAProject> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAProject> orderByComparator);

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of j i r a projects
	*/
	public java.util.List<JIRAProject> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<JIRAProject> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the j i r a projects from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of j i r a projects.
	*
	* @return the number of j i r a projects
	*/
	public int countAll();

	@Override
	public java.util.Set<java.lang.String> getBadColumnNames();
}