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

package com.liferay.ams.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.ams.model.Definition;

import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Definition in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Definition
 * @generated
 */
@ProviderType
public class DefinitionCacheModel implements CacheModel<Definition>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof DefinitionCacheModel)) {
			return false;
		}

		DefinitionCacheModel definitionCacheModel = (DefinitionCacheModel)obj;

		if (definitionId == definitionCacheModel.definitionId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, definitionId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(27);

		sb.append("{definitionId=");
		sb.append(definitionId);
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
		sb.append(", typeId=");
		sb.append(typeId);
		sb.append(", manufacturer=");
		sb.append(manufacturer);
		sb.append(", model=");
		sb.append(model);
		sb.append(", orderDate=");
		sb.append(orderDate);
		sb.append(", quantity=");
		sb.append(quantity);
		sb.append(", price=");
		sb.append(price);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Definition toEntityModel() {
		DefinitionImpl definitionImpl = new DefinitionImpl();

		definitionImpl.setDefinitionId(definitionId);
		definitionImpl.setGroupId(groupId);
		definitionImpl.setCompanyId(companyId);
		definitionImpl.setUserId(userId);

		if (userName == null) {
			definitionImpl.setUserName(StringPool.BLANK);
		}
		else {
			definitionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			definitionImpl.setCreateDate(null);
		}
		else {
			definitionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			definitionImpl.setModifiedDate(null);
		}
		else {
			definitionImpl.setModifiedDate(new Date(modifiedDate));
		}

		definitionImpl.setTypeId(typeId);

		if (manufacturer == null) {
			definitionImpl.setManufacturer(StringPool.BLANK);
		}
		else {
			definitionImpl.setManufacturer(manufacturer);
		}

		if (model == null) {
			definitionImpl.setModel(StringPool.BLANK);
		}
		else {
			definitionImpl.setModel(model);
		}

		if (orderDate == Long.MIN_VALUE) {
			definitionImpl.setOrderDate(null);
		}
		else {
			definitionImpl.setOrderDate(new Date(orderDate));
		}

		definitionImpl.setQuantity(quantity);
		definitionImpl.setPrice(price);

		definitionImpl.resetOriginalValues();

		return definitionImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		definitionId = objectInput.readLong();
		groupId = objectInput.readLong();
		companyId = objectInput.readLong();
		userId = objectInput.readLong();
		userName = objectInput.readUTF();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		typeId = objectInput.readLong();
		manufacturer = objectInput.readUTF();
		model = objectInput.readUTF();
		orderDate = objectInput.readLong();
		quantity = objectInput.readInt();
		price = objectInput.readDouble();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(definitionId);
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
		objectOutput.writeLong(typeId);

		if (manufacturer == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(manufacturer);
		}

		if (model == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(model);
		}

		objectOutput.writeLong(orderDate);
		objectOutput.writeInt(quantity);
		objectOutput.writeDouble(price);
	}

	public long definitionId;
	public long groupId;
	public long companyId;
	public long userId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public long typeId;
	public String manufacturer;
	public String model;
	public long orderDate;
	public int quantity;
	public double price;
}