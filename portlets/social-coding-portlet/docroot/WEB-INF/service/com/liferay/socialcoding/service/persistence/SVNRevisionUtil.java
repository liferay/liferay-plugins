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

package com.liferay.socialcoding.service.persistence;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import com.liferay.socialcoding.model.SVNRevision;

import java.util.List;

/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       SVNRevisionPersistence
 * @see       SVNRevisionPersistenceImpl
 * @generated
 */
public class SVNRevisionUtil {
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
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static SVNRevision remove(SVNRevision svnRevision)
		throws SystemException {
		return getPersistence().remove(svnRevision);
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

	public static void cacheResult(
		com.liferay.socialcoding.model.SVNRevision svnRevision) {
		getPersistence().cacheResult(svnRevision);
	}

	public static void cacheResult(
		java.util.List<com.liferay.socialcoding.model.SVNRevision> svnRevisions) {
		getPersistence().cacheResult(svnRevisions);
	}

	public static com.liferay.socialcoding.model.SVNRevision create(
		long svnRevisionId) {
		return getPersistence().create(svnRevisionId);
	}

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

	public static com.liferay.socialcoding.model.SVNRevision findByPrimaryKey(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence().findByPrimaryKey(svnRevisionId);
	}

	public static com.liferay.socialcoding.model.SVNRevision fetchByPrimaryKey(
		long svnRevisionId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(svnRevisionId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySVNUserId(svnUserId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySVNUserId(svnUserId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNUserId(
		java.lang.String svnUserId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySVNUserId(svnUserId, start, end, orderByComparator);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNUserId_First(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_First(svnUserId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNUserId_Last(
		java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_Last(svnUserId, orderByComparator);
	}

	public static com.liferay.socialcoding.model.SVNRevision[] findBySVNUserId_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNUserId_PrevAndNext(svnRevisionId, svnUserId,
			orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySVNRepositoryId(svnRepositoryId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNRepositoryId(
		long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySVNRepositoryId(svnRepositoryId, start, end,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNRepositoryId_First(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNRepositoryId_First(svnRepositoryId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNRepositoryId_Last(
		long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNRepositoryId_Last(svnRepositoryId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.SVNRevision[] findBySVNRepositoryId_PrevAndNext(
		long svnRevisionId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNRepositoryId_PrevAndNext(svnRevisionId,
			svnRepositoryId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findBySVNU_SVNR(
		java.lang.String svnUserId, long svnRepositoryId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findBySVNU_SVNR(svnUserId, svnRepositoryId, start, end,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNU_SVNR_First(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNU_SVNR_First(svnUserId, svnRepositoryId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.SVNRevision findBySVNU_SVNR_Last(
		java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNU_SVNR_Last(svnUserId, svnRepositoryId,
			orderByComparator);
	}

	public static com.liferay.socialcoding.model.SVNRevision[] findBySVNU_SVNR_PrevAndNext(
		long svnRevisionId, java.lang.String svnUserId, long svnRepositoryId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException,
			com.liferay.socialcoding.NoSuchSVNRevisionException {
		return getPersistence()
				   .findBySVNU_SVNR_PrevAndNext(svnRevisionId, svnUserId,
			svnRepositoryId, orderByComparator);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.socialcoding.model.SVNRevision> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySVNUserId(svnUserId);
	}

	public static void removeBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySVNRepositoryId(svnRepositoryId);
	}

	public static void removeBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countBySVNUserId(java.lang.String svnUserId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySVNUserId(svnUserId);
	}

	public static int countBySVNRepositoryId(long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySVNRepositoryId(svnRepositoryId);
	}

	public static int countBySVNU_SVNR(java.lang.String svnUserId,
		long svnRepositoryId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countBySVNU_SVNR(svnUserId, svnRepositoryId);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static SVNRevisionPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (SVNRevisionPersistence)PortletBeanLocatorUtil.locate(com.liferay.socialcoding.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					SVNRevisionPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(SVNRevisionPersistence persistence) {
		_persistence = persistence;
	}

	private static SVNRevisionPersistence _persistence;
}