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

package com.liferay.sharing.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link SharingEntry}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see SharingEntry
 * @generated
 */
@ProviderType
public class SharingEntryWrapper implements SharingEntry,
	ModelWrapper<SharingEntry> {
	public SharingEntryWrapper(SharingEntry sharingEntry) {
		_sharingEntry = sharingEntry;
	}

	@Override
	public Class<?> getModelClass() {
		return SharingEntry.class;
	}

	@Override
	public String getModelClassName() {
		return SharingEntry.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());
		attributes.put("sharingClassNameId", getSharingClassNameId());
		attributes.put("sharingClassPK", getSharingClassPK());

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

		Long sharingClassNameId = (Long)attributes.get("sharingClassNameId");

		if (sharingClassNameId != null) {
			setSharingClassNameId(sharingClassNameId);
		}

		Long sharingClassPK = (Long)attributes.get("sharingClassPK");

		if (sharingClassPK != null) {
			setSharingClassPK(sharingClassPK);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new SharingEntryWrapper((SharingEntry)_sharingEntry.clone());
	}

	@Override
	public int compareTo(com.liferay.sharing.model.SharingEntry sharingEntry) {
		return _sharingEntry.compareTo(sharingEntry);
	}

	/**
	* Returns the fully qualified class name of this sharing entry.
	*
	* @return the fully qualified class name of this sharing entry
	*/
	@Override
	public java.lang.String getClassName() {
		return _sharingEntry.getClassName();
	}

	/**
	* Returns the class name ID of this sharing entry.
	*
	* @return the class name ID of this sharing entry
	*/
	@Override
	public long getClassNameId() {
		return _sharingEntry.getClassNameId();
	}

	/**
	* Returns the class p k of this sharing entry.
	*
	* @return the class p k of this sharing entry
	*/
	@Override
	public long getClassPK() {
		return _sharingEntry.getClassPK();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _sharingEntry.getExpandoBridge();
	}

	/**
	* Returns the primary key of this sharing entry.
	*
	* @return the primary key of this sharing entry
	*/
	@Override
	public com.liferay.sharing.service.persistence.SharingEntryPK getPrimaryKey() {
		return _sharingEntry.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _sharingEntry.getPrimaryKeyObj();
	}

	/**
	* Returns the sharing class name ID of this sharing entry.
	*
	* @return the sharing class name ID of this sharing entry
	*/
	@Override
	public long getSharingClassNameId() {
		return _sharingEntry.getSharingClassNameId();
	}

	/**
	* Returns the sharing class p k of this sharing entry.
	*
	* @return the sharing class p k of this sharing entry
	*/
	@Override
	public long getSharingClassPK() {
		return _sharingEntry.getSharingClassPK();
	}

	@Override
	public int hashCode() {
		return _sharingEntry.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _sharingEntry.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _sharingEntry.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _sharingEntry.isNew();
	}

	@Override
	public void persist() {
		_sharingEntry.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_sharingEntry.setCachedModel(cachedModel);
	}

	@Override
	public void setClassName(java.lang.String className) {
		_sharingEntry.setClassName(className);
	}

	/**
	* Sets the class name ID of this sharing entry.
	*
	* @param classNameId the class name ID of this sharing entry
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_sharingEntry.setClassNameId(classNameId);
	}

	/**
	* Sets the class p k of this sharing entry.
	*
	* @param classPK the class p k of this sharing entry
	*/
	@Override
	public void setClassPK(long classPK) {
		_sharingEntry.setClassPK(classPK);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_sharingEntry.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_sharingEntry.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_sharingEntry.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public void setNew(boolean n) {
		_sharingEntry.setNew(n);
	}

	/**
	* Sets the primary key of this sharing entry.
	*
	* @param primaryKey the primary key of this sharing entry
	*/
	@Override
	public void setPrimaryKey(
		com.liferay.sharing.service.persistence.SharingEntryPK primaryKey) {
		_sharingEntry.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_sharingEntry.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the sharing class name ID of this sharing entry.
	*
	* @param sharingClassNameId the sharing class name ID of this sharing entry
	*/
	@Override
	public void setSharingClassNameId(long sharingClassNameId) {
		_sharingEntry.setSharingClassNameId(sharingClassNameId);
	}

	/**
	* Sets the sharing class p k of this sharing entry.
	*
	* @param sharingClassPK the sharing class p k of this sharing entry
	*/
	@Override
	public void setSharingClassPK(long sharingClassPK) {
		_sharingEntry.setSharingClassPK(sharingClassPK);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.sharing.model.SharingEntry> toCacheModel() {
		return _sharingEntry.toCacheModel();
	}

	@Override
	public com.liferay.sharing.model.SharingEntry toEscapedModel() {
		return new SharingEntryWrapper(_sharingEntry.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _sharingEntry.toString();
	}

	@Override
	public com.liferay.sharing.model.SharingEntry toUnescapedModel() {
		return new SharingEntryWrapper(_sharingEntry.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _sharingEntry.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof SharingEntryWrapper)) {
			return false;
		}

		SharingEntryWrapper sharingEntryWrapper = (SharingEntryWrapper)obj;

		if (Validator.equals(_sharingEntry, sharingEntryWrapper._sharingEntry)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public SharingEntry getWrappedSharingEntry() {
		return _sharingEntry;
	}

	@Override
	public SharingEntry getWrappedModel() {
		return _sharingEntry;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _sharingEntry.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _sharingEntry.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_sharingEntry.resetOriginalValues();
	}

	private final SharingEntry _sharingEntry;
}