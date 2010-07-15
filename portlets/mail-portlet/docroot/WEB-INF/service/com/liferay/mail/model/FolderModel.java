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

package com.liferay.mail.model;

import com.liferay.portal.kernel.annotation.AutoEscape;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.service.ServiceContext;

import com.liferay.portlet.expando.model.ExpandoBridge;

import java.io.Serializable;

import java.util.Date;

/**
 * <p>
 * This interface is a model that represents the Mail_Folder table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Folder
 * @see       com.liferay.mail.model.impl.FolderImpl
 * @see       com.liferay.mail.model.impl.FolderModelImpl
 * @generated
 */
public interface FolderModel extends BaseModel<Folder> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getFolderId();

	public void setFolderId(long folderId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public long getUserId();

	public void setUserId(long userId);

	public String getUserUuid() throws SystemException;

	public void setUserUuid(String userUuid);

	@AutoEscape
	public String getUserName();

	public void setUserName(String userName);

	public Date getCreateDate();

	public void setCreateDate(Date createDate);

	public Date getModifiedDate();

	public void setModifiedDate(Date modifiedDate);

	public long getAccountId();

	public void setAccountId(long accountId);

	@AutoEscape
	public String getFullName();

	public void setFullName(String fullName);

	@AutoEscape
	public String getDisplayName();

	public void setDisplayName(String displayName);

	public int getRemoteMessageCount();

	public void setRemoteMessageCount(int remoteMessageCount);

	public Folder toEscapedModel();

	public boolean isNew();

	public void setNew(boolean n);

	public boolean isCachedModel();

	public void setCachedModel(boolean cachedModel);

	public boolean isEscapedModel();

	public void setEscapedModel(boolean escapedModel);

	public Serializable getPrimaryKeyObj();

	public ExpandoBridge getExpandoBridge();

	public void setExpandoBridgeAttributes(ServiceContext serviceContext);

	public Object clone();

	public int compareTo(Folder folder);

	public int hashCode();

	public String toString();

	public String toXmlString();
}