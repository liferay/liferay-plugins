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

import com.liferay.portal.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KBFolder}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBFolder
 * @generated
 */
public class KBFolderWrapper implements KBFolder, ModelWrapper<KBFolder> {
	public KBFolderWrapper(KBFolder kbFolder) {
		_kbFolder = kbFolder;
	}

	@Override
	public Class<?> getModelClass() {
		return KBFolder.class;
	}

	@Override
	public String getModelClassName() {
		return KBFolder.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("kbFolderId", getKbFolderId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("parentKBFolderId", getParentKBFolderId());
		attributes.put("name", getName());
		attributes.put("urlTitle", getUrlTitle());
		attributes.put("description", getDescription());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long kbFolderId = (Long)attributes.get("kbFolderId");

		if (kbFolderId != null) {
			setKbFolderId(kbFolderId);
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

		Long parentKBFolderId = (Long)attributes.get("parentKBFolderId");

		if (parentKBFolderId != null) {
			setParentKBFolderId(parentKBFolderId);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String urlTitle = (String)attributes.get("urlTitle");

		if (urlTitle != null) {
			setUrlTitle(urlTitle);
		}

		String description = (String)attributes.get("description");

		if (description != null) {
			setDescription(description);
		}
	}

	/**
	* Returns the primary key of this k b folder.
	*
	* @return the primary key of this k b folder
	*/
	@Override
	public long getPrimaryKey() {
		return _kbFolder.getPrimaryKey();
	}

	/**
	* Sets the primary key of this k b folder.
	*
	* @param primaryKey the primary key of this k b folder
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kbFolder.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this k b folder.
	*
	* @return the uuid of this k b folder
	*/
	@Override
	public java.lang.String getUuid() {
		return _kbFolder.getUuid();
	}

	/**
	* Sets the uuid of this k b folder.
	*
	* @param uuid the uuid of this k b folder
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_kbFolder.setUuid(uuid);
	}

	/**
	* Returns the kb folder ID of this k b folder.
	*
	* @return the kb folder ID of this k b folder
	*/
	@Override
	public long getKbFolderId() {
		return _kbFolder.getKbFolderId();
	}

	/**
	* Sets the kb folder ID of this k b folder.
	*
	* @param kbFolderId the kb folder ID of this k b folder
	*/
	@Override
	public void setKbFolderId(long kbFolderId) {
		_kbFolder.setKbFolderId(kbFolderId);
	}

	/**
	* Returns the group ID of this k b folder.
	*
	* @return the group ID of this k b folder
	*/
	@Override
	public long getGroupId() {
		return _kbFolder.getGroupId();
	}

	/**
	* Sets the group ID of this k b folder.
	*
	* @param groupId the group ID of this k b folder
	*/
	@Override
	public void setGroupId(long groupId) {
		_kbFolder.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this k b folder.
	*
	* @return the company ID of this k b folder
	*/
	@Override
	public long getCompanyId() {
		return _kbFolder.getCompanyId();
	}

	/**
	* Sets the company ID of this k b folder.
	*
	* @param companyId the company ID of this k b folder
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kbFolder.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this k b folder.
	*
	* @return the user ID of this k b folder
	*/
	@Override
	public long getUserId() {
		return _kbFolder.getUserId();
	}

	/**
	* Sets the user ID of this k b folder.
	*
	* @param userId the user ID of this k b folder
	*/
	@Override
	public void setUserId(long userId) {
		_kbFolder.setUserId(userId);
	}

	/**
	* Returns the user uuid of this k b folder.
	*
	* @return the user uuid of this k b folder
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbFolder.getUserUuid();
	}

	/**
	* Sets the user uuid of this k b folder.
	*
	* @param userUuid the user uuid of this k b folder
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kbFolder.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this k b folder.
	*
	* @return the user name of this k b folder
	*/
	@Override
	public java.lang.String getUserName() {
		return _kbFolder.getUserName();
	}

	/**
	* Sets the user name of this k b folder.
	*
	* @param userName the user name of this k b folder
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kbFolder.setUserName(userName);
	}

	/**
	* Returns the create date of this k b folder.
	*
	* @return the create date of this k b folder
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kbFolder.getCreateDate();
	}

	/**
	* Sets the create date of this k b folder.
	*
	* @param createDate the create date of this k b folder
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kbFolder.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this k b folder.
	*
	* @return the modified date of this k b folder
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kbFolder.getModifiedDate();
	}

	/**
	* Sets the modified date of this k b folder.
	*
	* @param modifiedDate the modified date of this k b folder
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kbFolder.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the parent k b folder ID of this k b folder.
	*
	* @return the parent k b folder ID of this k b folder
	*/
	@Override
	public long getParentKBFolderId() {
		return _kbFolder.getParentKBFolderId();
	}

	/**
	* Sets the parent k b folder ID of this k b folder.
	*
	* @param parentKBFolderId the parent k b folder ID of this k b folder
	*/
	@Override
	public void setParentKBFolderId(long parentKBFolderId) {
		_kbFolder.setParentKBFolderId(parentKBFolderId);
	}

	/**
	* Returns the name of this k b folder.
	*
	* @return the name of this k b folder
	*/
	@Override
	public java.lang.String getName() {
		return _kbFolder.getName();
	}

	/**
	* Sets the name of this k b folder.
	*
	* @param name the name of this k b folder
	*/
	@Override
	public void setName(java.lang.String name) {
		_kbFolder.setName(name);
	}

	/**
	* Returns the url title of this k b folder.
	*
	* @return the url title of this k b folder
	*/
	@Override
	public java.lang.String getUrlTitle() {
		return _kbFolder.getUrlTitle();
	}

	/**
	* Sets the url title of this k b folder.
	*
	* @param urlTitle the url title of this k b folder
	*/
	@Override
	public void setUrlTitle(java.lang.String urlTitle) {
		_kbFolder.setUrlTitle(urlTitle);
	}

	/**
	* Returns the description of this k b folder.
	*
	* @return the description of this k b folder
	*/
	@Override
	public java.lang.String getDescription() {
		return _kbFolder.getDescription();
	}

	/**
	* Sets the description of this k b folder.
	*
	* @param description the description of this k b folder
	*/
	@Override
	public void setDescription(java.lang.String description) {
		_kbFolder.setDescription(description);
	}

	@Override
	public boolean isNew() {
		return _kbFolder.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kbFolder.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kbFolder.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kbFolder.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kbFolder.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kbFolder.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kbFolder.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kbFolder.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kbFolder.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kbFolder.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kbFolder.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KBFolderWrapper((KBFolder)_kbFolder.clone());
	}

	@Override
	public int compareTo(com.liferay.knowledgebase.model.KBFolder kbFolder) {
		return _kbFolder.compareTo(kbFolder);
	}

	@Override
	public int hashCode() {
		return _kbFolder.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.knowledgebase.model.KBFolder> toCacheModel() {
		return _kbFolder.toCacheModel();
	}

	@Override
	public com.liferay.knowledgebase.model.KBFolder toEscapedModel() {
		return new KBFolderWrapper(_kbFolder.toEscapedModel());
	}

	@Override
	public com.liferay.knowledgebase.model.KBFolder toUnescapedModel() {
		return new KBFolderWrapper(_kbFolder.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kbFolder.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kbFolder.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kbFolder.persist();
	}

	@Override
	public long getClassNameId() {
		return _kbFolder.getClassNameId();
	}

	@Override
	public java.lang.String getParentTitle(java.util.Locale locale)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbFolder.getParentTitle(locale);
	}

	@Override
	public boolean isEmpty()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kbFolder.isEmpty();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KBFolderWrapper)) {
			return false;
		}

		KBFolderWrapper kbFolderWrapper = (KBFolderWrapper)obj;

		if (Validator.equals(_kbFolder, kbFolderWrapper._kbFolder)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _kbFolder.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public KBFolder getWrappedKBFolder() {
		return _kbFolder;
	}

	@Override
	public KBFolder getWrappedModel() {
		return _kbFolder;
	}

	@Override
	public void resetOriginalValues() {
		_kbFolder.resetOriginalValues();
	}

	private KBFolder _kbFolder;
}