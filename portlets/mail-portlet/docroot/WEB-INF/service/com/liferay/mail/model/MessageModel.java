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
 * This interface is a model that represents the Mail_Message table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Message
 * @see       com.liferay.mail.model.impl.MessageImpl
 * @see       com.liferay.mail.model.impl.MessageModelImpl
 * @generated
 */
public interface MessageModel extends BaseModel<Message> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getMessageId();

	public void setMessageId(long messageId);

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

	public long getFolderId();

	public void setFolderId(long folderId);

	@AutoEscape
	public String getSender();

	public void setSender(String sender);

	@AutoEscape
	public String getTo();

	public void setTo(String to);

	@AutoEscape
	public String getCc();

	public void setCc(String cc);

	@AutoEscape
	public String getBcc();

	public void setBcc(String bcc);

	public Date getSentDate();

	public void setSentDate(Date sentDate);

	@AutoEscape
	public String getSubject();

	public void setSubject(String subject);

	@AutoEscape
	public String getPreview();

	public void setPreview(String preview);

	@AutoEscape
	public String getBody();

	public void setBody(String body);

	@AutoEscape
	public String getFlags();

	public void setFlags(String flags);

	public long getSize();

	public void setSize(long size);

	public long getRemoteMessageId();

	public void setRemoteMessageId(long remoteMessageId);

	public Message toEscapedModel();

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

	public int compareTo(Message message);

	public int hashCode();

	public String toString();

	public String toXmlString();
}