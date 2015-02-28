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

package com.liferay.asset.entry.set.model;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link AssetEntrySetLike}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySetLike
 * @generated
 */
public class AssetEntrySetLikeWrapper implements AssetEntrySetLike,
	ModelWrapper<AssetEntrySetLike> {
	public AssetEntrySetLikeWrapper(AssetEntrySetLike assetEntrySetLike) {
		_assetEntrySetLike = assetEntrySetLike;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetEntrySetLike.class;
	}

	@Override
	public String getModelClassName() {
		return AssetEntrySetLike.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetEntrySetId", getAssetEntrySetId());
		attributes.put("classNameId", getClassNameId());
		attributes.put("classPK", getClassPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetEntrySetId = (Long)attributes.get("assetEntrySetId");

		if (assetEntrySetId != null) {
			setAssetEntrySetId(assetEntrySetId);
		}

		Long classNameId = (Long)attributes.get("classNameId");

		if (classNameId != null) {
			setClassNameId(classNameId);
		}

		Long classPK = (Long)attributes.get("classPK");

		if (classPK != null) {
			setClassPK(classPK);
		}
	}

	/**
	* Returns the primary key of this asset entry set like.
	*
	* @return the primary key of this asset entry set like
	*/
	@Override
	public com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK getPrimaryKey() {
		return _assetEntrySetLike.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset entry set like.
	*
	* @param primaryKey the primary key of this asset entry set like
	*/
	@Override
	public void setPrimaryKey(
		com.liferay.asset.entry.set.service.persistence.AssetEntrySetLikePK primaryKey) {
		_assetEntrySetLike.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset entry set ID of this asset entry set like.
	*
	* @return the asset entry set ID of this asset entry set like
	*/
	@Override
	public long getAssetEntrySetId() {
		return _assetEntrySetLike.getAssetEntrySetId();
	}

	/**
	* Sets the asset entry set ID of this asset entry set like.
	*
	* @param assetEntrySetId the asset entry set ID of this asset entry set like
	*/
	@Override
	public void setAssetEntrySetId(long assetEntrySetId) {
		_assetEntrySetLike.setAssetEntrySetId(assetEntrySetId);
	}

	/**
	* Returns the fully qualified class name of this asset entry set like.
	*
	* @return the fully qualified class name of this asset entry set like
	*/
	@Override
	public java.lang.String getClassName() {
		return _assetEntrySetLike.getClassName();
	}

	@Override
	public void setClassName(java.lang.String className) {
		_assetEntrySetLike.setClassName(className);
	}

	/**
	* Returns the class name ID of this asset entry set like.
	*
	* @return the class name ID of this asset entry set like
	*/
	@Override
	public long getClassNameId() {
		return _assetEntrySetLike.getClassNameId();
	}

	/**
	* Sets the class name ID of this asset entry set like.
	*
	* @param classNameId the class name ID of this asset entry set like
	*/
	@Override
	public void setClassNameId(long classNameId) {
		_assetEntrySetLike.setClassNameId(classNameId);
	}

	/**
	* Returns the class p k of this asset entry set like.
	*
	* @return the class p k of this asset entry set like
	*/
	@Override
	public long getClassPK() {
		return _assetEntrySetLike.getClassPK();
	}

	/**
	* Sets the class p k of this asset entry set like.
	*
	* @param classPK the class p k of this asset entry set like
	*/
	@Override
	public void setClassPK(long classPK) {
		_assetEntrySetLike.setClassPK(classPK);
	}

	@Override
	public boolean isNew() {
		return _assetEntrySetLike.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_assetEntrySetLike.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _assetEntrySetLike.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetEntrySetLike.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _assetEntrySetLike.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _assetEntrySetLike.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetEntrySetLike.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetEntrySetLike.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_assetEntrySetLike.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_assetEntrySetLike.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetEntrySetLike.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetEntrySetLikeWrapper((AssetEntrySetLike)_assetEntrySetLike.clone());
	}

	@Override
	public int compareTo(
		com.liferay.asset.entry.set.model.AssetEntrySetLike assetEntrySetLike) {
		return _assetEntrySetLike.compareTo(assetEntrySetLike);
	}

	@Override
	public int hashCode() {
		return _assetEntrySetLike.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.asset.entry.set.model.AssetEntrySetLike> toCacheModel() {
		return _assetEntrySetLike.toCacheModel();
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySetLike toEscapedModel() {
		return new AssetEntrySetLikeWrapper(_assetEntrySetLike.toEscapedModel());
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySetLike toUnescapedModel() {
		return new AssetEntrySetLikeWrapper(_assetEntrySetLike.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetEntrySetLike.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _assetEntrySetLike.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetEntrySetLike.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntrySetLikeWrapper)) {
			return false;
		}

		AssetEntrySetLikeWrapper assetEntrySetLikeWrapper = (AssetEntrySetLikeWrapper)obj;

		if (Validator.equals(_assetEntrySetLike,
					assetEntrySetLikeWrapper._assetEntrySetLike)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public AssetEntrySetLike getWrappedAssetEntrySetLike() {
		return _assetEntrySetLike;
	}

	@Override
	public AssetEntrySetLike getWrappedModel() {
		return _assetEntrySetLike;
	}

	@Override
	public void resetOriginalValues() {
		_assetEntrySetLike.resetOriginalValues();
	}

	private AssetEntrySetLike _assetEntrySetLike;
}