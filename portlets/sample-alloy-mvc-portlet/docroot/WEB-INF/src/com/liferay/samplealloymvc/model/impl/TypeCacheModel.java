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

package com.liferay.samplealloymvc.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.samplealloymvc.model.Type;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing Type in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see Type
 * @generated
 */
@ProviderType
public class TypeCacheModel implements CacheModel<Type>, Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TypeCacheModel)) {
			return false;
		}

		TypeCacheModel typeCacheModel = (TypeCacheModel)obj;

		if (typeId == typeCacheModel.typeId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, typeId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(7);

		sb.append("{typeId=");
		sb.append(typeId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", name=");
		sb.append(name);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Type toEntityModel() {
		TypeImpl typeImpl = new TypeImpl();

		typeImpl.setTypeId(typeId);
		typeImpl.setGroupId(groupId);

		if (name == null) {
			typeImpl.setName(StringPool.BLANK);
		}
		else {
			typeImpl.setName(name);
		}

		typeImpl.resetOriginalValues();

		return typeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		typeId = objectInput.readLong();

		groupId = objectInput.readLong();
		name = objectInput.readUTF();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(typeId);

		objectOutput.writeLong(groupId);

		if (name == null) {
			objectOutput.writeUTF(StringPool.BLANK);
		}
		else {
			objectOutput.writeUTF(name);
		}
	}

	public long typeId;
	public long groupId;
	public String name;
}