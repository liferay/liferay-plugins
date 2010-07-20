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

/**
 * <p>
 * This class is a wrapper for {@link Account}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Account
 * @generated
 */
public class AccountWrapper implements Account {
	public AccountWrapper(Account account) {
		_account = account;
	}

	public long getPrimaryKey() {
		return _account.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_account.setPrimaryKey(pk);
	}

	public long getAccountId() {
		return _account.getAccountId();
	}

	public void setAccountId(long accountId) {
		_account.setAccountId(accountId);
	}

	public long getCompanyId() {
		return _account.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_account.setCompanyId(companyId);
	}

	public long getUserId() {
		return _account.getUserId();
	}

	public void setUserId(long userId) {
		_account.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _account.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_account.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _account.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_account.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _account.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_account.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _account.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_account.setModifiedDate(modifiedDate);
	}

	public java.lang.String getAddress() {
		return _account.getAddress();
	}

	public void setAddress(java.lang.String address) {
		_account.setAddress(address);
	}

	public java.lang.String getPersonalName() {
		return _account.getPersonalName();
	}

	public void setPersonalName(java.lang.String personalName) {
		_account.setPersonalName(personalName);
	}

	public java.lang.String getProtocol() {
		return _account.getProtocol();
	}

	public void setProtocol(java.lang.String protocol) {
		_account.setProtocol(protocol);
	}

	public java.lang.String getIncomingHostName() {
		return _account.getIncomingHostName();
	}

	public void setIncomingHostName(java.lang.String incomingHostName) {
		_account.setIncomingHostName(incomingHostName);
	}

	public int getIncomingPort() {
		return _account.getIncomingPort();
	}

	public void setIncomingPort(int incomingPort) {
		_account.setIncomingPort(incomingPort);
	}

	public boolean getIncomingSecure() {
		return _account.getIncomingSecure();
	}

	public boolean isIncomingSecure() {
		return _account.isIncomingSecure();
	}

	public void setIncomingSecure(boolean incomingSecure) {
		_account.setIncomingSecure(incomingSecure);
	}

	public java.lang.String getOutgoingHostName() {
		return _account.getOutgoingHostName();
	}

	public void setOutgoingHostName(java.lang.String outgoingHostName) {
		_account.setOutgoingHostName(outgoingHostName);
	}

	public int getOutgoingPort() {
		return _account.getOutgoingPort();
	}

	public void setOutgoingPort(int outgoingPort) {
		_account.setOutgoingPort(outgoingPort);
	}

	public boolean getOutgoingSecure() {
		return _account.getOutgoingSecure();
	}

	public boolean isOutgoingSecure() {
		return _account.isOutgoingSecure();
	}

	public void setOutgoingSecure(boolean outgoingSecure) {
		_account.setOutgoingSecure(outgoingSecure);
	}

	public java.lang.String getLogin() {
		return _account.getLogin();
	}

	public void setLogin(java.lang.String login) {
		_account.setLogin(login);
	}

	public java.lang.String getPassword() {
		return _account.getPassword();
	}

	public void setPassword(java.lang.String password) {
		_account.setPassword(password);
	}

	public boolean getSavePassword() {
		return _account.getSavePassword();
	}

	public boolean isSavePassword() {
		return _account.isSavePassword();
	}

	public void setSavePassword(boolean savePassword) {
		_account.setSavePassword(savePassword);
	}

	public java.lang.String getSignature() {
		return _account.getSignature();
	}

	public void setSignature(java.lang.String signature) {
		_account.setSignature(signature);
	}

	public boolean getUseSignature() {
		return _account.getUseSignature();
	}

	public boolean isUseSignature() {
		return _account.isUseSignature();
	}

	public void setUseSignature(boolean useSignature) {
		_account.setUseSignature(useSignature);
	}

	public java.lang.String getFolderPrefix() {
		return _account.getFolderPrefix();
	}

	public void setFolderPrefix(java.lang.String folderPrefix) {
		_account.setFolderPrefix(folderPrefix);
	}

	public long getInboxFolderId() {
		return _account.getInboxFolderId();
	}

	public void setInboxFolderId(long inboxFolderId) {
		_account.setInboxFolderId(inboxFolderId);
	}

	public long getDraftFolderId() {
		return _account.getDraftFolderId();
	}

	public void setDraftFolderId(long draftFolderId) {
		_account.setDraftFolderId(draftFolderId);
	}

	public long getSentFolderId() {
		return _account.getSentFolderId();
	}

	public void setSentFolderId(long sentFolderId) {
		_account.setSentFolderId(sentFolderId);
	}

	public long getTrashFolderId() {
		return _account.getTrashFolderId();
	}

	public void setTrashFolderId(long trashFolderId) {
		_account.setTrashFolderId(trashFolderId);
	}

	public boolean getDefaultSender() {
		return _account.getDefaultSender();
	}

	public boolean isDefaultSender() {
		return _account.isDefaultSender();
	}

	public void setDefaultSender(boolean defaultSender) {
		_account.setDefaultSender(defaultSender);
	}

	public com.liferay.mail.model.Account toEscapedModel() {
		return _account.toEscapedModel();
	}

	public boolean isNew() {
		return _account.isNew();
	}

	public void setNew(boolean n) {
		_account.setNew(n);
	}

	public boolean isCachedModel() {
		return _account.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_account.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _account.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_account.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _account.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _account.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_account.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _account.clone();
	}

	public int compareTo(com.liferay.mail.model.Account account) {
		return _account.compareTo(account);
	}

	public int hashCode() {
		return _account.hashCode();
	}

	public java.lang.String toString() {
		return _account.toString();
	}

	public java.lang.String toXmlString() {
		return _account.toXmlString();
	}

	public java.lang.String getPasswordDecrypted() {
		return _account.getPasswordDecrypted();
	}

	public void setPasswordDecrypted(java.lang.String unencryptedPassword) {
		_account.setPasswordDecrypted(unencryptedPassword);
	}

	public Account getWrappedAccount() {
		return _account;
	}

	private Account _account;
}