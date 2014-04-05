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
 * This class is a wrapper for {@link KBTemplate}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KBTemplate
 * @generated
 */
public class KBTemplateWrapper implements KBTemplate, ModelWrapper<KBTemplate> {
	public KBTemplateWrapper(KBTemplate kbTemplate) {
		_kbTemplate = kbTemplate;
	}

	@Override
	public Class<?> getModelClass() {
		return KBTemplate.class;
	}

	@Override
	public String getModelClassName() {
		return KBTemplate.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("uuid", getUuid());
		attributes.put("kbTemplateId", getKbTemplateId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("title", getTitle());
		attributes.put("content", getContent());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long kbTemplateId = (Long)attributes.get("kbTemplateId");

		if (kbTemplateId != null) {
			setKbTemplateId(kbTemplateId);
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

		String title = (String)attributes.get("title");

		if (title != null) {
			setTitle(title);
		}

		String content = (String)attributes.get("content");

		if (content != null) {
			setContent(content);
		}
	}

	/**
	* Returns the primary key of this k b template.
	*
	* @return the primary key of this k b template
	*/
	@Override
	public long getPrimaryKey() {
		return _kbTemplate.getPrimaryKey();
	}

	/**
	* Sets the primary key of this k b template.
	*
	* @param primaryKey the primary key of this k b template
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kbTemplate.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the uuid of this k b template.
	*
	* @return the uuid of this k b template
	*/
	@Override
	public java.lang.String getUuid() {
		return _kbTemplate.getUuid();
	}

	/**
	* Sets the uuid of this k b template.
	*
	* @param uuid the uuid of this k b template
	*/
	@Override
	public void setUuid(java.lang.String uuid) {
		_kbTemplate.setUuid(uuid);
	}

	/**
	* Returns the kb template ID of this k b template.
	*
	* @return the kb template ID of this k b template
	*/
	@Override
	public long getKbTemplateId() {
		return _kbTemplate.getKbTemplateId();
	}

	/**
	* Sets the kb template ID of this k b template.
	*
	* @param kbTemplateId the kb template ID of this k b template
	*/
	@Override
	public void setKbTemplateId(long kbTemplateId) {
		_kbTemplate.setKbTemplateId(kbTemplateId);
	}

	/**
	* Returns the group ID of this k b template.
	*
	* @return the group ID of this k b template
	*/
	@Override
	public long getGroupId() {
		return _kbTemplate.getGroupId();
	}

	/**
	* Sets the group ID of this k b template.
	*
	* @param groupId the group ID of this k b template
	*/
	@Override
	public void setGroupId(long groupId) {
		_kbTemplate.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this k b template.
	*
	* @return the company ID of this k b template
	*/
	@Override
	public long getCompanyId() {
		return _kbTemplate.getCompanyId();
	}

	/**
	* Sets the company ID of this k b template.
	*
	* @param companyId the company ID of this k b template
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kbTemplate.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this k b template.
	*
	* @return the user ID of this k b template
	*/
	@Override
	public long getUserId() {
		return _kbTemplate.getUserId();
	}

	/**
	* Sets the user ID of this k b template.
	*
	* @param userId the user ID of this k b template
	*/
	@Override
	public void setUserId(long userId) {
		_kbTemplate.setUserId(userId);
	}

	/**
	* Returns the user uuid of this k b template.
	*
	* @return the user uuid of this k b template
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplate.getUserUuid();
	}

	/**
	* Sets the user uuid of this k b template.
	*
	* @param userUuid the user uuid of this k b template
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kbTemplate.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this k b template.
	*
	* @return the user name of this k b template
	*/
	@Override
	public java.lang.String getUserName() {
		return _kbTemplate.getUserName();
	}

	/**
	* Sets the user name of this k b template.
	*
	* @param userName the user name of this k b template
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kbTemplate.setUserName(userName);
	}

	/**
	* Returns the create date of this k b template.
	*
	* @return the create date of this k b template
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kbTemplate.getCreateDate();
	}

	/**
	* Sets the create date of this k b template.
	*
	* @param createDate the create date of this k b template
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kbTemplate.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this k b template.
	*
	* @return the modified date of this k b template
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kbTemplate.getModifiedDate();
	}

	/**
	* Sets the modified date of this k b template.
	*
	* @param modifiedDate the modified date of this k b template
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kbTemplate.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the title of this k b template.
	*
	* @return the title of this k b template
	*/
	@Override
	public java.lang.String getTitle() {
		return _kbTemplate.getTitle();
	}

	/**
	* Sets the title of this k b template.
	*
	* @param title the title of this k b template
	*/
	@Override
	public void setTitle(java.lang.String title) {
		_kbTemplate.setTitle(title);
	}

	/**
	* Returns the content of this k b template.
	*
	* @return the content of this k b template
	*/
	@Override
	public java.lang.String getContent() {
		return _kbTemplate.getContent();
	}

	/**
	* Sets the content of this k b template.
	*
	* @param content the content of this k b template
	*/
	@Override
	public void setContent(java.lang.String content) {
		_kbTemplate.setContent(content);
	}

	@Override
	public boolean isNew() {
		return _kbTemplate.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kbTemplate.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kbTemplate.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kbTemplate.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kbTemplate.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kbTemplate.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kbTemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kbTemplate.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kbTemplate.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kbTemplate.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kbTemplate.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KBTemplateWrapper((KBTemplate)_kbTemplate.clone());
	}

	@Override
	public int compareTo(com.liferay.knowledgebase.model.KBTemplate kbTemplate) {
		return _kbTemplate.compareTo(kbTemplate);
	}

	@Override
	public int hashCode() {
		return _kbTemplate.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.knowledgebase.model.KBTemplate> toCacheModel() {
		return _kbTemplate.toCacheModel();
	}

	@Override
	public com.liferay.knowledgebase.model.KBTemplate toEscapedModel() {
		return new KBTemplateWrapper(_kbTemplate.toEscapedModel());
	}

	@Override
	public com.liferay.knowledgebase.model.KBTemplate toUnescapedModel() {
		return new KBTemplateWrapper(_kbTemplate.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kbTemplate.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kbTemplate.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kbTemplate.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KBTemplateWrapper)) {
			return false;
		}

		KBTemplateWrapper kbTemplateWrapper = (KBTemplateWrapper)obj;

		if (Validator.equals(_kbTemplate, kbTemplateWrapper._kbTemplate)) {
			return true;
		}

		return false;
	}

	@Override
	public StagedModelType getStagedModelType() {
		return _kbTemplate.getStagedModelType();
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	public KBTemplate getWrappedKBTemplate() {
		return _kbTemplate;
	}

	@Override
	public KBTemplate getWrappedModel() {
		return _kbTemplate;
	}

	@Override
	public void resetOriginalValues() {
		_kbTemplate.resetOriginalValues();
	}

	private KBTemplate _kbTemplate;
}