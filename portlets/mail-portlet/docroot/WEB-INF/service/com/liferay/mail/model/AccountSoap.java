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

package com.liferay.mail.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
@ProviderType
public class AccountSoap implements Serializable {
	public static AccountSoap toSoapModel(Account model) {
		AccountSoap soapModel = new AccountSoap();

		soapModel.setAccountId(model.getAccountId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setAddress(model.getAddress());
		soapModel.setPersonalName(model.getPersonalName());
		soapModel.setProtocol(model.getProtocol());
		soapModel.setIncomingHostName(model.getIncomingHostName());
		soapModel.setIncomingPort(model.getIncomingPort());
		soapModel.setIncomingSecure(model.getIncomingSecure());
		soapModel.setOutgoingHostName(model.getOutgoingHostName());
		soapModel.setOutgoingPort(model.getOutgoingPort());
		soapModel.setOutgoingSecure(model.getOutgoingSecure());
		soapModel.setLogin(model.getLogin());
		soapModel.setPassword(model.getPassword());
		soapModel.setSavePassword(model.getSavePassword());
		soapModel.setSignature(model.getSignature());
		soapModel.setUseSignature(model.getUseSignature());
		soapModel.setFolderPrefix(model.getFolderPrefix());
		soapModel.setInboxFolderId(model.getInboxFolderId());
		soapModel.setDraftFolderId(model.getDraftFolderId());
		soapModel.setSentFolderId(model.getSentFolderId());
		soapModel.setTrashFolderId(model.getTrashFolderId());
		soapModel.setDefaultSender(model.getDefaultSender());

		return soapModel;
	}

	public static AccountSoap[] toSoapModels(Account[] models) {
		AccountSoap[] soapModels = new AccountSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AccountSoap[][] toSoapModels(Account[][] models) {
		AccountSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AccountSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AccountSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AccountSoap[] toSoapModels(List<Account> models) {
		List<AccountSoap> soapModels = new ArrayList<AccountSoap>(models.size());

		for (Account model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AccountSoap[soapModels.size()]);
	}

	public AccountSoap() {
	}

	public long getPrimaryKey() {
		return _accountId;
	}

	public void setPrimaryKey(long pk) {
		setAccountId(pk);
	}

	public long getAccountId() {
		return _accountId;
	}

	public void setAccountId(long accountId) {
		_accountId = accountId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public String getPersonalName() {
		return _personalName;
	}

	public void setPersonalName(String personalName) {
		_personalName = personalName;
	}

	public String getProtocol() {
		return _protocol;
	}

	public void setProtocol(String protocol) {
		_protocol = protocol;
	}

	public String getIncomingHostName() {
		return _incomingHostName;
	}

	public void setIncomingHostName(String incomingHostName) {
		_incomingHostName = incomingHostName;
	}

	public int getIncomingPort() {
		return _incomingPort;
	}

	public void setIncomingPort(int incomingPort) {
		_incomingPort = incomingPort;
	}

	public boolean getIncomingSecure() {
		return _incomingSecure;
	}

	public boolean isIncomingSecure() {
		return _incomingSecure;
	}

	public void setIncomingSecure(boolean incomingSecure) {
		_incomingSecure = incomingSecure;
	}

	public String getOutgoingHostName() {
		return _outgoingHostName;
	}

	public void setOutgoingHostName(String outgoingHostName) {
		_outgoingHostName = outgoingHostName;
	}

	public int getOutgoingPort() {
		return _outgoingPort;
	}

	public void setOutgoingPort(int outgoingPort) {
		_outgoingPort = outgoingPort;
	}

	public boolean getOutgoingSecure() {
		return _outgoingSecure;
	}

	public boolean isOutgoingSecure() {
		return _outgoingSecure;
	}

	public void setOutgoingSecure(boolean outgoingSecure) {
		_outgoingSecure = outgoingSecure;
	}

	public String getLogin() {
		return _login;
	}

	public void setLogin(String login) {
		_login = login;
	}

	public String getPassword() {
		return _password;
	}

	public void setPassword(String password) {
		_password = password;
	}

	public boolean getSavePassword() {
		return _savePassword;
	}

	public boolean isSavePassword() {
		return _savePassword;
	}

	public void setSavePassword(boolean savePassword) {
		_savePassword = savePassword;
	}

	public String getSignature() {
		return _signature;
	}

	public void setSignature(String signature) {
		_signature = signature;
	}

	public boolean getUseSignature() {
		return _useSignature;
	}

	public boolean isUseSignature() {
		return _useSignature;
	}

	public void setUseSignature(boolean useSignature) {
		_useSignature = useSignature;
	}

	public String getFolderPrefix() {
		return _folderPrefix;
	}

	public void setFolderPrefix(String folderPrefix) {
		_folderPrefix = folderPrefix;
	}

	public long getInboxFolderId() {
		return _inboxFolderId;
	}

	public void setInboxFolderId(long inboxFolderId) {
		_inboxFolderId = inboxFolderId;
	}

	public long getDraftFolderId() {
		return _draftFolderId;
	}

	public void setDraftFolderId(long draftFolderId) {
		_draftFolderId = draftFolderId;
	}

	public long getSentFolderId() {
		return _sentFolderId;
	}

	public void setSentFolderId(long sentFolderId) {
		_sentFolderId = sentFolderId;
	}

	public long getTrashFolderId() {
		return _trashFolderId;
	}

	public void setTrashFolderId(long trashFolderId) {
		_trashFolderId = trashFolderId;
	}

	public boolean getDefaultSender() {
		return _defaultSender;
	}

	public boolean isDefaultSender() {
		return _defaultSender;
	}

	public void setDefaultSender(boolean defaultSender) {
		_defaultSender = defaultSender;
	}

	private long _accountId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _address;
	private String _personalName;
	private String _protocol;
	private String _incomingHostName;
	private int _incomingPort;
	private boolean _incomingSecure;
	private String _outgoingHostName;
	private int _outgoingPort;
	private boolean _outgoingSecure;
	private String _login;
	private String _password;
	private boolean _savePassword;
	private String _signature;
	private boolean _useSignature;
	private String _folderPrefix;
	private long _inboxFolderId;
	private long _draftFolderId;
	private long _sentFolderId;
	private long _trashFolderId;
	private boolean _defaultSender;
}