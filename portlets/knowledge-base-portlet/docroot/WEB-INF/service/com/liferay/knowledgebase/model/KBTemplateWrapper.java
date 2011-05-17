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

/**
 * <p>
 * This class is a wrapper for {@link KBTemplate}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KBTemplate
 * @generated
 */
public class KBTemplateWrapper implements KBTemplate {
	public KBTemplateWrapper(KBTemplate kbTemplate) {
		_kbTemplate = kbTemplate;
	}

	public Class<?> getModelClass() {
		return KBTemplate.class;
	}

	public String getModelClassName() {
		return KBTemplate.class.getName();
	}

	/**
	* Gets the primary key of this k b template.
	*
	* @return the primary key of this k b template
	*/
	public long getPrimaryKey() {
		return _kbTemplate.getPrimaryKey();
	}

	/**
	* Sets the primary key of this k b template
	*
	* @param primaryKey the primary key of this k b template
	*/
	public void setPrimaryKey(long primaryKey) {
		_kbTemplate.setPrimaryKey(primaryKey);
	}

	/**
	* Gets the uuid of this k b template.
	*
	* @return the uuid of this k b template
	*/
	public java.lang.String getUuid() {
		return _kbTemplate.getUuid();
	}

	/**
	* Sets the uuid of this k b template.
	*
	* @param uuid the uuid of this k b template
	*/
	public void setUuid(java.lang.String uuid) {
		_kbTemplate.setUuid(uuid);
	}

	/**
	* Gets the kb template ID of this k b template.
	*
	* @return the kb template ID of this k b template
	*/
	public long getKbTemplateId() {
		return _kbTemplate.getKbTemplateId();
	}

	/**
	* Sets the kb template ID of this k b template.
	*
	* @param kbTemplateId the kb template ID of this k b template
	*/
	public void setKbTemplateId(long kbTemplateId) {
		_kbTemplate.setKbTemplateId(kbTemplateId);
	}

	/**
	* Gets the group ID of this k b template.
	*
	* @return the group ID of this k b template
	*/
	public long getGroupId() {
		return _kbTemplate.getGroupId();
	}

	/**
	* Sets the group ID of this k b template.
	*
	* @param groupId the group ID of this k b template
	*/
	public void setGroupId(long groupId) {
		_kbTemplate.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this k b template.
	*
	* @return the company ID of this k b template
	*/
	public long getCompanyId() {
		return _kbTemplate.getCompanyId();
	}

	/**
	* Sets the company ID of this k b template.
	*
	* @param companyId the company ID of this k b template
	*/
	public void setCompanyId(long companyId) {
		_kbTemplate.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this k b template.
	*
	* @return the user ID of this k b template
	*/
	public long getUserId() {
		return _kbTemplate.getUserId();
	}

	/**
	* Sets the user ID of this k b template.
	*
	* @param userId the user ID of this k b template
	*/
	public void setUserId(long userId) {
		_kbTemplate.setUserId(userId);
	}

	/**
	* Gets the user uuid of this k b template.
	*
	* @return the user uuid of this k b template
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kbTemplate.getUserUuid();
	}

	/**
	* Sets the user uuid of this k b template.
	*
	* @param userUuid the user uuid of this k b template
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kbTemplate.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this k b template.
	*
	* @return the user name of this k b template
	*/
	public java.lang.String getUserName() {
		return _kbTemplate.getUserName();
	}

	/**
	* Sets the user name of this k b template.
	*
	* @param userName the user name of this k b template
	*/
	public void setUserName(java.lang.String userName) {
		_kbTemplate.setUserName(userName);
	}

	/**
	* Gets the create date of this k b template.
	*
	* @return the create date of this k b template
	*/
	public java.util.Date getCreateDate() {
		return _kbTemplate.getCreateDate();
	}

	/**
	* Sets the create date of this k b template.
	*
	* @param createDate the create date of this k b template
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kbTemplate.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this k b template.
	*
	* @return the modified date of this k b template
	*/
	public java.util.Date getModifiedDate() {
		return _kbTemplate.getModifiedDate();
	}

	/**
	* Sets the modified date of this k b template.
	*
	* @param modifiedDate the modified date of this k b template
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kbTemplate.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the title of this k b template.
	*
	* @return the title of this k b template
	*/
	public java.lang.String getTitle() {
		return _kbTemplate.getTitle();
	}

	/**
	* Sets the title of this k b template.
	*
	* @param title the title of this k b template
	*/
	public void setTitle(java.lang.String title) {
		_kbTemplate.setTitle(title);
	}

	/**
	* Gets the content of this k b template.
	*
	* @return the content of this k b template
	*/
	public java.lang.String getContent() {
		return _kbTemplate.getContent();
	}

	/**
	* Sets the content of this k b template.
	*
	* @param content the content of this k b template
	*/
	public void setContent(java.lang.String content) {
		_kbTemplate.setContent(content);
	}

	/**
	* Gets the engine type of this k b template.
	*
	* @return the engine type of this k b template
	*/
	public int getEngineType() {
		return _kbTemplate.getEngineType();
	}

	/**
	* Sets the engine type of this k b template.
	*
	* @param engineType the engine type of this k b template
	*/
	public void setEngineType(int engineType) {
		_kbTemplate.setEngineType(engineType);
	}

	/**
	* Gets the cacheable of this k b template.
	*
	* @return the cacheable of this k b template
	*/
	public boolean getCacheable() {
		return _kbTemplate.getCacheable();
	}

	/**
	* Determines if this k b template is cacheable.
	*
	* @return <code>true</code> if this k b template is cacheable; <code>false</code> otherwise
	*/
	public boolean isCacheable() {
		return _kbTemplate.isCacheable();
	}

	/**
	* Sets whether this k b template is cacheable.
	*
	* @param cacheable the cacheable of this k b template
	*/
	public void setCacheable(boolean cacheable) {
		_kbTemplate.setCacheable(cacheable);
	}

	public boolean isNew() {
		return _kbTemplate.isNew();
	}

	public void setNew(boolean n) {
		_kbTemplate.setNew(n);
	}

	public boolean isCachedModel() {
		return _kbTemplate.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kbTemplate.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kbTemplate.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kbTemplate.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kbTemplate.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kbTemplate.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kbTemplate.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kbTemplate.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new KBTemplateWrapper((KBTemplate)_kbTemplate.clone());
	}

	public int compareTo(com.liferay.knowledgebase.model.KBTemplate kbTemplate) {
		return _kbTemplate.compareTo(kbTemplate);
	}

	public int hashCode() {
		return _kbTemplate.hashCode();
	}

	public com.liferay.knowledgebase.model.KBTemplate toEscapedModel() {
		return new KBTemplateWrapper(_kbTemplate.toEscapedModel());
	}

	public java.lang.String toString() {
		return _kbTemplate.toString();
	}

	public java.lang.String toXmlString() {
		return _kbTemplate.toXmlString();
	}

	public boolean isFreeMarker() {
		return _kbTemplate.isFreeMarker();
	}

	public boolean isVelocity() {
		return _kbTemplate.isVelocity();
	}

	public KBTemplate getWrappedKBTemplate() {
		return _kbTemplate;
	}

	public void resetOriginalValues() {
		_kbTemplate.resetOriginalValues();
	}

	private KBTemplate _kbTemplate;
}