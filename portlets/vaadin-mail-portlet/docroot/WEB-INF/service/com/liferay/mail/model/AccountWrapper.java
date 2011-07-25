/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

	public Class<?> getModelClass() {
		return Account.class;
	}

	public String getModelClassName() {
		return Account.class.getName();
	}

	/**
	* Returns the primary key of this account.
	*
	* @return the primary key of this account
	*/
	public long getPrimaryKey() {
		return _account.getPrimaryKey();
	}

	/**
	* Sets the primary key of this account.
	*
	* @param primaryKey the primary key of this account
	*/
	public void setPrimaryKey(long primaryKey) {
		_account.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the account ID of this account.
	*
	* @return the account ID of this account
	*/
	public long getAccountId() {
		return _account.getAccountId();
	}

	/**
	* Sets the account ID of this account.
	*
	* @param accountId the account ID of this account
	*/
	public void setAccountId(long accountId) {
		_account.setAccountId(accountId);
	}

	/**
	* Returns the company ID of this account.
	*
	* @return the company ID of this account
	*/
	public long getCompanyId() {
		return _account.getCompanyId();
	}

	/**
	* Sets the company ID of this account.
	*
	* @param companyId the company ID of this account
	*/
	public void setCompanyId(long companyId) {
		_account.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this account.
	*
	* @return the user ID of this account
	*/
	public long getUserId() {
		return _account.getUserId();
	}

	/**
	* Sets the user ID of this account.
	*
	* @param userId the user ID of this account
	*/
	public void setUserId(long userId) {
		_account.setUserId(userId);
	}

	/**
	* Returns the user uuid of this account.
	*
	* @return the user uuid of this account
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _account.getUserUuid();
	}

	/**
	* Sets the user uuid of this account.
	*
	* @param userUuid the user uuid of this account
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_account.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this account.
	*
	* @return the user name of this account
	*/
	public java.lang.String getUserName() {
		return _account.getUserName();
	}

	/**
	* Sets the user name of this account.
	*
	* @param userName the user name of this account
	*/
	public void setUserName(java.lang.String userName) {
		_account.setUserName(userName);
	}

	/**
	* Returns the create date of this account.
	*
	* @return the create date of this account
	*/
	public java.util.Date getCreateDate() {
		return _account.getCreateDate();
	}

	/**
	* Sets the create date of this account.
	*
	* @param createDate the create date of this account
	*/
	public void setCreateDate(java.util.Date createDate) {
		_account.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this account.
	*
	* @return the modified date of this account
	*/
	public java.util.Date getModifiedDate() {
		return _account.getModifiedDate();
	}

	/**
	* Sets the modified date of this account.
	*
	* @param modifiedDate the modified date of this account
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_account.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the address of this account.
	*
	* @return the address of this account
	*/
	public java.lang.String getAddress() {
		return _account.getAddress();
	}

	/**
	* Sets the address of this account.
	*
	* @param address the address of this account
	*/
	public void setAddress(java.lang.String address) {
		_account.setAddress(address);
	}

	/**
	* Returns the personal name of this account.
	*
	* @return the personal name of this account
	*/
	public java.lang.String getPersonalName() {
		return _account.getPersonalName();
	}

	/**
	* Sets the personal name of this account.
	*
	* @param personalName the personal name of this account
	*/
	public void setPersonalName(java.lang.String personalName) {
		_account.setPersonalName(personalName);
	}

	/**
	* Returns the protocol of this account.
	*
	* @return the protocol of this account
	*/
	public java.lang.String getProtocol() {
		return _account.getProtocol();
	}

	/**
	* Sets the protocol of this account.
	*
	* @param protocol the protocol of this account
	*/
	public void setProtocol(java.lang.String protocol) {
		_account.setProtocol(protocol);
	}

	/**
	* Returns the incoming host name of this account.
	*
	* @return the incoming host name of this account
	*/
	public java.lang.String getIncomingHostName() {
		return _account.getIncomingHostName();
	}

	/**
	* Sets the incoming host name of this account.
	*
	* @param incomingHostName the incoming host name of this account
	*/
	public void setIncomingHostName(java.lang.String incomingHostName) {
		_account.setIncomingHostName(incomingHostName);
	}

	/**
	* Returns the incoming port of this account.
	*
	* @return the incoming port of this account
	*/
	public int getIncomingPort() {
		return _account.getIncomingPort();
	}

	/**
	* Sets the incoming port of this account.
	*
	* @param incomingPort the incoming port of this account
	*/
	public void setIncomingPort(int incomingPort) {
		_account.setIncomingPort(incomingPort);
	}

	/**
	* Returns the incoming secure of this account.
	*
	* @return the incoming secure of this account
	*/
	public boolean getIncomingSecure() {
		return _account.getIncomingSecure();
	}

	/**
	* Returns <code>true</code> if this account is incoming secure.
	*
	* @return <code>true</code> if this account is incoming secure; <code>false</code> otherwise
	*/
	public boolean isIncomingSecure() {
		return _account.isIncomingSecure();
	}

	/**
	* Sets whether this account is incoming secure.
	*
	* @param incomingSecure the incoming secure of this account
	*/
	public void setIncomingSecure(boolean incomingSecure) {
		_account.setIncomingSecure(incomingSecure);
	}

	/**
	* Returns the outgoing host name of this account.
	*
	* @return the outgoing host name of this account
	*/
	public java.lang.String getOutgoingHostName() {
		return _account.getOutgoingHostName();
	}

	/**
	* Sets the outgoing host name of this account.
	*
	* @param outgoingHostName the outgoing host name of this account
	*/
	public void setOutgoingHostName(java.lang.String outgoingHostName) {
		_account.setOutgoingHostName(outgoingHostName);
	}

	/**
	* Returns the outgoing port of this account.
	*
	* @return the outgoing port of this account
	*/
	public int getOutgoingPort() {
		return _account.getOutgoingPort();
	}

	/**
	* Sets the outgoing port of this account.
	*
	* @param outgoingPort the outgoing port of this account
	*/
	public void setOutgoingPort(int outgoingPort) {
		_account.setOutgoingPort(outgoingPort);
	}

	/**
	* Returns the outgoing secure of this account.
	*
	* @return the outgoing secure of this account
	*/
	public boolean getOutgoingSecure() {
		return _account.getOutgoingSecure();
	}

	/**
	* Returns <code>true</code> if this account is outgoing secure.
	*
	* @return <code>true</code> if this account is outgoing secure; <code>false</code> otherwise
	*/
	public boolean isOutgoingSecure() {
		return _account.isOutgoingSecure();
	}

	/**
	* Sets whether this account is outgoing secure.
	*
	* @param outgoingSecure the outgoing secure of this account
	*/
	public void setOutgoingSecure(boolean outgoingSecure) {
		_account.setOutgoingSecure(outgoingSecure);
	}

	/**
	* Returns the login of this account.
	*
	* @return the login of this account
	*/
	public java.lang.String getLogin() {
		return _account.getLogin();
	}

	/**
	* Sets the login of this account.
	*
	* @param login the login of this account
	*/
	public void setLogin(java.lang.String login) {
		_account.setLogin(login);
	}

	/**
	* Returns the password of this account.
	*
	* @return the password of this account
	*/
	public java.lang.String getPassword() {
		return _account.getPassword();
	}

	/**
	* Sets the password of this account.
	*
	* @param password the password of this account
	*/
	public void setPassword(java.lang.String password) {
		_account.setPassword(password);
	}

	/**
	* Returns the save password of this account.
	*
	* @return the save password of this account
	*/
	public boolean getSavePassword() {
		return _account.getSavePassword();
	}

	/**
	* Returns <code>true</code> if this account is save password.
	*
	* @return <code>true</code> if this account is save password; <code>false</code> otherwise
	*/
	public boolean isSavePassword() {
		return _account.isSavePassword();
	}

	/**
	* Sets whether this account is save password.
	*
	* @param savePassword the save password of this account
	*/
	public void setSavePassword(boolean savePassword) {
		_account.setSavePassword(savePassword);
	}

	/**
	* Returns the signature of this account.
	*
	* @return the signature of this account
	*/
	public java.lang.String getSignature() {
		return _account.getSignature();
	}

	/**
	* Sets the signature of this account.
	*
	* @param signature the signature of this account
	*/
	public void setSignature(java.lang.String signature) {
		_account.setSignature(signature);
	}

	/**
	* Returns the use signature of this account.
	*
	* @return the use signature of this account
	*/
	public boolean getUseSignature() {
		return _account.getUseSignature();
	}

	/**
	* Returns <code>true</code> if this account is use signature.
	*
	* @return <code>true</code> if this account is use signature; <code>false</code> otherwise
	*/
	public boolean isUseSignature() {
		return _account.isUseSignature();
	}

	/**
	* Sets whether this account is use signature.
	*
	* @param useSignature the use signature of this account
	*/
	public void setUseSignature(boolean useSignature) {
		_account.setUseSignature(useSignature);
	}

	/**
	* Returns the folder prefix of this account.
	*
	* @return the folder prefix of this account
	*/
	public java.lang.String getFolderPrefix() {
		return _account.getFolderPrefix();
	}

	/**
	* Sets the folder prefix of this account.
	*
	* @param folderPrefix the folder prefix of this account
	*/
	public void setFolderPrefix(java.lang.String folderPrefix) {
		_account.setFolderPrefix(folderPrefix);
	}

	/**
	* Returns the inbox folder ID of this account.
	*
	* @return the inbox folder ID of this account
	*/
	public long getInboxFolderId() {
		return _account.getInboxFolderId();
	}

	/**
	* Sets the inbox folder ID of this account.
	*
	* @param inboxFolderId the inbox folder ID of this account
	*/
	public void setInboxFolderId(long inboxFolderId) {
		_account.setInboxFolderId(inboxFolderId);
	}

	/**
	* Returns the draft folder ID of this account.
	*
	* @return the draft folder ID of this account
	*/
	public long getDraftFolderId() {
		return _account.getDraftFolderId();
	}

	/**
	* Sets the draft folder ID of this account.
	*
	* @param draftFolderId the draft folder ID of this account
	*/
	public void setDraftFolderId(long draftFolderId) {
		_account.setDraftFolderId(draftFolderId);
	}

	/**
	* Returns the sent folder ID of this account.
	*
	* @return the sent folder ID of this account
	*/
	public long getSentFolderId() {
		return _account.getSentFolderId();
	}

	/**
	* Sets the sent folder ID of this account.
	*
	* @param sentFolderId the sent folder ID of this account
	*/
	public void setSentFolderId(long sentFolderId) {
		_account.setSentFolderId(sentFolderId);
	}

	/**
	* Returns the trash folder ID of this account.
	*
	* @return the trash folder ID of this account
	*/
	public long getTrashFolderId() {
		return _account.getTrashFolderId();
	}

	/**
	* Sets the trash folder ID of this account.
	*
	* @param trashFolderId the trash folder ID of this account
	*/
	public void setTrashFolderId(long trashFolderId) {
		_account.setTrashFolderId(trashFolderId);
	}

	/**
	* Returns the default sender of this account.
	*
	* @return the default sender of this account
	*/
	public boolean getDefaultSender() {
		return _account.getDefaultSender();
	}

	/**
	* Returns <code>true</code> if this account is default sender.
	*
	* @return <code>true</code> if this account is default sender; <code>false</code> otherwise
	*/
	public boolean isDefaultSender() {
		return _account.isDefaultSender();
	}

	/**
	* Sets whether this account is default sender.
	*
	* @param defaultSender the default sender of this account
	*/
	public void setDefaultSender(boolean defaultSender) {
		_account.setDefaultSender(defaultSender);
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

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_account.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _account.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_account.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AccountWrapper((Account)_account.clone());
	}

	public int compareTo(Account account) {
		return _account.compareTo(account);
	}

	@Override
	public int hashCode() {
		return _account.hashCode();
	}

	public com.liferay.portal.model.CacheModel<Account> toCacheModel() {
		return _account.toCacheModel();
	}

	public Account toEscapedModel() {
		return new AccountWrapper(_account.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _account.toString();
	}

	public java.lang.String toXmlString() {
		return _account.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_account.persist();
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

	public void resetOriginalValues() {
		_account.resetOriginalValues();
	}

	private Account _account;
}