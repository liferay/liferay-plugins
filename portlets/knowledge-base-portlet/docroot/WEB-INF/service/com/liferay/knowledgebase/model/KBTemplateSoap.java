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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.knowledgebase.service.http.KBTemplateServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledgebase.service.http.KBTemplateServiceSoap
 * @generated
 */
public class KBTemplateSoap implements Serializable {
	public static KBTemplateSoap toSoapModel(KBTemplate model) {
		KBTemplateSoap soapModel = new KBTemplateSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setKbTemplateId(model.getKbTemplateId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setTitle(model.getTitle());
		soapModel.setContent(model.getContent());

		return soapModel;
	}

	public static KBTemplateSoap[] toSoapModels(KBTemplate[] models) {
		KBTemplateSoap[] soapModels = new KBTemplateSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KBTemplateSoap[][] toSoapModels(KBTemplate[][] models) {
		KBTemplateSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KBTemplateSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KBTemplateSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KBTemplateSoap[] toSoapModels(List<KBTemplate> models) {
		List<KBTemplateSoap> soapModels = new ArrayList<KBTemplateSoap>(models.size());

		for (KBTemplate model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KBTemplateSoap[soapModels.size()]);
	}

	public KBTemplateSoap() {
	}

	public long getPrimaryKey() {
		return _kbTemplateId;
	}

	public void setPrimaryKey(long pk) {
		setKbTemplateId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getKbTemplateId() {
		return _kbTemplateId;
	}

	public void setKbTemplateId(long kbTemplateId) {
		_kbTemplateId = kbTemplateId;
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

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	private String _uuid;
	private long _kbTemplateId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _content;
}