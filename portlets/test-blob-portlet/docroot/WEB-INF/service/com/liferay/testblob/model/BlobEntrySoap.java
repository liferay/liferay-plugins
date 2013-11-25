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

import java.io.Serializable;

import java.sql.Blob;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class BlobEntrySoap implements Serializable {
	public static BlobEntrySoap toSoapModel(BlobEntry model) {
		BlobEntrySoap soapModel = new BlobEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setTestBlobEntryId(model.getTestBlobEntryId());
		soapModel.setBlobField(model.getBlobField());

		return soapModel;
	}

	public static BlobEntrySoap[] toSoapModels(BlobEntry[] models) {
		BlobEntrySoap[] soapModels = new BlobEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static BlobEntrySoap[][] toSoapModels(BlobEntry[][] models) {
		BlobEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new BlobEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new BlobEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static BlobEntrySoap[] toSoapModels(List<BlobEntry> models) {
		List<BlobEntrySoap> soapModels = new ArrayList<BlobEntrySoap>(models.size());

		for (BlobEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new BlobEntrySoap[soapModels.size()]);
	}

	public BlobEntrySoap() {
	}

	public long getPrimaryKey() {
		return _testBlobEntryId;
	}

	public void setPrimaryKey(long pk) {
		setTestBlobEntryId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getTestBlobEntryId() {
		return _testBlobEntryId;
	}

	public void setTestBlobEntryId(long testBlobEntryId) {
		_testBlobEntryId = testBlobEntryId;
	}

	public Blob getBlobField() {
		return _blobField;
	}

	public void setBlobField(Blob blobField) {
		_blobField = blobField;
	}

	private String _uuid;
	private long _testBlobEntryId;
	private Blob _blobField;
}