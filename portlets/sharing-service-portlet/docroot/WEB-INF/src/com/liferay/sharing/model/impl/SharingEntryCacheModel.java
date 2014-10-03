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

package com.liferay.sharing.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.CacheModel;

import com.liferay.sharing.model.SharingEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing SharingEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see SharingEntry
 * @generated
 */
@ProviderType
public class SharingEntryCacheModel implements CacheModel<SharingEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{classNameId=");
		sb.append(classNameId);
		sb.append(", classPK=");
		sb.append(classPK);
		sb.append(", sharingClassNameId=");
		sb.append(sharingClassNameId);
		sb.append(", sharingClassPK=");
		sb.append(sharingClassPK);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public SharingEntry toEntityModel() {
		SharingEntryImpl sharingEntryImpl = new SharingEntryImpl();

		sharingEntryImpl.setClassNameId(classNameId);
		sharingEntryImpl.setClassPK(classPK);
		sharingEntryImpl.setSharingClassNameId(sharingClassNameId);
		sharingEntryImpl.setSharingClassPK(sharingClassPK);

		sharingEntryImpl.resetOriginalValues();

		return sharingEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		classNameId = objectInput.readLong();
		classPK = objectInput.readLong();
		sharingClassNameId = objectInput.readLong();
		sharingClassPK = objectInput.readLong();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput)
		throws IOException {
		objectOutput.writeLong(classNameId);
		objectOutput.writeLong(classPK);
		objectOutput.writeLong(sharingClassNameId);
		objectOutput.writeLong(sharingClassPK);
	}

	public long classNameId;
	public long classPK;
	public long sharingClassNameId;
	public long sharingClassPK;
}