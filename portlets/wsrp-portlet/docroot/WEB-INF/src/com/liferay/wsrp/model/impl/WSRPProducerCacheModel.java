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

package com.liferay.wsrp.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.wsrp.model.WSRPProducer;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing WSRPProducer in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see WSRPProducer
 * @generated
 */
@ProviderType
public class WSRPProducerCacheModel implements CacheModel<WSRPProducer>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof WSRPProducerCacheModel)) {
			return false;
		}

		WSRPProducerCacheModel wsrpProducerCacheModel = (WSRPProducerCacheModel)obj;

		if (wsrpProducerId == wsrpProducerCacheModel.wsrpProducerId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, wsrpProducerId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", wsrpProducerId=");
		sb.append(wsrpProducerId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", version=");
		sb.append(version);
		sb.append(", portletIds=");
		sb.append(portletIds);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public WSRPProducer toEntityModel() {
		WSRPProducerImpl wsrpProducerImpl = new WSRPProducerImpl();

		if (uuid == null) {
			wsrpProducerImpl.setUuid(StringPool.BLANK);
		}
		else {
			wsrpProducerImpl.setUuid(uuid);
		}

		wsrpProducerImpl.setWsrpProducerId(wsrpProducerId);
		wsrpProducerImpl.setGroupId(groupId);
		wsrpProducerImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			wsrpProducerImpl.setCreateDate(null);
		}
		else {
			wsrpProducerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			wsrpProducerImpl.setModifiedDate(null);
		}
		else {
			wsrpProducerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			wsrpProducerImpl.setName(StringPool.BLANK);
		}
		else {
			wsrpProducerImpl.setName(name);
		}

		if (version == null) {
			wsrpProducerImpl.setVersion(StringPool.BLANK);
		}
		else {
			wsrpProducerImpl.setVersion(version);
		}

		if (portletIds == null) {
			wsrpProducerImpl.setPortletIds(StringPool.BLANK);
		}
		else {
			wsrpProducerImpl.setPortletIds(portletIds);
		}

		wsrpProducerImpl.resetOriginalValues();

		return wsrpProducerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		wsrpProducerId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		version = objectInput.readUTF();
		portletIds = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		if (uuid == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(wsrpProducerId);
		objectOutput.writeLong(groupId);
		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (version == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(version);
		}

		if (portletIds == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(portletIds);
		}
	}

	public String uuid;
	public long wsrpProducerId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String version;
	public String portletIds;
}