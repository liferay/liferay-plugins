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
 * This class is a wrapper for {@link AssetEntrySet}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see AssetEntrySet
 * @generated
 */
public class AssetEntrySetWrapper implements AssetEntrySet,
	ModelWrapper<AssetEntrySet> {
	public AssetEntrySetWrapper(AssetEntrySet assetEntrySet) {
		_assetEntrySet = assetEntrySet;
	}

	@Override
	public Class<?> getModelClass() {
		return AssetEntrySet.class;
	}

	@Override
	public String getModelClassName() {
		return AssetEntrySet.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("assetEntrySetId", getAssetEntrySetId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("createTime", getCreateTime());
		attributes.put("modifiedTime", getModifiedTime());
		attributes.put("assetEntryId", getAssetEntryId());
		attributes.put("parentAssetEntrySetId", getParentAssetEntrySetId());
		attributes.put("creatorClassNameId", getCreatorClassNameId());
		attributes.put("creatorClassPK", getCreatorClassPK());
		attributes.put("payload", getPayload());
		attributes.put("childAssetEntrySetsCount", getChildAssetEntrySetsCount());
		attributes.put("assetEntrySetLikesCount", getAssetEntrySetLikesCount());
		attributes.put("privateAssetEntrySet", getPrivateAssetEntrySet());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long assetEntrySetId = (Long)attributes.get("assetEntrySetId");

		if (assetEntrySetId != null) {
			setAssetEntrySetId(assetEntrySetId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		Long createTime = (Long)attributes.get("createTime");

		if (createTime != null) {
			setCreateTime(createTime);
		}

		Long modifiedTime = (Long)attributes.get("modifiedTime");

		if (modifiedTime != null) {
			setModifiedTime(modifiedTime);
		}

		Long assetEntryId = (Long)attributes.get("assetEntryId");

		if (assetEntryId != null) {
			setAssetEntryId(assetEntryId);
		}

		Long parentAssetEntrySetId = (Long)attributes.get(
				"parentAssetEntrySetId");

		if (parentAssetEntrySetId != null) {
			setParentAssetEntrySetId(parentAssetEntrySetId);
		}

		Long creatorClassNameId = (Long)attributes.get("creatorClassNameId");

		if (creatorClassNameId != null) {
			setCreatorClassNameId(creatorClassNameId);
		}

		Long creatorClassPK = (Long)attributes.get("creatorClassPK");

		if (creatorClassPK != null) {
			setCreatorClassPK(creatorClassPK);
		}

		String payload = (String)attributes.get("payload");

		if (payload != null) {
			setPayload(payload);
		}

		Integer childAssetEntrySetsCount = (Integer)attributes.get(
				"childAssetEntrySetsCount");

		if (childAssetEntrySetsCount != null) {
			setChildAssetEntrySetsCount(childAssetEntrySetsCount);
		}

		Integer assetEntrySetLikesCount = (Integer)attributes.get(
				"assetEntrySetLikesCount");

		if (assetEntrySetLikesCount != null) {
			setAssetEntrySetLikesCount(assetEntrySetLikesCount);
		}

		Boolean privateAssetEntrySet = (Boolean)attributes.get(
				"privateAssetEntrySet");

		if (privateAssetEntrySet != null) {
			setPrivateAssetEntrySet(privateAssetEntrySet);
		}
	}

	/**
	* Returns the primary key of this asset entry set.
	*
	* @return the primary key of this asset entry set
	*/
	@Override
	public long getPrimaryKey() {
		return _assetEntrySet.getPrimaryKey();
	}

	/**
	* Sets the primary key of this asset entry set.
	*
	* @param primaryKey the primary key of this asset entry set
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_assetEntrySet.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the asset entry set ID of this asset entry set.
	*
	* @return the asset entry set ID of this asset entry set
	*/
	@Override
	public long getAssetEntrySetId() {
		return _assetEntrySet.getAssetEntrySetId();
	}

	/**
	* Sets the asset entry set ID of this asset entry set.
	*
	* @param assetEntrySetId the asset entry set ID of this asset entry set
	*/
	@Override
	public void setAssetEntrySetId(long assetEntrySetId) {
		_assetEntrySet.setAssetEntrySetId(assetEntrySetId);
	}

	/**
	* Returns the company ID of this asset entry set.
	*
	* @return the company ID of this asset entry set
	*/
	@Override
	public long getCompanyId() {
		return _assetEntrySet.getCompanyId();
	}

	/**
	* Sets the company ID of this asset entry set.
	*
	* @param companyId the company ID of this asset entry set
	*/
	@Override
	public void setCompanyId(long companyId) {
		_assetEntrySet.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this asset entry set.
	*
	* @return the user ID of this asset entry set
	*/
	@Override
	public long getUserId() {
		return _assetEntrySet.getUserId();
	}

	/**
	* Sets the user ID of this asset entry set.
	*
	* @param userId the user ID of this asset entry set
	*/
	@Override
	public void setUserId(long userId) {
		_assetEntrySet.setUserId(userId);
	}

	/**
	* Returns the user uuid of this asset entry set.
	*
	* @return the user uuid of this asset entry set
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _assetEntrySet.getUserUuid();
	}

	/**
	* Sets the user uuid of this asset entry set.
	*
	* @param userUuid the user uuid of this asset entry set
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_assetEntrySet.setUserUuid(userUuid);
	}

	/**
	* Returns the create time of this asset entry set.
	*
	* @return the create time of this asset entry set
	*/
	@Override
	public long getCreateTime() {
		return _assetEntrySet.getCreateTime();
	}

	/**
	* Sets the create time of this asset entry set.
	*
	* @param createTime the create time of this asset entry set
	*/
	@Override
	public void setCreateTime(long createTime) {
		_assetEntrySet.setCreateTime(createTime);
	}

	/**
	* Returns the modified time of this asset entry set.
	*
	* @return the modified time of this asset entry set
	*/
	@Override
	public long getModifiedTime() {
		return _assetEntrySet.getModifiedTime();
	}

	/**
	* Sets the modified time of this asset entry set.
	*
	* @param modifiedTime the modified time of this asset entry set
	*/
	@Override
	public void setModifiedTime(long modifiedTime) {
		_assetEntrySet.setModifiedTime(modifiedTime);
	}

	/**
	* Returns the asset entry ID of this asset entry set.
	*
	* @return the asset entry ID of this asset entry set
	*/
	@Override
	public long getAssetEntryId() {
		return _assetEntrySet.getAssetEntryId();
	}

	/**
	* Sets the asset entry ID of this asset entry set.
	*
	* @param assetEntryId the asset entry ID of this asset entry set
	*/
	@Override
	public void setAssetEntryId(long assetEntryId) {
		_assetEntrySet.setAssetEntryId(assetEntryId);
	}

	/**
	* Returns the parent asset entry set ID of this asset entry set.
	*
	* @return the parent asset entry set ID of this asset entry set
	*/
	@Override
	public long getParentAssetEntrySetId() {
		return _assetEntrySet.getParentAssetEntrySetId();
	}

	/**
	* Sets the parent asset entry set ID of this asset entry set.
	*
	* @param parentAssetEntrySetId the parent asset entry set ID of this asset entry set
	*/
	@Override
	public void setParentAssetEntrySetId(long parentAssetEntrySetId) {
		_assetEntrySet.setParentAssetEntrySetId(parentAssetEntrySetId);
	}

	/**
	* Returns the creator class name ID of this asset entry set.
	*
	* @return the creator class name ID of this asset entry set
	*/
	@Override
	public long getCreatorClassNameId() {
		return _assetEntrySet.getCreatorClassNameId();
	}

	/**
	* Sets the creator class name ID of this asset entry set.
	*
	* @param creatorClassNameId the creator class name ID of this asset entry set
	*/
	@Override
	public void setCreatorClassNameId(long creatorClassNameId) {
		_assetEntrySet.setCreatorClassNameId(creatorClassNameId);
	}

	/**
	* Returns the creator class p k of this asset entry set.
	*
	* @return the creator class p k of this asset entry set
	*/
	@Override
	public long getCreatorClassPK() {
		return _assetEntrySet.getCreatorClassPK();
	}

	/**
	* Sets the creator class p k of this asset entry set.
	*
	* @param creatorClassPK the creator class p k of this asset entry set
	*/
	@Override
	public void setCreatorClassPK(long creatorClassPK) {
		_assetEntrySet.setCreatorClassPK(creatorClassPK);
	}

	/**
	* Returns the payload of this asset entry set.
	*
	* @return the payload of this asset entry set
	*/
	@Override
	public java.lang.String getPayload() {
		return _assetEntrySet.getPayload();
	}

	/**
	* Sets the payload of this asset entry set.
	*
	* @param payload the payload of this asset entry set
	*/
	@Override
	public void setPayload(java.lang.String payload) {
		_assetEntrySet.setPayload(payload);
	}

	/**
	* Returns the child asset entry sets count of this asset entry set.
	*
	* @return the child asset entry sets count of this asset entry set
	*/
	@Override
	public int getChildAssetEntrySetsCount() {
		return _assetEntrySet.getChildAssetEntrySetsCount();
	}

	/**
	* Sets the child asset entry sets count of this asset entry set.
	*
	* @param childAssetEntrySetsCount the child asset entry sets count of this asset entry set
	*/
	@Override
	public void setChildAssetEntrySetsCount(int childAssetEntrySetsCount) {
		_assetEntrySet.setChildAssetEntrySetsCount(childAssetEntrySetsCount);
	}

	/**
	* Returns the asset entry set likes count of this asset entry set.
	*
	* @return the asset entry set likes count of this asset entry set
	*/
	@Override
	public int getAssetEntrySetLikesCount() {
		return _assetEntrySet.getAssetEntrySetLikesCount();
	}

	/**
	* Sets the asset entry set likes count of this asset entry set.
	*
	* @param assetEntrySetLikesCount the asset entry set likes count of this asset entry set
	*/
	@Override
	public void setAssetEntrySetLikesCount(int assetEntrySetLikesCount) {
		_assetEntrySet.setAssetEntrySetLikesCount(assetEntrySetLikesCount);
	}

	/**
	* Returns the private asset entry set of this asset entry set.
	*
	* @return the private asset entry set of this asset entry set
	*/
	@Override
	public boolean getPrivateAssetEntrySet() {
		return _assetEntrySet.getPrivateAssetEntrySet();
	}

	/**
	* Returns <code>true</code> if this asset entry set is private asset entry set.
	*
	* @return <code>true</code> if this asset entry set is private asset entry set; <code>false</code> otherwise
	*/
	@Override
	public boolean isPrivateAssetEntrySet() {
		return _assetEntrySet.isPrivateAssetEntrySet();
	}

	/**
	* Sets whether this asset entry set is private asset entry set.
	*
	* @param privateAssetEntrySet the private asset entry set of this asset entry set
	*/
	@Override
	public void setPrivateAssetEntrySet(boolean privateAssetEntrySet) {
		_assetEntrySet.setPrivateAssetEntrySet(privateAssetEntrySet);
	}

	@Override
	public boolean isNew() {
		return _assetEntrySet.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_assetEntrySet.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _assetEntrySet.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_assetEntrySet.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _assetEntrySet.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _assetEntrySet.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_assetEntrySet.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _assetEntrySet.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_assetEntrySet.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_assetEntrySet.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_assetEntrySet.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new AssetEntrySetWrapper((AssetEntrySet)_assetEntrySet.clone());
	}

	@Override
	public int compareTo(
		com.liferay.asset.entry.set.model.AssetEntrySet assetEntrySet) {
		return _assetEntrySet.compareTo(assetEntrySet);
	}

	@Override
	public int hashCode() {
		return _assetEntrySet.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.asset.entry.set.model.AssetEntrySet> toCacheModel() {
		return _assetEntrySet.toCacheModel();
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet toEscapedModel() {
		return new AssetEntrySetWrapper(_assetEntrySet.toEscapedModel());
	}

	@Override
	public com.liferay.asset.entry.set.model.AssetEntrySet toUnescapedModel() {
		return new AssetEntrySetWrapper(_assetEntrySet.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _assetEntrySet.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _assetEntrySet.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_assetEntrySet.persist();
	}

	@Override
	public java.util.List<com.liferay.asset.entry.set.model.AssetEntrySet> getChildAssetEntrySets() {
		return _assetEntrySet.getChildAssetEntrySets();
	}

	@Override
	public void setChildAssetEntrySets(long userId, int childAssetEntrySetsLimit)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_assetEntrySet.setChildAssetEntrySets(userId, childAssetEntrySetsLimit);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof AssetEntrySetWrapper)) {
			return false;
		}

		AssetEntrySetWrapper assetEntrySetWrapper = (AssetEntrySetWrapper)obj;

		if (Validator.equals(_assetEntrySet, assetEntrySetWrapper._assetEntrySet)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public AssetEntrySet getWrappedAssetEntrySet() {
		return _assetEntrySet;
	}

	@Override
	public AssetEntrySet getWrappedModel() {
		return _assetEntrySet;
	}

	@Override
	public void resetOriginalValues() {
		_assetEntrySet.resetOriginalValues();
	}

	private AssetEntrySet _assetEntrySet;
}