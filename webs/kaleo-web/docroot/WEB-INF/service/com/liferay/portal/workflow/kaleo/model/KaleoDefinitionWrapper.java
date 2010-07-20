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
 * This class is a wrapper for {@link KaleoDefinition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoDefinition
 * @generated
 */
public class KaleoDefinitionWrapper implements KaleoDefinition {
	public KaleoDefinitionWrapper(KaleoDefinition kaleoDefinition) {
		_kaleoDefinition = kaleoDefinition;
	}

	public long getPrimaryKey() {
		return _kaleoDefinition.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoDefinition.setPrimaryKey(pk);
	}

	public long getKaleoDefinitionId() {
		return _kaleoDefinition.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinition.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public long getGroupId() {
		return _kaleoDefinition.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoDefinition.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoDefinition.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoDefinition.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoDefinition.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoDefinition.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinition.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoDefinition.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoDefinition.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoDefinition.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoDefinition.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoDefinition.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoDefinition.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoDefinition.setModifiedDate(modifiedDate);
	}

	public java.lang.String getName() {
		return _kaleoDefinition.getName();
	}

	public void setName(java.lang.String name) {
		_kaleoDefinition.setName(name);
	}

	public java.lang.String getTitle() {
		return _kaleoDefinition.getTitle();
	}

	public java.lang.String getTitle(java.util.Locale locale) {
		return _kaleoDefinition.getTitle(locale);
	}

	public java.lang.String getTitle(java.util.Locale locale, boolean useDefault) {
		return _kaleoDefinition.getTitle(locale, useDefault);
	}

	public java.lang.String getTitle(java.lang.String languageId) {
		return _kaleoDefinition.getTitle(languageId);
	}

	public java.lang.String getTitle(java.lang.String languageId,
		boolean useDefault) {
		return _kaleoDefinition.getTitle(languageId, useDefault);
	}

	public java.util.Map<java.util.Locale, java.lang.String> getTitleMap() {
		return _kaleoDefinition.getTitleMap();
	}

	public void setTitle(java.lang.String title) {
		_kaleoDefinition.setTitle(title);
	}

	public void setTitle(java.util.Locale locale, java.lang.String title) {
		_kaleoDefinition.setTitle(locale, title);
	}

	public void setTitleMap(
		java.util.Map<java.util.Locale, java.lang.String> titleMap) {
		_kaleoDefinition.setTitleMap(titleMap);
	}

	public java.lang.String getDescription() {
		return _kaleoDefinition.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_kaleoDefinition.setDescription(description);
	}

	public int getVersion() {
		return _kaleoDefinition.getVersion();
	}

	public void setVersion(int version) {
		_kaleoDefinition.setVersion(version);
	}

	public boolean getActive() {
		return _kaleoDefinition.getActive();
	}

	public boolean isActive() {
		return _kaleoDefinition.isActive();
	}

	public void setActive(boolean active) {
		_kaleoDefinition.setActive(active);
	}

	public long getStartKaleoNodeId() {
		return _kaleoDefinition.getStartKaleoNodeId();
	}

	public void setStartKaleoNodeId(long startKaleoNodeId) {
		_kaleoDefinition.setStartKaleoNodeId(startKaleoNodeId);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoDefinition toEscapedModel() {
		return _kaleoDefinition.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoDefinition.isNew();
	}

	public void setNew(boolean n) {
		_kaleoDefinition.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoDefinition.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoDefinition.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoDefinition.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoDefinition.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoDefinition.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoDefinition.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoDefinition.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoDefinition.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoDefinition kaleoDefinition) {
		return _kaleoDefinition.compareTo(kaleoDefinition);
	}

	public int hashCode() {
		return _kaleoDefinition.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoDefinition.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoDefinition.toXmlString();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getKaleoStartNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinition.getKaleoStartNode();
	}

	public boolean hasIncompleteKaleoInstances()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoDefinition.hasIncompleteKaleoInstances();
	}

	public KaleoDefinition getWrappedKaleoDefinition() {
		return _kaleoDefinition;
	}

	private KaleoDefinition _kaleoDefinition;
}