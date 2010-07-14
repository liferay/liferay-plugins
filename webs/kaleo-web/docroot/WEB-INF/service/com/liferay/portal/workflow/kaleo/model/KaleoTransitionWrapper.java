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
 * This class is a wrapper for {@link KaleoTransition}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTransition
 * @generated
 */
public class KaleoTransitionWrapper implements KaleoTransition {
	public KaleoTransitionWrapper(KaleoTransition kaleoTransition) {
		_kaleoTransition = kaleoTransition;
	}

	public long getPrimaryKey() {
		return _kaleoTransition.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoTransition.setPrimaryKey(pk);
	}

	public long getKaleoTransitionId() {
		return _kaleoTransition.getKaleoTransitionId();
	}

	public void setKaleoTransitionId(long kaleoTransitionId) {
		_kaleoTransition.setKaleoTransitionId(kaleoTransitionId);
	}

	public long getGroupId() {
		return _kaleoTransition.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoTransition.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoTransition.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoTransition.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoTransition.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoTransition.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransition.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTransition.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoTransition.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoTransition.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoTransition.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoTransition.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoTransition.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTransition.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoTransition.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTransition.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public long getKaleoNodeId() {
		return _kaleoTransition.getKaleoNodeId();
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoTransition.setKaleoNodeId(kaleoNodeId);
	}

	public java.lang.String getName() {
		return _kaleoTransition.getName();
	}

	public void setName(java.lang.String name) {
		_kaleoTransition.setName(name);
	}

	public java.lang.String getDescription() {
		return _kaleoTransition.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_kaleoTransition.setDescription(description);
	}

	public long getSourceKaleoNodeId() {
		return _kaleoTransition.getSourceKaleoNodeId();
	}

	public void setSourceKaleoNodeId(long sourceKaleoNodeId) {
		_kaleoTransition.setSourceKaleoNodeId(sourceKaleoNodeId);
	}

	public java.lang.String getSourceKaleoNodeName() {
		return _kaleoTransition.getSourceKaleoNodeName();
	}

	public void setSourceKaleoNodeName(java.lang.String sourceKaleoNodeName) {
		_kaleoTransition.setSourceKaleoNodeName(sourceKaleoNodeName);
	}

	public long getTargetKaleoNodeId() {
		return _kaleoTransition.getTargetKaleoNodeId();
	}

	public void setTargetKaleoNodeId(long targetKaleoNodeId) {
		_kaleoTransition.setTargetKaleoNodeId(targetKaleoNodeId);
	}

	public java.lang.String getTargetKaleoNodeName() {
		return _kaleoTransition.getTargetKaleoNodeName();
	}

	public void setTargetKaleoNodeName(java.lang.String targetKaleoNodeName) {
		_kaleoTransition.setTargetKaleoNodeName(targetKaleoNodeName);
	}

	public boolean getDefaultTransition() {
		return _kaleoTransition.getDefaultTransition();
	}

	public boolean isDefaultTransition() {
		return _kaleoTransition.isDefaultTransition();
	}

	public void setDefaultTransition(boolean defaultTransition) {
		_kaleoTransition.setDefaultTransition(defaultTransition);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition toEscapedModel() {
		return _kaleoTransition.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoTransition.isNew();
	}

	public void setNew(boolean n) {
		_kaleoTransition.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTransition.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTransition.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTransition.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoTransition.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTransition.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTransition.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTransition.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoTransition.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTransition kaleoTransition) {
		return _kaleoTransition.compareTo(kaleoTransition);
	}

	public int hashCode() {
		return _kaleoTransition.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoTransition.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTransition.toXmlString();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getSourceKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransition.getSourceKaleoNode();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getTargetKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTransition.getTargetKaleoNode();
	}

	public KaleoTransition getWrappedKaleoTransition() {
		return _kaleoTransition;
	}

	private KaleoTransition _kaleoTransition;
}