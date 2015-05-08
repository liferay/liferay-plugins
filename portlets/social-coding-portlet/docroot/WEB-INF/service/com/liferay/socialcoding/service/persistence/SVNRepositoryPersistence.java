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

import com.liferay.socialcoding.model.SVNRepository;

/**
 * The persistence interface for the s v n repository service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.socialcoding.service.persistence.impl.SVNRepositoryPersistenceImpl
 * @see SVNRepositoryUtil
 * @generated
 */
@ProviderType
public interface SVNRepositoryPersistence extends BasePersistence<SVNRepository> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SVNRepositoryUtil} to access the s v n repository persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns the s v n repository where url = &#63; or throws a {@link NoSuchSVNRepositoryException} if it could not be found.
	*
	* @param url the url
	* @return the matching s v n repository
	* @throws NoSuchSVNRepositoryException if a matching s v n repository could not be found
	*/
	public SVNRepository findByUrl(java.lang.String url)
		throws com.liferay.socialcoding.NoSuchSVNRepositoryException;

	/**
	* Returns the s v n repository where url = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param url the url
	* @return the matching s v n repository, or <code>null</code> if a matching s v n repository could not be found
	*/
	public SVNRepository fetchByUrl(java.lang.String url);

	/**
	* Returns the s v n repository where url = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param url the url
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching s v n repository, or <code>null</code> if a matching s v n repository could not be found
	*/
	public SVNRepository fetchByUrl(java.lang.String url,
		boolean retrieveFromCache);

	/**
	* Removes the s v n repository where url = &#63; from the database.
	*
	* @param url the url
	* @return the s v n repository that was removed
	*/
	public SVNRepository removeByUrl(java.lang.String url)
		throws com.liferay.socialcoding.NoSuchSVNRepositoryException;

	/**
	* Returns the number of s v n repositories where url = &#63;.
	*
	* @param url the url
	* @return the number of matching s v n repositories
	*/
	public int countByUrl(java.lang.String url);

	/**
	* Caches the s v n repository in the entity cache if it is enabled.
	*
	* @param svnRepository the s v n repository
	*/
	public void cacheResult(SVNRepository svnRepository);

	/**
	* Caches the s v n repositories in the entity cache if it is enabled.
	*
	* @param svnRepositories the s v n repositories
	*/
	public void cacheResult(java.util.List<SVNRepository> svnRepositories);

	/**
	* Creates a new s v n repository with the primary key. Does not add the s v n repository to the database.
	*
	* @param svnRepositoryId the primary key for the new s v n repository
	* @return the new s v n repository
	*/
	public SVNRepository create(long svnRepositoryId);

	/**
	* Removes the s v n repository with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository that was removed
	* @throws NoSuchSVNRepositoryException if a s v n repository with the primary key could not be found
	*/
	public SVNRepository remove(long svnRepositoryId)
		throws com.liferay.socialcoding.NoSuchSVNRepositoryException;

	public SVNRepository updateImpl(SVNRepository svnRepository);

	/**
	* Returns the s v n repository with the primary key or throws a {@link NoSuchSVNRepositoryException} if it could not be found.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository
	* @throws NoSuchSVNRepositoryException if a s v n repository with the primary key could not be found
	*/
	public SVNRepository findByPrimaryKey(long svnRepositoryId)
		throws com.liferay.socialcoding.NoSuchSVNRepositoryException;

	/**
	* Returns the s v n repository with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param svnRepositoryId the primary key of the s v n repository
	* @return the s v n repository, or <code>null</code> if a s v n repository with the primary key could not be found
	*/
	public SVNRepository fetchByPrimaryKey(long svnRepositoryId);

	@Override
	public java.util.Map<java.io.Serializable, SVNRepository> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the s v n repositories.
	*
	* @return the s v n repositories
	*/
	public java.util.List<SVNRepository> findAll();

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
	public java.util.List<SVNRepository> findAll(int start, int end);

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
	public java.util.List<SVNRepository> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRepository> orderByComparator);

	/**
	* Removes all the s v n repositories from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of s v n repositories.
	*
	* @return the number of s v n repositories
	*/
	public int countAll();
}