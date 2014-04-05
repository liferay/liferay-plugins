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

package com.liferay.sync.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class SyncDLFileVersionDiffSoap implements Serializable {
	public static SyncDLFileVersionDiffSoap toSoapModel(
		SyncDLFileVersionDiff model) {
		SyncDLFileVersionDiffSoap soapModel = new SyncDLFileVersionDiffSoap();

		soapModel.setSyncDLFileVersionDiffId(model.getSyncDLFileVersionDiffId());
		soapModel.setFileEntryId(model.getFileEntryId());
		soapModel.setSourceFileVersionId(model.getSourceFileVersionId());
		soapModel.setTargetFileVersionId(model.getTargetFileVersionId());
		soapModel.setDataFileEntryId(model.getDataFileEntryId());
		soapModel.setSize(model.getSize());
		soapModel.setExpirationDate(model.getExpirationDate());

		return soapModel;
	}

	public static SyncDLFileVersionDiffSoap[] toSoapModels(
		SyncDLFileVersionDiff[] models) {
		SyncDLFileVersionDiffSoap[] soapModels = new SyncDLFileVersionDiffSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SyncDLFileVersionDiffSoap[][] toSoapModels(
		SyncDLFileVersionDiff[][] models) {
		SyncDLFileVersionDiffSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SyncDLFileVersionDiffSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SyncDLFileVersionDiffSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SyncDLFileVersionDiffSoap[] toSoapModels(
		List<SyncDLFileVersionDiff> models) {
		List<SyncDLFileVersionDiffSoap> soapModels = new ArrayList<SyncDLFileVersionDiffSoap>(models.size());

		for (SyncDLFileVersionDiff model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SyncDLFileVersionDiffSoap[soapModels.size()]);
	}

	public SyncDLFileVersionDiffSoap() {
	}

	public long getPrimaryKey() {
		return _syncDLFileVersionDiffId;
	}

	public void setPrimaryKey(long pk) {
		setSyncDLFileVersionDiffId(pk);
	}

	public long getSyncDLFileVersionDiffId() {
		return _syncDLFileVersionDiffId;
	}

	public void setSyncDLFileVersionDiffId(long syncDLFileVersionDiffId) {
		_syncDLFileVersionDiffId = syncDLFileVersionDiffId;
	}

	public long getFileEntryId() {
		return _fileEntryId;
	}

	public void setFileEntryId(long fileEntryId) {
		_fileEntryId = fileEntryId;
	}

	public long getSourceFileVersionId() {
		return _sourceFileVersionId;
	}

	public void setSourceFileVersionId(long sourceFileVersionId) {
		_sourceFileVersionId = sourceFileVersionId;
	}

	public long getTargetFileVersionId() {
		return _targetFileVersionId;
	}

	public void setTargetFileVersionId(long targetFileVersionId) {
		_targetFileVersionId = targetFileVersionId;
	}

	public long getDataFileEntryId() {
		return _dataFileEntryId;
	}

	public void setDataFileEntryId(long dataFileEntryId) {
		_dataFileEntryId = dataFileEntryId;
	}

	public long getSize() {
		return _size;
	}

	public void setSize(long size) {
		_size = size;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	private long _syncDLFileVersionDiffId;
	private long _fileEntryId;
	private long _sourceFileVersionId;
	private long _targetFileVersionId;
	private long _dataFileEntryId;
	private long _size;
	private Date _expirationDate;
}