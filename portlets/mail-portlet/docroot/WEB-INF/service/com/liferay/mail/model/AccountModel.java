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
 * This interface is a model that represents the Mail_Account table in the
 * database.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Account
 * @see       com.liferay.mail.model.impl.AccountImpl
 * @see       com.liferay.mail.model.impl.AccountModelImpl
 * @generated
 */
public interface AccountModel extends BaseModel<Account> {
	public long getPrimaryKey();

	public void setPrimaryKey(long pk);

	public long getAccountId();

	public void setAccountId(long accountId);

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

	@AutoEscape
	public String getAddress();

	public void setAddress(String address);

	@AutoEscape
	public String getPersonalName();

	public void setPersonalName(String personalName);

	@AutoEscape
	public String getProtocol();

	public void setProtocol(String protocol);

	@AutoEscape
	public String getIncomingHostName();

	public void setIncomingHostName(String incomingHostName);

	public int getIncomingPort();

	public void setIncomingPort(int incomingPort);

	public boolean getIncomingSecure();

	public boolean isIncomingSecure();

	public void setIncomingSecure(boolean incomingSecure);

	@AutoEscape
	public String getOutgoingHostName();

	public void setOutgoingHostName(String outgoingHostName);

	public int getOutgoingPort();

	public void setOutgoingPort(int outgoingPort);

	public boolean getOutgoingSecure();

	public boolean isOutgoingSecure();

	public void setOutgoingSecure(boolean outgoingSecure);

	@AutoEscape
	public String getLogin();

	public void setLogin(String login);

	@AutoEscape
	public String getPassword();

	public void setPassword(String password);

	public boolean getSavePassword();

	public boolean isSavePassword();

	public void setSavePassword(boolean savePassword);

	@AutoEscape
	public String getSignature();

	public void setSignature(String signature);

	public boolean getUseSignature();

	public boolean isUseSignature();

	public void setUseSignature(boolean useSignature);

	@AutoEscape
	public String getFolderPrefix();

	public void setFolderPrefix(String folderPrefix);

	public long getInboxFolderId();

	public void setInboxFolderId(long inboxFolderId);

	public long getDraftFolderId();

	public void setDraftFolderId(long draftFolderId);

	public long getSentFolderId();

	public void setSentFolderId(long sentFolderId);

	public long getTrashFolderId();

	public void setTrashFolderId(long trashFolderId);

	public boolean getDefaultSender();

	public boolean isDefaultSender();

	public void setDefaultSender(boolean defaultSender);

	public Account toEscapedModel();

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

	public int compareTo(Account account);

	public int hashCode();

	public String toString();

	public String toXmlString();
}