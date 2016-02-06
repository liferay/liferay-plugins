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

package com.liferay.opensocial.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.opensocial.model.Gadget;

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
 * The cache model class for representing Gadget in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Gadget
 * @generated
 */
@ProviderType
public class GadgetCacheModel implements CacheModel<Gadget>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof GadgetCacheModel)) {
			return false;
		}

		GadgetCacheModel gadgetCacheModel = (GadgetCacheModel)obj;

		if (gadgetId == gadgetCacheModel.gadgetId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, gadgetId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(19);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", gadgetId=");
		sb.append(gadgetId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", url=");
		sb.append(url);
		sb.append(", portletCategoryNames=");
		sb.append(portletCategoryNames);
		sb.append(", lastPublishDate=");
		sb.append(lastPublishDate);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Gadget toEntityModel() {
		GadgetImpl gadgetImpl = new GadgetImpl();

		if (uuid == null) {
			gadgetImpl.setUuid(StringPool.BLANK);
		}
		else {
			gadgetImpl.setUuid(uuid);
		}

		gadgetImpl.setGadgetId(gadgetId);
		gadgetImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			gadgetImpl.setCreateDate(null);
		}
		else {
			gadgetImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			gadgetImpl.setModifiedDate(null);
		}
		else {
			gadgetImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			gadgetImpl.setName(StringPool.BLANK);
		}
		else {
			gadgetImpl.setName(name);
		}

		if (url == null) {
			gadgetImpl.setUrl(StringPool.BLANK);
		}
		else {
			gadgetImpl.setUrl(url);
		}

		if (portletCategoryNames == null) {
			gadgetImpl.setPortletCategoryNames(StringPool.BLANK);
		}
		else {
			gadgetImpl.setPortletCategoryNames(portletCategoryNames);
		}

		if (lastPublishDate == Long.MIN_VALUE) {
			gadgetImpl.setLastPublishDate(null);
		}
		else {
			gadgetImpl.setLastPublishDate(new Date(lastPublishDate));
		}

		gadgetImpl.resetOriginalValues();

		return gadgetImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		gadgetId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		name = objectInput.readUTF();
		url = objectInput.readUTF();
		portletCategoryNames = objectInput.readUTF();
		lastPublishDate = objectInput.readLong();
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

		objectOutput.writeLong(gadgetId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}

		if (url == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(url);
		}

		if (portletCategoryNames == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(portletCategoryNames);
		}

		objectOutput.writeLong(lastPublishDate);
	}

	public String uuid;
	public long gadgetId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String url;
	public String portletCategoryNames;
	public long lastPublishDate;
}