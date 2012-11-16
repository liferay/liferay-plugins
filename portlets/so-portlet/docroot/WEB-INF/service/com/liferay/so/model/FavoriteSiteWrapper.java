/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.model.ModelWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link FavoriteSite}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       FavoriteSite
 * @generated
 */
public class FavoriteSiteWrapper implements FavoriteSite,
	ModelWrapper<FavoriteSite> {
	public FavoriteSiteWrapper(FavoriteSite favoriteSite) {
		_favoriteSite = favoriteSite;
	}

	public Class<?> getModelClass() {
		return FavoriteSite.class;
	}

	public String getModelClassName() {
		return FavoriteSite.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("favoriteSiteId", getFavoriteSiteId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());

		return attributes;
	}

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

	/**
	* Returns the primary key of this favorite site.
	*
	* @return the primary key of this favorite site
	*/
	public long getPrimaryKey() {
		return _favoriteSite.getPrimaryKey();
	}

	/**
	* Sets the primary key of this favorite site.
	*
	* @param primaryKey the primary key of this favorite site
	*/
	public void setPrimaryKey(long primaryKey) {
		_favoriteSite.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the favorite site ID of this favorite site.
	*
	* @return the favorite site ID of this favorite site
	*/
	public long getFavoriteSiteId() {
		return _favoriteSite.getFavoriteSiteId();
	}

	/**
	* Sets the favorite site ID of this favorite site.
	*
	* @param favoriteSiteId the favorite site ID of this favorite site
	*/
	public void setFavoriteSiteId(long favoriteSiteId) {
		_favoriteSite.setFavoriteSiteId(favoriteSiteId);
	}

	/**
	* Returns the group ID of this favorite site.
	*
	* @return the group ID of this favorite site
	*/
	public long getGroupId() {
		return _favoriteSite.getGroupId();
	}

	/**
	* Sets the group ID of this favorite site.
	*
	* @param groupId the group ID of this favorite site
	*/
	public void setGroupId(long groupId) {
		_favoriteSite.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this favorite site.
	*
	* @return the company ID of this favorite site
	*/
	public long getCompanyId() {
		return _favoriteSite.getCompanyId();
	}

	/**
	* Sets the company ID of this favorite site.
	*
	* @param companyId the company ID of this favorite site
	*/
	public void setCompanyId(long companyId) {
		_favoriteSite.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this favorite site.
	*
	* @return the user ID of this favorite site
	*/
	public long getUserId() {
		return _favoriteSite.getUserId();
	}

	/**
	* Sets the user ID of this favorite site.
	*
	* @param userId the user ID of this favorite site
	*/
	public void setUserId(long userId) {
		_favoriteSite.setUserId(userId);
	}

	/**
	* Returns the user uuid of this favorite site.
	*
	* @return the user uuid of this favorite site
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _favoriteSite.getUserUuid();
	}

	/**
	* Sets the user uuid of this favorite site.
	*
	* @param userUuid the user uuid of this favorite site
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_favoriteSite.setUserUuid(userUuid);
	}

	public boolean isNew() {
		return _favoriteSite.isNew();
	}

	public void setNew(boolean n) {
		_favoriteSite.setNew(n);
	}

	public boolean isCachedModel() {
		return _favoriteSite.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_favoriteSite.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _favoriteSite.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _favoriteSite.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_favoriteSite.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _favoriteSite.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_favoriteSite.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new FavoriteSiteWrapper((FavoriteSite)_favoriteSite.clone());
	}

	public int compareTo(com.liferay.so.model.FavoriteSite favoriteSite) {
		return _favoriteSite.compareTo(favoriteSite);
	}

	@Override
	public int hashCode() {
		return _favoriteSite.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.so.model.FavoriteSite> toCacheModel() {
		return _favoriteSite.toCacheModel();
	}

	public com.liferay.so.model.FavoriteSite toEscapedModel() {
		return new FavoriteSiteWrapper(_favoriteSite.toEscapedModel());
	}

	public com.liferay.so.model.FavoriteSite toUnescapedModel() {
		return new FavoriteSiteWrapper(_favoriteSite.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _favoriteSite.toString();
	}

	public java.lang.String toXmlString() {
		return _favoriteSite.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_favoriteSite.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public FavoriteSite getWrappedFavoriteSite() {
		return _favoriteSite;
	}

	public FavoriteSite getWrappedModel() {
		return _favoriteSite;
	}

	public void resetOriginalValues() {
		_favoriteSite.resetOriginalValues();
	}

	private FavoriteSite _favoriteSite;
}