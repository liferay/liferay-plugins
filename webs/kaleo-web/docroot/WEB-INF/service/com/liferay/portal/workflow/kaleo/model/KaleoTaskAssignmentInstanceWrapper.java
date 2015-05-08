/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoTaskAssignmentInstance}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoTaskAssignmentInstance
 * @generated
 */
@ProviderType
public class KaleoTaskAssignmentInstanceWrapper
	implements KaleoTaskAssignmentInstance,
		ModelWrapper<KaleoTaskAssignmentInstance> {
	public KaleoTaskAssignmentInstanceWrapper(
		KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		_kaleoTaskAssignmentInstance = kaleoTaskAssignmentInstance;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoTaskAssignmentInstance.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoTaskAssignmentInstance.class.getName();
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoTaskAssignmentInstanceId",
			getKaleoTaskAssignmentInstanceId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoInstanceId", getKaleoInstanceId());
		attributes.put("kaleoInstanceTokenId", getKaleoInstanceTokenId());
		attributes.put("kaleoTaskInstanceTokenId", getKaleoTaskInstanceTokenId());
		attributes.put("kaleoTaskId", getKaleoTaskId());
		attributes.put("kaleoTaskName", getKaleoTaskName());
		attributes.put("assigneeClassName", getAssigneeClassName());
		attributes.put("assigneeClassPK", getAssigneeClassPK());
		attributes.put("completed", getCompleted());
		attributes.put("completionDate", getCompletionDate());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoTaskAssignmentInstanceId = (Long)attributes.get(
				"kaleoTaskAssignmentInstanceId");

		if (kaleoTaskAssignmentInstanceId != null) {
			setKaleoTaskAssignmentInstanceId(kaleoTaskAssignmentInstanceId);
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

		Long kaleoDefinitionId = (Long)attributes.get("kaleoDefinitionId");

		if (kaleoDefinitionId != null) {
			setKaleoDefinitionId(kaleoDefinitionId);
		}

		Long kaleoInstanceId = (Long)attributes.get("kaleoInstanceId");

		if (kaleoInstanceId != null) {
			setKaleoInstanceId(kaleoInstanceId);
		}

		Long kaleoInstanceTokenId = (Long)attributes.get("kaleoInstanceTokenId");

		if (kaleoInstanceTokenId != null) {
			setKaleoInstanceTokenId(kaleoInstanceTokenId);
		}

		Long kaleoTaskInstanceTokenId = (Long)attributes.get(
				"kaleoTaskInstanceTokenId");

		if (kaleoTaskInstanceTokenId != null) {
			setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
		}

		Long kaleoTaskId = (Long)attributes.get("kaleoTaskId");

		if (kaleoTaskId != null) {
			setKaleoTaskId(kaleoTaskId);
		}

		String kaleoTaskName = (String)attributes.get("kaleoTaskName");

		if (kaleoTaskName != null) {
			setKaleoTaskName(kaleoTaskName);
		}

		String assigneeClassName = (String)attributes.get("assigneeClassName");

		if (assigneeClassName != null) {
			setAssigneeClassName(assigneeClassName);
		}

		Long assigneeClassPK = (Long)attributes.get("assigneeClassPK");

		if (assigneeClassPK != null) {
			setAssigneeClassPK(assigneeClassPK);
		}

		Boolean completed = (Boolean)attributes.get("completed");

		if (completed != null) {
			setCompleted(completed);
		}

		Date completionDate = (Date)attributes.get("completionDate");

		if (completionDate != null) {
			setCompletionDate(completionDate);
		}
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoTaskAssignmentInstanceWrapper((KaleoTaskAssignmentInstance)_kaleoTaskAssignmentInstance.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance kaleoTaskAssignmentInstance) {
		return _kaleoTaskAssignmentInstance.compareTo(kaleoTaskAssignmentInstance);
	}

	/**
	* Returns the assignee class name of this kaleo task assignment instance.
	*
	* @return the assignee class name of this kaleo task assignment instance
	*/
	@Override
	public java.lang.String getAssigneeClassName() {
		return _kaleoTaskAssignmentInstance.getAssigneeClassName();
	}

	/**
	* Returns the assignee class p k of this kaleo task assignment instance.
	*
	* @return the assignee class p k of this kaleo task assignment instance
	*/
	@Override
	public long getAssigneeClassPK() {
		return _kaleoTaskAssignmentInstance.getAssigneeClassPK();
	}

	/**
	* Returns the company ID of this kaleo task assignment instance.
	*
	* @return the company ID of this kaleo task assignment instance
	*/
	@Override
	public long getCompanyId() {
		return _kaleoTaskAssignmentInstance.getCompanyId();
	}

	/**
	* Returns the completed of this kaleo task assignment instance.
	*
	* @return the completed of this kaleo task assignment instance
	*/
	@Override
	public boolean getCompleted() {
		return _kaleoTaskAssignmentInstance.getCompleted();
	}

	/**
	* Returns the completion date of this kaleo task assignment instance.
	*
	* @return the completion date of this kaleo task assignment instance
	*/
	@Override
	public Date getCompletionDate() {
		return _kaleoTaskAssignmentInstance.getCompletionDate();
	}

	/**
	* Returns the create date of this kaleo task assignment instance.
	*
	* @return the create date of this kaleo task assignment instance
	*/
	@Override
	public Date getCreateDate() {
		return _kaleoTaskAssignmentInstance.getCreateDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoTaskAssignmentInstance.getExpandoBridge();
	}

	/**
	* Returns the group ID of this kaleo task assignment instance.
	*
	* @return the group ID of this kaleo task assignment instance
	*/
	@Override
	public long getGroupId() {
		return _kaleoTaskAssignmentInstance.getGroupId();
	}

	/**
	* Returns the kaleo definition ID of this kaleo task assignment instance.
	*
	* @return the kaleo definition ID of this kaleo task assignment instance
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoTaskAssignmentInstance.getKaleoDefinitionId();
	}

	/**
	* Returns the kaleo instance ID of this kaleo task assignment instance.
	*
	* @return the kaleo instance ID of this kaleo task assignment instance
	*/
	@Override
	public long getKaleoInstanceId() {
		return _kaleoTaskAssignmentInstance.getKaleoInstanceId();
	}

	/**
	* Returns the kaleo instance token ID of this kaleo task assignment instance.
	*
	* @return the kaleo instance token ID of this kaleo task assignment instance
	*/
	@Override
	public long getKaleoInstanceTokenId() {
		return _kaleoTaskAssignmentInstance.getKaleoInstanceTokenId();
	}

	/**
	* Returns the kaleo task assignment instance ID of this kaleo task assignment instance.
	*
	* @return the kaleo task assignment instance ID of this kaleo task assignment instance
	*/
	@Override
	public long getKaleoTaskAssignmentInstanceId() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskAssignmentInstanceId();
	}

	/**
	* Returns the kaleo task ID of this kaleo task assignment instance.
	*
	* @return the kaleo task ID of this kaleo task assignment instance
	*/
	@Override
	public long getKaleoTaskId() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskId();
	}

	/**
	* Returns the kaleo task instance token ID of this kaleo task assignment instance.
	*
	* @return the kaleo task instance token ID of this kaleo task assignment instance
	*/
	@Override
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskInstanceTokenId();
	}

	/**
	* Returns the kaleo task name of this kaleo task assignment instance.
	*
	* @return the kaleo task name of this kaleo task assignment instance
	*/
	@Override
	public java.lang.String getKaleoTaskName() {
		return _kaleoTaskAssignmentInstance.getKaleoTaskName();
	}

	/**
	* Returns the modified date of this kaleo task assignment instance.
	*
	* @return the modified date of this kaleo task assignment instance
	*/
	@Override
	public Date getModifiedDate() {
		return _kaleoTaskAssignmentInstance.getModifiedDate();
	}

	/**
	* Returns the primary key of this kaleo task assignment instance.
	*
	* @return the primary key of this kaleo task assignment instance
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoTaskAssignmentInstance.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoTaskAssignmentInstance.getPrimaryKeyObj();
	}

	/**
	* Returns the user ID of this kaleo task assignment instance.
	*
	* @return the user ID of this kaleo task assignment instance
	*/
	@Override
	public long getUserId() {
		return _kaleoTaskAssignmentInstance.getUserId();
	}

	/**
	* Returns the user name of this kaleo task assignment instance.
	*
	* @return the user name of this kaleo task assignment instance
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoTaskAssignmentInstance.getUserName();
	}

	/**
	* Returns the user uuid of this kaleo task assignment instance.
	*
	* @return the user uuid of this kaleo task assignment instance
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _kaleoTaskAssignmentInstance.getUserUuid();
	}

	@Override
	public int hashCode() {
		return _kaleoTaskAssignmentInstance.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoTaskAssignmentInstance.isCachedModel();
	}

	/**
	* Returns <code>true</code> if this kaleo task assignment instance is completed.
	*
	* @return <code>true</code> if this kaleo task assignment instance is completed; <code>false</code> otherwise
	*/
	@Override
	public boolean isCompleted() {
		return _kaleoTaskAssignmentInstance.isCompleted();
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoTaskAssignmentInstance.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _kaleoTaskAssignmentInstance.isNew();
	}

	@Override
	public void persist() {
		_kaleoTaskAssignmentInstance.persist();
	}

	/**
	* Sets the assignee class name of this kaleo task assignment instance.
	*
	* @param assigneeClassName the assignee class name of this kaleo task assignment instance
	*/
	@Override
	public void setAssigneeClassName(java.lang.String assigneeClassName) {
		_kaleoTaskAssignmentInstance.setAssigneeClassName(assigneeClassName);
	}

	/**
	* Sets the assignee class p k of this kaleo task assignment instance.
	*
	* @param assigneeClassPK the assignee class p k of this kaleo task assignment instance
	*/
	@Override
	public void setAssigneeClassPK(long assigneeClassPK) {
		_kaleoTaskAssignmentInstance.setAssigneeClassPK(assigneeClassPK);
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoTaskAssignmentInstance.setCachedModel(cachedModel);
	}

	/**
	* Sets the company ID of this kaleo task assignment instance.
	*
	* @param companyId the company ID of this kaleo task assignment instance
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoTaskAssignmentInstance.setCompanyId(companyId);
	}

	/**
	* Sets whether this kaleo task assignment instance is completed.
	*
	* @param completed the completed of this kaleo task assignment instance
	*/
	@Override
	public void setCompleted(boolean completed) {
		_kaleoTaskAssignmentInstance.setCompleted(completed);
	}

	/**
	* Sets the completion date of this kaleo task assignment instance.
	*
	* @param completionDate the completion date of this kaleo task assignment instance
	*/
	@Override
	public void setCompletionDate(Date completionDate) {
		_kaleoTaskAssignmentInstance.setCompletionDate(completionDate);
	}

	/**
	* Sets the create date of this kaleo task assignment instance.
	*
	* @param createDate the create date of this kaleo task assignment instance
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_kaleoTaskAssignmentInstance.setCreateDate(createDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoTaskAssignmentInstance.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoTaskAssignmentInstance.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoTaskAssignmentInstance.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this kaleo task assignment instance.
	*
	* @param groupId the group ID of this kaleo task assignment instance
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoTaskAssignmentInstance.setGroupId(groupId);
	}

	/**
	* Sets the kaleo definition ID of this kaleo task assignment instance.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo task assignment instance
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoTaskAssignmentInstance.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Sets the kaleo instance ID of this kaleo task assignment instance.
	*
	* @param kaleoInstanceId the kaleo instance ID of this kaleo task assignment instance
	*/
	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoTaskAssignmentInstance.setKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Sets the kaleo instance token ID of this kaleo task assignment instance.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID of this kaleo task assignment instance
	*/
	@Override
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoTaskAssignmentInstance.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	/**
	* Sets the kaleo task assignment instance ID of this kaleo task assignment instance.
	*
	* @param kaleoTaskAssignmentInstanceId the kaleo task assignment instance ID of this kaleo task assignment instance
	*/
	@Override
	public void setKaleoTaskAssignmentInstanceId(
		long kaleoTaskAssignmentInstanceId) {
		_kaleoTaskAssignmentInstance.setKaleoTaskAssignmentInstanceId(kaleoTaskAssignmentInstanceId);
	}

	/**
	* Sets the kaleo task ID of this kaleo task assignment instance.
	*
	* @param kaleoTaskId the kaleo task ID of this kaleo task assignment instance
	*/
	@Override
	public void setKaleoTaskId(long kaleoTaskId) {
		_kaleoTaskAssignmentInstance.setKaleoTaskId(kaleoTaskId);
	}

	/**
	* Sets the kaleo task instance token ID of this kaleo task assignment instance.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID of this kaleo task assignment instance
	*/
	@Override
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskAssignmentInstance.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	/**
	* Sets the kaleo task name of this kaleo task assignment instance.
	*
	* @param kaleoTaskName the kaleo task name of this kaleo task assignment instance
	*/
	@Override
	public void setKaleoTaskName(java.lang.String kaleoTaskName) {
		_kaleoTaskAssignmentInstance.setKaleoTaskName(kaleoTaskName);
	}

	/**
	* Sets the modified date of this kaleo task assignment instance.
	*
	* @param modifiedDate the modified date of this kaleo task assignment instance
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_kaleoTaskAssignmentInstance.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_kaleoTaskAssignmentInstance.setNew(n);
	}

	/**
	* Sets the primary key of this kaleo task assignment instance.
	*
	* @param primaryKey the primary key of this kaleo task assignment instance
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoTaskAssignmentInstance.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoTaskAssignmentInstance.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the user ID of this kaleo task assignment instance.
	*
	* @param userId the user ID of this kaleo task assignment instance
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoTaskAssignmentInstance.setUserId(userId);
	}

	/**
	* Sets the user name of this kaleo task assignment instance.
	*
	* @param userName the user name of this kaleo task assignment instance
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoTaskAssignmentInstance.setUserName(userName);
	}

	/**
	* Sets the user uuid of this kaleo task assignment instance.
	*
	* @param userUuid the user uuid of this kaleo task assignment instance
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoTaskAssignmentInstance.setUserUuid(userUuid);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance> toCacheModel() {
		return _kaleoTaskAssignmentInstance.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance toEscapedModel() {
		return new KaleoTaskAssignmentInstanceWrapper(_kaleoTaskAssignmentInstance.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoTaskAssignmentInstance.toString();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoTaskAssignmentInstance toUnescapedModel() {
		return new KaleoTaskAssignmentInstanceWrapper(_kaleoTaskAssignmentInstance.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoTaskAssignmentInstance.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoTaskAssignmentInstanceWrapper)) {
			return false;
		}

		KaleoTaskAssignmentInstanceWrapper kaleoTaskAssignmentInstanceWrapper = (KaleoTaskAssignmentInstanceWrapper)obj;

		if (Validator.equals(_kaleoTaskAssignmentInstance,
					kaleoTaskAssignmentInstanceWrapper._kaleoTaskAssignmentInstance)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoTaskAssignmentInstance getWrappedKaleoTaskAssignmentInstance() {
		return _kaleoTaskAssignmentInstance;
	}

	@Override
	public KaleoTaskAssignmentInstance getWrappedModel() {
		return _kaleoTaskAssignmentInstance;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoTaskAssignmentInstance.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoTaskAssignmentInstance.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoTaskAssignmentInstance.resetOriginalValues();
	}

	private final KaleoTaskAssignmentInstance _kaleoTaskAssignmentInstance;
}