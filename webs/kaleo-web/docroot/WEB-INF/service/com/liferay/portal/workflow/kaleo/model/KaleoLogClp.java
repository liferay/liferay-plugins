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

import com.liferay.portal.kernel.bean.AutoEscapeBeanHandler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.ProxyUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.BaseModel;
import com.liferay.portal.model.User;
import com.liferay.portal.model.impl.BaseModelImpl;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.workflow.kaleo.service.ClpSerializer;
import com.liferay.portal.workflow.kaleo.service.KaleoLogLocalServiceUtil;

import java.io.Serializable;

import java.lang.reflect.Method;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
@ProviderType
public class KaleoLogClp extends BaseModelImpl<KaleoLog> implements KaleoLog {
	public KaleoLogClp() {
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
	public long getPrimaryKey() {
		return _kaleoLogId;
	}

	@Override
	public void setPrimaryKey(long primaryKey) {
		setKaleoLogId(primaryKey);
	}

	@Override
	public Serializable getPrimaryKeyObj() {
		return _kaleoLogId;
	}

	@Override
	public void setPrimaryKeyObj(Serializable primaryKeyObj) {
		setPrimaryKey(((Long)primaryKeyObj).longValue());
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

		attributes.put("entityCacheEnabled", isEntityCacheEnabled());
		attributes.put("finderCacheEnabled", isFinderCacheEnabled());

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

		_entityCacheEnabled = GetterUtil.getBoolean("entityCacheEnabled");
		_finderCacheEnabled = GetterUtil.getBoolean("finderCacheEnabled");
	}

	@Override
	public long getKaleoLogId() {
		return _kaleoLogId;
	}

	@Override
	public void setKaleoLogId(long kaleoLogId) {
		_kaleoLogId = kaleoLogId;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoLogId", long.class);

				method.invoke(_kaleoLogRemoteModel, kaleoLogId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getGroupId() {
		return _groupId;
	}

	@Override
	public void setGroupId(long groupId) {
		_groupId = groupId;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setGroupId", long.class);

				method.invoke(_kaleoLogRemoteModel, groupId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCompanyId() {
		return _companyId;
	}

	@Override
	public void setCompanyId(long companyId) {
		_companyId = companyId;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setCompanyId", long.class);

				method.invoke(_kaleoLogRemoteModel, companyId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getUserId() {
		return _userId;
	}

	@Override
	public void setUserId(long userId) {
		_userId = userId;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setUserId", long.class);

				method.invoke(_kaleoLogRemoteModel, userId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getUserUuid() {
		try {
			User user = UserLocalServiceUtil.getUserById(getUserId());

			return user.getUuid();
		}
		catch (PortalException pe) {
			return StringPool.BLANK;
		}
	}

	@Override
	public void setUserUuid(String userUuid) {
	}

	@Override
	public String getUserName() {
		return _userName;
	}

	@Override
	public void setUserName(String userName) {
		_userName = userName;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setUserName", String.class);

				method.invoke(_kaleoLogRemoteModel, userName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getCreateDate() {
		return _createDate;
	}

	@Override
	public void setCreateDate(Date createDate) {
		_createDate = createDate;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setCreateDate", Date.class);

				method.invoke(_kaleoLogRemoteModel, createDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getModifiedDate() {
		return _modifiedDate;
	}

	@Override
	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setModifiedDate", Date.class);

				method.invoke(_kaleoLogRemoteModel, modifiedDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKaleoClassName() {
		return _kaleoClassName;
	}

	@Override
	public void setKaleoClassName(String kaleoClassName) {
		_kaleoClassName = kaleoClassName;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoClassName",
						String.class);

				method.invoke(_kaleoLogRemoteModel, kaleoClassName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoClassPK() {
		return _kaleoClassPK;
	}

	@Override
	public void setKaleoClassPK(long kaleoClassPK) {
		_kaleoClassPK = kaleoClassPK;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoClassPK", long.class);

				method.invoke(_kaleoLogRemoteModel, kaleoClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoDefinitionId() {
		return _kaleoDefinitionId;
	}

	@Override
	public void setKaleoDefinitionId(long kaleoDefinitionId) {
		_kaleoDefinitionId = kaleoDefinitionId;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoDefinitionId",
						long.class);

				method.invoke(_kaleoLogRemoteModel, kaleoDefinitionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoInstanceId() {
		return _kaleoInstanceId;
	}

	@Override
	public void setKaleoInstanceId(long kaleoInstanceId) {
		_kaleoInstanceId = kaleoInstanceId;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoInstanceId", long.class);

				method.invoke(_kaleoLogRemoteModel, kaleoInstanceId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoInstanceTokenId() {
		return _kaleoInstanceTokenId;
	}

	@Override
	public void setKaleoInstanceTokenId(long kaleoInstanceTokenId) {
		_kaleoInstanceTokenId = kaleoInstanceTokenId;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoInstanceTokenId",
						long.class);

				method.invoke(_kaleoLogRemoteModel, kaleoInstanceTokenId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoTaskInstanceTokenId() {
		return _kaleoTaskInstanceTokenId;
	}

	@Override
	public void setKaleoTaskInstanceTokenId(long kaleoTaskInstanceTokenId) {
		_kaleoTaskInstanceTokenId = kaleoTaskInstanceTokenId;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoTaskInstanceTokenId",
						long.class);

				method.invoke(_kaleoLogRemoteModel, kaleoTaskInstanceTokenId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKaleoNodeName() {
		return _kaleoNodeName;
	}

	@Override
	public void setKaleoNodeName(String kaleoNodeName) {
		_kaleoNodeName = kaleoNodeName;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoNodeName", String.class);

				method.invoke(_kaleoLogRemoteModel, kaleoNodeName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public boolean getTerminalKaleoNode() {
		return _terminalKaleoNode;
	}

	@Override
	public boolean isTerminalKaleoNode() {
		return _terminalKaleoNode;
	}

	@Override
	public void setTerminalKaleoNode(boolean terminalKaleoNode) {
		_terminalKaleoNode = terminalKaleoNode;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setTerminalKaleoNode",
						boolean.class);

				method.invoke(_kaleoLogRemoteModel, terminalKaleoNode);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getKaleoActionId() {
		return _kaleoActionId;
	}

	@Override
	public void setKaleoActionId(long kaleoActionId) {
		_kaleoActionId = kaleoActionId;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoActionId", long.class);

				method.invoke(_kaleoLogRemoteModel, kaleoActionId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKaleoActionName() {
		return _kaleoActionName;
	}

	@Override
	public void setKaleoActionName(String kaleoActionName) {
		_kaleoActionName = kaleoActionName;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoActionName",
						String.class);

				method.invoke(_kaleoLogRemoteModel, kaleoActionName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getKaleoActionDescription() {
		return _kaleoActionDescription;
	}

	@Override
	public void setKaleoActionDescription(String kaleoActionDescription) {
		_kaleoActionDescription = kaleoActionDescription;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setKaleoActionDescription",
						String.class);

				method.invoke(_kaleoLogRemoteModel, kaleoActionDescription);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getPreviousKaleoNodeId() {
		return _previousKaleoNodeId;
	}

	@Override
	public void setPreviousKaleoNodeId(long previousKaleoNodeId) {
		_previousKaleoNodeId = previousKaleoNodeId;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setPreviousKaleoNodeId",
						long.class);

				method.invoke(_kaleoLogRemoteModel, previousKaleoNodeId);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPreviousKaleoNodeName() {
		return _previousKaleoNodeName;
	}

	@Override
	public void setPreviousKaleoNodeName(String previousKaleoNodeName) {
		_previousKaleoNodeName = previousKaleoNodeName;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setPreviousKaleoNodeName",
						String.class);

				method.invoke(_kaleoLogRemoteModel, previousKaleoNodeName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getPreviousAssigneeClassName() {
		return _previousAssigneeClassName;
	}

	@Override
	public void setPreviousAssigneeClassName(String previousAssigneeClassName) {
		_previousAssigneeClassName = previousAssigneeClassName;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setPreviousAssigneeClassName",
						String.class);

				method.invoke(_kaleoLogRemoteModel, previousAssigneeClassName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getPreviousAssigneeClassPK() {
		return _previousAssigneeClassPK;
	}

	@Override
	public void setPreviousAssigneeClassPK(long previousAssigneeClassPK) {
		_previousAssigneeClassPK = previousAssigneeClassPK;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setPreviousAssigneeClassPK",
						long.class);

				method.invoke(_kaleoLogRemoteModel, previousAssigneeClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getCurrentAssigneeClassName() {
		return _currentAssigneeClassName;
	}

	@Override
	public void setCurrentAssigneeClassName(String currentAssigneeClassName) {
		_currentAssigneeClassName = currentAssigneeClassName;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrentAssigneeClassName",
						String.class);

				method.invoke(_kaleoLogRemoteModel, currentAssigneeClassName);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getCurrentAssigneeClassPK() {
		return _currentAssigneeClassPK;
	}

	@Override
	public void setCurrentAssigneeClassPK(long currentAssigneeClassPK) {
		_currentAssigneeClassPK = currentAssigneeClassPK;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setCurrentAssigneeClassPK",
						long.class);

				method.invoke(_kaleoLogRemoteModel, currentAssigneeClassPK);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getType() {
		return _type;
	}

	@Override
	public void setType(String type) {
		_type = type;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setType", String.class);

				method.invoke(_kaleoLogRemoteModel, type);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getComment() {
		return _comment;
	}

	@Override
	public void setComment(String comment) {
		_comment = comment;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setComment", String.class);

				method.invoke(_kaleoLogRemoteModel, comment);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getStartDate() {
		return _startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		_startDate = startDate;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setStartDate", Date.class);

				method.invoke(_kaleoLogRemoteModel, startDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public Date getEndDate() {
		return _endDate;
	}

	@Override
	public void setEndDate(Date endDate) {
		_endDate = endDate;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setEndDate", Date.class);

				method.invoke(_kaleoLogRemoteModel, endDate);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public long getDuration() {
		return _duration;
	}

	@Override
	public void setDuration(long duration) {
		_duration = duration;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setDuration", long.class);

				method.invoke(_kaleoLogRemoteModel, duration);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	@Override
	public String getWorkflowContext() {
		return _workflowContext;
	}

	@Override
	public void setWorkflowContext(String workflowContext) {
		_workflowContext = workflowContext;

		if (_kaleoLogRemoteModel != null) {
			try {
				Class<?> clazz = _kaleoLogRemoteModel.getClass();

				Method method = clazz.getMethod("setWorkflowContext",
						String.class);

				method.invoke(_kaleoLogRemoteModel, workflowContext);
			}
			catch (Exception e) {
				throw new UnsupportedOperationException(e);
			}
		}
	}

	public BaseModel<?> getKaleoLogRemoteModel() {
		return _kaleoLogRemoteModel;
	}

	public void setKaleoLogRemoteModel(BaseModel<?> kaleoLogRemoteModel) {
		_kaleoLogRemoteModel = kaleoLogRemoteModel;
	}

	public Object invokeOnRemoteModel(String methodName,
		Class<?>[] parameterTypes, Object[] parameterValues)
		throws Exception {
		Object[] remoteParameterValues = new Object[parameterValues.length];

		for (int i = 0; i < parameterValues.length; i++) {
			if (parameterValues[i] != null) {
				remoteParameterValues[i] = ClpSerializer.translateInput(parameterValues[i]);
			}
		}

		Class<?> remoteModelClass = _kaleoLogRemoteModel.getClass();

		ClassLoader remoteModelClassLoader = remoteModelClass.getClassLoader();

		Class<?>[] remoteParameterTypes = new Class[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			if (parameterTypes[i].isPrimitive()) {
				remoteParameterTypes[i] = parameterTypes[i];
			}
			else {
				String parameterTypeName = parameterTypes[i].getName();

				remoteParameterTypes[i] = remoteModelClassLoader.loadClass(parameterTypeName);
			}
		}

		Method method = remoteModelClass.getMethod(methodName,
				remoteParameterTypes);

		Object returnValue = method.invoke(_kaleoLogRemoteModel,
				remoteParameterValues);

		if (returnValue != null) {
			returnValue = ClpSerializer.translateOutput(returnValue);
		}

		return returnValue;
	}

	@Override
	public void persist() {
		if (this.isNew()) {
			KaleoLogLocalServiceUtil.addKaleoLog(this);
		}
		else {
			KaleoLogLocalServiceUtil.updateKaleoLog(this);
		}
	}

	@Override
	public KaleoLog toEscapedModel() {
		return (KaleoLog)ProxyUtil.newProxyInstance(KaleoLog.class.getClassLoader(),
			new Class[] { KaleoLog.class }, new AutoEscapeBeanHandler(this));
	}

	@Override
	public Object clone() {
		KaleoLogClp clone = new KaleoLogClp();

		clone.setKaleoLogId(getKaleoLogId());
		clone.setGroupId(getGroupId());
		clone.setCompanyId(getCompanyId());
		clone.setUserId(getUserId());
		clone.setUserName(getUserName());
		clone.setCreateDate(getCreateDate());
		clone.setModifiedDate(getModifiedDate());
		clone.setKaleoClassName(getKaleoClassName());
		clone.setKaleoClassPK(getKaleoClassPK());
		clone.setKaleoDefinitionId(getKaleoDefinitionId());
		clone.setKaleoInstanceId(getKaleoInstanceId());
		clone.setKaleoInstanceTokenId(getKaleoInstanceTokenId());
		clone.setKaleoTaskInstanceTokenId(getKaleoTaskInstanceTokenId());
		clone.setKaleoNodeName(getKaleoNodeName());
		clone.setTerminalKaleoNode(getTerminalKaleoNode());
		clone.setKaleoActionId(getKaleoActionId());
		clone.setKaleoActionName(getKaleoActionName());
		clone.setKaleoActionDescription(getKaleoActionDescription());
		clone.setPreviousKaleoNodeId(getPreviousKaleoNodeId());
		clone.setPreviousKaleoNodeName(getPreviousKaleoNodeName());
		clone.setPreviousAssigneeClassName(getPreviousAssigneeClassName());
		clone.setPreviousAssigneeClassPK(getPreviousAssigneeClassPK());
		clone.setCurrentAssigneeClassName(getCurrentAssigneeClassName());
		clone.setCurrentAssigneeClassPK(getCurrentAssigneeClassPK());
		clone.setType(getType());
		clone.setComment(getComment());
		clone.setStartDate(getStartDate());
		clone.setEndDate(getEndDate());
		clone.setDuration(getDuration());
		clone.setWorkflowContext(getWorkflowContext());

		return clone;
	}

	@Override
	public int compareTo(KaleoLog kaleoLog) {
		int value = 0;

		if (getKaleoLogId() < kaleoLog.getKaleoLogId()) {
			value = -1;
		}
		else if (getKaleoLogId() > kaleoLog.getKaleoLogId()) {
			value = 1;
		}
		else {
			value = 0;
		}

		if (value != 0) {
			return value;
		}

		return 0;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}

		if (!(obj instanceof KaleoLogClp)) {
			return false;
		}

		KaleoLogClp kaleoLog = (KaleoLogClp)obj;

		long primaryKey = kaleoLog.getPrimaryKey();

		if (getPrimaryKey() == primaryKey) {
			return true;
		}
		else {
			return false;
		}
	}

	public Class<?> getClpSerializerClass() {
		return _clpSerializerClass;
	}

	@Override
	public int hashCode() {
		return (int)getPrimaryKey();
	}

	@Override
	public boolean isEntityCacheEnabled() {
		return _entityCacheEnabled;
	}

	@Override
	public boolean isFinderCacheEnabled() {
		return _finderCacheEnabled;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(61);

		sb.append("{kaleoLogId=");
		sb.append(getKaleoLogId());
		sb.append(", groupId=");
		sb.append(getGroupId());
		sb.append(", companyId=");
		sb.append(getCompanyId());
		sb.append(", userId=");
		sb.append(getUserId());
		sb.append(", userName=");
		sb.append(getUserName());
		sb.append(", createDate=");
		sb.append(getCreateDate());
		sb.append(", modifiedDate=");
		sb.append(getModifiedDate());
		sb.append(", kaleoClassName=");
		sb.append(getKaleoClassName());
		sb.append(", kaleoClassPK=");
		sb.append(getKaleoClassPK());
		sb.append(", kaleoDefinitionId=");
		sb.append(getKaleoDefinitionId());
		sb.append(", kaleoInstanceId=");
		sb.append(getKaleoInstanceId());
		sb.append(", kaleoInstanceTokenId=");
		sb.append(getKaleoInstanceTokenId());
		sb.append(", kaleoTaskInstanceTokenId=");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append(", kaleoNodeName=");
		sb.append(getKaleoNodeName());
		sb.append(", terminalKaleoNode=");
		sb.append(getTerminalKaleoNode());
		sb.append(", kaleoActionId=");
		sb.append(getKaleoActionId());
		sb.append(", kaleoActionName=");
		sb.append(getKaleoActionName());
		sb.append(", kaleoActionDescription=");
		sb.append(getKaleoActionDescription());
		sb.append(", previousKaleoNodeId=");
		sb.append(getPreviousKaleoNodeId());
		sb.append(", previousKaleoNodeName=");
		sb.append(getPreviousKaleoNodeName());
		sb.append(", previousAssigneeClassName=");
		sb.append(getPreviousAssigneeClassName());
		sb.append(", previousAssigneeClassPK=");
		sb.append(getPreviousAssigneeClassPK());
		sb.append(", currentAssigneeClassName=");
		sb.append(getCurrentAssigneeClassName());
		sb.append(", currentAssigneeClassPK=");
		sb.append(getCurrentAssigneeClassPK());
		sb.append(", type=");
		sb.append(getType());
		sb.append(", comment=");
		sb.append(getComment());
		sb.append(", startDate=");
		sb.append(getStartDate());
		sb.append(", endDate=");
		sb.append(getEndDate());
		sb.append(", duration=");
		sb.append(getDuration());
		sb.append(", workflowContext=");
		sb.append(getWorkflowContext());
		sb.append("}");

		return sb.toString();
	}

	@Override
	public String toXmlString() {
		StringBundler sb = new StringBundler(94);

		sb.append("<model><model-name>");
		sb.append("com.liferay.portal.workflow.kaleo.model.KaleoLog");
		sb.append("</model-name>");

		sb.append(
			"<column><column-name>kaleoLogId</column-name><column-value><![CDATA[");
		sb.append(getKaleoLogId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>groupId</column-name><column-value><![CDATA[");
		sb.append(getGroupId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>companyId</column-name><column-value><![CDATA[");
		sb.append(getCompanyId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userId</column-name><column-value><![CDATA[");
		sb.append(getUserId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>userName</column-name><column-value><![CDATA[");
		sb.append(getUserName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>createDate</column-name><column-value><![CDATA[");
		sb.append(getCreateDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>modifiedDate</column-name><column-value><![CDATA[");
		sb.append(getModifiedDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoClassName</column-name><column-value><![CDATA[");
		sb.append(getKaleoClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoClassPK</column-name><column-value><![CDATA[");
		sb.append(getKaleoClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoDefinitionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoDefinitionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoInstanceId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoTaskInstanceTokenId</column-name><column-value><![CDATA[");
		sb.append(getKaleoTaskInstanceTokenId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>terminalKaleoNode</column-name><column-value><![CDATA[");
		sb.append(getTerminalKaleoNode());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoActionId</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoActionName</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>kaleoActionDescription</column-name><column-value><![CDATA[");
		sb.append(getKaleoActionDescription());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousKaleoNodeId</column-name><column-value><![CDATA[");
		sb.append(getPreviousKaleoNodeId());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousKaleoNodeName</column-name><column-value><![CDATA[");
		sb.append(getPreviousKaleoNodeName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousAssigneeClassName</column-name><column-value><![CDATA[");
		sb.append(getPreviousAssigneeClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>previousAssigneeClassPK</column-name><column-value><![CDATA[");
		sb.append(getPreviousAssigneeClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentAssigneeClassName</column-name><column-value><![CDATA[");
		sb.append(getCurrentAssigneeClassName());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>currentAssigneeClassPK</column-name><column-value><![CDATA[");
		sb.append(getCurrentAssigneeClassPK());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>type</column-name><column-value><![CDATA[");
		sb.append(getType());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>comment</column-name><column-value><![CDATA[");
		sb.append(getComment());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>startDate</column-name><column-value><![CDATA[");
		sb.append(getStartDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>endDate</column-name><column-value><![CDATA[");
		sb.append(getEndDate());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>duration</column-name><column-value><![CDATA[");
		sb.append(getDuration());
		sb.append("]]></column-value></column>");
		sb.append(
			"<column><column-name>workflowContext</column-name><column-value><![CDATA[");
		sb.append(getWorkflowContext());
		sb.append("]]></column-value></column>");

		sb.append("</model>");

		return sb.toString();
	}

	private long _kaleoLogId;
	private long _groupId;
	private long _companyId;
	private long _userId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _kaleoClassName;
	private long _kaleoClassPK;
	private long _kaleoDefinitionId;
	private long _kaleoInstanceId;
	private long _kaleoInstanceTokenId;
	private long _kaleoTaskInstanceTokenId;
	private String _kaleoNodeName;
	private boolean _terminalKaleoNode;
	private long _kaleoActionId;
	private String _kaleoActionName;
	private String _kaleoActionDescription;
	private long _previousKaleoNodeId;
	private String _previousKaleoNodeName;
	private String _previousAssigneeClassName;
	private long _previousAssigneeClassPK;
	private String _currentAssigneeClassName;
	private long _currentAssigneeClassPK;
	private String _type;
	private String _comment;
	private Date _startDate;
	private Date _endDate;
	private long _duration;
	private String _workflowContext;
	private BaseModel<?> _kaleoLogRemoteModel;
	private Class<?> _clpSerializerClass = ClpSerializer.class;
	private boolean _entityCacheEnabled;
	private boolean _finderCacheEnabled;
}