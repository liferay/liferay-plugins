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
 * This class is a wrapper for {@link KaleoInstanceToken}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoInstanceToken
 * @generated
 */
public class KaleoInstanceTokenWrapper implements KaleoInstanceToken {
	public KaleoInstanceTokenWrapper(KaleoInstanceToken kaleoInstanceToken) {
		_kaleoInstanceToken = kaleoInstanceToken;
	}

	public long getPrimaryKey() {
		return _kaleoInstanceToken.getPrimaryKey();
	}

	public void setPrimaryKey(long pk) {
		_kaleoInstanceToken.setPrimaryKey(pk);
	}

	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceToken.getKaleoInstanceTokenId();
	}

	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoInstanceToken.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	public long getGroupId() {
		return _kaleoInstanceToken.getGroupId();
	}

	public void setGroupId(long groupId) {
		_kaleoInstanceToken.setGroupId(groupId);
	}

	public long getCompanyId() {
		return _kaleoInstanceToken.getCompanyId();
	}

	public void setCompanyId(long companyId) {
		_kaleoInstanceToken.setCompanyId(companyId);
	}

	public long getUserId() {
		return _kaleoInstanceToken.getUserId();
	}

	public void setUserId(long userId) {
		_kaleoInstanceToken.setUserId(userId);
	}

	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getUserUuid();
	}

	public void setUserUuid(java.lang.String userUuid) {
		_kaleoInstanceToken.setUserUuid(userUuid);
	}

	public java.lang.String getUserName() {
		return _kaleoInstanceToken.getUserName();
	}

	public void setUserName(java.lang.String userName) {
		_kaleoInstanceToken.setUserName(userName);
	}

	public java.util.Date getCreateDate() {
		return _kaleoInstanceToken.getCreateDate();
	}

	public void setCreateDate(java.util.Date createDate) {
		_kaleoInstanceToken.setCreateDate(createDate);
	}

	public java.util.Date getModifiedDate() {
		return _kaleoInstanceToken.getModifiedDate();
	}

	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoInstanceToken.setModifiedDate(modifiedDate);
	}

	public long getKaleoDefinitionId() {
		return _kaleoInstanceToken.getKaleoDefinitionId();
	}

	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoInstanceToken.setKaleoDefinitionId(kaleoDefinitionId);
	}

	public long getKaleoInstanceId() {
		return _kaleoInstanceToken.getKaleoInstanceId();
	}

	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceToken.setKaleoInstanceId(kaleoInstanceId);
	}

	public long getParentKaleoInstanceTokenId() {
		return _kaleoInstanceToken.getParentKaleoInstanceTokenId();
	}

	public void setParentKaleoInstanceTokenId(long parentKaleoInstanceTokenId) {
		_kaleoInstanceToken.setParentKaleoInstanceTokenId(parentKaleoInstanceTokenId);
	}

	public long getCurrentKaleoNodeId() {
		return _kaleoInstanceToken.getCurrentKaleoNodeId();
	}

	public void setCurrentKaleoNodeId(long currentKaleoNodeId) {
		_kaleoInstanceToken.setCurrentKaleoNodeId(currentKaleoNodeId);
	}

	public java.lang.String getCurrentKaleoNodeName() {
		return _kaleoInstanceToken.getCurrentKaleoNodeName();
	}

	public void setCurrentKaleoNodeName(java.lang.String currentKaleoNodeName) {
		_kaleoInstanceToken.setCurrentKaleoNodeName(currentKaleoNodeName);
	}

	public java.lang.String getClassName() {
		return _kaleoInstanceToken.getClassName();
	}

	public void setClassName(java.lang.String className) {
		_kaleoInstanceToken.setClassName(className);
	}

	public long getClassPK() {
		return _kaleoInstanceToken.getClassPK();
	}

	public void setClassPK(long classPK) {
		_kaleoInstanceToken.setClassPK(classPK);
	}

	public boolean getCompleted() {
		return _kaleoInstanceToken.getCompleted();
	}

	public boolean isCompleted() {
		return _kaleoInstanceToken.isCompleted();
	}

	public void setCompleted(boolean completed) {
		_kaleoInstanceToken.setCompleted(completed);
	}

	public java.util.Date getCompletionDate() {
		return _kaleoInstanceToken.getCompletionDate();
	}

	public void setCompletionDate(java.util.Date completionDate) {
		_kaleoInstanceToken.setCompletionDate(completionDate);
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken toEscapedModel() {
		return _kaleoInstanceToken.toEscapedModel();
	}

	public boolean isNew() {
		return _kaleoInstanceToken.isNew();
	}

	public boolean setNew(boolean n) {
		return _kaleoInstanceToken.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoInstanceToken.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoInstanceToken.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoInstanceToken.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoInstanceToken.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoInstanceToken.getPrimaryKeyObj();
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoInstanceToken.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoInstanceToken.setExpandoBridgeAttributes(serviceContext);
	}

	public java.lang.Object clone() {
		return _kaleoInstanceToken.clone();
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken kaleoInstanceToken) {
		return _kaleoInstanceToken.compareTo(kaleoInstanceToken);
	}

	public int hashCode() {
		return _kaleoInstanceToken.hashCode();
	}

	public java.lang.String toString() {
		return _kaleoInstanceToken.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoInstanceToken.toXmlString();
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getChildrenKaleoInstanceTokens()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getChildrenKaleoInstanceTokens();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoNode getCurrentKaleoNode()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getCurrentKaleoNode();
	}

	public java.util.List<com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken> getIncompleteChildrenKaleoInstanceTokens()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getIncompleteChildrenKaleoInstanceTokens();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstance getKaleoInstance()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getKaleoInstance();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoInstanceToken getParentKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.getParentKaleoInstanceToken();
	}

	public boolean hasIncompleteChildrenKaleoInstanceToken()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoInstanceToken.hasIncompleteChildrenKaleoInstanceToken();
	}

	public void setCurrentKaleoNode(
		com.liferay.portal.workflow.kaleo.model.KaleoNode kaleoNode)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		_kaleoInstanceToken.setCurrentKaleoNode(kaleoNode);
	}

	public KaleoInstanceToken getWrappedKaleoInstanceToken() {
		return _kaleoInstanceToken;
	}

	private KaleoInstanceToken _kaleoInstanceToken;
}