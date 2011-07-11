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

package com.liferay.mail.model.impl;

import com.liferay.mail.model.Account;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.util.Date;

/**
 * The cache model class for representing Account in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Account
 * @generated
 */
public class AccountCacheModel implements CacheModel<Account> {
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