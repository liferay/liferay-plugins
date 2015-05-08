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
 * This class is a wrapper for {@link KaleoLog}.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see KaleoLog
 * @generated
 */
@ProviderType
public class KaleoLogWrapper implements KaleoLog, ModelWrapper<KaleoLog> {
	public KaleoLogWrapper(KaleoLog kaleoLog) {
		_kaleoLog = kaleoLog;
	}

	@Override
	public Class<?> getModelClass() {
		return KaleoLog.class;
	}

	@Override
	public String getModelClassName() {
		return KaleoLog.class.getName();
	}

	@Override
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

	@Override
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

	@Override
	public java.lang.Object clone() {
		return new KaleoLogWrapper((KaleoLog)_kaleoLog.clone());
	}

	@Override
	public int compareTo(
		com.liferay.portal.workflow.kaleo.model.KaleoLog kaleoLog) {
		return _kaleoLog.compareTo(kaleoLog);
	}

	/**
	* Returns the comment of this kaleo log.
	*
	* @return the comment of this kaleo log
	*/
	@Override
	public java.lang.String getComment() {
		return _kaleoLog.getComment();
	}

	/**
	* Returns the company ID of this kaleo log.
	*
	* @return the company ID of this kaleo log
	*/
	@Override
	public long getCompanyId() {
		return _kaleoLog.getCompanyId();
	}

	/**
	* Returns the create date of this kaleo log.
	*
	* @return the create date of this kaleo log
	*/
	@Override
	public Date getCreateDate() {
		return _kaleoLog.getCreateDate();
	}

	/**
	* Returns the current assignee class name of this kaleo log.
	*
	* @return the current assignee class name of this kaleo log
	*/
	@Override
	public java.lang.String getCurrentAssigneeClassName() {
		return _kaleoLog.getCurrentAssigneeClassName();
	}

	/**
	* Returns the current assignee class p k of this kaleo log.
	*
	* @return the current assignee class p k of this kaleo log
	*/
	@Override
	public long getCurrentAssigneeClassPK() {
		return _kaleoLog.getCurrentAssigneeClassPK();
	}

	/**
	* Returns the duration of this kaleo log.
	*
	* @return the duration of this kaleo log
	*/
	@Override
	public long getDuration() {
		return _kaleoLog.getDuration();
	}

	/**
	* Returns the end date of this kaleo log.
	*
	* @return the end date of this kaleo log
	*/
	@Override
	public Date getEndDate() {
		return _kaleoLog.getEndDate();
	}

