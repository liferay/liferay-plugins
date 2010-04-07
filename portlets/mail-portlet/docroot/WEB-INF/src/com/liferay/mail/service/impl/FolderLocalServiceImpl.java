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

package com.liferay.mail.service.impl;

import com.liferay.mail.model.Folder;
import com.liferay.mail.service.base.FolderLocalServiceBaseImpl;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.User;

import java.util.Date;
import java.util.List;

/**
 * <a href="FolderLocalServiceImpl.java.html"><b><i>View Source</i></b></a>
 *
 * @author Scott Lee
 */
public class FolderLocalServiceImpl extends FolderLocalServiceBaseImpl {

	public Folder addFolder(
			long userId, long accountId, String fullName, String displayName,
			int remoteMessageCount)
		throws PortalException, SystemException {

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

		folderPersistence.update(folder, false);

		return folder;
	}

	public Folder getFolder(long accountId, String fullName)
		throws PortalException, SystemException {

		return folderPersistence.findByA_F(accountId, fullName);
	}

	public List<Folder> getFolders(long accountId) throws SystemException {
		return folderPersistence.findByAccountId(accountId);
	}

	public Folder updateFolder(
			long folderId, String fullName, String displayName,
			int remoteMessageCount)
		throws PortalException, SystemException {

		Folder folder = folderPersistence.findByPrimaryKey(folderId);

		folder.setModifiedDate(new Date());
		folder.setFullName(fullName);
		folder.setDisplayName(displayName);
		folder.setRemoteMessageCount(remoteMessageCount);

		folderPersistence.update(folder, false);

		return folder;
	}

}