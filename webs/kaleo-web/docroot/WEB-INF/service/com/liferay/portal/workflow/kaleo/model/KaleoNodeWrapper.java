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
 * This class is a wrapper for {@link KaleoNode}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoNode
 * @generated
 */
public class KaleoNodeWrapper implements KaleoNode {
	public KaleoNodeWrapper(KaleoNode kaleoNode) {
		_kaleoNode = kaleoNode;
	}

	public long getPrimaryKey() {
		return _kaleoNode.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoNode.setPrimaryKey(pk);
	}

	public long getKaleoNodeId() {
		return _kaleoNode.getKaleoNodeId();
	}

	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoNode.setKaleoNodeId(kaleoNodeId);
	}

	public long getGroupId() {
		return _kaleoNode.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoNode.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoNode.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoNode.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoNode.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoNode.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNode.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoNode.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoNode.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoNode.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoNode.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoNode.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoNode.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoNode.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoNode.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoNode.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public java.lang.String getName() {
		return _kaleoNode.getName();
	}

	public void setName(java.lang.String name) {
		_kaleoNode.setName(name);
	}

	public java.lang.String getDescription() {
		return _kaleoNode.getDescription();
	}

	public void setDescription(java.lang.String description) {
		_kaleoNode.setDescription(description);
	}

	public java.lang.String getType() {
		return _kaleoNode.getType();
	}

	public void setType(java.lang.String type) {
		_kaleoNode.setType(type);
	}

	public boolean getInitial() {
		return _kaleoNode.getInitial();
	}

	public boolean isInitial() {
		return _kaleoNode.isInitial();
	}

	public void setInitial(boolean initial) {
		_kaleoNode.setInitial(initial);
	}

	public boolean getTerminal() {
		return _kaleoNode.getTerminal();
	}

	public boolean isTerminal() {
		return _kaleoNode.isTerminal();
	}

	public void setTerminal(boolean terminal) {
		_kaleoNode.setTerminal(terminal);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode toEscapedModel() {
		return _kaleoNode.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoNode.isNew();
	}

	public void setNew(boolean n) {
		_kaleoNode.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoNode.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoNode.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoNode.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoNode.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoNode.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoNode.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoNode.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoNode.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode) {
		return _kaleoNode.compareTo(kaleoNode);
	}

	public int hashCode() {
		return _kaleoNode.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoNode.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoNode.toXmlString();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getDefaultKaleoTransition()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNode.getDefaultKaleoTransition();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTransition getKaleoTransition(
		java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNode.getKaleoTransition(name);
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoTransition> getKaleoTransitions()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNode.getKaleoTransitions();
	}

	public boolean hasKaleoTransition()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoNode.hasKaleoTransition();
	}

	public KaleoNode getWrappedKaleoNode() {
		return _kaleoNode;
	}

	private KaleoNode _kaleoNode;
}