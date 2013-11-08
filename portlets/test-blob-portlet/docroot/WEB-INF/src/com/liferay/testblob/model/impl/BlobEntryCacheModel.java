/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.testblob.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import com.liferay.testblob.model.BlobEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing BlobEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see BlobEntry
 * @generated
 */
public class BlobEntryCacheModel implements CacheModel<BlobEntry>,
	Externalizable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", blobEntryId=");
		sb.append(blobEntryId);

		return sb.toString();
	}

	@Override
	public BlobEntry toEntityModel() {
		BlobEntryImpl blobEntryImpl = new BlobEntryImpl();

		if (uuid == null) {
			blobEntryImpl.setUuid(StringPool.BLANK);
		}
		else {
			blobEntryImpl.setUuid(uuid);
		}

		blobEntryImpl.setBlobEntryId(blobEntryId);

		blobEntryImpl.resetOriginalValues();

		return blobEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();
		blobEntryId = objectInput.readLong();
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

		objectOutput.writeLong(blobEntryId);
	}

	public String uuid;
	public long blobEntryId;
}