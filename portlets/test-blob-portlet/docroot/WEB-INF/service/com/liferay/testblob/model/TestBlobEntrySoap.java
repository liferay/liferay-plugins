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
@ProviderType
public class TestBlobEntrySoap implements Serializable {
	public static TestBlobEntrySoap toSoapModel(TestBlobEntry model) {
		TestBlobEntrySoap soapModel = new TestBlobEntrySoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setTestBlobEntryId(model.getTestBlobEntryId());
		soapModel.setBlobField(model.getBlobField());

		return soapModel;
	}

	public static TestBlobEntrySoap[] toSoapModels(TestBlobEntry[] models) {
		TestBlobEntrySoap[] soapModels = new TestBlobEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static TestBlobEntrySoap[][] toSoapModels(TestBlobEntry[][] models) {
		TestBlobEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new TestBlobEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new TestBlobEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static TestBlobEntrySoap[] toSoapModels(List<TestBlobEntry> models) {
		List<TestBlobEntrySoap> soapModels = new ArrayList<TestBlobEntrySoap>(models.size());

		for (TestBlobEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new TestBlobEntrySoap[soapModels.size()]);
	}

	public TestBlobEntrySoap() {
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