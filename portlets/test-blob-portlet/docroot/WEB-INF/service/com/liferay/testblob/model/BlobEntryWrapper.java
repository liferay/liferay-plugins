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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.sql.Blob;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link BlobEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see BlobEntry
 * @generated
 */
public class BlobEntryWrapper implements BlobEntry, ModelWrapper<BlobEntry> {
	public BlobEntryWrapper(BlobEntry blobEntry) {
		_blobEntry = blobEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return BlobEntry.class;
	}

	@Override
	public String getModelClassName() {
		return BlobEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("blobEntryId", getBlobEntryId());
		attributes.put("blobField", getBlobField());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long blobEntryId = (Long)attributes.get("blobEntryId");

		if (blobEntryId != null) {
			setBlobEntryId(blobEntryId);
		}

		Blob blobField = (Blob)attributes.get("blobField");

		if (blobField != null) {
			setBlobField(blobField);
		}
	}

	/**
	* Returns the primary key of this blob entry.
	*
	* @return the primary key of this blob entry
	*/
	@Override
	public long getPrimaryKey() {
		return _blobEntry.getPrimaryKey();
	}

	/**
	* Sets the primary key of this blob entry.
	*
	* @param primaryKey the primary key of this blob entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_blobEntry.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this blob entry.
	*
	* @return the uuid of this blob entry
	*/
	@Override
	public java.lang.String getUuid() {
		return _blobEntry.getUuid();
	}

	/**
	* Sets the uuid of this blob entry.
	*
	* @param uuid the uuid of this blob entry
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_blobEntry.setUuid(uuid);
	}

	/**
	* Returns the blob entry ID of this blob entry.
	*
	* @return the blob entry ID of this blob entry
	*/
	@Override
	public long getBlobEntryId() {
		return _blobEntry.getBlobEntryId();
	}

	/**
	* Sets the blob entry ID of this blob entry.
	*
	* @param blobEntryId the blob entry ID of this blob entry
	*/
	@Override
	public void setBlobEntryId(long blobEntryId) {
		_blobEntry.setBlobEntryId(blobEntryId);
	}

	/**
	* Returns the blob field of this blob entry.
	*
	* @return the blob field of this blob entry
	*/
	@Override
	public java.sql.Blob getBlobField() {
		return _blobEntry.getBlobField();
	}

	/**
	* Sets the blob field of this blob entry.
	*
	* @param blobField the blob field of this blob entry
	*/
	@Override
	public void setBlobField(java.sql.Blob blobField) {
		_blobEntry.setBlobField(blobField);
	}

	@Override
	public boolean isNew() {
		return _blobEntry.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_blobEntry.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _blobEntry.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_blobEntry.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _blobEntry.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _blobEntry.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_blobEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _blobEntry.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_blobEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_blobEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_blobEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new BlobEntryWrapper((BlobEntry)_blobEntry.clone());
	}

	@Override
	public int compareTo(com.liferay.testblob.model.BlobEntry blobEntry) {
		return _blobEntry.compareTo(blobEntry);
	}

	@Override
	public int hashCode() {
		return _blobEntry.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.testblob.model.BlobEntry> toCacheModel() {
		return _blobEntry.toCacheModel();
	}

	@Override
	public com.liferay.testblob.model.BlobEntry toEscapedModel() {
		return new BlobEntryWrapper(_blobEntry.toEscapedModel());
	}

	@Override
	public com.liferay.testblob.model.BlobEntry toUnescapedModel() {
		return new BlobEntryWrapper(_blobEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _blobEntry.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _blobEntry.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_blobEntry.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof BlobEntryWrapper)) {
			return false;
		}

		BlobEntryWrapper blobEntryWrapper = (BlobEntryWrapper)obj;

		if (Validator.equals(_blobEntry, blobEntryWrapper._blobEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public BlobEntry getWrappedBlobEntry() {
		return _blobEntry;
	}

	@Override
	public BlobEntry getWrappedModel() {
		return _blobEntry;
	}

	@Override
	public void resetOriginalValues() {
		_blobEntry.resetOriginalValues();
	}

	private BlobEntry _blobEntry;
}