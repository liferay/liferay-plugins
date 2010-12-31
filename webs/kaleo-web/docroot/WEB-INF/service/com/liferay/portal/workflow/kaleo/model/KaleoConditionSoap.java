/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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
 * <p>
 * This class is used by
 * {@link com.liferay.portal.workflow.kaleo.service.http.KaleoConditionServiceSoap}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.portal.workflow.kaleo.service.http.KaleoConditionServiceSoap
 * @generated
 */
public class KaleoConditionSoap implements Serializable {
	public static KaleoConditionSoap toSoapModel(KaleoCondition model) {
		KaleoConditionSoap soapModel = new KaleoConditionSoap();

		soapModel.setKaleoConditionId(model.getKaleoConditionId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setClassName(model.getClassName());
		soapModel.setClassPK(model.getClassPK());
		soapModel.setDescription(model.getDescription());
		soapModel.setScript(model.getScript());
		soapModel.setScriptLanguage(model.getScriptLanguage());

		return soapModel;
	}

	public static KaleoConditionSoap[] toSoapModels(KaleoCondition[] models) {
		KaleoConditionSoap[] soapModels = new KaleoConditionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoConditionSoap[][] toSoapModels(KaleoCondition[][] models) {
		KaleoConditionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoConditionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoConditionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoConditionSoap[] toSoapModels(List<KaleoCondition> models) {
		List<KaleoConditionSoap> soapModels = new ArrayList<KaleoConditionSoap>(models.size());

		for (KaleoCondition model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoConditionSoap[soapModels.size()]);
	}

	public KaleoConditionSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoConditionId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoConditionId(pk);
	}

	public long getKaleoConditionId() {
		return _kaleoConditionId;
	}

	public void setKaleoConditionId(long kaleoConditionId) {
		_kaleoConditionId = kaleoConditionId;
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

	public String getClassName() {
		return _className;
	}

	public void setClassName(String className) {
		_className = className;
	}

	public long getClassPK() {
		return _classPK;
	}

	public void setClassPK(long classPK) {
		_classPK = classPK;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public String getScript() {
		return _script;
	}

	public void setScript(String script) {
		_script = script;
	}

	public String getScriptLanguage() {
		return _scriptLanguage;
	}

	public void setScriptLanguage(String scriptLanguage) {
		_scriptLanguage = scriptLanguage;
	}

	private long _kaleoConditionId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _className;
	private long _classPK;
	private String _description;
	private String _script;
	private String _scriptLanguage;
}