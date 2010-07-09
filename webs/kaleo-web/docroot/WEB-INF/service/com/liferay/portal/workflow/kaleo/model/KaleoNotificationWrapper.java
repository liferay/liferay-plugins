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

package com.liferay.portal.workflow.kaleo.model;


/**
 * <p>
 * ServiceBuilder generated this class. Modifications in this class will be
 * overwritten the next time is generated.
 * </p>
 *
 * <p>
 * This class is a wrapper for {@link KaleoNotification}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNotification
 * @generated
 */
public class KaleoNotificationWrapper implements KaleoNotification {
	public KaleoNotificationWrapper(KaleoNotification kaleoNotification) {
		_kaleoNotification = kaleoNotification;
	}

	public long getPrimaryKey() {
		return _kaleoNotification.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoNotification.setPrimaryKey(pk);
	}

	public long getKaleoNotificationId() {
		return _kaleoNotification.getKaleoNotificationId();
	}

	public void setKaleoNotificationId(long kaleoNotificationId) {
		_kaleoNotification.setKaleoNotificationId(kaleoNotificationId);
	}

	public long getGroupId() {
		return _kaleoNotification.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoNotification.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoNotification.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoNotification.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoNotification.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoNotification.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNotification.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoNotification.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoNotification.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoNotification.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoNotification.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoNotification.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoNotification.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoNotification.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoNotification.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoNotification.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public long getKaleoNodeId() {
		return _kaleoNotification.getKaleoNodeId();
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNotification.setKaleoNodeId(kaleoNodeId);
	}

	public java.lang.String getKaleoNodeName() {
		return _kaleoNotification.getKaleoNodeName();
	}

	public void setKaleoNodeName(java.lang.String kaleoNodeName) {
		_kaleoNotification.setKaleoNodeName(kaleoNodeName);
	}

	public java.lang.String getName() {
		return _kaleoNotification.getName();
	}

	public void setName(java.lang.String name) {
		_kaleoNotification.setName(name);
	}

	public java.lang.String getDescription() {
		return _kaleoNotification.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_kaleoNotification.setDescription(description);
	}

	public java.lang.String getExecutionType() {
		return _kaleoNotification.getExecutionType();
	}

	public void setExecutionType(java.lang.String executionType) {
		_kaleoNotification.setExecutionType(executionType);
	}

	public java.lang.String getTemplate() {
		return _kaleoNotification.getTemplate();
	}

	public void setTemplate(java.lang.String template) {
		_kaleoNotification.setTemplate(template);
	}

	public java.lang.String getTemplateLanguage() {
		return _kaleoNotification.getTemplateLanguage();
	}

	public void setTemplateLanguage(java.lang.String templateLanguage) {
		_kaleoNotification.setTemplateLanguage(templateLanguage);
	}

	public java.lang.String getNotificationTypes() {
		return _kaleoNotification.getNotificationTypes();
	}

	public void setNotificationTypes(java.lang.String notificationTypes) {
		_kaleoNotification.setNotificationTypes(notificationTypes);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNotification toEscapedModel() {
		return _kaleoNotification.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoNotification.isNew();
	}

	public boolean setNew(boolean n) {
		return _kaleoNotification.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoNotification.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoNotification.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoNotification.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoNotification.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoNotification.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoNotification.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoNotification.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoNotification.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoNotification kaleoNotification) {
		return _kaleoNotification.compareTo(kaleoNotification);
	}

	public int hashCode() {
		return _kaleoNotification.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoNotification.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoNotification.toXmlString();
	}

	public KaleoNotification getWrappedKaleoNotification() {
		return _kaleoNotification;
	}

	private KaleoNotification _kaleoNotification;
}