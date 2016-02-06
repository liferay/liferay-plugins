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

package com.liferay.mail.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.mail.model.Account;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Account in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Account
 * @generated
 */
@ProviderType
public class AccountCacheModel implements CacheModel<Account>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AccountCacheModel)) {
			return false;
		}

		AccountCacheModel accountCacheModel = (AccountCacheModel)obj;

		if (accountId == accountCacheModel.accountId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, accountId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(53);

		sb.append("{accountId=");
		sb.append(accountId);
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
		sb.append(", address=");
		sb.append(address);
		sb.append(", personalName=");
		sb.append(personalName);
		sb.append(", protocol=");
		sb.append(protocol);
		sb.append(", incomingHostName=");
		sb.append(incomingHostName);
		sb.append(", incomingPort=");
		sb.append(incomingPort);
		sb.append(", incomingSecure=");
		sb.append(incomingSecure);
		sb.append(", outgoingHostName=");
		sb.append(outgoingHostName);
		sb.append(", outgoingPort=");
		sb.append(outgoingPort);
		sb.append(", outgoingSecure=");
		sb.append(outgoingSecure);
		sb.append(", login=");
		sb.append(login);
		sb.append(", password=");
		sb.append(password);
		sb.append(", savePassword=");
		sb.append(savePassword);
		sb.append(", signature=");
		sb.append(signature);
		sb.append(", useSignature=");
		sb.append(useSignature);
		sb.append(", folderPrefix=");
		sb.append(folderPrefix);
		sb.append(", inboxFolderId=");
		sb.append(inboxFolderId);
		sb.append(", draftFolderId=");
		sb.append(draftFolderId);
		sb.append(", sentFolderId=");
		sb.append(sentFolderId);
		sb.append(", trashFolderId=");
		sb.append(trashFolderId);
		sb.append(", defaultSender=");
		sb.append(defaultSender);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Account toEntityModel() {
		AccountImpl accountImpl = new AccountImpl();

		accountImpl.setAccountId(accountId);
		accountImpl.setCompanyId(companyId);
		accountImpl.setUserId(userId);

		if (userName == null) {
			accountImpl.setUserName(StringPool.BLANK);
		}
		else {
			accountImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			accountImpl.setCreateDate(null);
		}
		else {
			accountImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			accountImpl.setModifiedDate(null);
		}
		else {
			accountImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (address == null) {
			accountImpl.setAddress(StringPool.BLANK);
		}
		else {
			accountImpl.setAddress(address);
		}

		if (personalName == null) {
			accountImpl.setPersonalName(StringPool.BLANK);
		}
		else {
			accountImpl.setPersonalName(personalName);
		}

		if (protocol == null) {
			accountImpl.setProtocol(StringPool.BLANK);
		}
		else {
			accountImpl.setProtocol(protocol);
		}

		if (incomingHostName == null) {
			accountImpl.setIncomingHostName(StringPool.BLANK);
		}
		else {
			accountImpl.setIncomingHostName(incomingHostName);
		}

		accountImpl.setIncomingPort(incomingPort);
		accountImpl.setIncomingSecure(incomingSecure);

		if (outgoingHostName == null) {
			accountImpl.setOutgoingHostName(StringPool.BLANK);
		}
		else {
			accountImpl.setOutgoingHostName(outgoingHostName);
		}

		accountImpl.setOutgoingPort(outgoingPort);
		accountImpl.setOutgoingSecure(outgoingSecure);

		if (login == null) {
			accountImpl.setLogin(StringPool.BLANK);
		}
		else {
			accountImpl.setLogin(login);
		}

		if (password == null) {
			accountImpl.setPassword(StringPool.BLANK);
		}
		else {
			accountImpl.setPassword(password);
		}

		accountImpl.setSavePassword(savePassword);

		if (signature == null) {
			accountImpl.setSignature(StringPool.BLANK);
		}
		else {
			accountImpl.setSignature(signature);
		}

		accountImpl.setUseSignature(useSignature);

		if (folderPrefix == null) {
			accountImpl.setFolderPrefix(StringPool.BLANK);
		}
		else {
			accountImpl.setFolderPrefix(folderPrefix);
		}

		accountImpl.setInboxFolderId(inboxFolderId);
		accountImpl.setDraftFolderId(draftFolderId);
		accountImpl.setSentFolderId(sentFolderId);
		accountImpl.setTrashFolderId(trashFolderId);
		accountImpl.setDefaultSender(defaultSender);

		accountImpl.resetOriginalValues();

		return accountImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		accountId = objectInput.readLong();

		companyId = objectInput.readLong();

		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		address = objectInput.readUTF();
		personalName = objectInput.readUTF();
		protocol = objectInput.readUTF();
		incomingHostName = objectInput.readUTF();

		incomingPort = objectInput.readInt();

		incomingSecure = objectInput.readBoolean();
		outgoingHostName = objectInput.readUTF();

		outgoingPort = objectInput.readInt();

		outgoingSecure = objectInput.readBoolean();
		login = objectInput.readUTF();
		password = objectInput.readUTF();

		savePassword = objectInput.readBoolean();
		signature = objectInput.readUTF();

		useSignature = objectInput.readBoolean();
		folderPrefix = objectInput.readUTF();

		inboxFolderId = objectInput.readLong();

		draftFolderId = objectInput.readLong();

		sentFolderId = objectInput.readLong();

		trashFolderId = objectInput.readLong();

		defaultSender = objectInput.readBoolean();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(accountId);

		objectOutput.writeLong(companyId);

		objectOutput.writeLong(userId);

		if (userName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(userName);
		}

		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (address == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (personalName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(personalName);
		}

		if (protocol == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(protocol);
		}

		if (incomingHostName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(incomingHostName);
		}

		objectOutput.writeInt(incomingPort);

		objectOutput.writeBoolean(incomingSecure);

		if (outgoingHostName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(outgoingHostName);
		}

		objectOutput.writeInt(outgoingPort);

		objectOutput.writeBoolean(outgoingSecure);

		if (login == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(login);
		}

		if (password == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(password);
		}

		objectOutput.writeBoolean(savePassword);

		if (signature == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(signature);
		}

		objectOutput.writeBoolean(useSignature);

		if (folderPrefix == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(folderPrefix);
		}

		objectOutput.writeLong(inboxFolderId);

		objectOutput.writeLong(draftFolderId);

		objectOutput.writeLong(sentFolderId);

		objectOutput.writeLong(trashFolderId);

		objectOutput.writeBoolean(defaultSender);
	}

	public long accountId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String address;
	public String personalName;
	public String protocol;
	public String incomingHostName;
	public int incomingPort;
	public boolean incomingSecure;
	public String outgoingHostName;
	public int outgoingPort;
	public boolean outgoingSecure;
	public String login;
	public String password;
	public boolean savePassword;
	public String signature;
	public boolean useSignature;
	public String folderPrefix;
	public long inboxFolderId;
	public long draftFolderId;
	public long sentFolderId;
	public long trashFolderId;
	public boolean defaultSender;
}