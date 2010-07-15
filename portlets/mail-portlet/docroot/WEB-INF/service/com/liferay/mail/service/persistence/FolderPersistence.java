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

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * @author    Brian Wing Shun Chan
 * @see       FolderPersistenceImpl
 * @see       FolderUtil
 * @generated
 */
public interface FolderPersistence extends BasePersistence<Folder> {
	public void cacheResult(com.liferay.mail.model.Folder folder);

	public void cacheResult(
		java.util.List<com.liferay.mail.model.Folder> folders);

	public com.liferay.mail.model.Folder create(long folderId);

	public com.liferay.mail.model.Folder remove(long folderId)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Folder updateImpl(
		com.liferay.mail.model.Folder folder, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Folder findByPrimaryKey(long folderId)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Folder fetchByPrimaryKey(long folderId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Folder> findByAccountId(
		long accountId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Folder findByAccountId_First(long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Folder findByAccountId_Last(long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Folder[] findByAccountId_PrevAndNext(
		long folderId, long accountId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Folder findByA_F(long accountId,
		java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Folder fetchByA_F(long accountId,
		java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.mail.model.Folder fetchByA_F(long accountId,
		java.lang.String fullName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Folder> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Folder> findAll(int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException;

	public java.util.List<com.liferay.mail.model.Folder> findAll(int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByAccountId(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public void removeByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.mail.NoSuchFolderException,
			com.liferay.portal.kernel.exception.SystemException;

	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByAccountId(long accountId)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countByA_F(long accountId, java.lang.String fullName)
		throws com.liferay.portal.kernel.exception.SystemException;

	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}