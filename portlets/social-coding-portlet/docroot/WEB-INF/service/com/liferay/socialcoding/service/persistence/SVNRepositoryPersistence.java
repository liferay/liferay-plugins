/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.service.persistence.BasePersistence;

import com.liferay.socialcoding.model.SVNRepository;

/**
 * The persistence interface for the s v n repository service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SVNRepositoryPersistenceImpl
 * @see SVNRepositoryUtil
 * @generated
 */
public interface SVNRepositoryPersistence extends BasePersistence<SVNRepository> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SVNRepositoryUtil} to access the s v n repository persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the s v n repository in the entity cache if it is enabled.
	*
	* @param svnRepository the s v n repository
	*/
	public void cacheResult(
		com.liferay.socialcoding.model.SVNRepository svnRepository);

	/**
	* Caches the s v n repositories in the entity cache if it is enabled.
	*
	* @param svnRepositories the s v n repositories
	*/
	public void cacheResult(
		java.util.List<com.liferay.socialcoding.model.SVNRepository> svnRepositories);

	/**
	* Creates a new s v n repository with the primary key. Does not add the s v n repository to the database.
	*
	* @param svnRepositoryId the primary key for the new s v n repository
	* @return the new s v n repository
	*/
	public com.liferay.socialcoding.model.SVNRepository create(
		long svnRepositoryId);

	/**
	* Removes the s v n repository with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository that was removed
	* @throws com.liferay.socialcoding.NoSuchSVNRepositoryException if a s v n repository with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.SVNRepository remove(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRepositoryException;

	public com.liferay.socialcoding.model.SVNRepository updateImpl(
		com.liferay.socialcoding.model.SVNRepository svnRepository,
		boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the s v n repository with the primary key or throws a {@link com.liferay.socialcoding.NoSuchSVNRepositoryException} if it could not be found.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository
	* @throws com.liferay.socialcoding.NoSuchSVNRepositoryException if a s v n repository with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.SVNRepository findByPrimaryKey(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRepositoryException;

	/**
	* Returns the s v n repository with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository, or <code>null</code> if a s v n repository with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.SVNRepository fetchByPrimaryKey(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the s v n repository where url = &#63; or throws a {@link com.liferay.socialcoding.NoSuchSVNRepositoryException} if it could not be found.
	*
	* @param url the url
	* @return the matching s v n repository
	* @throws com.liferay.socialcoding.NoSuchSVNRepositoryException if a matching s v n repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.SVNRepository findByUrl(
		java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRepositoryException;

	/**
	* Returns the s v n repository where url = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param url the url
	* @return the matching s v n repository, or <code>null</code> if a matching s v n repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.SVNRepository fetchByUrl(
		java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the s v n repository where url = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param url the url
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching s v n repository, or <code>null</code> if a matching s v n repository could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.socialcoding.model.SVNRepository fetchByUrl(
		java.lang.String url, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the s v n repositories.
	*
	* @return the s v n repositories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.SVNRepository> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the s v n repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s v n repositories
	* @param end the upper bound of the range of s v n repositories (not inclusive)
	* @return the range of s v n repositories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.SVNRepository> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the s v n repositories.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s v n repositories
	* @param end the upper bound of the range of s v n repositories (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of s v n repositories
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.socialcoding.model.SVNRepository> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the s v n repository where url = &#63; from the database.
	*
	* @param url the url
	* @throws SystemException if a system exception occurred
	*/
	public void removeByUrl(java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRepositoryException;

	/**
	* Removes all the s v n repositories from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of s v n repositories where url = &#63;.
	*
	* @param url the url
	* @return the number of matching s v n repositories
	* @throws SystemException if a system exception occurred
	*/
	public int countByUrl(java.lang.String url)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of s v n repositories.
	*
	* @return the number of s v n repositories
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}