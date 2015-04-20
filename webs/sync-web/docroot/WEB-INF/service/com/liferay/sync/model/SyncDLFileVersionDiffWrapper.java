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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SyncDLFileVersionDiff}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SyncDLFileVersionDiff
 * @generated
 */
@ProviderType
public class SyncDLFileVersionDiffWrapper implements SyncDLFileVersionDiff,
	ModelWrapper<SyncDLFileVersionDiff> {
	public SyncDLFileVersionDiffWrapper(
		SyncDLFileVersionDiff syncDLFileVersionDiff) {
		_syncDLFileVersionDiff = syncDLFileVersionDiff;
	}

	@Override
	public Class<?> getModelClass() {
		return SyncDLFileVersionDiff.class;
	}

	@Override
	public String getModelClassName() {
		return SyncDLFileVersionDiff.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("syncDLFileVersionDiffId", getSyncDLFileVersionDiffId());
		attributes.put("fileEntryId", getFileEntryId());
		attributes.put("sourceFileVersionId", getSourceFileVersionId());
		attributes.put("targetFileVersionId", getTargetFileVersionId());
		attributes.put("dataFileEntryId", getDataFileEntryId());
		attributes.put("size", getSize());
		attributes.put("expirationDate", getExpirationDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long syncDLFileVersionDiffId = (Long)attributes.get(
				"syncDLFileVersionDiffId");

		if (syncDLFileVersionDiffId != null) {
			setSyncDLFileVersionDiffId(syncDLFileVersionDiffId);
		}

		Long fileEntryId = (Long)attributes.get("fileEntryId");

		if (fileEntryId != null) {
			setFileEntryId(fileEntryId);
		}

		Long sourceFileVersionId = (Long)attributes.get("sourceFileVersionId");

		if (sourceFileVersionId != null) {
			setSourceFileVersionId(sourceFileVersionId);
		}

		Long targetFileVersionId = (Long)attributes.get("targetFileVersionId");

		if (targetFileVersionId != null) {
			setTargetFileVersionId(targetFileVersionId);
		}

		Long dataFileEntryId = (Long)attributes.get("dataFileEntryId");

		if (dataFileEntryId != null) {
			setDataFileEntryId(dataFileEntryId);
		}

		Long size = (Long)attributes.get("size");

		if (size != null) {
			setSize(size);
		}

		Date expirationDate = (Date)attributes.get("expirationDate");

		if (expirationDate != null) {
			setExpirationDate(expirationDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new SyncDLFileVersionDiffWrapper((SyncDLFileVersionDiff)_syncDLFileVersionDiff.clone());
	}

	@Override
	public int compareTo(
		com.liferay.sync.model.SyncDLFileVersionDiff syncDLFileVersionDiff) {
		return _syncDLFileVersionDiff.compareTo(syncDLFileVersionDiff);
	}

	/**
	* Returns the data file entry ID of this sync d l file version diff.
	*
	* @return the data file entry ID of this sync d l file version diff
	*/
	@Override
	public long getDataFileEntryId() {
		return _syncDLFileVersionDiff.getDataFileEntryId();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _syncDLFileVersionDiff.getExpandoBridge();
	}

	/**
	* Returns the expiration date of this sync d l file version diff.
	*
	* @return the expiration date of this sync d l file version diff
	*/
	@Override
	public Date getExpirationDate() {
		return _syncDLFileVersionDiff.getExpirationDate();
	}

	/**
	* Returns the file entry ID of this sync d l file version diff.
	*
	* @return the file entry ID of this sync d l file version diff
	*/
	@Override
	public long getFileEntryId() {
		return _syncDLFileVersionDiff.getFileEntryId();
	}

	/**
	* Returns the primary key of this sync d l file version diff.
	*
	* @return the primary key of this sync d l file version diff
	*/
	@Override
	public long getPrimaryKey() {
		return _syncDLFileVersionDiff.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _syncDLFileVersionDiff.getPrimaryKeyObj();
	}

	/**
	* Returns the size of this sync d l file version diff.
	*
	* @return the size of this sync d l file version diff
	*/
	@Override
	public long getSize() {
		return _syncDLFileVersionDiff.getSize();
	}

	/**
	* Returns the source file version ID of this sync d l file version diff.
	*
	* @return the source file version ID of this sync d l file version diff
	*/
	@Override
	public long getSourceFileVersionId() {
		return _syncDLFileVersionDiff.getSourceFileVersionId();
	}

	/**
	* Returns the sync d l file version diff ID of this sync d l file version diff.
	*
	* @return the sync d l file version diff ID of this sync d l file version diff
	*/
	@Override
	public long getSyncDLFileVersionDiffId() {
		return _syncDLFileVersionDiff.getSyncDLFileVersionDiffId();
	}

	/**
	* Returns the target file version ID of this sync d l file version diff.
	*
	* @return the target file version ID of this sync d l file version diff
	*/
	@Override
	public long getTargetFileVersionId() {
		return _syncDLFileVersionDiff.getTargetFileVersionId();
	}

	@Override
	public int hashCode() {
		return _syncDLFileVersionDiff.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _syncDLFileVersionDiff.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _syncDLFileVersionDiff.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _syncDLFileVersionDiff.isNew();
	}

	@Override
	public void persist() {
		_syncDLFileVersionDiff.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_syncDLFileVersionDiff.setCachedModel(cachedModel);
	}

	/**
	* Sets the data file entry ID of this sync d l file version diff.
	*
	* @param dataFileEntryId the data file entry ID of this sync d l file version diff
	*/
	@Override
	public void setDataFileEntryId(long dataFileEntryId) {
		_syncDLFileVersionDiff.setDataFileEntryId(dataFileEntryId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_syncDLFileVersionDiff.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_syncDLFileVersionDiff.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_syncDLFileVersionDiff.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the expiration date of this sync d l file version diff.
	*
	* @param expirationDate the expiration date of this sync d l file version diff
	*/
	@Override
	public void setExpirationDate(Date expirationDate) {
		_syncDLFileVersionDiff.setExpirationDate(expirationDate);
	}

	/**
	* Sets the file entry ID of this sync d l file version diff.
	*
	* @param fileEntryId the file entry ID of this sync d l file version diff
	*/
	@Override
	public void setFileEntryId(long fileEntryId) {
		_syncDLFileVersionDiff.setFileEntryId(fileEntryId);
	}

	@Override
	public void setNew(boolean n) {
		_syncDLFileVersionDiff.setNew(n);
	}

	/**
	* Sets the primary key of this sync d l file version diff.
	*
	* @param primaryKey the primary key of this sync d l file version diff
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_syncDLFileVersionDiff.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_syncDLFileVersionDiff.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the size of this sync d l file version diff.
	*
	* @param size the size of this sync d l file version diff
	*/
	@Override
	public void setSize(long size) {
		_syncDLFileVersionDiff.setSize(size);
	}

	/**
	* Sets the source file version ID of this sync d l file version diff.
	*
	* @param sourceFileVersionId the source file version ID of this sync d l file version diff
	*/
	@Override
	public void setSourceFileVersionId(long sourceFileVersionId) {
		_syncDLFileVersionDiff.setSourceFileVersionId(sourceFileVersionId);
	}

	/**
	* Sets the sync d l file version diff ID of this sync d l file version diff.
	*
	* @param syncDLFileVersionDiffId the sync d l file version diff ID of this sync d l file version diff
	*/
	@Override
	public void setSyncDLFileVersionDiffId(long syncDLFileVersionDiffId) {
		_syncDLFileVersionDiff.setSyncDLFileVersionDiffId(syncDLFileVersionDiffId);
	}

	/**
	* Sets the target file version ID of this sync d l file version diff.
	*
	* @param targetFileVersionId the target file version ID of this sync d l file version diff
	*/
	@Override
	public void setTargetFileVersionId(long targetFileVersionId) {
		_syncDLFileVersionDiff.setTargetFileVersionId(targetFileVersionId);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.sync.model.SyncDLFileVersionDiff> toCacheModel() {
		return _syncDLFileVersionDiff.toCacheModel();
	}

	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff toEscapedModel() {
		return new SyncDLFileVersionDiffWrapper(_syncDLFileVersionDiff.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _syncDLFileVersionDiff.toString();
	}

	@Override
	public com.liferay.sync.model.SyncDLFileVersionDiff toUnescapedModel() {
		return new SyncDLFileVersionDiffWrapper(_syncDLFileVersionDiff.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _syncDLFileVersionDiff.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SyncDLFileVersionDiffWrapper)) {
			return false;
		}

		SyncDLFileVersionDiffWrapper syncDLFileVersionDiffWrapper = (SyncDLFileVersionDiffWrapper)obj;

		if (Validator.equals(_syncDLFileVersionDiff,
					syncDLFileVersionDiffWrapper._syncDLFileVersionDiff)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public SyncDLFileVersionDiff getWrappedSyncDLFileVersionDiff() {
		return _syncDLFileVersionDiff;
	}

	@Override
	public SyncDLFileVersionDiff getWrappedModel() {
		return _syncDLFileVersionDiff;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _syncDLFileVersionDiff.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _syncDLFileVersionDiff.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_syncDLFileVersionDiff.resetOriginalValues();
	}

	private final SyncDLFileVersionDiff _syncDLFileVersionDiff;
}