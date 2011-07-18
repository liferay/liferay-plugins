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

package com.liferay.portal.workflow.kaleo.model;

/**
 * <p>
 * This class is a wrapper for {@link KaleoTaskAssignment}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoTaskAssignment
 * @generated
 */
public class KaleoTaskAssignmentWrapper implements KaleoTaskAssignment {
	public KaleoTaskAssignmentWrapper(KaleoTaskAssignment kaleoTaskAssignment) {
		_kaleoTaskAssignment = kaleoTaskAssignment;
	}

	public Class<?> getModelClass() {
		return KaleoTaskAssignment.class;
	}

	public String getModelClassName() {
		return KaleoTaskAssignment.class.getName();
	}

	/**
	* Returns the primary key of this kaleo task assignment.
	*
	* @return the primary key of this kaleo task assignment
	*/
	public long getPrimaryKey() {
		return _kaleoTaskAssignment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo task assignment.
	*
	* @param primaryKey the primary key of this kaleo task assignment
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoTaskAssignment.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo task assignment ID of this kaleo task assignment.
	*
	* @return the kaleo task assignment ID of this kaleo task assignment
	*/
	public long getKaleoTaskAssignmentId() {
		return _kaleoTaskAssignment.getKaleoTaskAssignmentId();
	}

	/**
	* Sets the kaleo task assignment ID of this kaleo task assignment.
	*
	* @param kaleoTaskAssignmentId the kaleo task assignment ID of this kaleo task assignment
	*/
	public void setKaleoTaskAssignmentId(long kaleoTaskAssignmentId) {
		_kaleoTaskAssignment.setKaleoTaskAssignmentId(kaleoTaskAssignmentId);
	}

	/**
	* Returns the group ID of this kaleo task assignment.
	*
	* @return the group ID of this kaleo task assignment
	*/
	public long getGroupId() {
		return _kaleoTaskAssignment.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo task assignment.
	*
	* @param groupId the group ID of this kaleo task assignment
	*/
	public void setGroupId(long groupId) {
		_kaleoTaskAssignment.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo task assignment.
	*
	* @return the company ID of this kaleo task assignment
	*/
	public long getCompanyId() {
		return _kaleoTaskAssignment.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo task assignment.
	*
	* @param companyId the company ID of this kaleo task assignment
	*/
	public void setCompanyId(long companyId) {
		_kaleoTaskAssignment.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo task assignment.
	*
	* @return the user ID of this kaleo task assignment
	*/
	public long getUserId() {
		return _kaleoTaskAssignment.getUserId();
	}

	/**
	* Sets the user ID of this kaleo task assignment.
	*
	* @param userId the user ID of this kaleo task assignment
	*/
	public void setUserId(long userId) {
		_kaleoTaskAssignment.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo task assignment.
	*
	* @return the user uuid of this kaleo task assignment
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignment.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo task assignment.
	*
	* @param userUuid the user uuid of this kaleo task assignment
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTaskAssignment.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo task assignment.
	*
	* @return the user name of this kaleo task assignment
	*/
	public java.lang.String getUserName() {
		return _kaleoTaskAssignment.getUserName();
	}

	/**
	* Sets the user name of this kaleo task assignment.
	*
	* @param userName the user name of this kaleo task assignment
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoTaskAssignment.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo task assignment.
	*
	* @return the create date of this kaleo task assignment
	*/
	public java.util.Date getCreateDate() {
		return _kaleoTaskAssignment.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo task assignment.
	*
	* @param createDate the create date of this kaleo task assignment
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoTaskAssignment.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo task assignment.
	*
	* @return the modified date of this kaleo task assignment
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoTaskAssignment.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo task assignment.
	*
	* @param modifiedDate the modified date of this kaleo task assignment
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTaskAssignment.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo class name of this kaleo task assignment.
	*
	* @return the kaleo class name of this kaleo task assignment
	*/
	public java.lang.String getKaleoClassName() {
		return _kaleoTaskAssignment.getKaleoClassName();
	}

	/**
	* Sets the kaleo class name of this kaleo task assignment.
	*
	* @param kaleoClassName the kaleo class name of this kaleo task assignment
	*/
	public void setKaleoClassName(java.lang.String kaleoClassName) {
		_kaleoTaskAssignment.setKaleoClassName(kaleoClassName);
	}

	/**
	* Returns the kaleo class p k of this kaleo task assignment.
	*
	* @return the kaleo class p k of this kaleo task assignment
	*/
	public long getKaleoClassPK() {
		return _kaleoTaskAssignment.getKaleoClassPK();
	}

	/**
	* Sets the kaleo class p k of this kaleo task assignment.
	*
	* @param kaleoClassPK the kaleo class p k of this kaleo task assignment
	*/
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoTaskAssignment.setKaleoClassPK(kaleoClassPK);
	}

	/**
	* Returns the kaleo definition ID of this kaleo task assignment.
	*
	* @return the kaleo definition ID of this kaleo task assignment
	*/
	public long getKaleoDefinitionId() {
		return _kaleoTaskAssignment.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo task assignment.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo task assignment
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTaskAssignment.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo node ID of this kaleo task assignment.
	*
	* @return the kaleo node ID of this kaleo task assignment
	*/
	public long getKaleoNodeId() {
		return _kaleoTaskAssignment.getKaleoNodeId();
	}

	/**
	* Sets the kaleo node ID of this kaleo task assignment.
	*
	* @param kaleoNodeId the kaleo node ID of this kaleo task assignment
	*/
	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoTaskAssignment.setKaleoNodeId(kaleoNodeId);
	}

	/**
	* Returns the assignee class name of this kaleo task assignment.
	*
	* @return the assignee class name of this kaleo task assignment
	*/
	public java.lang.String getAssigneeClassName() {
		return _kaleoTaskAssignment.getAssigneeClassName();
	}

	/**
	* Sets the assignee class name of this kaleo task assignment.
	*
	* @param assigneeClassName the assignee class name of this kaleo task assignment
	*/
	public void setAssigneeClassName(java.lang.String assigneeClassName) {
		_kaleoTaskAssignment.setAssigneeClassName(assigneeClassName);
	}

	/**
	* Returns the assignee class p k of this kaleo task assignment.
	*
	* @return the assignee class p k of this kaleo task assignment
	*/
	public long getAssigneeClassPK() {
		return _kaleoTaskAssignment.getAssigneeClassPK();
	}

	/**
	* Sets the assignee class p k of this kaleo task assignment.
	*
	* @param assigneeClassPK the assignee class p k of this kaleo task assignment
	*/
	public void setAssigneeClassPK(long assigneeClassPK) {
		_kaleoTaskAssignment.setAssigneeClassPK(assigneeClassPK);
	}

	/**
	* Returns the assignee action ID of this kaleo task assignment.
	*
	* @return the assignee action ID of this kaleo task assignment
	*/
	public java.lang.String getAssigneeActionId() {
		return _kaleoTaskAssignment.getAssigneeActionId();
	}

	/**
	* Sets the assignee action ID of this kaleo task assignment.
	*
	* @param assigneeActionId the assignee action ID of this kaleo task assignment
	*/
	public void setAssigneeActionId(java.lang.String assigneeActionId) {
		_kaleoTaskAssignment.setAssigneeActionId(assigneeActionId);
	}

	/**
	* Returns the assignee script of this kaleo task assignment.
	*
	* @return the assignee script of this kaleo task assignment
	*/
	public java.lang.String getAssigneeScript() {
		return _kaleoTaskAssignment.getAssigneeScript();
	}

	/**
	* Sets the assignee script of this kaleo task assignment.
	*
	* @param assigneeScript the assignee script of this kaleo task assignment
	*/
	public void setAssigneeScript(java.lang.String assigneeScript) {
		_kaleoTaskAssignment.setAssigneeScript(assigneeScript);
	}

	/**
	* Returns the assignee script language of this kaleo task assignment.
	*
	* @return the assignee script language of this kaleo task assignment
	*/
	public java.lang.String getAssigneeScriptLanguage() {
		return _kaleoTaskAssignment.getAssigneeScriptLanguage();
	}

	/**
	* Sets the assignee script language of this kaleo task assignment.
	*
	* @param assigneeScriptLanguage the assignee script language of this kaleo task assignment
	*/
	public void setAssigneeScriptLanguage(
		java.lang.String assigneeScriptLanguage) {
		_kaleoTaskAssignment.setAssigneeScriptLanguage(assigneeScriptLanguage);
	}

	public boolean isNew() {
		return _kaleoTaskAssignment.isNew();
	}

	public void setNew(boolean n) {
		_kaleoTaskAssignment.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoTaskAssignment.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoTaskAssignment.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoTaskAssignment.isEscapedModel();
	}

	public void setEscapedModel(boolean escapedModel) {
		_kaleoTaskAssignment.setEscapedModel(escapedModel);
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTaskAssignment.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTaskAssignment.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTaskAssignment.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTaskAssignment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTaskAssignmentWrapper((KaleoTaskAssignment)_kaleoTaskAssignment.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment) {
		return _kaleoTaskAssignment.compareTo(kaleoTaskAssignment);
	}

	@Override
	public int hashCode() {
		return _kaleoTaskAssignment.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> toCacheModel() {
		return _kaleoTaskAssignment.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment toEscapedModel() {
		return new KaleoTaskAssignmentWrapper(_kaleoTaskAssignment.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTaskAssignment.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoTaskAssignment.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignment.persist();
	}

	public KaleoTaskAssignment getWrappedKaleoTaskAssignment() {
		return _kaleoTaskAssignment;
	}

	public void resetOriginalValues() {
		_kaleoTaskAssignment.resetOriginalValues();
	}

	private KaleoTaskAssignment _kaleoTaskAssignment;
}