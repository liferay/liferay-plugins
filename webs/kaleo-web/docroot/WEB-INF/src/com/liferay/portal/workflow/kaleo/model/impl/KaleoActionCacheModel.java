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
import com.liferay.portal.workflow.kaleo.model.KaleoAction;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing KaleoAction in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see KaleoAction
 * @generated
 */
@ProviderType
public class KaleoActionCacheModel implements CacheModel<KaleoAction>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoActionCacheModel)) {
			return false;
		}

		KaleoActionCacheModel kaleoActionCacheModel = (KaleoActionCacheModel)obj;

		if (kaleoActionId == kaleoActionCacheModel.kaleoActionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, kaleoActionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(37);

		sb.append("{kaleoActionId=");
		sb.append(kaleoActionId);
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
		sb.append(", script=");
		sb.append(script);
		sb.append(", scriptLanguage=");
		sb.append(scriptLanguage);
		sb.append(", scriptRequiredContexts=");
		sb.append(scriptRequiredContexts);
		sb.append(", priority=");
		sb.append(priority);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public KaleoAction toEntityModel() {
		KaleoActionImpl kaleoActionImpl = new KaleoActionImpl();

		kaleoActionImpl.setKaleoActionId(kaleoActionId);
		kaleoActionImpl.setGroupId(groupId);
		kaleoActionImpl.setCompanyId(companyId);
		kaleoActionImpl.setUserId(userId);

		if (userName == null) {
			kaleoActionImpl.setUserName(StringPool.BLANK);
		}
		else {
			kaleoActionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			kaleoActionImpl.setCreateDate(null);
		}
		else {
			kaleoActionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			kaleoActionImpl.setModifiedDate(null);
		}
		else {
			kaleoActionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (kaleoClassName == null) {
			kaleoActionImpl.setKaleoClassName(StringPool.BLANK);
		}
		else {
			kaleoActionImpl.setKaleoClassName(kaleoClassName);
		}

		kaleoActionImpl.setKaleoClassPK(kaleoClassPK);
		kaleoActionImpl.setKaleoDefinitionId(kaleoDefinitionId);

		if (kaleoNodeName == null) {
			kaleoActionImpl.setKaleoNodeName(StringPool.BLANK);
		}
		else {
			kaleoActionImpl.setKaleoNodeName(kaleoNodeName);
		}

		if (name == null) {
			kaleoActionImpl.setName(StringPool.BLANK);
		}
		else {
			kaleoActionImpl.setName(name);
		}

		if (description == null) {
			kaleoActionImpl.setDescription(StringPool.BLANK);
		}
		else {
			kaleoActionImpl.setDescription(description);
		}

		if (executionType == null) {
			kaleoActionImpl.setExecutionType(StringPool.BLANK);
		}
		else {
			kaleoActionImpl.setExecutionType(executionType);
		}

		if (script == null) {
			kaleoActionImpl.setScript(StringPool.BLANK);
		}
		else {
			kaleoActionImpl.setScript(script);
		}

		if (scriptLanguage == null) {
			kaleoActionImpl.setScriptLanguage(StringPool.BLANK);
		}
		else {
			kaleoActionImpl.setScriptLanguage(scriptLanguage);
		}

		if (scriptRequiredContexts == null) {
			kaleoActionImpl.setScriptRequiredContexts(StringPool.BLANK);
		}
		else {
			kaleoActionImpl.setScriptRequiredContexts(scriptRequiredContexts);
		}

		kaleoActionImpl.setPriority(priority);

		kaleoActionImpl.resetOriginalValues();

		return kaleoActionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		kaleoActionId = objectInput.readLong();
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
		script = objectInput.readUTF();
		scriptLanguage = objectInput.readUTF();
		scriptRequiredContexts = objectInput.readUTF();
		priority = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(kaleoActionId);
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

		if (script == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(script);
		}

		if (scriptLanguage == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scriptLanguage);
		}

		if (scriptRequiredContexts == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(scriptRequiredContexts);
		}

		objectOutput.writeInt(priority);
	}

	public long kaleoActionId;
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
	public String script;
	public String scriptLanguage;
	public String scriptRequiredContexts;
	public int priority;
}