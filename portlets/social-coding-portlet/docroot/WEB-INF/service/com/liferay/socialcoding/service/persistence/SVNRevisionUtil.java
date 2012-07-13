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

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialcoding.model.SVNRevision;

import java.util.List;

/**
 * The persistence utility for the s v n revision service. This utility wraps {@link SVNRevisionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SVNRevisionPersistence
 * @see SVNRevisionPersistenceImpl
 * @generated
 */
public class SVNRevisionUtil {
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
	public static void clearCache(SVNRevision svnRevision) {
		getPersistence().clearCache(svnRevision);
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
	public static List<SVNRevision> findWithDynamicQuery(
		DynamicQuery dynamicQuery) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SVNRevision> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SVNRevision> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator orderByComparator) throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static SVNRevision update(SVNRevision svnRevision, boolean merge)
		throws SystemException {
		return getPersistence().update(svnRevision, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static SVNRevision update(SVNRevision svnRevision, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(svnRevision, merge, serviceContext);
	}

	/**
	* Caches the s v n revision in the entity cache if it is enabled.
	*
	* @param svnRevision the s v n revision
	*/
	public static void cacheResult(
		com.liferay.socialcoding.model.SVNRevision svnRevision) {
		getPersistence().cacheResult(svnRevision);
	}

	/**
	* Caches the s v n revisions in the entity cache if it is enabled.
	*
	* @param svnRevisions the s v n revisions
	*/
	public static void cacheResult(
		java.util.List<com.liferay.socialcoding.model.SVNRevision> svnRevisions) {
		getPersistence().cacheResult(svnRevisions);
	}

	/**
	* Creates a new s v n revision with the primary key. Does not add the s v n revision to the database.
	*
	* @param svnRevisionId the primary key for the new s v n revision
	* @return the new s v n revision
	*/
	public static com.liferay.socialcoding.model.SVNRevision create(
		long svnRevisionId) {
		return getPersistence().create(svnRevisionId);
	}

	/**
	* Removes the s v n revision with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision that was removed
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision remove(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence().remove(svnRevisionId);
	}

	public static com.liferay.socialcoding.model.SVNRevision updateImpl(
		com.liferay.socialcoding.model.SVNRevision svnRevision, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(svnRevision, merge);
	}

	/**
	* Returns the s v n revision with the primary key or throws a {@link com.liferay.socialcoding.NoSuchSVNRevisionException} if it could not be found.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision findByPrimaryKey(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence().findByPrimaryKey(svnRevisionId);
	}

	/**
	* Returns the s v n revision with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision, or <code>null</code> if a s v n revision with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision fetchByPrimaryKey(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(svnRevisionId);
	}

	/**
	* Returns all the s v n revisions where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @return the matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySVNUserId(svnUserId);
	}

	/**
	* Returns a range of all the s v n revisions where svnUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param svnUserId the svn user ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @return the range of matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySVNUserId(svnUserId, start, end);
	}

	/**
	* Returns an ordered range of all the s v n revisions where svnUserId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param svnUserId the svn user ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySVNUserId(svnUserId, start, end, orderByComparator);
	}

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision findBySVNUserId_First(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_First(svnUserId, orderByComparator);
	}

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision fetchBySVNUserId_First(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySVNUserId_First(svnUserId, orderByComparator);
	}

	/**
	* Returns the last s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision findBySVNUserId_Last(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_Last(svnUserId, orderByComparator);
	}

	/**
	* Returns the last s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision fetchBySVNUserId_Last(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySVNUserId_Last(svnUserId, orderByComparator);
	}

	/**
	* Returns the s v n revisions before and after the current s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnRevisionId the primary key of the current s v n revision
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s v n revision
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision[] findBySVNUserId_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_PrevAndNext(svnRevisionId, svnUserId,
			orderByComparator);
	}

	/**
	* Returns all the s v n revisions where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @return the matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySVNRepositoryId(svnRepositoryId);
	}

	/**
	* Returns a range of all the s v n revisions where svnRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param svnRepositoryId the svn repository ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @return the range of matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, start, end);
	}

	/**
	* Returns an ordered range of all the s v n revisions where svnRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param svnRepositoryId the svn repository ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision findBySVNRepositoryId_First(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNRepositoryId_First(svnRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the first s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision fetchBySVNRepositoryId_First(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySVNRepositoryId_First(svnRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the last s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision findBySVNRepositoryId_Last(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNRepositoryId_Last(svnRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the last s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision fetchBySVNRepositoryId_Last(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySVNRepositoryId_Last(svnRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the s v n revisions before and after the current s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRevisionId the primary key of the current s v n revision
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s v n revision
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision[] findBySVNRepositoryId_PrevAndNext(
		long svnRevisionId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNRepositoryId_PrevAndNext(svnRevisionId,
			svnRepositoryId, orderByComparator);
	}

	/**
	* Returns all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @return the matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	/**
	* Returns a range of all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @return the range of matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end);
	}

	/**
	* Returns an ordered range of all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end,
			orderByComparator);
	}

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision findBySVNU_SVNR_First(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNU_SVNR_First(svnUserId, svnRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision fetchBySVNU_SVNR_First(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySVNU_SVNR_First(svnUserId, svnRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the last s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision findBySVNU_SVNR_Last(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNU_SVNR_Last(svnUserId, svnRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the last s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision fetchBySVNU_SVNR_Last(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchBySVNU_SVNR_Last(svnUserId, svnRepositoryId,
			orderByComparator);
	}

	/**
	* Returns the s v n revisions before and after the current s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnRevisionId the primary key of the current s v n revision
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next s v n revision
	* @throws com.liferay.socialcoding.NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.socialcoding.model.SVNRevision[] findBySVNU_SVNR_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNU_SVNR_PrevAndNext(svnRevisionId, svnUserId,
			svnRepositoryId, orderByComparator);
	}

	/**
	* Returns all the s v n revisions.
	*
	* @return the s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	/**
	* Returns a range of all the s v n revisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @return the range of s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	/**
	* Returns an ordered range of all the s v n revisions.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of s v n revisions
	* @param end the upper bound of the range of s v n revisions (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	/**
	* Removes all the s v n revisions where svnUserId = &#63; from the database.
	*
	* @param svnUserId the svn user ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySVNUserId(svnUserId);
	}

	/**
	* Removes all the s v n revisions where svnRepositoryId = &#63; from the database.
	*
	* @param svnRepositoryId the svn repository ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySVNRepositoryId(svnRepositoryId);
	}

	/**
	* Removes all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63; from the database.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @throws SystemException if a system exception occurred
	*/
	public static void removeBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	/**
	* Removes all the s v n revisions from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of s v n revisions where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @return the number of matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySVNUserId(svnUserId);
	}

	/**
	* Returns the number of s v n revisions where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @return the number of matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySVNRepositoryId(svnRepositoryId);
	}

	/**
	* Returns the number of s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @return the number of matching s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static int countBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	/**
	* Returns the number of s v n revisions.
	*
	* @return the number of s v n revisions
	* @throws SystemException if a system exception occurred
	*/
	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SVNRevisionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SVNRevisionPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.getServletContextName(),
					SVNRevisionPersistence.class.getName());

			ReferenceRegistry.registerReference(SVNRevisionUtil.class,
				"_persistence");
		}

		return _persistence;
	}

	/**
	 * @deprecated
	 */
	public void setPersistence(SVNRevisionPersistence persistence) {
	}

	private static SVNRevisionPersistence _persistence;
}