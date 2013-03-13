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
 * This class is used by SOAP remote services, specifically {@link com.liferay.polls.service.http.PollsVoteServiceSoap}.
 *
 * @author    Juan Fern√°ndez
 * @see       com.liferay.polls.service.http.PollsVoteServiceSoap
 * @generated
 */
public class PollsVoteSoap implements Serializable {
	public static PollsVoteSoap toSoapModel(PollsVote model) {
		PollsVoteSoap soapModel = new PollsVoteSoap();

		soapModel.setPollsVoteId(model.getPollsVoteId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setPollsQuestionId(model.getPollsQuestionId());
		soapModel.setPollsChoiceId(model.getPollsChoiceId());
		soapModel.setVoteDate(model.getVoteDate());

		return soapModel;
	}

	public static PollsVoteSoap[] toSoapModels(PollsVote[] models) {
		PollsVoteSoap[] soapModels = new PollsVoteSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PollsVoteSoap[][] toSoapModels(PollsVote[][] models) {
		PollsVoteSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PollsVoteSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PollsVoteSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PollsVoteSoap[] toSoapModels(List<PollsVote> models) {
		List<PollsVoteSoap> soapModels = new ArrayList<PollsVoteSoap>(models.size());

		for (PollsVote model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PollsVoteSoap[soapModels.size()]);
	}

	public PollsVoteSoap() {
	}

	public long getPrimaryKey() {
		return _pollsVoteId;
	}

	public void setPrimaryKey(long pk) {
		setPollsVoteId(pk);
	}

	public long getPollsVoteId() {
		return _pollsVoteId;
	}

	public void setPollsVoteId(long pollsVoteId) {
		_pollsVoteId = pollsVoteId;
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

	public long getPollsQuestionId() {
		return _pollsQuestionId;
	}

	public void setPollsQuestionId(long pollsQuestionId) {
		_pollsQuestionId = pollsQuestionId;
	}

	public long getPollsChoiceId() {
		return _pollsChoiceId;
	}

	public void setPollsChoiceId(long pollsChoiceId) {
		_pollsChoiceId = pollsChoiceId;
	}

	public Date getVoteDate() {
		return _voteDate;
	}

	public void setVoteDate(Date voteDate) {
		_voteDate = voteDate;
	}

	private long _pollsVoteId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _pollsQuestionId;
	private long _pollsChoiceId;
	private Date _voteDate;
}