	@Override
	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _kaleoLog.getExpandoBridge();
	}

	/**
	* Returns the group ID of this kaleo log.
	*
	* @return the group ID of this kaleo log
	*/
	@Override
	public long getGroupId() {
		return _kaleoLog.getGroupId();
	}

	/**
	* Returns the kaleo action description of this kaleo log.
	*
	* @return the kaleo action description of this kaleo log
	*/
	@Override
	public java.lang.String getKaleoActionDescription() {
		return _kaleoLog.getKaleoActionDescription();
	}

	/**
	* Returns the kaleo action ID of this kaleo log.
	*
	* @return the kaleo action ID of this kaleo log
	*/
	@Override
	public long getKaleoActionId() {
		return _kaleoLog.getKaleoActionId();
	}

	/**
	* Returns the kaleo action name of this kaleo log.
	*
	* @return the kaleo action name of this kaleo log
	*/
	@Override
	public java.lang.String getKaleoActionName() {
		return _kaleoLog.getKaleoActionName();
	}

	/**
	* Returns the kaleo class name of this kaleo log.
	*
	* @return the kaleo class name of this kaleo log
	*/
	@Override
	public java.lang.String getKaleoClassName() {
		return _kaleoLog.getKaleoClassName();
	}

	/**
	* Returns the kaleo class p k of this kaleo log.
	*
	* @return the kaleo class p k of this kaleo log
	*/
	@Override
	public long getKaleoClassPK() {
		return _kaleoLog.getKaleoClassPK();
	}

	/**
	* Returns the kaleo definition ID of this kaleo log.
	*
	* @return the kaleo definition ID of this kaleo log
	*/
	@Override
	public long getKaleoDefinitionId() {
		return _kaleoLog.getKaleoDefinitionId();
	}

	/**
	* Returns the kaleo instance ID of this kaleo log.
	*
	* @return the kaleo instance ID of this kaleo log
	*/
	@Override
	public long getKaleoInstanceId() {
		return _kaleoLog.getKaleoInstanceId();
	}

	/**
	* Returns the kaleo instance token ID of this kaleo log.
	*
	* @return the kaleo instance token ID of this kaleo log
	*/
	@Override
	public long getKaleoInstanceTokenId() {
		return _kaleoLog.getKaleoInstanceTokenId();
	}

	/**
	* Returns the kaleo log ID of this kaleo log.
	*
	* @return the kaleo log ID of this kaleo log
	*/
	@Override
	public long getKaleoLogId() {
		return _kaleoLog.getKaleoLogId();
	}

	/**
	* Returns the kaleo node name of this kaleo log.
	*
	* @return the kaleo node name of this kaleo log
	*/
	@Override
	public java.lang.String getKaleoNodeName() {
		return _kaleoLog.getKaleoNodeName();
	}

	/**
	* Returns the kaleo task instance token ID of this kaleo log.
	*
	* @return the kaleo task instance token ID of this kaleo log
	*/
	@Override
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoLog.getKaleoTaskInstanceTokenId();
	}

	/**
	* Returns the modified date of this kaleo log.
	*
	* @return the modified date of this kaleo log
	*/
	@Override
	public Date getModifiedDate() {
		return _kaleoLog.getModifiedDate();
	}

	/**
	* Returns the previous assignee class name of this kaleo log.
	*
	* @return the previous assignee class name of this kaleo log
	*/
	@Override
	public java.lang.String getPreviousAssigneeClassName() {
		return _kaleoLog.getPreviousAssigneeClassName();
	}

	/**
	* Returns the previous assignee class p k of this kaleo log.
	*
	* @return the previous assignee class p k of this kaleo log
	*/
	@Override
	public long getPreviousAssigneeClassPK() {
		return _kaleoLog.getPreviousAssigneeClassPK();
	}

	/**
	* Returns the previous kaleo node ID of this kaleo log.
	*
	* @return the previous kaleo node ID of this kaleo log
	*/
	@Override
	public long getPreviousKaleoNodeId() {
		return _kaleoLog.getPreviousKaleoNodeId();
	}

	/**
	* Returns the previous kaleo node name of this kaleo log.
	*
	* @return the previous kaleo node name of this kaleo log
	*/
	@Override
	public java.lang.String getPreviousKaleoNodeName() {
		return _kaleoLog.getPreviousKaleoNodeName();
	}

	/**
	* Returns the primary key of this kaleo log.
	*
	* @return the primary key of this kaleo log
	*/
	@Override
	public long getPrimaryKey() {
		return _kaleoLog.getPrimaryKey();
	}

	@Override
	public java.io.Serializable getPrimaryKeyObj() {
		return _kaleoLog.getPrimaryKeyObj();
	}

	/**
	* Returns the start date of this kaleo log.
	*
	* @return the start date of this kaleo log
	*/
	@Override
	public Date getStartDate() {
		return _kaleoLog.getStartDate();
	}

	/**
	* Returns the terminal kaleo node of this kaleo log.
	*
	* @return the terminal kaleo node of this kaleo log
	*/
	@Override
	public boolean getTerminalKaleoNode() {
		return _kaleoLog.getTerminalKaleoNode();
	}

	/**
	* Returns the type of this kaleo log.
	*
	* @return the type of this kaleo log
	*/
	@Override
	public java.lang.String getType() {
		return _kaleoLog.getType();
	}

	/**
	* Returns the user ID of this kaleo log.
	*
	* @return the user ID of this kaleo log
	*/
	@Override
	public long getUserId() {
		return _kaleoLog.getUserId();
	}

	/**
	* Returns the user name of this kaleo log.
	*
	* @return the user name of this kaleo log
	*/
	@Override
	public java.lang.String getUserName() {
		return _kaleoLog.getUserName();
	}

	/**
	* Returns the user uuid of this kaleo log.
	*
	* @return the user uuid of this kaleo log
	*/
	@Override
	public java.lang.String getUserUuid() {
		return _kaleoLog.getUserUuid();
	}

	/**
	* Returns the workflow context of this kaleo log.
	*
	* @return the workflow context of this kaleo log
	*/
	@Override
	public java.lang.String getWorkflowContext() {
		return _kaleoLog.getWorkflowContext();
	}

	@Override
	public int hashCode() {
		return _kaleoLog.hashCode();
	}

	@Override
	public boolean isCachedModel() {
		return _kaleoLog.isCachedModel();
	}

	@Override
	public boolean isEscapedModel() {
		return _kaleoLog.isEscapedModel();
	}

	@Override
	public boolean isNew() {
		return _kaleoLog.isNew();
	}

	/**
	* Returns <code>true</code> if this kaleo log is terminal kaleo node.
	*
	* @return <code>true</code> if this kaleo log is terminal kaleo node; <code>false</code> otherwise
	*/
	@Override
	public boolean isTerminalKaleoNode() {
		return _kaleoLog.isTerminalKaleoNode();
	}

	@Override
	public void persist() {
		_kaleoLog.persist();
	}

	@Override
	public void setCachedModel(boolean cachedModel) {
		_kaleoLog.setCachedModel(cachedModel);
	}

	/**
	* Sets the comment of this kaleo log.
	*
	* @param comment the comment of this kaleo log
	*/
	@Override
	public void setComment(java.lang.String comment) {
		_kaleoLog.setComment(comment);
	}

	/**
	* Sets the company ID of this kaleo log.
	*
	* @param companyId the company ID of this kaleo log
	*/
	@Override
	public void setCompanyId(long companyId) {
		_kaleoLog.setCompanyId(companyId);
	}

	/**
	* Sets the create date of this kaleo log.
	*
	* @param createDate the create date of this kaleo log
	*/
	@Override
	public void setCreateDate(Date createDate) {
		_kaleoLog.setCreateDate(createDate);
	}

	/**
	* Sets the current assignee class name of this kaleo log.
	*
	* @param currentAssigneeClassName the current assignee class name of this kaleo log
	*/
	@Override
	public void setCurrentAssigneeClassName(
		java.lang.String currentAssigneeClassName) {
		_kaleoLog.setCurrentAssigneeClassName(currentAssigneeClassName);
	}

	/**
	* Sets the current assignee class p k of this kaleo log.
	*
	* @param currentAssigneeClassPK the current assignee class p k of this kaleo log
	*/
	@Override
	public void setCurrentAssigneeClassPK(long currentAssigneeClassPK) {
		_kaleoLog.setCurrentAssigneeClassPK(currentAssigneeClassPK);
	}

	/**
	* Sets the duration of this kaleo log.
	*
	* @param duration the duration of this kaleo log
	*/
	@Override
	public void setDuration(long duration) {
		_kaleoLog.setDuration(duration);
	}

	/**
	* Sets the end date of this kaleo log.
	*
	* @param endDate the end date of this kaleo log
	*/
	@Override
	public void setEndDate(Date endDate) {
		_kaleoLog.setEndDate(endDate);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.model.BaseModel<?> baseModel) {
		_kaleoLog.setExpandoBridgeAttributes(baseModel);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portlet.expando.model.ExpandoBridge expandoBridge) {
		_kaleoLog.setExpandoBridgeAttributes(expandoBridge);
	}

	@Override
	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_kaleoLog.setExpandoBridgeAttributes(serviceContext);
	}

	/**
	* Sets the group ID of this kaleo log.
	*
	* @param groupId the group ID of this kaleo log
	*/
	@Override
	public void setGroupId(long groupId) {
		_kaleoLog.setGroupId(groupId);
	}

	/**
	* Sets the kaleo action description of this kaleo log.
	*
	* @param kaleoActionDescription the kaleo action description of this kaleo log
	*/
	@Override
	public void setKaleoActionDescription(
		java.lang.String kaleoActionDescription) {
		_kaleoLog.setKaleoActionDescription(kaleoActionDescription);
	}

	/**
	* Sets the kaleo action ID of this kaleo log.
	*
	* @param kaleoActionId the kaleo action ID of this kaleo log
	*/
	@Override
	public void setKaleoActionId(long kaleoActionId) {
		_kaleoLog.setKaleoActionId(kaleoActionId);
	}

	/**
	* Sets the kaleo action name of this kaleo log.
	*
	* @param kaleoActionName the kaleo action name of this kaleo log
	*/
	@Override
	public void setKaleoActionName(java.lang.String kaleoActionName) {
		_kaleoLog.setKaleoActionName(kaleoActionName);
	}

	/**
	* Sets the kaleo class name of this kaleo log.
	*
	* @param kaleoClassName the kaleo class name of this kaleo log
	*/
	@Override
	public void setKaleoClassName(java.lang.String kaleoClassName) {
		_kaleoLog.setKaleoClassName(kaleoClassName);
	}

	/**
	* Sets the kaleo class p k of this kaleo log.
	*
	* @param kaleoClassPK the kaleo class p k of this kaleo log
	*/
	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoLog.setKaleoClassPK(kaleoClassPK);
	}

	/**
	* Sets the kaleo definition ID of this kaleo log.
	*
	* @param kaleoDefinitionId the kaleo definition ID of this kaleo log
	*/
	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoLog.setKaleoDefinitionId(kaleoDefinitionId);
	}

	/**
	* Sets the kaleo instance ID of this kaleo log.
	*
	* @param kaleoInstanceId the kaleo instance ID of this kaleo log
	*/
	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoLog.setKaleoInstanceId(kaleoInstanceId);
	}

	/**
	* Sets the kaleo instance token ID of this kaleo log.
	*
	* @param kaleoInstanceTokenId the kaleo instance token ID of this kaleo log
	*/
	@Override
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoLog.setKaleoInstanceTokenId(kaleoInstanceTokenId);
	}

	/**
	* Sets the kaleo log ID of this kaleo log.
	*
	* @param kaleoLogId the kaleo log ID of this kaleo log
	*/
	@Override
	public void setKaleoLogId(long kaleoLogId) {
		_kaleoLog.setKaleoLogId(kaleoLogId);
	}

	/**
	* Sets the kaleo node name of this kaleo log.
	*
	* @param kaleoNodeName the kaleo node name of this kaleo log
	*/
	@Override
	public void setKaleoNodeName(java.lang.String kaleoNodeName) {
		_kaleoLog.setKaleoNodeName(kaleoNodeName);
	}

	/**
	* Sets the kaleo task instance token ID of this kaleo log.
	*
	* @param kaleoTaskInstanceTokenId the kaleo task instance token ID of this kaleo log
	*/
	@Override
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoLog.setKaleoTaskInstanceTokenId(kaleoTaskInstanceTokenId);
	}

	/**
	* Sets the modified date of this kaleo log.
	*
	* @param modifiedDate the modified date of this kaleo log
	*/
	@Override
	public void setModifiedDate(Date modifiedDate) {
		_kaleoLog.setModifiedDate(modifiedDate);
	}

	@Override
	public void setNew(boolean n) {
		_kaleoLog.setNew(n);
	}

	/**
	* Sets the previous assignee class name of this kaleo log.
	*
	* @param previousAssigneeClassName the previous assignee class name of this kaleo log
	*/
	@Override
	public void setPreviousAssigneeClassName(
		java.lang.String previousAssigneeClassName) {
		_kaleoLog.setPreviousAssigneeClassName(previousAssigneeClassName);
	}

	/**
	* Sets the previous assignee class p k of this kaleo log.
	*
	* @param previousAssigneeClassPK the previous assignee class p k of this kaleo log
	*/
	@Override
	public void setPreviousAssigneeClassPK(long previousAssigneeClassPK) {
		_kaleoLog.setPreviousAssigneeClassPK(previousAssigneeClassPK);
	}

	/**
	* Sets the previous kaleo node ID of this kaleo log.
	*
	* @param previousKaleoNodeId the previous kaleo node ID of this kaleo log
	*/
	@Override
	public void setPreviousKaleoNodeId(long previousKaleoNodeId) {
		_kaleoLog.setPreviousKaleoNodeId(previousKaleoNodeId);
	}

	/**
	* Sets the previous kaleo node name of this kaleo log.
	*
	* @param previousKaleoNodeName the previous kaleo node name of this kaleo log
	*/
	@Override
	public void setPreviousKaleoNodeName(java.lang.String previousKaleoNodeName) {
		_kaleoLog.setPreviousKaleoNodeName(previousKaleoNodeName);
	}

	/**
	* Sets the primary key of this kaleo log.
	*
	* @param primaryKey the primary key of this kaleo log
	*/
	@Override
	public void setPrimaryKey(long primaryKey) {
		_kaleoLog.setPrimaryKey(primaryKey);
	}

	@Override
	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_kaleoLog.setPrimaryKeyObj(primaryKeyObj);
	}

	/**
	* Sets the start date of this kaleo log.
	*
	* @param startDate the start date of this kaleo log
	*/
	@Override
	public void setStartDate(Date startDate) {
		_kaleoLog.setStartDate(startDate);
	}

	/**
	* Sets whether this kaleo log is terminal kaleo node.
	*
	* @param terminalKaleoNode the terminal kaleo node of this kaleo log
	*/
	@Override
	public void setTerminalKaleoNode(boolean terminalKaleoNode) {
		_kaleoLog.setTerminalKaleoNode(terminalKaleoNode);
	}

	/**
	* Sets the type of this kaleo log.
	*
	* @param type the type of this kaleo log
	*/
	@Override
	public void setType(java.lang.String type) {
		_kaleoLog.setType(type);
	}

	/**
	* Sets the user ID of this kaleo log.
	*
	* @param userId the user ID of this kaleo log
	*/
	@Override
	public void setUserId(long userId) {
		_kaleoLog.setUserId(userId);
	}

	/**
	* Sets the user name of this kaleo log.
	*
	* @param userName the user name of this kaleo log
	*/
	@Override
	public void setUserName(java.lang.String userName) {
		_kaleoLog.setUserName(userName);
	}

	/**
	* Sets the user uuid of this kaleo log.
	*
	* @param userUuid the user uuid of this kaleo log
	*/
	@Override
	public void setUserUuid(java.lang.String userUuid) {
		_kaleoLog.setUserUuid(userUuid);
	}

	/**
	* Sets the workflow context of this kaleo log.
	*
	* @param workflowContext the workflow context of this kaleo log
	*/
	@Override
	public void setWorkflowContext(java.lang.String workflowContext) {
		_kaleoLog.setWorkflowContext(workflowContext);
	}

	@Override
	public com.liferay.portal.model.CacheModel<com.liferay.portal.workflow.kaleo.model.KaleoLog> toCacheModel() {
		return _kaleoLog.toCacheModel();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog toEscapedModel() {
		return new KaleoLogWrapper(_kaleoLog.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _kaleoLog.toString();
	}

	@Override
	public com.liferay.portal.workflow.kaleo.model.KaleoLog toUnescapedModel() {
		return new KaleoLogWrapper(_kaleoLog.toUnescapedModel());
	}

	@Override
	public java.lang.String toXmlString() {
		return _kaleoLog.toXmlString();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoLogWrapper)) {
			return false;
		}

		KaleoLogWrapper kaleoLogWrapper = (KaleoLogWrapper)obj;

		if (Validator.equals(_kaleoLog, kaleoLogWrapper._kaleoLog)) {
			return true;
		}

		return false;
	}

	/**
	 * @deprecated As of 6.1.0, replaced by {@link #getWrappedModel}
	 */
	@Deprecated
	public KaleoLog getWrappedKaleoLog() {
		return _kaleoLog;
	}

	@Override
	public KaleoLog getWrappedModel() {
		return _kaleoLog;
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _kaleoLog.isEntityCacheEnabled();
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _kaleoLog.isFinderCacheEnabled();
	}

	@Override
	public void resetOriginalValues() {
		_kaleoLog.resetOriginalValues();
	}

	private final KaleoLog _kaleoLog;
}