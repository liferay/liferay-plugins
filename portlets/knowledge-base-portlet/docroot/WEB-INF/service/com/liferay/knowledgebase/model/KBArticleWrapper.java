/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KBArticle}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KBArticle
 * @generated
 */
public class KBArticleWrapper implements KBArticle, ModelWrapper<KBArticle> {
	public KBArticleWrapper(KBArticle kbArticle) {
		_kbArticle = kbArticle;
	}

	public Class<?> getModelClass() {
		return KBArticle.class;
	}

	public String getModelClassName() {
		return KBArticle.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("kbArticleId", getKbArticleId());
		attributes.put("resourcePrimKey", getResourcePrimKey());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("rootResourcePrimKey", getRootResourcePrimKey());
		attributes.put("parentResourcePrimKey", getParentResourcePrimKey());
		attributes.put("version", getVersion());
		attributes.put("title", getTitle());
		attributes.put("content", getContent());
		attributes.put("description", getDescription());
		attributes.put("priority", getPriority());
		attributes.put("sections", getSections());
		attributes.put("viewCount", getViewCount());
		attributes.put("latest", getLatest());
		attributes.put("main", getMain());
		attributes.put("status", getStatus());
		attributes.put("statusByUserId", getStatusByUserId());
		attributes.put("statusByUserName", getStatusByUserName());
		attributes.put("statusDate", getStatusDate());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long kbArticleId = (Long)attributes.get("kbArticleId");

		if (kbArticleId != null) {
			setKbArticleId(kbArticleId);
		}

		Long resourcePrimKey = (Long)attributes.get("resourcePrimKey");

		if (resourcePrimKey != null) {
			setResourcePrimKey(resourcePrimKey);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long userId = (Long)attributes.get("userId");

		if (userId != null) {
			setUserId(userId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long rootResourcePrimKey = (Long)attributes.get("rootResourcePrimKey");

		if (rootResourcePrimKey != null) {
			setRootResourcePrimKey(rootResourcePrimKey);
		}

		Long parentResourcePrimKey = (Long)attributes.get(
				"parentResourcePrimKey");

		if (parentResourcePrimKey != null) {
			setParentResourcePrimKey(parentResourcePrimKey);
		}

		Integer version = (Integer)attributes.get("version");

		if (version != null) {
			setVersion(version);
		}

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}

		Double priority = (Double)attributes.get("priority");

		if (priority != null) {
			setPriority(priority);
		}

		String sections = (String)attributes.get("sections");

		if (sections != null) {
			setSections(sections);
		}

		Integer viewCount = (Integer)attributes.get("viewCount");

		if (viewCount != null) {
			setViewCount(viewCount);
		}

		Boolean latest = (Boolean)attributes.get("latest");

		if (latest != null) {
			setLatest(latest);
		}

		Boolean main = (Boolean)attributes.get("main");

		if (main != null) {
			setMain(main);
		}

		Integer status = (Integer)attributes.get("status");

		if (status != null) {
			setStatus(status);
		}

		Long statusByUserId = (Long)attributes.get("statusByUserId");

		if (statusByUserId != null) {
			setStatusByUserId(statusByUserId);
		}

		String statusByUserName = (String)attributes.get("statusByUserName");

		if (statusByUserName != null) {
			setStatusByUserName(statusByUserName);
		}

		Date statusDate = (Date)attributes.get("statusDate");

		if (statusDate != null) {
			setStatusDate(statusDate);
		}
	}

	/**
	* Returns the primary key of this k b article.
	*
	* @return the primary key of this k b article
	*/
	public long getPrimaryKey() {
		return _kbArticle.getPrimaryKey();
	}

	/**
	* Sets the primary key of this k b article.
	*
	* @param primaryKey the primary key of this k b article
	*/
	public void setPrimaryKey(long primaryKey) {
		_kbArticle.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this k b article.
	*
	* @return the uuid of this k b article
	*/
	public java.lang.String getUuid() {
		return _kbArticle.getUuid();
	}

	/**
	* Sets the uuid of this k b article.
	*
	* @param uuid the uuid of this k b article
	*/
	public void setUuid(java.lang.String uuid) {
		_kbArticle.setUuid(uuid);
	}

	/**
	* Returns the kb article ID of this k b article.
	*
	* @return the kb article ID of this k b article
	*/
	public long getKbArticleId() {
		return _kbArticle.getKbArticleId();
	}

	/**
	* Sets the kb article ID of this k b article.
	*
	* @param kbArticleId the kb article ID of this k b article
	*/
	public void setKbArticleId(long kbArticleId) {
		_kbArticle.setKbArticleId(kbArticleId);
	}

	/**
	* Returns the resource prim key of this k b article.
	*
	* @return the resource prim key of this k b article
	*/
	public long getResourcePrimKey() {
		return _kbArticle.getResourcePrimKey();
	}

	/**
	* Sets the resource prim key of this k b article.
	*
	* @param resourcePrimKey the resource prim key of this k b article
	*/
	public void setResourcePrimKey(long resourcePrimKey) {
		_kbArticle.setResourcePrimKey(resourcePrimKey);
	}

	public boolean isResourceMain() {
		return _kbArticle.isResourceMain();
	}

	/**
	* Returns the group ID of this k b article.
	*
	* @return the group ID of this k b article
	*/
	public long getGroupId() {
		return _kbArticle.getGroupId();
	}

	/**
	* Sets the group ID of this k b article.
	*
	* @param groupId the group ID of this k b article
	*/
	public void setGroupId(long groupId) {
		_kbArticle.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this k b article.
	*
	* @return the company ID of this k b article
	*/
	public long getCompanyId() {
		return _kbArticle.getCompanyId();
	}

	/**
	* Sets the company ID of this k b article.
	*
	* @param companyId the company ID of this k b article
	*/
	public void setCompanyId(long companyId) {
		_kbArticle.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this k b article.
	*
	* @return the user ID of this k b article
	*/
	public long getUserId() {
		return _kbArticle.getUserId();
	}

	/**
	* Sets the user ID of this k b article.
	*
	* @param userId the user ID of this k b article
	*/
	public void setUserId(long userId) {
		_kbArticle.setUserId(userId);
	}

	/**
	* Returns the user uuid of this k b article.
	*
	* @return the user uuid of this k b article
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticle.getUserUuid();
	}

	/**
	* Sets the user uuid of this k b article.
	*
	* @param userUuid the user uuid of this k b article
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kbArticle.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this k b article.
	*
	* @return the user name of this k b article
	*/
	public java.lang.String getUserName() {
		return _kbArticle.getUserName();
	}

	/**
	* Sets the user name of this k b article.
	*
	* @param userName the user name of this k b article
	*/
	public void setUserName(java.lang.String userName) {
		_kbArticle.setUserName(userName);
	}

	/**
	* Returns the create date of this k b article.
	*
	* @return the create date of this k b article
	*/
	public java.util.Date getCreateDate() {
		return _kbArticle.getCreateDate();
	}

	/**
	* Sets the create date of this k b article.
	*
	* @param createDate the create date of this k b article
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kbArticle.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this k b article.
	*
	* @return the modified date of this k b article
	*/
	public java.util.Date getModifiedDate() {
		return _kbArticle.getModifiedDate();
	}

	/**
	* Sets the modified date of this k b article.
	*
	* @param modifiedDate the modified date of this k b article
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kbArticle.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the root resource prim key of this k b article.
	*
	* @return the root resource prim key of this k b article
	*/
	public long getRootResourcePrimKey() {
		return _kbArticle.getRootResourcePrimKey();
	}

	/**
	* Sets the root resource prim key of this k b article.
	*
	* @param rootResourcePrimKey the root resource prim key of this k b article
	*/
	public void setRootResourcePrimKey(long rootResourcePrimKey) {
		_kbArticle.setRootResourcePrimKey(rootResourcePrimKey);
	}

	/**
	* Returns the parent resource prim key of this k b article.
	*
	* @return the parent resource prim key of this k b article
	*/
	public long getParentResourcePrimKey() {
		return _kbArticle.getParentResourcePrimKey();
	}

	/**
	* Sets the parent resource prim key of this k b article.
	*
	* @param parentResourcePrimKey the parent resource prim key of this k b article
	*/
	public void setParentResourcePrimKey(long parentResourcePrimKey) {
		_kbArticle.setParentResourcePrimKey(parentResourcePrimKey);
	}

	/**
	* Returns the version of this k b article.
	*
	* @return the version of this k b article
	*/
	public int getVersion() {
		return _kbArticle.getVersion();
	}

	/**
	* Sets the version of this k b article.
	*
	* @param version the version of this k b article
	*/
	public void setVersion(int version) {
		_kbArticle.setVersion(version);
	}

	/**
	* Returns the title of this k b article.
	*
	* @return the title of this k b article
	*/
	public java.lang.String getTitle() {
		return _kbArticle.getTitle();
	}

	/**
	* Sets the title of this k b article.
	*
	* @param title the title of this k b article
	*/
	public void setTitle(java.lang.String title) {
		_kbArticle.setTitle(title);
	}

	/**
	* Returns the content of this k b article.
	*
	* @return the content of this k b article
	*/
	public java.lang.String getContent() {
		return _kbArticle.getContent();
	}

	/**
	* Sets the content of this k b article.
	*
	* @param content the content of this k b article
	*/
	public void setContent(java.lang.String content) {
		_kbArticle.setContent(content);
	}

	/**
	* Returns the description of this k b article.
	*
	* @return the description of this k b article
	*/
	public java.lang.String getDescription() {
		return _kbArticle.getDescription();
	}

	/**
	* Sets the description of this k b article.
	*
	* @param description the description of this k b article
	*/
	public void setDescription(java.lang.String description) {
		_kbArticle.setDescription(description);
	}

	/**
	* Returns the priority of this k b article.
	*
	* @return the priority of this k b article
	*/
	public double getPriority() {
		return _kbArticle.getPriority();
	}

	/**
	* Sets the priority of this k b article.
	*
	* @param priority the priority of this k b article
	*/
	public void setPriority(double priority) {
		_kbArticle.setPriority(priority);
	}

	/**
	* Returns the sections of this k b article.
	*
	* @return the sections of this k b article
	*/
	public java.lang.String getSections() {
		return _kbArticle.getSections();
	}

	/**
	* Sets the sections of this k b article.
	*
	* @param sections the sections of this k b article
	*/
	public void setSections(java.lang.String sections) {
		_kbArticle.setSections(sections);
	}

	/**
	* Returns the view count of this k b article.
	*
	* @return the view count of this k b article
	*/
	public int getViewCount() {
		return _kbArticle.getViewCount();
	}

	/**
	* Sets the view count of this k b article.
	*
	* @param viewCount the view count of this k b article
	*/
	public void setViewCount(int viewCount) {
		_kbArticle.setViewCount(viewCount);
	}

	/**
	* Returns the latest of this k b article.
	*
	* @return the latest of this k b article
	*/
	public boolean getLatest() {
		return _kbArticle.getLatest();
	}

	/**
	* Returns <code>true</code> if this k b article is latest.
	*
	* @return <code>true</code> if this k b article is latest; <code>false</code> otherwise
	*/
	public boolean isLatest() {
		return _kbArticle.isLatest();
	}

	/**
	* Sets whether this k b article is latest.
	*
	* @param latest the latest of this k b article
	*/
	public void setLatest(boolean latest) {
		_kbArticle.setLatest(latest);
	}

	/**
	* Returns the main of this k b article.
	*
	* @return the main of this k b article
	*/
	public boolean getMain() {
		return _kbArticle.getMain();
	}

	/**
	* Returns <code>true</code> if this k b article is main.
	*
	* @return <code>true</code> if this k b article is main; <code>false</code> otherwise
	*/
	public boolean isMain() {
		return _kbArticle.isMain();
	}

	/**
	* Sets whether this k b article is main.
	*
	* @param main the main of this k b article
	*/
	public void setMain(boolean main) {
		_kbArticle.setMain(main);
	}

	/**
	* Returns the status of this k b article.
	*
	* @return the status of this k b article
	*/
	public int getStatus() {
		return _kbArticle.getStatus();
	}

	/**
	* Sets the status of this k b article.
	*
	* @param status the status of this k b article
	*/
	public void setStatus(int status) {
		_kbArticle.setStatus(status);
	}

	/**
	* Returns the status by user ID of this k b article.
	*
	* @return the status by user ID of this k b article
	*/
	public long getStatusByUserId() {
		return _kbArticle.getStatusByUserId();
	}

	/**
	* Sets the status by user ID of this k b article.
	*
	* @param statusByUserId the status by user ID of this k b article
	*/
	public void setStatusByUserId(long statusByUserId) {
		_kbArticle.setStatusByUserId(statusByUserId);
	}

	/**
	* Returns the status by user uuid of this k b article.
	*
	* @return the status by user uuid of this k b article
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getStatusByUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbArticle.getStatusByUserUuid();
	}

	/**
	* Sets the status by user uuid of this k b article.
	*
	* @param statusByUserUuid the status by user uuid of this k b article
	*/
	public void setStatusByUserUuid(java.lang.String statusByUserUuid) {
		_kbArticle.setStatusByUserUuid(statusByUserUuid);
	}

	/**
	* Returns the status by user name of this k b article.
	*
	* @return the status by user name of this k b article
	*/
	public java.lang.String getStatusByUserName() {
		return _kbArticle.getStatusByUserName();
	}

	/**
	* Sets the status by user name of this k b article.
	*
	* @param statusByUserName the status by user name of this k b article
	*/
	public void setStatusByUserName(java.lang.String statusByUserName) {
		_kbArticle.setStatusByUserName(statusByUserName);
	}

	/**
	* Returns the status date of this k b article.
	*
	* @return the status date of this k b article
	*/
	public java.util.Date getStatusDate() {
		return _kbArticle.getStatusDate();
	}

	/**
	* Sets the status date of this k b article.
	*
	* @param statusDate the status date of this k b article
	*/
	public void setStatusDate(java.util.Date statusDate) {
		_kbArticle.setStatusDate(statusDate);
	}

	/**
	* @deprecated Renamed to {@link #isApproved()}
	*/
	public boolean getApproved() {
		return _kbArticle.getApproved();
	}

	/**
	* Returns <code>true</code> if this k b article is approved.
	*
	* @return <code>true</code> if this k b article is approved; <code>false</code> otherwise
	*/
	public boolean isApproved() {
		return _kbArticle.isApproved();
	}

	/**
	* Returns <code>true</code> if this k b article is a draft.
	*
	* @return <code>true</code> if this k b article is a draft; <code>false</code> otherwise
	*/
	public boolean isDraft() {
		return _kbArticle.isDraft();
	}

	/**
	* Returns <code>true</code> if this k b article is expired.
	*
	* @return <code>true</code> if this k b article is expired; <code>false</code> otherwise
	*/
	public boolean isExpired() {
		return _kbArticle.isExpired();
	}

	/**
	* Returns <code>true</code> if this k b article is in the Recycle Bin.
	*
	* @return <code>true</code> if this k b article is in the Recycle Bin; <code>false</code> otherwise
	*/
	public boolean isInTrash() {
		return _kbArticle.isInTrash();
	}

	/**
	* Returns <code>true</code> if this k b article is pending.
	*
	* @return <code>true</code> if this k b article is pending; <code>false</code> otherwise
	*/
	public boolean isPending() {
		return _kbArticle.isPending();
	}

	public boolean isNew() {
		return _kbArticle.isNew();
	}

	public void setNew(boolean n) {
		_kbArticle.setNew(n);
	}

	public boolean isCachedModel() {
		return _kbArticle.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kbArticle.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kbArticle.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kbArticle.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kbArticle.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kbArticle.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kbArticle.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KBArticleWrapper((KBArticle)_kbArticle.clone());
	}

	public int compareTo(com.liferay.knowledgebase.model.KBArticle kbArticle) {
		return _kbArticle.compareTo(kbArticle);
	}

	@Override
	public int hashCode() {
		return _kbArticle.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.knowledgebase.model.KBArticle> toCacheModel() {
		return _kbArticle.toCacheModel();
	}

	public com.liferay.knowledgebase.model.KBArticle toEscapedModel() {
		return new KBArticleWrapper(_kbArticle.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kbArticle.toString();
	}

	public java.lang.String toXmlString() {
		return _kbArticle.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kbArticle.persist();
	}

	public java.lang.String getAttachmentsDirName() {
		return _kbArticle.getAttachmentsDirName();
	}

	public java.lang.String[] getAttachmentsFileNames()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbArticle.getAttachmentsFileNames();
	}

	public long getClassPK() {
		return _kbArticle.getClassPK();
	}

	public boolean isFirstVersion() {
		return _kbArticle.isFirstVersion();
	}

	public boolean isRoot() {
		return _kbArticle.isRoot();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KBArticle getWrappedKBArticle() {
		return _kbArticle;
	}

	public KBArticle getWrappedModel() {
		return _kbArticle;
	}

	public void resetOriginalValues() {
		_kbArticle.resetOriginalValues();
	}

	private KBArticle _kbArticle;
}