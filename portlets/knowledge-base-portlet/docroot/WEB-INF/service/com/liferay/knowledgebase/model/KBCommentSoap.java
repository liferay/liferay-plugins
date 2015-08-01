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

package com.liferay.knowledgebase.model;

import aQute.bnd.annotation.ProviderType;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.knowledgebase.service.http.KBCommentServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledgebase.service.http.KBCommentServiceSoap
 * @generated
 */
@ProviderType
public class KBCommentSoap implements Serializable {
	public static KBCommentSoap toSoapModel(KBComment model) {
		KBCommentSoap soapModel = new KBCommentSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setKbCommentId(model.getKbCommentId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassNameId(model.getClassNameId());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setContent(model.getContent());
		soapModel.setUserRating(model.getUserRating());
		soapModel.setLastPublishDate(model.getLastPublishDate());
		soapModel.setStatus(model.getStatus());

		return soapModel;
	}

	public static KBCommentSoap[] toSoapModels(KBComment[] models) {
		KBCommentSoap[] soapModels = new KBCommentSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KBCommentSoap[][] toSoapModels(KBComment[][] models) {
		KBCommentSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KBCommentSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KBCommentSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KBCommentSoap[] toSoapModels(List<KBComment> models) {
		List<KBCommentSoap> soapModels = new ArrayList<KBCommentSoap>(models.size());

		for (KBComment model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KBCommentSoap[soapModels.size()]);
	}

	public KBCommentSoap() {
	}

	public long getPrimaryKey() {
		return _kbCommentId;
	}

	public void setPrimaryKey(long pk) {
		setKbCommentId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getKbCommentId() {
		return _kbCommentId;
	}

	public void setKbCommentId(long kbCommentId) {
		_kbCommentId = kbCommentId;
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

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public int getUserRating() {
		return _userRating;
	}

	public void setUserRating(int userRating) {
		_userRating = userRating;
	}

	public Date getLastPublishDate() {
		return _lastPublishDate;
	}

	public void setLastPublishDate(Date lastPublishDate) {
		_lastPublishDate = lastPublishDate;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	private String _uuid;
	private long _kbCommentId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _classNameId;
	private long _classPK;
	private String _content;
	private int _userRating;
	private Date _lastPublishDate;
	private int _status;
}