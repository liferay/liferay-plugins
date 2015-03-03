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

package com.liferay.portal.workflow.kaleo.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;
import com.liferay.portal.workflow.kaleo.model.KaleoNotificationRecipient;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoNotificationRecipient in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotificationRecipient
 * @generated
 */
@ProviderType
public class KaleoNotificationRecipientCacheModel implements CacheModel<KaleoNotificationRecipient>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoNotificationRecipientCacheModel)) {
			return false;
		}

		KaleoNotificationRecipientCacheModel kaleoNotificationRecipientCacheModel =
			(KaleoNotificationRecipientCacheModel)obj;

		if (kaleoNotificationRecipientId == kaleoNotificationRecipientCacheModel.kaleoNotificationRecipientId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoNotificationRecipientId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoNotificationRecipientId=");
		sb.append(kaleoNotificationRecipientId);
		sb.append(", groupId=");
		sb.append(groupId);
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
		sb.append(", kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
		sb.append(", kaleoNotificationId=");
		sb.append(kaleoNotificationId);
		sb.append(", recipientClassName=");
		sb.append(recipientClassName);
		sb.append(", recipientClassPK=");
		sb.append(recipientClassPK);
		sb.append(", recipientRoleType=");
		sb.append(recipientRoleType);
		sb.append(", recipientScript=");
		sb.append(recipientScript);
		sb.append(", recipientScriptLanguage=");
		sb.append(recipientScriptLanguage);
		sb.append(", recipientScriptRequiredContexts=");
		sb.append(recipientScriptRequiredContexts);
		sb.append(", address=");
		sb.append(address);
		sb.append(", notificationReceptionType=");
		sb.append(notificationReceptionType);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoNotificationRecipient toEntityModel() {
		KaleoNotificationRecipientImpl kaleoNotificationRecipientImpl = new KaleoNotificationRecipientImpl();

		kaleoNotificationRecipientImpl.setKaleoNotificationRecipientId(kaleoNotificationRecipientId);
		kaleoNotificationRecipientImpl.setGroupId(groupId);
		kaleoNotificationRecipientImpl.setCompanyId(companyId);
		kaleoNotificationRecipientImpl.setUserId(userId);

		if (userName == null) {
			kaleoNotificationRecipientImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoNotificationRecipientImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoNotificationRecipientImpl.setCreateDate(null);
		}
		else {
			kaleoNotificationRecipientImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoNotificationRecipientImpl.setModifiedDate(null);
		}
		else {
			kaleoNotificationRecipientImpl.setModifiedDate(new Date(
					modifiedDate));
		}

		kaleoNotificationRecipientImpl.setKaleoDefinitionId(kaleoDefinitionId);
		kaleoNotificationRecipientImpl.setKaleoNotificationId(kaleoNotificationId);

		if (recipientClassName == null) {
			kaleoNotificationRecipientImpl.setRecipientClassName(StringPool.BLANK);
		}
		else {
			kaleoNotificationRecipientImpl.setRecipientClassName(recipientClassName);
		}

		kaleoNotificationRecipientImpl.setRecipientClassPK(recipientClassPK);
		kaleoNotificationRecipientImpl.setRecipientRoleType(recipientRoleType);

		if (recipientScript == null) {
			kaleoNotificationRecipientImpl.setRecipientScript(StringPool.BLANK);
		}
		else {
			kaleoNotificationRecipientImpl.setRecipientScript(recipientScript);
		}

		if (recipientScriptLanguage == null) {
			kaleoNotificationRecipientImpl.setRecipientScriptLanguage(StringPool.BLANK);
		}
		else {
			kaleoNotificationRecipientImpl.setRecipientScriptLanguage(recipientScriptLanguage);
		}

		if (recipientScriptRequiredContexts == null) {
			kaleoNotificationRecipientImpl.setRecipientScriptRequiredContexts(StringPool.BLANK);
		}
		else {
			kaleoNotificationRecipientImpl.setRecipientScriptRequiredContexts(recipientScriptRequiredContexts);
		}

		if (address == null) {
			kaleoNotificationRecipientImpl.setAddress(StringPool.BLANK);
		}
		else {
			kaleoNotificationRecipientImpl.setAddress(address);
		}

		if (notificationReceptionType == null) {
			kaleoNotificationRecipientImpl.setNotificationReceptionType(StringPool.BLANK);
		}
		else {
			kaleoNotificationRecipientImpl.setNotificationReceptionType(notificationReceptionType);
		}

		kaleoNotificationRecipientImpl.resetOriginalValues();

		return kaleoNotificationRecipientImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoNotificationRecipientId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		kaleoDefinitionId = objectInput.readLong();
		kaleoNotificationId = objectInput.readLong();
		recipientClassName = objectInput.readUTF();
		recipientClassPK = objectInput.readLong();
		recipientRoleType = objectInput.readInt();
		recipientScript = objectInput.readUTF();
		recipientScriptLanguage = objectInput.readUTF();
		recipientScriptRequiredContexts = objectInput.readUTF();
		address = objectInput.readUTF();
		notificationReceptionType = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoNotificationRecipientId);
		objectOutput.writeLong(groupId);
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
		objectOutput.writeLong(kaleoDefinitionId);
		objectOutput.writeLong(kaleoNotificationId);

		if (recipientClassName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recipientClassName);
		}

		objectOutput.writeLong(recipientClassPK);
		objectOutput.writeInt(recipientRoleType);

		if (recipientScript == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recipientScript);
		}

		if (recipientScriptLanguage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recipientScriptLanguage);
		}

		if (recipientScriptRequiredContexts == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(recipientScriptRequiredContexts);
		}

		if (address == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(address);
		}

		if (notificationReceptionType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notificationReceptionType);
		}
	}

	public long kaleoNotificationRecipientId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long kaleoDefinitionId;
	public long kaleoNotificationId;
	public String recipientClassName;
	public long recipientClassPK;
	public int recipientRoleType;
	public String recipientScript;
	public String recipientScriptLanguage;
	public String recipientScriptRequiredContexts;
	public String address;
	public String notificationReceptionType;
}