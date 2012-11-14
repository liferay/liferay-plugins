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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.util.PortalUtil;

import com.liferay.so.service.FavoriteSiteLocalServiceUtil;

import java.io.Serializable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class FavoriteSiteClp extends BaseModelImpl<FavoriteSite>
	implements FavoriteSite {
	public FavoriteSiteClp() {
	}

	public Class<?> getModelClass() {
		return FavoriteSite.class;
	}

	public String getModelClassName() {
		return FavoriteSite.class.getName();
	}

	public long getPrimaryKey() {
		return _favoriteSiteId;
	}

	public void setPrimaryKey(long primaryKey) {
		setFavoriteSiteId(primaryKey);
	}

	public Serializable getPrimaryKeyObj() {
		return new Long(_favoriteSiteId);
	}

	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

	public long getFavoriteSiteId() {
		return _favoriteSiteId;
	}

	public void setFavoriteSiteId(long favoriteSiteId) {
		_favoriteSiteId = favoriteSiteId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getUserId() {
		return _userId;
	}

	public void setUserId(long userId) {
		_userId = userId;
	}

	public String getUserUuid() throws SystemException {
		return PortalUtil.getUserValue(getUserId(), "uuid", _userUuid);
	}

	public void setUserUuid(String userUuid) {
		_userUuid = userUuid;
	}

	public BaseModel<?> getFavoriteSiteRemoteModel() {
		return _favoriteSiteRemoteModel;
	}

	public void setFavoriteSiteRemoteModel(BaseModel<?> favoriteSiteRemoteModel) {
		_favoriteSiteRemoteModel = favoriteSiteRemoteModel;
	}

	public void persist() throws SystemException {
		if (this.isNew()) {
			FavoriteSiteLocalServiceUtil.addFavoriteSite(this);
		}
		else {
			FavoriteSiteLocalServiceUtil.updateFavoriteSite(this);
		}
	}

	@Override
	public FavoriteSite toEscapedModel() {
		return (FavoriteSite)ProxyUtil.newProxyInstance(FavoriteSite.class.getClassLoader(),
			new Class[] { FavoriteSite.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		FavoriteSiteClp clone = new FavoriteSiteClp();

		clone.setFavoriteSiteId(getFavoriteSiteId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());

		return clone;
	}

	public int compareTo(FavoriteSite favoriteSite) {
		long primaryKey = favoriteSite.getPrimaryKey();

		if (getPrimaryKey() < primaryKey) {
			return -1;
		}
		else if (getPrimaryKey() > primaryKey) {
			return 1;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}

		FavoriteSiteClp favoriteSite = null;

		try {
			favoriteSite = (FavoriteSiteClp)obj;
		}
		catch (ClassCastException cce) {
			return false;
		}

		long primaryKey = favoriteSite.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(9);

		sb.append("{favoriteSiteId=");
		sb.append(getFavoriteSiteId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append("}");

		return sb.toString();
	}

	public String toXmlString() {
		StringBundler sb = new StringBundler(16);

		sb.append("<model><model-name>");
		sb.append("com.liferay.so.model.FavoriteSite");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>favoriteSiteId</column-name><column-value><![CDATA[");
		sb.append(getFavoriteSiteId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _favoriteSiteId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userUuid;
	private BaseModel<?> _favoriteSiteRemoteModel;
}