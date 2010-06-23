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

package com.liferay.mail.service.persistence;

import com.liferay.mail.model.Folder;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.service.ServiceContext;

import java.util.List;

/**
 * <a href="FolderUtil.java.html"><b><i>View Source</i></b></a>
 *
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FolderPersistence
 * @see       FolderPersistenceImpl
 * @generated
 */
public class FolderUtil {
	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache()
	 */
	public static void clearCache() {
		getPersistence().clearCache();
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#clearCache(Folder)
	 */
	public static void clearCache(Folder folder) {
		getPersistence().clearCache(folder);
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
	public static List<Folder> findWithDynamicQuery(DynamicQuery dynamicQuery)
		throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
	 */
	public static List<Folder> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end) throws SystemException {
		return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
	 */
	public static List<Folder> findWithDynamicQuery(DynamicQuery dynamicQuery,
		int start, int end, OrderByComparator orderByComparator)
		throws SystemException {
		return getPersistence()
				   .findWithDynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#remove(com.liferay.portal.model.BaseModel)
	 */
	public static Folder remove(Folder folder) throws SystemException {
		return getPersistence().remove(folder);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean)
	 */
	public static Folder update(Folder folder, boolean merge)
		throws SystemException {
		return getPersistence().update(folder, merge);
	}

	/**
	 * @see com.liferay.portal.service.persistence.BasePersistence#update(com.liferay.portal.model.BaseModel, boolean, ServiceContext)
	 */
	public static Folder update(Folder folder, boolean merge,
		ServiceContext serviceContext) throws SystemException {
		return getPersistence().update(folder, merge, serviceContext);
	}

	public static void cacheResult(com.liferay.mail.model.Folder folder) {
		getPersistence().cacheResult(folder);
	}

	public static void cacheResult(
		java.util.List<com.liferay.mail.model.Folder> folders) {
		getPersistence().cacheResult(folders);
	}

	public static com.liferay.mail.model.Folder create(long folderId) {
		return getPersistence().create(folderId);
	}

	public static com.liferay.mail.model.Folder remove(long folderId)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().remove(folderId);
	}

	public static com.liferay.mail.model.Folder updateImpl(
		com.liferay.mail.model.Folder folder, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().updateImpl(folder, merge);
	}

	public static com.liferay.mail.model.Folder findByPrimaryKey(long folderId)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByPrimaryKey(folderId);
	}

	public static com.liferay.mail.model.Folder fetchByPrimaryKey(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByPrimaryKey(folderId);
	}

	public static java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountId(accountId);
	}

	public static java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByAccountId(accountId, start, end);
	}

	public static java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountId(accountId, start, end, orderByComparator);
	}

	public static com.liferay.mail.model.Folder findByAccountId_First(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountId_First(accountId, orderByComparator);
	}

	public static com.liferay.mail.model.Folder findByAccountId_Last(
		long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountId_Last(accountId, orderByComparator);
	}

	public static com.liferay.mail.model.Folder[] findByAccountId_PrevAndNext(
		long folderId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .findByAccountId_PrevAndNext(folderId, accountId,
			orderByComparator);
	}

	public static com.liferay.mail.model.Folder findByA_F(long accountId,
		java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findByA_F(accountId, fullName);
	}

	public static com.liferay.mail.model.Folder fetchByA_F(long accountId,
		java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().fetchByA_F(accountId, fullName);
	}

	public static com.liferay.mail.model.Folder fetchByA_F(long accountId,
		java.lang.String fullName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence()
				   .fetchByA_F(accountId, fullName, retrieveFromCache);
	}

	public static java.util.List<com.liferay.mail.model.Folder> findAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll();
	}

	public static java.util.List<com.liferay.mail.model.Folder> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end);
	}

	public static java.util.List<com.liferay.mail.model.Folder> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().findAll(start, end, orderByComparator);
	}

	public static void removeByAccountId(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByAccountId(accountId);
	}

	public static void removeByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeByA_F(accountId, fullName);
	}

	public static void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		getPersistence().removeAll();
	}

	public static int countByAccountId(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByAccountId(accountId);
	}

	public static int countByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countByA_F(accountId, fullName);
	}

	public static int countAll()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getPersistence().countAll();
	}

	public static FolderPersistence getPersistence() {
		if (_persistence == null) {
			_persistence = (FolderPersistence)PortletBeanLocatorUtil.locate(com.liferay.mail.service.ClpSerializer.SERVLET_CONTEXT_NAME,
					FolderPersistence.class.getName());
		}

		return _persistence;
	}

	public void setPersistence(FolderPersistence persistence) {
		_persistence = persistence;
	}

	private static FolderPersistence _persistence;
}