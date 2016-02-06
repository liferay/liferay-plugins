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

package com.liferay.testblob.model.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.util.HashUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;

import com.liferay.testblob.model.TestBlobEntry;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

/**
 * The cache model class for representing TestBlobEntry in entity cache.
 *
 * @author Brian Wing Shun Chan
 * @see TestBlobEntry
 * @generated
 */
@ProviderType
public class TestBlobEntryCacheModel implements CacheModel<TestBlobEntry>,
	Externalizable {
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestBlobEntryCacheModel)) {
			return false;
		}

		TestBlobEntryCacheModel testBlobEntryCacheModel = (TestBlobEntryCacheModel)obj;

		if (testBlobEntryId == testBlobEntryCacheModel.testBlobEntryId) {
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		return HashUtil.hash(0, testBlobEntryId);
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(5);

		sb.append("{uuid=");
		sb.append(uuid);
		sb.append(", testBlobEntryId=");
		sb.append(testBlobEntryId);

		return sb.toString();
	}

	@Override
	public TestBlobEntry toEntityModel() {
		TestBlobEntryImpl testBlobEntryImpl = new TestBlobEntryImpl();

		if (uuid == null) {
			testBlobEntryImpl.setUuid(StringPool.BLANK);
		}
		else {
			testBlobEntryImpl.setUuid(uuid);
		}

		testBlobEntryImpl.setTestBlobEntryId(testBlobEntryId);

		testBlobEntryImpl.resetOriginalValues();

		return testBlobEntryImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		uuid = objectInput.readUTF();

		testBlobEntryId = objectInput.readLong();
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

		objectOutput.writeLong(testBlobEntryId);
	}

	public String uuid;
	public long testBlobEntryId;
}