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

package com.liferay.knowledgebase.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services, specifically {@link com.liferay.knowledgebase.service.http.KBStructureServiceSoap}.
 *
 * @author    Brian Wing Shun Chan
 * @see       com.liferay.knowledgebase.service.http.KBStructureServiceSoap
 * @generated
 */
public class KBStructureSoap implements Serializable {
	public static KBStructureSoap toSoapModel(KBStructure model) {
		KBStructureSoap soapModel = new KBStructureSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setKbStructureId(model.getKbStructureId());
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

	public static KBStructureSoap[] toSoapModels(KBStructure[] models) {
		KBStructureSoap[] soapModels = new KBStructureSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KBStructureSoap[][] toSoapModels(KBStructure[][] models) {
		KBStructureSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KBStructureSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KBStructureSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KBStructureSoap[] toSoapModels(List<KBStructure> models) {
		List<KBStructureSoap> soapModels = new ArrayList<KBStructureSoap>(models.size());

		for (KBStructure model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KBStructureSoap[soapModels.size()]);
	}

	public KBStructureSoap() {
	}

	public long getPrimaryKey() {
		return _kbStructureId;
	}

	public void setPrimaryKey(long pk) {
		setKbStructureId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getKbStructureId() {
		return _kbStructureId;
	}

	public void setKbStructureId(long kbStructureId) {
		_kbStructureId = kbStructureId;
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
	private long _kbStructureId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _title;
	private String _content;
}