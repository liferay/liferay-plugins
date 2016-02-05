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

import com.liferay.mail.service.AccountLocalServiceUtil;
import com.liferay.mail.service.ClpSerializer;

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class AccountClp extends BaseModelImpl<Account> implements Account {
	public AccountClp() {
	}

	@Override
	public Class<?> getModelClass() {
		return Account.class;
	}

	@Override
	public String getModelClassName() {
		return Account.class.getName();
	}

	@Override
	public long getPrimaryKey() {
		return _accountId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setAccountId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _accountId;
	}

	@Override
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getAccountId() {
		return _accountId;
	}

	@Override
	public void setAccountId(long accountId) {
		_accountId = accountId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setAccountId", long.class);

				method.invoke(_accountRemoteModel, accountId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_accountRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_accountRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_accountRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_accountRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_accountRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getAddress() {
		return _address;
	}

	@Override
	public void setAddress(String address) {
		_address = address;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setAddress", String.class);

				method.invoke(_accountRemoteModel, address);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPersonalName() {
		return _personalName;
	}

	@Override
	public void setPersonalName(String personalName) {
		_personalName = personalName;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setPersonalName", String.class);

				method.invoke(_accountRemoteModel, personalName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getProtocol() {
		return _protocol;
	}

	@Override
	public void setProtocol(String protocol) {
		_protocol = protocol;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setProtocol", String.class);

				method.invoke(_accountRemoteModel, protocol);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getIncomingHostName() {
		return _incomingHostName;
	}

	@Override
	public void setIncomingHostName(String incomingHostName) {
		_incomingHostName = incomingHostName;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setIncomingHostName",
						String.class);

				method.invoke(_accountRemoteModel, incomingHostName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getIncomingPort() {
		return _incomingPort;
	}

	@Override
	public void setIncomingPort(int incomingPort) {
		_incomingPort = incomingPort;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setIncomingPort", int.class);

				method.invoke(_accountRemoteModel, incomingPort);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getIncomingSecure() {
		return _incomingSecure;
	}

	@Override
	public boolean isIncomingSecure() {
		return _incomingSecure;
	}

	@Override
	public void setIncomingSecure(boolean incomingSecure) {
		_incomingSecure = incomingSecure;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setIncomingSecure",
						boolean.class);

				method.invoke(_accountRemoteModel, incomingSecure);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getOutgoingHostName() {
		return _outgoingHostName;
	}

	@Override
	public void setOutgoingHostName(String outgoingHostName) {
		_outgoingHostName = outgoingHostName;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setOutgoingHostName",
						String.class);

				method.invoke(_accountRemoteModel, outgoingHostName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public int getOutgoingPort() {
		return _outgoingPort;
	}

	@Override
	public void setOutgoingPort(int outgoingPort) {
		_outgoingPort = outgoingPort;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setOutgoingPort", int.class);

				method.invoke(_accountRemoteModel, outgoingPort);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getOutgoingSecure() {
		return _outgoingSecure;
	}

	@Override
	public boolean isOutgoingSecure() {
		return _outgoingSecure;
	}

	@Override
	public void setOutgoingSecure(boolean outgoingSecure) {
		_outgoingSecure = outgoingSecure;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setOutgoingSecure",
						boolean.class);

				method.invoke(_accountRemoteModel, outgoingSecure);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getLogin() {
		return _login;
	}

	@Override
	public void setLogin(String login) {
		_login = login;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setLogin", String.class);

				method.invoke(_accountRemoteModel, login);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPassword() {
		return _password;
	}

	@Override
	public void setPassword(String password) {
		_password = password;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setPassword", String.class);

				method.invoke(_accountRemoteModel, password);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getSavePassword() {
		return _savePassword;
	}

	@Override
	public boolean isSavePassword() {
		return _savePassword;
	}

	@Override
	public void setSavePassword(boolean savePassword) {
		_savePassword = savePassword;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setSavePassword", boolean.class);

				method.invoke(_accountRemoteModel, savePassword);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getSignature() {
		return _signature;
	}

	@Override
	public void setSignature(String signature) {
		_signature = signature;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setSignature", String.class);

				method.invoke(_accountRemoteModel, signature);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getUseSignature() {
		return _useSignature;
	}

	@Override
	public boolean isUseSignature() {
		return _useSignature;
	}

	@Override
	public void setUseSignature(boolean useSignature) {
		_useSignature = useSignature;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setUseSignature", boolean.class);

				method.invoke(_accountRemoteModel, useSignature);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getFolderPrefix() {
		return _folderPrefix;
	}

	@Override
	public void setFolderPrefix(String folderPrefix) {
		_folderPrefix = folderPrefix;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setFolderPrefix", String.class);

				method.invoke(_accountRemoteModel, folderPrefix);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getInboxFolderId() {
		return _inboxFolderId;
	}

	@Override
	public void setInboxFolderId(long inboxFolderId) {
		_inboxFolderId = inboxFolderId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setInboxFolderId", long.class);

				method.invoke(_accountRemoteModel, inboxFolderId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getDraftFolderId() {
		return _draftFolderId;
	}

	@Override
	public void setDraftFolderId(long draftFolderId) {
		_draftFolderId = draftFolderId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setDraftFolderId", long.class);

				method.invoke(_accountRemoteModel, draftFolderId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getSentFolderId() {
		return _sentFolderId;
	}

	@Override
	public void setSentFolderId(long sentFolderId) {
		_sentFolderId = sentFolderId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setSentFolderId", long.class);

				method.invoke(_accountRemoteModel, sentFolderId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getTrashFolderId() {
		return _trashFolderId;
	}

	@Override
	public void setTrashFolderId(long trashFolderId) {
		_trashFolderId = trashFolderId;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setTrashFolderId", long.class);

				method.invoke(_accountRemoteModel, trashFolderId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getDefaultSender() {
		return _defaultSender;
	}

	@Override
	public boolean isDefaultSender() {
		return _defaultSender;
	}

	@Override
	public void setDefaultSender(boolean defaultSender) {
		_defaultSender = defaultSender;

		if (_accountRemoteModel != null) {
			try {
				Class<?> clazz = _accountRemoteModel.getClass();

				Method method = clazz.getMethod("setDefaultSender",
						boolean.class);

				method.invoke(_accountRemoteModel, defaultSender);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public void setPasswordDecrypted(java.lang.String unencryptedPassword) {
		try {
			String methodName = "setPasswordDecrypted";

			Class<?>[] parameterTypes = new Class<?>[] { java.lang.String.class };

			Object[] parameterValues = new Object[] { unencryptedPassword };

			invokeOnRemoteModel(methodName, parameterTypes, parameterValues);
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	@Override
	public java.lang.String getPasswordDecrypted() {
		try {
			String methodName = "getPasswordDecrypted";

			Class<?>[] parameterTypes = new Class<?>[] {  };

			Object[] parameterValues = new Object[] {  };

			java.lang.String returnObj = (java.lang.String)invokeOnRemoteModel(methodName,
					parameterTypes, parameterValues);

			return returnObj;
		}
		catch (Exception e) {
			throw new UnsupportedOperationException(e);
		}
	}

	public BaseModel<?> getAccountRemoteModel() {
		return _accountRemoteModel;
	}

	public void setAccountRemoteModel(BaseModel<?> accountRemoteModel) {
		_accountRemoteModel = accountRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _accountRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_accountRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
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

	@Override
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
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountClp)) {
			return false;
		}

		AccountClp account = (AccountClp)obj;

		long primaryKey = account.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
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

	@Override
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
	private Class<?> _clpSerializerClass = com.liferay.mail.service.ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}