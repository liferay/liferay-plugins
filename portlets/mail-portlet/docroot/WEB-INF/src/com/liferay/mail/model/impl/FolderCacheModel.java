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

package com.liferay.mail.model.impl;

import com.liferay.mail.model.Folder;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Folder in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Folder
 * @generated
 */
public class FolderCacheModel implements CacheModel<Folder>, Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(21);

		sb.append("{folderId=");
		sb.append(folderId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", userId=");
		sb.append(userId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", accountId=");
		sb.append(accountId);
		sb.append(", fullName=");
		sb.append(fullName);
		sb.append(", displayName=");
		sb.append(displayName);
		sb.append(", remoteMessageCount=");
		sb.append(remoteMessageCount);
		sb.append("}");

		return sb.toString();
	}

	public Folder toEntityModel() {
		FolderImpl folderImpl = new FolderImpl();

		folderImpl.setFolderId(folderId);
		folderImpl.setCompanyId(companyId);
		folderImpl.setUserId(userId);

		if (userName == null) {
			folderImpl.setUserName(StringPool.BLANK);
		}
		else {
			folderImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			folderImpl.setCreateDate(null);
		}
		else {
			folderImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			folderImpl.setModifiedDate(null);
		}
		else {
			folderImpl.setModifiedDate(new Date(modifiedDate));
		}

		folderImpl.setAccountId(accountId);

		if (fullName == null) {
			folderImpl.setFullName(StringPool.BLANK);
		}
		else {
			folderImpl.setFullName(fullName);
		}

		if (displayName == null) {
			folderImpl.setDisplayName(StringPool.BLANK);
		}
		else {
			folderImpl.setDisplayName(displayName);
		}

		folderImpl.setRemoteMessageCount(remoteMessageCount);

		folderImpl.resetOriginalValues();

		return folderImpl;
	}

	public long folderId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long accountId;
	public String fullName;
	public String displayName;
	public int remoteMessageCount;
}