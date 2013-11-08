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

package com.liferay.testblob.model;

import java.sql.Blob;

/**
 * The Blob model class for lazy loading the blobField column in BlobEntry.
 *
 * @author Brian Wing Shun Chan
 * @see BlobEntry
 * @generated
 */
public class BlobEntryBlobFieldBlobModel {
	public BlobEntryBlobFieldBlobModel() {
	}

	public BlobEntryBlobFieldBlobModel(long blobEntryId) {
		_blobEntryId = blobEntryId;
	}

	public BlobEntryBlobFieldBlobModel(long blobEntryId, Blob blobFieldBlob) {
		_blobEntryId = blobEntryId;
		_blobFieldBlob = blobFieldBlob;
	}

	public long getBlobEntryId() {
		return _blobEntryId;
	}

	public void setBlobEntryId(long blobEntryId) {
		_blobEntryId = blobEntryId;
	}

	public Blob getBlobFieldBlob() {
		return _blobFieldBlob;
	}

	public void setBlobFieldBlob(Blob blobFieldBlob) {
		_blobFieldBlob = blobFieldBlob;
	}

	private long _blobEntryId;
	private Blob _blobFieldBlob;
}