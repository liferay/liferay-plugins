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

import com.liferay.socialcoding.model.SVNRevision;

/**
 * The persistence interface for the s v n revision service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.socialcoding.service.persistence.impl.SVNRevisionPersistenceImpl
 * @see SVNRevisionUtil
 * @generated
 */
@ProviderType
public interface SVNRevisionPersistence extends BasePersistence<SVNRevision> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link SVNRevisionUtil} to access the s v n revision persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the s v n revisions where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @return the matching s v n revisions
	*/
	public java.util.List<SVNRevision> findBySVNUserId(
		java.lang.String svnUserId);

	/**
	* Returns a range of all the s v n revisions where svnUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param svnUserId the svn user ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @return the range of matching s v n revisions
	*/
	public java.util.List<SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end);

	/**
	* Returns an ordered range of all the s v n revisions where svnUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param svnUserId the svn user ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s v n revisions
	*/
	public java.util.List<SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator);

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public SVNRevision findBySVNUserId_First(java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	*/
	public SVNRevision fetchBySVNUserId_First(java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator);

	/**
	* Returns the last s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public SVNRevision findBySVNUserId_Last(java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	/**
	* Returns the last s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	*/
	public SVNRevision fetchBySVNUserId_Last(java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator);

	/**
	* Returns the s v n revisions before and after the current s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnRevisionId the primary key of the current s v n revision
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s v n revision
	* @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	*/
	public SVNRevision[] findBySVNUserId_PrevAndNext(long svnRevisionId,
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	/**
	* Removes all the s v n revisions where svnUserId = &#63; from the database.
	*
	* @param svnUserId the svn user ID
	*/
	public void removeBySVNUserId(java.lang.String svnUserId);

	/**
	* Returns the number of s v n revisions where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @return the number of matching s v n revisions
	*/
	public int countBySVNUserId(java.lang.String svnUserId);

	/**
	* Returns all the s v n revisions where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @return the matching s v n revisions
	*/
	public java.util.List<SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId);

	/**
	* Returns a range of all the s v n revisions where svnRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param svnRepositoryId the svn repository ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @return the range of matching s v n revisions
	*/
	public java.util.List<SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end);

	/**
	* Returns an ordered range of all the s v n revisions where svnRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param svnRepositoryId the svn repository ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s v n revisions
	*/
	public java.util.List<SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator);

	/**
	* Returns the first s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public SVNRevision findBySVNRepositoryId_First(long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	/**
	* Returns the first s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	*/
	public SVNRevision fetchBySVNRepositoryId_First(long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator);

	/**
	* Returns the last s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public SVNRevision findBySVNRepositoryId_Last(long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	/**
	* Returns the last s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	*/
	public SVNRevision fetchBySVNRepositoryId_Last(long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator);

	/**
	* Returns the s v n revisions before and after the current s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRevisionId the primary key of the current s v n revision
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s v n revision
	* @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	*/
	public SVNRevision[] findBySVNRepositoryId_PrevAndNext(long svnRevisionId,
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	/**
	* Removes all the s v n revisions where svnRepositoryId = &#63; from the database.
	*
	* @param svnRepositoryId the svn repository ID
	*/
	public void removeBySVNRepositoryId(long svnRepositoryId);

	/**
	* Returns the number of s v n revisions where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @return the number of matching s v n revisions
	*/
	public int countBySVNRepositoryId(long svnRepositoryId);

	/**
	* Returns all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @return the matching s v n revisions
	*/
	public java.util.List<SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId);

	/**
	* Returns a range of all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @return the range of matching s v n revisions
	*/
	public java.util.List<SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end);

	/**
	* Returns an ordered range of all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s v n revisions
	*/
	public java.util.List<SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator);

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public SVNRevision findBySVNU_SVNR_First(java.lang.String svnUserId,
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	*/
	public SVNRevision fetchBySVNU_SVNR_First(java.lang.String svnUserId,
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator);

	/**
	* Returns the last s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public SVNRevision findBySVNU_SVNR_Last(java.lang.String svnUserId,
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	/**
	* Returns the last s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	*/
	public SVNRevision fetchBySVNU_SVNR_Last(java.lang.String svnUserId,
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator);

	/**
	* Returns the s v n revisions before and after the current s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnRevisionId the primary key of the current s v n revision
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s v n revision
	* @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	*/
	public SVNRevision[] findBySVNU_SVNR_PrevAndNext(long svnRevisionId,
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	/**
	* Removes all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63; from the database.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	*/
	public void removeBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId);

	/**
	* Returns the number of s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @return the number of matching s v n revisions
	*/
	public int countBySVNU_SVNR(java.lang.String svnUserId, long svnRepositoryId);

	/**
	* Caches the s v n revision in the entity cache if it is enabled.
	*
	* @param svnRevision the s v n revision
	*/
	public void cacheResult(SVNRevision svnRevision);

	/**
	* Caches the s v n revisions in the entity cache if it is enabled.
	*
	* @param svnRevisions the s v n revisions
	*/
	public void cacheResult(java.util.List<SVNRevision> svnRevisions);

	/**
	* Creates a new s v n revision with the primary key. Does not add the s v n revision to the database.
	*
	* @param svnRevisionId the primary key for the new s v n revision
	* @return the new s v n revision
	*/
	public SVNRevision create(long svnRevisionId);

	/**
	* Removes the s v n revision with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision that was removed
	* @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	*/
	public SVNRevision remove(long svnRevisionId)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	public SVNRevision updateImpl(SVNRevision svnRevision);

	/**
	* Returns the s v n revision with the primary key or throws a {@link NoSuchSVNRevisionException} if it could not be found.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision
	* @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	*/
	public SVNRevision findByPrimaryKey(long svnRevisionId)
		throws com.liferay.socialcoding.NoSuchSVNRevisionException;

	/**
	* Returns the s v n revision with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision, or <code>null</code> if a s v n revision with the primary key could not be found
	*/
	public SVNRevision fetchByPrimaryKey(long svnRevisionId);

	@Override
	public java.util.Map<java.io.Serializable, SVNRevision> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the s v n revisions.
	*
	* @return the s v n revisions
	*/
	public java.util.List<SVNRevision> findAll();

	/**
	* Returns a range of all the s v n revisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @return the range of s v n revisions
	*/
	public java.util.List<SVNRevision> findAll(int start, int end);

	/**
	* Returns an ordered range of all the s v n revisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SVNRevisionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of s v n revisions
	*/
	public java.util.List<SVNRevision> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<SVNRevision> orderByComparator);

	/**
	* Removes all the s v n revisions from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of s v n revisions.
	*
	* @return the number of s v n revisions
	*/
	public int countAll();
}