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

package com.liferay.microblogs.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.microblogs.service.http.MicroblogsEntryServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.microblogs.service.http.MicroblogsEntryServiceSoap
 * @generated
 */
@ProviderType
public class MicroblogsEntrySoap implements Serializable {
	public static MicroblogsEntrySoap toSoapModel(MicroblogsEntry model) {
		MicroblogsEntrySoap soapModel = new MicroblogsEntrySoap();

		soapModel.setMicroblogsEntryId(model.getMicroblogsEntryId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setCreatorClassNameId(model.getCreatorClassNameId());
		soapModel.setCreatorClassPK(model.getCreatorClassPK());
		soapModel.setContent(model.getContent());
		soapModel.setType(model.getType());
		soapModel.setReceiverUserId(model.getReceiverUserId());
		soapModel.setReceiverMicroblogsEntryId(model.getReceiverMicroblogsEntryId());
		soapModel.setSocialRelationType(model.getSocialRelationType());

		return soapModel;
	}

	public static MicroblogsEntrySoap[] toSoapModels(MicroblogsEntry[] models) {
		MicroblogsEntrySoap[] soapModels = new MicroblogsEntrySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static MicroblogsEntrySoap[][] toSoapModels(
		MicroblogsEntry[][] models) {
		MicroblogsEntrySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new MicroblogsEntrySoap[models.length][models[0].length];
		}
		else {
			soapModels = new MicroblogsEntrySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static MicroblogsEntrySoap[] toSoapModels(
		List<MicroblogsEntry> models) {
		List<MicroblogsEntrySoap> soapModels = new ArrayList<MicroblogsEntrySoap>(models.size());

		for (MicroblogsEntry model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new MicroblogsEntrySoap[soapModels.size()]);
	}

	public MicroblogsEntrySoap() {
	}

	public long getPrimaryKey() {
		return _microblogsEntryId;
	}

	public void setPrimaryKey(long pk) {
		setMicroblogsEntryId(pk);
	}

	public long getMicroblogsEntryId() {
		return _microblogsEntryId;
	}

	public void setMicroblogsEntryId(long microblogsEntryId) {
		_microblogsEntryId = microblogsEntryId;
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

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
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

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public int getType() {
		return _type;
	}

	public void setType(int type) {
		_type = type;
	}

	public long getReceiverUserId() {
		return _receiverUserId;
	}

	public void setReceiverUserId(long receiverUserId) {
		_receiverUserId = receiverUserId;
	}

	public long getReceiverMicroblogsEntryId() {
		return _receiverMicroblogsEntryId;
	}

	public void setReceiverMicroblogsEntryId(long receiverMicroblogsEntryId) {
		_receiverMicroblogsEntryId = receiverMicroblogsEntryId;
	}

	public int getSocialRelationType() {
		return _socialRelationType;
	}

	public void setSocialRelationType(int socialRelationType) {
		_socialRelationType = socialRelationType;
	}

	private long _microblogsEntryId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _creatorClassNameId;
	private long _creatorClassPK;
	private String _content;
	private int _type;
	private long _receiverUserId;
	private long _receiverMicroblogsEntryId;
	private int _socialRelationType;
}