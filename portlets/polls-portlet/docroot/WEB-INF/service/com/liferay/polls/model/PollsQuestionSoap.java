/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.polls.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.polls.service.http.PollsQuestionServiceSoap}.
 *
 * @author    Juan Fern√°ndez
 * @see       com.liferay.polls.service.http.PollsQuestionServiceSoap
 * @generated
 */
public class PollsQuestionSoap implements Serializable {
	public static PollsQuestionSoap toSoapModel(PollsQuestion model) {
		PollsQuestionSoap soapModel = new PollsQuestionSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPollsQuestionId(model.getPollsQuestionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setDescription(model.getDescription());
		soapModel.setExpirationDate(model.getExpirationDate());
		soapModel.setLastVoteDate(model.getLastVoteDate());

		return soapModel;
	}

	public static PollsQuestionSoap[] toSoapModels(PollsQuestion[] models) {
		PollsQuestionSoap[] soapModels = new PollsQuestionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PollsQuestionSoap[][] toSoapModels(PollsQuestion[][] models) {
		PollsQuestionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PollsQuestionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PollsQuestionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PollsQuestionSoap[] toSoapModels(List<PollsQuestion> models) {
		List<PollsQuestionSoap> soapModels = new ArrayList<PollsQuestionSoap>(models.size());

		for (PollsQuestion model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PollsQuestionSoap[soapModels.size()]);
	}

	public PollsQuestionSoap() {
	}

	public long getPrimaryKey() {
		return _pollsQuestionId;
	}

	public void setPrimaryKey(long pk) {
		setPollsQuestionId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPollsQuestionId() {
		return _pollsQuestionId;
	}

	public void setPollsQuestionId(long pollsQuestionId) {
		_pollsQuestionId = pollsQuestionId;
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

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public Date getExpirationDate() {
		return _expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		_expirationDate = expirationDate;
	}

	public Date getLastVoteDate() {
		return _lastVoteDate;
	}

	public void setLastVoteDate(Date lastVoteDate) {
		_lastVoteDate = lastVoteDate;
	}

	private String _uuid;
	private long _pollsQuestionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _description;
	private Date _expirationDate;
	private Date _lastVoteDate;
}