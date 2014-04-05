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

package com.liferay.portal.workflow.kaleo.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author Brian Wing Shun Chan
 * @generated
 */
public class KaleoTimerSoap implements Serializable {
	public static KaleoTimerSoap toSoapModel(KaleoTimer model) {
		KaleoTimerSoap soapModel = new KaleoTimerSoap();

		soapModel.setKaleoTimerId(model.getKaleoTimerId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoClassName(model.getKaleoClassName());
		soapModel.setKaleoClassPK(model.getKaleoClassPK());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setName(model.getName());
		soapModel.setBlocking(model.getBlocking());
		soapModel.setDescription(model.getDescription());
		soapModel.setDuration(model.getDuration());
		soapModel.setScale(model.getScale());
		soapModel.setRecurrenceDuration(model.getRecurrenceDuration());
		soapModel.setRecurrenceScale(model.getRecurrenceScale());

		return soapModel;
	}

	public static KaleoTimerSoap[] toSoapModels(KaleoTimer[] models) {
		KaleoTimerSoap[] soapModels = new KaleoTimerSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoTimerSoap[][] toSoapModels(KaleoTimer[][] models) {
		KaleoTimerSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoTimerSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoTimerSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoTimerSoap[] toSoapModels(List<KaleoTimer> models) {
		List<KaleoTimerSoap> soapModels = new ArrayList<KaleoTimerSoap>(models.size());

		for (KaleoTimer model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoTimerSoap[soapModels.size()]);
	}

	public KaleoTimerSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoTimerId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoTimerId(pk);
	}

	public long getKaleoTimerId() {
		return _kaleoTimerId;
	}

	public void setKaleoTimerId(long kaleoTimerId) {
		_kaleoTimerId = kaleoTimerId;
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

	public String getKaleoClassName() {
		return _kaleoClassName;
	}

	public void setKaleoClassName(String kaleoClassName) {
		_kaleoClassName = kaleoClassName;
	}

	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoClassPK = kaleoClassPK;
	}

	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public boolean getBlocking() {
		return _blocking;
	}

	public boolean isBlocking() {
		return _blocking;
	}

	public void setBlocking(boolean blocking) {
		_blocking = blocking;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public double getDuration() {
		return _duration;
	}

	public void setDuration(double duration) {
		_duration = duration;
	}

	public String getScale() {
		return _scale;
	}

	public void setScale(String scale) {
		_scale = scale;
	}

	public double getRecurrenceDuration() {
		return _recurrenceDuration;
	}

	public void setRecurrenceDuration(double recurrenceDuration) {
		_recurrenceDuration = recurrenceDuration;
	}

	public String getRecurrenceScale() {
		return _recurrenceScale;
	}

	public void setRecurrenceScale(String recurrenceScale) {
		_recurrenceScale = recurrenceScale;
	}

	private long _kaleoTimerId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionId;
	private String _name;
	private boolean _blocking;
	private String _description;
	private double _duration;
	private String _scale;
	private double _recurrenceDuration;
	private String _recurrenceScale;
}