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
import com.liferay.portal.workflow.kaleo.model.KaleoNotification;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoNotification in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoNotification
 * @generated
 */
@ProviderType
public class KaleoNotificationCacheModel implements CacheModel<KaleoNotification>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoNotificationCacheModel)) {
			return false;
		}

		KaleoNotificationCacheModel kaleoNotificationCacheModel = (KaleoNotificationCacheModel)obj;

		if (kaleoNotificationId == kaleoNotificationCacheModel.kaleoNotificationId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoNotificationId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(35);

		sb.append("{kaleoNotificationId=");
		sb.append(kaleoNotificationId);
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
		sb.append(", kaleoClassName=");
		sb.append(kaleoClassName);
		sb.append(", kaleoClassPK=");
		sb.append(kaleoClassPK);
		sb.append(", kaleoDefinitionId=");
		sb.append(kaleoDefinitionId);
		sb.append(", kaleoNodeName=");
		sb.append(kaleoNodeName);
		sb.append(", name=");
		sb.append(name);
		sb.append(", description=");
		sb.append(description);
		sb.append(", executionType=");
		sb.append(executionType);
		sb.append(", template=");
		sb.append(template);
		sb.append(", templateLanguage=");
		sb.append(templateLanguage);
		sb.append(", notificationTypes=");
		sb.append(notificationTypes);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoNotification toEntityModel() {
		KaleoNotificationImpl kaleoNotificationImpl = new KaleoNotificationImpl();

		kaleoNotificationImpl.setKaleoNotificationId(kaleoNotificationId);
		kaleoNotificationImpl.setGroupId(groupId);
		kaleoNotificationImpl.setCompanyId(companyId);
		kaleoNotificationImpl.setUserId(userId);

		if (userName == null) {
			kaleoNotificationImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoNotificationImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoNotificationImpl.setCreateDate(null);
		}
		else {
			kaleoNotificationImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoNotificationImpl.setModifiedDate(null);
		}
		else {
			kaleoNotificationImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (kaleoClassName == null) {
			kaleoNotificationImpl.setKaleoClassName(StringPool.BLANK);
		}
		else {
			kaleoNotificationImpl.setKaleoClassName(kaleoClassName);
		}

		kaleoNotificationImpl.setKaleoClassPK(kaleoClassPK);
		kaleoNotificationImpl.setKaleoDefinitionId(kaleoDefinitionId);

		if (kaleoNodeName == null) {
			kaleoNotificationImpl.setKaleoNodeName(StringPool.BLANK);
		}
		else {
			kaleoNotificationImpl.setKaleoNodeName(kaleoNodeName);
		}

		if (name == null) {
			kaleoNotificationImpl.setName(StringPool.BLANK);
		}
		else {
			kaleoNotificationImpl.setName(name);
		}

		if (description == null) {
			kaleoNotificationImpl.setDescription(StringPool.BLANK);
		}
		else {
			kaleoNotificationImpl.setDescription(description);
		}

		if (executionType == null) {
			kaleoNotificationImpl.setExecutionType(StringPool.BLANK);
		}
		else {
			kaleoNotificationImpl.setExecutionType(executionType);
		}

		if (template == null) {
			kaleoNotificationImpl.setTemplate(StringPool.BLANK);
		}
		else {
			kaleoNotificationImpl.setTemplate(template);
		}

		if (templateLanguage == null) {
			kaleoNotificationImpl.setTemplateLanguage(StringPool.BLANK);
		}
		else {
			kaleoNotificationImpl.setTemplateLanguage(templateLanguage);
		}

		if (notificationTypes == null) {
			kaleoNotificationImpl.setNotificationTypes(StringPool.BLANK);
		}
		else {
			kaleoNotificationImpl.setNotificationTypes(notificationTypes);
		}

		kaleoNotificationImpl.resetOriginalValues();

		return kaleoNotificationImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoNotificationId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		kaleoClassName = objectInput.readUTF();
		kaleoClassPK = objectInput.readLong();
		kaleoDefinitionId = objectInput.readLong();
		kaleoNodeName = objectInput.readUTF();
		name = objectInput.readUTF();
		description = objectInput.readUTF();
		executionType = objectInput.readUTF();
		template = objectInput.readUTF();
		templateLanguage = objectInput.readUTF();
		notificationTypes = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoNotificationId);
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

		if (kaleoClassName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(kaleoClassName);
		}

		objectOutput.writeLong(kaleoClassPK);
		objectOutput.writeLong(kaleoDefinitionId);

		if (kaleoNodeName == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(kaleoNodeName);
		}

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (description == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(description);
		}

		if (executionType == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(executionType);
		}

		if (template == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(template);
		}

		if (templateLanguage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(templateLanguage);
		}

		if (notificationTypes == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(notificationTypes);
		}
	}

	public long kaleoNotificationId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String kaleoClassName;
	public long kaleoClassPK;
	public long kaleoDefinitionId;
	public String kaleoNodeName;
	public String name;
	public String description;
	public String executionType;
	public String template;
	public String templateLanguage;
	public String notificationTypes;
}