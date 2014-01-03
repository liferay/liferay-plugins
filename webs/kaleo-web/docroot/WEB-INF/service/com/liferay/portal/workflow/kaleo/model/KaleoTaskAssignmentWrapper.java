/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoTaskAssignment}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignment
 * @generated
 */
public class KaleoTaskAssignmentWrapper implements KaleoTaskAssignment,
	ModelWrapper<KaleoTaskAssignment> {
	public KaleoTaskAssignmentWrapper(KaleoTaskAssignment kaleoTaskAssignment) {
		_kaleoTaskAssignment = kaleoTaskAssignment;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTaskAssignment.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTaskAssignment.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTaskAssignmentId", getKaleoTaskAssignmentId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoClassName", getKaleoClassName());
		attributes.put("kaleoClassPK", getKaleoClassPK());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoNodeId", getKaleoNodeId());
		attributes.put("assigneeClassName", getAssigneeClassName());
		attributes.put("assigneeClassPK", getAssigneeClassPK());
		attributes.put("assigneeActionId", getAssigneeActionId());
		attributes.put("assigneeScript", getAssigneeScript());
		attributes.put("assigneeScriptLanguage", getAssigneeScriptLanguage());
		attributes.put("assigneeScriptRequiredContexts",
			getAssigneeScriptRequiredContexts());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTaskAssignmentId = (Long)attributes.get(
				"kaleoTaskAssignmentId");

		if (kaleoTaskAssignmentId != null) {
			setKaleoTaskAssignmentId(kaleoTaskAssignmentId);
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

		String kaleoClassName = (String)attributes.get("kaleoClassName");

		if (kaleoClassName != null) {
			setKaleoClassName(kaleoClassName);
		}

		Long kaleoClassPK = (Long)attributes.get("kaleoClassPK");

		if (kaleoClassPK != null) {
			setKaleoClassPK(kaleoClassPK);
		}

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		Long kaleoNodeId = (Long)attributes.get("kaleoNodeId");

		if (kaleoNodeId != null) {
			setKaleoNodeId(kaleoNodeId);
		}

		String assigneeClassName = (String)attributes.get("assigneeClassName");

		if (assigneeClassName != null) {
			setAssigneeClassName(assigneeClassName);
		}

		Long assigneeClassPK = (Long)attributes.get("assigneeClassPK");

		if (assigneeClassPK != null) {
			setAssigneeClassPK(assigneeClassPK);
		}

		String assigneeActionId = (String)attributes.get("assigneeActionId");

		if (assigneeActionId != null) {
			setAssigneeActionId(assigneeActionId);
		}

		String assigneeScript = (String)attributes.get("assigneeScript");

		if (assigneeScript != null) {
			setAssigneeScript(assigneeScript);
		}

		String assigneeScriptLanguage = (String)attributes.get(
				"assigneeScriptLanguage");

		if (assigneeScriptLanguage != null) {
			setAssigneeScriptLanguage(assigneeScriptLanguage);
		}

		String assigneeScriptRequiredContexts = (String)attributes.get(
				"assigneeScriptRequiredContexts");

		if (assigneeScriptRequiredContexts != null) {
			setAssigneeScriptRequiredContexts(assigneeScriptRequiredContexts);
		}
	}

	/**
	* Returns the primary key of this kaleo task assignment.
	*
	* @return the primary key of this kaleo task assignment
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoTaskAssignment.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo task assignment.
	*
	* @param primaryKey the primary key of this kaleo task assignment
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoTaskAssignment.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo task assignment ID of this kaleo task assignment.
	*
	* @return the kaleo task assignment ID of this kaleo task assignment
	*/
	@Override
	public long getKaleoTaskAssignmentId() {
		return _kaleoTaskAssignment.getKaleoTaskAssignmentId();
	}

	/**
	* Sets the kaleo task assignment ID of this kaleo task assignment.
	*
	* @param kaleoTaskAssignmentId the kaleo task assignment ID of this kaleo task assignment
	*/
	@Override
	public void setKaleoTaskAssignmentId(long kaleoTaskAssignmentId) {
		_kaleoTaskAssignment.setKaleoTaskAssignmentId(kaleoTaskAssignmentId);
	}

	/**
	* Returns the group ID of this kaleo task assignment.
	*
	* @return the group ID of this kaleo task assignment
	*/
	@Override
	public long getGroupId() {
		return _kaleoTaskAssignment.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo task assignment.
	*
	* @param groupId the group ID of this kaleo task assignment
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoTaskAssignment.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo task assignment.
	*
	* @return the company ID of this kaleo task assignment
	*/
	@Override
	public long getCompanyId() {
		return _kaleoTaskAssignment.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo task assignment.
	*
	* @param companyId the company ID of this kaleo task assignment
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoTaskAssignment.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo task assignment.
	*
	* @return the user ID of this kaleo task assignment
	*/
	@Override
	public long getUserId() {
		return _kaleoTaskAssignment.getUserId();
	}

	/**
	* Sets the user ID of this kaleo task assignment.
	*
	* @param userId the user ID of this kaleo task assignment
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoTaskAssignment.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo task assignment.
	*
	* @return the user uuid of this kaleo task assignment
	* @throws SystemException if a system exception occurred
	*/
	@Override
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoTaskAssignment.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo task assignment.
	*
	* @param userUuid the user uuid of this kaleo task assignment
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTaskAssignment.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo task assignment.
	*
	* @return the user name of this kaleo task assignment
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoTaskAssignment.getUserName();
	}

	/**
	* Sets the user name of this kaleo task assignment.
	*
	* @param userName the user name of this kaleo task assignment
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoTaskAssignment.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo task assignment.
	*
	* @return the create date of this kaleo task assignment
	*/
	@Override
	public java.util.Date getCreateDate() {
		return _kaleoTaskAssignment.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo task assignment.
	*
	* @param createDate the create date of this kaleo task assignment
	*/
	@Override
	public void setCreateDate(java.util.Date createDate) {
		_kaleoTaskAssignment.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo task assignment.
	*
	* @return the modified date of this kaleo task assignment
	*/
	@Override
	public java.util.Date getModifiedDate() {
		return _kaleoTaskAssignment.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo task assignment.
	*
	* @param modifiedDate the modified date of this kaleo task assignment
	*/
	@Override
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoTaskAssignment.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo class name of this kaleo task assignment.
	*
	* @return the kaleo class name of this kaleo task assignment
	*/
	@Override
	public java.lang.String getKaleoClassName() {
		return _kaleoTaskAssignment.getKaleoClassName();
	}

	/**
	* Sets the kaleo class name of this kaleo task assignment.
	*
	* @param kaleoClassName the kaleo class name of this kaleo task assignment
	*/
	@Override
	public void setKaleoClassName(java.lang.String kaleoClassName) {
		_kaleoTaskAssignment.setKaleoClassName(kaleoClassName);
	}

	/**
	* Returns the kaleo class p k of this kaleo task assignment.
	*
	* @return the kaleo class p k of this kaleo task assignment
	*/
	@Override
	public long getKaleoClassPK() {
		return _kaleoTaskAssignment.getKaleoClassPK();
	}

	/**
	* Sets the kaleo class p k of this kaleo task assignment.
	*
	* @param kaleoClassPK the kaleo class p k of this kaleo task assignment
	*/
	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoTaskAssignment.setKaleoClassPK(kaleoClassPK);
	}

	/**
	* Returns the kaleo definition ID of this kaleo task assignment.
	*
	* @return the kaleo definition ID of this kaleo task assignment
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoTaskAssignment.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo task assignment.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo task assignment
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTaskAssignment.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo node ID of this kaleo task assignment.
	*
	* @return the kaleo node ID of this kaleo task assignment
	*/
	@Override
	public long getKaleoNodeId() {
		return _kaleoTaskAssignment.getKaleoNodeId();
	}

	/**
	* Sets the kaleo node ID of this kaleo task assignment.
	*
	* @param kaleoNodeId the kaleo node ID of this kaleo task assignment
	*/
	@Override
	public void setKaleoNodeId(long kaleoNodeId) {
		_kaleoTaskAssignment.setKaleoNodeId(kaleoNodeId);
	}

	/**
	* Returns the assignee class name of this kaleo task assignment.
	*
	* @return the assignee class name of this kaleo task assignment
	*/
	@Override
	public java.lang.String getAssigneeClassName() {
		return _kaleoTaskAssignment.getAssigneeClassName();
	}

	/**
	* Sets the assignee class name of this kaleo task assignment.
	*
	* @param assigneeClassName the assignee class name of this kaleo task assignment
	*/
	@Override
	public void setAssigneeClassName(java.lang.String assigneeClassName) {
		_kaleoTaskAssignment.setAssigneeClassName(assigneeClassName);
	}

	/**
	* Returns the assignee class p k of this kaleo task assignment.
	*
	* @return the assignee class p k of this kaleo task assignment
	*/
	@Override
	public long getAssigneeClassPK() {
		return _kaleoTaskAssignment.getAssigneeClassPK();
	}

	/**
	* Sets the assignee class p k of this kaleo task assignment.
	*
	* @param assigneeClassPK the assignee class p k of this kaleo task assignment
	*/
	@Override
	public void setAssigneeClassPK(long assigneeClassPK) {
		_kaleoTaskAssignment.setAssigneeClassPK(assigneeClassPK);
	}

	/**
	* Returns the assignee action ID of this kaleo task assignment.
	*
	* @return the assignee action ID of this kaleo task assignment
	*/
	@Override
	public java.lang.String getAssigneeActionId() {
		return _kaleoTaskAssignment.getAssigneeActionId();
	}

	/**
	* Sets the assignee action ID of this kaleo task assignment.
	*
	* @param assigneeActionId the assignee action ID of this kaleo task assignment
	*/
	@Override
	public void setAssigneeActionId(java.lang.String assigneeActionId) {
		_kaleoTaskAssignment.setAssigneeActionId(assigneeActionId);
	}

	/**
	* Returns the assignee script of this kaleo task assignment.
	*
	* @return the assignee script of this kaleo task assignment
	*/
	@Override
	public java.lang.String getAssigneeScript() {
		return _kaleoTaskAssignment.getAssigneeScript();
	}

	/**
	* Sets the assignee script of this kaleo task assignment.
	*
	* @param assigneeScript the assignee script of this kaleo task assignment
	*/
	@Override
	public void setAssigneeScript(java.lang.String assigneeScript) {
		_kaleoTaskAssignment.setAssigneeScript(assigneeScript);
	}

	/**
	* Returns the assignee script language of this kaleo task assignment.
	*
	* @return the assignee script language of this kaleo task assignment
	*/
	@Override
	public java.lang.String getAssigneeScriptLanguage() {
		return _kaleoTaskAssignment.getAssigneeScriptLanguage();
	}

	/**
	* Sets the assignee script language of this kaleo task assignment.
	*
	* @param assigneeScriptLanguage the assignee script language of this kaleo task assignment
	*/
	@Override
	public void setAssigneeScriptLanguage(
		java.lang.String assigneeScriptLanguage) {
		_kaleoTaskAssignment.setAssigneeScriptLanguage(assigneeScriptLanguage);
	}

	/**
	* Returns the assignee script required contexts of this kaleo task assignment.
	*
	* @return the assignee script required contexts of this kaleo task assignment
	*/
	@Override
	public java.lang.String getAssigneeScriptRequiredContexts() {
		return _kaleoTaskAssignment.getAssigneeScriptRequiredContexts();
	}

	/**
	* Sets the assignee script required contexts of this kaleo task assignment.
	*
	* @param assigneeScriptRequiredContexts the assignee script required contexts of this kaleo task assignment
	*/
	@Override
	public void setAssigneeScriptRequiredContexts(
		java.lang.String assigneeScriptRequiredContexts) {
		_kaleoTaskAssignment.setAssigneeScriptRequiredContexts(assigneeScriptRequiredContexts);
	}

	@Override
	public boolean isNew() {
		return _kaleoTaskAssignment.isNew();
	}

	@Override
	public void setNew(boolean n) {
		_kaleoTaskAssignment.setNew(n);
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoTaskAssignment.isCachedModel();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoTaskAssignment.setCachedModel(cachedModel);
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoTaskAssignment.isEscapedModel();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTaskAssignment.getPrimaryKeyObj();
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTaskAssignment.setPrimaryKeyObj(primaryKeyObj);
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTaskAssignment.getExpandoBridge();
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoTaskAssignment.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoTaskAssignment.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTaskAssignment.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTaskAssignmentWrapper((KaleoTaskAssignment)_kaleoTaskAssignment.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment kaleoTaskAssignment) {
		return _kaleoTaskAssignment.compareTo(kaleoTaskAssignment);
	}

	@Override
	public int hashCode() {
		return _kaleoTaskAssignment.hashCode();
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment> toCacheModel() {
		return _kaleoTaskAssignment.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment toEscapedModel() {
		return new KaleoTaskAssignmentWrapper(_kaleoTaskAssignment.toEscapedModel());
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignment toUnescapedModel() {
		return new KaleoTaskAssignmentWrapper(_kaleoTaskAssignment.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTaskAssignment.toString();
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoTaskAssignment.toXmlString();
	}

	@Override
	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoTaskAssignment.persist();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTaskAssignmentWrapper)) {
			return false;
		}

		KaleoTaskAssignmentWrapper kaleoTaskAssignmentWrapper = (KaleoTaskAssignmentWrapper)obj;

		if (Validator.equals(_kaleoTaskAssignment,
					kaleoTaskAssignmentWrapper._kaleoTaskAssignment)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoTaskAssignment getWrappedKaleoTaskAssignment() {
		return _kaleoTaskAssignment;
	}

	@Override
	public KaleoTaskAssignment getWrappedModel() {
		return _kaleoTaskAssignment;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoTaskAssignment.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoTaskAssignment.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoTaskAssignment.resetOriginalValues();
	}

	private KaleoTaskAssignment _kaleoTaskAssignment;
}