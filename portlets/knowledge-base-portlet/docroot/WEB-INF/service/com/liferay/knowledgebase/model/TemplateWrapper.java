/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

	public long getPrimaryKey() {
		return _template.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_template.setPrimaryKey(pk);
	}

	public java.lang.String getUuid() {
		return _template.getUuid();
	}

	public void setUuid(java.lang.String uuid) {
		_template.setUuid(uuid);
	}

	public long getTemplateId() {
		return _template.getTemplateId();
	}

	public void setTemplateId(long templateId) {
		_template.setTemplateId(templateId);
	}

	public long getGroupId() {
		return _template.getGroupId();
	}

	public void setGroupId(long groupId) {
		_template.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _template.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_template.setCompanyId(companyId);
	}

	public long getUserId() {
		return _template.getUserId();
	}

	public void setUserId(long userId) {
		_template.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _template.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_template.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _template.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_template.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _template.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_template.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _template.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_template.setModifiedDate(modifiedDate);
	}

	public java.lang.String getTitle() {
		return _template.getTitle();
	}

	public void setTitle(java.lang.String title) {
		_template.setTitle(title);
	}

	public java.lang.String getContent() {
		return _template.getContent();
	}

	public void setContent(java.lang.String content) {
		_template.setContent(content);
	}

	public java.lang.String getDescription() {
		return _template.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_template.setDescription(description);
	}

	public com.liferay.knowledgebase.model.Template toEscapedModel() {
		return _template.toEscapedModel();
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
		return _template.clone();
	}

	public int compareTo(com.liferay.knowledgebase.model.Template template) {
		return _template.compareTo(template);
	}

	public int hashCode() {
		return _template.hashCode();
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