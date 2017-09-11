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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.asset.entry.set.service.http.AssetEntrySetServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.asset.entry.set.service.http.AssetEntrySetServiceSoap
 * @generated
 */
public class AssetEntrySetSoap implements Serializable {
	public static AssetEntrySetSoap toSoapModel(AssetEntrySet model) {
		AssetEntrySetSoap soapModel = new AssetEntrySetSoap();

		soapModel.setAssetEntrySetId(model.getAssetEntrySetId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateTime(model.getCreateTime());
		soapModel.setModifiedTime(model.getModifiedTime());
		soapModel.setAssetEntryId(model.getAssetEntryId());
		soapModel.setParentAssetEntrySetId(model.getParentAssetEntrySetId());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setCreatorClassNameId(model.getCreatorClassNameId());
		soapModel.setCreatorClassPK(model.getCreatorClassPK());
		soapModel.setCreatorName(model.getCreatorName());
		soapModel.setAssetEntrySetLikesCount(model.getAssetEntrySetLikesCount());
		soapModel.setChildAssetEntrySetsCount(model.getChildAssetEntrySetsCount());
		soapModel.setLevel(model.getLevel());
		soapModel.setPayload(model.getPayload());
		soapModel.setPrivateAssetEntrySet(model.getPrivateAssetEntrySet());
		soapModel.setStickyTime(model.getStickyTime());
		soapModel.setTitle(model.getTitle());
		soapModel.setType(model.getType());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static AssetEntrySetSoap[] toSoapModels(AssetEntrySet[] models) {
		AssetEntrySetSoap[] soapModels = new AssetEntrySetSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static AssetEntrySetSoap[][] toSoapModels(AssetEntrySet[][] models) {
		AssetEntrySetSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new AssetEntrySetSoap[models.length][models[0].length];
		}
		else {
			soapModels = new AssetEntrySetSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static AssetEntrySetSoap[] toSoapModels(List<AssetEntrySet> models) {
		List<AssetEntrySetSoap> soapModels = new ArrayList<AssetEntrySetSoap>(models.size());

		for (AssetEntrySet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new AssetEntrySetSoap[soapModels.size()]);
	}

	public AssetEntrySetSoap() {
	}

	public long getPrimaryKey() {
		return _assetEntrySetId;
	}

	public void setPrimaryKey(long pk) {
		setAssetEntrySetId(pk);
	}

	public long getAssetEntrySetId() {
		return _assetEntrySetId;
	}

	public void setAssetEntrySetId(long assetEntrySetId) {
		_assetEntrySetId = assetEntrySetId;
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

	public long getCreateTime() {
		return _createTime;
	}

	public void setCreateTime(long createTime) {
		_createTime = createTime;
	}

	public long getModifiedTime() {
		return _modifiedTime;
	}

	public void setModifiedTime(long modifiedTime) {
		_modifiedTime = modifiedTime;
	}

	public long getAssetEntryId() {
		return _assetEntryId;
	}

	public void setAssetEntryId(long assetEntryId) {
		_assetEntryId = assetEntryId;
	}

	public long getParentAssetEntrySetId() {
		return _parentAssetEntrySetId;
	}

	public void setParentAssetEntrySetId(long parentAssetEntrySetId) {
		_parentAssetEntrySetId = parentAssetEntrySetId;
	}

	public long getClassNameId() {
		return _classNameId;
	}

	public void setClassNameId(long classNameId) {
		_classNameId = classNameId;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public long getCreatorClassNameId() {
		return _creatorClassNameId;
	}

	public void setCreatorClassNameId(long creatorClassNameId) {
		_creatorClassNameId = creatorClassNameId;
	}

	public long getCreatorClassPK() {
		return _creatorClassPK;
	}

	public void setCreatorClassPK(long creatorClassPK) {
		_creatorClassPK = creatorClassPK;
	}

	public String getCreatorName() {
		return _creatorName;
	}

	public void setCreatorName(String creatorName) {
		_creatorName = creatorName;
	}

	public int getAssetEntrySetLikesCount() {
		return _assetEntrySetLikesCount;
	}

	public void setAssetEntrySetLikesCount(int assetEntrySetLikesCount) {
		_assetEntrySetLikesCount = assetEntrySetLikesCount;
	}

	public int getChildAssetEntrySetsCount() {
		return _childAssetEntrySetsCount;
	}

	public void setChildAssetEntrySetsCount(int childAssetEntrySetsCount) {
		_childAssetEntrySetsCount = childAssetEntrySetsCount;
	}

	public int getLevel() {
		return _level;
	}

	public void setLevel(int level) {
		_level = level;
	}

	public String getPayload() {
		return _payload;
	}

	public void setPayload(String payload) {
		_payload = payload;
	}

	public boolean getPrivateAssetEntrySet() {
		return _privateAssetEntrySet;
	}

	public boolean isPrivateAssetEntrySet() {
		return _privateAssetEntrySet;
	}

	public void setPrivateAssetEntrySet(boolean privateAssetEntrySet) {
		_privateAssetEntrySet = privateAssetEntrySet;
	}

	public long getStickyTime() {
		return _stickyTime;
	}

	public void setStickyTime(long stickyTime) {
		_stickyTime = stickyTime;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private long _assetEntrySetId;
	private long _companyId;
	private long _userId;
	private long _createTime;
	private long _modifiedTime;
	private long _assetEntryId;
	private long _parentAssetEntrySetId;
	private long _classNameId;
	private long _classPK;
	private long _creatorClassNameId;
	private long _creatorClassPK;
	private String _creatorName;
	private int _assetEntrySetLikesCount;
	private int _childAssetEntrySetsCount;
	private int _level;
	private String _payload;
	private boolean _privateAssetEntrySet;
	private long _stickyTime;
	private String _title;
	private int _type;
	private int _status;
}