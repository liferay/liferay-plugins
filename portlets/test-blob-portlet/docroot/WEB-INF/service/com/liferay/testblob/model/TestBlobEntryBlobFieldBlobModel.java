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

package com.liferay.testblob.model;

import aQute.bnd.annotation.ProviderType;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the blobField column in TestBlobEntry.
 *
 * @author Brian Wing Shun Chan
 * @see TestBlobEntry
 * @generated
 */
@ProviderType
public class TestBlobEntryBlobFieldBlobModel {
	public TestBlobEntryBlobFieldBlobModel() {
	}

	public TestBlobEntryBlobFieldBlobModel(long testBlobEntryId) {
		_testBlobEntryId = testBlobEntryId;
	}

	public TestBlobEntryBlobFieldBlobModel(long testBlobEntryId,
		Blob blobFieldBlob) {
		_testBlobEntryId = testBlobEntryId;
		_blobFieldBlob = blobFieldBlob;
	}

	public long getTestBlobEntryId() {
		return _testBlobEntryId;
	}

	public void setTestBlobEntryId(long testBlobEntryId) {
		_testBlobEntryId = testBlobEntryId;
	}

	public Blob getBlobFieldBlob() {
		return _blobFieldBlob;
	}

	public void setBlobFieldBlob(Blob blobFieldBlob) {
		_blobFieldBlob = blobFieldBlob;
	}

	private long _testBlobEntryId;
	private Blob _blobFieldBlob;
}