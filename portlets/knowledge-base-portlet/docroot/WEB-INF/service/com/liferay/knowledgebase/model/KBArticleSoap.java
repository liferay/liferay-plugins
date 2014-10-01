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
 * This class is used by SOAP remote services, specifically {@link com.liferay.knowledgebase.service.http.KBArticleServiceSoap}.
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.knowledgebase.service.http.KBArticleServiceSoap
 * @generated
 */
public class KBArticleSoap implements Serializable {
	public static KBArticleSoap toSoapModel(KBArticle model) {
		KBArticleSoap soapModel = new KBArticleSoap();

		soapModel.setUuid(model.getUuid());
		soapModel.setKbArticleId(model.getKbArticleId());
		soapModel.setResourcePrimKey(model.getResourcePrimKey());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setUserId(model.getUserId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setRootResourcePrimKey(model.getRootResourcePrimKey());
		soapModel.setParentResourceClassNameId(model.getParentResourceClassNameId());
		soapModel.setParentResourcePrimKey(model.getParentResourcePrimKey());
		soapModel.setKbFolderId(model.getKbFolderId());
		soapModel.setVersion(model.getVersion());
		soapModel.setTitle(model.getTitle());
		soapModel.setUrlTitle(model.getUrlTitle());
		soapModel.setContent(model.getContent());
		soapModel.setDescription(model.getDescription());
		soapModel.setPriority(model.getPriority());
		soapModel.setSections(model.getSections());
		soapModel.setViewCount(model.getViewCount());
		soapModel.setLatest(model.getLatest());
		soapModel.setMain(model.getMain());
		soapModel.setSourceURL(model.getSourceURL());
		soapModel.setStatus(model.getStatus());
		soapModel.setStatusByUserId(model.getStatusByUserId());
		soapModel.setStatusByUserName(model.getStatusByUserName());
		soapModel.setStatusDate(model.getStatusDate());

		return soapModel;
	}

	public static KBArticleSoap[] toSoapModels(KBArticle[] models) {
		KBArticleSoap[] soapModels = new KBArticleSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static KBArticleSoap[][] toSoapModels(KBArticle[][] models) {
		KBArticleSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new KBArticleSoap[models.length][models[0].length];
		}
		else {
			soapModels = new KBArticleSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static KBArticleSoap[] toSoapModels(List<KBArticle> models) {
		List<KBArticleSoap> soapModels = new ArrayList<KBArticleSoap>(models.size());

		for (KBArticle model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new KBArticleSoap[soapModels.size()]);
	}

	public KBArticleSoap() {
	}

	public long getPrimaryKey() {
		return _kbArticleId;
	}

	public void setPrimaryKey(long pk) {
		setKbArticleId(pk);
	}

	public String getUuid() {
		return _uuid;
	}

	public void setUuid(String uuid) {
		_uuid = uuid;
	}

	public long getKbArticleId() {
		return _kbArticleId;
	}

	public void setKbArticleId(long kbArticleId) {
		_kbArticleId = kbArticleId;
	}

	public long getResourcePrimKey() {
		return _resourcePrimKey;
	}

	public void setResourcePrimKey(long resourcePrimKey) {
		_resourcePrimKey = resourcePrimKey;
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

	public long getRootResourcePrimKey() {
		return _rootResourcePrimKey;
	}

	public void setRootResourcePrimKey(long rootResourcePrimKey) {
		_rootResourcePrimKey = rootResourcePrimKey;
	}

	public long getParentResourceClassNameId() {
		return _parentResourceClassNameId;
	}

	public void setParentResourceClassNameId(long parentResourceClassNameId) {
		_parentResourceClassNameId = parentResourceClassNameId;
	}

	public long getParentResourcePrimKey() {
		return _parentResourcePrimKey;
	}

	public void setParentResourcePrimKey(long parentResourcePrimKey) {
		_parentResourcePrimKey = parentResourcePrimKey;
	}

	public long getKbFolderId() {
		return _kbFolderId;
	}

	public void setKbFolderId(long kbFolderId) {
		_kbFolderId = kbFolderId;
	}

	public int getVersion() {
		return _version;
	}

	public void setVersion(int version) {
		_version = version;
	}

	public String getTitle() {
		return _title;
	}

	public void setTitle(String title) {
		_title = title;
	}

	public String getUrlTitle() {
		return _urlTitle;
	}

	public void setUrlTitle(String urlTitle) {
		_urlTitle = urlTitle;
	}

	public String getContent() {
		return _content;
	}

	public void setContent(String content) {
		_content = content;
	}

	public String getDescription() {
		return _description;
	}

	public void setDescription(String description) {
		_description = description;
	}

	public double getPriority() {
		return _priority;
	}

	public void setPriority(double priority) {
		_priority = priority;
	}

	public String getSections() {
		return _sections;
	}

	public void setSections(String sections) {
		_sections = sections;
	}

	public int getViewCount() {
		return _viewCount;
	}

	public void setViewCount(int viewCount) {
		_viewCount = viewCount;
	}

	public boolean getLatest() {
		return _latest;
	}

	public boolean isLatest() {
		return _latest;
	}

	public void setLatest(boolean latest) {
		_latest = latest;
	}

	public boolean getMain() {
		return _main;
	}

	public boolean isMain() {
		return _main;
	}

	public void setMain(boolean main) {
		_main = main;
	}

	public String getSourceURL() {
		return _sourceURL;
	}

	public void setSourceURL(String sourceURL) {
		_sourceURL = sourceURL;
	}

	public int getStatus() {
		return _status;
	}

	public void setStatus(int status) {
		_status = status;
	}

	public long getStatusByUserId() {
		return _statusByUserId;
	}

	public void setStatusByUserId(long statusByUserId) {
		_statusByUserId = statusByUserId;
	}

	public String getStatusByUserName() {
		return _statusByUserName;
	}

	public void setStatusByUserName(String statusByUserName) {
		_statusByUserName = statusByUserName;
	}

	public Date getStatusDate() {
		return _statusDate;
	}

	public void setStatusDate(Date statusDate) {
		_statusDate = statusDate;
	}

	private String _uuid;
	private long _kbArticleId;
	private long _resourcePrimKey;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private long _rootResourcePrimKey;
	private long _parentResourceClassNameId;
	private long _parentResourcePrimKey;
	private long _kbFolderId;
	private int _version;
	private String _title;
	private String _urlTitle;
	private String _content;
	private String _description;
	private double _priority;
	private String _sections;
	private int _viewCount;
	private boolean _latest;
	private boolean _main;
	private String _sourceURL;
	private int _status;
	private long _statusByUserId;
	private String _statusByUserName;
	private Date _statusDate;
}