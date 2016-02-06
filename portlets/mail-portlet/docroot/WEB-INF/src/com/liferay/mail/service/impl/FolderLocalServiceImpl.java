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

package com.liferay.mail.service.impl;

import com.liferay.mail.model.Folder;
import com.liferay.mail.service.base.FolderLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;

import java.util.Date;
import java.util.List;

/**
 * @author Scott Lee
 */
public class FolderLocalServiceImpl extends FolderLocalServiceBaseImpl {

	public Folder addFolder(
			long userId, long accountId, String fullName, String displayName,
			int remoteMessageCount)
		throws PortalException {

		User user = userPersistence.findByPrimaryKey(userId);
		Date now = new Date();

		long folderId = counterLocalService.increment();

		Folder folder = folderPersistence.create(folderId);

		folder.setCompanyId(user.getCompanyId());
		folder.setUserId(user.getUserId());
		folder.setUserName(user.getFullName());
		folder.setCreateDate(now);
		folder.setModifiedDate(now);
		folder.setAccountId(accountId);
		folder.setFullName(fullName);
		folder.setDisplayName(displayName);
		folder.setRemoteMessageCount(remoteMessageCount);

		folderPersistence.update(folder);

		return folder;
	}

	@Override
	public Folder deleteFolder(Folder folder) throws PortalException {

		// Folder

		folderPersistence.remove(folder);

		// Messages

		messageLocalService.deleteMessages(folder.getFolderId());

		// Indexer

		Indexer<Folder> indexer = IndexerRegistryUtil.getIndexer(Folder.class);

		indexer.delete(folder);

		return folder;
	}

	@Override
	public Folder deleteFolder(long folderId) throws PortalException {
		Folder folder = folderPersistence.findByPrimaryKey(folderId);

		return deleteFolder(folder);
	}

	public void deleteFolders(long accountId) throws PortalException {
		List<Folder> folders = folderPersistence.findByAccountId(accountId);

		for (Folder folder : folders) {
			deleteFolder(folder);
		}
	}

	public Folder getFolder(long accountId, String fullName)
		throws PortalException {

		return folderPersistence.findByA_F(accountId, fullName);
	}

	public List<Folder> getFolders(long accountId) {
		return folderPersistence.findByAccountId(accountId);
	}

	public int getLocalPageCount(long folderId, int messagesPerPage) {
		int localMessageCount = messagePersistence.countByFolderId(folderId);

		return (int)Math.ceil(localMessageCount / (double)messagesPerPage);
	}

	public int getPercentDownloaded(long folderId) throws PortalException {
		Folder folder = folderPersistence.findByPrimaryKey(folderId);

		int remoteMessageCount = folder.getRemoteMessageCount();

		if (remoteMessageCount == 0) {
			return 100;
		}

		int localMessageCount = messagePersistence.countByFolderId(folderId);

		return (int)((localMessageCount / (double)remoteMessageCount) * 100);
	}

	public int getRemotePageCount(long folderId, int messagesPerPage)
		throws PortalException {

		Folder folder = folderPersistence.findByPrimaryKey(folderId);

		int remoteMessageCount = folder.getRemoteMessageCount();

		return (int)Math.ceil(remoteMessageCount / (double)messagesPerPage);
	}

	public Folder updateFolder(
			long folderId, String fullName, String displayName,
			int remoteMessageCount)
		throws PortalException {

		Folder folder = folderPersistence.findByPrimaryKey(folderId);

		folder.setModifiedDate(new Date());
		folder.setFullName(fullName);
		folder.setDisplayName(displayName);
		folder.setRemoteMessageCount(remoteMessageCount);

		folderPersistence.update(folder);

		return folder;
	}

}