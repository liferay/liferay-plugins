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

/**
 * <p>
 * This interface is a model that represents the Mail_Attachment table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Attachment
 * @see       com.liferay.mail.model.impl.AttachmentImpl
 * @see       com.liferay.mail.model.impl.AttachmentModelImpl
 * @generated
 */
public interface AttachmentModel extends BaseModel<Attachment> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getAttachmentId();

	public void setAttachmentId(long attachmentId);

	public long getCompanyId();

	public void setCompanyId(long companyId);

	public long getUserId();

	public void setUserId(long userId);

	public String getUserUuid() throws SystemException;

	public void setUserUuid(String userUuid);

	public long getAccountId();

	public void setAccountId(long accountId);

	public long getFolderId();

	public void setFolderId(long folderId);

	public long getMessageId();

	public void setMessageId(long messageId);

	@AutoEscape
	public String getContentPath();

	public void setContentPath(String contentPath);

	@AutoEscape
	public String getFileName();

	public void setFileName(String fileName);

	public long getSize();

	public void setSize(long size);

	public Attachment toEscapedModel();

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

	public int compareTo(Attachment attachment);

	public int hashCode();

	public String toString();

	public String toXmlString();
}