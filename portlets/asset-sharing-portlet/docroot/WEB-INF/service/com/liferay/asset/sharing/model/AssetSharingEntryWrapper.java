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

package com.liferay.asset.sharing.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AssetSharingEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetSharingEntry
 * @generated
 */
@ProviderType
public class AssetSharingEntryWrapper implements AssetSharingEntry,
	ModelWrapper<AssetSharingEntry> {
	public AssetSharingEntryWrapper(AssetSharingEntry assetSharingEntry) {
		_assetSharingEntry = assetSharingEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetSharingEntry.class;
	}

	@Override
	public String getModelClassName() {
		return AssetSharingEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("sharedToClassNameId", getSharedToClassNameId());
		attributes.put("sharedToClassPK", getSharedToClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}

		Long sharedToClassNameId = (Long)attributes.get("sharedToClassNameId");

		if (sharedToClassNameId != null) {
			setSharedToClassNameId(sharedToClassNameId);
		}

		Long sharedToClassPK = (Long)attributes.get("sharedToClassPK");

		if (sharedToClassPK != null) {
			setSharedToClassPK(sharedToClassPK);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new AssetSharingEntryWrapper((AssetSharingEntry)_assetSharingEntry.clone());
	}

	@Override
	public int compareTo(
		com.liferay.asset.sharing.model.AssetSharingEntry assetSharingEntry) {
		return _assetSharingEntry.compareTo(assetSharingEntry);
	}

	/**
	* Returns the fully qualified class name of this asset sharing entry.
	*
	* @return the fully qualified class name of this asset sharing entry
	*/
	@Override
	public java.lang.String getClassName() {
		return _assetSharingEntry.getClassName();
	}

	/**
	* Returns the class name ID of this asset sharing entry.
	*
	* @return the class name ID of this asset sharing entry
	*/
	@Override
	public long getClassNameId() {
		return _assetSharingEntry.getClassNameId();
	}

	/**
	* Returns the class p k of this asset sharing entry.
	*
	* @return the class p k of this asset sharing entry
	*/
	@Override
	public long getClassPK() {
		return _assetSharingEntry.getClassPK();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetSharingEntry.getExpandoBridge();
	}

	/**
	* Returns the primary key of this asset sharing entry.
	*
	* @return the primary key of this asset sharing entry
	*/
	@Override
	public com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK getPrimaryKey() {
		return _assetSharingEntry.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _assetSharingEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the shared to class name ID of this asset sharing entry.
	*
	* @return the shared to class name ID of this asset sharing entry
	*/
	@Override
	public long getSharedToClassNameId() {
		return _assetSharingEntry.getSharedToClassNameId();
	}

	/**
	* Returns the shared to class p k of this asset sharing entry.
	*
	* @return the shared to class p k of this asset sharing entry
	*/
	@Override
	public long getSharedToClassPK() {
		return _assetSharingEntry.getSharedToClassPK();
	}

	@Override
	public int hashCode() {
		return _assetSharingEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _assetSharingEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _assetSharingEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _assetSharingEntry.isNew();
	}

	@Override
	public void persist() {
		_assetSharingEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetSharingEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_assetSharingEntry.setClassName(className);
	}

	/**
	* Sets the class name ID of this asset sharing entry.
	*
	* @param classNameId the class name ID of this asset sharing entry
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_assetSharingEntry.setClassNameId(classNameId);
	}

	/**
	* Sets the class p k of this asset sharing entry.
	*
	* @param classPK the class p k of this asset sharing entry
	*/
	@Override
	public void setClassPK(long classPK) {
		_assetSharingEntry.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_assetSharingEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_assetSharingEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetSharingEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_assetSharingEntry.setNew(n);
	}

	/**
	* Sets the primary key of this asset sharing entry.
	*
	* @param primaryKey the primary key of this asset sharing entry
	*/
	@Override
	public void setPrimaryKey(
		com.liferay.asset.sharing.service.persistence.AssetSharingEntryPK primaryKey) {
		_assetSharingEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetSharingEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the shared to class name ID of this asset sharing entry.
	*
	* @param sharedToClassNameId the shared to class name ID of this asset sharing entry
	*/
	@Override
	public void setSharedToClassNameId(long sharedToClassNameId) {
		_assetSharingEntry.setSharedToClassNameId(sharedToClassNameId);
	}

	/**
	* Sets the shared to class p k of this asset sharing entry.
	*
	* @param sharedToClassPK the shared to class p k of this asset sharing entry
	*/
	@Override
	public void setSharedToClassPK(long sharedToClassPK) {
		_assetSharingEntry.setSharedToClassPK(sharedToClassPK);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.asset.sharing.model.AssetSharingEntry> toCacheModel() {
		return _assetSharingEntry.toCacheModel();
	}

	@Override
	public com.liferay.asset.sharing.model.AssetSharingEntry toEscapedModel() {
		return new AssetSharingEntryWrapper(_assetSharingEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetSharingEntry.toString();
	}

	@Override
	public com.liferay.asset.sharing.model.AssetSharingEntry toUnescapedModel() {
		return new AssetSharingEntryWrapper(_assetSharingEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _assetSharingEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetSharingEntryWrapper)) {
			return false;
		}

		AssetSharingEntryWrapper assetSharingEntryWrapper = (AssetSharingEntryWrapper)obj;

		if (Validator.equals(_assetSharingEntry,
					assetSharingEntryWrapper._assetSharingEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public AssetSharingEntry getWrappedAssetSharingEntry() {
		return _assetSharingEntry;
	}

	@Override
	public AssetSharingEntry getWrappedModel() {
		return _assetSharingEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _assetSharingEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _assetSharingEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_assetSharingEntry.resetOriginalValues();
	}

	private final AssetSharingEntry _assetSharingEntry;
}