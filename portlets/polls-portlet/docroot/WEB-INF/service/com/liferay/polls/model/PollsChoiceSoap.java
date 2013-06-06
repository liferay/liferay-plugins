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
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.polls.service.http.PollsChoiceServiceSoap}.
 *
 * @author Juan Fern√°ndez
 * @see com.liferay.polls.service.http.PollsChoiceServiceSoap
 * @generated
 */
public class PollsChoiceSoap implements Serializable {
	public static PollsChoiceSoap toSoapModel(PollsChoice model) {
		PollsChoiceSoap soapModel = new PollsChoiceSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setPollsChoiceId(model.getPollsChoiceId());
		soapModel.setPollsQuestionId(model.getPollsQuestionId());
		soapModel.setName(model.getName());
		soapModel.setDescription(model.getDescription());

		return soapModel;
	}

	public static PollsChoiceSoap[] toSoapModels(PollsChoice[] models) {
		PollsChoiceSoap[] soapModels = new PollsChoiceSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static PollsChoiceSoap[][] toSoapModels(PollsChoice[][] models) {
		PollsChoiceSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new PollsChoiceSoap[models.length][models[0].length];
		}
		else {
			soapModels = new PollsChoiceSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static PollsChoiceSoap[] toSoapModels(List<PollsChoice> models) {
		List<PollsChoiceSoap> soapModels = new ArrayList<PollsChoiceSoap>(models.size());

		for (PollsChoice model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new PollsChoiceSoap[soapModels.size()]);
	}

	public PollsChoiceSoap() {
	}

	public long getPrimaryKey() {
		return _pollsChoiceId;
	}

	public void setPrimaryKey(long pk) {
		setPollsChoiceId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getPollsChoiceId() {
		return _pollsChoiceId;
	}

	public void setPollsChoiceId(long pollsChoiceId) {
		_pollsChoiceId = pollsChoiceId;
	}

	public long getPollsQuestionId() {
		return _pollsQuestionId;
	}

	public void setPollsQuestionId(long pollsQuestionId) {
		_pollsQuestionId = pollsQuestionId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	private String _uuid;
	private long _pollsChoiceId;
	private long _pollsQuestionId;
	private String _name;
	private String _description;
}