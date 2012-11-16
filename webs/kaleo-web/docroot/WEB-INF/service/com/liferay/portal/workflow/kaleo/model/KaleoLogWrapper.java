/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link KaleoLog}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       KaleoLog
 * @generated
 */
public class KaleoLogWrapper implements KaleoLog, ModelWrapper<KaleoLog> {
	public KaleoLogWrapper(KaleoLog kaleoLog) {
		_kaleoLog = kaleoLog;
	}

	public Class<?> getModelClass() {
		return KaleoLog.class;
	}

	public String getModelClassName() {
		return KaleoLog.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("kaleoLogId", getKaleoLogId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("userId", getUserId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("kaleoClassName", getKaleoClassName());
		attributes.put("kaleoClassPK", getKaleoClassPK());
		attributes.put("kaleoDefinitionId", getKaleoDefinitionId());
		attributes.put("kaleoInstanceId", getKaleoInstanceId());
		attributes.put("kaleoInstanceTokenId", getKaleoInstanceTokenId());
		attributes.put("kaleoTaskInstanceTokenId", getKaleoTaskInstanceTokenId());
		attributes.put("kaleoNodeName", getKaleoNodeName());
		attributes.put("terminalKaleoNode", getTerminalKaleoNode());
		attributes.put("kaleoActionId", getKaleoActionId());
		attributes.put("kaleoActionName", getKaleoActionName());
		attributes.put("kaleoActionDescription", getKaleoActionDescription());
		attributes.put("previousKaleoNodeId", getPreviousKaleoNodeId());
		attributes.put("previousKaleoNodeName", getPreviousKaleoNodeName());
		attributes.put("previousAssigneeClassName",
			getPreviousAssigneeClassName());
		attributes.put("previousAssigneeClassPK", getPreviousAssigneeClassPK());
		attributes.put("currentAssigneeClassName", getCurrentAssigneeClassName());
		attributes.put("currentAssigneeClassPK", getCurrentAssigneeClassPK());
		attributes.put("type", getType());
		attributes.put("comment", getComment());
		attributes.put("startDate", getStartDate());
		attributes.put("endDate", getEndDate());
		attributes.put("duration", getDuration());
		attributes.put("workflowContext", getWorkflowContext());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long kaleoLogId = (Long)attributes.get("kaleoLogId");

		if (kaleoLogId != null) {
			setKaleoLogId(kaleoLogId);
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

		String kaleoNodeName = (String)attributes.get("kaleoNodeName");

		if (kaleoNodeName != null) {
			setKaleoNodeName(kaleoNodeName);
		}

		Boolean terminalKaleoNode = (Boolean)attributes.get("terminalKaleoNode");

		if (terminalKaleoNode != null) {
			setTerminalKaleoNode(terminalKaleoNode);
		}

		Long kaleoActionId = (Long)attributes.get("kaleoActionId");

		if (kaleoActionId != null) {
			setKaleoActionId(kaleoActionId);
		}

		String kaleoActionName = (String)attributes.get("kaleoActionName");

		if (kaleoActionName != null) {
			setKaleoActionName(kaleoActionName);
		}

		String kaleoActionDescription = (String)attributes.get(
				"kaleoActionDescription");

		if (kaleoActionDescription != null) {
			setKaleoActionDescription(kaleoActionDescription);
		}

		Long previousKaleoNodeId = (Long)attributes.get("previousKaleoNodeId");

		if (previousKaleoNodeId != null) {
			setPreviousKaleoNodeId(previousKaleoNodeId);
		}

		String previousKaleoNodeName = (String)attributes.get(
				"previousKaleoNodeName");

		if (previousKaleoNodeName != null) {
			setPreviousKaleoNodeName(previousKaleoNodeName);
		}

		String previousAssigneeClassName = (String)attributes.get(
				"previousAssigneeClassName");

		if (previousAssigneeClassName != null) {
			setPreviousAssigneeClassName(previousAssigneeClassName);
		}

		Long previousAssigneeClassPK = (Long)attributes.get(
				"previousAssigneeClassPK");

		if (previousAssigneeClassPK != null) {
			setPreviousAssigneeClassPK(previousAssigneeClassPK);
		}

		String currentAssigneeClassName = (String)attributes.get(
				"currentAssigneeClassName");

		if (currentAssigneeClassName != null) {
			setCurrentAssigneeClassName(currentAssigneeClassName);
		}

		Long currentAssigneeClassPK = (Long)attributes.get(
				"currentAssigneeClassPK");

		if (currentAssigneeClassPK != null) {
			setCurrentAssigneeClassPK(currentAssigneeClassPK);
		}

		String type = (String)attributes.get("type");

		if (type != null) {
			setType(type);
		}

		String comment = (String)attributes.get("comment");

		if (comment != null) {
			setComment(comment);
		}

		Date startDate = (Date)attributes.get("startDate");

		if (startDate != null) {
			setStartDate(startDate);
		}

		Date endDate = (Date)attributes.get("endDate");

		if (endDate != null) {
			setEndDate(endDate);
		}

		Long duration = (Long)attributes.get("duration");

		if (duration != null) {
			setDuration(duration);
		}

		String workflowContext = (String)attributes.get("workflowContext");

		if (workflowContext != null) {
			setWorkflowContext(workflowContext);
		}
	}

	/**
	* Returns the primary key of this kaleo log.
	*
	* @return the primary key of this kaleo log
	*/
	public long getPrimaryKey() {
		return _kaleoLog.getPrimaryKey();
	}

	/**
	* Sets the primary key of this kaleo log.
	*
	* @param primaryKey the primary key of this kaleo log
	*/
	public void setPrimaryKey(long primaryKey) {
		_kaleoLog.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the kaleo log ID of this kaleo log.
	*
	* @return the kaleo log ID of this kaleo log
	*/
	public long getKaleoLogId() {
		return _kaleoLog.getKaleoLogId();
	}

	/**
	* Sets the kaleo log ID of this kaleo log.
	*
	* @param kaleoLogId the kaleo log ID of this kaleo log
	*/
	public void setKaleoLogId(long kaleoLogId) {
		_kaleoLog.setKaleoLogId(kaleoLogId);
	}

	/**
	* Returns the group ID of this kaleo log.
	*
	* @return the group ID of this kaleo log
	*/
	public long getGroupId() {
		return _kaleoLog.getGroupId();
	}

	/**
	* Sets the group ID of this kaleo log.
	*
	* @param groupId the group ID of this kaleo log
	*/
	public void setGroupId(long groupId) {
		_kaleoLog.setGroupId(groupId);
	}

	/**
	* Returns the company ID of this kaleo log.
	*
	* @return the company ID of this kaleo log
	*/
	public long getCompanyId() {
		return _kaleoLog.getCompanyId();
	}

	/**
	* Sets the company ID of this kaleo log.
	*
	* @param companyId the company ID of this kaleo log
	*/
	public void setCompanyId(long companyId) {
		_kaleoLog.setCompanyId(companyId);
	}

	/**
	* Returns the user ID of this kaleo log.
	*
	* @return the user ID of this kaleo log
	*/
	public long getUserId() {
		return _kaleoLog.getUserId();
	}

	/**
	* Sets the user ID of this kaleo log.
	*
	* @param userId the user ID of this kaleo log
	*/
	public void setUserId(long userId) {
		_kaleoLog.setUserId(userId);
	}

	/**
	* Returns the user uuid of this kaleo log.
	*
	* @return the user uuid of this kaleo log
	* @throws SystemException if a system exception occurred
	*/
	public java.lang.String getUserUuid()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _kaleoLog.getUserUuid();
	}

	/**
	* Sets the user uuid of this kaleo log.
	*
	* @param userUuid the user uuid of this kaleo log
	*/
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoLog.setUserUuid(userUuid);
	}

	/**
	* Returns the user name of this kaleo log.
	*
	* @return the user name of this kaleo log
	*/
	public java.lang.String getUserName() {
		return _kaleoLog.getUserName();
	}

	/**
	* Sets the user name of this kaleo log.
	*
	* @param userName the user name of this kaleo log
	*/
	public void setUserName(java.lang.String userName) {
		_kaleoLog.setUserName(userName);
	}

	/**
	* Returns the create date of this kaleo log.
	*
	* @return the create date of this kaleo log
	*/
	public java.util.Date getCreateDate() {
		return _kaleoLog.getCreateDate();
	}

	/**
	* Sets the create date of this kaleo log.
	*
	* @param createDate the create date of this kaleo log
	*/
	public void setCreateDate(java.util.Date createDate) {
		_kaleoLog.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this kaleo log.
	*
	* @return the modified date of this kaleo log
	*/
	public java.util.Date getModifiedDate() {
		return _kaleoLog.getModifiedDate();
	}

	/**
	* Sets the modified date of this kaleo log.
	*
	* @param modifiedDate the modified date of this kaleo log
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_kaleoLog.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the kaleo class name of this kaleo log.
	*
	* @return the kaleo class name of this kaleo log
	*/
	public java.lang.String getKaleoClassName() {
		return _kaleoLog.getKaleoClassName();
	}

	/**
	* Sets the kaleo class name of this kaleo log.
	*
	* @param kaleoClassName the kaleo class name of this kaleo log
	*/
	public void setKaleoClassName(java.lang.String kaleoClassName) {
		_kaleoLog.setKaleoClassName(kaleoClassName);
	}

	/**
	* Returns the kaleo class p k of this kaleo log.
	*
	* @return the kaleo class p k of this kaleo log
	*/
	public long getKaleoClassPK() {
		return _kaleoLog.getKaleoClassPK();
	}

	/**
	* Sets the kaleo class p k of this kaleo log.
	*
	* @param kaleoClassPK the kaleo class p k of this kaleo log
	*/
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoLog.setKaleoClassPK(kaleoClassPK);
	}

	/**
	* Returns the kaleo definition ID of this kaleo log.
	*
	* @return the kaleo definition ID of this kaleo log
	*/
	public long getKaleoDefinitionId() {
		return _kaleoLog.getKaleoDefinitionId();
	}

	/**
	* Sets the kaleo definition ID of this kaleo log.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo log
	*/
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoLog.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Returns the kaleo instance ID of this kaleo log.
	*
	* @return the kaleo instance ID of this kaleo log
	*/
	public long getKaleoInstanceId() {
		return _kaleoLog.getKaleoInstanceId();
	}

	/**
	* Sets the kaleo instance ID of this kaleo log.
	*
	* @param kaleoInstanceId the kaleo instance ID of this kaleo log
	*/
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoLog.setKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Returns the kaleo instance token ID of this kaleo log.
	*
	* @return the kaleo instance token ID of this kaleo log
	*/
	public long getKaleoInstanceTokenId() {
		return _kaleoLog.getKaleoInstanceTokenId();
	}

	/**
	* Sets the kaleo instance token ID of this kaleo log.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID of this kaleo log
	*/
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoLog.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	/**
	* Returns the kaleo task instance token ID of this kaleo log.
	*
	* @return the kaleo task instance token ID of this kaleo log
	*/
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoLog.getKaleoTaskInstanceTokenId();
	}

	/**
	* Sets the kaleo task instance token ID of this kaleo log.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID of this kaleo log
	*/
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoLog.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	/**
	* Returns the kaleo node name of this kaleo log.
	*
	* @return the kaleo node name of this kaleo log
	*/
	public java.lang.String getKaleoNodeName() {
		return _kaleoLog.getKaleoNodeName();
	}

	/**
	* Sets the kaleo node name of this kaleo log.
	*
	* @param kaleoNodeName the kaleo node name of this kaleo log
	*/
	public void setKaleoNodeName(java.lang.String kaleoNodeName) {
		_kaleoLog.setKaleoNodeName(kaleoNodeName);
	}

	/**
	* Returns the terminal kaleo node of this kaleo log.
	*
	* @return the terminal kaleo node of this kaleo log
	*/
	public boolean getTerminalKaleoNode() {
		return _kaleoLog.getTerminalKaleoNode();
	}

	/**
	* Returns <code>true</code> if this kaleo log is terminal kaleo node.
	*
	* @return <code>true</code> if this kaleo log is terminal kaleo node; <code>false</code> otherwise
	*/
	public boolean isTerminalKaleoNode() {
		return _kaleoLog.isTerminalKaleoNode();
	}

	/**
	* Sets whether this kaleo log is terminal kaleo node.
	*
	* @param terminalKaleoNode the terminal kaleo node of this kaleo log
	*/
	public void setTerminalKaleoNode(boolean terminalKaleoNode) {
		_kaleoLog.setTerminalKaleoNode(terminalKaleoNode);
	}

	/**
	* Returns the kaleo action ID of this kaleo log.
	*
	* @return the kaleo action ID of this kaleo log
	*/
	public long getKaleoActionId() {
		return _kaleoLog.getKaleoActionId();
	}

	/**
	* Sets the kaleo action ID of this kaleo log.
	*
	* @param kaleoActionId the kaleo action ID of this kaleo log
	*/
	public void setKaleoActionId(long kaleoActionId) {
		_kaleoLog.setKaleoActionId(kaleoActionId);
	}

	/**
	* Returns the kaleo action name of this kaleo log.
	*
	* @return the kaleo action name of this kaleo log
	*/
	public java.lang.String getKaleoActionName() {
		return _kaleoLog.getKaleoActionName();
	}

	/**
	* Sets the kaleo action name of this kaleo log.
	*
	* @param kaleoActionName the kaleo action name of this kaleo log
	*/
	public void setKaleoActionName(java.lang.String kaleoActionName) {
		_kaleoLog.setKaleoActionName(kaleoActionName);
	}

	/**
	* Returns the kaleo action description of this kaleo log.
	*
	* @return the kaleo action description of this kaleo log
	*/
	public java.lang.String getKaleoActionDescription() {
		return _kaleoLog.getKaleoActionDescription();
	}

	/**
	* Sets the kaleo action description of this kaleo log.
	*
	* @param kaleoActionDescription the kaleo action description of this kaleo log
	*/
	public void setKaleoActionDescription(
		java.lang.String kaleoActionDescription) {
		_kaleoLog.setKaleoActionDescription(kaleoActionDescription);
	}

	/**
	* Returns the previous kaleo node ID of this kaleo log.
	*
	* @return the previous kaleo node ID of this kaleo log
	*/
	public long getPreviousKaleoNodeId() {
		return _kaleoLog.getPreviousKaleoNodeId();
	}

	/**
	* Sets the previous kaleo node ID of this kaleo log.
	*
	* @param previousKaleoNodeId the previous kaleo node ID of this kaleo log
	*/
	public void setPreviousKaleoNodeId(long previousKaleoNodeId) {
		_kaleoLog.setPreviousKaleoNodeId(previousKaleoNodeId);
	}

	/**
	* Returns the previous kaleo node name of this kaleo log.
	*
	* @return the previous kaleo node name of this kaleo log
	*/
	public java.lang.String getPreviousKaleoNodeName() {
		return _kaleoLog.getPreviousKaleoNodeName();
	}

	/**
	* Sets the previous kaleo node name of this kaleo log.
	*
	* @param previousKaleoNodeName the previous kaleo node name of this kaleo log
	*/
	public void setPreviousKaleoNodeName(java.lang.String previousKaleoNodeName) {
		_kaleoLog.setPreviousKaleoNodeName(previousKaleoNodeName);
	}

	/**
	* Returns the previous assignee class name of this kaleo log.
	*
	* @return the previous assignee class name of this kaleo log
	*/
	public java.lang.String getPreviousAssigneeClassName() {
		return _kaleoLog.getPreviousAssigneeClassName();
	}

	/**
	* Sets the previous assignee class name of this kaleo log.
	*
	* @param previousAssigneeClassName the previous assignee class name of this kaleo log
	*/
	public void setPreviousAssigneeClassName(
		java.lang.String previousAssigneeClassName) {
		_kaleoLog.setPreviousAssigneeClassName(previousAssigneeClassName);
	}

	/**
	* Returns the previous assignee class p k of this kaleo log.
	*
	* @return the previous assignee class p k of this kaleo log
	*/
	public long getPreviousAssigneeClassPK() {
		return _kaleoLog.getPreviousAssigneeClassPK();
	}

	/**
	* Sets the previous assignee class p k of this kaleo log.
	*
	* @param previousAssigneeClassPK the previous assignee class p k of this kaleo log
	*/
	public void setPreviousAssigneeClassPK(long previousAssigneeClassPK) {
		_kaleoLog.setPreviousAssigneeClassPK(previousAssigneeClassPK);
	}

	/**
	* Returns the current assignee class name of this kaleo log.
	*
	* @return the current assignee class name of this kaleo log
	*/
	public java.lang.String getCurrentAssigneeClassName() {
		return _kaleoLog.getCurrentAssigneeClassName();
	}

	/**
	* Sets the current assignee class name of this kaleo log.
	*
	* @param currentAssigneeClassName the current assignee class name of this kaleo log
	*/
	public void setCurrentAssigneeClassName(
		java.lang.String currentAssigneeClassName) {
		_kaleoLog.setCurrentAssigneeClassName(currentAssigneeClassName);
	}

	/**
	* Returns the current assignee class p k of this kaleo log.
	*
	* @return the current assignee class p k of this kaleo log
	*/
	public long getCurrentAssigneeClassPK() {
		return _kaleoLog.getCurrentAssigneeClassPK();
	}

	/**
	* Sets the current assignee class p k of this kaleo log.
	*
	* @param currentAssigneeClassPK the current assignee class p k of this kaleo log
	*/
	public void setCurrentAssigneeClassPK(long currentAssigneeClassPK) {
		_kaleoLog.setCurrentAssigneeClassPK(currentAssigneeClassPK);
	}

	/**
	* Returns the type of this kaleo log.
	*
	* @return the type of this kaleo log
	*/
	public java.lang.String getType() {
		return _kaleoLog.getType();
	}

	/**
	* Sets the type of this kaleo log.
	*
	* @param type the type of this kaleo log
	*/
	public void setType(java.lang.String type) {
		_kaleoLog.setType(type);
	}

	/**
	* Returns the comment of this kaleo log.
	*
	* @return the comment of this kaleo log
	*/
	public java.lang.String getComment() {
		return _kaleoLog.getComment();
	}

	/**
	* Sets the comment of this kaleo log.
	*
	* @param comment the comment of this kaleo log
	*/
	public void setComment(java.lang.String comment) {
		_kaleoLog.setComment(comment);
	}

	/**
	* Returns the start date of this kaleo log.
	*
	* @return the start date of this kaleo log
	*/
	public java.util.Date getStartDate() {
		return _kaleoLog.getStartDate();
	}

	/**
	* Sets the start date of this kaleo log.
	*
	* @param startDate the start date of this kaleo log
	*/
	public void setStartDate(java.util.Date startDate) {
		_kaleoLog.setStartDate(startDate);
	}

	/**
	* Returns the end date of this kaleo log.
	*
	* @return the end date of this kaleo log
	*/
	public java.util.Date getEndDate() {
		return _kaleoLog.getEndDate();
	}

	/**
	* Sets the end date of this kaleo log.
	*
	* @param endDate the end date of this kaleo log
	*/
	public void setEndDate(java.util.Date endDate) {
		_kaleoLog.setEndDate(endDate);
	}

	/**
	* Returns the duration of this kaleo log.
	*
	* @return the duration of this kaleo log
	*/
	public long getDuration() {
		return _kaleoLog.getDuration();
	}

	/**
	* Sets the duration of this kaleo log.
	*
	* @param duration the duration of this kaleo log
	*/
	public void setDuration(long duration) {
		_kaleoLog.setDuration(duration);
	}

	/**
	* Returns the workflow context of this kaleo log.
	*
	* @return the workflow context of this kaleo log
	*/
	public java.lang.String getWorkflowContext() {
		return _kaleoLog.getWorkflowContext();
	}

	/**
	* Sets the workflow context of this kaleo log.
	*
	* @param workflowContext the workflow context of this kaleo log
	*/
	public void setWorkflowContext(java.lang.String workflowContext) {
		_kaleoLog.setWorkflowContext(workflowContext);
	}

	public boolean isNew() {
		return _kaleoLog.isNew();
	}

	public void setNew(boolean n) {
		_kaleoLog.setNew(n);
	}

	public boolean isCachedModel() {
		return _kaleoLog.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_kaleoLog.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _kaleoLog.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoLog.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoLog.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoLog.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoLog.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new KaleoLogWrapper((KaleoLog)_kaleoLog.clone());
	}

	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog) {
		return _kaleoLog.compareTo(kaleoLog);
	}

	@Override
	public int hashCode() {
		return _kaleoLog.hashCode();
	}

	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoLog> toCacheModel() {
		return _kaleoLog.toCacheModel();
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog toEscapedModel() {
		return new KaleoLogWrapper(_kaleoLog.toEscapedModel());
	}

	public com.liferay.portal.workflow.kaleo.model.KaleoLog toUnescapedModel() {
		return new KaleoLogWrapper(_kaleoLog.toUnescapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoLog.toString();
	}

	public java.lang.String toXmlString() {
		return _kaleoLog.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_kaleoLog.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public KaleoLog getWrappedKaleoLog() {
		return _kaleoLog;
	}

	public KaleoLog getWrappedModel() {
		return _kaleoLog;
	}

	public void resetOriginalValues() {
		_kaleoLog.resetOriginalValues();
	}

	private KaleoLog _kaleoLog;
}