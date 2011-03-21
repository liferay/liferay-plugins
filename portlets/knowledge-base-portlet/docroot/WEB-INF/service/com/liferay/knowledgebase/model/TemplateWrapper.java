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
 * This class is a wrapper for {@link Template}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       Template
 * @generated
 */
public class TemplateWrapper implements Template {
	public TemplateWrapper(Template template) {
		_template = template;
	}

	public Class<?> getModelClass() {
		return Template.class;
	}

	public String getModelClassName() {
		return Template.class.getName();
	}

	/**
	* Gets the primary key of this template.
	*
	* @return the primary key of this template
	*/
	public long getPrimaryKey() {
		return _template.getPrimaryKey();
	}

	/**
	* Sets the primary key of this template
	*
	* @param pk the primary key of this template
	*/
	public void setPrimaryKey(long pk) {
		_template.setPrimaryKey(pk);
	}

	/**
	* Gets the uuid of this template.
	*
	* @return the uuid of this template
	*/
	public java.lang.String getUuid() {
		return _template.getUuid();
	}

	/**
	* Sets the uuid of this template.
	*
	* @param uuid the uuid of this template
	*/
	public void setUuid(java.lang.String uuid) {
		_template.setUuid(uuid);
	}

	/**
	* Gets the template ID of this template.
	*
	* @return the template ID of this template
	*/
	public long getTemplateId() {
		return _template.getTemplateId();
	}

	/**
	* Sets the template ID of this template.
	*
	* @param templateId the template ID of this template
	*/
	public void setTemplateId(long templateId) {
		_template.setTemplateId(templateId);
	}

	/**
	* Gets the group ID of this template.
	*
	* @return the group ID of this template
	*/
	public long getGroupId() {
		return _template.getGroupId();
	}

	/**
	* Sets the group ID of this template.
	*
	* @param groupId the group ID of this template
	*/
	public void setGroupId(long groupId) {
		_template.setGroupId(groupId);
	}

	/**
	* Gets the company ID of this template.
	*
	* @return the company ID of this template
	*/
	public long getCompanyId() {
		return _template.getCompanyId();
	}

	/**
	* Sets the company ID of this template.
	*
	* @param companyId the company ID of this template
	*/
	public void setCompanyId(long companyId) {
		_template.setCompanyId(companyId);
	}

	/**
	* Gets the user ID of this template.
	*
	* @return the user ID of this template
	*/
	public long getUserId() {
		return _template.getUserId();
	}

	/**
	* Sets the user ID of this template.
	*
	* @param userId the user ID of this template
	*/
	public void setUserId(long userId) {
		_template.setUserId(userId);
	}

	/**
	* Gets the user uuid of this template.
	*
	* @return the user uuid of this template
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _template.getUserUuid();
	}

	/**
	* Sets the user uuid of this template.
	*
	* @param userUuid the user uuid of this template
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_template.setUserUuid(userUuid);
	}

	/**
	* Gets the user name of this template.
	*
	* @return the user name of this template
	*/
	public java.lang.String getUserName() {
		return _template.getUserName();
	}

	/**
	* Sets the user name of this template.
	*
	* @param userName the user name of this template
	*/
	public void setUserName(java.lang.String userName) {
		_template.setUserName(userName);
	}

	/**
	* Gets the create date of this template.
	*
	* @return the create date of this template
	*/
	public java.util.Date getCreateDate() {
		return _template.getCreateDate();
	}

	/**
	* Sets the create date of this template.
	*
	* @param createDate the create date of this template
	*/
	public void setCreateDate(java.util.Date createDate) {
		_template.setCreateDate(createDate);
	}

	/**
	* Gets the modified date of this template.
	*
	* @return the modified date of this template
	*/
	public java.util.Date getModifiedDate() {
		return _template.getModifiedDate();
	}

	/**
	* Sets the modified date of this template.
	*
	* @param modifiedDate the modified date of this template
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_template.setModifiedDate(modifiedDate);
	}

	/**
	* Gets the title of this template.
	*
	* @return the title of this template
	*/
	public java.lang.String getTitle() {
		return _template.getTitle();
	}

	/**
	* Sets the title of this template.
	*
	* @param title the title of this template
	*/
	public void setTitle(java.lang.String title) {
		_template.setTitle(title);
	}

	/**
	* Gets the content of this template.
	*
	* @return the content of this template
	*/
	public java.lang.String getContent() {
		return _template.getContent();
	}

	/**
	* Sets the content of this template.
	*
	* @param content the content of this template
	*/
	public void setContent(java.lang.String content) {
		_template.setContent(content);
	}

	/**
	* Gets the description of this template.
	*
	* @return the description of this template
	*/
	public java.lang.String getDescription() {
		return _template.getDescription();
	}

	/**
	* Sets the description of this template.
	*
	* @param description the description of this template
	*/
	public void setDescription(java.lang.String description) {
		_template.setDescription(description);
	}

	public boolean isNew() {
		return _template.isNew();
	}

	public void setNew(boolean n) {
		_template.setNew(n);
	}

	public boolean isCachedModel() {
		return _template.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_template.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _template.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_template.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _template.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _template.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_template.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return new TemplateWrapper((Template)_template.clone());
	}

	public int compareTo(com.liferay.knowledgebase.model.Template template) {
		return _template.compareTo(template);
	}

	public int hashCode() {
		return _template.hashCode();
	}

	public com.liferay.knowledgebase.model.Template toEscapedModel() {
		return new TemplateWrapper(_template.toEscapedModel());
	}

	public java.lang.String toString() {
		return _template.toString();
	}

	public java.lang.String toXmlString() {
		return _template.toXmlString();
	}

	public Template getWrappedTemplate() {
		return _template;
	}

	private Template _template;
}