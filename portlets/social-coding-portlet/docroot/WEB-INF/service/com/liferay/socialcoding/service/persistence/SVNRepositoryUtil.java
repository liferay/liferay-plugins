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
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;

import com.liferay.socialcoding.model.SVNRepository;

import java.util.List;

/**
 * The persistence utility for the s v n repository service. This utility wraps {@link com.liferay.socialcoding.service.persistence.impl.SVNRepositoryPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SVNRepositoryPersistence
 * @see com.liferay.socialcoding.service.persistence.impl.SVNRepositoryPersistenceImpl
 * @generated
 */
@ProviderType
public class SVNRepositoryUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#clearCache(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static void clearCache(SVNRepository svnRepository) {
		getPersistence().clearCache(svnRepository);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
	 */
	public static long countWithDynamicQuery(DynamicQuery dynamicQuery) {
		return getPersistence().countWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
	 */
	public static List<SVNRepository> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SVNRepository> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SVNRepository> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SVNRepository> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SVNRepository update(SVNRepository svnRepository) {
		return getPersistence().update(svnRepository);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SVNRepository update(SVNRepository svnRepository,
		ServiceContext serviceContext) {
		return getPersistence().update(svnRepository, serviceContext);
	}

	/**
	* Returns the s v n repository where url = &#63; or throws a {@link NoSuchSVNRepositoryException} if it could not be found.
	*
	* @param url the url
	* @return the matching s v n repository
	* @throws NoSuchSVNRepositoryException if a matching s v n repository could not be found
	*/
	public static SVNRepository findByUrl(java.lang.String url)
		throws com.liferay.socialcoding.exception.NoSuchSVNRepositoryException {
		return getPersistence().findByUrl(url);
	}

	/**
	* Returns the s v n repository where url = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param url the url
	* @return the matching s v n repository, or <code>null</code> if a matching s v n repository could not be found
	*/
	public static SVNRepository fetchByUrl(java.lang.String url) {
		return getPersistence().fetchByUrl(url);
	}

	/**
	* Returns the s v n repository where url = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param url the url
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the matching s v n repository, or <code>null</code> if a matching s v n repository could not be found
	*/
	public static SVNRepository fetchByUrl(java.lang.String url,
		boolean retrieveFromCache) {
		return getPersistence().fetchByUrl(url, retrieveFromCache);
	}

	/**
	* Removes the s v n repository where url = &#63; from the database.
	*
	* @param url the url
	* @return the s v n repository that was removed
	*/
	public static SVNRepository removeByUrl(java.lang.String url)
		throws com.liferay.socialcoding.exception.NoSuchSVNRepositoryException {
		return getPersistence().removeByUrl(url);
	}

	/**
	* Returns the number of s v n repositories where url = &#63;.
	*
	* @param url the url
	* @return the number of matching s v n repositories
	*/
	public static int countByUrl(java.lang.String url) {
		return getPersistence().countByUrl(url);
	}

	/**
	* Caches the s v n repository in the entity cache if it is enabled.
	*
	* @param svnRepository the s v n repository
	*/
	public static void cacheResult(SVNRepository svnRepository) {
		getPersistence().cacheResult(svnRepository);
	}

	/**
	* Caches the s v n repositories in the entity cache if it is enabled.
	*
	* @param svnRepositories the s v n repositories
	*/
	public static void cacheResult(List<SVNRepository> svnRepositories) {
		getPersistence().cacheResult(svnRepositories);
	}

	/**
	* Creates a new s v n repository with the primary key. Does not add the s v n repository to the database.
	*
	* @param svnRepositoryId the primary key for the new s v n repository
	* @return the new s v n repository
	*/
	public static SVNRepository create(long svnRepositoryId) {
		return getPersistence().create(svnRepositoryId);
	}

	/**
	* Removes the s v n repository with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository that was removed
	* @throws NoSuchSVNRepositoryException if a s v n repository with the primary key could not be found
	*/
	public static SVNRepository remove(long svnRepositoryId)
		throws com.liferay.socialcoding.exception.NoSuchSVNRepositoryException {
		return getPersistence().remove(svnRepositoryId);
	}

	public static SVNRepository updateImpl(SVNRepository svnRepository) {
		return getPersistence().updateImpl(svnRepository);
	}

	/**
	* Returns the s v n repository with the primary key or throws a {@link NoSuchSVNRepositoryException} if it could not be found.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository
	* @throws NoSuchSVNRepositoryException if a s v n repository with the primary key could not be found
	*/
	public static SVNRepository findByPrimaryKey(long svnRepositoryId)
		throws com.liferay.socialcoding.exception.NoSuchSVNRepositoryException {
		return getPersistence().findByPrimaryKey(svnRepositoryId);
	}

	/**
	* Returns the s v n repository with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository, or <code>null</code> if a s v n repository with the primary key could not be found
	*/
	public static SVNRepository fetchByPrimaryKey(long svnRepositoryId) {
		return getPersistence().fetchByPrimaryKey(svnRepositoryId);
	}

	public static java.util.Map<java.io.Serializable, SVNRepository> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the s v n repositories.
	*
	* @return the s v n repositories
	*/
	public static List<SVNRepository> findAll() {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the s v n repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s v n repositories
	* @param end the upper bound of the range of s v n repositories (not inclusive)
	* @return the range of s v n repositories
	*/
	public static List<SVNRepository> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the s v n repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s v n repositories
	* @param end the upper bound of the range of s v n repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of s v n repositories
	*/
	public static List<SVNRepository> findAll(int start, int end,
		OrderByComparator<SVNRepository> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Returns an ordered range of all the s v n repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRepositoryModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s v n repositories
	* @param end the upper bound of the range of s v n repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of s v n repositories
	*/
	public static List<SVNRepository> findAll(int start, int end,
		OrderByComparator<SVNRepository> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the s v n repositories from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of s v n repositories.
	*
	* @return the number of s v n repositories
	*/
	public static int countAll() {
		return getPersistence().countAll();
	}

	public static SVNRepositoryPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SVNRepositoryPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.getServletContextName(),
					SVNRepositoryPersistence.class.getName());

			ReferenceRegistry.registerReference(SVNRepositoryUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	private static SVNRepositoryPersistence _persistence;
}