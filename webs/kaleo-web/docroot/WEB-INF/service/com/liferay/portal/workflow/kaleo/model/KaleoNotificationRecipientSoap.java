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

import aQute.bnd.annotation.ProviderType;

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
@ProviderType
public class KaleoNotificationRecipientSoap implements Serializable {
	public static KaleoNotificationRecipientSoap toSoapModel(
		KaleoNotificationRecipient model) {
		KaleoNotificationRecipientSoap soapModel = new KaleoNotificationRecipientSoap();

		soapModel.setKaleoNotificationRecipientId(model.getKaleoNotificationRecipientId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setKaleoDefinitionId(model.getKaleoDefinitionId());
		soapModel.setKaleoNotificationId(model.getKaleoNotificationId());
		soapModel.setRecipientClassName(model.getRecipientClassName());
		soapModel.setRecipientClassPK(model.getRecipientClassPK());
		soapModel.setRecipientRoleType(model.getRecipientRoleType());
		soapModel.setRecipientScript(model.getRecipientScript());
		soapModel.setRecipientScriptLanguage(model.getRecipientScriptLanguage());
		soapModel.setRecipientScriptRequiredContexts(model.getRecipientScriptRequiredContexts());
		soapModel.setAddress(model.getAddress());
		soapModel.setNotificationReceptionType(model.getNotificationReceptionType());

		return soapModel;
	}

	public static KaleoNotificationRecipientSoap[] toSoapModels(
		KaleoNotificationRecipient[] models) {
		KaleoNotificationRecipientSoap[] soapModels = new KaleoNotificationRecipientSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KaleoNotificationRecipientSoap[][] toSoapModels(
		KaleoNotificationRecipient[][] models) {
		KaleoNotificationRecipientSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KaleoNotificationRecipientSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KaleoNotificationRecipientSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KaleoNotificationRecipientSoap[] toSoapModels(
		List<KaleoNotificationRecipient> models) {
		List<KaleoNotificationRecipientSoap> soapModels = new ArrayList<KaleoNotificationRecipientSoap>(models.size());

		for (KaleoNotificationRecipient model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KaleoNotificationRecipientSoap[soapModels.size()]);
	}

	public KaleoNotificationRecipientSoap() {
	}

	public long getPrimaryKey() {
		return _kaleoNotificationRecipientId;
	}

	public void setPrimaryKey(long pk) {
		setKaleoNotificationRecipientId(pk);
	}

	public long getKaleoNotificationRecipientId() {
		return _kaleoNotificationRecipientId;
	}

	public void setKaleoNotificationRecipientId(
		long kaleoNotificationRecipientId) {
		_kaleoNotificationRecipientId = kaleoNotificationRecipientId;
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

	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;
	}

	public long getKaleoNotificationId() {
		return _kaleoNotificationId;
	}

	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotificationId = kaleoNotificationId;
	}

	public String getRecipientClassName() {
		return _recipientClassName;
	}

	public void setRecipientClassName(String recipientClassName) {
		_recipientClassName = recipientClassName;
	}

	public long getRecipientClassPK() {
		return _recipientClassPK;
	}

	public void setRecipientClassPK(long recipientClassPK) {
		_recipientClassPK = recipientClassPK;
	}

	public int getRecipientRoleType() {
		return _recipientRoleType;
	}

	public void setRecipientRoleType(int recipientRoleType) {
		_recipientRoleType = recipientRoleType;
	}

	public String getRecipientScript() {
		return _recipientScript;
	}

	public void setRecipientScript(String recipientScript) {
		_recipientScript = recipientScript;
	}

	public String getRecipientScriptLanguage() {
		return _recipientScriptLanguage;
	}

	public void setRecipientScriptLanguage(String recipientScriptLanguage) {
		_recipientScriptLanguage = recipientScriptLanguage;
	}

	public String getRecipientScriptRequiredContexts() {
		return _recipientScriptRequiredContexts;
	}

	public void setRecipientScriptRequiredContexts(
		String recipientScriptRequiredContexts) {
		_recipientScriptRequiredContexts = recipientScriptRequiredContexts;
	}

	public String getAddress() {
		return _address;
	}

	public void setAddress(String address) {
		_address = address;
	}

	public String getNotificationReceptionType() {
		return _notificationReceptionType;
	}

	public void setNotificationReceptionType(String notificationReceptionType) {
		_notificationReceptionType = notificationReceptionType;
	}

	private long _kaleoNotificationRecipientId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _kaleoDefinitionId;
	private long _kaleoNotificationId;
	private String _recipientClassName;
	private long _recipientClassPK;
	private int _recipientRoleType;
	private String _recipientScript;
	private String _recipientScriptLanguage;
	private String _recipientScriptRequiredContexts;
	private String _address;
	private String _notificationReceptionType;
}