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

package com.liferay.mail.model;

import com.liferay.mail.service.AccountLocalServiceUtil;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import java.io.Serializable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class AccountClp extends BaseModelImpl<Account> implements Account {
	public AccountClp() {
	}

	public Class<?> getModelClass() {
		return Account.class;
	}

	public String getModelClassName() {
		return Account.class.getName();
	}

	public long getPrimaryKey() {
		return _accountId;
	}

	public void setPrimaryKey(long primaryKey) {
		setAccountId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_accountId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("accountId", getAccountId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("address", getAddress());
		attributes.put("personalName", getPersonalName());
		attributes.put("protocol", getProtocol());
		attributes.put("incomingHostName", getIncomingHostName());
		attributes.put("incomingPort", getIncomingPort());
		attributes.put("incomingSecure", getIncomingSecure());
		attributes.put("outgoingHostName", getOutgoingHostName());
		attributes.put("outgoingPort", getOutgoingPort());
		attributes.put("outgoingSecure", getOutgoingSecure());
		attributes.put("login", getLogin());
		attributes.put("password", getPassword());
		attributes.put("savePassword", getSavePassword());
		attributes.put("signature", getSignature());
		attributes.put("useSignature", getUseSignature());
		attributes.put("folderPrefix", getFolderPrefix());
		attributes.put("inboxFolderId", getInboxFolderId());
		attributes.put("draftFolderId", getDraftFolderId());
		attributes.put("sentFolderId", getSentFolderId());
		attributes.put("trashFolderId", getTrashFolderId());
		attributes.put("defaultSender", getDefaultSender());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long accountId = (Long)attributes.get("accountId");

		if (accountId != null) {
			setAccountId(accountId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String address = (String)attributes.get("address");

		if (address != null) {
			setAddress(address);
		}

		String personalName = (String)attributes.get("personalName");

		if (personalName != null) {
			setPersonalName(personalName);
		}

		String protocol = (String)attributes.get("protocol");

		if (protocol != null) {
			setProtocol(protocol);
		}

		String incomingHostName = (String)attributes.get("incomingHostName");

		if (incomingHostName != null) {
			setIncomingHostName(incomingHostName);
		}

		Integer incomingPort = (Integer)attributes.get("incomingPort");

		if (incomingPort != null) {
			setIncomingPort(incomingPort);
		}

		Boolean incomingSecure = (Boolean)attributes.get("incomingSecure");

		if (incomingSecure != null) {
			setIncomingSecure(incomingSecure);
		}

		String outgoingHostName = (String)attributes.get("outgoingHostName");

		if (outgoingHostName != null) {
			setOutgoingHostName(outgoingHostName);
		}

		Integer outgoingPort = (Integer)attributes.get("outgoingPort");

		if (outgoingPort != null) {
			setOutgoingPort(outgoingPort);
		}

		Boolean outgoingSecure = (Boolean)attributes.get("outgoingSecure");

		if (outgoingSecure != null) {
			setOutgoingSecure(outgoingSecure);
		}

		String login = (String)attributes.get("login");

		if (login != null) {
			setLogin(login);
		}

		String password = (String)attributes.get("password");

		if (password != null) {
			setPassword(password);
		}

		Boolean savePassword = (Boolean)attributes.get("savePassword");

		if (savePassword != null) {
			setSavePassword(savePassword);
		}

		String signature = (String)attributes.get("signature");

		if (signature != null) {
			setSignature(signature);
		}

		Boolean useSignature = (Boolean)attributes.get("useSignature");

		if (useSignature != null) {
			setUseSignature(useSignature);
		}

		String folderPrefix = (String)attributes.get("folderPrefix");

		if (folderPrefix != null) {
			setFolderPrefix(folderPrefix);
		}

		Long inboxFolderId = (Long)attributes.get("inboxFolderId");

		if (inboxFolderId != null) {
			setInboxFolderId(inboxFolderId);
		}

		Long draftFolderId = (Long)attributes.get("draftFolderId");

		if (draftFolderId != null) {
			setDraftFolderId(draftFolderId);
		}

		Long sentFolderId = (Long)attributes.get("sentFolderId");

		if (sentFolderId != null) {
			setSentFolderId(sentFolderId);
		}

		Long trashFolderId = (Long)attributes.get("trashFolderId");

		if (trashFolderId != null) {
			setTrashFolderId(trashFolderId);
		}

		Boolean defaultSender = (Boolean)attributes.get("defaultSender");

		if (defaultSender != null) {
			setDefaultSender(defaultSender);
		}
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

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
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

	public void setPasswordDecrypted(java.lang.String unencryptedPassword) {
		throw new UnsupportedOperationException();
	}

	public java.lang.String getPasswordDecrypted() {
		throw new UnsupportedOperationException();
	}

	public BaseModel<?> getAccountRemoteModel() {
		return _accountRemoteModel;
	}

	public void setAccountRemoteModel(BaseModel<?> accountRemoteModel) {
		_accountRemoteModel = accountRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			AccountLocalServiceUtil.addAccount(this);
		}
		else {
			AccountLocalServiceUtil.updateAccount(this);
		}
	}

	@Override
	public Account toEscapedModel() {
		return (Account)ProxyUtil.newProxyInstance(Account.class.getClassLoader(),
			new Class[] { Account.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		AccountClp clone = new AccountClp();

		clone.setAccountId(getAccountId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setAddress(getAddress());
		clone.setPersonalName(getPersonalName());
		clone.setProtocol(getProtocol());
		clone.setIncomingHostName(getIncomingHostName());
		clone.setIncomingPort(getIncomingPort());
		clone.setIncomingSecure(getIncomingSecure());
		clone.setOutgoingHostName(getOutgoingHostName());
		clone.setOutgoingPort(getOutgoingPort());
		clone.setOutgoingSecure(getOutgoingSecure());
		clone.setLogin(getLogin());
		clone.setPassword(getPassword());
		clone.setSavePassword(getSavePassword());
		clone.setSignature(getSignature());
		clone.setUseSignature(getUseSignature());
		clone.setFolderPrefix(getFolderPrefix());
		clone.setInboxFolderId(getInboxFolderId());
		clone.setDraftFolderId(getDraftFolderId());
		clone.setSentFolderId(getSentFolderId());
		clone.setTrashFolderId(getTrashFolderId());
		clone.setDefaultSender(getDefaultSender());

		return clone;
	}

	public int compareTo(Account account) {
		int value = 0;

		value = getAddress().compareTo(account.getAddress());

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		AccountClp account = null;

		try {
			account = (AccountClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = account.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(53);

		sb.append("{accountId=");
		sb.append(getAccountId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", address=");
		sb.append(getAddress());
		sb.append(", personalName=");
		sb.append(getPersonalName());
		sb.append(", protocol=");
		sb.append(getProtocol());
		sb.append(", incomingHostName=");
		sb.append(getIncomingHostName());
		sb.append(", incomingPort=");
		sb.append(getIncomingPort());
		sb.append(", incomingSecure=");
		sb.append(getIncomingSecure());
		sb.append(", outgoingHostName=");
		sb.append(getOutgoingHostName());
		sb.append(", outgoingPort=");
		sb.append(getOutgoingPort());
		sb.append(", outgoingSecure=");
		sb.append(getOutgoingSecure());
		sb.append(", login=");
		sb.append(getLogin());
		sb.append(", password=");
		sb.append(getPassword());
		sb.append(", savePassword=");
		sb.append(getSavePassword());
		sb.append(", signature=");
		sb.append(getSignature());
		sb.append(", useSignature=");
		sb.append(getUseSignature());
		sb.append(", folderPrefix=");
		sb.append(getFolderPrefix());
		sb.append(", inboxFolderId=");
		sb.append(getInboxFolderId());
		sb.append(", draftFolderId=");
		sb.append(getDraftFolderId());
		sb.append(", sentFolderId=");
		sb.append(getSentFolderId());
		sb.append(", trashFolderId=");
		sb.append(getTrashFolderId());
		sb.append(", defaultSender=");
		sb.append(getDefaultSender());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(82);

		sb.append("<model><model-name>");
		sb.append("com.liferay.mail.model.Account");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>accountId</column-name><column-value><![CDATA[");
		sb.append(getAccountId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>address</column-name><column-value><![CDATA[");
		sb.append(getAddress());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>personalName</column-name><column-value><![CDATA[");
		sb.append(getPersonalName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>protocol</column-name><column-value><![CDATA[");
		sb.append(getProtocol());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>incomingHostName</column-name><column-value><![CDATA[");
		sb.append(getIncomingHostName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>incomingPort</column-name><column-value><![CDATA[");
		sb.append(getIncomingPort());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>incomingSecure</column-name><column-value><![CDATA[");
		sb.append(getIncomingSecure());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outgoingHostName</column-name><column-value><![CDATA[");
		sb.append(getOutgoingHostName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outgoingPort</column-name><column-value><![CDATA[");
		sb.append(getOutgoingPort());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>outgoingSecure</column-name><column-value><![CDATA[");
		sb.append(getOutgoingSecure());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>login</column-name><column-value><![CDATA[");
		sb.append(getLogin());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>password</column-name><column-value><![CDATA[");
		sb.append(getPassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>savePassword</column-name><column-value><![CDATA[");
		sb.append(getSavePassword());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>signature</column-name><column-value><![CDATA[");
		sb.append(getSignature());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>useSignature</column-name><column-value><![CDATA[");
		sb.append(getUseSignature());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>folderPrefix</column-name><column-value><![CDATA[");
		sb.append(getFolderPrefix());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>inboxFolderId</column-name><column-value><![CDATA[");
		sb.append(getInboxFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>draftFolderId</column-name><column-value><![CDATA[");
		sb.append(getDraftFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>sentFolderId</column-name><column-value><![CDATA[");
		sb.append(getSentFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>trashFolderId</column-name><column-value><![CDATA[");
		sb.append(getTrashFolderId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>defaultSender</column-name><column-value><![CDATA[");
		sb.append(getDefaultSender());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _accountId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
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
	private BaseModel<?> _accountRemoteModel;
}