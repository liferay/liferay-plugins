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

package com.liferay.so.model;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FavoriteSite}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see FavoriteSite
 * @generated
 */
@ProviderType
public class FavoriteSiteWrapper implements FavoriteSite,
	ModelWrapper<FavoriteSite> {
	public FavoriteSiteWrapper(FavoriteSite favoriteSite) {
		_favoriteSite = favoriteSite;
	}

	@Override
	public Class<?> getModelClass() {
		return FavoriteSite.class;
	}

	@Override
	public String getModelClassName() {
		return FavoriteSite.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("favoriteSiteId", getFavoriteSiteId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long favoriteSiteId = (Long)attributes.get("favoriteSiteId");

		if (favoriteSiteId != null) {
			setFavoriteSiteId(favoriteSiteId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new FavoriteSiteWrapper((FavoriteSite)_favoriteSite.clone());
	}

	@Override
	public int compareTo(com.liferay.so.model.FavoriteSite favoriteSite) {
		return _favoriteSite.compareTo(favoriteSite);
	}

	/**
	* Returns the company ID of this favorite site.
	*
	* @return the company ID of this favorite site
	*/
	@Override
	public long getCompanyId() {
		return _favoriteSite.getCompanyId();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _favoriteSite.getExpandoBridge();
	}

	/**
	* Returns the favorite site ID of this favorite site.
	*
	* @return the favorite site ID of this favorite site
	*/
	@Override
	public long getFavoriteSiteId() {
		return _favoriteSite.getFavoriteSiteId();
	}

	/**
	* Returns the group ID of this favorite site.
	*
	* @return the group ID of this favorite site
	*/
	@Override
	public long getGroupId() {
		return _favoriteSite.getGroupId();
	}

	/**
	* Returns the primary key of this favorite site.
	*
	* @return the primary key of this favorite site
	*/
	@Override
	public long getPrimaryKey() {
		return _favoriteSite.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _favoriteSite.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this favorite site.
	*
	* @return the user ID of this favorite site
	*/
	@Override
	public long getUserId() {
		return _favoriteSite.getUserId();
	}

	/**
	* Returns the user uuid of this favorite site.
	*
	* @return the user uuid of this favorite site
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _favoriteSite.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _favoriteSite.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _favoriteSite.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _favoriteSite.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _favoriteSite.isNew();
	}

	@Override
	public void persist() {
		_favoriteSite.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_favoriteSite.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this favorite site.
	*
	* @param companyId the company ID of this favorite site
	*/
	@Override
	public void setCompanyId(long companyId) {
		_favoriteSite.setCompanyId(companyId);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_favoriteSite.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_favoriteSite.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_favoriteSite.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the favorite site ID of this favorite site.
	*
	* @param favoriteSiteId the favorite site ID of this favorite site
	*/
	@Override
	public void setFavoriteSiteId(long favoriteSiteId) {
		_favoriteSite.setFavoriteSiteId(favoriteSiteId);
	}

	/**
	* Sets the group ID of this favorite site.
	*
	* @param groupId the group ID of this favorite site
	*/
	@Override
	public void setGroupId(long groupId) {
		_favoriteSite.setGroupId(groupId);
	}

	@Override
	public void setNew(boolean n) {
		_favoriteSite.setNew(n);
	}

	/**
	* Sets the primary key of this favorite site.
	*
	* @param primaryKey the primary key of this favorite site
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_favoriteSite.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_favoriteSite.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this favorite site.
	*
	* @param userId the user ID of this favorite site
	*/
	@Override
	public void setUserId(long userId) {
		_favoriteSite.setUserId(userId);
	}

	/**
	* Sets the user uuid of this favorite site.
	*
	* @param userUuid the user uuid of this favorite site
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_favoriteSite.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.so.model.FavoriteSite> toCacheModel() {
		return _favoriteSite.toCacheModel();
	}

	@Override
	public com.liferay.so.model.FavoriteSite toEscapedModel() {
		return new FavoriteSiteWrapper(_favoriteSite.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _favoriteSite.toString();
	}

	@Override
	public com.liferay.so.model.FavoriteSite toUnescapedModel() {
		return new FavoriteSiteWrapper(_favoriteSite.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _favoriteSite.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof FavoriteSiteWrapper)) {
			return false;
		}

		FavoriteSiteWrapper favoriteSiteWrapper = (FavoriteSiteWrapper)obj;

		if (Validator.equals(_favoriteSite, favoriteSiteWrapper._favoriteSite)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public FavoriteSite getWrappedFavoriteSite() {
		return _favoriteSite;
	}

	@Override
	public FavoriteSite getWrappedModel() {
		return _favoriteSite;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _favoriteSite.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _favoriteSite.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_favoriteSite.resetOriginalValues();
	}

	private final FavoriteSite _favoriteSite;
}