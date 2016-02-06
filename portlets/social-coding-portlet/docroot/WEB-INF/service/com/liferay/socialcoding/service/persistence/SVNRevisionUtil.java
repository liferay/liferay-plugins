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

import com.liferay.socialcoding.model.SVNRevision;

import java.util.List;

/**
 * The persistence utility for the s v n revision service. This utility wraps {@link com.liferay.socialcoding.service.persistence.impl.SVNRevisionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SVNRevisionPersistence
 * @see com.liferay.socialcoding.service.persistence.impl.SVNRevisionPersistenceImpl
 * @generated
 */
@ProviderType
public class SVNRevisionUtil {
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
	public static void clearCache(SVNRevision svnRevision) {
		getPersistence().clearCache(svnRevision);
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
	public static List<SVNRevision> findWithDynamicQuery(
		DynamicQuery dynamicQuery) {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<SVNRevision> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end) {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<SVNRevision> findWithDynamicQuery(
		DynamicQuery dynamicQuery, int start, int end,
		OrderByComparator<SVNRevision> orderByComparator) {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel)
	 */
	public static SVNRevision update(SVNRevision svnRevision) {
		return getPersistence().update(svnRevision);
	}

	/**
	 * @see com.liferay.portal.kernel.service.persistence.BasePersistence#update(com.liferay.portal.kernel.model.BaseModel, ServiceContext)
	 */
	public static SVNRevision update(SVNRevision svnRevision,
		ServiceContext serviceContext) {
		return getPersistence().update(svnRevision, serviceContext);
	}

	/**
	* Returns all the s v n revisions where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @return the matching s v n revisions
	*/
	public static List<SVNRevision> findBySVNUserId(java.lang.String svnUserId) {
		return getPersistence().findBySVNUserId(svnUserId);
	}

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
	public static List<SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end) {
		return getPersistence().findBySVNUserId(svnUserId, start, end);
	}

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
	public static List<SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end,
		OrderByComparator<SVNRevision> orderByComparator) {
		return getPersistence()
				   .findBySVNUserId(svnUserId, start, end, orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching s v n revisions
	*/
	public static List<SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end,
		OrderByComparator<SVNRevision> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySVNUserId(svnUserId, start, end, orderByComparator,
			retrieveFromCache);
	}

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public static SVNRevision findBySVNUserId_First(
		java.lang.String svnUserId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_First(svnUserId, orderByComparator);
	}

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	*/
	public static SVNRevision fetchBySVNUserId_First(
		java.lang.String svnUserId,
		OrderByComparator<SVNRevision> orderByComparator) {
		return getPersistence()
				   .fetchBySVNUserId_First(svnUserId, orderByComparator);
	}

	/**
	* Returns the last s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public static SVNRevision findBySVNUserId_Last(java.lang.String svnUserId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_Last(svnUserId, orderByComparator);
	}

	/**
	* Returns the last s v n revision in the ordered set where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching s v n revision, or <code>null</code> if a matching s v n revision could not be found
	*/
	public static SVNRevision fetchBySVNUserId_Last(
		java.lang.String svnUserId,
		OrderByComparator<SVNRevision> orderByComparator) {
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
	* @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	*/
	public static SVNRevision[] findBySVNUserId_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_PrevAndNext(svnRevisionId, svnUserId,
			orderByComparator);
	}

	/**
	* Removes all the s v n revisions where svnUserId = &#63; from the database.
	*
	* @param svnUserId the svn user ID
	*/
	public static void removeBySVNUserId(java.lang.String svnUserId) {
		getPersistence().removeBySVNUserId(svnUserId);
	}

	/**
	* Returns the number of s v n revisions where svnUserId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @return the number of matching s v n revisions
	*/
	public static int countBySVNUserId(java.lang.String svnUserId) {
		return getPersistence().countBySVNUserId(svnUserId);
	}

	/**
	* Returns all the s v n revisions where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @return the matching s v n revisions
	*/
	public static List<SVNRevision> findBySVNRepositoryId(long svnRepositoryId) {
		return getPersistence().findBySVNRepositoryId(svnRepositoryId);
	}

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
	public static List<SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end) {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, start, end);
	}

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
	public static List<SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end,
		OrderByComparator<SVNRevision> orderByComparator) {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, start, end,
			orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching s v n revisions
	*/
	public static List<SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end,
		OrderByComparator<SVNRevision> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first s v n revision in the ordered set where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public static SVNRevision findBySVNRepositoryId_First(
		long svnRepositoryId, OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
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
	*/
	public static SVNRevision fetchBySVNRepositoryId_First(
		long svnRepositoryId, OrderByComparator<SVNRevision> orderByComparator) {
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
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public static SVNRevision findBySVNRepositoryId_Last(long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
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
	*/
	public static SVNRevision fetchBySVNRepositoryId_Last(
		long svnRepositoryId, OrderByComparator<SVNRevision> orderByComparator) {
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
	* @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	*/
	public static SVNRevision[] findBySVNRepositoryId_PrevAndNext(
		long svnRevisionId, long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNRepositoryId_PrevAndNext(svnRevisionId,
			svnRepositoryId, orderByComparator);
	}

	/**
	* Removes all the s v n revisions where svnRepositoryId = &#63; from the database.
	*
	* @param svnRepositoryId the svn repository ID
	*/
	public static void removeBySVNRepositoryId(long svnRepositoryId) {
		getPersistence().removeBySVNRepositoryId(svnRepositoryId);
	}

	/**
	* Returns the number of s v n revisions where svnRepositoryId = &#63;.
	*
	* @param svnRepositoryId the svn repository ID
	* @return the number of matching s v n revisions
	*/
	public static int countBySVNRepositoryId(long svnRepositoryId) {
		return getPersistence().countBySVNRepositoryId(svnRepositoryId);
	}

	/**
	* Returns all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @return the matching s v n revisions
	*/
	public static List<SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId) {
		return getPersistence().findBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

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
	public static List<SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end) {
		return getPersistence()
				   .findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end);
	}

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
	public static List<SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end,
		OrderByComparator<SVNRevision> orderByComparator) {
		return getPersistence()
				   .findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end,
			orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching s v n revisions
	*/
	public static List<SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end,
		OrderByComparator<SVNRevision> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end,
			orderByComparator, retrieveFromCache);
	}

	/**
	* Returns the first s v n revision in the ordered set where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching s v n revision
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public static SVNRevision findBySVNU_SVNR_First(
		java.lang.String svnUserId, long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
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
	*/
	public static SVNRevision fetchBySVNU_SVNR_First(
		java.lang.String svnUserId, long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator) {
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
	* @throws NoSuchSVNRevisionException if a matching s v n revision could not be found
	*/
	public static SVNRevision findBySVNU_SVNR_Last(java.lang.String svnUserId,
		long svnRepositoryId, OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
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
	*/
	public static SVNRevision fetchBySVNU_SVNR_Last(
		java.lang.String svnUserId, long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator) {
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
	* @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	*/
	public static SVNRevision[] findBySVNU_SVNR_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId, long svnRepositoryId,
		OrderByComparator<SVNRevision> orderByComparator)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNU_SVNR_PrevAndNext(svnRevisionId, svnUserId,
			svnRepositoryId, orderByComparator);
	}

	/**
	* Removes all the s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63; from the database.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	*/
	public static void removeBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId) {
		getPersistence().removeBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	/**
	* Returns the number of s v n revisions where svnUserId = &#63; and svnRepositoryId = &#63;.
	*
	* @param svnUserId the svn user ID
	* @param svnRepositoryId the svn repository ID
	* @return the number of matching s v n revisions
	*/
	public static int countBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId) {
		return getPersistence().countBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	/**
	* Caches the s v n revision in the entity cache if it is enabled.
	*
	* @param svnRevision the s v n revision
	*/
	public static void cacheResult(SVNRevision svnRevision) {
		getPersistence().cacheResult(svnRevision);
	}

	/**
	* Caches the s v n revisions in the entity cache if it is enabled.
	*
	* @param svnRevisions the s v n revisions
	*/
	public static void cacheResult(List<SVNRevision> svnRevisions) {
		getPersistence().cacheResult(svnRevisions);
	}

	/**
	* Creates a new s v n revision with the primary key. Does not add the s v n revision to the database.
	*
	* @param svnRevisionId the primary key for the new s v n revision
	* @return the new s v n revision
	*/
	public static SVNRevision create(long svnRevisionId) {
		return getPersistence().create(svnRevisionId);
	}

	/**
	* Removes the s v n revision with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision that was removed
	* @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	*/
	public static SVNRevision remove(long svnRevisionId)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
		return getPersistence().remove(svnRevisionId);
	}

	public static SVNRevision updateImpl(SVNRevision svnRevision) {
		return getPersistence().updateImpl(svnRevision);
	}

	/**
	* Returns the s v n revision with the primary key or throws a {@link NoSuchSVNRevisionException} if it could not be found.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision
	* @throws NoSuchSVNRevisionException if a s v n revision with the primary key could not be found
	*/
	public static SVNRevision findByPrimaryKey(long svnRevisionId)
		throws com.liferay.socialcoding.exception.NoSuchSVNRevisionException {
		return getPersistence().findByPrimaryKey(svnRevisionId);
	}

	/**
	* Returns the s v n revision with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param svnRevisionId the primary key of the s v n revision
	* @return the s v n revision, or <code>null</code> if a s v n revision with the primary key could not be found
	*/
	public static SVNRevision fetchByPrimaryKey(long svnRevisionId) {
		return getPersistence().fetchByPrimaryKey(svnRevisionId);
	}

	public static java.util.Map<java.io.Serializable, SVNRevision> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys) {
		return getPersistence().fetchByPrimaryKeys(primaryKeys);
	}

	/**
	* Returns all the s v n revisions.
	*
	* @return the s v n revisions
	*/
	public static List<SVNRevision> findAll() {
		return getPersistence().findAll();
	}

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
	public static List<SVNRevision> findAll(int start, int end) {
		return getPersistence().findAll(start, end);
	}

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
	public static List<SVNRevision> findAll(int start, int end,
		OrderByComparator<SVNRevision> orderByComparator) {
		return getPersistence().findAll(start, end, orderByComparator);
	}

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
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of s v n revisions
	*/
	public static List<SVNRevision> findAll(int start, int end,
		OrderByComparator<SVNRevision> orderByComparator,
		boolean retrieveFromCache) {
		return getPersistence()
				   .findAll(start, end, orderByComparator, retrieveFromCache);
	}

	/**
	* Removes all the s v n revisions from the database.
	*/
	public static void removeAll() {
		getPersistence().removeAll();
	}

	/**
	* Returns the number of s v n revisions.
	*
	* @return the number of s v n revisions
	*/
	public static int countAll() {
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

	private static SVNRevisionPersistence _persistence;
}