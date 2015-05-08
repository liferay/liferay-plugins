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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.sql.Blob;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link TestBlobEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see TestBlobEntry
 * @generated
 */
@ProviderType
public class TestBlobEntryWrapper implements TestBlobEntry,
	ModelWrapper<TestBlobEntry> {
	public TestBlobEntryWrapper(TestBlobEntry testBlobEntry) {
		_testBlobEntry = testBlobEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return TestBlobEntry.class;
	}

	@Override
	public String getModelClassName() {
		return TestBlobEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("testBlobEntryId", getTestBlobEntryId());
		attributes.put("blobField", getBlobField());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long testBlobEntryId = (Long)attributes.get("testBlobEntryId");

		if (testBlobEntryId != null) {
			setTestBlobEntryId(testBlobEntryId);
		}

		Blob blobField = (Blob)attributes.get("blobField");

		if (blobField != null) {
			setBlobField(blobField);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new TestBlobEntryWrapper((TestBlobEntry)_testBlobEntry.clone());
	}

	@Override
	public int compareTo(com.liferay.testblob.model.TestBlobEntry testBlobEntry) {
		return _testBlobEntry.compareTo(testBlobEntry);
	}

	/**
	* Returns the blob field of this test blob entry.
	*
	* @return the blob field of this test blob entry
	*/
	@Override
	public Blob getBlobField() {
		return _testBlobEntry.getBlobField();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _testBlobEntry.getExpandoBridge();
	}

	/**
	* Returns the primary key of this test blob entry.
	*
	* @return the primary key of this test blob entry
	*/
	@Override
	public long getPrimaryKey() {
		return _testBlobEntry.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _testBlobEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the test blob entry ID of this test blob entry.
	*
	* @return the test blob entry ID of this test blob entry
	*/
	@Override
	public long getTestBlobEntryId() {
		return _testBlobEntry.getTestBlobEntryId();
	}

	/**
	* Returns the uuid of this test blob entry.
	*
	* @return the uuid of this test blob entry
	*/
	@Override
	public java.lang.String getUuid() {
		return _testBlobEntry.getUuid();
	}

	@Override
	public int hashCode() {
		return _testBlobEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _testBlobEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _testBlobEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _testBlobEntry.isNew();
	}

	@Override
	public void persist() {
		_testBlobEntry.persist();
	}

	/**
	* Sets the blob field of this test blob entry.
	*
	* @param blobField the blob field of this test blob entry
	*/
	@Override
	public void setBlobField(Blob blobField) {
		_testBlobEntry.setBlobField(blobField);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_testBlobEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_testBlobEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_testBlobEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_testBlobEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_testBlobEntry.setNew(n);
	}

	/**
	* Sets the primary key of this test blob entry.
	*
	* @param primaryKey the primary key of this test blob entry
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_testBlobEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_testBlobEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the test blob entry ID of this test blob entry.
	*
	* @param testBlobEntryId the test blob entry ID of this test blob entry
	*/
	@Override
	public void setTestBlobEntryId(long testBlobEntryId) {
		_testBlobEntry.setTestBlobEntryId(testBlobEntryId);
	}

	/**
	* Sets the uuid of this test blob entry.
	*
	* @param uuid the uuid of this test blob entry
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_testBlobEntry.setUuid(uuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.testblob.model.TestBlobEntry> toCacheModel() {
		return _testBlobEntry.toCacheModel();
	}

	@Override
	public com.liferay.testblob.model.TestBlobEntry toEscapedModel() {
		return new TestBlobEntryWrapper(_testBlobEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _testBlobEntry.toString();
	}

	@Override
	public com.liferay.testblob.model.TestBlobEntry toUnescapedModel() {
		return new TestBlobEntryWrapper(_testBlobEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _testBlobEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof TestBlobEntryWrapper)) {
			return false;
		}

		TestBlobEntryWrapper testBlobEntryWrapper = (TestBlobEntryWrapper)obj;

		if (Validator.equals(_testBlobEntry, testBlobEntryWrapper._testBlobEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public TestBlobEntry getWrappedTestBlobEntry() {
		return _testBlobEntry;
	}

	@Override
	public TestBlobEntry getWrappedModel() {
		return _testBlobEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _testBlobEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _testBlobEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_testBlobEntry.resetOriginalValues();
	}

	private final TestBlobEntry _testBlobEntry;
}