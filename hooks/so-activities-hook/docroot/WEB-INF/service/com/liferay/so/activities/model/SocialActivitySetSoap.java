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

package com.liferay.so.activities.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    Brian Wing Shun Chan
 * @generated
 */
public class SocialActivitySetSoap implements Serializable {
	public static SocialActivitySetSoap toSoapModel(SocialActivitySet model) {
		SocialActivitySetSoap soapModel = new SocialActivitySetSoap();

		soapModel.setActivitySetId(model.getActivitySetId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setType(model.getType());
		soapModel.setExtraData(model.getExtraData());
		soapModel.setActivityCount(model.getActivityCount());

		return soapModel;
	}

	public static SocialActivitySetSoap[] toSoapModels(
		SocialActivitySet[] models) {
		SocialActivitySetSoap[] soapModels = new SocialActivitySetSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SocialActivitySetSoap[][] toSoapModels(
		SocialActivitySet[][] models) {
		SocialActivitySetSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SocialActivitySetSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SocialActivitySetSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SocialActivitySetSoap[] toSoapModels(
		List<SocialActivitySet> models) {
		List<SocialActivitySetSoap> soapModels = new ArrayList<SocialActivitySetSoap>(models.size());

		for (SocialActivitySet model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SocialActivitySetSoap[soapModels.size()]);
	}

	public SocialActivitySetSoap() {
	}

	public long getPrimaryKey() {
		return _activitySetId;
	}

	public void setPrimaryKey(long pk) {
		setActivitySetId(pk);
	}

	public long getActivitySetId() {
		return _activitySetId;
	}

	public void setActivitySetId(long activitySetId) {
		_activitySetId = activitySetId;
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

	public long getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(long createDate) {
		_createDate = createDate;
	}

	public long getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(long modifiedDate) {
		_modifiedDate = modifiedDate;
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

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public String getExtraData() {
		return _extraData;
	}

	public void setExtraData(String extraData) {
		_extraData = extraData;
	}

	public int getActivityCount() {
		return _activityCount;
	}

	public void setActivityCount(int activityCount) {
		_activityCount = activityCount;
	}

	private long _activitySetId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private long _createDate;
	private long _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private int _type;
	private String _extraData;
	private int _activityCount;
}